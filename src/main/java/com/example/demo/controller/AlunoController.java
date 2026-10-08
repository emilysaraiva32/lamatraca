package com.example.demo.controller;

import com.example.demo.dto.AlunoRequestDTO;
import com.example.demo.dto.AlunoResponseDTO;
import com.example.demo.service.AlunoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
@CrossOrigin(origins = "http://localhost:5173")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> buscar(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> criar(
            @Valid @RequestBody AlunoRequestDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AlunoRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}