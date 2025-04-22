package cp2.example.mercadoMedieval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cp2.example.mercadoMedieval.model.ItemFilter;
import cp2.example.mercadoMedieval.model.ItemMagico;
import cp2.example.mercadoMedieval.repository.ItemMagicoRepository;
import cp2.example.mercadoMedieval.specification.ItemSpecification;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemMagicoRepository repository;

    @PostMapping
    public ResponseEntity<ItemMagico> criar(
            @org.springframework.web.bind.annotation.RequestBody @Valid ItemMagico item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(item));
    }

    @GetMapping
    public Page<ItemMagico> filtrar(ItemFilter filtro,
            @PageableDefault(size = 10, sort = "preco") Pageable pageable) {
        return repository.findAll(ItemSpecification.comFiltros(filtro), pageable);
    }

    @GetMapping
    public List<ItemMagico> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemMagico> buscar(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemMagico> atualizar(@PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody @Valid ItemMagico atualizacao) {
        return repository.findById(id).map(i -> {
            i.setNome(atualizacao.getNome());
            i.setTipo(atualizacao.getTipo());
            i.setPreco(atualizacao.getPreco());
            i.setRaridade(atualizacao.getRaridade());
            i.setDono(atualizacao.getDono());
            return ResponseEntity.ok(repository.save(i));
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
