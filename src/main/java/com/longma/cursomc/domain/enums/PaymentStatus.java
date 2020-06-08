package com.longma.cursomc.domain.enums;

public enum PaymentStatus {

	PENDING(1, "Pending"),
	SETTLED(2, "Settled"),
	CANCELLED(3, "Cancelled");
	
	private int number;
	private String description;

	private PaymentStatus(int number, String description) {
		this.number = number;
		this.description = description;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static PaymentStatus toEnum(Integer number) {
		if (number == null) {
			return null;
		}
		
		for (PaymentStatus x: PaymentStatus.values()) {
			if (number.equals(x.getNumber())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalid: " + number);
	}
}
