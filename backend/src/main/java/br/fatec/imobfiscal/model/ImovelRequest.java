package br.fatec.imobfiscal.model;

import br.fatec.imobfiscal.enums.TipoImovel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ImovelRequest(
        UUID locadorId,

        @NotBlank(message = "Código obrigatório")
        String codigo,

        @NotNull(message = "Tipo obrigatório")
        TipoImovel tipo,

        @NotBlank
        @Size(min = 8, max = 8, message = "CEP deve ter 8 dígitos")
        @Pattern(regexp = "\\d{8}", message = "CEP deve conter somente números")
        String cep,

        @NotBlank String logradouro,
        @NotBlank String numero,
        String complemento,
        @NotBlank String bairro,
        @NotBlank String cidade,

        @NotBlank
        @Size(min = 2, max = 2, message = "UF deve ter 2 letras")
        @Pattern(regexp = "[A-Za-z]{2}", message = "UF deve conter 2 letras")
        String uf,

        @PositiveOrZero(message = "Área total não pode ser negativa")
        BigDecimal areaTotal,
        @PositiveOrZero(message = "Quantidade de quartos não pode ser negativa")
        Integer quartos,
        @PositiveOrZero(message = "Quantidade de vagas não pode ser negativa")
        Integer vagas,
        @PositiveOrZero(message = "Valor de compra não pode ser negativo")
        BigDecimal valorCompra,
        LocalDate dataCompra,
        @PositiveOrZero(message = "Valor venal não pode ser negativo")
        BigDecimal valorVenal
) {}
