package com.fiap.areslife.dto.request;

import com.fiap.areslife.enums.Localizacao;
import com.fiap.areslife.enums.StatusTurista;
import jakarta.validation.constraints.*;

public record TuristaEspacialRequest(
        @NotBlank String nome,
        @NotNull(message = "Idade é obrigatória")
        @Min(value = 18, message = "Idade inválida, permitido apenas maiores de 18 anos.")
        @Max(value = 99, message = "Idade máxima permitida é 99 anos.")
        Integer idade,
        @NotBlank String pais,
        @NotNull Localizacao destino,
        @NotNull
        StatusTurista status
) {}
