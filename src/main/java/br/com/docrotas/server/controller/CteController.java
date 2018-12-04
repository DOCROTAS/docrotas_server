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

import br.com.docrotas.server.entity.Cte;
import br.com.docrotas.server.entity.Empresa;
import br.com.docrotas.server.repository.CteRepository;
import br.com.docrotas.server.repository.EmpresaRepository;
import br.com.docrotas.server.service.CteService;
import br.com.docrotas.server.service.EmpresaService;

@CrossOrigin
@RestController
public class CteController {

	@Autowired
	private CteRepository cteRepository;
	
	@Autowired
	private CteService cteService;
	
	@GetMapping("/cte/{id}")
	public Cte buscarPorId(@PathVariable Long id) {
		return cteService.buscarPorId(id);
	}
	
	@GetMapping("/cte")
	public Page<Cte> buscarTodos(@RequestParam(value = "pagina", required = true)int pagina,
						             @RequestParam(value = "qtd", required = true)int qtd,
						             @RequestParam(value = "id", required = false)Long id) throws Exception {
		
		Pageable pageable = new PageRequest(pagina, qtd);
		
		Page<Cte> page;
		if(id != null) {
			page = cteRepository.findById(id, pageable);
		} else {
			page = cteRepository.findAll(pageable);
		}
		
		return page;
	}
	
	@PostMapping(value = "/cte")
	public Cte salvar(@RequestBody Cte cte) {
		return cteService.salvar(cte);
	}
	
	@DeleteMapping(value = "/cte/{id}")
	public void excluir(@PathVariable Long id) {
		cteRepository.delete(id);
	}
	
	@GetMapping("/cte/transmitir/{id}")
	public Cte transmitir(@PathVariable Long id) throws Exception {
		
		Cte cte = cteRepository.findOne(id);

		cteService.buscarAutorizacao(cte);
		
		return cteRepository.findOne(id);
	}
	
	@GetMapping("/cte/email/{id}")
	public void enviarEmail(@PathVariable Long id) throws Exception {
		Cte cte = cteRepository.findOne(id);

		cteService.enviarEmail(cte);
	}
}
