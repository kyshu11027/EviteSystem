
import java.time.LocalDateTime;

public class Formal extends Party implements HasDressCode, java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4287460848825788158L;
	private DressCode dressCode;
	private boolean hasFood;
	
	
	
	public Formal(Host host, String name, String venue, LocalDateTime time, DressCode dressCode, boolean hasFood) {
		super(host, name, venue, time);
		this.dressCode = dressCode;
		this.hasFood = hasFood;
	}
	/**
	 * @return the dressCode
	 */
	public DressCode getDressCode() {
		return dressCode;
	}
	/**
	 * @param dressCode the dressCode to set
	 */
	public void setDressCode(DressCode dressCode) {
		this.dressCode = dressCode;
	}
	/**
	 * @return the hasFood
	 */
	public boolean hasFood() {
		return hasFood;
	}
	/**
	 * @param hasFood the hasFood to set
	 */
	public void setHasFood(boolean hasFood) {
		this.hasFood = hasFood;
	}
	
	public String toString() {
		String res = "";
		if(hasFood) {
			res = "(Formal Party and FOOD PROVIDED) " + super.toString();
		}
		else {
			res = "(Formal Party and FOOD NOT PROVIDED) " + super.toString();
		}
		return res;
	}
	
}
