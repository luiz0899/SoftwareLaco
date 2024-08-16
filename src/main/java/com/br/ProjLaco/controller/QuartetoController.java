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

import com.br.ProjLaco.dto.QuartetoDto.PessoaQuartetoDTO;
import com.br.ProjLaco.dto.QuartetoDto.QuartetoDTO;
import com.br.ProjLaco.dto.QuartetoDto.QuartetoFinalDTO;
import com.br.ProjLaco.dto.QuartetoDto.QuartetoUpdateLoteDTO;
import com.br.ProjLaco.entity.quarteto.PessoaQuarteto;
import com.br.ProjLaco.entity.quarteto.Quarteto;
import com.br.ProjLaco.service.QuartetoService;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/quarteto")
@CrossOrigin(origins = "*")
public class QuartetoController {
	
	@Autowired
	@Qualifier("quartetoServiceProxy")
	private QuartetoService quartetoService;
	
	@GetMapping("/{nome}")
	public ResponseEntity<?> buscarQuarteto(@PathVariable("nome") String nome) {
		try {
			Quarteto quarteto = quartetoService.buscarQuarteto(nome);
			return ResponseEntity.ok(quarteto); 
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<?> listarQuarteto() {
	    try {
	        List<Quarteto> quartetos = quartetoService.listarQuarteto();
	        
	        // Mapeando os quartetos para o DTO
	        List<QuartetoDTO> quartetoDTOs = quartetos.stream()
	                .map(quarteto -> new QuartetoDTO(
	                	quarteto.getId(),
	                    quarteto.getNome(),
	                    quarteto.getPessoas().stream()
	                        .map(pessoa -> new PessoaQuartetoDTO(pessoa.getId(), pessoa.getNome(), pessoa.getPontos())) 
	                        .collect(Collectors.toList())
	                ))
	                .collect(Collectors.toList());
	        // Retornando a lista de DTOs dentro de um ResponseEntity
	        return ResponseEntity.ok(quartetoDTOs);
	        
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.notFound().build();
	    }
	}
		
	@PostMapping("/adicionar")
    public ResponseEntity<?> adicionarQuarteto(@RequestBody Quarteto quarteto) {
        try {
            Quarteto salvoQuarteto = quartetoService.adicionarQuarteto(quarteto);
            return ResponseEntity.created(URI.create("/quarteto/" + salvoQuarteto.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocorreu um erro ao adicionar o quarteto. Motivo: " + e.getMessage());
        }
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarQuarteto(@PathVariable Integer id, @RequestBody QuartetoDTO quartetoDTO) {
	    try {
	        // Buscar o quarteto pelo ID
	        Quarteto quarteto = quartetoService.buscarPorId(id);
	        if (quarteto == null) {
	            return ResponseEntity.notFound().build();
	        }

	        // Atualizar o nome do quarteto
	        quarteto.setNome(quartetoDTO.getNomeQuarteto());

	        // Limpar a lista atual de pessoas
	        quarteto.getPessoas().clear();

	        // Adicionar as novas pessoas ao quarteto
	        for (PessoaQuartetoDTO pessoaDTO : quartetoDTO.getPessoas()) {
	            PessoaQuarteto pessoa = new PessoaQuarteto();
	            pessoa.setNome(pessoaDTO.getNome());
	            pessoa.setPontos(pessoaDTO.getPontos());
	            pessoa.setQuarteto(quarteto); // Associa a pessoa ao quarteto
	            quarteto.getPessoas().add(pessoa);
	        }

	        // Salvar as alterações
	        quartetoService.adicionarQuarteto(quarteto);

	        return ResponseEntity.ok("Quarteto atualizado com sucesso.");
	        
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o quarteto.");
	    }
	}
	
	@PutMapping("/atualizar-lote")
	public ResponseEntity<?> atualizarQuartetoLote(@RequestBody QuartetoUpdateLoteDTO loteDTO) {
	    try {
	        for (QuartetoDTO updateDTO : loteDTO.getQuartetoUpdates()) {
	            Quarteto quarteto = quartetoService.buscarPorId(updateDTO.getId());
	            if (quarteto != null) {
	                // Atualizar o nome do quarteto
	                quarteto.setNome(updateDTO.getNomeQuarteto());

	                // Limpar a lista atual de pessoas
	                quarteto.getPessoas().clear();

	                // Adicionar as novas pessoas ao quarteto
	                for (PessoaQuartetoDTO pessoaDTO : updateDTO.getPessoas()) {
	                    PessoaQuarteto pessoa = new PessoaQuarteto();
	                    pessoa.setNome(pessoaDTO.getNome());
	                    pessoa.setPontos(pessoaDTO.getPontos());
	                    pessoa.setQuarteto(quarteto); // Associa a pessoa ao quarteto
	                    quarteto.getPessoas().add(pessoa);
	                }

	                // Salvar as alterações
	                quartetoService.adicionarQuarteto(quarteto);
	            } else {
	                // Caso algum quarteto não seja encontrado
	                throw new EntityNotFoundException("Quarteto com ID " + updateDTO.getId() + " não encontrado.");
	            }
	        }
	        return ResponseEntity.ok("Quartetos atualizados com sucesso.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar os quarteto. Motivo: " + e.getMessage());
	    }
	}

	
	@DeleteMapping("/deletar/{nome}")
	public ResponseEntity<?> deletarQuarteto(@PathVariable String nome) {
	    try {
	        quartetoService.deletarQuartetoPorNome(nome);
	        return ResponseEntity.noContent().build();
	    } catch (EntityNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao deletar o quarteto. Motivo: " + e.getMessage());
	    }
	}
	
	@GetMapping("/pontos/{quantidade}")
	public List<QuartetoFinalDTO> buscarQuartetosPorPontos(@PathVariable int quantidade) {
	    List<Quarteto> quartetos = quartetoService.listarQuarteto();
	    return quartetos.stream()
	            .filter(quarteto -> quarteto.getPontosSomados() >= quantidade)
	            .map(quarteto -> new QuartetoFinalDTO(
	                    quarteto.getId(),
	                    quarteto.getNome(),
	                    quarteto.getPessoas().stream().map(pessoa -> new PessoaQuartetoDTO(
	                            pessoa.getId(),
	                            pessoa.getNome(),
	                            pessoa.getPontos()
	                    )).collect(Collectors.toList()), 
	                    quarteto.getPontosSomados()
	            ))
	            .collect(Collectors.toList());
	}
	

	
	
}
