package com.br.ProjLaco.repository.quarteto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.ProjLaco.entity.quarteto.PessoaQuarteto;


@Repository
public interface PessoaRepository extends JpaRepository<PessoaQuarteto , Integer > {

}
