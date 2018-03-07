package org.shopping.cart.discount;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Ignore;
import org.junit.Test;
import org.shopping.cart.domain.CartItem;
import org.shopping.cart.domain.Product;
import org.shopping.cart.domain.ShoppingCart;


public class RulesDiscountCalculatorTest {
	
	 
	private static LocalDate TODAY = LocalDate.now();
	
//	private static LocalDate TOMORROW = LocalDate.now().plusDays(1);
	
	private String 	apples = "Apples",
					soup = "Soup",
					bread = "Bread",
					milk = "Milk";
	
	private Product appleprod = new Product(apples, BigDecimal.valueOf(1.00));
	private Product soupprod = new Product(soup, BigDecimal.valueOf(0.65));
	private Product breadprod = new Product(bread, BigDecimal.valueOf(0.80));
	private Product milkprod = new Product(milk, BigDecimal.valueOf(1.30));

//	@Ignore
	@Test
	public void testPercentageDiscountRule() {
			
		BigDecimal discountval = this.appleprod.getUnitPrice().multiply(new BigDecimal(0.50));
			
		RulesDiscountCalculator rulecalc = new RulesDiscountCalculator();
		
		rulecalc.addRule(new PercentageDiscountRule(this.appleprod, "Apples 50% off", 0.50));
		
		ShoppingCart cart = generateStubCartListForPercentageRule();
		
		cart = rulecalc.applyDiscountRuleToCart(cart);

		BigDecimal totalapplediscount = cart.getTotalDiscounts(this.appleprod);
		
		assertTrue((totalapplediscount.compareTo(discountval)) == 0);
		
		cart.printTotals();
		

		/* Second test to verify multiple values work */
		
		cart = generateStubCartListForPercentageRule3();
		
		cart = rulecalc.applyDiscountRuleToCart(cart);
		
	
		totalapplediscount = cart.getTotalDiscounts(this.appleprod);
		
		assertTrue((totalapplediscount.compareTo(discountval.multiply(new BigDecimal(3)))) == 0);
		

		cart.printTotals();
		
	}
	
//	@Ignore
	@Test
	public void testMultipleProductDiscountedRule() {
		
		BigDecimal discountval = this.breadprod.getUnitPrice().multiply(new BigDecimal(0.50));
		
		RulesDiscountCalculator rulecalc = new RulesDiscountCalculator();
		
		rulecalc.addRule(new MultipleProductDiscountedRule(soupprod, "2 Tins of soup get a loaf half price ", 2, this.breadprod, 0.50));
		

		ShoppingCart cart = generateStubCartListForMultipleProductDiscountedRule();
		
		cart = rulecalc.applyDiscountRuleToCart(cart);
		
		BigDecimal totalapplediscount = cart.getTotalDiscounts(this.breadprod);
		
		assertTrue((totalapplediscount.compareTo(discountval)) == 0);
		
		cart.printTotals();
    
	}
	
//	@Ignore
	@Test
	public void testRuleFactory() {
		
		BigDecimal breaddiscountval = this.breadprod.getUnitPrice().multiply(new BigDecimal(0.50));
		BigDecimal applesdiscountval = this.appleprod.getUnitPrice().multiply(new BigDecimal(0.50));
		
		DiscountCalculator rulecalc = DiscountCalculatorFactory.getDiscountCalculator(LocalDate.now());
			
		ShoppingCart cart = generateStubCartListForPercentageRule();
			
		cart = rulecalc.applyDiscountRuleToCart(cart);
		
		/* Factory Currently only holds 2 rules which we have defined */
		// TODO: This will need to be modified to support different rule types
		BigDecimal totalapplediscountbread = cart.getTotalDiscounts(this.breadprod);
		BigDecimal totalapplediscountapples = cart.getTotalDiscounts(this.appleprod);
		
		
		
		assertTrue((totalapplediscountapples.compareTo(applesdiscountval)) == 0);
		
		assertTrue((totalapplediscountbread.compareTo(breaddiscountval)) == 0);
		
		cart.printTotals();
	}
	
	@Ignore
	@Test
	public void testCalculateTotalDiscount() {
		

		DiscountCalculator rulecalc = DiscountCalculatorFactory.getDiscountCalculator(TODAY);
		
		fail("Not yet implemented");
	}
	
	public ShoppingCart generateStubCartListForMultipleProductDiscountedRule(){
		
		ShoppingCart shoppingCart = new ShoppingCart("Test Cart");
	
		shoppingCart.addCartItem(new CartItem(this.soupprod));
		shoppingCart.addCartItem(new CartItem(this.soupprod));
		shoppingCart.addCartItem(new CartItem(this.breadprod));
				
		
		return shoppingCart;
	}

	public ShoppingCart generateStubCartListForPercentageRule(){
		
		ShoppingCart shoppingCart = new ShoppingCart("Test Cart");
			
		shoppingCart.addCartItem(new CartItem(this.appleprod));
		shoppingCart.addCartItem(new CartItem(this.soupprod));
		shoppingCart.addCartItem(new CartItem(this.soupprod));
		shoppingCart.addCartItem(new CartItem(this.breadprod));
		shoppingCart.addCartItem(new CartItem(this.breadprod));
		shoppingCart.addCartItem(new CartItem(this.soupprod));
		shoppingCart.addCartItem(new CartItem(this.soupprod));
//		shoppingCart.addCartItem(new CartItem(this.appleprod));
		
		
		return shoppingCart;
	}
	
	public ShoppingCart generateStubCartListForPercentageRule3(){
		
		ShoppingCart shoppingCart = new ShoppingCart("Test Cart");
			
		shoppingCart.addCartItem(new CartItem(this.appleprod));
		shoppingCart.addCartItem(new CartItem(this.appleprod));
		shoppingCart.addCartItem(new CartItem(this.appleprod));
		shoppingCart.addCartItem(new CartItem(this.breadprod));
		shoppingCart.addCartItem(new CartItem(this.breadprod));
		shoppingCart.addCartItem(new CartItem(this.soupprod));
		shoppingCart.addCartItem(new CartItem(this.soupprod));
//		shoppingCart.addCartItem(new CartItem(this.appleprod));
		
		
		return shoppingCart;
	}

}
