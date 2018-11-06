package br.com.docrotas.server.service;

import static br.com.docrotas.server.specification.PessoaSpecification.filtrarOpcoes;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.docrotas.server.entity.Pessoa;
import br.com.docrotas.server.repository.PessoaRepository;

@Service
public class PessoaServiceImpl /*implements PessoaService*/ {
	
//	@Autowired
//	private PessoaRepository pessoaRepository;
//	
//	@Override
//	public Page<Pessoa> findAll(Map<String, String> filtro, Pageable pageable){
//		return pessoaRepository.findAll(filtrarOpcoes(filtro), pageable);
//	}
//	
//	@Override
//	public Pessoa salvar(Pessoa pessoa) {
//		return pessoaRepository.save(pessoa);
//	}
//
//	@Override
//	public void deletar(Long id) {
//		pessoaRepository.delete(id);
//	}
	
}
