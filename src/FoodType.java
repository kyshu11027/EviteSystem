
public enum FoodType {
	APPETIZER("Appetizer"),
	MAIN("Main Dish"), 
	DESSERT("Dessert"),
	SIDE("Side Dish"),
	BEVERAGE("Beverage/Drink");
	
	private String description;
	private FoodType(String description){
		this.description = description;
	}
	
	public String getDisplayString(){
		return this.description;
	}
	public static int getNumOptions() {
		return FoodType.values().length;
	}
	
	public static FoodType getOption(int num) {
		return FoodType.values()[num];
	}
	public static String getFoodOptions() {
		String prompt = "*****\tDifferent Food Options\t*****";

		for(FoodType m : FoodType.values()){ //array from the enum
			prompt += "\n" + (m.ordinal()) + ": " + m.getDisplayString();
		}
		prompt+="\n**********************************************\n";
		return prompt;
	}
	public static void printFoodOptions() {
		String prompt = getFoodOptions();
		System.out.println(prompt);
	}
}
