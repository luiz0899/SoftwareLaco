package com.br.ProjLaco.dto.individualDto;

import java.util.List;

import lombok.Data;

@Data
public class LacadorUpdateLoteDto {
	
	private List<LacadorDTO>lacadorDTOs;

	public LacadorUpdateLoteDto() {
	}

	public LacadorUpdateLoteDto(List<LacadorDTO> lacadorDTOs) {
		this.lacadorDTOs = lacadorDTOs;
	}

}
