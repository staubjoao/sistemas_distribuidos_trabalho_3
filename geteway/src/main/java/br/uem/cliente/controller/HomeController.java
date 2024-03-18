package br.uem.cliente.controller;

import br.uem.cliente.dto.ImovelEnvioDTO;
import br.uem.cliente.dto.ImovelRetornoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@RestController
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public List<ImovelRetornoDTO> imoveis() {
        int tentativasRestantes = 3;

        while (tentativasRestantes > 0) {
            try {
                ResponseEntity<ImovelRetornoDTO[]> responseEntity = restTemplate.exchange("http://arboviroses/api/imovel", HttpMethod.GET, null, ImovelRetornoDTO[].class);

                if (responseEntity.getStatusCode().is2xxSuccessful()) {
                    List<ImovelRetornoDTO> imoveis = Arrays.asList(responseEntity.getBody());
                    imoveis.forEach(System.out::println);
                    return imoveis;
                } else {
                    System.out.println("Erro ao obter lista de imóveis: " + responseEntity.getStatusCode());
                }
            } catch (Exception e) {
                System.out.println("Tentativa " + (4 - tentativasRestantes) + " falhou ao obter lista de imóveis: " + e.getMessage());
                tentativasRestantes--;
            }
        }

        System.out.println("Falha ao obter lista de imóveis após " + (4 - tentativasRestantes) + " tentativas");
        return null;
    }

    @PostMapping("/")
    public ImovelEnvioDTO imovel(@RequestBody ImovelEnvioDTO imovelDTO) {
        HttpEntity<ImovelEnvioDTO> requestEntity = new HttpEntity<>(imovelDTO);
        int tentativasRestantes = 3;

        while (tentativasRestantes > 0) {
            try {
                ResponseEntity<ImovelEnvioDTO> responseEntity = restTemplate.exchange("http://arboviroses/api/imovel", HttpMethod.POST, requestEntity, ImovelEnvioDTO.class);
                System.out.println("Imóvel criado com sucesso: " + responseEntity.getBody());
                return responseEntity.getBody();
            } catch (Exception e) {
                System.out.println("Tentativa " + (4 - tentativasRestantes) + " falhou ao criar imóvel: " + e.getMessage());
                tentativasRestantes--;
            }
        }

        System.out.println("Falha ao criar imóvel após " + (4 - tentativasRestantes) + " tentativas");
        return null;
    }
}
