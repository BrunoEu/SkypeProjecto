
public class Counter {

	private int counter;
	
	public Counter(){
		reset();
	}
	
	public void increment(){
		counter ++;
	}
	
	public int getCounter(){
		return counter;
	}
	
	public void reset(){
		counter = 0;
	}
	
}
