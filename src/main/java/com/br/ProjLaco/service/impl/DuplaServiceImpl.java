package com.br.ProjLaco.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ProjLaco.entity.dupla.Dupla;
import com.br.ProjLaco.entity.dupla.PessoaDupla;
import com.br.ProjLaco.repository.dupla.DuplaRepository;
import com.br.ProjLaco.service.DuplaService;

import jakarta.persistence.EntityNotFoundException;


@Service
public class DuplaServiceImpl implements DuplaService{
	
	@Autowired
	private DuplaRepository duplaRepository;

	@Override
	public Dupla buscarPorId(Integer idDupla) {
			
		Optional<Dupla> optionalDupla = duplaRepository.findById(idDupla);
		
		if(optionalDupla == null ) {
			
			throw new IllegalArgumentException("Dupla não encontrado!");			
		}	
		
	     return optionalDupla.orElse(null);
	}

	@Override
	public Dupla buscarDupla(String nome) {
		
		Dupla dupla = duplaRepository.buscarDupla(nome);
		
		if(dupla == null ) {
			
			throw new IllegalArgumentException("Dupla não encontrado!");
		
		}
		
		return dupla;
	}

	@Override
	public List<Dupla> listarDupla() {
		
		try {
			
			List<Dupla> listarDupla = duplaRepository.listarDupla();
			return listarDupla;
			
		} catch (Exception e) {
			
			throw new IllegalArgumentException("Duplas não existente!");
		}
	}

	@Override
	public Dupla adicionarDupla(Dupla dupla) {
		
		// Verificar se há exatamente duas pessoas associadas à dupla
	    if (dupla.getPessoas().size() != 2) {
	    
	    	throw new IllegalArgumentException("Uma dupla deve conter exatamente duas pessoas.");
	    
	    }else {
	    	
	    	// Verificar e configurar as referências bidirecionais
	    	for (PessoaDupla pessoaDupla : dupla.getPessoas()) {
	    		pessoaDupla.setDupla(dupla); // Associa a pessoa ao quarteto
	    	}
	    	
	    	// Salvar o quarteto e suas pessoas associadas
	    	return duplaRepository.save(dupla);
	    }
    }

	@Override
	public void deletarDuplaPorNome(String nome) {
		
	    Dupla dupla = duplaRepository.buscarDupla(nome);
	    
	    if (dupla != null) {
	        
	        duplaRepository.delete(dupla);
	        
	    } else {
	    	
	        throw new EntityNotFoundException("Dupla com nome " + nome + " não encontrada.");
	    }
	}
	
}
	
