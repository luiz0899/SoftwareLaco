package com.br.ProjLaco.entity.dupla;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "dupla")
@Entity(name = "Dupla")
public class Dupla {
	
	@Id
	@Column(name = "id_dupla")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
   
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "categoria_dupla")
	private String categoriaDupla;

	@OneToMany(mappedBy = "dupla", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference 
	 private List<PessoaDupla> pessoas = new ArrayList<>();
	 
	 public int getPontosSomados() {
	        // Implementar a lógica para calcular os pontos somados
	        return pessoas.stream()
	                .mapToInt(PessoaDupla::getPontos)
	                .sum();
	    }

}
