package com.br.ProjLaco.dto.DuplaDto;

import java.util.List;

import lombok.Data;

@Data
public class DuplaUpdateLoteDto {
	
	private List<DuplaDTO>duplaUpdates;

	public DuplaUpdateLoteDto() {
	}

	public DuplaUpdateLoteDto(List<DuplaDTO> duplaUpdates) {
		this.duplaUpdates = duplaUpdates;
	}
	
	

}
