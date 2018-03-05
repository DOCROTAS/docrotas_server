package br.com.docrotas.server.listerner;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang3.StringUtils;

import br.com.docrotas.server.entity.Empresa;
import br.com.docrotas.server.utils.CpfCnpjUtils;
import br.com.docrotas.server.utils.EnderecoUtils;

public class EmpresaListerner {

	@PrePersist
	public void prePersistEmpresaEvent (Empresa empresa) {
		limparMascaraCnpj(empresa);
		limparMascaraCep(empresa);
		inseriDtCriacao(empresa);
		atualizaDtAlteracao(empresa);
	}
	
	@PreUpdate
	public void preUpdateEmpresaEvent (Empresa empresa) {
		limparMascaraCnpj(empresa);
		limparMascaraCep(empresa);
		atualizaDtAlteracao(empresa);
	}
	
	private void atualizaDtAlteracao (Empresa empresa) {
		empresa.setDtAlteracao(new Date());
	}
	
	private void inseriDtCriacao (Empresa empresa) {
		if (empresa.getDtCriacao() == null) {
			empresa.setDtCriacao(new Date());
		}
	}
	
	private void limparMascaraCnpj (Empresa empresa) {
		empresa.setCnpj(CpfCnpjUtils.limparMascara(empresa.getCnpj()));
	}
	
	private void limparMascaraCep (Empresa empresa) {
		if (empresa.getEndereco() != null) {
			empresa.getEndereco().setCep(EnderecoUtils.limparMascaraCep(empresa.getEndereco().getCep()));
		}
	}
}
