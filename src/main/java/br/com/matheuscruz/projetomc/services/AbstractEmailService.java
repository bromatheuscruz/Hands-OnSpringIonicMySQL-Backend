package br.com.matheuscruz.projetomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.matheuscruz.projetomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("{$default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {

		SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromPedido(pedido);
		sendEmail(simpleMailMessage);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(pedido.getCliente().getEmail());
		simpleMailMessage.setFrom(sender);
		simpleMailMessage.setSubject("Pedido confirmado! Número do pedido: " + pedido.getId());
		simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
		simpleMailMessage.setText(pedido.toString());
		return simpleMailMessage;
	}

}
