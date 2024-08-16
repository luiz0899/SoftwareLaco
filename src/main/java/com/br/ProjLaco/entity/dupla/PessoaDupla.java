
package com.br.ProjLaco.entity.dupla;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class PessoaDupla {
	
	@Id
	@Column(name = "id_dupla")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
 
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "pontos")
    private Integer pontos;
	
	@ManyToOne
	@JoinColumn(name = "dupla_id")
	@JsonBackReference 
	private Dupla dupla;

	
	


}
