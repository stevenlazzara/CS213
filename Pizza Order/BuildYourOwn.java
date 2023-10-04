package pizza;

/**
 * Class for Build-your-own style pizza instances and a subclass of Pizza.
 * Toppings are determined by the user's selection of choices.
 * Small set of key methods include constructor and calculation of price.
 * @author Dyllon Jeng, Steven Lazzara
 */
import java.util.ArrayList;

public class BuildYourOwn extends Pizza{
	
	final int SMALL_SIZE_FEE = 5;
	final int MEDIUM_SIZE_FEE = SMALL_SIZE_FEE + 2;
	final int LARGE_SIZE_FEE= SMALL_SIZE_FEE + 4;
	final int PRICE_PER_TOPPING = 2;

	public BuildYourOwn(String size) {
		super("Build-your-own", size);
	}
	
	/**
	 * Constructor with size and set of toppings as parameter.
	 * Size and toppings will differ for each instance.
	 * @param size of instance.
	 * @param toppings as an ArrayList of toppings choices 
	 */
	public BuildYourOwn(String size, ArrayList<String> toppings) {
		super("Build-your-own", size, toppings);
	}
	
	@Override
	/**
	 * Method for calculating price of a Build-your-own style pizza.
	 * Determines price based on size and selection of toppings.
	 * @return price of pizza ordered.
	 */
	public int pizzaPrice() {
		int numToppings = toppings.size();
		String size = this.size;
		int price = 0;
		
		switch(size) {
		case "Small":
			price = SMALL_SIZE_FEE + (numToppings*PRICE_PER_TOPPING);
			break;
		case "Medium":
			price = MEDIUM_SIZE_FEE + (numToppings*PRICE_PER_TOPPING);
			break;
			
		case "Large":
			price = LARGE_SIZE_FEE + (numToppings*PRICE_PER_TOPPING);
			break;
		}
		
		return price;
	}

}
