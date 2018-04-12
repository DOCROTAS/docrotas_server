package br.com.docrotas.server.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.docrotas.server.listerner.MDFeListerner;

@Entity
@Table(name = "mdfe")
@EntityListeners(value=MDFeListerner.class)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class MDFe implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero")
	private Long numero;
	
	@Column(name = "serie")
	private Long serie;
	
	@Column(name = "chave")
	private String chave;
	
	@Column(name = "dt_emissao")
	private Date dtEmissao;
	
	@Column(name = "dt_iviagem")	
	private Date dtInicioViagem;
	
	@Column(name = "dt_fviagem")
	private Date dtFimViagem;
	
	@Column(name = "apolice")
	private String apolice;	
	
	@Column(name = "averbacao")
	private String averbacao;
	
	@Column(name = "dt_criacao")
	private Date dtCriacao;
	
	@Column(name = "dt_alteracao")
	private Date dtAlteracao;
	
	@Column(name = "tipo_ambiente")
	private TipoAmbienteEmissao tipoAmbienteEmissao;
	
	@Column(name = "tipo_emissao")
	private TipoEmissao tipoEmissao;
	
	@Column(name = "situacao")
	private Long situacao;
	
	@ManyToOne
	@JoinColumn(name = "emitente_id")
	private Empresa emitente;
	
	@ManyToOne
	@JoinColumn(name = "seguradora_id")
	private Pessoa seguradora;
	
	@ManyToOne
	@JoinColumn(name = "motorista_id")
	private Pessoa motorista;
	
	@ManyToOne
	@JoinColumn(name = "cidadeorigem_id")
	private Cidade cidadeOrigem;
	
	@ManyToOne
	@JoinColumn(name = "cidadedestino_id")
	private Cidade cidadeDestino;
	
	@ManyToOne
	@JoinColumn(name = "veiculo_id")
	private Veiculo veiculo;

	@OneToMany(mappedBy = "primaryKey.mdfe")
	private List<MDFeUFIntermediaria> mdfeUfIntermediaria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Long getSerie() {
		return serie;
	}

	public void setSerie(Long serie) {
		this.serie = serie;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Date getDtEmissao() {
		return dtEmissao;
	}

	public void setDtEmissao(Date dtEmissao) {
		this.dtEmissao = dtEmissao;
	}

	public Date getDtInicioViagem() {
		return dtInicioViagem;
	}

	public void setDtInicioViagem(Date dtInicioViagem) {
		this.dtInicioViagem = dtInicioViagem;
	}

	public Date getDtFimViagem() {
		return dtFimViagem;
	}

	public void setDtFimViagem(Date dtFimViagem) {
		this.dtFimViagem = dtFimViagem;
	}

	public String getApolice() {
		return apolice;
	}

	public void setApolice(String apolice) {
		this.apolice = apolice;
	}

	public String getAverbacao() {
		return averbacao;
	}

	public void setAverbacao(String averbacao) {
		this.averbacao = averbacao;
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

	public TipoAmbienteEmissao getTipoAmbienteEmissao() {
		return tipoAmbienteEmissao;
	}

	public void setTipoAmbienteEmissao(TipoAmbienteEmissao tipoAmbienteEmissao) {
		this.tipoAmbienteEmissao = tipoAmbienteEmissao;
	}

	public TipoEmissao getTipoEmissao() {
		return tipoEmissao;
	}

	public void setTipoEmissao(TipoEmissao tipoEmissao) {
		this.tipoEmissao = tipoEmissao;
	}

	public Long getSituacao() {
		return situacao;
	}

	public void setSituacao(Long situacao) {
		this.situacao = situacao;
	}

	public Empresa getEmitente() {
		return emitente;
	}

	public void setEmitente(Empresa emitente) {
		this.emitente = emitente;
	}

	public Pessoa getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Pessoa seguradora) {
		this.seguradora = seguradora;
	}

	public Pessoa getMotorista() {
		return motorista;
	}

	public void setMotorista(Pessoa motorista) {
		this.motorista = motorista;
	}

	public Cidade getCidadeOrigem() {
		return cidadeOrigem;
	}

	public void setCidadeOrigem(Cidade cidadeOrigem) {
		this.cidadeOrigem = cidadeOrigem;
	}

	public Cidade getCidadeDestino() {
		return cidadeDestino;
	}

	public void setCidadeDestino(Cidade cidadeDestino) {
		this.cidadeDestino = cidadeDestino;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public List<MDFeUFIntermediaria> getMdfeUfIntermediaria() {
		return mdfeUfIntermediaria;
	}

	public void setMdfeUfIntermediaria(List<MDFeUFIntermediaria> mdfeUfIntermediaria) {
		this.mdfeUfIntermediaria = mdfeUfIntermediaria;
	}

	@Override
	public String toString() {
		return "MDFe [id=" + id + ", numero=" + numero + ", serie=" + serie + ", chave=" + chave + ", dtEmissao="
				+ dtEmissao + ", dtInicioViagem=" + dtInicioViagem + ", dtFimViagem=" + dtFimViagem + ", apolice="
				+ apolice + ", averbacao=" + averbacao + ", dtCriacao=" + dtCriacao + ", dtAlteracao=" + dtAlteracao
				+ ", tipoAmbienteEmissao=" + tipoAmbienteEmissao + ", tipoEmissao=" + tipoEmissao + ", situacao="
				+ situacao + ", emitente=" + emitente + ", seguradora=" + seguradora + ", motorista=" + motorista
				+ ", cidadeOrigem=" + cidadeOrigem + ", cidadeDestino=" + cidadeDestino + ", veiculo=" + veiculo
				+ ", mdfeUfIntermediaria=" + mdfeUfIntermediaria + "]";
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
		MDFe other = (MDFe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
