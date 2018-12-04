package br.com.docrotas.server.listerner;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.docrotas.server.entity.Cidade;
import br.com.docrotas.server.entity.Cte;

public class CteListerner {

	@PrePersist
	public void prePersistCidadeEvent(Cte cte) {
		inseriDtCriacao(cte);
		atualizaDtAlteracao(cte);
	}
	
	@PreUpdate
	public void preUpdateCidadeEvent(Cte cte) {
		atualizaDtAlteracao(cte);
	}
	
	private void atualizaDtAlteracao(Cte cte) {
		cte.setDtAlteracao(new Date());
	}
	
	private void inseriDtCriacao(Cte cte) {
		if (cte.getDtCriacao() == null) {
			cte.setDtCriacao(new Date());
		}
	}
}
