
public enum Response {
	COMING("Coming"),
	TENTATIVE("Tentative"),
	NOT_COMING("Not Coming");
	
	private String description;
	
	private Response(String description) {
		this.description = description;
	}
	
	public String getDisplayString(){
		return this.description;
	}
}
