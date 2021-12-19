import java.time.LocalDateTime;
import java.util.*;

public class Holiday extends Party implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8651778178729380987L;
	private List<String> gifts;

	
	
	public Holiday(Host host, String name, String venue, LocalDateTime time) {
		super(host, name, venue, time);
		this.gifts = new ArrayList<>();
	}

	public void printGifts() {
		System.out.println("Below are the gifts being brought to the Holiday Party");
		for (String s: this.gifts) {
			System.out.println("\t-" + s);
		}
		System.out.println();
	}
	
	public void addGift(String g) {
		this.gifts.add(g);
	}

	@Override
	public String toString() {
		return "(Holiday Party) " + super.toString();
	}
	
	
}
