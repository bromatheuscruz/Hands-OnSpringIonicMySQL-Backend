package br.com.matheuscruz.projetomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.matheuscruz.projetomc.services.DBService;

@Configuration
@Profile("text")
public class TestConfig {

	@Autowired
	DBService dbService;

	@Bean
	public boolean instantiateDatabase() throws ParseException {

		dbService.instantiateTestDatabase();
		return true;
	}

}