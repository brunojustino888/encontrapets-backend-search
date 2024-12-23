package br.com.encontrapets.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.encontrapets.dto.CadastroRequestDto;
import br.com.encontrapets.model.Endereco;
import br.com.encontrapets.model.Pessoa;
import br.com.encontrapets.model.Usuario;
import br.com.encontrapets.repository.CadastroRepository;
import br.com.encontrapets.repository.EnderecoRepository;
import br.com.encontrapets.repository.UsuarioRepository;

/**
 * Service utilizado para cadastro de pessoas.
 * 
 * @author Bruno Justino.
 */
@Service
public class CadastroService {
	
	/**
	 * Jparepository de pessoa.
	 */
    @Autowired
    private CadastroRepository cadastroRepository;
    
    /**
	 * Jparepository de usuario.
	 */
    @Autowired
    private UsuarioRepository userRepository;
    
    /**
	 * Jparepository de endereco.
	 */
    @Autowired
    private EnderecoRepository adressRepository;
    
    /**
	 * Encoder par criptografia de senha.
	 */
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    /**
     * Cria o registro do usuario em 3 tabelas (T_USUARIO, T_ENDERECO e T_PESSOA).
     * 
     * @param objectloginDto - CadastroRequestDto - contem os dados para cadastro.
     * @return ResponseEntity - 201 CREATED em caso de sucesso.
     */
    @Transactional
	public ResponseEntity<CadastroRequestDto> save(CadastroRequestDto objectloginDto) {
		Optional<Pessoa> pPessoaOpt = this.cadastroRepository.findByEmail(objectloginDto.getEmail());
		if (pPessoaOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(objectloginDto);
		}
		objectloginDto.setSenha(this.passwordEncoder.encode(objectloginDto.getSenha()));
		objectloginDto.setBairro(objectloginDto.getBairro().toUpperCase()); 
		objectloginDto.setCidade(objectloginDto.getCidade().toUpperCase()); 
		objectloginDto.setEstado(objectloginDto.getEstado().toUpperCase()); 
		objectloginDto.setLogradouro(objectloginDto.getLogradouro().toUpperCase()); 
		objectloginDto.setNome(objectloginDto.getNome().toUpperCase()); 
		objectloginDto.setNumeroComplemento(objectloginDto.getNumeroComplemento().toUpperCase()); 
		objectloginDto.setFlagPhoneVisivel("S");
		Usuario uUsuario = new Usuario();
		BeanUtils.copyProperties(objectloginDto, uUsuario);
		uUsuario.setIdPerfil(2);
		uUsuario.setFlagBloqueio("N");
		uUsuario.setFlagExclusao("N");
		uUsuario.setLogin(objectloginDto.getEmail());
		uUsuario.setUserCadastro("SYSTEM");
		uUsuario.setUserAtualizacao("SYSTEM");
		uUsuario.setDataAtualizacao(new Date());
		uUsuario.setDataCadastro(new Date());
		uUsuario = this.userRepository.save(uUsuario);
		Endereco eEndereco = new Endereco();
		BeanUtils.copyProperties(objectloginDto, eEndereco);
		eEndereco.setUserCadastro("SYSTEM");
		eEndereco.setUserAtualizacao("SYSTEM");
		eEndereco.setDataAtualizacao(new Date());
		eEndereco.setDataCadastro(new Date());
		eEndereco.setIdEnderecoUsuario(uUsuario.getIdUsuario());
		eEndereco = this.adressRepository.save(eEndereco);
		Pessoa pessoaTarget = new Pessoa();
		BeanUtils.copyProperties(objectloginDto, pessoaTarget);
		pessoaTarget.setIdEndereco(eEndereco.getIdEndereco());
		pessoaTarget.setIdUsuario(uUsuario.getIdUsuario());
		pessoaTarget.setUserCadastro("SYSTEM");
		pessoaTarget.setUserAtualizacao("SYSTEM");
		pessoaTarget.setDataAtualizacao(new Date());
		pessoaTarget.setDataCadastro(new Date());
		this.cadastroRepository.save(pessoaTarget);
		return ResponseEntity.status(HttpStatus.CREATED).body(objectloginDto);
	}
	
}
