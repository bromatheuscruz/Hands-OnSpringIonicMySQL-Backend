package br.com.matheuscruz.projetomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender javaMailSender;

	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage message) {
		LOG.info("Simulando envio de email...");
		mailSender.send(message);
		LOG.info("Email enviado!");
	}

	@Override
	public void sendHtmlEmail(MimeMessage mimeMessage) {
		
		LOG.info("Simulando envio de email de HTML...");
		javaMailSender.send(mimeMessage);
		LOG.info("Email enviado!");

	}

}
