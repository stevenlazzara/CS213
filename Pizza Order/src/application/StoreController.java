package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.Event;

/**
 * The first and main menu of pizza ordering
 * 
 * @author Steven Lazzara
 *
 */
public class StoreController implements Initializable {
	PizzaList order = new PizzaList();
	Image custom = new Image("img/custom.jpg");
	Image hawaiian = new Image("img/hawaiian.jpg");
	Image deluxe = new Image("img/deluxe.jpg");
	@FXML
	private ComboBox<String> pizzaCombo;
	@FXML
	private ComboBox<String> sizes;
	@FXML
	private Button addTopping;
	@FXML
	private Button removeTopping;
	@FXML
	private Button clear;
	@FXML
	private Button addOrder;
	@FXML
	private Button showOrder;
	@FXML
	private ListView<String> toppings;
	@FXML
	private ListView<String> selectedToppings;
	@FXML
	private TextArea messages;
	@FXML
	private ImageView image;

	@Override
	/**
	 * initializes the pizza ordering window
	 */
	public void initialize(URL url, ResourceBundle rb) {
		image.setImage(custom);
		sizes.getItems().addAll("small", "medium", "large");
		sizes.getSelectionModel().select("medium");
		pizzaCombo.getItems().addAll("Deluxe", "Hawaiian", "Build your own");
		pizzaCombo.getSelectionModel().select("Build your own");
		toppings.getItems().addAll("Beef", "Cheese", "Chicken", "Green Pepper", "Ham", "Mushroom", "Onion", "Pepperoni",
				"Pineapple", "Sausage");
		toppings.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		selectedToppings.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		addOrder.setDisable(true);
		pizzaCombo.getSelectionModel().selectedItemProperty()
				.addListener((v, oldValue, newValue) -> pizzaCombobox(newValue));

	}

	@FXML
	/**
	 * clears the current selection of pizza and its toppings
	 * 
	 * @param event
	 */
	public void clearButtonPressed(Event event) {
		clearToDefault();

	}

	/**
	 * method that resets the window back to its default state, where no toppings is
	 * chosen and pizza selected is DIY, and size is medium
	 */
	public void clearToDefault() {
		sizes.getSelectionModel().select("medium");
		pizzaCombo.getSelectionModel().select("Build your own");
		clearToppings();
		toppings.getItems().addAll("Beef", "Cheese", "Chicken", "Green Pepper", "Ham", "Mushroom", "Onion", "Pepperoni",
				"Pineapple", "Sausage");
		addTopping.setDisable(false);
		removeTopping.setDisable(false);
		addOrder.setDisable(true);
	}

	/**
	 * changes what buttons/toppings the user can select when a type of pizza is
	 * selected
	 * 
	 * @param type type of pizza
	 */
	public void pizzaCombobox(String type) {
		if (type.equals("Build your own")) {
			image.setImage(custom);
			clearToppings();
			toppings.getItems().addAll("Beef", "Cheese", "Chicken", "Green Pepper", "Ham", "Mushroom", "Onion",
					"Pepperoni", "Pineapple", "Sausage");
			addTopping.setDisable(false);
			removeTopping.setDisable(false);
			addOrder.setDisable(true);

		} else if (type.equals("Hawaiian")) {
			image.setImage(hawaiian);
			clearToppings();
			selectedToppings.getItems().addAll("Ham", "Pineapple");
			addTopping.setDisable(true);
			removeTopping.setDisable(true);
			addOrder.setDisable(false);

		} else {
			image.setImage(deluxe);
			clearToppings();
			selectedToppings.getItems().addAll("Green Pepper", "Mushroom", "Onion", "Pepperoni", "Sausage");
			addTopping.setDisable(true);
			removeTopping.setDisable(true);
			addOrder.setDisable(false);
		}
	}

	@FXML
	/**
	 * adds topping
	 * 
	 * @param event when the add toppings button is pressed
	 */
	public void addToppingPressed(Event event) {
		if (!toppings.getSelectionModel().isEmpty()) {
			String topping = toppings.getSelectionModel().getSelectedItem();
			selectedToppings.getItems().add(topping);
			toppings.getItems().remove(topping);

		}
		int toppingAmount = selectedToppings.getItems().size();
		if (toppingAmount < 1 || toppingAmount>6) {
			addOrder.setDisable(true);
		}else if(toppingAmount>5) {
			addTopping.setDisable(true);
		}else {
			addOrder.setDisable(false);
			addTopping.setDisable(false);
		}
	}

	@FXML
	/**
	 * removes topping
	 * 
	 * @param event when the remove toppings button is pressed
	 */
	public void removeToppingPressed(Event event) {
		if (!selectedToppings.getSelectionModel().isEmpty()) {
			String topping = selectedToppings.getSelectionModel().getSelectedItem();
			selectedToppings.getItems().remove(topping);
			toppings.getItems().add(topping);

		}
		int toppingAmount = selectedToppings.getItems().size();
		if (toppingAmount > 0 && toppingAmount < 7) {
			addOrder.setDisable(false);
			addTopping.setDisable(false);
		} else {
			addOrder.setDisable(true);
		}
	}

	@FXML
	/**
	 * adds pizza to order list
	 * 
	 * @param event when the add order button is pressed
	 */
	public void addOrderPressed(Event event) {
		String type = pizzaCombo.getValue();
		String size = sizes.getValue();
		if (type.equals("Build your own")) {
			ObservableList<String> selected = selectedToppings.getItems();
			ArrayList<String> toppings = new ArrayList<String>();
			for (Object item : selected) {
				toppings.add(String.format("%s", (String) item));
			}
			Pizza p = new BuildYourOwn("Build your own", size, toppings);
			order.add(p);

		} else if (type.equals("Hawaiian")) {
			Pizza p = new Hawaiian("Hawaiian", size);
			order.add(p);

		} else {
			Pizza p = new Deluxe("Deluxe", size);
			order.add(p);
		}
		clearToDefault();
		messages.appendText("Pizza added to the order. \n");
	}

	/**
	 * clears toppings selected
	 */
	public void clearToppings() {
		selectedToppings.getItems().clear();
		toppings.getItems().clear();
	}

	/**
	 * gets the pizzalist from the receipt controller(second window)
	 * 
	 * @param p
	 */
	public void getPizzaList(PizzaList p) {
		order = p;
	}

	@FXML
	/**
	 * takes you to the second window to show the pizza order list
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void showOrderPressed(Event event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Reciept.fxml"));
		Parent orderList = loader.load();
		Scene orderScene = new Scene(orderList, 500, 600);
		Stage orderStage = new Stage();

		orderStage.setTitle("Orders");
		orderStage.setScene(orderScene);
		orderStage.show();
		RecieptController recieptController = loader.getController();
		if (order.isEmpty()) {
			recieptController.getText("You have not ordered any pizza yet! \n");
		} else {
			recieptController.getText(order.print());
			recieptController.getPizzaList(order);
		}
	}

}
