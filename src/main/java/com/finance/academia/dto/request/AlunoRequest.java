package com.finance.academia.dto.request;

import com.finance.academia.model.enums.Sexo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record AlunoRequest(

        @NotNull
        Long academyId,

        @NotBlank(message = "nome é obrigatório")
        String nome,

        @NotBlank
        @Size(min = 11, max = 14, message = "CPF deve conter entre 11 e 14 caracteres")
        String cpf,

        @NotBlank
        @Email(message = "email deve ser válido")
        String email,

        String telefone,

        @NotNull(message = "data de nascimento é obrigatória")
        Date dataNascimento,

        Sexo sexo,
        String fotoUrl,
        String observacoes,

        String bairro,
        String rua,
        String numero,
        String cidade,
        String estado,
        String cep
) {
}
