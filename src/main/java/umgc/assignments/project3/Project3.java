/**
 * Programmer: Devon Blank
 * Date: 11/21/2025
 * Purpose: JavaFX Trip Cost Estimator GUI
 * Collects user input (distance, gasoline cost, gas mileage, number of days, hotel cost,
 * food cost, and attractions cost) with TextFields and ComboBoxes, then uses the TripCost class
 * to calculate and display the total estimated trip cost.
 * */

package umgc.assignments.project3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;


public class Project3 extends Application {

	@Override
	public void start(Stage primaryStage) {

		//Create a grid pane layout
		GridPane pane = new GridPane();
		pane.setHgap(5); //these spacings were most similar to the example
		pane.setVgap(5);

		pane.setPadding(new Insets(20)); //adds padding s content is not flush against window edge, to better match example

		//Distance row
		pane.add(new Label("Distance:"), 0, 0);
		TextField distanceField = new TextField("1000"); //default values in handout example are used throughout program
		pane.add(distanceField, 1, 0); //col 2 row 1
		ComboBox<String> distanceCombo = new ComboBox<>();
		distanceCombo.getItems().addAll("miles" , "kilometers"); //Options in ComboBox for unit type
		distanceCombo.setValue("miles"); //default is miles
		pane.add(distanceCombo, 2, 0);//col 3 row 1

		//Gasoline cost row
		pane.add(new Label("Gasoline Cost:"), 0, 1);
		TextField gasCostField = new TextField("3.95");
		pane.add(gasCostField, 1, 1);
		ComboBox<String> gasCostCombo = new ComboBox<>();
		gasCostCombo.getItems().addAll("dollars/gal", "dollars/liter");
		gasCostCombo.setValue("dollars/gal");
		pane.add(gasCostCombo, 2, 1);

		//Gas Mileage row
		pane.add(new Label("Gas Mileage:"), 0 , 2);
		TextField gasMileageField = new TextField("31");
		pane.add(gasMileageField, 1, 2);
		ComboBox<String> gasMileageCombo = new ComboBox<>();
		gasMileageCombo.getItems().addAll("miles/gallon", "kms/liter");
		gasMileageCombo.setValue("miles/gallon");
		pane.add(gasMileageCombo, 2, 2);

		//Number of days
		pane.add(new Label("Number of Days:"), 0, 3);
		TextField numberOfDaysField = new TextField("2");
		pane.add(numberOfDaysField, 1, 3);

		//Hotel Cost
		pane.add(new Label("Hotel Cost:"), 0, 4);
		TextField hotelCostField = new TextField("150");
		pane.add(hotelCostField, 1, 4);

		//Food cost
		pane.add(new Label("Food Cost:"), 0, 5);
		TextField foodCostField = new TextField("125");
		pane.add(foodCostField, 1, 5);

		//Attractions row
		pane.add(new Label("Attractions: "),0, 6);
		TextField attractionsField = new TextField("78");
		pane.add(attractionsField, 1, 6);

		//Calculate button
		Button calculateButton = new Button("Calculate");
		calculateButton.prefWidthProperty().bind(distanceField.widthProperty()); //To make Calculate button correct width to match example
		pane.add(calculateButton, 1, 7);

		//Total Trip Cost display
		pane.add(new Label("Total Trip Cost: "), 0, 8);
		TextField totalCostField = new TextField("0.00");
		totalCostField.setEditable(false);
		pane.add(totalCostField, 1,8);

		//Button event handler
		calculateButton.setOnAction(e -> {
					try {
						//Read user input
						double distance = Double.parseDouble(distanceField.getText());
						double gasPrice = Double.parseDouble(gasCostField.getText());
						double mileage = Double.parseDouble(gasMileageField.getText());
						double numberOfDays = Double.parseDouble(numberOfDaysField.getText());
						double hotelCost = Double.parseDouble(hotelCostField.getText());
						double foodCost = Double.parseDouble(foodCostField.getText());
						double attractions = Double.parseDouble(attractionsField.getText());

						String distanceUnit = distanceCombo.getValue();
						String gasPriceUnit = gasCostCombo.getValue();
						String gasMileageUnit = gasMileageCombo.getValue();

						//Maps the gas mileage combo selection to the units expected by TripCost.fromMixedUnits
						String mileageDistanceUnit;
						String mileageVolumeUnit;
						if ("kms/liter".equals(gasMileageUnit)) {
							mileageDistanceUnit = "kilometers";
							mileageVolumeUnit = "liter";
						} else {
							mileageDistanceUnit = "miles";
							mileageVolumeUnit = "gallons";
						}

						TripCost trip = TripCost.fromMixedUnits(
								distance,
								distanceUnit,
								gasPrice,
								gasPriceUnit,
								mileage,
								mileageDistanceUnit,
								mileageVolumeUnit,
								numberOfDays,
								hotelCost,
								foodCost,
								attractions
						);

						//Calculate and display total trip cost
						double total = trip.calculateTotalTripCost();
						totalCostField.setText(String.format("$%.2f", total));

					} catch (NumberFormatException ex) {//one or more text fields did not contain valid number
						totalCostField.setText("Invalid Input");
					} catch (IllegalArgumentException ex) {//TripCost validation failed (negative or zero value)
						totalCostField.setText("Error: " + ex.getMessage());
					}
				});

		//Create scene and show
		Scene scene = new Scene(pane, 500, 400);
		primaryStage.setTitle("Trip Cost Estimator");
		primaryStage.setScene(scene);
		calculateButton.fire(); // Ensure program calculates defaults before showing results so output field does not show 0.0
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}