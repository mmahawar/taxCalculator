package com.dsw.model;

public class PurchasedItem {

	private Item item;
	private double purchasedPrice;
	private double salesTax;

	public Item getItem() {
		return item;
	}

	public double getPurchasedPrice() {
		return purchasedPrice;
	}

	public double getSalesTax() {
		return salesTax;
	}

	public PurchasedItem(Item item, double purchasedPrice, double salesTax) {
		this.item = item;
		this.purchasedPrice = purchasedPrice;
		this.salesTax = salesTax;

	}

	public int getQuantity() {
		return item.getQuantity();
	}

	public String getName() {
		return item.getName();
	}
}