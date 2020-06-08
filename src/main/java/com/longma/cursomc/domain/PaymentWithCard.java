package com.longma.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import com.longma.cursomc.domain.enums.PaymentStatus;

@Entity
public class PaymentWithCard extends Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer numberOfPackets;
	
	public PaymentWithCard() { }

	public PaymentWithCard(Integer id, PaymentStatus status, Order order, Integer numberOfPackets) {
		super(id, status, order);
		this.numberOfPackets = numberOfPackets;
	}

	public Integer getNumberOfPackets() {
		return numberOfPackets;
	}

	public void setNumberOfPackets(Integer numberOfPackets) {
		this.numberOfPackets = numberOfPackets;
	}
	
	
}
