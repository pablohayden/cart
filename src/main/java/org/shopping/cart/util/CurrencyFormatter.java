package org.shopping.cart.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {
	
	/* Format amount in Great Britain Currency format*/
	// TODO: implement location to support different currency types
	private static Locale locale = new Locale("en", "GB");
	
	public static String currencyFormat(BigDecimal n) {
	    return NumberFormat.getCurrencyInstance(locale).format(n);
	}

}
