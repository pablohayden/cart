package org.shopping.cart.discount;

import org.shopping.cart.domain.Product;

/**
 * This class a base abstract class used for defining a discount rules.
 * 
 * @author phayden
 *
 */
public abstract class DiscountRule implements IDiscountRule {
	
	private Product product;
	
	private String discountRuleDesc;

	protected DiscountRule(Product product, String discountRuleDesc) {
		this.setProduct(product);
		this.discountRuleDesc = discountRuleDesc;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getDiscountRuleDesc() {
		return discountRuleDesc;
	}

	public void setDiscountRuleDesc(String discountRuleDesc) {
		this.discountRuleDesc = discountRuleDesc;
	}
}
