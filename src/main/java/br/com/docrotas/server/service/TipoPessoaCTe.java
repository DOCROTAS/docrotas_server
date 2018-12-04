package br.com.docrotas.server.service;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoPessoaCTe {
	
	REMETENTE("rem"),
	DESTINATARIO("dest");
	
	private String tag;
	
	@JsonValue
	public int toValue() {
		return ordinal();
	}

	private TipoPessoaCTe(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}
	

}
