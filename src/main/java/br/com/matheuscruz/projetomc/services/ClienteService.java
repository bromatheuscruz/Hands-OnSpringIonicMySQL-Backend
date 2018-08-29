package br.com.matheuscruz.projetomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheuscruz.projetomc.domain.Cliente;
import br.com.matheuscruz.projetomc.repositories.ClienteRepository;
import br.com.matheuscruz.projetomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("O id " + id + " não foi encontrado."));
	}

}
