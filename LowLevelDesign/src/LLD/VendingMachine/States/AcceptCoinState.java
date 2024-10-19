package LLD.VendingMachine.States;

import LLD.VendingMachine.VendingMachine;
import LLD.VendingMachine.Enums.Coin;

public class AcceptCoinState extends VendingMachineState {

	@Override
	public void insertCoin(VendingMachine vendingMachine, Coin coin) throws Exception {
		vendingMachine.addUserCoin(coin);
		System.out.println("Coin inserted with value "+ coin.value);
	}

	@Override
	public void pressChooseProduct(VendingMachine vendingMachine) throws Exception {
		// check if at least one coin is entered
		if( vendingMachine.isUserCoinsEmpty() ) {
			System.out.println("Insert coin first.");
			return;
		}
		// get him to ItemSelectionState
		vendingMachine.setCurrentState( new ItemSelectionState() );
		System.out.println("The state of the vending machine is set to ItemSelectionState. Please choose one item by entering code.");
	}

	@Override
	public void pressCancelButton(VendingMachine vendingMachine) throws Exception {
		// refund
		this.provideRefund(vendingMachine);
		// get him to IdleState
		vendingMachine.setCurrentState( new IdleState() );
		System.out.println("Taking you back to the IdleState.");
	}
	
	private void provideRefund( VendingMachine vendingMachine ) {
		int refund = vendingMachine.getUserCoinsValue();
		vendingMachine.clearUserCoins();
		// provideRefund
		System.out.println("Please collect your refund of "+ refund +".");
	}

}
