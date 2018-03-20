package br.com.docrotas.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.docrotas.server.entity.Cfop;

public interface CfopRepository extends JpaRepository<Cfop, Long>, JpaSpecificationExecutor<Cfop> {
	public Page<Cfop> findById(Long id, Pageable pageable);
	
	public Page<Cfop> findByDescricaoContaining(String descricao, Pageable pageable);
}
