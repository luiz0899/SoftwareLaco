package com.br.ProjLaco.dto.QuartetoDto;

import java.util.List;

import lombok.Data;

@Data
public class QuartetoUpdateLoteDTO {
	
	private List<QuartetoDTO> quartetoUpdates;
	  
	public QuartetoUpdateLoteDTO() {
	}

	public QuartetoUpdateLoteDTO(List<QuartetoDTO> quartetoUpdates) {
			this.quartetoUpdates = quartetoUpdates;
	}
		  
	  

}
