package br.com.docrotas.server.repository;

import org.springframework.stereotype.Repository;

import br.com.docrotas.server.entity.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa> {
		
}
