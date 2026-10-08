package com.example.demo.dto;

import com.example.demo.model.Aluno;
import java.time.LocalDateTime;

public class AlunoResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String curso;
    private Integer idade;
    private Boolean ativo;
    private LocalDateTime dataCadastro;

    public AlunoResponseDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.email = aluno.getEmail();
        this.cpf = aluno.getCpf();
        this.curso = aluno.getCurso();
        this.idade = aluno.getIdade();
        this.ativo = aluno.getAtivo();
        this.dataCadastro = aluno.getDataCadastro();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCurso() {
        return curso;
    }

    public Integer getIdade() {
        return idade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
}