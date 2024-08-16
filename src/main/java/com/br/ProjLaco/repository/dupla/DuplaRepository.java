package com.br.ProjLaco.repository.dupla;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.ProjLaco.entity.dupla.Dupla;

@Repository
public interface DuplaRepository extends JpaRepository<Dupla , Integer > {

	   @Query("SELECT d FROM Dupla d WHERE d.nome = :nome")
	   public Dupla buscarDupla(String nome);
	   
	   @Query(value = "SELECT * FROM Dupla", nativeQuery = true)
	   public List<Dupla> listarDupla();
}
