package br.com.encontrapets.dto;

import lombok.Data;

@Data
public class CadastroRequestDto { 

    private String nome;

    private String telefone;

    private String email;

    private String flagPhoneVisivel;

    private Long idEndereco;

    private String logradouro;

    private String cep;

    private String bairro;
    
    private String numeroComplemento;
    
    private String cidade;
    
    private String estado;
    
    private String senha; 
    
}