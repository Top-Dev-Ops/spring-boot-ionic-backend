package com.longma.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.longma.cursomc.domain.Category;
import com.longma.cursomc.repositories.CategoryRepository;
import com.longma.cursomc.services.exceptions.DataIntegrityException;
import com.longma.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	public Category find(Integer id) {
		Category obj = repo.findById(id).orElse(null);
		if(obj == null) {
			throw new ObjectNotFoundException("Object not found with Id " + id);
		}
		return obj;
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Category update(Category obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Impossible to delete category with products");
		}
	}
}
