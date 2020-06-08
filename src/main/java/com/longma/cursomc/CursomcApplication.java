package com.longma.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.longma.cursomc.domain.Address;
import com.longma.cursomc.domain.Category;
import com.longma.cursomc.domain.City;
import com.longma.cursomc.domain.Client;
import com.longma.cursomc.domain.ItemOrder;
import com.longma.cursomc.domain.Order;
import com.longma.cursomc.domain.Payment;
import com.longma.cursomc.domain.PaymentWithCard;
import com.longma.cursomc.domain.PaymentWithWallet;
import com.longma.cursomc.domain.Product;
import com.longma.cursomc.domain.State;
import com.longma.cursomc.domain.enums.ClientType;
import com.longma.cursomc.domain.enums.PaymentStatus;
import com.longma.cursomc.repositories.AddressRepository;
import com.longma.cursomc.repositories.CategoryRepository;
import com.longma.cursomc.repositories.CityRepository;
import com.longma.cursomc.repositories.ClientRepository;
import com.longma.cursomc.repositories.ItemOrderRepository;
import com.longma.cursomc.repositories.OrderRepository;
import com.longma.cursomc.repositories.PaymentRepository;
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
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ItemOrderRepository itemOrderRepository;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Order order1 = new Order(null, sdf.parse("30/09/2020 10:32"), cli1, add1);
		Order order2 = new Order(null, sdf.parse("10/10/2020 10:32"), cli1, add2);
		
		Payment pay1 = new PaymentWithCard(null, PaymentStatus.SETTLED, order1, 6);
		order1.setPayment(pay1);
		Payment pay2 = new PaymentWithWallet(null, PaymentStatus.PENDING, order2, sdf.parse("20/10/2020 00:00"), null);
		order2.setPayment(pay2);

		cli1.getOrders().addAll(Arrays.asList(order1, order2));
		
		orderRepository.saveAll(Arrays.asList(order1, order2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		ItemOrder io1 = new ItemOrder(order1, p1, 0.00, 1, 2000.00);
		ItemOrder io2 = new ItemOrder(order1, p3, 0.00, 2, 80.00);
		ItemOrder io3 = new ItemOrder(order2, p2, 100.00, 1, 800.00);
		
		order1.getItems().addAll(Arrays.asList(io1, io2));
		order2.getItems().addAll(Arrays.asList(io3));
		p1.getItems().addAll(Arrays.asList(io1));
		p2.getItems().addAll(Arrays.asList(io3));
		p3.getItems().addAll(Arrays.asList(io2));

		itemOrderRepository.saveAll(Arrays.asList(io1, io2, io3));
	}

}
