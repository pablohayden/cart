package org.shopping.cart.repository;

import java.util.Optional;

import org.shopping.cart.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{
	
	Optional<ShoppingCart> findByCartName(String cartName);
	
}