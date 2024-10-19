package LLD.VendingMachine;

public class Inventory {
	private ItemShelf itemShelves []; // this itemShelves is a reference variable for the array
	
	public Inventory(int size, Integer codeStart) {
		itemShelves = new ItemShelf[size]; // Here we are just allocating space but not actually creating objects at every place.
										   // All space will be occupied by null and will have to create actual objects manually
		for(int i=0; i< size; i++) {
			itemShelves[i] = new ItemShelf(codeStart++); 
		}
	}
	
	public void addItem( Item item, Integer code ) {
		for( ItemShelf itemShelf:  itemShelves) {
			if( itemShelf.getCode() == code ) {
				itemShelf.setItem( item );
				itemShelf.setAvailable(true);
			}
		}
	}
	
	public void removeItem( Integer code ) {
		for( ItemShelf itemShelf:  itemShelves) {
			if( itemShelf.getCode() == code ) {
				itemShelf.setItem(null);
				itemShelf.setAvailable(false);
			}
		}
	}
	
	public Item getItem( Integer code ) {
		for( ItemShelf itemShelf:  itemShelves) {
			if( itemShelf.getCode() == code ) {
				return itemShelf.getItem();
			}
		}
		return null;
	}
}
