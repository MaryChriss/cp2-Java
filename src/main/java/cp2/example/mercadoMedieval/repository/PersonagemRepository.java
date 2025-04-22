package cp2.example.mercadoMedieval.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cp2.example.mercadoMedieval.model.Classe;
import cp2.example.mercadoMedieval.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long>, JpaSpecificationExecutor<Personagem> {
    List<Personagem> findByNomeContainingIgnoreCase(String nome);

    List<Personagem> findByClasse(Classe classe);
}
