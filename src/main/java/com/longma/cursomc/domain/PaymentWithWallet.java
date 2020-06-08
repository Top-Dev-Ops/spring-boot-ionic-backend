package com.longma.cursomc.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import com.longma.cursomc.domain.enums.PaymentStatus;

@Entity
public class PaymentWithWallet extends Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dueDate;
	private Date payDate;
	
	public PaymentWithWallet() { }

	public PaymentWithWallet(Integer id, PaymentStatus status, Order order, Date dueDate, Date payDate) {
		super(id, status, order);
		this.dueDate = dueDate;
		this.payDate = payDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
}
