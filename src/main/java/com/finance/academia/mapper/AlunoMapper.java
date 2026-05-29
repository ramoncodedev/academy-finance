package com.finance.academia.mapper;

import com.finance.academia.dto.request.AlunoRequest;
import com.finance.academia.dto.response.AlunoResponse;
import com.finance.academia.model.usuario.AlunoModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AlunoMapper {

    public AlunoModel toEntity(AlunoRequest request) {
        return AlunoModel.builder()
                .nome(request.nome())
                .cpf(request.cpf())
                .email(request.email())
                .telefone(request.telefone())
                .dataNascimento(request.dataNascimento())
                .sexo(request.sexo())
                .fotoUrl(request.fotoUrl())
                .observacoes(request.observacoes())
                .bairro(request.bairro())
                .rua(request.rua())
                .numero(request.numero())
                .cidade(request.cidade())
                .estado(request.estado())
                .cep(request.cep())
                .build();
    }

    public AlunoResponse toResponse(AlunoModel aluno) {
        return AlunoResponse.builder()
                .id(aluno.getId())
                .academyId(aluno.getAcademy() != null ? aluno.getAcademy().getId() : null)
                .nome(aluno.getNome())
                .cpf(aluno.getCpf())
                .email(aluno.getEmail())
                .telefone(aluno.getTelefone())
                .dataNascimento(aluno.getDataNascimento())
                .sexo(aluno.getSexo())
                .fotoUrl(aluno.getFotoUrl())
                .observacoes(aluno.getObservacoes())
                .ativo(aluno.getAtivo())
                .bairro(aluno.getBairro())
                .rua(aluno.getRua())
                .numero(aluno.getNumero())
                .cidade(aluno.getCidade())
                .estado(aluno.getEstado())
                .cep(aluno.getCep())
                .createdAt(aluno.getCreatedAt())
                .build();
    }
}
