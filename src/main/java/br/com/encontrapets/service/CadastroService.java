package br.com.encontrapets.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.encontrapets.dto.CadastroRequestDto;
import br.com.encontrapets.model.Endereco;
import br.com.encontrapets.model.Pessoa;
import br.com.encontrapets.model.Usuario;
import br.com.encontrapets.repository.CadastroRepository;
import br.com.encontrapets.repository.EnderecoRepository;
import br.com.encontrapets.repository.UsuarioRepository;

@Service
public class CadastroService {
	
    @Autowired
    private CadastroRepository cadastroRepository;
    
    @Autowired
    private UsuarioRepository userRepository;
    
    @Autowired
    private EnderecoRepository adressRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
 
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
		
		Endereco eEndereco = new Endereco();
		BeanUtils.copyProperties(objectloginDto, eEndereco);
		eEndereco.setUserCadastro("SYSTEM");
		eEndereco.setUserAtualizacao("SYSTEM");
		eEndereco.setDataAtualizacao(new Date());
		eEndereco.setDataCadastro(new Date());
		eEndereco = this.adressRepository.save(eEndereco);
		
		Usuario uUsuario = new Usuario();
		BeanUtils.copyProperties(objectloginDto, uUsuario);
		uUsuario.setIdPerfil(3);
		uUsuario.setLogin(objectloginDto.getEmail());
		uUsuario.setUserCadastro("SYSTEM");
		uUsuario.setUserAtualizacao("SYSTEM");
		uUsuario.setDataAtualizacao(new Date());
		uUsuario.setDataCadastro(new Date());
		uUsuario = this.userRepository.save(uUsuario);
		
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