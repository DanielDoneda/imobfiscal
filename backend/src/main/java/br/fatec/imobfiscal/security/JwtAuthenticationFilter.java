package br.fatec.imobfiscal.security;

import br.fatec.imobfiscal.model.Usuario;
import br.fatec.imobfiscal.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Pattern ROTA_IMOBILIARIA =
            Pattern.compile("^/api/imobiliarias/([0-9a-fA-F-]{36})(?:/.*)?$");

    private final JwtService jwtService;
    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Claims claims = jwtService.validarEObterClaims(authorization.substring(7));
            Usuario usuario = usuarioService.buscarPorEmail(claims.getSubject())
                    .orElseThrow(() -> new JwtException("Usuário não encontrado"));

            UUID imobiliariaDoToken = jwtService.getImobiliariaId(claims);
            if (!usuario.getImobiliariaId().equals(imobiliariaDoToken)) {
                escreverErro(response, HttpStatus.UNAUTHORIZED, "Token inválido");
                return;
            }

            Matcher matcher = ROTA_IMOBILIARIA.matcher(request.getRequestURI());
            if (matcher.matches() && !imobiliariaDoToken.equals(UUID.fromString(matcher.group(1)))) {
                escreverErro(response, HttpStatus.FORBIDDEN,
                        "Acesso negado aos dados de outra imobiliária");
                return;
            }

            var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getPerfil().name()));
            var authentication = new UsernamePasswordAuthenticationToken(
                    usuario.getEmail(), null, authorities);
            authentication.setDetails(Map.of(
                    "usuarioId", usuario.getId(),
                    "imobiliariaId", usuario.getImobiliariaId()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (JwtException | IllegalArgumentException ex) {
            SecurityContextHolder.clearContext();
            escreverErro(response, HttpStatus.UNAUTHORIZED, "Token inválido ou expirado");
        }
    }

    private void escreverErro(HttpServletResponse response, HttpStatus status, String mensagem)
            throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), Map.of("message", mensagem));
    }
}
