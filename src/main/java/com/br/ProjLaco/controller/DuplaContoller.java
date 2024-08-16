package com.br.ProjLaco.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.ProjLaco.dto.DuplaDto.DuplaDTO;
import com.br.ProjLaco.dto.DuplaDto.DuplaFinalDTO;
import com.br.ProjLaco.dto.DuplaDto.DuplaUpdateLoteDto;
import com.br.ProjLaco.dto.DuplaDto.PessoaDuplaDTO;

import com.br.ProjLaco.entity.dupla.Dupla;
import com.br.ProjLaco.entity.dupla.PessoaDupla;

import com.br.ProjLaco.service.DuplaService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/dupla")
@CrossOrigin(origins = "*")
public class DuplaContoller {
	
	@Autowired
	@Qualifier("duplaServiceProxy")
	private DuplaService duplaService;
	
	@GetMapping("/{nome}")
	public ResponseEntity<?> buscardupla(@PathVariable("nome") String nome) {
		try {
			Dupla dupla = duplaService.buscarDupla(nome);
			return ResponseEntity.ok(dupla); 
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<?> listarDuplas() {
	    try {
	        List<Dupla> duplas = duplaService.listarDupla();
	        
	        // Mapeando os quartetos para o DTO
	        List<DuplaDTO> duplaDTOs = duplas.stream()
	                .map(dupla -> new DuplaDTO(
	                	dupla.getId(),
	                	dupla.getNome(),
	                	dupla.getCategoriaDupla(),
	                	dupla.getPessoas().stream()
	                        .map(pessoa -> new PessoaDuplaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getPontos())) 
	                        .collect(Collectors.toList())
	                ))
	                .collect(Collectors.toList());
	        // Retornando a lista de DTOs dentro de um ResponseEntity
	        return ResponseEntity.ok(duplaDTOs);
	        
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PostMapping("/adicionar")
    public ResponseEntity<?> adicionarDupla(@RequestBody Dupla dupla) {
        try {
        	Dupla salvardupla = duplaService.adicionarDupla(dupla);
            return ResponseEntity.created(URI.create("/dupla/" + salvardupla.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocorreu um erro ao adicionar a dupla. Motivo: " + e.getMessage());
        }
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarDupla(@PathVariable Integer id, @RequestBody DuplaDTO duplaDTO) {
	    try {
	        // Buscar o dupla pelo ID
	        Dupla dupla = duplaService.buscarPorId(id);
	        if (dupla == null) {
	            return ResponseEntity.notFound().build();
	        }

	        // Atualizar o nome do dupla
	        dupla.setNome(duplaDTO.getNome());
	        dupla.setCategoriaDupla(duplaDTO.getCategoriaDupla());

	        // Limpar a lista atual de pessoas
	        dupla.getPessoas().clear();

	        // Adicionar as novas pessoas a dupla
	        for (PessoaDuplaDTO pessoaDTO : duplaDTO.getPessoas()) {
	            PessoaDupla pessoa = new PessoaDupla();
	            pessoa.setNome(pessoaDTO.getNome());
	            pessoa.setPontos(pessoaDTO.getPontos());
	            pessoa.setDupla(dupla); // Associa a pessoa ao quarteto
	            dupla.getPessoas().add(pessoa);
	        }

	        // Salvar as alterações
	        duplaService.adicionarDupla(dupla);

	        return ResponseEntity.ok("Dupla atualizado com sucesso.");
	        
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar a dupla.");
	    }
	}
	
	@PutMapping("/atualizar-lote")
	public ResponseEntity<?> atualizarDuplaLote(@RequestBody DuplaUpdateLoteDto loteDTO) {
	    try {
	        for (DuplaDTO updateDTO : loteDTO.getDuplaUpdates()) {
	            Dupla dupla = duplaService.buscarPorId(updateDTO.getId());
	            if (dupla != null) {
	                // Atualizar o nome da dupla
	            	dupla.setNome(updateDTO.getNome());
	            	dupla.setCategoriaDupla(updateDTO.getCategoriaDupla());

	                // Limpar a lista atual de pessoas
	            	dupla.getPessoas().clear();

	                // Adicionar as novas pessoas a dupla
	                for (PessoaDuplaDTO pessoaDTO : updateDTO.getPessoas()) {
	                    PessoaDupla pessoa = new PessoaDupla();
	                    pessoa.setNome(pessoaDTO.getNome());
	                    pessoa.setPontos(pessoaDTO.getPontos());
	                    pessoa.setDupla(dupla); // Associa a pessoa a dupla
	                    dupla.getPessoas().add(pessoa);
	                }

	                // Salvar as alterações
	                duplaService.adicionarDupla(dupla);
	            } else {
	                // Caso algum dupla não seja encontrado
	                throw new EntityNotFoundException("Dupla com ID " + updateDTO.getId() + " não encontrado.");
	            }
	        }
	        return ResponseEntity.ok("Duplas atualizados com sucesso.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar as dupla. Motivo: " + e.getMessage());
	    }
	}
	
	@DeleteMapping("/deletar/{nome}")
	public ResponseEntity<?> deletarDupla(@PathVariable String nome) {
	    try {
	        duplaService.deletarDuplaPorNome(nome);
	    
	        return ResponseEntity.noContent().build();
	    } catch (EntityNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao deletar a dupla. Motivo: " + e.getMessage());
	    }
	}
	
	@GetMapping("/pontos/{quantidade}/categoria/{categoriaDupla}")
	public List<DuplaFinalDTO> buscarDuplasPorPontosECategoria(@PathVariable int quantidade, @PathVariable String categoriaDupla) {
	    List<Dupla> duplas = duplaService.listarDupla();
	    return duplas.stream()
	            .filter(dupla -> dupla.getPontosSomados() >= quantidade && dupla.getCategoriaDupla().equalsIgnoreCase(categoriaDupla))
	            .map(dupla -> new DuplaFinalDTO(
	                    dupla.getId(),
	                    dupla.getNome(),
	                    dupla.getCategoriaDupla(),
	                    dupla.getPessoas().stream().map(pessoa -> new PessoaDuplaDTO(
	                            pessoa.getId(),
	                            pessoa.getNome(),
	                            pessoa.getPontos()
	                    )).collect(Collectors.toList()), 
	                    dupla.getPontosSomados()
	            ))
	            .collect(Collectors.toList());
	}
	
	
	
}
