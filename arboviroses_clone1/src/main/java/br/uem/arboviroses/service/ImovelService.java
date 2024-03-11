package br.uem.arboviroses.service;

import br.uem.arboviroses.model.Imovel;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface ImovelService {

    Imovel salvar(@Valid @RequestBody Imovel imovel);

    List<Imovel> getlAll();

}
