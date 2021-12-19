
public enum PartyType {
	BIRTHDAY("Plan a Birthday Party"),
	POTLUCK("Plan a Potluck"),
	HOLIDAY("Plan a Holiday Party"),
	REUNION("Plan a Reunion"),
	FORMAL("Plan a Formal"),
	PERFORMANCE("Plan a Performance"),
	QUIT("Quit");
	
	private String description;
	private PartyType(String description){
		this.description = description;
	}
	
	public String getDisplayString(){
		return this.description;
	}
	public static int getNumOptions() {
		return PartyType.values().length;
	}
	
	public static PartyType getOption(int num) {
		return PartyType.values()[num];
	}
	public static String getPartyOptions() {
		String prompt = "*****\tParty Options\t*****";

		for(PartyType m : PartyType.values()){ //array from the enum
			prompt += "\n" + (m.ordinal()) + ": " + m.getDisplayString();
		}
		prompt+="\n**********************************************\n";
		return prompt;
	}
	public static void printPartyOptions() {
		String prompt = getPartyOptions();
		System.out.println(prompt);
	}
}
