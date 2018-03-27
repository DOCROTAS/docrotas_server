package br.com.docrotas.server.controller;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import br.com.docrotas.server.entity.CertificadoEntity;
import br.com.docrotas.server.service.CertificadoEntityService;

@CrossOrigin
@RestController
public class CertificadoEntityController {
	
	@Autowired
	private CertificadoEntityService certificadoEntityService;
	
	@GetMapping("/certificado/{id}")
	public CertificadoEntity buscarPorId(@PathVariable Long id) throws IOException {
		return certificadoEntityService.buscarPorId(id);
	}

	@GetMapping("/certificado")
	public Page<CertificadoEntity> buscar(	@RequestParam(value = "pagina", required = true)int pagina,
						             		@RequestParam(value = "qtd", required = true)int qtd,
					             			@RequestParam(value = "contaId", required = false)Long contaId){
		Pageable pageable = new PageRequest(pagina, qtd);

		Page pageCertificado;

		if (contaId != null) {
			pageCertificado = certificadoEntityService.buscarPorContaId(contaId,pageable);
		} else {
			pageCertificado = certificadoEntityService.buscarTodos(pageable);
		}

		return pageCertificado;
	}
	
	@DeleteMapping("/certificado/{id}")
	public void excluir (@PathVariable Long id) {
		certificadoEntityService.excluir(id);
	}
	
	@PostMapping("/certificado")
	public void carregarArquivo(@RequestParam(name = "file", required = true) MultipartFile file,
								@RequestParam(name = "senha", required = true) String senha) throws IOException, Exception {
		certificadoEntityService.validar(file, senha);
	}
	
}
