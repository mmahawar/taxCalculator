package com.dsw.model;

import java.text.DecimalFormat;
import java.util.List;

public class Receipt {

	DecimalFormat decimalFormat = new DecimalFormat("##.00");
	public List<PurchasedItem> purchasedItems;
	public Double salesTax;
	public Double totalPrice;

	public Receipt(List<PurchasedItem> purchasedItems) {
		this.purchasedItems = purchasedItems;
		this.salesTax = salesTaxGatherer();
		this.totalPrice = totalPriceGatherer();
	}

	private Double salesTaxGatherer() {
		double salesTax = 0;
		for (PurchasedItem item : purchasedItems) {
			salesTax += item.getSalesTax();
		}
		return salesTax;
	}

	private Double totalPriceGatherer() {
		double totalItemPrice = 0;
		for (PurchasedItem item : purchasedItems) {
			totalItemPrice += item.getPurchasedPrice();
		}
		return totalItemPrice;

	}

	@Override
	public String toString() {
		String receiptOutput = "";
		for (PurchasedItem i : purchasedItems) {
			Item item = i.getItem();
			receiptOutput = receiptOutput.concat(item.getQuantity() + " " + item.getName() + " : "
					+ decimalFormat.format(i.getPurchasedPrice()) + "\n");
		}
		receiptOutput = receiptOutput.concat("Sales Taxes:" + salesTax + "\n");
		receiptOutput = receiptOutput.concat("Total:" + totalPrice);
		return receiptOutput;
	}

}
