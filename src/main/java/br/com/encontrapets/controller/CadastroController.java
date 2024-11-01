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

@RestController
@RequestMapping("/usuarios")
public class CadastroController {
	
    @Autowired
    private CadastroService usuarioService;
    
    @PostMapping("/cadastro")
    public ResponseEntity<CadastroRequestDto> cadastraUser(@RequestBody @Valid CadastroRequestDto objectloginDto) {
        return this.usuarioService.save(objectloginDto);
    }
    
}

