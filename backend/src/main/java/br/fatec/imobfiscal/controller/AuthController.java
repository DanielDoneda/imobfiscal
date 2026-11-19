package br.fatec.imobfiscal.controller;

import br.fatec.imobfiscal.audit.AcaoAuditoria;
import br.fatec.imobfiscal.audit.AuditoriaService;
import br.fatec.imobfiscal.audit.RegistroAuditoria;
import br.fatec.imobfiscal.model.Usuario;
import br.fatec.imobfiscal.model.Imobiliaria;
import br.fatec.imobfiscal.enums.PerfilUsuario;
import br.fatec.imobfiscal.service.ImobiliariaService;
import br.fatec.imobfiscal.service.UsuarioService;
import br.fatec.imobfiscal.security.JwtService;
import br.fatec.imobfiscal.model.CadastroRequest;
import br.fatec.imobfiscal.model.LoginRequest;
import br.fatec.imobfiscal.model.LoginResponse;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final ImobiliariaService imobiliariaService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuditoriaService auditoriaService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request,
                                               HttpServletRequest httpRequest) {
        Usuario usuario = usuarioService.buscarPorEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos"));

        if (!passwordEncoder.matches(request.senha(), usuario.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos");
        }

        String token = jwtService.gerarToken(usuario);

        auditoriaService.registrar(new RegistroAuditoria(
                usuario.getImobiliariaId(), usuario.getId(), usuario.getEmail(),
                AcaoAuditoria.LOGIN, "POST", httpRequest.getRequestURI(), 200,
                httpRequest.getRemoteAddr(), httpRequest.getHeader("User-Agent")));

        return ResponseEntity.ok(new LoginResponse(
                token,
                usuario.getEmail(),
                usuario.getNome(),
                usuario.getPerfil().name(),
                usuario.getImobiliariaId().toString(),
                jwtService.getExpirationSeconds()));
    }

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody CadastroRequest request) {
        if (usuarioService.existePorEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        }

        if (imobiliariaService.existePorCnpj(request.cnpj())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF ou CNPJ já cadastrado");
        }

        Imobiliaria imobiliaria = imobiliariaService.criar(
                request.nomeImobiliaria(), request.cnpj(), request.email());
        usuarioService.cadastrar(imobiliaria.getId(), request.email(), request.senha(),
                request.nome(), PerfilUsuario.ADMIN);
        return ResponseEntity.status(201).build();
    }
}
