package br.fatec.imobfiscal.audit;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuditoriaFilter extends OncePerRequestFilter {

    private static final Set<String> METODOS_AUDITADOS = Set.of("POST", "PUT", "PATCH", "DELETE");
    private final AuditoriaService auditoriaService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } finally {
            registrarSeAplicavel(request, response);
        }
    }

    private void registrarSeAplicavel(HttpServletRequest request, HttpServletResponse response) {
        if (!METODOS_AUDITADOS.contains(request.getMethod())
                || request.getRequestURI().startsWith("/api/auth/")) {
            return;
        }

        Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();
        if (autenticacao == null || !autenticacao.isAuthenticated()
                || !(autenticacao.getDetails() instanceof Map<?, ?> detalhes)) {
            return;
        }

        UUID usuarioId = (UUID) detalhes.get("usuarioId");
        UUID imobiliariaId = (UUID) detalhes.get("imobiliariaId");
        auditoriaService.registrar(new RegistroAuditoria(
                imobiliariaId,
                usuarioId,
                autenticacao.getName(),
                acaoDoMetodo(request.getMethod()),
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                obterIp(request),
                limitar(request.getHeader("User-Agent"), 300)));
    }

    static AcaoAuditoria acaoDoMetodo(String metodo) {
        return switch (metodo) {
            case "POST" -> AcaoAuditoria.CRIACAO;
            case "DELETE" -> AcaoAuditoria.EXCLUSAO;
            default -> AcaoAuditoria.ALTERACAO;
        };
    }

    private String obterIp(HttpServletRequest request) {
        String encaminhado = request.getHeader("X-Forwarded-For");
        return encaminhado == null || encaminhado.isBlank()
                ? request.getRemoteAddr()
                : encaminhado.split(",")[0].trim();
    }

    private String limitar(String valor, int tamanhoMaximo) {
        if (valor == null || valor.length() <= tamanhoMaximo) {
            return valor;
        }
        return valor.substring(0, tamanhoMaximo);
    }
}
