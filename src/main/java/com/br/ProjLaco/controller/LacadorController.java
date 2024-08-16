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


import com.br.ProjLaco.dto.individualDto.LacadorDTO;
import com.br.ProjLaco.dto.individualDto.LacadorUpdateLoteDto;
import com.br.ProjLaco.entity.individual.Lacador;
import com.br.ProjLaco.service.LacadorService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/lacador")
@CrossOrigin(origins = "*")
public class LacadorController {
	
	@Autowired
	@Qualifier("lacadorServiceProxy")
	private LacadorService lacadorService;
	
	@GetMapping("/{nome}")
	public ResponseEntity<?> buscarLacador(@PathVariable("nome") String nome) {
		try {
			Lacador lacador = lacadorService.buscarLacador(nome);
			return ResponseEntity.ok(lacador); 
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<?> listarLacador() {
	    try {
	        List<Lacador> lacadores = lacadorService.listarLacador();
	        
	        // Mapeando os quartetos para o DTO
	        List<LacadorDTO> lacadorDTOs = lacadores.stream()
	                .map(lacador -> new LacadorDTO(
	                	lacador.getId(),
	                	lacador.getNome(),
	                	lacador.getCategoriaIndividual(),
	                	lacador.getPonto()))
	                .collect(Collectors.toList());
	        // Retornando a lista de DTOs dentro de um ResponseEntity
	        return ResponseEntity.ok(lacadorDTOs);
	        
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.notFound().build();
	    }
	}
	@PostMapping("/adicionar")
    public ResponseEntity<?> adicionarLacador(@RequestBody Lacador Lacador) {
        try {
        	Lacador salvarLacador = lacadorService.adicionarLacador(Lacador);
            return ResponseEntity.created(URI.create("/lacador/" + salvarLacador.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocorreu um erro ao adicionar o lacador. Motivo: " + e.getMessage());
        }
    }
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarLacador(@PathVariable Integer id, @RequestBody LacadorDTO lacadorDTO) {
	    try {
	        // Buscar o dupla pelo ID
	        Lacador lacador = lacadorService.buscarPorId(id);
	        
	        if (lacador == null) {
	        	
	            return ResponseEntity.notFound().build();
	        }

	        // Atualizar o nome do dupla
	        lacador.setNome(lacadorDTO.getNome());
	        lacador.setCategoriaIndividual(lacadorDTO.getCategoriaIndividual());
	        lacador.setPonto(lacadorDTO.getPontos());
	        
	        // Salvar as alterações
	        lacadorService.adicionarLacador(lacador);

	        return ResponseEntity.ok("Lacador atualizado com sucesso.");
	        
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar a dupla.");
	    }
	}
	
	@PutMapping("/atualizar-lote")
	public ResponseEntity<?> atualizarDuplaLote(@RequestBody LacadorUpdateLoteDto loteDTO) {
	    try {
	        for (LacadorDTO updateDTO : loteDTO.getLacadorDTOs()) {
	            Lacador lacador = lacadorService.buscarPorId(updateDTO.getId());

	            if (lacador == null) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body("Lacador com ID " + updateDTO.getId() + " não encontrado.");
	            }

	            // Atualizar os dados do lacador
	            lacador.setNome(updateDTO.getNome());
	            lacador.setCategoriaIndividual(updateDTO.getCategoriaIndividual());
	            lacador.setPonto(updateDTO.getPontos());

	            // Salvar as alterações
	            lacadorService.adicionarLacador(lacador);
	        }

	        return ResponseEntity.ok("Laçadores atualizados com sucesso.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Erro ao atualizar os laçadores. Motivo: " + e.getMessage());
	    }
	}
	@DeleteMapping("/deletar/{nome}")
	public ResponseEntity<?> deletarLacador(@PathVariable String nome) {
	    try {
	        lacadorService.deletarLacadorPorNome(nome);
	    
	        return ResponseEntity.noContent().build();
	    } catch (EntityNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao deletar o laçador. Motivo: " + e.getMessage());
	    }
	}
	
	@GetMapping("/pontos/{quantidade}/categoria/{categoriaLacador}")
	public List<LacadorDTO> buscarLacadorPorPontosECategoria(@PathVariable int quantidade, @PathVariable String categoriaLacador) {
	    List<Lacador> lacadores = lacadorService.listarLacador();
	    return lacadores.stream()
	            .filter(lacador -> lacador.getPonto() >= quantidade && lacador.getCategoriaIndividual().equalsIgnoreCase(categoriaLacador))
	            .map(lacador -> new LacadorDTO(
	                	lacador.getId(),
	                	lacador.getNome(),
	                	lacador.getCategoriaIndividual(),
	                	lacador.getPonto()))
	                .collect(Collectors.toList());
                    
	}

	
	
	

}
