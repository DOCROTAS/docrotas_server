package br.com.docrotas.server.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.docrotas.server.entity.Pessoa;
import br.com.docrotas.server.service.PessoaService;

@RestController
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/pessoa")
	public Page<Pessoa> buscarTodos(@RequestParam(value = "pagina", required = true)int pagina,
						            @RequestParam(value = "qtd", required = true)int qtd,
						            @RequestParam(required = false)Map<String, String> filtro) throws Exception {
		return pessoaService.findAll(filtro, new PageRequest(pagina, qtd));
	}
	
	@PostMapping(value = "/pessoa")
	public Pessoa salvar(@RequestBody Pessoa pessoa) {
		return pessoaService.salvar(pessoa);
	}
	
	@DeleteMapping(value = "/pessoa/{id}")
	public void excluir(@PathVariable Long id) {
		pessoaService.deletar(id);
	}
}
	
