package com.br.ProjLaco.dto.QuartetoDto;

import java.util.List;

import lombok.Data;

@Data
public class QuartetoFinalDTO {

	
	private Integer id ;
	private String nomeQuarteto;
    private List<PessoaQuartetoDTO> pessoas;
    private int pontosSomados;
    
    
    
	public QuartetoFinalDTO() {

	}
	
	public QuartetoFinalDTO(Integer id, String nomeQuarteto, List<PessoaQuartetoDTO> pessoas, int pontosSomados) {

		this.id = id;
		this.nomeQuarteto = nomeQuarteto;
		this.pessoas = pessoas;
		this.pontosSomados = pontosSomados;
	}


   
  
}
