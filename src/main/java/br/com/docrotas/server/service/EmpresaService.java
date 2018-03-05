package br.com.docrotas.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.docrotas.server.entity.Empresa;
import br.com.docrotas.server.repository.ContaRepository;
import br.com.docrotas.server.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private ContaRepository contaRepository;
	
	public Empresa buscarPorId(Long id) {
		return empresaRepository.findOne(id);
	}
	
	public Empresa salvar(Empresa empresa) {
		//FIXME - Alterar para pegar a conta do usuário
		empresa.setConta(contaRepository.findOne(1L));
		return empresaRepository.save(empresa);
//		return empresaRepository.findOne(empresa.getId());
	}
}
