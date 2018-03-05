package br.com.docrotas.server.utils;
 

public class StringUtils {
 
  
 
  public static String removerCaracteresEspeciasAlfaNumericos(String texto) {
 
    if (org.apache.commons.lang3.StringUtils.isNotBlank(texto)) {
 
      StringBuilder textoLimpo = new StringBuilder();
 

 
      for (char caracter : texto.toCharArray()) {
 
        if (caracter == '0' || caracter == '1'|| caracter == '2'|| caracter == '3'|| caracter == '4'
 
            || caracter == '5'|| caracter == '6'|| caracter == '7'|| caracter == '8'|| caracter == '9') {
 
          textoLimpo.append(caracter);
 
        }
 
      }
 

 
      texto = textoLimpo.toString();
 
    }
 
    
 
    return texto;
 
  }
 

 
}
 