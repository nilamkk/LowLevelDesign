package LLD.OddEven.Thread;


class State{
	int currentNumber;
	int maxNumber;
	
	public State(int curNum, int maxNum) {
		this.currentNumber = curNum;
		this.maxNumber = maxNum;
	}
}

class EvenPrinter implements Runnable{

	State state;
	
	public EvenPrinter(State state){
		this.state = state;
	}
	
	@Override
	public void run() {
		try {
			while( true) {
				synchronized ( this.state ) {
					while( (this.state.currentNumber & 1) == 1 && this.state.currentNumber <= this.state.maxNumber ) {
						this.state.wait();
					}
					if( this.state.currentNumber <= this.state.maxNumber) {
    					System.out.println( "Even Thread -> " +this.state.currentNumber );
    					this.state.currentNumber++;	
					    this.state.notify();
					}else{
					    this.state.notify();
					    break;
					}
				}	
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Even thread done and dusted!");
	}
}

class OddPrinter implements Runnable{

	State state;
	
	public OddPrinter(State state){
		this.state = state;
	}
	
	@Override
	public void run() {
		try {
			while( true) {
				synchronized ( this.state ) {
					while( (this.state.currentNumber & 1) == 0 && this.state.currentNumber <= this.state.maxNumber ) {
						this.state.wait();
					}
					if( this.state.currentNumber <= this.state.maxNumber) {
    					System.out.println( "Odd Thread -> " +this.state.currentNumber );
    					this.state.currentNumber++;	
					    this.state.notify();
					}else{
					    this.state.notify();
					    break;
					}
				}	
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Odd thread done and dusted!");
	}
}

public class Demo {

	public static void main(String[] args) {
		State state = new State( 0, 10 );
		
		EvenPrinter evenPrinter = new EvenPrinter(state);
		OddPrinter oddPrinter = new OddPrinter(state);
		
		Thread evenPrintThread = new Thread( evenPrinter );
		Thread oddPrintThread = new Thread( oddPrinter );
		
		evenPrintThread.start();
		oddPrintThread.start();
		
	}

}
