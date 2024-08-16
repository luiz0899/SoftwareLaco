package com.br.ProjLaco.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ProjLaco.entity.quarteto.PessoaQuarteto;
import com.br.ProjLaco.entity.quarteto.Quarteto;
import com.br.ProjLaco.repository.quarteto.QuartetoRepository;
import com.br.ProjLaco.service.QuartetoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class QuartetoServiceImpl implements QuartetoService{
	
	@Autowired
	private QuartetoRepository quartetoRepository;

	@Override
	public Quarteto buscarQuarteto(String nome) {
		
		Quarteto quarteto = quartetoRepository.buscarQuarteto(nome);
		
		if (quarteto == null) {
			throw new IllegalArgumentException("Quarteto não encontrado!");
		}
		return quarteto;
	}


	@Override
	public List<Quarteto> listarQuarteto() {
		
		try {
			List<Quarteto> listaEncontrada = quartetoRepository.listarQuarteto();
			return listaEncontrada;
		} catch (Exception e) {
			throw new IllegalArgumentException("Quartetos não existente !");
		}
		
	}

	@Override
	public Quarteto adicionarQuarteto(Quarteto quarteto) {
		
		// Verificar se há exatamente duas pessoas associadas à quarteto
	    if (quarteto.getPessoas().size() != 4) {
	        throw new IllegalArgumentException("Uma dupla deve conter exatamente duas pessoas.");
	    }else {
	    	
	    	// Verificar e configurar as referências bidirecionais
	    	for (PessoaQuarteto pessoa : quarteto.getPessoas()) {
	    		pessoa.setQuarteto(quarteto); // Associa a pessoa ao quarteto
	    	}
	    	
	    	// Salvar o quarteto e suas pessoas associadas
	    	return quartetoRepository.save(quarteto);
	    }
	    	
    }


	@Override
	public Quarteto buscarPorId(Integer idQuarteto) {
		
		 Optional<Quarteto> optionalQuarteto = quartetoRepository.findById(idQuarteto);
		 
	     return optionalQuarteto.orElse(null);
	}
	
	public void deletarQuartetoPorNome(String nome) {
        Quarteto quarteto = quartetoRepository.buscarQuarteto(nome);
        if (quarteto != null) {
            quartetoRepository.delete(quarteto);
        } else {
            throw new EntityNotFoundException("Quarteto com nome " + nome + " não encontrado.");
        }
    }
	
}
