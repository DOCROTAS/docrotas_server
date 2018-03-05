package br.com.docrotas.server.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.docrotas.server.listerner.CertificadoEntityListener;
import br.com.docrotas.server.utils.DateUtils;

@Entity
@Table(name = "certificado")
@EntityListeners(value=CertificadoEntityListener.class)
public class CertificadoEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_arquivo", length=80)
	private String nomeArquivo;
	
	@Column(name = "senha", length = 80)
	private String senha;
	
	@Column(name = "dt_inclusao")
	private Date dtInclusao;
	
	@Column(name = "dt_vencimento")
	private Date dtVencimento;
	
	@ManyToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;
	
	@JsonIgnore
	@Lob
	@Column(name = "arquivo")
	private byte[] arquivo;
	
	@Column(name = "observacao")
	private String observacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(Date dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public Date getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(Date dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public String getDtVencimentoFormatada() {
		if (dtVencimento != null) {
			return DateUtils.formatarddmmyyyyhhmmss(dtVencimento);
		} else {
			return "";
		}
	}

	@Override
	public String toString() {
		return "CertificadoEntity [id=" + id + ", nomeArquivo=" + nomeArquivo + ", senha=" + senha + ", dtInclusao=" + dtInclusao + ", dtVencimento=" + dtVencimento + ", conta=" + conta + ", arquivo=" + Arrays.toString(arquivo) + ", observacao=" + observacao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CertificadoEntity other = (CertificadoEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
