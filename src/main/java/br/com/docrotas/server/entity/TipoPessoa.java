package br.com.docrotas.server.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoPessoa {
	CLIENTE("0"),
	MOTORISTA("1"),
	FORNECEDOR("2"),
	SEGURADORA("3");
	
	private String codigo;
	
	@JsonValue
	public int toValue() {
		return ordinal();
	}
	
	private TipoPessoa(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
}
