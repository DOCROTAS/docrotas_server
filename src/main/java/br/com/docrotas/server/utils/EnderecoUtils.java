package br.com.docrotas.server.utils;

public class EnderecoUtils {
	
	public static String limparMascaraCep (String cep) {
		return StringUtils.removerCaracteresEspeciasAlfaNumericos(cep);
	}

}
