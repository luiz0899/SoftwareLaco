package com.br.ProjLaco.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ProjLaco.entity.Usuario;
import com.br.ProjLaco.repository.UsuarioRepository;
import com.br.ProjLaco.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario obterPorEmail(String email, String senha) {
		
		try {
			Usuario usuarioEncontrado = usuarioRepository.buscarPorEmail(email, senha);
			return usuarioEncontrado;
		} catch (Exception e) {
			
			throw new IllegalArgumentException(" Ousuario não existe!");
		}	
	}
	@Override
	public Usuario adicionarUsuario(Usuario usuario) {
		
		try {
			usuarioRepository.save(usuario);
			return usuario;
		} catch (Exception e) {
			throw new IllegalArgumentException("não foi possivel salvar seu usuario");
		}
		
	}

}
