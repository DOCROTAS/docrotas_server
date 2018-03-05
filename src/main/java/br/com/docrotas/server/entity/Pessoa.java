package br.com.docrotas.server.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.docrotas.server.listerner.PessoaListerner;

@Entity
@Table(name = "pessoa")
@EntityListeners(value=PessoaListerner.class)
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 60, nullable = false)
	private String nome;
	
	@Column(name = "fantasia", length = 60, nullable = true)
	private String fantasia;
	
	@Column(name = "cpf_cnpj", length = 14, nullable = false)
	private String cpfCnpj;
	
	@Column(name = "ie", length = 14, nullable = true)
	private String ie;
	
	@Column(name = "tipo_pessoa")
	private TipoPessoa tipoPessoa;
	
	@Column(name = "dt_criacao")
	private Date dtCriacao;

	@Column(name = "dt_alteracao")
	private Date dtAlteracao;
	
	@ManyToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;
	
	@OneToMany(mappedBy="pessoaId", fetch=FetchType.LAZY)
	private List<EnderecoPessoa> enderecoPessoa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
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

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<EnderecoPessoa> getEnderecoPessoa() {
		return enderecoPessoa;
	}

	public void setEnderecoPessoa(List<EnderecoPessoa> enderecoPessoa) {
		this.enderecoPessoa = enderecoPessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", fantasia=" + fantasia + ", cpfCnpj=" + cpfCnpj + ", ie=" + ie
				+ ", tipoPessoa=" + tipoPessoa + ", dtCriacao=" + dtCriacao + ", dtAlteracao=" + dtAlteracao
				+ ", conta=" + conta + ", enderecoPessoa=" + enderecoPessoa + "]";
	}	
	
}
