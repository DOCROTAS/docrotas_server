package br.com.docrotas.server.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.docrotas.server.listerner.VeiculoListerner;

@Entity
@Table(name = "veiculo")
@EntityListeners(value=VeiculoListerner.class)
public class Veiculo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cod_interno")
	private String codInterno;
	
	@Column(name = "placa")
	private String placa;
	
	@Column(name = "renavam")
	private String renavam;
	
	@Column(name = "tara")
	private Long tara;
	
	@Column(name = "cap_kg")
	private Long capKG;
	
	@Column(name = "cap_m3")
	private Long capM3;
	
	@Column(name = "rntrc")
	private String rntrc;
	
	@ManyToOne
	@JoinColumn(name = "pessoaproprietario_id", referencedColumnName = "id")
	private Pessoa pessoaProprietario;
	
	@ManyToOne
	@JoinColumn(name = "uflicenciamento_id", referencedColumnName = "cod_ibge")
	private Uf ufLicenciamento;
	
	@Column(name = "tipo_proprietario")
	private TipoProprietario tipoProprietario;
	
	@Column(name = "tipo_rodado")
	private TipoRodado tipoRodado;
	
	@Column(name = "tipo_carroceria")
	private TipoCarroceria tipoCarroceria;
	
	@Column(name = "dt_criacao")
	private Date dtCriacao;

	@Column(name = "dt_alteracao")
	private Date dtAlteracao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodInterno() {
		return codInterno;
	}

	public void setCodInterno(String codInterno) {
		this.codInterno = codInterno;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public Long getTara() {
		return tara;
	}

	public void setTara(Long tara) {
		this.tara = tara;
	}

	public Long getCapKG() {
		return capKG;
	}

	public void setCapKG(Long capKG) {
		this.capKG = capKG;
	}

	public Long getCapM3() {
		return capM3;
	}

	public void setCapM3(Long capM3) {
		this.capM3 = capM3;
	}

	public String getRntrc() {
		return rntrc;
	}

	public void setRntrc(String rntrc) {
		this.rntrc = rntrc;
	}

	public Pessoa getPessoaProprietario() {
		return pessoaProprietario;
	}

	public void setPessoaProprietario(Pessoa pessoaProprietario) {
		this.pessoaProprietario = pessoaProprietario;
	}

	public Uf getUfLicenciamento() {
		return ufLicenciamento;
	}

	public void setUfLicenciamento(Uf ufLicenciamento) {
		this.ufLicenciamento = ufLicenciamento;
	}

	public TipoProprietario getTipoProprietario() {
		return tipoProprietario;
	}

	public void setTipoProprietario(TipoProprietario tipoProprietario) {
		this.tipoProprietario = tipoProprietario;
	}

	public TipoRodado getTipoRodado() {
		return tipoRodado;
	}

	public void setTipoRodado(TipoRodado tipoRodado) {
		this.tipoRodado = tipoRodado;
	}

	public TipoCarroceria getTipoCarroceria() {
		return tipoCarroceria;
	}

	public void setTipoCarroceria(TipoCarroceria tipoCarroceria) {
		this.tipoCarroceria = tipoCarroceria;
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

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", codInterno=" + codInterno + ", placa=" + placa + ", renavam=" + renavam
				+ ", tara=" + tara + ", capKG=" + capKG + ", capM3=" + capM3 + ", rntrc=" + rntrc
				+ ", pessoaProprietario=" + pessoaProprietario + ", ufLicenciamento=" + ufLicenciamento
				+ ", tipoProprietario=" + tipoProprietario + ", tipoRodado=" + tipoRodado + ", tipoCarroceria="
				+ tipoCarroceria + ", dtCriacao=" + dtCriacao + ", dtAlteracao=" + dtAlteracao + "]";
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
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
		
}
