package br.com.matheuscruz.projetomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheuscruz.projetomc.domain.Pedido;
import br.com.matheuscruz.projetomc.repositories.PedidoRepository;
import br.com.matheuscruz.projetomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("O id: " + id + " não foi encontrado."));

	}
}
