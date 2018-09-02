package br.com.matheuscruz.projetomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.matheuscruz.projetomc.domain.Cliente;
import br.com.matheuscruz.projetomc.repositories.ClienteRepository;
import br.com.matheuscruz.projetomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder psEncoder;

	@Autowired
	EmailService emailService;

	private Random random = new Random();

	public void sendNewPassword(String email) {

		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}

		String newPassword = newPasswordword();
		cliente.setSenha(psEncoder.encode(newPassword));

		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPassword);

	}

	private String newPasswordword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {

		int opt = random.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (random.nextInt(10) + 48);
		} else if (opt == 1) { // geral letra maiuscula
			return (char) (random.nextInt(26) + 65);
		} else { // gera letra minuscula
			return (char) (random.nextInt(26) + 97);
		}

	}

}
