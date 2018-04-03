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

import br.com.docrotas.server.entity.Cfop;
import br.com.docrotas.server.repository.CfopRepository;

@CrossOrigin
@RestController
public class CfopController {

	@Autowired
	private CfopRepository cfopRepository;
	
	@GetMapping("/cfop/{id}")
	public Cfop buscarPorID(@PathVariable Long id) {
		return cfopRepository.findOne(id);
	}
	
	@GetMapping("/cfop")
	public Page<Cfop> buscarTodos(@RequestParam(value = "pagina", required = true)int pagina,
								  @RequestParam(value = "qtd", required = true)int qtd,
								  @RequestParam(value = "codCfop", required = false)Long id,
								  @RequestParam(value = "descricao", required = false)String descricao) throws Exception{
		Pageable pageable = new PageRequest(pagina, qtd);
		
		Page<Cfop> pageCfops;
		if(id != null) {
			pageCfops = cfopRepository.findById(id, pageable);
		} else if(descricao != null) {
			pageCfops = cfopRepository.findByDescricaoContaining(descricao, pageable);
		} else {
			pageCfops = cfopRepository.findAll(pageable);
		}
		
		return pageCfops;
	}
	
	@PostMapping(value = "/cfop")
	public Cfop salvar(@RequestBody Cfop cfop) {
		return cfopRepository.save(cfop);
	}
	
	@DeleteMapping("/cfop/{id}")
	public void excluir(@PathVariable Long id) {
		cfopRepository.delete(id);
	}
	
}
