package br.com.matheuscruz.projetomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.matheuscruz.projetomc.services.DBService;
import br.com.matheuscruz.projetomc.services.EmailService;
import br.com.matheuscruz.projetomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	DBService dbService;

	@Bean
	public boolean instantiateDatabase() throws ParseException {

		dbService.instantiateTestDatabase();
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
