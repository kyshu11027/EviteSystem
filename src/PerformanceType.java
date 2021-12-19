
public enum PerformanceType {
	DANCE("Dance Performance"),
	VISUAL_ART("Visual Art (Film, Art, etc.) Performance"),
	MUSIC("Musical Performance");
	
	private String description;
	
	private PerformanceType(String description) {
		this.description = description;
	}
	
	public String getDisplayString(){
		return this.description;
	}
	
	public static int getNumOptions() {
		return PerformanceType.values().length;
	}
	
	public static PerformanceType getOption(int num) {
		return PerformanceType.values()[num];
	}
	public static String getPerformanceOptions() {
		String prompt = "*****\tPerformance Types\t*****";

		for(PerformanceType m : PerformanceType.values()){ //array from the enum
			prompt += "\n" + (m.ordinal()) + ": " + m.getDisplayString();
		}
		prompt+="\n**********************************************\n";
		return prompt;
	}
	public static void printPerformanceOptions() {
		String prompt = getPerformanceOptions();
		System.out.println(prompt);
	}
}
