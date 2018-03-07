package org.shopping.cart.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.shopping.cart.domain.Product;
import org.shopping.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * The prodoct service layer which provides a service around the CRUD repository layer and returns a DAO.
 * Implemented using Spring stereotypes and provides transactional support.
 * 
 * 
 * @author phayden
 *
 */
@Service
@Transactional
public class ProductService {
	
	
	@Autowired private ProductRepository prodrepo;
	
	public Product findByName(String productName) throws ProductNotFoundException{
		
		
		
		Optional<Product> product = prodrepo.findByName(productName);
		
		
		if(product.isPresent()) return product.get();
		
		throw new ProductNotFoundException(productName);
		
	}
		
	
}
