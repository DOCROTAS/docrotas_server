package br.com.docrotas.server.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.docrotas.server.entity.Pessoa;

public interface PessoaService {
		
	Page<Pessoa> findAll(Map<String, String> filtro, Pageable pageable);
	
	Pessoa salvar(Pessoa pessoa);
	
	void deletar(Long id);
}
