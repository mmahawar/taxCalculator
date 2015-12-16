package com.dsw;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ReceiptBuilderTest {
	//Can be read from anywhere i.e. database, property file etc
	private static final double importedTax = 0.05;
	private static final double salesTax = 0.10;

	@Test
	public void testShouldGenerateTheReceiptForNonImportedPurchasedItems() {

		Item book = new Item("book", Category.BOOK, 12.49, false);
		Item music = new Item("music cd", Category.OTHERS, 14.99, false);
		Item chocolate = new Item("chocolate bar", Category.FOOD, 0.85, false);

		List<PurchasedItem> purchasedItems = new ArrayList<PurchasedItem>();
		purchasedItems.add(book.purchaseAndMaybeApplyTax(salesTax, importedTax, 1));
		purchasedItems.add(music.purchaseAndMaybeApplyTax(salesTax, importedTax, 1));
		purchasedItems.add(chocolate.purchaseAndMaybeApplyTax(salesTax, importedTax, 1));
		Receipt receipt = new Receipt(purchasedItems);
		List<String> printReceipt = receipt.printReceipt();
				
		assertEquals(printReceipt.size(), 5);
		assertEquals("1 book : 12.49", printReceipt.get(0));
		assertEquals("1 music cd : 16.49", printReceipt.get(1));
		assertEquals("1 chocolate bar : .85", printReceipt.get(2));
		assertEquals("Sales Taxes: 1.50", printReceipt.get(3));
		assertEquals("Total: 29.83", printReceipt.get(4));
	}

	@Test
	public void testShouldGenerateTheReceiptForImportedPurchasedItems() {

		Item chocolate = new Item("imported box of chocolates", Category.FOOD, 10.00, true);
		Item perfume = new Item("imported bottle of perfume", Category.OTHERS, 47.50, true);

		List<PurchasedItem> purchasedItems = new ArrayList<PurchasedItem>();
		purchasedItems.add(chocolate.purchaseAndMaybeApplyTax(salesTax, importedTax, 1));
		purchasedItems.add(perfume.purchaseAndMaybeApplyTax(salesTax, importedTax, 1));
		Receipt receipt = new Receipt(purchasedItems);
		
		List<String> printReceipt = receipt.printReceipt();
		
		assertEquals(printReceipt.size(), 4);
		assertEquals("1 imported box of chocolates : 10.50", printReceipt.get(0));
		assertEquals("1 imported bottle of perfume : 54.65", printReceipt.get(1));
		assertEquals("Sales Taxes: 7.65", printReceipt.get(2));
		assertEquals("Total: 65.15", printReceipt.get(3));

	}

	@Test
	public void testShouldGenerateTheReceiptForImportedAndNonImportedPurchasedItems() {
		
		Item importedPerfume = new Item("imported bottle of perfume", Category.OTHERS, 27.99, true);
		Item perfume = new Item("bottle of perfume", Category.OTHERS, 18.99, false);
		Item medicine = new Item("packet of headache pills",  Category.MEDICINE, 9.75, false);
		Item chocolate = new Item("box of imported chocolates", Category.FOOD, 11.25, true);
		
		List<PurchasedItem> purchasedItems = new ArrayList<PurchasedItem>();
		purchasedItems.add(importedPerfume.purchaseAndMaybeApplyTax(salesTax, importedTax, 1));
		purchasedItems.add(perfume.purchaseAndMaybeApplyTax(salesTax, importedTax, 1));
		purchasedItems.add(medicine.purchaseAndMaybeApplyTax(salesTax, importedTax, 1));
		purchasedItems.add(chocolate.purchaseAndMaybeApplyTax(salesTax, importedTax, 1));
		Receipt receipt = new Receipt(purchasedItems);
		
		List<String> printReceipt = receipt.printReceipt();
		
		assertEquals(printReceipt.size(), 6);
		assertEquals("1 imported bottle of perfume : 32.19", printReceipt.get(0));
		assertEquals("1 bottle of perfume : 20.89", printReceipt.get(1));
		assertEquals("1 packet of headache pills : 9.75", printReceipt.get(2));
		assertEquals("1 box of imported chocolates : 11.85", printReceipt.get(3));
		assertEquals("Sales Taxes: 6.70", printReceipt.get(4));
		assertEquals("Total: 74.68", printReceipt.get(5));
		
	}
}
