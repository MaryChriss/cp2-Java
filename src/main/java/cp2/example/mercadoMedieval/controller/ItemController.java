package cp2.example.mercadoMedieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cp2.example.mercadoMedieval.model.ItemFilter;
import cp2.example.mercadoMedieval.model.ItemMagico;
import cp2.example.mercadoMedieval.repository.ItemMagicoRepository;
import cp2.example.mercadoMedieval.specification.ItemSpecification;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/itens")
@Slf4j
public class ItemController {

    @Autowired
    private ItemMagicoRepository repository;

    @GetMapping
    public Page<ItemMagico> filtrar(ItemFilter filtro,
            @PageableDefault(size = 10, sort = "preco") Pageable pageable) {
        return repository.findAll(ItemSpecification.comFiltros(filtro), pageable);
    }

    @PostMapping
    @Operation(responses = @ApiResponse(responseCode = "400"))
    @ResponseStatus(HttpStatus.CREATED)
    public ItemMagico criar( @RequestBody @Valid ItemMagico item) { 
        log.info("Cadastrando categoria " + item.getNome());
        return repository.save(item);
    }

    @GetMapping("/{id}")
    public ItemMagico buscar(@PathVariable Long id) {
        log.info("Buscando categoria " + id);
        return getItemMagico(id);
    }

    @PutMapping("/{id}")
    public ItemMagico atualizar(@PathVariable Long id, @RequestBody ItemMagico item) {
        log.info("Atualizando categoria " + id);

        getItemMagico(id);
        item.setId(id);
        return repository.save(item);
    }   

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        log.info("Apagando item " + id);
        repository.delete(getItemMagico(id));
    }

    private ItemMagico getItemMagico(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Categoria " + id + "  não encontrada"));
    }
}
