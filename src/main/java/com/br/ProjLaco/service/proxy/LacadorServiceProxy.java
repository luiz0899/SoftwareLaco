package com.br.ProjLaco.service.proxy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.br.ProjLaco.entity.individual.Lacador;
import com.br.ProjLaco.service.LacadorService;

@Service
public class LacadorServiceProxy implements LacadorService {
	
	@Autowired
	@Qualifier("lacadorServiceImpl")
	private LacadorService lacadorService;

	@Override
	public Lacador buscarPorId(Integer idLacador) {		
		return lacadorService.buscarPorId(idLacador);
	}

	@Override
	public Lacador buscarLacador(String nome) {
		return lacadorService.buscarLacador(nome);
	}

	@Override
	public List<Lacador> listarLacador() {
		return lacadorService.listarLacador();
	}

	@Override
	public Lacador adicionarLacador(Lacador lacador) {
		return lacadorService.adicionarLacador(lacador);
	}

	@Override
	public void deletarLacadorPorNome(String nome) {
		lacadorService.deletarLacadorPorNome(nome);
	}

}
