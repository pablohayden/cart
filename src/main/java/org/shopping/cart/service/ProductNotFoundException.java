package org.shopping.cart.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(String prod) {
		
		super(String.format("Product[%s] was not found. Please select from the following product types only [Bread, Milk, Apples, Soup]", prod));
	}
	
	public ProductNotFoundException(Long prodid) {
		
		super(String.format("Product ID[%s] was not found. Please select from the following product types only [Bread, Milk, Apples, Soup]", prodid));
	}

}
