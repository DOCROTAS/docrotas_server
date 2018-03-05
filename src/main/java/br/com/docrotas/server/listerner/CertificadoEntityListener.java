package br.com.docrotas.server.listerner;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.docrotas.server.entity.CertificadoEntity;

public class CertificadoEntityListener {

	@PrePersist
	public void prePersistCertificadoEntityEvent (CertificadoEntity certificadoEntity) {
		inseriDtCriacao(certificadoEntity);
	}
	
	@PreUpdate
	public void preUpdateCertificadoEntityEvent (CertificadoEntity certificadoEntity) {
		
	}
	
	private void inseriDtCriacao (CertificadoEntity certificadoEntity) {
		if (certificadoEntity.getDtInclusao() == null) {
			certificadoEntity.setDtInclusao(new Date());
		}
	}
}
