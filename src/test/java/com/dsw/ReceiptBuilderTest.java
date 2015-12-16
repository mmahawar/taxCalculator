package com.dsw;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dsw.model.Category;
import com.dsw.model.Item;
import com.dsw.model.PurchasedItem;
import com.dsw.model.Receipt;

public class ReceiptBuilderTest {
	
	private static final double importedTax = 0.05;
	private static final double salesTax = 0.10;

	@Test
	public void testShouldGenerateTheReceiptForNonImportedPurchasedItems() {

		Item book = new Item("book", 1, Category.BOOK, 12.49, false);
		Item music = new Item("music cd", 1, Category.OTHERS, 14.99, false);
		Item chocolate = new Item("chocolate bar", 1, Category.FOOD, 0.85, false);

		List<PurchasedItem> purchasedItems = new ArrayList<PurchasedItem>();
		purchasedItems.add(book.purchase(salesTax, importedTax));
		purchasedItems.add(music.purchase(salesTax, importedTax));
		purchasedItems.add(chocolate.purchase(salesTax, importedTax));
		Receipt receipt = new Receipt(purchasedItems);
				
		DecimalFormat decimalFormat = new DecimalFormat("##.00");
		assertEquals("1 book : 12.49", purchasedItems.get(0).getQuantity() + " " + purchasedItems.get(0).getName() + " : " + decimalFormat.format(purchasedItems.get(0).getPurchasedPrice()));
		assertEquals("1 music cd : 16.49", purchasedItems.get(1).getQuantity() + " " + purchasedItems.get(1).getName() + " : " + decimalFormat.format(purchasedItems.get(1).getPurchasedPrice()));
		assertEquals("1 chocolate bar : .85", purchasedItems.get(2).getQuantity() + " " + purchasedItems.get(2).getName() + " : " + decimalFormat.format(purchasedItems.get(2).getPurchasedPrice()));
		assertEquals(receipt.purchasedItems.size(), 3);
		assertEquals(1.50, receipt.salesTax,0.001);
		assertEquals(29.83, receipt.totalPrice, 0.001);

	}


	@Test
	public void testShouldGenerateTheReceiptForImportedPurchasedItems() {

		Item chocolate = new Item("imported box of chocolates", 1, Category.FOOD, 10.00, true);
		Item perfume = new Item("imported bottle of perfume", 1, Category.OTHERS, 47.50, true);

		List<PurchasedItem> purchasedItems = new ArrayList<PurchasedItem>();
		purchasedItems.add(chocolate.purchase(salesTax, importedTax));
		purchasedItems.add(perfume.purchase(salesTax, importedTax));
		Receipt receipt = new Receipt(purchasedItems);
		
		DecimalFormat decimalFormat = new DecimalFormat("##.00");
		assertEquals("1 imported box of chocolates : 10.50", purchasedItems.get(0).getQuantity() + " " + purchasedItems.get(0).getName() + " : " + decimalFormat.format(purchasedItems.get(0).getPurchasedPrice()));
		assertEquals("1 imported bottle of perfume : 54.65", purchasedItems.get(0).getQuantity() + " " + purchasedItems.get(1).getName() + " : " + decimalFormat.format(purchasedItems.get(1).getPurchasedPrice()));
		assertEquals(receipt.purchasedItems.size(), 2);
		assertEquals(7.65, receipt.salesTax, 0.001);
		assertEquals(65.15, receipt.totalPrice, 0.001);

	}
}
