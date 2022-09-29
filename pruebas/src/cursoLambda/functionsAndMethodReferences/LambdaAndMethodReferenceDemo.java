package cursoLambda.functionsAndMethodReferences;

public class LambdaAndMethodReferenceDemo {

	public static void main(String[] args) {
		
		// ========== Lambda function demo ==========
		OrderManagement orderManagement = 
				new OrderManagement(new DefaultDistanceCalculator());
		
		orderManagement.setDistanceCalculator(new DistanceCalculator() {
			@Override
			public double calculateDistance(City city1, City city2) {
				// take into account interstate distance calculation
				return city1.getLogitude() - city2.getLogitude(); // some dummy calculations
			}
		});
		
		DistanceCalculator dCalculator = (city1, city2) -> city1.getLogitude() - city2.getLogitude();
		DistanceCalculator dCalculator2 = (city1, city2) -> {
			System.out.println("Text inside lambda function");
			return city1.getLogitude() - city2.getLogitude();
		};
		
		City cityExample1 = new City(127.00, 30.00);
		City cityExample2 = new City(10.50, 10.00);
		System.out.println(dCalculator2.calculateDistance(cityExample1, cityExample2));
		
		orderManagement.setDistanceCalculator((city1, city2) -> city1.getLogitude() - city2.getLogitude());
		
		
		// ========== Method reference demo ==========
		
		orderManagement
			.setDistanceCalculator(GoogleDistanceCalculator::getDistanceBetweenCitiesStatic);
		System.out.println("Google Distance Calculator 1 (Static): " + orderManagement.getDistanceCalculator().calculateDistance(cityExample1, cityExample2));
		
		GoogleDistanceCalculator gdc = new GoogleDistanceCalculator();
		orderManagement
			.setDistanceCalculator(gdc::getDistanceBetweenCities);
		System.out.println("Google Distance Calculator 2: " + orderManagement.getDistanceCalculator().calculateDistance(cityExample1, cityExample2));
	}

}



class OrderManagement {

	private DistanceCalculator distanceCalculator;

	public OrderManagement(DistanceCalculator distanceCalculator) {
		this.distanceCalculator = distanceCalculator;
	}
	
	public void setDistanceCalculator(DistanceCalculator distanceCalculator) {
		this.distanceCalculator = distanceCalculator;
	}
	
	public DistanceCalculator getDistanceCalculator() {
		return this.distanceCalculator;
	}
}


class DefaultDistanceCalculator implements DistanceCalculator {

	@Override
	public double calculateDistance(City city1, City city2) {
		return 0;
	}

}

class GoogleDistanceCalculator {

	public double getDistanceBetweenCities(City city1, City city2) {
		return city1.getLogitude() - city2.getLogitude();
	}
	
	public static double getDistanceBetweenCitiesStatic(City city1, City city2) {
		return city2.getLogitude() - city1.getLogitude();
	}
}
