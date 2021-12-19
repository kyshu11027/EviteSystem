
import java.time.LocalDateTime;
import java.util.*;

public abstract class Party implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Map<String, Response> guests;
	protected Host host;
	protected String name;
	protected String venue;
	protected LocalDateTime time;
	
	public Party(Host host, String name, String venue, LocalDateTime time) {
		this.host = host;
		this.name = name;
		this.venue = venue;
		this.time = time;
		guests = new HashMap<>();
	}

	/**
	 * @return the guests
	 */
	public Map<String, Response> getGuests() {
		return guests;
	}

	/**
	 * @param guests the guests to set
	 */
	public void setGuests(Map<String, Response> guests) {
		this.guests = guests;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the venue
	 */
	public String getVenue() {
		return venue;
	}

	/**
	 * @param venue the venue to set
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public void addGuest(String email) {
		if(guests.containsKey(email)) {
			System.out.println("You've already invited this person.");
		}
		else {
			guests.put(email, Response.TENTATIVE);
		}
	}
	
	public static String timeConvert(int hour, int min) {
		String res = "";
		String minStr = "";
		if (min< 10) {
			minStr = "0" + min;
		}
		else {
			minStr = "" + min;
		}
		if (hour>12) {
			res += (hour - 12) + ":" + minStr + " PM";
		}
		else {
			res += hour + ":" + minStr + " AM";
		}
		return res;
	}
	
	public String toString() {
		return name + " on " + time.getMonth() + " " + time.getDayOfMonth() + " at " + timeConvert(time.getHour(), time.getMinute()) + " at " + this.venue;
	}
	public Host getHost() {
		return host;
	}
}
