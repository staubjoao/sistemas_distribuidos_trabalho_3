package br.uem.arboviroses.controller;

import br.uem.arboviroses.model.*;
import br.uem.arboviroses.rabbit.ImovelCreateEvent;
import br.uem.arboviroses.service.impl.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/imovel")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ImovelController {

    @Autowired
    private ImovelServiceImpl service;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Imovel post(@RequestBody Imovel imovelDTO) {
        String routingKey = "imoveis.v1.imovel-created";

        Imovel imovel = new Imovel();
        imovel.setId(String.valueOf(UUID.randomUUID()));
        imovel.setNumero(imovelDTO.getNumero());
        imovel.setComplemento(imovelDTO.getComplemento());
        imovel.setBairro(imovelDTO.getBairro());
        imovel.setLogradouro(imovelDTO.getLogradouro());
        imovel.setTipoImovel(imovelDTO.getTipoImovel());
        imovel.setMunicipio(imovelDTO.getMunicipio());
        imovel.setLocalidade(imovelDTO.getLocalidade());

        imovel = service.salvar(imovel);

        if (imovel == null) {
            return null;
        }

        String query = getString(imovel);

        ImovelCreateEvent event = new ImovelCreateEvent(imovel.getId(), query);

        rabbitTemplate.convertAndSend(routingKey, event);

        return imovel;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    private List<Imovel> getAll() {
        return service.getlAll();
    }

    @NotNull
    private static String getString(Imovel imovel) {
        String logradouro = imovel.getLogradouro();
        String numero = imovel.getNumero();
        String cidade = imovel.getMunicipio();

        return logradouro.replace(" ", "+") +
                "+" +
                numero +
                "+" +
                cidade.replace(" ", "+") +
                "+" +
                "paraná";
    }

}
