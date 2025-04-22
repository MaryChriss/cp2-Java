package main.java.cp2.example.mercadoMedieval.repository;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    List<Personagem> findByNomeContainingIgnoreCase(String nome);
    List<Personagem> findByClasse(Classe classe);
}
