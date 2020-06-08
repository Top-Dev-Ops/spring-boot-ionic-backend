package com.longma.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longma.cursomc.domain.Client;
import com.longma.cursomc.repositories.ClientRepository;
import com.longma.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	
	public Client find(Integer id) {
		Client obj = repo.findById(id).orElse(null);
		if(obj == null) {
			throw new ObjectNotFoundException("Object not found with Id " + id);
		}
		return obj;
	}
}
