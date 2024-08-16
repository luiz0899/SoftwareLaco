package com.br.ProjLaco.dto.QuartetoDto;

import java.util.List;

import lombok.Data;

@Data
public class QuartetoDTO {
	
	private Integer id ;
	private String nomeQuarteto;
    private List<PessoaQuartetoDTO> pessoas;
    
    public QuartetoDTO() {
    	
    }
    
    public QuartetoDTO(Integer id , String nomeQuarteto, List<PessoaQuartetoDTO> pessoas) {
    	this.id = id ;
        this.nomeQuarteto = nomeQuarteto;
        this.pessoas = pessoas;
    }
  
}
