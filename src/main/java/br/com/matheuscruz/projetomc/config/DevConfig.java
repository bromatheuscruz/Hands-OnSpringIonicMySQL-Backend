package br.com.matheuscruz.projetomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.matheuscruz.projetomc.services.DBService;
import br.com.matheuscruz.projetomc.services.EmailService;
import br.com.matheuscruz.projetomc.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {

		if ("create".equals(strategy)) {
			dbService.instantiateTestDatabase();
			return true;
		}

		return false;
	}

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}
