package main.java.cp2.example.mercadoMedieval.controller;

import java.math.BigDecimal;
import java.util.List;

import main.java.cp2.example.mercadoMedieval.model.ItemMagico;
import main.java.cp2.example.mercadoMedieval.repository.ItemMagicoRepository;
import main.java.cp2.example.mercadoMedieval.specification.ItemSpecification;

public @RestController
@RequestMapping("/itens")
public class ItemMagicoController {

    @Autowired
    private ItemMagicoRepository repository;

    @PostMapping
    public ResponseEntity<ItemMagico> criar(@RequestBody ItemMagico item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(item));
    }

    @GetMapping
    public List<ItemMagico> listar() {
        return repository.findAll();
    }
    
}
