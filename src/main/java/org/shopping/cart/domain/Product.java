package org.shopping.cart.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
	
	
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable = false, name = "product_name", unique = true)
	private String name;
	
	@Column(nullable = false, name = "unit_price", scale=2 )
	private BigDecimal unitPrice;
	
	
	// JPA requires no argument constructor
	public Product() {
		super();
	}

	public Product(String name) {
		this.name = name;
	}

	public Product(String productname, BigDecimal unitPrice) {
		this.name = productname;
		this.unitPrice = unitPrice;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Product) {
			return ((Product)obj).getName().equals(this.name);
		}
	
		return false;
	}

	@Override
	public int hashCode() {
		return 1;
	}

	
}
