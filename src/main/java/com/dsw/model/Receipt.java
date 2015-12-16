package com.dsw.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class Receipt {
	DecimalFormat decimalFormat = new DecimalFormat("##.00");
	public List<Item> purchasedItems;
	public BigDecimal salesTax;
	public BigDecimal totalPrice;
	
	public Receipt(List<Item> purchasedItems) {
		this.purchasedItems = purchasedItems;
		this.salesTax = salesTaxGatherer();
		this.totalPrice = totalPriceGatherer();
	}
	
	private BigDecimal salesTaxGatherer(){
		BigDecimal salesTax = new BigDecimal(0);
		for (Item item : purchasedItems) {
			 salesTax = salesTax.add(item.calculateSalesTax());
		}
		return salesTax;
		
	}
	
	private BigDecimal totalPriceGatherer(){
		BigDecimal totalItemPrice = new BigDecimal(0);
		for (Item item : purchasedItems) {
			totalItemPrice = totalItemPrice.add(item.getPrice());
		}
		return new BigDecimal(decimalFormat.format(totalItemPrice)) ;
		
	}
	
	@Override
	public String toString() {
		String receiptOutput = ""; 
		for (Item item : purchasedItems) {
			receiptOutput = receiptOutput.concat(item.quantity + " " + item.name + " : " + decimalFormat.format(item.getPrice()) + "\n");
		}
		receiptOutput = receiptOutput.concat("Sales Taxes:" + salesTax + "\n");
		receiptOutput = receiptOutput.concat("Total:" + totalPrice);
		return receiptOutput;
	}
}
