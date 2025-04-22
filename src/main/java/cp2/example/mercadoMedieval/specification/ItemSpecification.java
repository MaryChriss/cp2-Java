package main.java.cp2.example.mercadoMedieval.specification;

public class ItemSpecification {

    public static Specification<ItemMagico> comFiltros(String tipo, String raridade, BigDecimal precoMin, BigDecimal precoMax) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (tipo != null) {
                predicates.add(cb.equal(cb.lower(root.get("tipo")), tipo.toLowerCase()));
            }

            if (raridade != null) {
                predicates.add(cb.equal(cb.lower(root.get("raridade")), raridade.toLowerCase()));
            }

            if (precoMin != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("preco"), precoMin));
            }

            if (precoMax != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("preco"), precoMax));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
