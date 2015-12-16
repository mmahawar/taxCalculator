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

		double totalPriceForAllPurchasedQuantity = (price + totalTax) * quantity;
		double totalTaxForAllPurchasedQuantity = totalTax * quantity;
		return new PurchasedItem(this, totalPriceForAllPurchasedQuantity, totalTaxForAllPurchasedQuantity, quantity);
	}

	public String getName() {
		return name;
	}
}
