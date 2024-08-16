package com.br.ProjLaco.repository.dupla;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.ProjLaco.entity.dupla.PessoaDupla;

@Repository
public interface PessoaDuplaRepository extends JpaRepository<PessoaDupla , Integer > {

}
