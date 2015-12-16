package com.dsw;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.dsw.model.TaxCalculator;

public class TaxCalculatorTest {

	@Test
	public void testRounding() {
		assertEquals(1.50, TaxCalculator.roundSalesTaxToNearestHalf(1.49), 0.001);
		assertEquals(7.65, TaxCalculator.roundSalesTaxToNearestHalf(7.625), 0.001);
		assertEquals(11.85, TaxCalculator.roundSalesTaxToNearestHalf(11.8125), 0.001);
	}
}
