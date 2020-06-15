package com.longma.cursomc.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.longma.cursomc.domain.enums.PaymentStatus;

@Entity
@JsonTypeName("paymentWithWallet")
public class PaymentWithWallet extends Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date dueDate;
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
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
