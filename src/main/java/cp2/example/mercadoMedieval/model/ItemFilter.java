package cp2.example.mercadoMedieval.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItemFilter {
    private String tipo;
    private String raridade;
    private BigDecimal precoMin;
    private BigDecimal precoMax;
}
