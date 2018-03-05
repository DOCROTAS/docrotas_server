package br.com.docrotas.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.docrotas.server.entity.EnderecoPessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface EnderecoPessoaRepository extends JpaRepository<EnderecoPessoa, Long>, JpaSpecificationExecutor<EnderecoPessoa> {

	public Page<EnderecoPessoa> findById(Long id, Pageable pageable);
	
	public Page<EnderecoPessoa> findByPessoaId(Long pessoaId, Pageable pageable);
	
}
