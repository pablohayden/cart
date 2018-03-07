package org.shopping.cart.discount;

import org.shopping.cart.domain.ShoppingCart;

public interface DiscountCalculator {
	
	public ShoppingCart applyDiscountRuleToCart(ShoppingCart cart);

}
