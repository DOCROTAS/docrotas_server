package br.com.docrotas.server.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	private static final SimpleDateFormat ddmmyyyyhhmmss = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	public static String formatarddmmyyyyhhmmss(Date data) {
		String dataFormatada = null;

		if (data != null) {
			dataFormatada = ddmmyyyyhhmmss.format(data);
		}

		return dataFormatada;
	}

}
