package br.com.docrotas.server.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoRodado {
	TRUCK("01"),
	TOCO("02"),
	CAVALO_MECANICO("03"),
	VAN("04"),
	UTILITARIO("05"),
	OUTROS("06");
	
	private String codigo;
	
	@JsonValue
	public int toValue() {
		return ordinal();
	}

	private TipoRodado(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
	
}
