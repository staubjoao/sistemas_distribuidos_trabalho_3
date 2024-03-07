package br.uem.geolocalizacao;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class GeolocalizacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeolocalizacaoApplication.class, args);
	}

}
