package com.example.demo.service;

import com.example.demo.dto.AlunoRequestDTO;
import com.example.demo.dto.AlunoResponseDTO;
import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public List<AlunoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(AlunoResponseDTO::new)
                .toList();
    }

    public AlunoResponseDTO buscarPorId(Long id) {
        Aluno aluno = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        return new AlunoResponseDTO(aluno);
    }

    public AlunoResponseDTO criar(AlunoRequestDTO dto) {

        if (repository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        if (repository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        Aluno aluno = new Aluno();

        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setCpf(dto.getCpf());
        aluno.setCurso(dto.getCurso());
        aluno.setIdade(dto.getIdade());
        aluno.setAtivo(dto.getAtivo());

        aluno = repository.save(aluno);

        return new AlunoResponseDTO(aluno);
    }

    public AlunoResponseDTO atualizar(Long id, AlunoRequestDTO dto) {

        Aluno aluno = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        if (!aluno.getEmail().equals(dto.getEmail())
                && repository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        if (!aluno.getCpf().equals(dto.getCpf())
                && repository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setCpf(dto.getCpf());
        aluno.setCurso(dto.getCurso());
        aluno.setIdade(dto.getIdade());
        aluno.setAtivo(dto.getAtivo());

        aluno = repository.save(aluno);

        return new AlunoResponseDTO(aluno);
    }

    public void excluir(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Aluno não encontrado");
        }

        repository.deleteById(id);
    }
}