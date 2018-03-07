package org.shopping.cart.discount;

import org.shopping.cart.domain.ShoppingCart;

/**
 * Discount rule interface provides a single which all implementation rules must implement.
 * 
 * @author phayden
 *
 */
public interface IDiscountRule{

	/**
	 * Applies a discount rule to a list of CartItems within a ShoppingCart
	 * Returns the same list with updated discounted fields applied where rules apply.
	 * 
	 * @param cart
	 * @return
	 */
	public ShoppingCart applyShoppingCartDiscount(ShoppingCart shoppingCart);
}
