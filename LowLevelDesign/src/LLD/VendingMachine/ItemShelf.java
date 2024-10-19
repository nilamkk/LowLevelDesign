package LLD.VendingMachine;

public class ItemShelf {
	private Item item;
	private Integer code;
	private boolean isAvailable;
	
	public ItemShelf(Integer code) {
		this.code = code;
		this.isAvailable = false;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
}
