package br.com.docrotas.server.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class CertificadoDigitalUtils {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public static boolean senhaValida (byte[] arquivo, String senha) throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		boolean senhaValida = true;
		
		try {
			carregarKeyStore(arquivo, senha);
			
		} catch (IOException e) {
				if (e.getCause().getClass().equals(UnrecoverableKeyException.class)) {
					senhaValida = false;
				} else {
					throw e;
				}
		}

		return senhaValida;
	}
	
	public static KeyStore carregarKeyStore(byte [] arquivo, String senha) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(new ByteArrayInputStream(arquivo), senha.toCharArray());
		
		return keyStore;
	}
	
	public static Date getDataVencimento(KeyStore keyStore) throws KeyStoreException {
		Date vencimento = null;
		
		Enumeration<String> eAliases = keyStore.aliases();
		
		while (eAliases.hasMoreElements()) {
			String alias = (String) eAliases.nextElement();
			X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);

			vencimento = cert.getNotAfter();
		}
		
		return vencimento;
	}

	public static Date getDataVencimento(byte[] arquivo, String senha) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		return getDataVencimento(carregarKeyStore(arquivo, senha));
	}

	public static String getObservacao(KeyStore keyStore) throws KeyStoreException {
		String observacao = null;
		
		Enumeration<String> eAliases = keyStore.aliases();
		
		while (eAliases.hasMoreElements()) {
			String alias = (String) eAliases.nextElement();
			X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);
			
			observacao = cert.getSubjectDN().getName();
		}
		
		return observacao;
	}
	
	public static String getObservacao(byte[] arquivo, String senha) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		return getObservacao(carregarKeyStore(arquivo, senha));
	}
	
}
