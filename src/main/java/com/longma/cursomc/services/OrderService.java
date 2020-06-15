package com.longma.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longma.cursomc.domain.ItemOrder;
import com.longma.cursomc.domain.Order;
import com.longma.cursomc.domain.PaymentWithWallet;
import com.longma.cursomc.domain.enums.PaymentStatus;
import com.longma.cursomc.repositories.ItemOrderRepository;
import com.longma.cursomc.repositories.OrderRepository;
import com.longma.cursomc.repositories.PaymentRepository;
import com.longma.cursomc.repositories.ProductRepository;
import com.longma.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ItemOrderRepository itemOrderRepo;
	
	public Order find(Integer id) {
		Order obj = repo.findById(id).orElse(null);
		if(obj == null) {
			throw new ObjectNotFoundException("Object not found with Id " + id);
		}
		return obj;
	}
	
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.getPayment().setStatus(PaymentStatus.PENDING);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof PaymentWithWallet) {
			PaymentWithWallet payww = (PaymentWithWallet) obj.getPayment();
			walletService.fillPaymentWithWallet(payww, obj.getInstant());
		}
		obj = repo.save(obj);
		paymentRepo.save(obj.getPayment());
		for (ItemOrder io : obj.getItems()) {
			io.setDiscount(0.0);
			io.setPrice(productRepo.findById(io.getProduct().getId()).get().getPrice());
			io.setOrder(obj);
		}
		itemOrderRepo.saveAll(obj.getItems());
		return obj;
	}
}
