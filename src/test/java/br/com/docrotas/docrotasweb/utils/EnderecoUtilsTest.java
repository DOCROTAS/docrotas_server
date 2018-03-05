package br.com.docrotas.docrotasweb.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.docrotas.server.utils.EnderecoUtils;

public class EnderecoUtilsTest {
	
	@Test
	public void deveTestarLimparMascaraCpf() {
		String cep = "38.410-668";
		String retornoEsperado = "38410668";		
		
		String cepSemMascara = EnderecoUtils.limparMascaraCep(cep);
		
		assertEquals(cepSemMascara, retornoEsperado);
	}
	

}
