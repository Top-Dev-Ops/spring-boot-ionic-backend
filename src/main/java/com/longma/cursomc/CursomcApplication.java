package com.longma.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.longma.cursomc.domain.Address;
import com.longma.cursomc.domain.Category;
import com.longma.cursomc.domain.City;
import com.longma.cursomc.domain.Client;
import com.longma.cursomc.domain.Product;
import com.longma.cursomc.domain.State;
import com.longma.cursomc.domain.enums.ClientType;
import com.longma.cursomc.repositories.AddressRepository;
import com.longma.cursomc.repositories.CategoryRepository;
import com.longma.cursomc.repositories.CityRepository;
import com.longma.cursomc.repositories.ClientRepository;
import com.longma.cursomc.repositories.ProductRepository;
import com.longma.cursomc.repositories.StateRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(1, "Information");
		Category cat2 = new Category(2, "Electronics");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 700.00);
		Product p3 = new Product(null, "Camera", 300.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1));
		cat2.getProducts().addAll(Arrays.asList(p1, p2, p3));
		
		p1.getCategories().addAll(Arrays.asList(cat1, cat2));
		p1.getCategories().addAll(Arrays.asList(cat2));
		p1.getCategories().addAll(Arrays.asList(cat2));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		State st1 = new State(null, "California");
		State st2 = new State(null, "Michigan");
		
		City city1 = new City(null, "San Diego", st1);
		City city2 = new City(null, "Michigan", st2);
		City city3 = new City(null, "Los Angeles", st1);
		
		st1.getCities().addAll(Arrays.asList(city1, city3));
		st2.getCities().addAll(Arrays.asList(city2));
		
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));
		
		Client cli1 = new Client(null, "LongMa", "longma@email.com", "111", ClientType.PERSONLEGAL);
		cli1.getTelephones().addAll(Arrays.asList("1234", "2345"));
		
		Address add1 = new Address(null, "XinQu", "200", "Apart 906", "Unknown", "118000", cli1, city1);
		Address add2 = new Address(null, "XinQu", "300", "Apart 707", "JooSung", "118000", cli1, city2);
		
		cli1.getAddresses().addAll(Arrays.asList(add1, add2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(add1, add2));
	}

}
