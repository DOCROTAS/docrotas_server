package br.com.docrotas.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.docrotas.server.entity.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>, JpaSpecificationExecutor<Veiculo> {
	
	public Page<Veiculo> findById(Long id, Pageable pageable);
	
	public Page<Veiculo> findByPlaca(String placa, Pageable pageable);
	
}
