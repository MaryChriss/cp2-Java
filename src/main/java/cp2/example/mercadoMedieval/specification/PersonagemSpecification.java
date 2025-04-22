package cp2.example.mercadoMedieval.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import cp2.example.mercadoMedieval.model.Personagem;
import cp2.example.mercadoMedieval.model.PersonagemFilter;
import jakarta.persistence.criteria.Predicate;

public class PersonagemSpecification {

    public static Specification<Personagem> comFiltros(PersonagemFilter filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            }

            if (filtro.getClasse() != null) {
                predicates.add(cb.equal(root.get("classe"), filtro.getClasse()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
