package br.fatec.imobfiscal.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ValidationTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void deveRejeitarLoginInvalido() {
        assertFalse(validator.validate(new LoginRequest("email-invalido", "")).isEmpty());
    }

    @Test
    void deveRejeitarImovelComCepUfEValoresInvalidos() {
        ImovelRequest request = new ImovelRequest(
                null, "IM-01", null, "12A", "Rua Teste", "10", null,
                "Centro", "Cidade", "123", BigDecimal.valueOf(-1),
                -1, -1, BigDecimal.valueOf(-10), null, BigDecimal.valueOf(-5));

        assertFalse(validator.validate(request).isEmpty());
    }
}
