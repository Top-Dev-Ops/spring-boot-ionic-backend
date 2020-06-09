package com.longma.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.longma.cursomc.domain.Client;
import com.longma.cursomc.domain.enums.ClientType;
import com.longma.cursomc.dto.ClientNewDTO;
import com.longma.cursomc.repositories.ClientRepository;
import com.longma.cursomc.resources.exception.FieldMessage;
import com.longma.cursomc.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

	
	@Autowired
	private ClientRepository repo;
	
	@Override
	public void initialize(ClientInsert ann) {
		
	}
	
	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		
		// include the tests here, inserting errors in the list
		if (objDto.getType().equals(ClientType.PERSONPHYSICS.getNumber()) && !BR.isValidCPF(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("CpfOrCnpj", "Invalid CPF"));
		}
		
		if (objDto.getType().equals(ClientType.PERSONLEGAL.getNumber()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("CpfOrCnpj", "Invalid CNPJ"));
		}
		
		Client aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("Email", "Email already exists"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty();
	}

}
