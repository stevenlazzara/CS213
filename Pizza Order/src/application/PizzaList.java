package application;

import java.util.ArrayList;

public class PizzaList {
	public ArrayList<Pizza> list;
	private int pizzaAmount;

	/**
	 * Default constructor
	 */
	public PizzaList() {
	}

	/**
	 * used to start a Pizza list if there isn't one in it already
	 */
	private void makeNew() {
		if (list == null) {
			list = new ArrayList<Pizza>();
			pizzaAmount = 0;
		}
	}

	/**
	 * clears the pizza list
	 */
	public void clear() {
		list = null;
		pizzaAmount = 0;
	}

	/**
	 * Used to tell if the list is empty or not
	 * 
	 * @return true if empty, false if not empty
	 */
	public boolean isEmpty() {
		if (pizzaAmount == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Used to add Pizza
	 * 
	 * @param p The Pizza to be added
	 */
	public void add(Pizza p) {
		if (list == null) {
			makeNew();
		}
		list.add(p);
		pizzaAmount++;

	}

	/**
	 * Used to print the list of Pizza Format: style: size toppings price
	 */
	public String print() {
		int totalPrice = 0;
		String allPizzas = "";
		for (Pizza p : list) {
			allPizzas += p.toString() + "\n";
			totalPrice += p.pizzaPrice();
		}
		return String.format(allPizzas + "Total Price: %s", totalPrice);
	}

	/**
	 * testbed main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PizzaList mylist = new PizzaList();
		Pizza d = new Deluxe("Deluxe", "medium");
		Pizza h = new Hawaiian("Hawaiian", "medium");
		ArrayList<String> toppings = new ArrayList<String>();
		toppings.add("Sausage");
		toppings.add("Mushroom");
		toppings.add("Onion");
		Pizza b = new BuildYourOwn("Build your own", "medium", toppings);

		mylist.add(d);
		mylist.add(h);
		mylist.add(b);
		System.out.println(mylist.print());
		mylist.clear();
		mylist.add(d);
		System.out.println(mylist.print());
	}

}
