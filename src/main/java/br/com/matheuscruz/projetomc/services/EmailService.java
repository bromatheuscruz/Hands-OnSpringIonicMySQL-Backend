package br.com.matheuscruz.projetomc.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.matheuscruz.projetomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage message);
	
	
}
