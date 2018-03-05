package br.com.docrotas.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EntityScan(basePackages = "br.com.docrotas.server.entity")
@EnableJpaRepositories(basePackages = "br.com.docrotas.server.repository")
public class DocrotaswebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocrotaswebApplication.class, args);
	}
	
//	@Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/usuario").allowedOrigins("*");
//                registry.addMapping("/usuario/{id}").allowedOrigins("*");
//            }
//        };
//    }
}
