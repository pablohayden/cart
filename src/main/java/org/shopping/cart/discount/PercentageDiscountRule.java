package org.shopping.cart.discount;

import java.math.BigDecimal;

import org.shopping.cart.domain.Product;
import org.shopping.cart.domain.ShoppingCart;

public class PercentageDiscountRule extends DiscountRule {
	
	private Double discountpercentage;
	
	protected PercentageDiscountRule(Product product, String discountRuleDesc, Double discountpercentage ) {
		super(product, discountRuleDesc);
		this.discountpercentage = discountpercentage;
	}

	@Override
	public ShoppingCart applyShoppingCartDiscount(ShoppingCart shoppingcart) {
		
		
		shoppingcart.getCartList().forEach(item->{
			
			if(item.getProduct().equals(super.getProduct())){
				
				BigDecimal unitprice = item.getProduct().getUnitPrice();
				
				BigDecimal discountedprice = unitprice.multiply(BigDecimal.valueOf(discountpercentage));
				
				if(!item.isDiscountApplied()) item.setDiscountedPrice(discountedprice, this);
			}
				
		});
		
		return shoppingcart;
	}

}
