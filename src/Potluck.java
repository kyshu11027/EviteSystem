import java.time.LocalDateTime;
import java.util.*;

public class Potluck extends Party implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -451311321891354322L;
	private Map<FoodType, Integer> food;
	
	
	

	public Potluck(Host host, String name, String venue, LocalDateTime time) {
		super(host, name, venue, time);
		this.food = new HashMap<>();
		this.food.put(FoodType.APPETIZER, 0);
		this.food.put(FoodType.MAIN, 0);
		this.food.put(FoodType.DESSERT, 0);
		this.food.put(FoodType.SIDE, 0);
		this.food.put(FoodType.BEVERAGE, 0);
	}

	public void printFoodMap() {
		System.out.println("Below are the types of foods being brought by the number of people bringing them: ");
		for (FoodType f: food.keySet()) {
			System.out.println("\t-"+ f + ": " + food.get(f));
		}
		System.out.println();
	}
	
	public void addFood(FoodType f) {
		if (food.containsKey(f)) {
			food.put(f, food.get(f) + 1);
		}
		else {
			food.put(f, 1);
		}
	}
	
	public String toString() {
		return "(Potluck) " + super.toString();
	}
}
