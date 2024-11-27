package br.com.encontrapets.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Dto de cadastro de usuario.
 * 
 * @author Bruno Justino.
 */
@Data
public class CadastroRequestDto {

	/**
	 * Representa o nome do usuario.
	 */
	@NotBlank(message = "O nome é obrigatório.")
	@Size(min = 2, message = "Nome deve ter no mínimo 2 caracteres.")
	private String nome;

	/**
	 * Representa o telefone do usuario.
	 */
	@NotBlank(message = "O telefone é obrigatório.")
	@Size(min = 2, message = "Telefone deve ter no mínimo 2 caracteres.")
	private String telefone;

	/**
	 * Representa o email do usuario.
	 */
	@NotBlank(message = "O email é obrigatório.")
	@Email(message = "O email deve ser válido.")
	private String email;

	/**
	 * Flag utilizada para deixar o telefone visivel ou nao.
	 */
	private String flagPhoneVisivel;

	/**
	 * Logradouro do endereco de cadastro.
	 */
	@NotBlank(message = "O logradouro é obrigatório.")
	@Size(min = 2, message = "Logradouro deve ter no mínimo 2 caracteres.")
	private String logradouro;

	/**
	 * Cep do endereco de cadastro.
	 */
	@NotBlank(message = "O CEP é obrigatório.")
	@Size(min = 2, message = "CEP deve ter no mínimo 2 caracteres.")
	private String cep;

	/**
	 * Bairro do endereco de cadastro.
	 */
	@NotBlank(message = "O bairro é obrigatório.")
	@Size(min = 2, message = "Bairro deve ter no mínimo 2 caracteres.")
	private String bairro;

	/**
	 * Numero e complemento do endereco de cadastro.
	 */
	@NotBlank(message = "O número/complemento é obrigatório.")
	@Size(min = 2, message = "Numero e complemento devem ter no mínimo 2 caracteres.")
	private String numeroComplemento;

	/**
	 * Cidade do endereco de cadastro.
	 */
	@NotBlank(message = "A cidade é obrigatória.")
	@Size(min = 2, message = "Cidade deve ter no mínimo 2 caracteres.")
	private String cidade;

	/**
	 * Estado do endereco de cadastro.
	 */
	@NotBlank(message = "O estado é obrigatório.")
	@Size(min = 2, message = "Estado deve ter no mínimo 2 caracteres.")
	private String estado;

	/**
	 * Senha de cadastro.
	 */
	@NotBlank(message = "A senha é obrigatória.")
	@Size(min = 7, message = "A senha deve ter no mínimo 7 caracteres.")
	private String senha;
	
}