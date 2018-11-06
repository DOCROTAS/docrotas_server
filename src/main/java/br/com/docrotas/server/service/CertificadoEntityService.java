package br.com.docrotas.server.service;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.docrotas.server.entity.CertificadoEntity;
import br.com.docrotas.server.entity.Conta;
import br.com.docrotas.server.repository.CertificadoEntityRepository;
import br.com.docrotas.server.repository.ContaRepository;
import br.com.docrotas.server.utils.CertificadoDigitalUtils;

@Service
public class CertificadoEntityService {
	
	@Autowired
	private CertificadoEntityRepository certificadoEntityRepository;
	@Autowired
	private ContaRepository contaRepository;
	
	public CertificadoEntity buscarPorId (Long id){
		return certificadoEntityRepository.findById(id);
	}

	public Page<CertificadoEntity> buscarPorContaId (Long id, Pageable page) {
		return certificadoEntityRepository.findByContaId(id, page);
	}

	public Page<CertificadoEntity> buscarPorConta (Conta conta, Pageable page) {
		return buscarPorContaId(conta.getId(), page);
	}

	public Page<CertificadoEntity> buscarTodos(Pageable pageable) {
		return certificadoEntityRepository.findAll(pageable);
	}

	public CertificadoEntity salvar (CertificadoEntity certificadoEntity) {
		
		return certificadoEntityRepository.save(certificadoEntity);
	}
	
	public void excluir (CertificadoEntity certificadoEntity) {
		certificadoEntityRepository.delete(certificadoEntity);
	}
	
	public void excluir (Long id) {
		certificadoEntityRepository.delete(id);
	}

	public CertificadoEntity salvarArquivo(MultipartFile file, String senha) throws IOException, Exception {
		
		boolean senhaValida = CertificadoDigitalUtils.senhaValida(file.getBytes(), senha);
		
		if (!senhaValida) {
			throw new Exception("Senha do certificado inválida.");
		} 
		
		CertificadoEntity certificado = new CertificadoEntity();
		
		Date vencimento = CertificadoDigitalUtils.getDataVencimento(file.getBytes(), senha);
		
		if (vencimento == null) {
			throw new Exception("Não foi possível obter da data do vencimento do certificado");
		}
		
		if (vencimento.getTime() < new Date().getTime()) {
			throw new Exception("Certificado esta vencido.");
		}
		
//		FIXME - Pegar conta do usuário
		certificado.setConta(contaRepository.getOne(1L));
		certificado.setArquivo(file.getBytes());
		certificado.setNomeArquivo(file.getOriginalFilename());
		certificado.setSenha(senha);
		certificado.setDtVencimento(vencimento);
		certificado.setObservacao(CertificadoDigitalUtils.getObservacao(file.getBytes(), senha));
		
		return salvar(certificado);
	}
}
