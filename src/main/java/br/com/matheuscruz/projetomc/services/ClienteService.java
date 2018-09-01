package br.com.matheuscruz.projetomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.matheuscruz.projetomc.domain.Cliente;
import br.com.matheuscruz.projetomc.dto.ClienteDTO;
import br.com.matheuscruz.projetomc.repositories.ClienteRepository;
import br.com.matheuscruz.projetomc.services.exceptions.ConstraintViolationException;
import br.com.matheuscruz.projetomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("O id " + id + " não foi encontrado"));
	}

	public Cliente fromtDTO(ClienteDTO clienteDTO) {

		return new Cliente(clienteDTO.getId(), clienteDTO.getEmail(), clienteDTO.getNome(), null, null);

	}

	public void delete(Integer id) {
		find(id);
		try {
		clienteRepository.deleteById(id);
		} catch (ConstraintViolationException e) {
			throw new ConstraintViolationException("Não é possível excluir um cliente que contém pedidos");
		}
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return clienteRepository.findAll(pageRequest);

	}

	public Cliente update(Cliente cliente) {

		Cliente newCliente = find(cliente.getId());
		updateData(newCliente, cliente);
		return clienteRepository.save(newCliente);

	}

	private void updateData(Cliente newObj, Cliente obj) {

		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
