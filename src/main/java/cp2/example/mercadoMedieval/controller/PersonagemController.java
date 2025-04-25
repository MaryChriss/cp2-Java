package cp2.example.mercadoMedieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    @GetMapping
    public Page<Personagem> filtrar(PersonagemFilter filtro, Pageable pageable) {
        return repository.findAll(PersonagemSpecification.comFiltros(filtro), pageable);
    }
    
    @PostMapping
    public Personagem criar(@RequestBody @Valid Personagem personagem) {
        log.info("Cadastrando personagem " + personagem.getNome());
        return repository.save(personagem);
    }

    @GetMapping("/{id}")
    public Personagem buscar(@PathVariable Long id) {
        log.info("Buscando personagem " + id);
        return getPersonagem(id);
    }

    @PutMapping("/{id}")
    public Personagem atualizar(@PathVariable Long id, @RequestBody  Personagem personagem) {
        log.info("Atualizando personagem " + id + " " + personagem);

        getPersonagem(id);
        personagem.setId(id);
        return repository.save(personagem);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        log.info("Apagando personagem " + id);
        repository.delete(getPersonagem(id));
    }

    private Personagem getPersonagem(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Categoria " + id + "  não encontrada"));
    }

}
