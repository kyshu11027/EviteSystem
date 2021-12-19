
public enum Menu {
	RSVP("RSVP to your invites."),
	PLAN("Plan a new party."),
	ADD_GUEST("Add a guest to a preexisting party."),
	SHOW_PARTIES("Look at the current state of your parties."),
	REGISTER("Register as a host."),
	LOGOUT("Log out of your account."),
	LOGIN("Log into your account"),
	QUIT("Quit");
	
	private String description;
	private Menu(String description){
		this.description = description;
	}
	
	public String getDisplayString(){
		return this.description;
	}
	public static int getNumOptions() {
		return Menu.values().length;
	}
	
	public static Menu getOption(int num) {
		return Menu.values()[num];
	}
	public static String getMenuOptions() {
		String prompt = "*****\tKevinVite System Menu\t*****";

		for(Menu m : Menu.values()){ //array from the enum
			prompt += "\n" + (m.ordinal()) + ": " + m.getDisplayString();
		}
		prompt+="\n**********************************************\n";
		return prompt;
	}
	public static void printMenuOptions() {
		String prompt = getMenuOptions();
		System.out.println(prompt);
	}
}
