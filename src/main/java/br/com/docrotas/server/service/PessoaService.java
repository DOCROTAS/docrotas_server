package br.com.docrotas.server.service;

import static br.com.docrotas.server.specification.PessoaSpecification.filtrarOpcoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.docrotas.server.entity.Empresa;
import br.com.docrotas.server.entity.EnderecoPessoa;
import br.com.docrotas.server.entity.Pessoa;
import br.com.docrotas.server.repository.CidadeRepository;
import br.com.docrotas.server.repository.ContaRepository;
import br.com.docrotas.server.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired 
	private CidadeRepository cidadeRepository;
		
	public Page<Pessoa> findAll(Map<String, String> filtro, Pageable pageable) {
		return pessoaRepository.findAll(filtrarOpcoes(filtro), pageable);
	}
	
	public Pessoa salvar(Pessoa pessoa) {
		pessoa.setConta(contaRepository.findOne(1L));
		
		if (pessoa.getEnderecoPessoa() == null || pessoa.getEnderecoPessoa().isEmpty()) {
			EnderecoPessoa endereco = new EnderecoPessoa();
			endereco.setBairro("Brasil");
			endereco.setCep("38400000");
			endereco.setCidade(cidadeRepository.findOne(5318L));
			endereco.setComplemento("casa");
			endereco.setLogradouro("Av. Afonso Pena");
			endereco.setNumero(100);
			endereco.setPessoaId(pessoa);
			
			List<EnderecoPessoa> enderecos = new ArrayList<EnderecoPessoa>();
			enderecos.add(endereco);
			
			pessoa.setEnderecoPessoa(enderecos);		
		}
		
		return pessoaRepository.save(pessoa);
	}
	
	public void deletar(Long id) {
		pessoaRepository.delete(id);
	}
	
	public Pessoa buscarPorId(Long id) {
		return pessoaRepository.findOne(id);
	}
}
