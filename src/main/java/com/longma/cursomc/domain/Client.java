package com.longma.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.longma.cursomc.domain.enums.ClientType;

@Entity
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String phyOrLegal;
	private Integer type;
	
	@JsonManagedReference
	@OneToMany(mappedBy="client")
	private List<Address> addresses = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="TELEPHONE")
	private Set<String> telephones = new HashSet<>();
	
	@OneToMany(mappedBy ="client")
	private List<Order> orders = new ArrayList<>();
	
	public Client() { }

	public Client(Integer id, String name, String email, String phyOrLegal, ClientType type) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phyOrLegal = phyOrLegal;
		this.type = type.getNumber();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhyOrLegal() {
		return phyOrLegal;
	}

	public void setPhyOrLegal(String phyOrLegal) {
		this.phyOrLegal = phyOrLegal;
	}

	public ClientType getType() {
		return ClientType.toEnum(type);
	}

	public void setType(ClientType type) {
		this.type = type.getNumber();
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<String> getTelephones() {
		return telephones;
	}

	public void setTelephones(Set<String> telephones) {
		this.telephones = telephones;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
