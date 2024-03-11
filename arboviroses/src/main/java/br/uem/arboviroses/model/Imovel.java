package br.uem.arboviroses.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "imovel")
public class Imovel {

    @Id
    @GeneratedValue
    @Column(name = "imovel_id")
    private String id;

    @Column
    private String localidade;

    @Column
    private String numero;

    @Column
    private String complemento;

    @Column(columnDefinition = "GEOMETRY(POINT, 4326)")
    private String pontoGeografico;

    @Column
    private String bairro;

    @Column
    private String logradouro;

    @Column
    private String tipoImovel;

    @Column
    private String municipio;

    public Imovel() {

    }
    public Imovel(String id, String localidade, String numero, String complemento, String pontoGeografico, String bairro, String logradouro, String tipoImovel, String municipio) {
        this.id = id;
        this.localidade = localidade;
        this.numero = numero;
        this.complemento = complemento;
        this.pontoGeografico = pontoGeografico;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.tipoImovel = tipoImovel;
        this.municipio = municipio;
    }

}
