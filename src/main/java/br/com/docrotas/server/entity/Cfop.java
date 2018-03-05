package br.com.docrotas.server.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cfop")
public class Cfop implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_cfop", precision = 2)
	private Long codCFOP;
	
	@Column(name = "descricao", length = 75, nullable = false)
	private String descricao;

	@Override
	public String toString() {
		return "Cfop [codCFOP=" + codCFOP + ", descricao=" + descricao + "]";
	}

	public Long getCodCFOP() {
		return codCFOP;
	}

	public void setCodCFOP(Long codCFOP) {
		this.codCFOP = codCFOP;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codCFOP == null) ? 0 : codCFOP.hashCode());
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
		Cfop other = (Cfop) obj;
		if (codCFOP == null) {
			if (other.codCFOP != null)
				return false;
		} else if (!codCFOP.equals(other.codCFOP))
			return false;
		return true;
	}
	
}
