package br.fatec.imobfiscal.audit;

import java.util.UUID;

public record RegistroAuditoria(
        UUID imobiliariaId,
        UUID usuarioId,
        String usuarioEmail,
        AcaoAuditoria acao,
        String metodoHttp,
        String rota,
        int statusHttp,
        String enderecoIp,
        String navegador) {
}
