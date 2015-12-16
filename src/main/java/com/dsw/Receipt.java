package com.dsw;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Receipt {

	DecimalFormat decimalFormat = new DecimalFormat("##.00");
	public List<PurchasedLineItem> purchasedItems;
	public double salesTax;
	public double totalPrice;

	public Receipt(List<PurchasedLineItem> purchasedItems) {
		this.purchasedItems = purchasedItems;
		this.salesTax = totalSalesTaxCalculator();
		this.totalPrice = totalPriceCalculator();
	}

	private double totalSalesTaxCalculator() {
		double salesTax = 0;
		for (PurchasedLineItem item : purchasedItems) {
			salesTax += item.getSalesTax();
		}
		return salesTax;
	}

	private double totalPriceCalculator() {
		double totalItemPrice = 0;
		for (PurchasedLineItem item : purchasedItems) {
			totalItemPrice += item.getPurchasedPrice();
		}
		return totalItemPrice;

	}

	public List<String> printReceipt() {
		List<String> receiptOutput = new ArrayList<String>();
		for (PurchasedLineItem i : purchasedItems) {
			Item item = i.getItem();
			receiptOutput.add(i.getQuantity() + " " + item.getName() + " : "
					+ decimalFormat.format(i.getPurchasedPrice()));
		}
		receiptOutput.add("Sales Taxes: " + decimalFormat.format(salesTax));
		receiptOutput.add("Total: " + decimalFormat.format(totalPrice));
		return receiptOutput;
	}

}
