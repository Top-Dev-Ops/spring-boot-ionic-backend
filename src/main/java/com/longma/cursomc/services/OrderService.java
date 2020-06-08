package com.longma.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longma.cursomc.domain.Order;
import com.longma.cursomc.repositories.OrderRepository;
import com.longma.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	
	public Order find(Integer id) {
		Order obj = repo.findById(id).orElse(null);
		if(obj == null) {
			throw new ObjectNotFoundException("Object not found with Id " + id);
		}
		return obj;
	}
}
