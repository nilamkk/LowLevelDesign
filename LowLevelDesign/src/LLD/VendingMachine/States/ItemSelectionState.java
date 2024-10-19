package LLD.VendingMachine.States;

import LLD.VendingMachine.Inventory;
import LLD.VendingMachine.Item;
import LLD.VendingMachine.VendingMachine;

public class ItemSelectionState extends VendingMachineState {

	@Override
	public void pressCancelButton(VendingMachine vendingMachine) throws Exception {
		// refund
		provideRefund(vendingMachine);
		// clear select item code
		vendingMachine.setUserItemCode(null);
		// get him to IdleState
		vendingMachine.setCurrentState( new IdleState() );
	}

	@Override
	public void selectItem(VendingMachine vendingMachine, Integer code) throws Exception {
		vendingMachine.setUserItemCode(code);
		System.out.println("You have selected item with code "+ code);
	}

	@Override
	public void pressGetProductButton(VendingMachine vendingMachine) throws Exception {
		// check if item is available
		Integer selectedItemCode = vendingMachine.getUserItemCode();
		Inventory inventory = vendingMachine.getInventory();
		Item selectedItem = inventory.getItem(selectedItemCode);
		
		if( selectedItem == null ) {
			System.out.println("Selected item is not available. Please try again!");
			return;
		}
		
		// check if selected item's cost == total coin value
		Integer totalValueOfCoins = vendingMachine.getUserCoinsValue();
		
		if( totalValueOfCoins != selectedItem.getPrice() ) {
			System.out.println("Value of coin should be exactly equal to price of selected item.");
			return;
		}
		
		// get him to DispenseItemState
		vendingMachine.setCurrentState( new DispenseItemState( ) );
		
		// call that method
		try {
			vendingMachine.getCurrentState().dispenseRequiredItem( vendingMachine );			
		}catch(Exception e) {
			
		}
	}
	
	private void provideRefund( VendingMachine vendingMachine ) {
		int refund = vendingMachine.getUserCoinsValue();
		vendingMachine.clearUserCoins();
		// provideRefund
		System.out.println("Please collect your refund of "+ refund +".");
	}
	
}
