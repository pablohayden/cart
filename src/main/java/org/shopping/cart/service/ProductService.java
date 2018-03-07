package org.shopping.cart.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.shopping.cart.domain.Product;
import org.shopping.cart.repository.ProductRepository;
import org.slf4j.Logger;
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
	
	@Autowired Logger log;
	
	public Optional<Product> findByName(String productName) {
		
		return prodrepo.findByName(productName);
		
	}

	public List<Product> findAll() {
		
		return prodrepo.findAll();
	}

	public Optional<Product> findById(Long id) {
		
		
		return prodrepo.findById(id);
		
		
	}

	public Product saveAndFlush(Product product) {
		return prodrepo.saveAndFlush(product);
	}
	
	
	public void delete(Product product) {
		prodrepo.delete(product);
	}
		
	
}
