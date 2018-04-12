package br.com.docrotas.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.docrotas.server.entity.MDFeUFIntermediaria;

@Repository
public interface MDFeUfRepository extends JpaRepository<MDFeUFIntermediaria, Long>, JpaSpecificationExecutor<MDFeUFIntermediaria> {
	
}
