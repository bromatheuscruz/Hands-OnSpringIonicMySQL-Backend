package br.com.matheuscruz.projetomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.matheuscruz.projetomc.domain.Cliente;
import br.com.matheuscruz.projetomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

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

	public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
		try {

			MimeMessage mimeMessage = prepareMimeMessageFromPedido(pedido);
			sendHtmlEmail(mimeMessage);

		} catch (MessagingException e) {
			sendOrderConfirmationEmail(pedido);
		}
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setTo(pedido.getCliente().getEmail());
		mimeMessageHelper.setFrom(sender);
		mimeMessageHelper.setSubject("Pedido confirmado! Código: " + pedido.getId());
		mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
		mimeMessageHelper.setText(htmlFromTemplatePedido(pedido), true);
		return mimeMessage;

	}

	protected String htmlFromTemplatePedido(Pedido pedido) {

		Context context = new Context();
		context.setVariable("pedido", pedido);
		return templateEngine.process("email/confirmacaoPedido", context);

	}

	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPassword) {
		SimpleMailMessage simpleMailMessage = prepareNewPasswordEmail(cliente, newPassword);
		sendEmail(simpleMailMessage);

	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPassword) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(cliente.getEmail());
		simpleMailMessage.setFrom(sender);
		simpleMailMessage.setSubject("Solicitação de nova senha");
		simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
		simpleMailMessage.setText("Nova senha: " + newPassword);
		return simpleMailMessage;

	}
}
