package br.com.docrotas.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.docrotas.server.entity.Usuario;
import br.com.docrotas.server.repository.UsuarioRepository;
import br.com.docrotas.server.service.UsuarioService;

@CrossOrigin
@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuario")
	public Page<Usuario> buscarTodos(@RequestParam(value = "pagina", required = true)int pagina,
									 @RequestParam(value = "qtd", required = true)int qtd,
									 @RequestParam(value = "id", required = false)Long id,
									 @RequestParam(value = "login", required = false)String login,
									 @RequestParam(value = "email", required = false)String email,
									 @RequestParam(value = "nome", required = false)String nome){
		Pageable pageable = new PageRequest(pagina, qtd);
		
		Page<Usuario> pageUsuarios;
		if(id != null){
			pageUsuarios = usuarioRepository.findById(id, pageable);
		} else if (login != null) {
			pageUsuarios = usuarioRepository.findByLoginContaining(login, pageable);
		} else if (email != null) {
			pageUsuarios = usuarioRepository.findByEmail(email, pageable);
		} else if (nome != null) {
			pageUsuarios = usuarioRepository.findByNomeContaining (nome, pageable);
		}  else {
			pageUsuarios = usuarioRepository.findAll(pageable);
		}
		
		return pageUsuarios;
	}

	@GetMapping("/usuario/{id}")
	public Usuario buscarPorID(@PathVariable Long id) {
		return usuarioService.buscarPorID(id);
	}

	@PostMapping(value = "/usuario")
	public Usuario salvar(@RequestBody Usuario usuario) throws Exception{
		return usuarioService.salvar(usuario);
	}
	
	@DeleteMapping("/usuario/{id}")
	public void excluir(@PathVariable Long id) {
		usuarioRepository.delete(id);
	}
}
