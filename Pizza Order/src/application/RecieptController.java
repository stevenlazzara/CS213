package application;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Second window controller, displays the pizzas in the order list and has the
 * ability to clear it
 * 
 * @author Steven Lazzara
 *
 */
public class RecieptController {
	PizzaList order = new PizzaList();
	@FXML
	private TextArea output;
	@FXML
	private Button clearButton;
	@FXML
	private Control backButton;

	public void getText(String string) {
		output.appendText(string);
	}

	@FXML
	/**
	 * Closes this window
	 * 
	 * @param event when back button is pressed
	 */
	public void backButtonPressed(Event event) {
		Stage orderStage = (Stage) backButton.getScene().getWindow();
		orderStage.close();
	}

	@FXML
	/**
	 * Clears the pizza order made
	 * 
	 * @param event when clear button is pressed
	 * @throws IOException
	 */
	public void clearButtonPressed(Event event) throws IOException {
		if (order.isEmpty()) {
			output.appendText("There is no order to remove!\n");
		} else {
			order.clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Store.fxml"));
			loader.load();
			StoreController storeController = loader.getController();
			storeController.getPizzaList(order);
			output.clear();
		}
	}

	/**
	 * gets Pizza List from Store Controller (first window)
	 * 
	 * @param p
	 */
	public void getPizzaList(PizzaList p) {
		order = p;
	}

}
