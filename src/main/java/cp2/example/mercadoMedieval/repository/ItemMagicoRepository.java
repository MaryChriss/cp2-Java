package main.java.cp2.example.mercadoMedieval.repository;

public interface ItemMagicoRepository extends JpaRepository<ItemMagico, Long>, JpaSpecificationExecutor<ItemMagico> {
    List<ItemMagico> findByNomeContainingIgnoreCase(String nome);
    List<ItemMagico> findByTipoIgnoreCase(String tipo);
}

