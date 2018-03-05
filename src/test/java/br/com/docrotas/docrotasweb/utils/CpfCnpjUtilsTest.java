package br.com.docrotas.docrotasweb.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.docrotas.server.utils.CpfCnpjUtils;

public class CpfCnpjUtilsTest {
	
	@Test
	public void deveTestarLimparMascaraCpf() {
		String cpf = "043.681.570-28";
		String retornoEsperado = "04368157028";		
		
		String cpfSemMascara = CpfCnpjUtils.limparMascara(cpf);
		
		assertEquals(cpfSemMascara, retornoEsperado);
	}
	
	@Test
	public void deveTestarLimparMascaraCpnj() {
		String cnpj = "58.584.453/0001-92";
		String retornoEsperado = "58584453000192";		
		
		String cnpjSemMascara = CpfCnpjUtils.limparMascara(cnpj);
		
		assertEquals(cnpjSemMascara, retornoEsperado);
	}

	@Test
	public void deveTestarLimparMascaraNull() {
		String cnpj = null;
		String retornoEsperado = null;		
		
		String cnpjSemMascara = CpfCnpjUtils.limparMascara(cnpj);
		
		assertEquals(cnpjSemMascara, retornoEsperado);
	}
}
