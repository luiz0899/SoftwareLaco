package com.br.ProjLaco.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.br.ProjLaco.entity.individual.Lacador;

@Validated
public interface LacadorService {
	
	public Lacador buscarPorId (Integer idLacador) ;
	
	public Lacador buscarLacador(String nome);
	
	public List<Lacador> listarLacador();
	
	public Lacador adicionarLacador(Lacador lacador);
	
	public void deletarLacadorPorNome(String nome);

}
