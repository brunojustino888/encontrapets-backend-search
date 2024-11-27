package br.com.encontrapets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.encontrapets.dto.CadastroRequestDto;
import br.com.encontrapets.service.CadastroService;
import jakarta.validation.Valid;

/**
 * Restcontroller utilizado para cadastro de novos usuarios.
 * 
 * @author Bruno Justino.
 */
@RestController
@RequestMapping("/usuarios")
public class CadastroController {
	
	/**
	 * Service utilizado para cadastro de usuarios.
	 */
    @Autowired
    private CadastroService usuarioService;
    
    /**
     * Endpoint do cadastro de usuarios.
     * 
     * @param objectloginDto - CadastroRequestDto - dto contendo os dados do cadastro.
     * @return ResponseEntity - 201 CREATED em caso de sucesso.
     */
    @PostMapping("/cadastro")
    public ResponseEntity<CadastroRequestDto> cadastraUser(@RequestBody @Valid CadastroRequestDto objectloginDto) {
        return this.usuarioService.save(objectloginDto);
    }
    
}

