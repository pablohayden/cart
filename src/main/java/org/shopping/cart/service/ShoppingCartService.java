package org.shopping.cart.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.shopping.cart.domain.ShoppingCart;
import org.shopping.cart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ShoppingCartService {
	
	@Autowired ShoppingCartRepository shoppingCartRepository;
	
	/**
	 * Service method to proxy request to the JPA CRUD repository
	 * @param name
	 * @return
	 */
	public Optional<ShoppingCart> findShoppingCart(String name) {
		
		return shoppingCartRepository.findByCartName(name);
		
	}

	public void saveAndFlush(ShoppingCart shoppingCart) {
		shoppingCartRepository.saveAndFlush(shoppingCart);
	}

	
}
