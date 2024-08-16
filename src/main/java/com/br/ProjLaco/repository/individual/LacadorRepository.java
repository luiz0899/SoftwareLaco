package com.br.ProjLaco.repository.individual;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.ProjLaco.entity.individual.Lacador;

@Repository
public interface LacadorRepository extends JpaRepository<Lacador, Integer> {
	
		@Query("SELECT l FROM Lacador l WHERE l.nome = :nome")
	    public Lacador buscarLacador(String nome);
	   
	    @Query(value = "SELECT * FROM Lacador", nativeQuery = true)
	    public List<Lacador> listarLacador();
} 

