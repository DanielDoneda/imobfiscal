package br.fatec.imobfiscal.audit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuditoriaFilterTest {

    @Test
    void deveClassificarMetodosDeAlteracao() {
        assertEquals(AcaoAuditoria.CRIACAO, AuditoriaFilter.acaoDoMetodo("POST"));
        assertEquals(AcaoAuditoria.ALTERACAO, AuditoriaFilter.acaoDoMetodo("PUT"));
        assertEquals(AcaoAuditoria.ALTERACAO, AuditoriaFilter.acaoDoMetodo("PATCH"));
        assertEquals(AcaoAuditoria.EXCLUSAO, AuditoriaFilter.acaoDoMetodo("DELETE"));
    }
}
