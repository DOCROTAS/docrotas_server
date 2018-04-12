package br.com.docrotas.server.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class MDFeUfId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private MDFe mdfe;
	
	@ManyToOne(cascade = CascadeType.ALL)	
	private Uf uf;
	
	public MDFe getMdfe() {
		return mdfe;
	}
	public void setMdfe(MDFe mdfe) {
		this.mdfe = mdfe;
	}
	
	public Uf getUf() {
		return uf;
	}
	public void setUf(Uf uf) {
		this.uf = uf;
	}
	
	
}
