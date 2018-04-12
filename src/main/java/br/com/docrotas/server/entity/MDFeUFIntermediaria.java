package br.com.docrotas.server.entity;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mdfe_uf")
@AssociationOverrides({
	@AssociationOverride(name = "primaryKey.mdfe", joinColumns = @JoinColumn(name  = "id_mdfe")),
	@AssociationOverride(name = "primaryKey.uf", joinColumns = @JoinColumn(name = "id_uf")) })
public class MDFeUFIntermediaria {
	
	@JsonIgnore
	@EmbeddedId
	private MDFeUfId primaryKey = new MDFeUfId();
	
	@Column(name = "sequencia")
	private Integer sequencia;
	
	@Column(name = "dt_criacao")
	private Date dtCriacao;
	
	@Column(name = "dt_alteracao")
	private Date dtAlteracao;

	public MDFeUfId getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(MDFeUfId primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Transient
	public MDFe getMDFe() {
		return getPrimaryKey().getMdfe();
	}
	
	public void setMDFe(MDFe mdfe) {
		getPrimaryKey().setMdfe(mdfe);
	}
	
	@Transient
	public Uf getUf() {
		return getPrimaryKey().getUf();
	}
	
	public void setUf(Uf uf) {
		getPrimaryKey().setUf(uf);
	}
	
	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

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
