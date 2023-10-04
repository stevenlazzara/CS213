package application;

import java.util.ArrayList;

/**
 * Hawaiian Pizza Subclass
 * 
 * @author Jonathan Li, Jihun Joo, Bufan Jiang
 *
 */
public class Hawaiian extends Pizza {
	private int smallPrice = 8;
	private int mediumPrice = smallPrice + 2;
	private int largePrice = smallPrice + 4;
	private ArrayList<String> toppings = new ArrayList<String>();

	public Hawaiian(String style, String size) {
		super(style, size);
		this.toppings.add("Ham");
		this.toppings.add("Pineapple");

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
		Pizza h = new Hawaiian("Hawaiian", "medium");
		System.out.println(h.toString());
	}
}
