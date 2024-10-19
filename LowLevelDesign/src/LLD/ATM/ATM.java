package LLD.ATM;

import java.util.HashMap;
import LLD.ATM.States.ATMState;
import LLD.ATM.Enums.Denomination;

public class ATM {
	private ATMState currentAtmState;
	private HashMap<Denomination, Integer> availableCash;
	
	public void setCurrentState(ATMState state) {
		this.currentAtmState = state;
	}
	
	
}
