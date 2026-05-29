package com.finance.academia.controller;

import com.finance.academia.dto.request.AlunoRequest;
import com.finance.academia.dto.response.AlunoResponse;
import com.finance.academia.mapper.AlunoMapper;
import com.finance.academia.model.usuario.AlunoModel;
import com.finance.academia.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'RECEPCIONISTA')")
    @PostMapping
    public ResponseEntity<AlunoResponse> criarAluno(@RequestBody @Valid AlunoRequest request) {
        AlunoModel alunoModel = AlunoMapper.toEntity(request);
        AlunoModel alunoSave = alunoService.criarAluno(alunoModel, request.academyId());
        return ResponseEntity.status(HttpStatus.CREATED).body(AlunoMapper.toResponse(alunoSave));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'RECEPCIONISTA')")
    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listarAlunos() {
        List<AlunoModel> alunoList = alunoService.alunos();
        List<AlunoResponse> alunoConvert = alunoList.stream().map(AlunoMapper::toResponse).toList();
        return ResponseEntity.ok(alunoConvert);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'RECEPCIONISTA', 'INSTRUTOR')")
    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> buscarAlunoPorId(@PathVariable Long id) {
        AlunoModel aluno = alunoService.buscarAlunoPorId(id);
        return ResponseEntity.ok(AlunoMapper.toResponse(aluno));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'RECEPCIONISTA')")
    @GetMapping("/buscar/nome")
    public ResponseEntity<AlunoResponse> buscarPorNome(@RequestParam String nome) {
        AlunoModel aluno = alunoService.buscarAlunoPorNome(nome);
        return ResponseEntity.ok(AlunoMapper.toResponse(aluno));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'RECEPCIONISTA')")
    @GetMapping("/buscar/cpf")
    public ResponseEntity<AlunoResponse> buscarPorCpf(@RequestParam String cpf) {
        AlunoModel aluno = alunoService.buscarAlunoPorCpf(cpf);
        return ResponseEntity.ok(AlunoMapper.toResponse(aluno));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'RECEPCIONISTA')")
    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> atualizarAluno(@PathVariable Long id, @RequestBody @Valid AlunoRequest request) {
        AlunoModel alunoModel = AlunoMapper.toEntity(request);
        AlunoModel alunoAtualizado = alunoService.editarAluno(id, alunoModel);
        return ResponseEntity.ok(AlunoMapper.toResponse(alunoAtualizado));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        alunoService.inativarAluno(id);
        return ResponseEntity.noContent().build();
    }
}
