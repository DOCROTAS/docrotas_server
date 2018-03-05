package br.com.docrotas.docrotasweb.utils;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import org.junit.Test;

import br.com.docrotas.server.utils.DateUtils;

public class DateUtilsTest {
	
	@Test
	public void testarFormatarDataddMMyyyyhhmmss() {
		Date data = new Date(1524576106000L);

		String dataFormatada = DateUtils.formatarddmmyyyyhhmmss(data);

		assertEquals(dataFormatada, "24/04/2018 10:21:46");
	}

	@Test
	public void testarFormatarDataNula() {
		assertEquals(DateUtils.formatarddmmyyyyhhmmss(null), null);
	}
}
