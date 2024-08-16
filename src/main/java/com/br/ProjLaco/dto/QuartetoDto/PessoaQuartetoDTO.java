package com.br.ProjLaco.dto.QuartetoDto;

import lombok.Data;

@Data
public class PessoaQuartetoDTO {
	
	private Integer id;
	private String nome;	
	private Integer Pontos ;
	
	public PessoaQuartetoDTO() {
	
	}
	
	public PessoaQuartetoDTO(Integer id ,String nome, Integer pontos) {
		this.id = id ;
		this.nome = nome;
		Pontos = pontos;
	}
	
	

}
