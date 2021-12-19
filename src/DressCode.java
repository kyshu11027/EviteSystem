
public enum DressCode {
	BUSINESS_FORMAL("Business Formal Attire"),
	CASUAL("Casual Attire"),
	BUSINESS_CASUAL("Business Casual Attire"),
	SMART_CASUAL("Smart Casual Attire");
	
	private String description;
	
	private DressCode(String description) {
		this.description = description;
	}
	
	public String getDisplayString(){
		return this.description;
	}
	public static int getNumOptions() {
		return DressCode.values().length;
	}
	
	public static DressCode getOption(int num) {
		return DressCode.values()[num];
	}
	public static String getDressOptions() {
		String prompt = "*****\tDifferent Food Options\t*****";

		for(DressCode m : DressCode.values()){ //array from the enum
			prompt += "\n" + (m.ordinal()) + ": " + m.getDisplayString();
		}
		prompt+="\n**********************************************\n";
		return prompt;
	}
	public static void printDressOptions() {
		String prompt = getDressOptions();
		System.out.println(prompt);
	}
	
}
