package LLD.VendingMachine;

import java.util.ArrayList;

import LLD.VendingMachine.Enums.Coin;
import LLD.VendingMachine.States.IdleState;
import LLD.VendingMachine.States.VendingMachineState;

public class VendingMachine {
	private VendingMachineState currentState;
	private Inventory inventory;
	private ArrayList<Coin> userCoins;
	private Integer userItemCode;
	
	public VendingMachine( Inventory inventory ) {
		this.currentState = new IdleState();
		this.inventory = inventory;
		this.userCoins = null;
		this.userItemCode = null;
	}
	
	
	// USERCOINS RELATED METHODS: 
	// keeping these here else this data structure 
	// will be tightly coupled with the states which is bad
	public void addUserCoin( Coin coin ) {
		if( this.userCoins == null ) {
			this.userCoins = new ArrayList<Coin>();
		}
		this.userCoins.add(coin);
	}
	public boolean isUserCoinsEmpty() {
		if( this.userCoins == null || this.userCoins.isEmpty() == true )
			return true;
		return false;
	}
	public Integer getUserCoinsValue() {
		int total = 0;
		for(Coin coin: this.userCoins) {
			total += coin.value;
		}
		return total;
	}
	public void clearUserCoins() {
		if( this.userCoins != null )
			this.userCoins = null;
	}
	
	
	// GETTERS AND SETTERS
	public VendingMachineState getCurrentState() {
		return currentState;
	}
	public void setCurrentState(VendingMachineState currentState) {
		this.currentState = currentState;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
//	public ArrayList<Coin> getUserCoins() {
//		return userCoins;
//	}
//	public void setUserCoins(ArrayList<Coin> userCoins) {
//		this.userCoins = userCoins;
//	}
	public int getUserItemCode() {
		return userItemCode;
	}
	public void setUserItemCode(Integer userItemCode) {
		this.userItemCode = userItemCode;
	}
}
