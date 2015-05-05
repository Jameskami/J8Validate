package java8.validation;

import java.util.Random;

public class Snake {
	private boolean isVenomous;
	private boolean isConstrictor;
	private int length;
	
	public boolean isVenomous() {
		return isVenomous;
	}
	
	public boolean isConstrictor() {
		return isConstrictor;
	}
	
	public int getLength() {
		return length;
	}
	
	public Snake() {
		Random rand = new Random();
		length = rand.nextInt(12);
		isVenomous = rand.nextInt(10) > 5? true : false;
		isConstrictor = !isVenomous && rand.nextInt(10) > 5? true : false;
	}
}
