package br.com.encontrapets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Aplicacao backend springboot sem autenticacao para buscas e cadastros.
 * 
 * @author Bruno Justino.
 */
@SpringBootApplication
public class EncontrapetsBackendSearchApplication {

	/**
	 * Metodo main da aplicacao.
	 * 
	 * @param args - String[] - parametros da jvm.
	 */
	public static void main(String[] args) {
		SpringApplication.run(EncontrapetsBackendSearchApplication.class, args);
	}

}
