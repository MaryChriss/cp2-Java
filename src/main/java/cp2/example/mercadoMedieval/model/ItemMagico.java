package main.java.cp2.example.mercadoMedieval.model;

@Entity
public class ItemMagico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String tipo;

    private BigDecimal preco;

    private String raridade; 

}
