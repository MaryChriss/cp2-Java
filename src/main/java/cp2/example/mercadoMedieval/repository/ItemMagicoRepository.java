package cp2.example.mercadoMedieval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cp2.example.mercadoMedieval.model.ItemMagico;

public interface ItemMagicoRepository extends JpaRepository<ItemMagico, Long>, JpaSpecificationExecutor<ItemMagico> {
    //List<ItemMagico> findByNomeContainingIgnoreCase(String nome);

    //List<ItemMagico> findByTipo(ItemType tipo);

}
