package br.com.docrotas.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.docrotas.server.entity.CertificadoEntity;

@Repository
public interface CertificadoEntityRepository extends JpaRepository<CertificadoEntity, Long>, JpaSpecificationExecutor<CertificadoEntity>{

	public CertificadoEntity findById(Long id);

	public Page<CertificadoEntity> findByContaId(Long id, Pageable page);
}
