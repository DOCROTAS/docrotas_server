package br.com.docrotas.server.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.docrotas.server.listerner.CteListerner;

@Entity
@Table(name="cte")
@EntityListeners(value=CteListerner.class)
public class Cte implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero")
	private Long numero;
	
	@Column(name="serie")
	private Long serie;
	
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name = "remetente_id")
	private Pessoa remetente;

	@ManyToOne
	@JoinColumn(name = "destinatario_id")
	private Pessoa destinatario;
	
	@ManyToOne
	@JoinColumn(name = "tomador_id")
	private Pessoa tomador;
	
	@Column(name = "vlr_frete")
	private Double vlrFrete;
	
	@Column(name = "vlr_mercadoria")
	private Double vlrMercadoria;
	
	@ManyToOne
	@JoinColumn(name = "cfop_id")
	private Cfop cfop;
	
	@Column(name = "base_calculo")
	private Double baseCalculo;
	
	@Column(name = "aliquota")
	private Double aliquota;
	
	@Column(name = "vlr_icms")
	private Double vlrIcms;
	
	@Column(name = "produto", length=70)
	private String produto;
	
	@Column(name = "chave_acesso", length=44)
	private String chaveAcesso;
	
	@Column(name = "protocolo_lote", length=30)
	private String protocoloLote;
	
	@Column(name = "protocolo_aut", length=30)
	private String protocoloAutorizacao;
	
	@Column(name = "dt_criacao")
	private Date dtCriacao;
	
	@Column(name = "dt_alteracao")
	private Date dtAlteracao;
	
	@Column(name = "dt_prot_lote")
	private Date dtProtocoloLote;
	
	@Column(name = "dt_prot_aut")
	private Date dtProcolocoloAutorizacao;
	
	@Column(name = "situacao")
	@Enumerated(EnumType.ORDINAL)
	private SituacaoDocumento situacao;

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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Pessoa getRemetente() {
		return remetente;
	}

	public void setRemetente(Pessoa remetente) {
		this.remetente = remetente;
	}

	public Pessoa getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Pessoa destinatario) {
		this.destinatario = destinatario;
	}

	public Pessoa getTomador() {
		return tomador;
	}

	public void setTomador(Pessoa tomador) {
		this.tomador = tomador;
	}

	public Double getVlrFrete() {
		return vlrFrete;
	}

	public void setVlrFrete(Double vlrFrete) {
		this.vlrFrete = vlrFrete;
	}

	public Double getVlrMercadoria() {
		return vlrMercadoria;
	}

	public void setVlrMercadoria(Double vlrMercadoria) {
		this.vlrMercadoria = vlrMercadoria;
	}

	public Cfop getCfop() {
		return cfop;
	}

	public void setCfop(Cfop cfop) {
		this.cfop = cfop;
	}

	public Double getBaseCalculo() {
		return baseCalculo;
	}

	public void setBaseCalculo(Double baseCalculo) {
		this.baseCalculo = baseCalculo;
	}

	public Double getAliquota() {
		return aliquota;
	}

	public void setAliquota(Double aliquota) {
		this.aliquota = aliquota;
	}

	public Double getVlrIcms() {
		return vlrIcms;
	}

	public void setVlrIcms(Double vlrIcms) {
		this.vlrIcms = vlrIcms;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

	public String getProtocoloLote() {
		return protocoloLote;
	}

	public void setProtocoloLote(String protocoloLote) {
		this.protocoloLote = protocoloLote;
	}

	public String getProtocoloAutorizacao() {
		return protocoloAutorizacao;
	}

	public void setProtocoloAutorizacao(String protocoloAutorizacao) {
		this.protocoloAutorizacao = protocoloAutorizacao;
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

	public Date getDtProtocoloLote() {
		return dtProtocoloLote;
	}

	public void setDtProtocoloLote(Date dtProtocoloLote) {
		this.dtProtocoloLote = dtProtocoloLote;
	}

	public Date getDtProcolocoloAutorizacao() {
		return dtProcolocoloAutorizacao;
	}

	public void setDtProcolocoloAutorizacao(Date dtProcolocoloAutorizacao) {
		this.dtProcolocoloAutorizacao = dtProcolocoloAutorizacao;
	}

	public SituacaoDocumento getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoDocumento situacao) {
		this.situacao = situacao;
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
		Cte other = (Cte) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
