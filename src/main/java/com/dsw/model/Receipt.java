package com.dsw.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Receipt {

	DecimalFormat decimalFormat = new DecimalFormat("##.00");
	public List<PurchasedItem> purchasedItems;
	public double salesTax;
	public double totalPrice;

	public Receipt(List<PurchasedItem> purchasedItems) {
		this.purchasedItems = purchasedItems;
		this.salesTax = salesTaxGatherer();
		this.totalPrice = totalPriceGatherer();
	}

	private double salesTaxGatherer() {
		double salesTax = 0;
		for (PurchasedItem item : purchasedItems) {
			salesTax += item.getSalesTax();
		}
		return salesTax;
	}

	private double totalPriceGatherer() {
		double totalItemPrice = 0;
		for (PurchasedItem item : purchasedItems) {
			totalItemPrice += item.getPurchasedPrice();
		}
		return totalItemPrice;

	}

	public List<String> printReceipt() {
		List<String> receiptOutput = new ArrayList<String>();
		for (PurchasedItem i : purchasedItems) {
			Item item = i.getItem();
			receiptOutput.add(item.getQuantity() + " " + item.getName() + " : "
					+ decimalFormat.format(i.getPurchasedPrice()));
		}
		receiptOutput.add("Sales Taxes: " + decimalFormat.format(salesTax));
		receiptOutput.add("Total: " + decimalFormat.format(totalPrice));
		return receiptOutput;
	}

}
