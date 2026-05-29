package com.finance.academia.service;

import com.finance.academia.exception.ResourceNotFoundException;
import com.finance.academia.model.academia.Academy;
import com.finance.academia.model.usuario.AlunoModel;
import com.finance.academia.repository.AcademyRepository;
import com.finance.academia.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AcademyRepository academyRepository;

    public AlunoModel criarAluno(AlunoModel model, Long academyId) {
        Academy academy = academyRepository.findById(academyId)
                .orElseThrow(() -> new ResourceNotFoundException("Academia não encontrada com id: " + academyId));

        verificarEmail(model.getEmail());
        verificarCpf(model.getCpf());

        model.setAcademy(academy);
        model.setAtivo(true);
        return alunoRepository.save(model);
    }

    public List<AlunoModel> alunos() {
        return alunoRepository.findAll();
    }

    public AlunoModel buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com id: " + id));
    }

    public AlunoModel buscarAlunoPorNome(String nome) {
        AlunoModel aluno = alunoRepository.findByNomeContainingIgnoreCase(nome);
        if (aluno == null) {
            throw new ResourceNotFoundException("Aluno não encontrado com nome: " + nome);
        }
        return aluno;
    }

    public AlunoModel buscarAlunoPorCpf(String cpf) {
        return alunoRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com CPF: " + cpf));
    }

    public AlunoModel editarAluno(Long id, AlunoModel dados) {
        AlunoModel aluno = buscarAlunoPorId(id);

        if (!aluno.getEmail().equals(dados.getEmail())) {
            verificarEmail(dados.getEmail());
        }

        aluno.setNome(dados.getNome());
        aluno.setEmail(dados.getEmail());
        aluno.setTelefone(dados.getTelefone());
        aluno.setDataNascimento(dados.getDataNascimento());
        aluno.setSexo(dados.getSexo());
        aluno.setFoto_url(dados.getFoto_url());
        aluno.setObservacoes(dados.getObservacoes());
        aluno.setBairro(dados.getBairro());
        aluno.setRua(dados.getRua());
        aluno.setNumero(dados.getNumero());
        aluno.setCidade(dados.getCidade());
        aluno.setEstado(dados.getEstado());
        aluno.setCep(dados.getCep());

        return alunoRepository.save(aluno);
    }

    public void inativarAluno(Long id) {
        AlunoModel aluno = buscarAlunoPorId(id);
        aluno.setAtivo(false);
        alunoRepository.save(aluno);
    }

    private void verificarEmail(String email) {
        if (alunoRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Já existe um aluno com o email: " + email);
        }
    }

    private void verificarCpf(String cpf) {
        if (alunoRepository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("Já existe um aluno com o CPF: " + cpf);
        }
    }
}