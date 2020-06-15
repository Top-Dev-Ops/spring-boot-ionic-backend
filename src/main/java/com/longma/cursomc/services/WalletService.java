package com.longma.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.longma.cursomc.domain.PaymentWithWallet;

@Service
public class WalletService {

	public void fillPaymentWithWallet(PaymentWithWallet payww, Date instant) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		payww.setDueDate(cal.getTime());
	}
}
