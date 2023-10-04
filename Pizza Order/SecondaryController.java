package application;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import pizza.*;

public class SecondaryController implements Initializable{
	
	@FXML
	Button backButton;
	
	@FXML
	Button clearOrderButton;
	
	@FXML
	TextArea orderDisplay;
	
	@FXML
	ObservableList<Pizza> Order;
	
	
	String orderDetails;
	
	PrimaryController refToPrimaryController;
	
	int total;
	
	@FXML
	/**
	 * Called when "Clear order" button is pressed.
	 * Clears the order of all pizzas that were previously added.
	 * ArrayList of pizzas used for the first window is emptied. 
	 * Display for the second window is cleared as well. 
	 * @param event
	 */
	void clearOrder(ActionEvent event) {
		refToPrimaryController.order.clear();
		Order.clear();
		total=0;
		orderDisplay.setText("Current order:\n\nTOTAL:\t\t\t\t$" + total);
	}
	
	/**
	 * Called when "Back" button is pressed.
	 * Simply closes the second window for the user to return to the first window.
	 * @param event
	 */
	@FXML
	void goBack(ActionEvent event) {
		Stage window = (Stage)backButton.getScene().getWindow();
		window.close();
	}
	
	/**
	 * Retrieves reference to the primary controller, which will be used to call fields and methods in said controller.
	 * Called in the primary controller when it begins to load and show the second window.
	 * @param controllerRef
	 */
	void getController(PrimaryController controllerRef) {
		refToPrimaryController = controllerRef;
	}
	
	/**
	 * Retrieves order information gathered from the first window.
	 * Called in the primary controller when it begins to load and show the second window.
	 * Initializes the text for the order display as well.
	 * @param order
	 */
	void enterOrder(ObservableList<Pizza> order) {
		Order=order;
		listOrderDetails();
	}
	
	/**
	 * Formats the display of the order.
	 * Outputs the display onto the TextArea used for listing the order details.
	 * Called when the window is being initialized and loaded.
	 */
	void listOrderDetails() {
		orderDetails="Current order:\n";
		for(Pizza element: Order) {
			orderDetails+=element.toString();
			total+=element.pizzaPrice();
		}
		orderDetails+="\nTOTAL:\t\t\t\t$" + total;
		orderDisplay.setText(orderDetails);
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		total=0;
		
	}
	
}
