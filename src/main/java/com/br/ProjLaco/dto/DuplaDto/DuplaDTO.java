package com.br.ProjLaco.dto.DuplaDto;

import java.util.List;

import lombok.Data;

@Data
public class DuplaDTO {
	
	private Integer id;
	private String nome;
	private String categoriaDupla;
	private List<PessoaDuplaDTO>pessoas;
	
	
	
	public DuplaDTO() {
	}
	
	
	public DuplaDTO(Integer id, String nome,String categoriaDupla, List<PessoaDuplaDTO> pessoas) {
		this.id = id; 
		this.nome = nome;
		this.categoriaDupla = categoriaDupla;
		this.pessoas = pessoas;
	}


	

}
