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

import br.com.docrotas.server.entity.Empresa;
import br.com.docrotas.server.entity.MDFe;
import br.com.docrotas.server.entity.Veiculo;
import br.com.docrotas.server.repository.MDFeRepository;

@CrossOrigin
@RestController
public class MDFeController {
	
	@Autowired
	private MDFeRepository mdfeRepository;
	
	@GetMapping("/mdfe/{id}")
	public MDFe buscarPorId(@PathVariable Long id) {
		return mdfeRepository.findById(id);
	}
	
	@GetMapping("/mdfe")
	public Page<MDFe> buscarTodos(@RequestParam(value = "pagina", required = true)int pagina,
								  @RequestParam(value = "qtd", required = true)int qtd,
								  @RequestParam(value = "id", required = false)Long id,
								  @RequestParam(value = "numero", required = false)Long numero,
								  @RequestParam(value = "empresa", required = false)Long empresaId,
								  @RequestParam(value = "situacao", required = false)Long situacao,
								  @RequestParam(value = "placa", required = false)String placa) throws Exception {
		Pageable pageable = new PageRequest(pagina, qtd);
		
		Page<MDFe> pageMDFes;
		if(id != null) {
			pageMDFes = mdfeRepository.findById(id, pageable);
		}else if(numero != null) {
			pageMDFes = mdfeRepository.findByNumero(numero, pageable);
		}else if(empresaId != null) {
			Empresa empresa = new Empresa();
			empresa.setId(empresaId);
			pageMDFes = mdfeRepository.findByEmitente(empresa, pageable);
		}else if(situacao != null) {
			pageMDFes = mdfeRepository.findBySituacao(situacao, pageable);
		}else if(placa != null) {
			Veiculo veiculo = new Veiculo();
			veiculo.setPlaca(placa);
			pageMDFes = mdfeRepository.findByVeiculo(veiculo, pageable);
		}else {
			pageMDFes = mdfeRepository.findAll(pageable);
		}
		
		return pageMDFes;
	}
	
	@PostMapping(value = "/mdfe")
	public MDFe salvar(@RequestBody MDFe mdfe) {
		return mdfeRepository.save(mdfe);
	}

	@DeleteMapping(value = "/mdfe/{id}")
	public void excluir(@PathVariable Long id) {
		mdfeRepository.delete(id);
	}
}
