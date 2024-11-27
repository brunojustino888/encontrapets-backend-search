package br.com.encontrapets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.encontrapets.model.Pessoa;

/**
 * JpaRepository de cadastro de pessoa.
 * 
 * @author Bruno Justino.
 */
public interface CadastroRepository extends JpaRepository<Pessoa, String> {
    
	/**
	 * Encontra o usuario por email.
	 * 
	 * @param email - String - email da pessoa.
	 * @return Optional - container de pessoa.
	 */
	Optional<Pessoa> findByEmail(String email);

}