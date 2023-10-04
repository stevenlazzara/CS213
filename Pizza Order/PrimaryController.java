package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import pizza.*;

public class PrimaryController implements Initializable{
	
	@FXML
	ComboBox<String> pizzaOptions;
	
	@FXML
	ComboBox<String> sizeOptions;
	
	@FXML
	ImageView pizzaImg;
	
	@FXML
	Image pizza;

	@FXML
	ListView<String> toppingsOptions;
	
	@FXML
	ListView<String> selectedToppings;
	
	@FXML
	Button addToppingsButton;
	
	@FXML 
	Button removeToppingsButton;
	
	@FXML
	Button clearButton;
	
	@FXML
	Button addPizzaButton;
	
	@FXML
	Button showOrderButton;
	
	@FXML
	TextArea messageDisplay;
	
	ObservableList<Pizza> order;
	
	int test;
	
	String currentPizzaChoice;
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		 
		ObservableList<String> pizzas = FXCollections.observableArrayList("Select a style", "Deluxe", "Hawaiian", "Build your own");
		pizzaOptions.setItems(pizzas);
		pizzaOptions.getSelectionModel().selectFirst();
		
		ObservableList<String> sizes = FXCollections.observableArrayList("Select a size", "Small", "Medium", "Large");
		sizeOptions.setItems(sizes);
		sizeOptions.getSelectionModel().selectFirst();
		
		pizza = new Image("file:img/Build your own.jpg", 100, 100, true, true);
		pizzaImg.setImage(pizza);
		
		ObservableList<String> toppings = FXCollections.observableArrayList("Beef", "Cheese", "Buffalo Chicken", "Green Pepper", "Ham", "Mushroom", "Onion", "Pepperoni", "Pineapple", "Sausage");
		toppingsOptions.setItems(toppings);
		toppingsOptions.setFixedCellSize(25);
		toppingsOptions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		selectedToppings.setFixedCellSize(25);
		selectedToppings.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		addToppingsButton.setDisable(true);
		removeToppingsButton.setDisable(true);
		
		order = FXCollections.observableArrayList();
		currentPizzaChoice="default";
		
		
	}
	
	@FXML
	/**
	 * Called when "Add toppings" button is pressed. 
	 * Takes all toppings selected in the list of toppings 
	 * and removes them from said list and adds them to the list of
	 * selected toppings.
	 * @param event
	 */
	void addToppings(ActionEvent event) {
		ObservableList<String> selected = FXCollections.observableArrayList();
		
		for(String element: selectedToppings.getItems()) {
			selected.add(element);
		}
		for(String element: toppingsOptions.getSelectionModel().getSelectedItems()) {
			selected.add(element);
			toppingsOptions.getItems().remove(element);
			if(toppingsOptions.getItems().isEmpty())
				break;
		}
		selectedToppings.setItems(selected);
		
		
		
	}
	
	@FXML
	/**
	 * Called when "Remove toppings" button is pressed.
	 * Takes all topings that were previously added to the list of select toppings and removes them.
	 * All removed toppings are then placed back in the list of available toppings.
	 * @param event
	 */
	void removeToppings(ActionEvent event) {
		ObservableList<String> removed = FXCollections.observableArrayList();
		
		for(String element: toppingsOptions.getItems()) {
			removed.add(element);
		}
		
		for(String element: selectedToppings.getSelectionModel().getSelectedItems()) {
			removed.add(element);
			selectedToppings.getItems().remove(element);
			if(selectedToppings.getItems().isEmpty())
				break;
		}
		toppingsOptions.setItems(removed);
	}
	
	@FXML
	/**
	 * Called when "Clear selection" button is pressed.
	 * Resets the GUI to the default state with all lists back in the initial/default state. 
	 * @param event
	 */
	void clear(ActionEvent event) {
		resetAll();
	}
	
	@FXML
	/**
	 * Called when "Add to order" button is pressed
	 * Collects all information entered into the window and instantiates pizza instance.
	 * Instance is then added to local ArrayList of pizzas.
	 * @param event
	 */
	void addPizza(ActionEvent event) {
		String style = pizzaOptions.getSelectionModel().getSelectedItem();
		String size = sizeOptions.getSelectionModel().getSelectedItem();
		Pizza pizza;
		
		if(invalidInput())
			return;
		
		if(style.equals("Build your own")) {
			ArrayList<String> toppings = new ArrayList<String>();
			toppings.addAll(selectedToppings.getItems());
			pizza = new BuildYourOwn(size, toppings);
		}else if(style.equals("Deluxe")) 
			pizza = new Deluxe(size);
		else
			pizza = new Hawaiian(size);
		
		order.add(pizza);
		resetAll();
		messageDisplay.setText("Pizza added!");
	}
	
	@FXML
	/**
	 * Called when "Show order" button is pressed.
	 * Loads a second window and shares the order information with the controller linked with said window.
	 * Shows the second window that will display the details of the current order. 
	 * @param event
	 */
	void showOrder(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Secondary.fxml"));
			Stage orderStage = new Stage();
			Scene orderScene = new Scene((HBox)loader.load());
			orderStage.setScene(orderScene);
			orderStage.setTitle("Your Order");
			SecondaryController ref = loader.<SecondaryController>getController();
			ref.enterOrder(order);
			ref.getController(this);
			
			orderStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	/**
	 * Called when a slot is selected in the ComboBox for style choices.
	 * Used to change the display of the page based on the style selected.
	 * Ability to add toppings is enabled for Build-your-own pizzas but kept as disabled otherwise.
	 * Also displays image for the style of pizza selected by user.
	 */
	void chooseStyle() {
		String choice = pizzaOptions.getSelectionModel().getSelectedItem();
		if(choice.equals(currentPizzaChoice))
			return;
		
		currentPizzaChoice=choice;
		List<String> defaultToppings=null;
		resetToppings();
		
		if(!pizzaOptions.getSelectionModel().getSelectedItem().equals("Select a style"))
			showPizzaImg(choice);

		switch(choice) {
		case "Build your own":
			addToppingsButton.setDisable(false);
			removeToppingsButton.setDisable(false);
			return;
			
		case "Select a style":
			resetAll();
			return;
			
		case "Deluxe":
			String[] deluxeToppings= {"Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom"};
			defaultToppings=Arrays.asList(deluxeToppings);
			break;
			
		case "Hawaiian":
			String[] hawaiianToppings= {"Ham", "Pineapple"};
			defaultToppings=Arrays.asList(hawaiianToppings);
			break;
		
		}

		if(toppingsOptions.getItems().removeAll(defaultToppings))
			selectedToppings.getItems().addAll(defaultToppings);
		
		addToppingsButton.setDisable(true);
		removeToppingsButton.setDisable(true);
	}
	
	/**
	 * Displays image of pizza style selected by the user
	 * @param choice of style selected by user
	 */
	void showPizzaImg(String choice) {
		String filePath = "file:img/" + choice +".jpg";

		try {
			pizza = new Image(filePath, 100, 100, true, true);
		}catch(Exception e) {
			System.out.println("welp");
		}
		
		pizzaImg.setImage(pizza);
	}
	
	/**
	 * Resets window to default state.
	 * Called by clear() when "Clear selection" button is pressed
	 * Called when "Select a style" is selected in the ComboBox for pizza styles
	 */
	void resetAll() {
		//reset to "Select a style" and "Select a size" default choices
		pizzaOptions.getSelectionModel().select(0);			
		sizeOptions.getSelectionModel().select(0);
		
		addToppingsButton.setDisable(true);
		removeToppingsButton.setDisable(true);
		
		resetToppings();							//resetting topping selections and transferring them back to options list
	}
	
	/**
	 * Resets the lists of toppings available to add and of toppings currently selected.
	 * Default state is the list of available toppings completely full with the list of toppings selected emptied.
	 * Called when the window is being reset to its default state and when a different style is chosen in the ComboBox for pizza styles.
	 */
	void resetToppings() {
		if(!selectedToppings.getItems().isEmpty()) {
			toppingsOptions.getItems().addAll(selectedToppings.getItems());
			selectedToppings.getItems().clear(); 
		}
		
	}
	
	/**
	 * Checks if user has filled out all necessary information.
	 * Style and size are always required.
	 * Selection of toppings is needed only for Build-your-own pizzas.
	 * Outputs message describing user error as well.
	 * @return true if input is invalid, false otherwise
	 */
	boolean invalidInput() {
		String errorMessage="";
		boolean invalidInput = false;
		
		if(pizzaOptions.getSelectionModel().getSelectedIndex()==0) {		//"Select a style" is selected 
			errorMessage+="Please select a style.\n";
			invalidInput=true;
		}
		if(sizeOptions.getSelectionModel().getSelectedIndex()==0) {			//"Select a size" is selected
			errorMessage+="Please select a size.\n";
			invalidInput=true;
		}
		if(pizzaOptions.getSelectionModel().getSelectedItem().equals("Build your own")) {
			if(selectedToppings.getItems().isEmpty()) {
				errorMessage+="Please choose at least ONE topping.\n";
				invalidInput=true;
			}
		}
		
		messageDisplay.setText(errorMessage);
		return invalidInput;

	}
	

}
