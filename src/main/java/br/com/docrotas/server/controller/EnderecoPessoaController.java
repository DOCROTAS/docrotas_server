package br.com.docrotas.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.docrotas.server.entity.EnderecoPessoa;
import br.com.docrotas.server.repository.EnderecoPessoaRepository;

@RestController
public class EnderecoPessoaController {

	@Autowired
	private EnderecoPessoaRepository enderecoPessoaRepository;
	
	@GetMapping("/enderecopessoa")
	public Page<EnderecoPessoa> buscarTodos(@RequestParam(value = "pagina", required = true)int pagina,
						            		@RequestParam(value = "qtd", required = true)int qtd,
						            		@RequestParam(value = "id", required = false)Long id,
						            		@RequestParam(value = "pessoaid", required = false)Long pessoaid) throws Exception {
		
		Pageable pageable = new PageRequest(pagina, qtd);
		
		Page<EnderecoPessoa> pageEnderecoPessoa;
		
		if(id != null) {
			pageEnderecoPessoa = enderecoPessoaRepository.findById(id, pageable);
		}else if(pessoaid != null) {
			pageEnderecoPessoa = enderecoPessoaRepository.findByPessoaId(pessoaid, pageable);
		}else {
			pageEnderecoPessoa = enderecoPessoaRepository.findAll(pageable);
		}
		
		return pageEnderecoPessoa;
	}
	
	@PostMapping(value = "/enderecopessoa")
	public EnderecoPessoa salvar(@RequestBody EnderecoPessoa enderecoPessoa) {
		return enderecoPessoaRepository.save(enderecoPessoa);
	}
	
	@DeleteMapping(value = "/enderecopessoa/{id}")
	public void excluir(@PathVariable Long id) {
		enderecoPessoaRepository.delete(id);
	}
}
