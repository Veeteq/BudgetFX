package com.app.budget.model;

public class Item {

	private long itemId;
	private String itemName;

	public Item(){
	}
	
	public Item(long itemId, String itemName) {
		this.itemId = itemId;
		this.itemName = itemName;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + itemId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (this.itemId != other.itemId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getItemName();
	}
}
