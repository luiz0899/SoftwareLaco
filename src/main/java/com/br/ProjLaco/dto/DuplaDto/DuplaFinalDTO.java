package com.br.ProjLaco.dto.DuplaDto;

import java.util.List;

import lombok.Data;

@Data
public class DuplaFinalDTO {
	
	private Integer id ;
	private String nome ;
	private String categoriaDupla;
	private List<PessoaDuplaDTO> pessoas ;
	private int pontosSomados ;
	
	public DuplaFinalDTO() {
	}
	
	
	public DuplaFinalDTO(Integer id, String nome,String categoriaDupla, List<PessoaDuplaDTO> pessoas, int pontosSomados) {
		this.id = id;
		this.nome = nome;
		this.categoriaDupla = categoriaDupla;
		this.pessoas = pessoas;
		this.pontosSomados = pontosSomados;
	}


	

}
