package com.dsw;

public class TaxCalculator {

	public static double calculateSalesTax(double price, double salesTax){
		 return roundSalesTaxToNearestHalf(price * salesTax);
	}

	public static double calculateImportTax(double price, double importTax){
		return roundSalesTaxToNearestHalf(price * importTax);
	}
	
	public static double roundSalesTaxToNearestHalf(double totalSalesTax) {
		double accuracy = 0.05;
		return Math.ceil(totalSalesTax / accuracy) * accuracy;
	}
}
