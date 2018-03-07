package org.shopping.cart.discount;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.shopping.cart.domain.CartItem;
import org.shopping.cart.domain.Product;
import org.shopping.cart.domain.ShoppingCart;

/**
 * Rule implementation that will apply a discount to a set of Products base upon the specific rule.
 * 
 * For x * Products apply discount percentage to next y Product.
 * 
 * Discount is applied to products that don't already have a discount applied.
 * 
 * @author phayden
 *
 */
public class MultipleProductDiscountedRule extends DiscountRule {

	private int sourceproductmultiple = 0;
	private Product targetdiscountedproduct;
	private double targetdiscountpercentage;
	
	protected MultipleProductDiscountedRule(Product sourceproduct, String discountRuleDesc, int sourceproductmultiple, Product targetdiscountedproduct, double targetdiscountpercentage) {
		super(sourceproduct, discountRuleDesc);
		
		this.sourceproductmultiple = sourceproductmultiple;
		this.targetdiscountedproduct = targetdiscountedproduct;
		this.targetdiscountpercentage = targetdiscountpercentage;
	
	}

	@Override
	public ShoppingCart applyShoppingCartDiscount(ShoppingCart shoppingcart) {
		
		
		int numsourceprooduct = shoppingcart.getCartList().stream().filter(
				item ->	item.getProduct().equals(super.getProduct())).toArray().length;
	
		int numpurchased = numsourceprooduct / sourceproductmultiple;
		
		if(numpurchased > 0) {
			
			List<CartItem> targetlist = shoppingcart.getCartList().stream().filter(item -> (item.getProduct().equals(targetdiscountedproduct) && !item.isDiscountApplied())).collect(Collectors.toList()); 
			
			if(!targetlist.isEmpty()) {
				CartItem targetcartitem = targetlist.get(0);
				
				BigDecimal discountedPrice = targetcartitem.getProduct().getUnitPrice().multiply(BigDecimal.valueOf(targetdiscountpercentage));
				
				targetcartitem.setDiscountedPrice(discountedPrice, this);
				
				targetcartitem.setDiscountRule(this);
			}
		
//			CartItem cartitem = shoppingcart.getCartList().stream().filter(c -> (c.getProduct().equals(targetdiscountedproduct) && c.isDiscountApplied())).findFirst().get();
//			
//			BigDecimal discountedPrice = cartitem.getProduct().getUnitPrice().multiply(BigDecimal.valueOf(targetdiscountpercentage));
//					
//			cartitem.setDiscountedPrice(discountedPrice, this);
//					
//			cartitem.setDiscountRule(this);
		}
		
		return shoppingcart;
	}

}
