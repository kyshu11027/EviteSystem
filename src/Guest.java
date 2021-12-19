
import java.util.*;

public class Guest extends User implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7391977259851953228L;
	protected List<Party> invites;
	
	public Guest(String email) {
		super(email);
		invites = new ArrayList<>();
	}
	
	public void seeInvites() {
		for (int i = 0; i< invites.size(); i++) {
			System.out.println(invites.get(i));
		}
	}
	
	public List<Party> getInvites(){
		return this.invites;
	}
}
