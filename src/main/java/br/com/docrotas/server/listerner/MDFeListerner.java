package br.com.docrotas.server.listerner;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.docrotas.server.entity.MDFe;
import br.com.docrotas.server.utils.CpfCnpjUtils;

public class MDFeListerner {

	@PrePersist
	public void prePrersistMDFeEvent(MDFe mdfe) {
		limparMascaraCnpj(mdfe);
		inseriDtCriacao(mdfe);
		atualizaDtAlteracao(mdfe);
	}
	
	private void atualizaDtAlteracao(MDFe mdfe) {
		mdfe.setDtAlteracao(new Date());
	}


	private void limparMascaraCnpj(MDFe mdfe) {
		mdfe.getEmitente().setCnpj(CpfCnpjUtils.limparMascara(mdfe.getEmitente().getCnpj()));
		mdfe.getMotorista().setCpfCnpj(CpfCnpjUtils.limparMascara(mdfe.getMotorista().getCpfCnpj()));
		mdfe.getSeguradora().setCpfCnpj(CpfCnpjUtils.limparMascara(mdfe.getSeguradora().getCpfCnpj()));
	}

	@PreUpdate
	public void preUpdateMDFeEvent(MDFe mdfe) {
		limparMascaraCnpj(mdfe);
		atualizaDtAlteracao(mdfe);
	}
	
	private void inseriDtCriacao(MDFe mdfe) {
		if(mdfe.getDtCriacao() == null) {
			mdfe.setDtCriacao(new Date());
		}
	}
	
	
}
