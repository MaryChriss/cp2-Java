package main.java.cp2.example.mercadoMedieval.controller;

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
