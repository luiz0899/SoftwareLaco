package com.br.ProjLaco.repository.quarteto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.ProjLaco.entity.quarteto.Quarteto;


@Repository
public interface QuartetoRepository extends JpaRepository<Quarteto , Integer > {

	   @Query("SELECT q FROM Quarteto q WHERE q.nome = :nome")
	   public Quarteto buscarQuarteto(String nome);
	   
	
	   @Query(value = "SELECT * FROM quarteto", nativeQuery = true)
	   public List<Quarteto> listarQuarteto();
	  
	  
	
		
}
