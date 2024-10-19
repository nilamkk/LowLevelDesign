package LLD.VendingMachine.States;

import LLD.VendingMachine.VendingMachine;
import LLD.VendingMachine.Enums.Coin;

public abstract class VendingMachineState {
	public void pressInsertCoinButton(VendingMachine vendingMachine) throws Exception {
		System.out.println("Something went wrong!");
	}
	public void insertCoin(VendingMachine vendingMachine, Coin coin) throws Exception {
		System.out.println("Something went wrong!");
	}
	public void pressChooseProduct(VendingMachine vendingMachine) throws Exception {
		System.out.println("Something went wrong!");
	}
	public void pressCancelButton(VendingMachine vendingMachine) throws Exception {
		System.out.println("Something went wrong!");
	}
	public void selectItem(VendingMachine vendingMachine, Integer code) throws Exception {
		System.out.println("Something went wrong!");
	}
	public void pressGetProductButton(VendingMachine vendingMachine) throws Exception {
		System.out.println("Something went wrong!");
	}
	public void dispenseRequiredItem(VendingMachine vendingMachine) throws Exception {
		System.out.println("Something went wrong!");
	}
}
