package com.dsw.model;

public class PurchasedItem {

	private Item item;
	private Double purchasedPrice;
	private Double salesTax;

	public Item getItem() {
		return item;
	}

	public Double getPurchasedPrice() {
		return purchasedPrice;
	}

	public Double getSalesTax() {
		return salesTax;
	}

	public PurchasedItem(Item item, Double purchasedPrice, Double salesTax) {
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