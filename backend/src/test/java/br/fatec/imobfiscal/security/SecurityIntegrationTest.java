package br.fatec.imobfiscal.security;

import br.fatec.imobfiscal.config.DataInitializer;
import br.fatec.imobfiscal.enums.PerfilUsuario;
import br.fatec.imobfiscal.model.Usuario;
import br.fatec.imobfiscal.repository.UsuarioRepository;
import br.fatec.imobfiscal.repository.ImobiliariaRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SecurityIntegrationTest {

    private static final UUID IMOBILIARIA_ID =
            UUID.fromString("11111111-1111-1111-1111-111111111111");

    @Autowired private MockMvc mockMvc;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ImobiliariaRepository imobiliariaRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private DataInitializer dataInitializer;

    @BeforeEach
    void prepararUsuario() {
        usuarioRepository.deleteAll();
        imobiliariaRepository.deleteAll();
        Usuario usuario = new Usuario();
        usuario.setImobiliariaId(IMOBILIARIA_ID);
        usuario.setEmail("admin@imobfiscal.com.br");
        usuario.setSenha(passwordEncoder.encode("admin123"));
        usuario.setNome("Admin ImobFiscal");
        usuario.setPerfil(PerfilUsuario.ADMIN);
        usuarioRepository.save(usuario);
    }

    @Test
    void cadastroPublicoDeveCriarImobiliariaEUsuarioAdministrador() throws Exception {
        mockMvc.perform(post("/api/auth/cadastro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nome":"Maria Gestora",
                                  "nomeImobiliaria":"Imobiliária Horizonte",
                                  "cnpj":"12345678000190",
                                  "email":"maria@horizonte.com.br",
                                  "senha":"senha123"
                                }
                                """))
                .andExpect(status().isCreated());

        Usuario usuario = usuarioRepository.findByEmailAndDeletedAtIsNull("maria@horizonte.com.br")
                .orElseThrow();
        org.junit.jupiter.api.Assertions.assertEquals(PerfilUsuario.ADMIN, usuario.getPerfil());
        org.junit.jupiter.api.Assertions.assertTrue(passwordEncoder.matches("senha123", usuario.getSenha()));
        org.junit.jupiter.api.Assertions.assertTrue(
                imobiliariaRepository.findByIdAndDeletedAtIsNull(usuario.getImobiliariaId()).isPresent());
    }

    @Test
    void cadastroPublicoDeveRecusarCnpjDuplicado() throws Exception {
        String cadastro = """
                {
                  "nome":"Maria Gestora",
                  "nomeImobiliaria":"Imobiliária Horizonte",
                  "cnpj":"12345678000190",
                  "email":"%s",
                  "senha":"senha123"
                }
                """;

        mockMvc.perform(post("/api/auth/cadastro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cadastro.formatted("maria@horizonte.com.br")))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/auth/cadastro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cadastro.formatted("outra@horizonte.com.br")))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("CPF ou CNPJ já cadastrado"));
    }

    @Test
    void cadastroPublicoDeveAceitarCpf() throws Exception {
        mockMvc.perform(post("/api/auth/cadastro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nome":"João Proprietário",
                                  "nomeImobiliaria":"João Proprietário",
                                  "cnpj":"12345678901",
                                  "email":"joao@proprietario.com.br",
                                  "senha":"senha123"
                                }
                                """))
                .andExpect(status().isCreated());

        Usuario usuario = usuarioRepository.findByEmailAndDeletedAtIsNull("joao@proprietario.com.br")
                .orElseThrow();
        org.junit.jupiter.api.Assertions.assertEquals(PerfilUsuario.ADMIN, usuario.getPerfil());
    }

    @Test
    void healthDevePermanecerPublico() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ok"));
    }

    @Test
    void rotaProtegidaSemTokenDeveRetornar401() throws Exception {
        mockMvc.perform(get("/api/imobiliarias/{id}/imoveis", IMOBILIARIA_ID))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Autenticação necessária"));
    }

    @Test
    void loginDeveEmitirJwtEPermitirAcessoAoProprioTenant() throws Exception {
        String token = autenticar();

        mockMvc.perform(get("/api/imobiliarias/{id}/imoveis", IMOBILIARIA_ID)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void tokenNaoDeveAcessarOutraImobiliaria() throws Exception {
        String token = autenticar();
        UUID outraImobiliaria = UUID.fromString("22222222-2222-2222-2222-222222222222");

        mockMvc.perform(get("/api/imobiliarias/{id}/imoveis", outraImobiliaria)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message")
                        .value("Acesso negado aos dados de outra imobiliária"));
    }

    @Test
    void beanValidationDeveRetornarErrosPorCampo() throws Exception {
        String token = autenticar();

        mockMvc.perform(post("/api/imobiliarias/{id}/imoveis", IMOBILIARIA_ID)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.codigo").exists())
                .andExpect(jsonPath("$.errors.cep").exists());
    }

    private String autenticar() throws Exception {
        String resposta = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"email":"admin@imobfiscal.com.br","senha":"admin123"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.imobiliariaId").value(IMOBILIARIA_ID.toString()))
                .andReturn().getResponse().getContentAsString();

        JsonNode json = objectMapper.readTree(resposta);
        return json.get("token").asText();
    }
}
