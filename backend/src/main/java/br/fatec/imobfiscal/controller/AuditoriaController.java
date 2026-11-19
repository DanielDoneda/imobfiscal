package br.fatec.imobfiscal.controller;

import br.fatec.imobfiscal.audit.AuditoriaService;
import br.fatec.imobfiscal.audit.LogAtividade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/imobiliarias/{imobiliariaId}/auditoria")
@RequiredArgsConstructor
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    public Page<LogAtividade> listar(
            @org.springframework.web.bind.annotation.PathVariable UUID imobiliariaId,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanho) {
        int tamanhoSeguro = Math.min(Math.max(tamanho, 1), 100);
        return auditoriaService.listar(imobiliariaId,
                PageRequest.of(Math.max(pagina, 0), tamanhoSeguro,
                        Sort.by(Sort.Direction.DESC, "dataHora")));
    }
}
