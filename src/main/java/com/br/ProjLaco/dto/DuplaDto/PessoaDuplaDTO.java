package com.br.ProjLaco.dto.DuplaDto;


import lombok.Data;

@Data
public class PessoaDuplaDTO {
	
	private Integer id;
	private String nome;
	private Integer Pontos ;
	
	public PessoaDuplaDTO() {
	
	}

	public PessoaDuplaDTO(Integer id, String nome, Integer pontos) {
		this.id = id;
		this.nome = nome;
		Pontos = pontos;
	}
	
	
	
	

}
