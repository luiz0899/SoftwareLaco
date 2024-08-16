package com.br.ProjLaco.entity.individual;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "lacador")
@Entity(name = "Lacador")
public class Lacador {
	
	@Id
	@Column(name = "id_lacador")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
   
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "ponto")
	private Integer ponto;
	
	@Column(name = "categoria_individual")
	private String categoriaIndividual;

	

}
