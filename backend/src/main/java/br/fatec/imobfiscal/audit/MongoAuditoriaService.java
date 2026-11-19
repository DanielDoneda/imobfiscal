package br.fatec.imobfiscal.audit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "app.audit.enabled", havingValue = "true")
public class MongoAuditoriaService implements AuditoriaService {

    private final LogAtividadeRepository repository;

    @Override
    public void registrar(RegistroAuditoria registro) {
        try {
            repository.save(LogAtividade.builder()
                    .imobiliariaId(registro.imobiliariaId())
                    .usuarioId(registro.usuarioId())
                    .usuarioEmail(registro.usuarioEmail())
                    .acao(registro.acao())
                    .metodoHttp(registro.metodoHttp())
                    .rota(registro.rota())
                    .statusHttp(registro.statusHttp())
                    .enderecoIp(registro.enderecoIp())
                    .navegador(registro.navegador())
                    .dataHora(Instant.now())
                    .build());
        } catch (RuntimeException ex) {
            // Auditoria nunca deve interromper a operacao principal do usuario.
            log.error("Nao foi possivel gravar o log de auditoria", ex);
        }
    }

    @Override
    public Page<LogAtividade> listar(UUID imobiliariaId, Pageable pageable) {
        return repository.findByImobiliariaId(imobiliariaId, pageable);
    }
}
