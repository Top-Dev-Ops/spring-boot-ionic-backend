package com.longma.cursomc.domain.enums;

public enum ClientType {
	PERSONPHYSICS(1, "Person Physics"),
	PERSONLEGAL(2, "Person Legal");
	
	private int number;
	private String description;

	ClientType(int number, String description) {
		this.number = number;
		this.description = description;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static ClientType toEnum(Integer number) {
		if (number == null) {
			return null;
		}
		
		for (ClientType x: ClientType.values()) {
			if (number.equals(x.getNumber())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalid: " + number);
	}
}
