package br.com.docrotas.server.utils;

public class CpfCnpjUtils {
	
	public static String limparMascara(String cpfCnpj) {
		return StringUtils.removerCaracteresEspeciasAlfaNumericos(cpfCnpj);
	}

	
}
