package com.finance.academia.controller;

import com.finance.academia.dto.request.AcademyRequest;
import com.finance.academia.dto.response.AcademyResponse;
import com.finance.academia.mapper.AcademyMapper;
import com.finance.academia.model.academia.Academy;
import com.finance.academia.service.AcademyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/academies")
public class AcademyControler {

    private final AcademyService academyService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<AcademyResponse> createAcademy(@RequestBody @Valid AcademyRequest request){
        Academy academy = AcademyMapper.toAcademy(request);
        Academy academySave = academyService.saveAcademy(academy);

        return ResponseEntity.ok(AcademyMapper.toResponse(academySave));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<AcademyResponse>> ListAcademy(){
        List<Academy> academyList = academyService.findAllAcademies();
        List<AcademyResponse> academyConvert = academyList.stream().map(AcademyMapper::toResponse).toList();

        return ResponseEntity.ok().body(academyConvert);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<AcademyResponse> findByAcademy( @RequestParam String name){
        Academy academy = academyService.findAcademyByName(name);

        return ResponseEntity.ok().body(AcademyMapper.toResponse(academy));
    }

}
