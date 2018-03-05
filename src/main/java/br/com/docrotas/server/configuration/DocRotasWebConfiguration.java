package br.com.docrotas.server.configuration;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;


@Configuration
@ComponentScan(basePackages = {"br.com.docrotas.server.service"})
public class DocRotasWebConfiguration {
	
	@Bean
	public MultipartResolver multipartResolver() throws IOException {        
        return new StandardServletMultipartResolver();
    }

}
