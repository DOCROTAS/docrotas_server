package br.com.docrotas.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.docrotas.server.entity.Cte;

@Repository
public interface CteRepository extends JpaRepository<Cte, Long>, JpaSpecificationExecutor<Cte> {
		
	public Page<Cte> findById(Long id, Pageable pageable);
}
