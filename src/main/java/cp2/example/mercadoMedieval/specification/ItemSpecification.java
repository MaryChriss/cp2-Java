package cp2.example.mercadoMedieval.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import cp2.example.mercadoMedieval.model.ItemFilter;
import cp2.example.mercadoMedieval.model.ItemMagico;
import jakarta.persistence.criteria.Predicate;

public class ItemSpecification {

    public static Specification<ItemMagico> comFiltros(ItemFilter filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getTipo() != null) {
                predicates.add(cb.equal(cb.lower(root.get("tipo")), filtro.getTipo().toLowerCase()));
            }

            if (filtro.getRaridade() != null) {
                predicates.add(cb.equal(cb.lower(root.get("raridade")), filtro.getRaridade().toLowerCase()));
            }

            if (filtro.getPrecoMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("preco"), filtro.getPrecoMin()));
            }

            if (filtro.getPrecoMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("preco"), filtro.getPrecoMax()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
