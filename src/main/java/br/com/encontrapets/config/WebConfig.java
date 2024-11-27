package br.com.encontrapets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuracoes de CORS da aplicacao.
 * 
 * @author Bruno Justino.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
	/**
	 * Configurando o CORS para a aplicacao.
	 * Permite CORS para todas as rotas.
	 * 
	 * @return WebMvcConfigurer - objeto de configuracao CORS.
	 */
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  
                        .allowedOrigins("http://localhost:5173", "https://encontrapets.com.br")  
                        .allowedMethods("GET", "POST")  
                        .allowedHeaders("*") 
                        .allowCredentials(true); 
            }
        };
    }
	
}
