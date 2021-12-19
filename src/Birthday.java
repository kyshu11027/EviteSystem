
import java.time.LocalDateTime;

public class Birthday extends Party implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7913688554746894844L;
	private String whoseBday;
	private boolean surprise;
	private int age;
	
	
	
	public Birthday(Host host, String name, String venue, LocalDateTime time, String whoseBday, boolean surprise,
			int age) {
		super(host, name, venue, time);
		this.whoseBday = whoseBday;
		this.surprise = surprise;
		this.age = age;
	}

	public String getWhoseBday() {
		return this.whoseBday;
	}
	
	public boolean isSurprise() {
		return this.surprise;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String toString() {
		return "(" + whoseBday + "'s " + age + " Birthday Party) " + super.toString();
	}
}
