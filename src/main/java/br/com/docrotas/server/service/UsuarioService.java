package br.com.docrotas.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.docrotas.server.entity.Usuario;
import br.com.docrotas.server.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario buscarPorID(Long id) {
		return usuarioRepository.findOne(id);
	}
	
	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}

	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}
