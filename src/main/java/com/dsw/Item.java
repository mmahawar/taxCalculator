package com.dsw;

public class Item {
	private final String name;
	private final Category category;
	private final double price;
	private final boolean isImported;

	public Item(String name, Category category, double price, boolean isImported) {
		this.name = name;
		this.category = category;
		this.price = price;
		this.isImported = isImported;
	}

	public PurchasedItem purchaseAndMaybeApplyTax(double salesTax, double importedTax, int quantity) {
		double totalTax = 0;
		if (!category.isExempt()) {
			totalTax += TaxCalculator.calculateSalesTax(price, salesTax);
		}

		if (isImported) {
			totalTax += TaxCalculator.calculateImportTax(price, importedTax);
		}
		return new PurchasedItem(this, price + totalTax, totalTax, quantity);
	}

	public String getName() {
		return name;
	}
}
