package br.fatec.imobfiscal.model;

import br.fatec.imobfiscal.enums.RegimeTributario;
import br.fatec.imobfiscal.enums.TipoPessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record LocadorRequest(

        @NotNull
        TipoPessoa tipoPessoa,

        @NotBlank
        @Size(min = 11, max = 14)
        @Pattern(regexp = "\\d{11}|\\d{14}", message = "CPF/CNPJ deve conter 11 ou 14 números")
        String cpfCnpj,

        @NotBlank
        String nome,

        @Email(message = "E-mail inválido") String email,

        @Pattern(regexp = "^$|\\d{10,11}", message = "Telefone deve conter 10 ou 11 números")
        String telefone,

        RegimeTributario regimeTributario
) {}
