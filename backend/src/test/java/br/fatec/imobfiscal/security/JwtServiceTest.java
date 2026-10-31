package br.fatec.imobfiscal.security;

import br.fatec.imobfiscal.enums.PerfilUsuario;
import br.fatec.imobfiscal.model.Usuario;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtServiceTest {

    private static final String SECRET = "chave-de-testes-imobfiscal-com-mais-de-32-caracteres";

    @Test
    void deveGerarEValidarTokenComTenantEPerfil() {
        JwtService service = new JwtService(SECRET, 3_600_000);
        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        usuario.setImobiliariaId(UUID.randomUUID());
        usuario.setEmail("admin@imobfiscal.com.br");
        usuario.setPerfil(PerfilUsuario.ADMIN);

        Claims claims = service.validarEObterClaims(service.gerarToken(usuario));

        assertEquals(usuario.getEmail(), claims.getSubject());
        assertEquals(usuario.getImobiliariaId(), service.getImobiliariaId(claims));
        assertEquals("ADMIN", claims.get("perfil", String.class));
        assertEquals(3600, service.getExpirationSeconds());
    }

    @Test
    void deveRecusarChaveCurta() {
        assertThrows(IllegalArgumentException.class, () -> new JwtService("curta", 1000));
    }
}
