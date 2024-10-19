package LLD.VendingMachine.States;

import LLD.VendingMachine.Inventory;
import LLD.VendingMachine.Item;
import LLD.VendingMachine.VendingMachine;

public class DispenseItemState extends VendingMachineState {
	
	public DispenseItemState( ) {
		
	}

	@Override
	public void dispenseRequiredItem(VendingMachine vendingMachine) throws Exception {
		
		// Dispense item
		Inventory inventory = vendingMachine.getInventory();
		Integer selectedItemCode = vendingMachine.getUserItemCode();
		Item selectedItem = inventory.getItem(selectedItemCode);
		
		System.out.println("Plese collect your item: "+ selectedItem.getItemType());
		
		// update inventory
		inventory.removeItem(selectedItemCode);
		
		// userCoins and Item code
		vendingMachine.clearUserCoins();
		vendingMachine.setUserItemCode(null);
		
		// back to IdleState
		vendingMachine.setCurrentState( new IdleState() );
	}

}
