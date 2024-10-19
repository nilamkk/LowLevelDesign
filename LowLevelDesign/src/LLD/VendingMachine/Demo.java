package LLD.VendingMachine;

import LLD.VendingMachine.Enums.Coin;
import LLD.VendingMachine.Enums.ItemType;

public class Demo {
	
	public static void main(String[] args) {
		
		// Create the inventory
		Inventory inventory = new Inventory( 5, 101);
		// Fill the inventory with items
		ItemType items [] = { ItemType.AMUL_COOL, ItemType.COKE, ItemType.KURKURE, ItemType.UNCLE_CHIPS, ItemType.FIVE_STAR };
		Integer codeStart = 101;
		for(ItemType itemType: items) {
			inventory.addItem( new Item(itemType, 100) , codeStart++);
		}
		
		// Setup the vending machine
		VendingMachine vendingMachine = new VendingMachine( inventory );
		
		try {
			// *******************************************************************************************
			// Coin inserted 100 and item asked AMUL_COOL
			// insertCoin
			vendingMachine.getCurrentState().pressInsertCoinButton(vendingMachine);
			
			// insert coins
			int numCoins = 10; 
			Coin coin = Coin.TEN;
			for(int i=0; i< numCoins; i++) {
				vendingMachine.getCurrentState().insertCoin(vendingMachine, coin);
			}
			
			// press choose product button
			vendingMachine.getCurrentState().pressChooseProduct(vendingMachine);
			
			// choose item
			vendingMachine.getCurrentState().selectItem(vendingMachine, 101);
			
			// press get product
			vendingMachine.getCurrentState().pressGetProductButton(vendingMachine);
			
			// *******************************************************************************************
			// ask Amul cool again
			// insertCoin
			vendingMachine.getCurrentState().pressInsertCoinButton(vendingMachine);
			
			// insert coins
			numCoins = 10; 
			coin = Coin.TEN;
			for(int i=0; i< numCoins; i++) {
				vendingMachine.getCurrentState().insertCoin(vendingMachine, coin);
			}
			
			// press choose product button
			vendingMachine.getCurrentState().pressChooseProduct(vendingMachine);
			
			// choose item
			vendingMachine.getCurrentState().selectItem(vendingMachine, 101);
			
			// press get product
			vendingMachine.getCurrentState().pressGetProductButton(vendingMachine);
			
			
		}catch( Exception e ) {
			
		}

		
		
	}

}
