/**
 * Programmer: Devon Blank
 * Date: 11/21/2025
 * Purpose: TripCost model class.
 * Holds trip parameters and performs unit conversion and calculations for fuel expense and total trip cost based on
 * inputs: distance, gasoline cost, gas mileage, number of days, hotel cost, food cost, and attractions.
 * */


package umgc.assignments.project3;

public final class TripCost {

	//Variables & inputs
	private final double distance;
	private final double gasolineCost;
	private final double gasMileage;
	private final double numberOfDays;
	private final double hotelCost;
	private final double foodCost;
	private final double attractions;


	//Constants
	public static final double KILOMETERS_PER_MILE = 1.609347;
	public static final double LITERS_PER_GALLON = 3.78541178;
	public static final double METRIC_FUEL_EFFICIENCY_TO_IMPERIAL = LITERS_PER_GALLON / KILOMETERS_PER_MILE; //~2.35214

	/**
	 * Constructs a TripCost with all required trip values.
	 * @param distance - total trip distance in miles
	 * @param gasolineCost - cost of gas per gallon in dollars
	 * @param gasMileage - fuel efficiency in miles per gallon
	 * @param numberOfDays - number of days for the trip
	 * @param hotelCost - hotel cost per day in dollars
	 * @param foodCost - food cost per day in dollars
	 * @param attractions - total attractions cost in dollars
	 */

	//Constructs a TripCost with all required trip values
	public TripCost(double distance,
	                double gasolineCost,
	                double gasMileage,
	                double numberOfDays,
	                double hotelCost,
	                double foodCost,
	                double attractions) {

		//Invalid input error handling for each input
		if (distance < 0.0) {
			throw new IllegalArgumentException("Distance cannot be negative");
		}
		if (gasolineCost < 0.0) {
			throw new IllegalArgumentException("Gasoline cost cannot be negative");
		}
		if (gasMileage <= 0.0) {//Zero is not possible for gasMileage
			throw new IllegalArgumentException("Gas mileage must be positive");
		}
		if (numberOfDays < 0.0) {
			throw new IllegalArgumentException("Days cannot be negative");
		}
		if (hotelCost < 0.0) {
			throw new IllegalArgumentException("Hotel cost cannot be negative");
		}
		if (foodCost < 0.0) {
			throw new IllegalArgumentException("Food cost cannot be negative");
		}
		if (attractions < 0.0) {
			throw new IllegalArgumentException("Attractions cannot be negative");
		}

		this.distance = distance;
		this.gasolineCost = gasolineCost;
		this.gasMileage = gasMileage;
		this.numberOfDays = numberOfDays;
		this.hotelCost = hotelCost;
		this.foodCost = foodCost;
		this.attractions = attractions;
	}

	//Creates a TripCost from user-entered inputs. Can use either imperial or metric units. Units internally stored as imperial.
	public static TripCost fromMixedUnits(
			double distance,
			String distanceUnit,
			double gasPrice,
			String gasPriceUnit,
			double mileage,
			String mileageDistUnit,
			String mileageVolUnit,
			double numberOfDays,
			double hotelCost,
			double foodCost,
			double attractions){

		//Normalize distance to miles
		double distanceInMiles;
		if("kilometers".equals(distanceUnit)){
			distanceInMiles = distance /  KILOMETERS_PER_MILE;
		}else{
			distanceInMiles = distance;
		}

		//Normalize gas price to dollars per gallon
		double gasPricePerGallon;
		if("dollars/liter".equals(gasPriceUnit)){
			gasPricePerGallon =  gasPrice * LITERS_PER_GALLON;
		}else{
			gasPricePerGallon = gasPrice;
		}

		//Normalize mileage to miles per gallon
		double mileageInMilesPerGallon;
		if("kilometers".equals(mileageDistUnit) && "liter".equals(mileageVolUnit)){
			mileageInMilesPerGallon = mileage * METRIC_FUEL_EFFICIENCY_TO_IMPERIAL;
		}else{
			mileageInMilesPerGallon = mileage;
		}

		return new TripCost(
				distanceInMiles,
				gasPricePerGallon,
				mileageInMilesPerGallon,
				numberOfDays,
				hotelCost,
				foodCost,
				attractions
		);
	}


	//Distance Getter
	public double getDistance() {
		return distance;
	}

	//gasolineCost Getter
	public double getGasolineCost() {
		return gasolineCost;
	}

	//gasMileage Getter
	public double getGasMileage() {
		return gasMileage;
	}

	//numberOfDays Getter
	public double getNumberOfDays() {
		return numberOfDays;
	}

	//hotelCost Getter
	public double getHotelCost() {
		return hotelCost;
	}

	//foodCost Getter
	public double getFoodCost() {
		return foodCost;
	}

	//attractions Getter
	public double getAttractions() {
		return attractions;
	}

	//Calculation methods
	//Fuel Expense calculations
	public double calculateFuelExpense() {
			return (distance / gasMileage) * gasolineCost;

	}

	//Total trip cost calculations
	public double calculateTotalTripCost() {
		double fuelExpense = calculateFuelExpense();
		return fuelExpense + ((hotelCost + foodCost) * numberOfDays) + attractions;
	}
}