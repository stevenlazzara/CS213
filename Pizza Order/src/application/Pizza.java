package application;

import java.util.ArrayList;

/**
 * Pizza superclass
 * 
 * @author Steven Lazzara
 */
public abstract class Pizza {
	protected String style;
	protected String size;
	protected ArrayList<String> toppings;

	/**
	 * Pizza constructor
	 * 
	 * @param style    style of the pizza
	 * @param size     size of the pizza
	 * @param toppings toppings for the pizza
	 */
	public Pizza(String style, String size, ArrayList<String> toppings) {
		this.style = style;
		this.size = size;
		this.toppings = toppings;
	}

	/**
	 * Pizza constructor
	 * 
	 * @param style style of the pizza
	 * @param size  size of the pizza
	 */
	public Pizza(String style, String size) {
		this.style = style;
		this.size = size;
	}

	/**
	 * abstract pizza price method
	 * 
	 * @return price of pizza
	 */
	public abstract int pizzaPrice();

	public String toString() {

		return String.format(style + ": " + size + "\n" + "Toppings: \n");
	}

}
