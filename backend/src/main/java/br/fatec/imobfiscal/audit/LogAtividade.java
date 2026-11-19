package br.fatec.imobfiscal.audit;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "logs_atividades")
@CompoundIndex(name = "imobiliaria_data_idx", def = "{'imobiliariaId': 1, 'dataHora': -1}")
@Getter
@Builder
public class LogAtividade {

    @Id
    private String id;

    @Indexed
    private UUID imobiliariaId;

    private UUID usuarioId;
    private String usuarioEmail;
    private AcaoAuditoria acao;
    private String metodoHttp;
    private String rota;
    private int statusHttp;
    private String enderecoIp;
    private String navegador;

    @Indexed
    private Instant dataHora;
}
