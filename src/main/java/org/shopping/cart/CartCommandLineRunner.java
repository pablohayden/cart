package org.shopping.cart;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.shopping.cart.discount.DiscountCalculator;
import org.shopping.cart.discount.DiscountCalculatorFactory;
import org.shopping.cart.domain.CartItem;
import org.shopping.cart.domain.Product;
import org.shopping.cart.domain.ShoppingCart;
import org.shopping.cart.repository.ProductRepository;
import org.shopping.cart.repository.ShoppingCartRepository;
import org.shopping.cart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Spring Bean component that returns a spring boot managed Bean that implements commandLineRunner.
 * This is used for application launch...
 * 
 * @author root
 *
 */
@Component
class CartCommandLineRunner implements CommandLineRunner{
	
	@Autowired ShoppingCartRepository cartRepository;
	
	@Autowired ProductRepository productRepository;
	
	@Autowired ShoppingCartService shoppingService;
	
	@Autowired ShoppingCartService cartService;
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public void run(String... args) throws Exception {
		
	
		ShoppingCart shoppingCart = getShoppingCart(args[0]);
		
		boolean first = true;
		
		for(String arg : args) {
					
					if(first) {
						
						first = false;
					}
					else {
						
						Optional<Product> product = productRepository.findByName(arg);
						
						if(product.isPresent()) shoppingCart.addCartItem(new CartItem(product.get()));
		
					}
		}
		
		cartRepository.saveAndFlush(shoppingCart);
		
		DiscountCalculator discountcalculator = DiscountCalculatorFactory.getDiscountCalculator(LocalDate.now());
		
		shoppingCart = discountcalculator.applyDiscountRuleToCart(shoppingCart);
		
		shoppingCart.printTotals();
	
	}
	
	/**
	 * 
	 * Utility method to look up the shopping cart using the service methods provided.
	 * @param cartname
	 * @return ShoppingCart
	 */
	public ShoppingCart getShoppingCart(String cartname) {
	
		Optional<ShoppingCart> shoppingCart = cartService.findShoppingCart(cartname);
		
		shoppingCart.orElse(new ShoppingCart(cartname));
		
		return shoppingCart.get();
		
	}
}


