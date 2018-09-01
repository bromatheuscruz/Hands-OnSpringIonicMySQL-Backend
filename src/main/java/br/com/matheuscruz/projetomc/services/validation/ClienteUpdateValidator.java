package br.com.matheuscruz.projetomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.matheuscruz.projetomc.domain.Cliente;
import br.com.matheuscruz.projetomc.dto.ClienteDTO;
import br.com.matheuscruz.projetomc.repositories.ClienteRepository;
import br.com.matheuscruz.projetomc.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	HttpServletRequest request;

	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		Integer uriId = Integer.parseInt(map.get("id"));

		Cliente cliente = clienteRepository.findByEmail(clienteDTO.getEmail());

		if (cliente != null && uriId != cliente.getId()) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
					.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();

	}

}
