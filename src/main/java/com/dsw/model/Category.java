package com.dsw.model;

public enum Category {
	FOOD, MEDICINE, BOOK, OTHERS;

	public static boolean isExempt(Category category) {
		switch (category) {
		case FOOD:
		case MEDICINE:
		case BOOK:
			return true;
		default:
			return false;
		}
	}
}
