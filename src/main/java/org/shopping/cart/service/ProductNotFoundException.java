package org.shopping.cart.service;

public class ProductNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(String prod) {
		
		super(String.format("Product[%s] was not found. Please select from the following product types only [Bread, Milk, Apples, Soup]", prod));
	}

}
