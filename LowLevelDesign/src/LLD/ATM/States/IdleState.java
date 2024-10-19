package LLD.ATM.States;

import LLD.ATM.ATM;
import LLD.ATM.Card;

public class IdleState extends ATMState{

	@Override
	public void insertCard(ATM atm, Card card) {
		System.out.println("The card is accepted!");
		atm.setCurrentState( new HasCardState() );
	}
	
}
