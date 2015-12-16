package com.dsw.model;

import java.math.BigDecimal;

public class Item {
	private static final BigDecimal IMPORT_DUTY_IN_PERCENTAGE = new BigDecimal(0.05);
	private static final BigDecimal SALES_TAX_IN_PERCENTAGE = new BigDecimal(0.10);
	private static final BigDecimal NO_TAX = new BigDecimal(0);

	public String name;
	public int quantity;
	public Category category;
	private BigDecimal price;
	private boolean isImported;

	public Item(String name, int quantity, Category category, BigDecimal price, boolean isImported) {
		this.name = name;
		this.quantity = quantity;
		this.category = category;
		this.price = price;
		this.isImported = isImported;
	}

	public BigDecimal calculateSalesTax() {
		BigDecimal totalSalesTax = calculateBasicSalesTax().add(calculateImportDuty());
		return roundSalesTaxToNearestHalf(totalSalesTax);
	}

	private BigDecimal calculateBasicSalesTax() {
		if (isExempt()) {
			return NO_TAX;
		}
		return price.multiply(SALES_TAX_IN_PERCENTAGE);
	}

	private boolean isExempt() {
		switch (category) {
		case FOOD:
		case MEDICINE:
		case BOOK:
			return true;
		default:
			return false;
		}
	}

	private BigDecimal calculateImportDuty() {
		if (isImported) {
			return price.multiply(IMPORT_DUTY_IN_PERCENTAGE);
		}
		return NO_TAX;
	}

	private BigDecimal roundSalesTaxToNearestHalf(BigDecimal totalSalesTax) {
		double accuracy = 0.05;
		double result = Math.round(totalSalesTax.doubleValue() / accuracy) * accuracy;
		BigDecimal finalTax = new BigDecimal(result);
		return finalTax;
	}
	
	public BigDecimal getPrice() {
		return price.add(calculateSalesTax());
	}
}