package main.java.cp2.example.mercadoMedieval.model;

import java.math.BigDecimal;

@Entity
public class personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "campo obrigatório")
    private String nome;

    @Enumerated(EnumType.STRING)
    private Classe classe;

    @Min(1)
    @Max(99)
    private int nivel;

    @DecimalMin("0.0")
    private BigDecimal moedas;

}
