package br.com.matheuscruz.projetomc.services;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.matheuscruz.projetomc.domain.Cidade;
import br.com.matheuscruz.projetomc.domain.Cliente;
import br.com.matheuscruz.projetomc.domain.Endereco;
import br.com.matheuscruz.projetomc.domain.enums.Perfil;
import br.com.matheuscruz.projetomc.domain.enums.TipoCliente;
import br.com.matheuscruz.projetomc.dto.ClienteDTO;
import br.com.matheuscruz.projetomc.dto.ClienteNewDTO;
import br.com.matheuscruz.projetomc.repositories.ClienteRepository;
import br.com.matheuscruz.projetomc.repositories.EnderecoRepository;
import br.com.matheuscruz.projetomc.security.UserSS;
import br.com.matheuscruz.projetomc.services.exceptions.AuthorizationException;
import br.com.matheuscruz.projetomc.services.exceptions.ConstraintViolationException;
import br.com.matheuscruz.projetomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder psEncoder;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}

		Optional<Cliente> obj = clienteRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("O id " + id + " não foi encontrado"));
	}

	public Cliente fromtDTO(ClienteDTO clienteDTO) {

		return new Cliente(clienteDTO.getId(), clienteDTO.getEmail(), clienteDTO.getNome(), null, null, null);

	}

	public Cliente fromDTO(ClienteNewDTO clienteNewDTO) {
		Cliente cliente = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(),
				clienteNewDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteNewDTO.getTipoCliente()),
				psEncoder.encode(clienteNewDTO.getSenha()));

		Cidade cidade = new Cidade(clienteNewDTO.getCidadeId(), null, null);

		Endereco endereco = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(),
				clienteNewDTO.getComplemento(), clienteNewDTO.getBairro(), clienteNewDTO.getCep(), cliente, cidade);

		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().addAll(Arrays.asList(clienteNewDTO.getTelefone1(), clienteNewDTO.getTelefone2(),
				clienteNewDTO.getTelefone3()));

		return cliente;

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

	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

}
