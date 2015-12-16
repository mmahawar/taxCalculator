package com.dsw;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dsw.model.Category;
import com.dsw.model.Item;
import com.dsw.model.Receipt;

public class ReceiptBuilderTest {
	
	
	public Double rounding(double v) {
		double accuracy = 0.05;
		double result = Math.round(v / accuracy) * accuracy;
		return result;
	}
	
	@Test
	public void testRounding() {
		assertEquals(1.50, rounding(1.49), 0.001);
		assertEquals(7.65, rounding(7.625), 0.001);
	}
	

	@Test
	public void testShouldGenerateTheReceiptForNonImportedPurchasedItems() {

		Item book = new Item("book", 1, Category.BOOK, new BigDecimal(12.49), false);
		Item music = new Item("music cd", 1, Category.OTHERS, new BigDecimal(14.99), false);
		Item chocolate = new Item("chocolate bar", 1, Category.FOOD, new BigDecimal(0.85), false);

		List<Item> purchasedItems = new ArrayList<Item>();
		purchasedItems.add(book);
		purchasedItems.add(music);
		purchasedItems.add(chocolate);
		Receipt receipt = new Receipt(purchasedItems);
		
		DecimalFormat decimalFormat = new DecimalFormat("##.00");
		assertEquals("1 book : 12.49", purchasedItems.get(0).quantity + " " + purchasedItems.get(0).name + " : " + decimalFormat.format(purchasedItems.get(0).getPrice()));
		assertEquals("1 music cd : 16.49", purchasedItems.get(1).quantity + " " + purchasedItems.get(1).name + " : " + decimalFormat.format(purchasedItems.get(1).getPrice()));
		assertEquals("1 chocolate bar : .85", purchasedItems.get(2).quantity + " " + purchasedItems.get(2).name + " : " + decimalFormat.format(purchasedItems.get(2).getPrice()));
		assertEquals(receipt.purchasedItems.size(), 3);
		assertEquals("1.50", decimalFormat.format(receipt.salesTax));
		assertEquals("29.83", decimalFormat.format(receipt.totalPrice));

	}


	@Test
	public void testShouldGenerateTheReceiptForImportedPurchasedItems() {

		Item chocolate = new Item("imported box of chocolates", 1, Category.FOOD, new BigDecimal(10.00), true);
		Item perfume = new Item("imported bottle of perfume", 1, Category.OTHERS, new BigDecimal(47.50), true);

		List<Item> purchasedItems = new ArrayList<Item>();
		purchasedItems.add(chocolate);
		purchasedItems.add(perfume);
		Receipt receipt = new Receipt(purchasedItems);
		
		DecimalFormat decimalFormat = new DecimalFormat("##.00");
		assertEquals("1 imported box of chocolates : 10.50", purchasedItems.get(0).quantity + " " + purchasedItems.get(0).name + " : " + decimalFormat.format(purchasedItems.get(0).getPrice()));
		assertEquals("1 imported bottle of perfume : 54.65", purchasedItems.get(0).quantity + " " + purchasedItems.get(1).name + " : " + decimalFormat.format(purchasedItems.get(1).getPrice()));
		assertEquals(receipt.purchasedItems.size(), 2);
		assertEquals("7.65", decimalFormat.format(receipt.salesTax));
		assertEquals("65.15", decimalFormat.format(receipt.totalPrice));

	}
}
