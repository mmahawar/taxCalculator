package com.dsw.model;

public class TaxCalculator {

	public static double calculateSalesTax(Double price, Double salesTax){
		 return roundSalesTaxToNearestHalf(price * salesTax);
	}

	public static double calculateImportTax(Double price, Double importTax){
		return roundSalesTaxToNearestHalf(price * importTax);
	}
	
	private static Double roundSalesTaxToNearestHalf(Double totalSalesTax) {
		double accuracy = 0.05;
		return Math.round(totalSalesTax.doubleValue() / accuracy) * accuracy;
	}
}
