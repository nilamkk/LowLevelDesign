package LLD.ATM.States;

import LLD.ATM.ATM;
import LLD.ATM.Card;

public class HasCardState extends ATMState{

	@Override
	public void authenticatePin(ATM atm, Card card, int pin) {
	}

	@Override
	public void returnCard() {
	}

	@Override
	public void exit(ATM atm) {
	}
	
}
