package cp2.example.mercadoMedieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cp2.example.mercadoMedieval.model.Personagem;
import cp2.example.mercadoMedieval.model.PersonagemFilter;
import cp2.example.mercadoMedieval.repository.PersonagemRepository;
import cp2.example.mercadoMedieval.specification.PersonagemSpecification;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/personagens")
@Slf4j
public class PersonagemController {

    @Autowired
    private PersonagemRepository repository;

    @PostMapping
    public ResponseEntity<Personagem> criar(@RequestBody @Valid Personagem personagem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(personagem));
    }

    @GetMapping
    public List<Personagem> filtrar(PersonagemFilter filtro) {
        return repository.findAll(PersonagemSpecification.comFiltros(filtro));
    }

    @GetMapping
    public List<Personagem> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> buscar(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personagem> atualizar(@PathVariable Long id, @RequestBody @Valid Personagem atualizacao) {
        return repository.findById(id).map(p -> {
            p.setNome(atualizacao.getNome());
            p.setClasse(atualizacao.getClasse());
            p.setNivel(atualizacao.getNivel());
            p.setMoedas(atualizacao.getMoedas());
            return ResponseEntity.ok(repository.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
