package com.br.ProjLaco.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.br.ProjLaco.entity.Usuario;
import com.br.ProjLaco.service.UsuarioService;

@Service
public class UsuarioServiceProxy implements UsuarioService {
	
	@Autowired	
	@Qualifier("usuarioServiceImpl")
	private UsuarioService service;

	@Override
	public Usuario obterPorEmail(String email, String senha) {
		return service.obterPorEmail(email, senha);
	}

	@Override
	public Usuario adicionarUsuario(Usuario usuario) {
		return service.adicionarUsuario(usuario);
	}
	

}
