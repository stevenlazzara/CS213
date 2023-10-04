package pizza;

import java.util.ArrayList;

/**
 * Abstract class for pizza objects. Template for the three
 * types of pizzas that are represented as subclasses.
 * @author Dyllon Jeng, Steven Lazarra
 *
 */
public abstract class Pizza {
	protected String style;
	protected String size;
	protected ArrayList<String> toppings;

	/**
	 * Constructor with style, size, and toppings entered as parameter.
	 * Primarily used for pizza instances that to manually instantiate a set of toppings. 
	 * @param style selection for type of pizza
	 * @param size selection for three different sizes
	 * @param toppings ArrayList of topping choices for pizza
	 */
	public Pizza(String style, String size, ArrayList<String> toppings) {
		this.style=style;
		this.size=size;
		this.toppings=toppings;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor with style and size entere as parameter.
	 * Primarily used for pizza with default sets of toppings.
	 * @param style
	 * @param size
	 */
	public Pizza(String style, String size) {
		this.style=style;
		this.size=size;
	}
	
	/**
	 * Abstract method for accessing prices of pizza instances.
	 * Calculation differs for different styles of pizza.
	 * @return price of each pizza instance
	 */
	public abstract int pizzaPrice();
	
	/**
	 * String representation of pizza instances.
	 * Will primarily be used for display purposes when listing 
	 * the details of an order.
	 */
	public String toString() {
		String details="";
		details+="1 " + style+", " + size+ ":\t\t$" + pizzaPrice()+ "\n";
		
		for(String element: toppings)
			details+=element+"\n";
		
		details+="\n";
		
		return details;
	}

}
