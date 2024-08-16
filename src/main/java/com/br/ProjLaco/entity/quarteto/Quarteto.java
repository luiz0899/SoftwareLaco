package com.br.ProjLaco.entity.quarteto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "quarteto")
@Entity(name = "Quarteto")
public class Quarteto {
	
	@Id
	@Column(name = "id_quarteto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
   
	@Column(name = "nome")
	private String nome;

	@OneToMany(mappedBy = "quarteto", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference 
	 private List<PessoaQuarteto> pessoas = new ArrayList<>();
	 
	 public int getPontosSomados() {
	        // Implementar a lógica para calcular os pontos somados
	        return pessoas.stream()
	                .mapToInt(PessoaQuarteto::getPontos)
	                .sum();
	    }

}
