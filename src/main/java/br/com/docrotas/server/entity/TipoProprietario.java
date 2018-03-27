package br.com.docrotas.server.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoProprietario {
	TAC_AGREGADO("0"),
	TAC_INDEPENDENTE("1"),
	OUTROS("2");
	
	private String codigo;
	
	@JsonValue
	public int toValue() {
		return ordinal();
	}

	private TipoProprietario(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

}
