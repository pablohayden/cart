package org.shopping.cart.repository;

import java.util.Optional;

import org.shopping.cart.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Optional<Product> findByName(String productName);
	
}