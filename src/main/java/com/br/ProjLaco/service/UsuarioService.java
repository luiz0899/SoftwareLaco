package com.br.ProjLaco.service;

import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import com.br.ProjLaco.entity.Usuario;

@Validated
public interface UsuarioService {
	
	public Usuario obterPorEmail (String email , String senha);
	
	public Usuario adicionarUsuario(Usuario usuario);
	
}
