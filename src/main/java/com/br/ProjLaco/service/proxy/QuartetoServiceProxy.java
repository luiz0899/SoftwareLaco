package com.br.ProjLaco.service.proxy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.br.ProjLaco.dto.QuartetoDto.QuartetoDTO;
import com.br.ProjLaco.entity.quarteto.Quarteto;
import com.br.ProjLaco.service.QuartetoService;

@Service
public class QuartetoServiceProxy implements QuartetoService{

	@Autowired
	@Qualifier("quartetoServiceImpl")
	private QuartetoService service ;
	
	@Override
	public Quarteto buscarQuarteto(String nome) {
		return service.buscarQuarteto(nome);
	}

	@Override
	public List<Quarteto> listarQuarteto() {
		return service.listarQuarteto();
	}

	@Override
	public Quarteto adicionarQuarteto(Quarteto quarteto) {
		return service.adicionarQuarteto(quarteto);
	}

	@Override
	public Quarteto buscarPorId(Integer idQuarteto) {
		
		return service.buscarPorId(idQuarteto);
	}

	@Override
	public void deletarQuartetoPorNome(String nome) {
		service.deletarQuartetoPorNome(nome);
	}
	
	
	
	

}
