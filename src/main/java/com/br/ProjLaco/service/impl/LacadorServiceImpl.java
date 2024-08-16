package com.br.ProjLaco.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ProjLaco.entity.individual.Lacador;
import com.br.ProjLaco.repository.individual.LacadorRepository;
import com.br.ProjLaco.service.LacadorService;

@Service
public class LacadorServiceImpl implements LacadorService {
	
	@Autowired
	private LacadorRepository lacadorRepository;

	@Override
	public Lacador buscarPorId(Integer idLacador) {
			
		try {
			
			Optional<Lacador> optionalLacador = lacadorRepository.findById(idLacador);
			return optionalLacador.orElse(null);
			
		} catch (Exception e) {
			
			throw new IllegalArgumentException("Laçador não encontrado!");
		} 
	}

	@Override
	public Lacador buscarLacador(String nome) {
		
		try {
			
			Lacador lacador = lacadorRepository.buscarLacador(nome);
			return lacador;
			
		} catch (Exception e) {
			
			throw new IllegalArgumentException("Laçador não existente!");
		}
		
	}

	@Override
	public List<Lacador> listarLacador() {
		
		try {
			
			List<Lacador> listarLacador = lacadorRepository.listarLacador();
			return listarLacador;
			
		} catch (Exception e) {
			
			throw new IllegalArgumentException("Laçador não existente!");
		}
	}

	@Override
	public Lacador adicionarLacador(Lacador lacador) {
		
		try {
			return lacadorRepository.save(lacador);
		} catch (Exception e){

			throw new IllegalArgumentException("Não foi possivel salvar o laçador!");
		}
	}

	@Override
	public void deletarLacadorPorNome(String nome) {
		
		try {
			
		    Lacador lacador = lacadorRepository.buscarLacador(nome);
			lacadorRepository.delete(lacador);
			
		} catch (Exception e) {
			
			throw new IllegalArgumentException("Não foi possivel deletar o laçador!");
		}
		
	}

}
