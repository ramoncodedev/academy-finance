package com.finance.academia.dto.response;

import com.finance.academia.model.enums.Sexo;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
public record AlunoResponse(


        Long id,

        Long academyId,

        String nome,

        String cpf,

        String email,

        String telefone,
        String celular,

        Date dataNascimento,
        Sexo sexo,

        String fotoUrl,
        String observacoes,

        Boolean ativo,
        String bairro,

        String rua,
        String numero,

        String cidade,
        String estado,

        String cep,
        LocalDateTime createdAt
) {
}
