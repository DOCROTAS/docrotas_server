package br.com.docrotas.server.listerner;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.docrotas.server.entity.EnderecoPessoa;

public class EnderecoPessoaListerner {

	@PrePersist
	public void prePersistEnderecoPessoaEvent(EnderecoPessoa enderecoPessoa) {
		inseriDtCriacao(enderecoPessoa);
		atualizaDtAlteracao(enderecoPessoa);
	}
	
	@PreUpdate
	public void preUpdateEnderecoPessoaEvent(EnderecoPessoa enderecoPessoa) {
		atualizaDtAlteracao(enderecoPessoa);
	}

	private void atualizaDtAlteracao(EnderecoPessoa enderecoPessoa) {
		enderecoPessoa.setDtAlteracao(new Date());	
	}

	private void inseriDtCriacao(EnderecoPessoa enderecoPessoa) {
		if (enderecoPessoa.getDtCriacao() == null) {
			enderecoPessoa.setDtCriacao(new Date());
		}
	}
}
