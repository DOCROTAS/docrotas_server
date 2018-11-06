package br.com.docrotas.server.entity;

import java.util.Date;

import javax.persistence.Column;

public class EntidadeBasica {
	
	@Column(name = "dt_criacao")
	private Date dtCriacao;

	@Column(name = "dt_alteracao")
	private Date dtAlteracao;

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}
}
