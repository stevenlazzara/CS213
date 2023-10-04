package pizza;

/**
 * Class representing all Hawaiian pizza instances, defined as a subclass of Pizza.
 * Default set of toppings is ham and pineapple. 
 * Only key methods include determining price of an Hawaiian instance and constructor. 
 * @author Dyllon Jeng, Steven Lazzara
 */

import java.util.ArrayList;

public class Hawaiian extends Pizza {
	
	final int SMALL_SIZE_FEE = 8;
	final int MED_SIZE_FEE = SMALL_SIZE_FEE+2;	//10
	final int LARGE_SIZE_FEE = SMALL_SIZE_FEE+4;	//14

	
	/**
	 * Constructor with just size as a parameter.
	 * Style is by default the same for each instance.
	 * Default set of toppings initialized as well.
	 * @param size of instance
	 */
	public Hawaiian(String size) {
		super("Hawaiian", size);
		ArrayList<String> toppings = new ArrayList<String>();
		toppings.add("Ham");
		toppings.add("Pineapple");
		this.toppings=toppings;
	}
	
	@Override
	/**
	 * Determines the price of a Hawaiian pizza 
	 * Price is calculated as a factor of only size.
	 * @return price of instance
	 */
	public int pizzaPrice() {
		int price = 0;
		switch(size) {
		case "Small":
			price = SMALL_SIZE_FEE;
			break;
		case "Medium":	
			price = MED_SIZE_FEE;
			break;
		case "Large":
			price = LARGE_SIZE_FEE;
			break;
		}
	    
	    return price;
	}

}
