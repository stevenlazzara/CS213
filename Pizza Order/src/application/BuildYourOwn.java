package application;

import java.util.ArrayList;

/**
 * Build Your Own Pizza Subclass
 * 
 * @author Jonathan Li, Jihun Joo, Bufan Jiang
 */
public class BuildYourOwn extends Pizza {

	private int smallPrice = 5;
	private int mediumPrice = smallPrice + 2;
	private int largePrice = smallPrice + 4;
	private int toppingPrice = 2;

	public BuildYourOwn(String style, String size, ArrayList<String> toppings) {
		super(style, size, toppings);

	}

	/**
	 * returns number of toppings
	 * 
	 * @param toppings an array list of toppings
	 * @return number of toppings
	 */
	private int toppingNum(ArrayList<String> toppings) {
		return toppings.size();

	}

	/**
	 * returns the price of pizza
	 */
	public int pizzaPrice() {
		int toppingTotal = toppingNum(toppings) * toppingPrice;
		if (size.equals("small")) {
			return smallPrice + toppingTotal;
		} else if (size.equals("medium")) {
			return mediumPrice + toppingTotal;
		} else if (size.equals("large")) {
			return largePrice + toppingTotal;
		}
		return 0;
	}

	@Override
	/**
	 * outputs a string of all the pizza ordered
	 */
	public String toString() {
		String topping = new String();

		for (int i = 0; i < toppings.size(); i++) {
			topping = topping.concat(toppings.get(i) + "\n");

		}

		return super.toString() + topping + "$" + pizzaPrice() + "\n";
	}

	/**
	 * test bed main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> toppings = new ArrayList<String>();
		toppings.add("Sausage");
		toppings.add("Mushroom");
		toppings.add("Onion");
		Pizza h = new BuildYourOwn("Build your own", "medium", toppings);
		System.out.println(h.toString());
		System.out.println();
	}

}
