

import java.util.*;

public class Host extends Guest implements java.io.Serializable{
	
	private static final long serialVersionUID = -502846806126480251L;
	private String password;
	private List<Party> parties;
	
	
	public Host(String email, String password) {
		super(email);
		// TODO Auto-generated constructor stub
		this.password = password;
		parties = new ArrayList<>();
	}

	public void addParty(Party party) {
		this.parties.add(party);
	}

	/**
	 * @return the parties
	 */
	public List<Party> getParties() {
		return parties;
	}
	
	public void printParties() {
		for (int i = 0; i< parties.size(); i++) {
			System.out.println(i + ": " + parties.get(i));
		}
		System.out.println();
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	public boolean checkPassword(String attempt) {
		// TODO Auto-generated method stub
		return attempt.equals(this.password);
	}
	public boolean equals(Object obj) {
		return this.email.equals(((Host) obj).getEmail());
	}
	
	public String toFileString() {
		return this.email + "\t" + password;
	}
}
