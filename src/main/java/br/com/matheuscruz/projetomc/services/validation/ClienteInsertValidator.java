package br.com.matheuscruz.projetomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.matheuscruz.projetomc.domain.enums.TipoCliente;
import br.com.matheuscruz.projetomc.dto.ClienteNewDTO;
import br.com.matheuscruz.projetomc.repositories.ClienteRepository;
import br.com.matheuscruz.projetomc.resources.exception.FieldMessage;
import br.com.matheuscruz.projetomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (clienteRepository.findByEmail(objDto.getEmail()) != null) {
			list.add(new FieldMessage("email", "Email já existente na base de dados"));
		}

		if (objDto.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getCodigo())
				&& !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}

		if (objDto.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCodigo())
				&& !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}