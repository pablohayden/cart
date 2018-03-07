package org.shopping.cart.discount;

import java.util.ArrayList;
import java.util.List;

import org.shopping.cart.domain.ShoppingCart;

/**
 * Leverage strategy pattern to implement a dynamic rule strategy that allows use to create new discount rules.
 * Additional rules can be created without modifying any of the existing classes.
 * In a real world situation it may be better to use a separate rules engine for this behaviour depending 
 * the scale and business requirements.
 *
 * @author phayden
 *
 */
public class RulesDiscountCalculator implements DiscountCalculator {
	
	List<DiscountRule> discountrules = new ArrayList<DiscountRule>();
	
	protected RulesDiscountCalculator() {}
	 
	@Override
	public ShoppingCart applyDiscountRuleToCart(ShoppingCart shoppingcart) {
		
		
		discountrules.forEach(item->{
			
			item.applyShoppingCartDiscount(shoppingcart);
				
		});
				
		return shoppingcart;
	}

	public void addRule(DiscountRule discountRule) {
		this.discountrules.add(discountRule);
	}

}
