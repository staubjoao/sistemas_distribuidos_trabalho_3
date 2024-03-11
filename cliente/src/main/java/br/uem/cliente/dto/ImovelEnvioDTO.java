package br.uem.cliente.dto;

import lombok.Data;

@Data
public class ImovelEnvioDTO {

    private String localidade;
    private String numero;
    private String complemento;
    private String bairro;
    private String logradouro;
    private String tipoImovel;
    private String municipio;

}
