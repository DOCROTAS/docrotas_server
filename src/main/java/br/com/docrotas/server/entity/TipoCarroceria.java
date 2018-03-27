package br.com.docrotas.server.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoCarroceria {
	NAO_APLICAVEL("00"),
	ABERTA("01"),
	FECHADA_BAU("02"),
	GRANELERA("03"),
	PORTA_CONTAINER("04"),
	SIDER("05");
	
	private String codigo;
	
	@JsonValue
	public int toValue() {
		return ordinal();
	}

	private TipoCarroceria(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
	
}
