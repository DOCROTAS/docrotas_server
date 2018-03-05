package br.com.docrotas.server.listerner;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.docrotas.server.entity.Pessoa;

public class PessoaListerner {
	@PrePersist
	public void prePersistEmpresaEvent(Pessoa pessoa) {
		inseriDtCriacao(pessoa);
		atualizaDtAlteracao(pessoa);
	}
	
	@PreUpdate
	public void preUpdateEmpresaEvent(Pessoa pessoa) {
		atualizaDtAlteracao(pessoa);
	}
	
	private void atualizaDtAlteracao(Pessoa pessoa) {
		pessoa.setDtAlteracao(new Date());
	}
	
	private void inseriDtCriacao(Pessoa pessoa) {
		if (pessoa.getDtCriacao() == null) {
			pessoa.setDtCriacao(new Date());
		}
	}
}
