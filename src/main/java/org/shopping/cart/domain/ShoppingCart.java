package org.shopping.cart.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.shopping.cart.discount.DiscountRule;
import org.shopping.cart.util.CurrencyFormatter;

@Entity
public class ShoppingCart{
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(name="cart_name", nullable=false, length=512)
	private String cartName;
	
    @Column(name = "items")
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CartItem> items ;
	
	// JPA requires no argument constructor
	public ShoppingCart() {
		
	}
	
	public ShoppingCart(String shoppingcartname) {
		this.cartName = shoppingcartname;
	}

	public Long getId() {
		return id;
	}

	public String getCartName() {
		return cartName;
	}

	public void setCartName(String cartName) {
		this.cartName = cartName;
	}
	
	public void addCartItem(CartItem cartItem) {
		if(items == null) items = new ArrayList<CartItem>();
		
		items.add(cartItem);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public Collection<CartItem> getCartList() {
		return items;
	}

	public String getSubTotal() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public BigDecimal getTotalDiscounts(Product product) {
		
		BigDecimal totalproddiscount = items.stream().filter(c -> (c.getProduct().equals(product) && c.isDiscountApplied())).map(x-> x.getDiscountedPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
	
		
		return totalproddiscount;
		
	}

	public void printTotals() {
		
		System.out.println("-------------------- Bask Print Start ----------------------");

		
		BigDecimal total = items.stream().map(e -> e.isDiscountApplied() ? e.getDiscountedPrice() : e.getProduct().getUnitPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
		
		BigDecimal subtotal = items.stream().map(e -> e.getProduct().getUnitPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
		
		System.out.format("SubTotal : %s\n", CurrencyFormatter.currencyFormat(subtotal));
		
        Map<String, Set<CartItem>> groupedresults =
                items.stream().filter(c -> c.getDiscountRule() != null).collect(
                        Collectors.groupingBy(CartItem::getProductName,  Collectors.toSet())
                );

        if(groupedresults.isEmpty()) {
        	System.out.println("(no offers available");
        }else {
	        for (Map.Entry<String, Set<CartItem>> entry : groupedresults.entrySet()) {
	        	
	             	Set<CartItem> cartitemset = entry.getValue();
	        	
	        	DiscountRule discount = cartitemset.stream().findFirst().get().getDiscountRule();
	        	
	        	BigDecimal discountperproduct = cartitemset.stream().map(e -> e.getDiscountedPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
	
	        	System.out.format("%s offer - %s : -%s\n", entry.getKey(), discount.getDiscountRuleDesc(), CurrencyFormatter.currencyFormat(discountperproduct));
	
	        }
        }
        
        System.out.format("Total : %s\n", CurrencyFormatter.currencyFormat(total));
        
        System.out.println("-------------------- Bask Print End ------------------------");
   
	}
}
