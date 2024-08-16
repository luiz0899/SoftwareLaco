package com.br.ProjLaco.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.br.ProjLaco.entity.dupla.Dupla;

@Validated
public interface DuplaService {
	
	public Dupla buscarPorId (Integer idDupla) ;
	
	public Dupla buscarDupla(String nome);
	
	public List<Dupla> listarDupla();
	
	public Dupla adicionarDupla(Dupla dupla);
	
	public void deletarDuplaPorNome(String nome);

}
