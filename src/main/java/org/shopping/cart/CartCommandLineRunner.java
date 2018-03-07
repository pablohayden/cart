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
import org.shopping.cart.service.ProductService;
import org.shopping.cart.service.ShoppingCartService;
import org.slf4j.Logger;
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
	
	
	@Autowired Logger log;
	
	@Autowired ProductService prodService;
	
	@Autowired ShoppingCartService cartService;
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public void run(String... args) throws Exception {
		
		if(args.length == 0) throw new ShoppingCartException("You must specify command line input parameters [shoppingcartname] [item1][item2][item3]...");
		if(args.length == 1) throw new ShoppingCartException("You must specify at least one shopping cart item [shoppingcartname] [item1][item2][item3]...");
		
	
		ShoppingCart shoppingCart = getShoppingCart(args[0]);
		
		boolean first = true;
		
		for(String arg : args) {
					
					if(first) {
						
						first = false;
					}
					else {
						
						Optional<Product> product = prodService.findByName(arg);
						
						if(product.isPresent()) shoppingCart.addCartItem(new CartItem(product.get()));
		
					}
		}
		
		cartService.saveAndFlush(shoppingCart);
		
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
		
		return shoppingCart.orElse(new ShoppingCart(cartname));
		
//		return shoppingCart.get();
		
	}
}


