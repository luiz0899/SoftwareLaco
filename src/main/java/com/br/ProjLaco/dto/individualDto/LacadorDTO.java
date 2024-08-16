package com.br.ProjLaco.dto.individualDto;

import lombok.Data;

@Data
public class LacadorDTO {
	
	private Integer id;
	private String nome;
	private String categoriaIndividual;
	private Integer pontos ;
	
	public LacadorDTO() {
		
	}

	public LacadorDTO(Integer id, String nome, String categoriaIndividual, Integer pontos) {
		
		this.id = id;
		this.nome = nome;
		this.categoriaIndividual = categoriaIndividual;
		this.pontos = pontos;
	}
	
	
	

}
