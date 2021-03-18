package br.carlosmelo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


import br.carlosmelo.api.config.property.CarlosMeloApiProperties;

@SpringBootApplication
@EnableConfigurationProperties(CarlosMeloApiProperties.class)
public class CarlosMeloApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarlosMeloApiApplication.class, args);
	}
}
