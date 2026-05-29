package com.finance.academia.mapper;

import com.finance.academia.dto.request.AcademyRequest;
import com.finance.academia.dto.response.AcademyResponse;
import com.finance.academia.model.academia.Academy;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AcademyMapper {

    public Academy toAcademy(AcademyRequest request){
        return Academy.builder()
                .name(request.name())
                .cnpj(request.cnpj())
                .email(request.email())
                .phone(request.phone())
                .build();
    }

    public AcademyResponse toResponse(Academy academy){
        return AcademyResponse.builder()
                .id(academy.getId())
                .name(academy.getName())
                .cnpj(academy.getCnpj())
                .email(academy.getEmail())
                .phone(academy.getPhone())
                .createdAt(academy.getCreatedAt())
                .build();
    }
}
