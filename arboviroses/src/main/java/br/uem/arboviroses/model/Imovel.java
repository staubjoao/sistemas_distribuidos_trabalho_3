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

}
