package com.dsw;

public enum Category {
	FOOD(true), MEDICINE(true), BOOK(true), OTHERS(false);

	private boolean isExempted;

	private Category(boolean isExempted) {
		this.isExempted = isExempted;
	}

	public boolean isExempt() {
		return isExempted;
	}
}
