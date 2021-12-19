
public abstract class User implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5621756040718403130L;
	protected String email;
	
	public User(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
}
