package br.com.docrotas.server.listerner;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.docrotas.server.entity.Usuario;

public class UsuarioListerner {

	@PrePersist
	public void prePersistUsuarioEvent(Usuario usuario) {
		inseriDtCriacao(usuario);
		atualizaDtAlteracao(usuario);
	}
	
	@PreUpdate
	public void preUpdateUsuarioEvent(Usuario usuario){
		atualizaDtAlteracao(usuario);
	}
	
	private void atualizaDtAlteracao(Usuario usuario) {
		usuario.setDtAlteracao(new Date());
		
	}

	private void inseriDtCriacao(Usuario usuario) {
		if (usuario.getDtCriacao() == null) {
			usuario.setDtCriacao(new Date());
		}
	}	
	
}
