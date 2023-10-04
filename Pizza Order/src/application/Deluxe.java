package application;

import java.util.ArrayList;

/**
 * Deluxe Pizza Subclass
 * 
 * @author Jonathan Li, Jihun Joo, Bufan Jiang
 *
 */
public class Deluxe extends Pizza {
	private int smallPrice = 14;
	private int mediumPrice = smallPrice + 2;
	private int largePrice = smallPrice + 4;
	private ArrayList<String> toppings = new ArrayList<String>();

	public Deluxe(String style, String size) {
		super(style, size);
		this.toppings.add("Sausage");
		this.toppings.add("Pepperoni");
		this.toppings.add("Green Pepper");
		this.toppings.add("Onion");
		this.toppings.add("Mushroom");
	}

	/**
	 * returns the price of pizza
	 */
	public int pizzaPrice() {
		if (size.equals("small")) {
			return smallPrice;
		} else if (size.equals("medium")) {
			return mediumPrice;
		} else if (size.equals("large")) {
			return largePrice;
		}
		return 0;
	}

	@Override
	/**
	 * outputs a string of all the pizza ordered
	 */
	public String toString() {
		String topping = new String();

		for (String s : toppings) {
			topping = topping.concat(s + "\n");

		}

		return super.toString() + topping + "$" + pizzaPrice() + "\n";
	}

	/**
	 * test bed main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Pizza d = new Deluxe("Deluxe", "medium");
		System.out.println(d.toString());
	}
}
