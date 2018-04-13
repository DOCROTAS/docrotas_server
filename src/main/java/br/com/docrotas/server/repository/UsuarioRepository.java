package br.com.docrotas.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.docrotas.server.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

	public Page<Usuario> findById(Long id, Pageable pageable);
	
	public Page<Usuario> findByLoginContaining(String login, Pageable pageable);
	
	public Usuario findByLoginAndSenha(String login, String senha);
	
	public Page<Usuario> findByEmail(String email, Pageable pageable);

	public Page<Usuario> findByNomeContaining(String nome, Pageable pageable);
	
}
