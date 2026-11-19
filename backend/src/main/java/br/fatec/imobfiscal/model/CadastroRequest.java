package br.fatec.imobfiscal.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public record CadastroRequest(
        @NotBlank(message = "Nome obrigatório")
        String nome,

        @Email(message = "E-mail inválido")
        @NotBlank(message = "E-mail obrigatório")
        String email,

        @NotBlank(message = "Senha obrigatória")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String senha,

        @NotBlank(message = "Nome da imobiliária ou proprietário obrigatório")
        @Size(max = 150, message = "Nome da imobiliária ou proprietário deve ter no máximo 150 caracteres")
        String nomeImobiliaria,

        @NotBlank(message = "CPF ou CNPJ obrigatório")
        @Pattern(regexp = "(?:\\d{11}|\\d{14})", message = "CPF deve conter 11 números ou CNPJ 14 números")
        String cnpj
) {}
