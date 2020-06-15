package com.longma.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.longma.cursomc.domain.Category;
import com.longma.cursomc.domain.Product;
import com.longma.cursomc.repositories.CategoryRepository;
import com.longma.cursomc.repositories.ProductRepository;
import com.longma.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	public Product find(Integer id) {
		Product obj = repo.findById(id).orElse(null);
		if(obj == null) {
			throw new ObjectNotFoundException("Object not found with Id " + id);
		}
		return obj;
	}
	
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest request = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepo.findAllById(ids);
		return repo.search(name, categories, request);
	}
}
