package LLD.VendingMachine.States;

import LLD.VendingMachine.VendingMachine;

public class IdleState extends VendingMachineState {
	@Override
	public void pressInsertCoinButton(VendingMachine vendingMachine) throws Exception {
		vendingMachine.setCurrentState( new AcceptCoinState() );
		System.out.println("The state of the vending machine is set to AcceptCoinState. You can inset coins now.");
		System.out.println("Please mind that the value of coin should exactly match the item price.");
	}
}
