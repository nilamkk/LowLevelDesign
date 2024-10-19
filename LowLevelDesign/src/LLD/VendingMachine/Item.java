package LLD.VendingMachine;

import LLD.VendingMachine.Enums.ItemType;

public class Item {
	private ItemType itemType;
	private int price;
	
	public Item(ItemType itemType, int price) {
		this.itemType = itemType;
		this.price = price;
	}
	
	public ItemType getItemType() {
		return itemType;
	}
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
