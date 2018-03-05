package br.com.docrotas.server.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Endereco {
	
	@Column(name = "logradouro", length=70, nullable = false)
	private String logradouro;
	
	@Column(name = "nro", nullable = false)
	private int numero;
	
	@Column(name = "complemento", length=60)
	private String complemento;
	
	@Column(name = "bairro", length = 60, nullable = false)
	private String bairro;
	
	@Column(name = "cep", length = 8, nullable = false)
	private String cep;
	
	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}	

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "Endereco [logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento
				+ ", bairro=" + bairro + ", cidade=" + cidade + ", cep=" + cep + "]";
	}

}
