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

import br.com.docrotas.server.entity.Cidade;
import br.com.docrotas.server.repository.CidadeRepository;

@CrossOrigin
@RestController
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@GetMapping("/cidade/{id}")
	public Cidade buscarPorId(@PathVariable Long id) {
		return cidadeRepository.findOne(id);
	}
	
	@GetMapping("/cidade")
	public Page<Cidade> buscarTodos(@RequestParam(value = "pagina", required = true)int pagina,
									@RequestParam(value = "qtd", required = true)int qtd,
									@RequestParam(value = "id", required = false)Long id,
									@RequestParam(value = "nome", required = false)String nome,
									@RequestParam(value = "ufId", required = false)Long ufId,
									@RequestParam(value = "codibge", required = false)Long codibge,
									@RequestParam(value = "siglaUf", required = false)String siglaUf) throws Exception{
		Pageable pageable = new PageRequest(pagina, qtd); 
		
		Page<Cidade> pageCidades;
		if (id != null) {
			pageCidades = cidadeRepository.findById(id, pageable);
		} else if(nome != null) {
			pageCidades = cidadeRepository.findByNomeContaining(nome, pageable);
		} else if(ufId != null) {
			pageCidades = cidadeRepository.findByUf(ufId, pageable);
		} else if(codibge != null) {
			pageCidades = cidadeRepository.findByCodIBGE(codibge, pageable);
		} else if(siglaUf != null) {
			pageCidades = cidadeRepository.findByUfSigla(siglaUf, pageable);
		} else {
			pageCidades = cidadeRepository.findAll(pageable);
		}
		
		return pageCidades;
	}
	
	@PostMapping(value = "/cidade")
	public Cidade salvar(@RequestBody Cidade cidade) {
		return cidadeRepository.save(cidade);
	}
	
	@DeleteMapping("/cidade/{id}")
	public void excluir(@PathVariable Long id) {
		cidadeRepository.delete(id);
	}	
	
	
		
}

