package br.fatec.imobfiscal.audit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface LogAtividadeRepository extends MongoRepository<LogAtividade, String> {
    Page<LogAtividade> findByImobiliariaId(UUID imobiliariaId, Pageable pageable);
}
