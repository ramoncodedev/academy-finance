package com.finance.academia.mapper;

import com.finance.academia.dto.request.UsuarioRequest;
import com.finance.academia.dto.response.UsuarioResponse;
import com.finance.academia.model.Usuario;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequest request) {
        return Usuario.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .role(request.role())
                .build();
    }

    public UsuarioResponse toResponse(Usuario usuario){
        return UsuarioResponse.builder()
                .academyId(usuario.getAcademy().getId())
                .name(usuario.getName())
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .role(usuario.getRole())
                .createdAt(usuario.getCreatedAt())
                .build();
    }
}
