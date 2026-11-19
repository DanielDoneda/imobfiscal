package br.fatec.imobfiscal.audit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AuditoriaService {
    void registrar(RegistroAuditoria registro);
    Page<LogAtividade> listar(UUID imobiliariaId, Pageable pageable);
}
