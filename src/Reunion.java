
import java.time.LocalDateTime;

public class Reunion extends Party{
	/**
	 * 
	 */
	private static final long serialVersionUID = -726235070950899546L;
	private int numYears;


	public Reunion(Host host, String name, String venue, LocalDateTime time, int numYears) {
		super(host, name, venue, time);
		this.numYears = numYears;
	}

	/**
	 * @return the numYears
	 */
	public int getNumYears() {
		return numYears;
	}

	/**
	 * @param numYears the numYears to set
	 */
	public void setNumYears(int numYears) {
		this.numYears = numYears;
	}
	
	public String toString() {
		return "(" + this.numYears + " Year Reunion) " + super.toString();
	}

}
