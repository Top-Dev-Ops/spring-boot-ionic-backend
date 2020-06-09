package com.longma.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.longma.cursomc.domain.Address;
import com.longma.cursomc.domain.City;
import com.longma.cursomc.domain.Client;
import com.longma.cursomc.domain.enums.ClientType;
import com.longma.cursomc.dto.ClientDTO;
import com.longma.cursomc.dto.ClientNewDTO;
import com.longma.cursomc.repositories.AddressRepository;
import com.longma.cursomc.repositories.CityRepository;
import com.longma.cursomc.repositories.ClientRepository;
import com.longma.cursomc.services.exceptions.DataIntegrityException;
import com.longma.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	public Client find(Integer id) {
		Client obj = repo.findById(id).orElse(null);
		if(obj == null) {
			throw new ObjectNotFoundException("Object not found with Id " + id);
		}
		return obj;
	}
	
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addressRepo.saveAll(obj.getAddresses());
		return repo.save(obj);
	}
	
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Impossible to delete client due to relations with orders");
		}
	}
	
	public List<Client> findAll() {
		return repo.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDto) {
		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), ClientType.toEnum(objDto.getType()));
		City city = cityRepo.findById(objDto.getCityId()).orElse(null);
		Address add = new Address(null, objDto.getPlace(), objDto.getNumber(), objDto.getComplement(), objDto.getNeighborhood(), objDto.getZip(), cli, city);
		cli.getAddresses().add(add);
		cli.getTelephones().add(objDto.getTelephone1());
		if(objDto.getTelephone2() != null) {
			cli.getTelephones().add(objDto.getTelephone2());
		}
		if(objDto.getTelephone3() != null) {
			cli.getTelephones().add(objDto.getTelephone3());
		}
		return cli;
	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
