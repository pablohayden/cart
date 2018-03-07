package org.shopping.cart.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.shopping.cart.discount.DiscountRule;

@Entity
public class CartItem {
	
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="fk_cartitem")
	private Product product;
	
	// We're not persisting this so this should be moved out into a decorator 
	@Transient
	private BigDecimal discountedPrice;
		
	@Transient 
	private boolean discountApplied = false;
	
	@Transient
	private DiscountRule discountRule;
	
	// JPA requires no argument constructor
	public CartItem() {
		super();
		
	}

	public CartItem(Product product) {
		this.product = product;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getDiscountedPrice() {
		
		return discountedPrice == null ? new BigDecimal(0) : discountedPrice;
	}

	public void setDiscountedPrice(BigDecimal discountedPrice, DiscountRule discountRule) {
		this.discountedPrice = discountedPrice;
		this.discountRule = discountRule;
		this.discountApplied = true;
	}

	public boolean isDiscountApplied() {
		return discountApplied;
	}

	public void setDiscountApplied(boolean discountApplied) {
		this.discountApplied = discountApplied;
	}

	public DiscountRule getDiscountRule() {
		return discountRule;
	}

	public void setDiscountRule(DiscountRule discountRule) {
		this.discountRule = discountRule;
	}

	public String getProductName() {
		return product.getName();
	}
}
