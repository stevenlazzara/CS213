package pizza; 

/**
 * Class representing Deluxe pizza instances, defined as a subclass of Pizza.
 * Default set of toppings include sausage, pepperoni, green pepper, onion, and mushroom.
 * Small set of key methods include constructor and calculation of price.  
 * @author Dyllon Jeng, Steven Lazzara
 */
import java.util.ArrayList;


public class Deluxe extends Pizza {

	final int SMALL_SIZE_FEE = 14;
	final int MED_SIZE_FEE = SMALL_SIZE_FEE + 2;
	final int LARGE_SIZE_FEE = SMALL_SIZE_FEE + 4;
	
	/**
	 * Constructor with just size as a parameter.
	 * Style is by default the same for each Deluxe pizza.
	 * Default set of toppings defined as well.
	 * @param size of instance
	 */
	public Deluxe(String size) {
		super("Deluxe", size);
		ArrayList<String> toppings = new ArrayList<String>();
		toppings.add("Sausage");
		toppings.add("Pepperoni");
		toppings.add("Green Pepper");
		toppings.add("Onion");
		toppings.add("Mushroom");
		this.toppings=toppings;
	}
	
	@Override
	/**
	 * Calculates the price of a Deluxe pizza
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
