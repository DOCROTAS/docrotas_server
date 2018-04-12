package br.com.docrotas.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.docrotas.server.entity.Empresa;
import br.com.docrotas.server.entity.MDFe;
import br.com.docrotas.server.entity.Veiculo;

@Repository
public interface MDFeRepository extends JpaRepository<MDFe, Long>, JpaSpecificationExecutor<MDFe> {
	
	public MDFe findById(Long id);
	
	public Page<MDFe> findById(Long id, Pageable pageable);
	
	public Page<MDFe> findByNumero(Long numero, Pageable pageable);
	
	public Page<MDFe> findByEmitente(Empresa empresa, Pageable pageable);
	
	public Page<MDFe> findBySituacao(Long situacao, Pageable pageable);
	
	public Page<MDFe> findByVeiculo(Veiculo veiculo, Pageable pageable);
	
}
