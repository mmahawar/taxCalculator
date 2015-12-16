package com.dsw;

public class Item {
	private final String name;
	private final int quantity;
	private final Category category;
	private final double price;
	private final boolean isImported;

	public Item(String name, int quantity, Category category, double price, boolean isImported) {
		this.name = name;
		this.quantity = quantity;
		this.category = category;
		this.price = price;
		this.isImported = isImported;
	}

	public PurchasedItem purchaseAndMaybeApplyTax(double salesTax, double importedTax) {
		double totalTax = 0;
		if (!category.isExempt()) {
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
