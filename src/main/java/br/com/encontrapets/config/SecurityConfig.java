package br.com.encontrapets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Securityconfig da aplicacao.
 * 
 * @author Bruno Justino.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	/**
	 * Configuracao do Encoder utilizado para criptografia.
	 * 
	 * @return PasswordEncoder - encoder de criptografia.
	 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuracao da cadeia de filtros de seguranca da aplicacao.
     * Habilita as configuracoes de CORS.
     * Desabilita CSRF.
     * Utiliza httpbasic.
     * Permite requisicoes para todos endpoins sem autenticacao.
     * 
     * @param http - HttpSecurity - objeto http do spring.
     * @return SecurityFilterChain - cadeia de filtros de seguranca.
     * @throws Exception - em caso de falha.
     */
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors()  
        .and()
        .csrf().disable()
            .authorizeRequests() 
            .anyRequest().anonymous()
            .and()
            .httpBasic();
        return http.build();
    }
       
    
}