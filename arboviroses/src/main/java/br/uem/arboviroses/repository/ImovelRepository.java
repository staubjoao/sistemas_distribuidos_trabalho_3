package br.uem.arboviroses.repository;

import br.uem.arboviroses.model.Imovel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, String> {

    @Modifying
    @Transactional
    @Query(value =  "INSERT INTO imovel (imovel_id, localidade, numero, complemento, bairro, logradouro, tipo_imovel, municipio) " +
                    "VALUES (:imovel_id," +
                    ":localidade, " +
                    ":numero, " +
                    ":complemento, " +
                    ":bairro, " +
                    ":logradouro, " +
                    ":tipoImovel, " +
                    ":municipio)", nativeQuery = true)
    void saveImovel(@Param("imovel_id") String imovel_id,
                    @Param("localidade") String localidade,
                    @Param("numero") String numero,
                    @Param("complemento") String complemento,
                    @Param("bairro") String bairro,
                    @Param("logradouro") String logradouro,
                    @Param("tipoImovel") String tipoImovel,
                    @Param("municipio") String municipio);

    @Modifying
    @Transactional
    @Query(value =  "UPDATE imovel SET " +
                    "ponto_geografico = ST_GeomFromText(:pontoWkt, 4326) " +
                    "WHERE (imovel_id = :id);", nativeQuery = true)
    void updateImovelById(@Param("id") String id,
                           @Param("pontoWkt") String pontoWkt);

    @Query(value =  "SELECT imovel_id, " +
                    "localidade, " +
                    "numero, " +
                    "complemento, " +
                    "ST_AsText(ponto_geografico) AS pontoGeografico, " +
                    "bairro, " +
                    "logradouro, " +
                    "tipo_imovel, AS tipoImovel"  +
                    "municipio " +
                    "FROM imovel;", nativeQuery = true)
    List<Imovel> findAllQuarteiroes();
}
