package br.fatec.imobfiscal.audit;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@ConditionalOnProperty(name = "app.audit.enabled", havingValue = "false", matchIfMissing = true)
public class AuditoriaDesativadaService implements AuditoriaService {
    @Override
    public void registrar(RegistroAuditoria registro) {
        // Implementacao intencionalmente vazia para ambientes sem MongoDB.
    }

    @Override
    public Page<LogAtividade> listar(UUID imobiliariaId, Pageable pageable) {
        return new PageImpl<>(List.of(), pageable, 0);
    }
}
