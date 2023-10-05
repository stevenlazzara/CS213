/**
 * 
 */
package application;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Steven Lazzara
 *
 */
public class BuildYourOwnTest {

	/**
	 * Test method for {@link application.BuildYourOwn#pizzaPrice()}.
	 */
	@Test
	public void testPizzaPrice() {
		ArrayList<String> toppingsA = new ArrayList<String>();
		toppingsA.add("Sausage");
		toppingsA.add("Mushroom");
		toppingsA.add("Onion");
		Pizza a = new BuildYourOwn("Build your own", "small", toppingsA);
		Pizza b = new BuildYourOwn("Build your own", "medium", toppingsA);
		Pizza c = new BuildYourOwn("Build your own", "large", toppingsA);
		assertEquals(11, a.pizzaPrice());
		assertEquals(13, b.pizzaPrice());
		assertEquals(15, c.pizzaPrice());
		ArrayList<String> toppingsB = new ArrayList<String>();
		toppingsB.add("Pineapple");
		Pizza d = new BuildYourOwn("Build your own", "small", toppingsB);
		Pizza e = new BuildYourOwn("Build your own", "medium", toppingsB);
		Pizza f = new BuildYourOwn("Build your own", "large", toppingsB);
		assertEquals(7, d.pizzaPrice());
		assertEquals(9, e.pizzaPrice());
		assertEquals(11, f.pizzaPrice());
	}

}
