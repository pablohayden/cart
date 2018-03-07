package org.shopping.cart.discount;

import java.time.LocalDate;

import org.shopping.cart.domain.Product;

/**
 * DiscountCalculatorFactory class is in the creation of RulesDiscountCallculators.
 * Purpose is to encapsulate creation of all discount calculators into a single location and follows the single 
 * responsibility principle.
 * In a real world situation this could an be extended to support externally loaded rulesets 
 * 
 * @author phayden
 *
 */
public final class DiscountCalculatorFactory {

	/**
	 * Creates a DiscountCalculator for a given date calculating discounts on a cart of order items.
	 * 
	 * @param date
	 * @return DiscountCalculator
	 */
	public static DiscountCalculator getDiscountCalculator(LocalDate date){
		
		RulesDiscountCalculator rulecalc = new RulesDiscountCalculator();
		
		/* Used as an example of to generate different rule sets using a factory.
		 * Production generator could load these from a datasource based upon the date. */
		if(date.isEqual(LocalDate.now())) {
					
			rulecalc.addRule(new PercentageDiscountRule(new Product("Apples"), "Apples 50% off", 0.50));
			rulecalc.addRule(new MultipleProductDiscountedRule(new Product("Soup"), "2 Tins of soup get a loaf half price ", 2, new Product("Bread"), 0.50));
		}
		else if(date.isAfter(LocalDate.now())) {
				
			rulecalc.addRule(new PercentageDiscountRule(new Product("Apples"), "Apples 10% off", 0.50));
			rulecalc.addRule(new PercentageDiscountRule(new Product("Soup"), "2 Tins of soup get a loaf half price ", 0.50));
		}
		
		return rulecalc;
		   
	 
	}

}
