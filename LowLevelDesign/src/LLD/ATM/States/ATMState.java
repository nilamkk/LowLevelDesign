package LLD.ATM.States;

import LLD.ATM.ATM;
import LLD.ATM.Card;
import LLD.ATM.Enums.TransactionType;

public abstract class ATMState {
	public void insertCard(ATM atm, Card card) {
		System.out.println("Something went wrong!");
	}
	public void authenticatePin(ATM atm, Card card, int pin) {
		System.out.println("Something went wrong!");
	}
	public void setSelectedTransaction(ATM atm, Card card, TransactionType transType) {
		System.out.println("Something went wrong!");
	}
	public void withdrawCash(ATM atm, Card card, int withdrawAmount) {
		System.out.println("Something went wrong!");
	}
	public void displayBalance(ATM atm, Card card) {
		System.out.println("Something went wrong!");
	}
	public void returnCard() {
		System.out.println("Something went wrong!");
	}
	public void exit(ATM atm) {
		System.out.println("Something went wrong!");
	}
}
