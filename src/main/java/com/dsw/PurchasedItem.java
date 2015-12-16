package com.dsw;

public class PurchasedItem {

	private Item item;
	private int quantity;
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

	public PurchasedItem(Item item, double purchasedPrice, double salesTax, int quantity) {
		this.item = item;
		this.purchasedPrice = purchasedPrice;
		this.salesTax = salesTax;
		this.quantity = quantity;

	}

	public int getQuantity() {
		return this.quantity;
	}

	public String getName() {
		return item.getName();
	}
}