package com.longma.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class DBService {
	
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

	public void instantiateTestDatabase() throws ParseException {
		Category cat1 = new Category(1, "Information");
		Category cat2 = new Category(2, "Office");
		Category cat3 = new Category(3, "Bed Table and Bath");
		Category cat4 = new Category(4, "Electronics");
		Category cat5 = new Category(5, "Gardening");
		Category cat6 = new Category(6, "Decoration");
		Category cat7 = new Category(7, "Perfume");		
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Desk", 300.00);
		Product p5 = new Product(null, "Towel", 50.00);
		Product p6 = new Product(null, "Quilt", 200.00);
		Product p7 = new Product(null, "TV True Color", 1200.00);
		Product p8 = new Product(null, "Rocker", 800.00);
		Product p9 = new Product(null, "Lamp", 100.00);
		Product p10 = new Product(null, "Pending", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));
		
		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4,  p5, p6, p7, p8, p9, p10, p11));
		
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
