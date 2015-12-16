package com.dsw.model;

public class Item {
	private final String name;
	private final int quantity;
	private final Category category;
	private final Double price;
	private final boolean isImported;

	public Item(String name, int quantity, Category category, Double price, boolean isImported) {
		this.name = name;
		this.quantity = quantity;
		this.category = category;
		this.price = price;
		this.isImported = isImported;
	}

	public PurchasedItem purchase(Double salesTax, Double importedTax) {
		double totalTax = 0;
		if (!Category.isExempt(category)) {
			totalTax += TaxCalculator.calculateSalesTax(price, salesTax);
		}

		if (isImported) {
			totalTax += TaxCalculator.calculateImportTax(price, importedTax);
		}
		return new PurchasedItem(this, price + totalTax, totalTax);
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}
}
