package br.fatec.imobfiscal.security;

import br.fatec.imobfiscal.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    private final SecretKey chave;
    private final long expirationMs;

    public JwtService(
            @Value("${app.security.jwt.secret}") String secret,
            @Value("${app.security.jwt.expiration-ms}") long expirationMs) {
        if (secret == null || secret.getBytes(StandardCharsets.UTF_8).length < 32) {
            throw new IllegalArgumentException("JWT_SECRET deve possuir pelo menos 32 caracteres");
        }
        this.chave = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    public String gerarToken(Usuario usuario) {
        Instant agora = Instant.now();
        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("usuarioId", usuario.getId().toString())
                .claim("imobiliariaId", usuario.getImobiliariaId().toString())
                .claim("perfil", usuario.getPerfil().name())
                .issuedAt(Date.from(agora))
                .expiration(Date.from(agora.plusMillis(expirationMs)))
                .signWith(chave)
                .compact();
    }

    public Claims validarEObterClaims(String token) {
        return Jwts.parser()
                .verifyWith(chave)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public UUID getImobiliariaId(Claims claims) {
        return UUID.fromString(claims.get("imobiliariaId", String.class));
    }

    public long getExpirationSeconds() {
        return expirationMs / 1000;
    }
}
