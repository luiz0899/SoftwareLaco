package com.br.ProjLaco.service.proxy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.br.ProjLaco.entity.dupla.Dupla;
import com.br.ProjLaco.service.DuplaService;

@Service
public class DuplaServiceProxy implements DuplaService{

	@Autowired
	@Qualifier("duplaServiceImpl")
	private DuplaService service ;

	@Override
	public Dupla buscarPorId(Integer idDupla) {
		return service.buscarPorId(idDupla);
	}

	@Override
	public Dupla buscarDupla(String nome) {
		return service.buscarDupla(nome);
	}

	@Override
	public List<Dupla> listarDupla() {
		return service.listarDupla();
	}

	@Override
	public Dupla adicionarDupla(Dupla dupla) {		
		return service.adicionarDupla(dupla);
	}

	@Override
	public void deletarDuplaPorNome(String nome) {
		 service.deletarDuplaPorNome(nome); 
	}
	
}
