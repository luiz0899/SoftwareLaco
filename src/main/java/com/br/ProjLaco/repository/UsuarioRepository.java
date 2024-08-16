package com.br.ProjLaco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.ProjLaco.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha")
	public Usuario buscarPorEmail (String email, String senha);
 
}

