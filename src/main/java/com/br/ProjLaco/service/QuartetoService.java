package com.br.ProjLaco.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.br.ProjLaco.entity.quarteto.Quarteto;

@Validated
public interface QuartetoService {
	
	public Quarteto buscarPorId (Integer idQuarteto) ;
	
	public Quarteto buscarQuarteto(String nome);
	
	public List<Quarteto> listarQuarteto();
	
	public Quarteto adicionarQuarteto(Quarteto quarteto);
	
	public void deletarQuartetoPorNome(String nome);
		
}
