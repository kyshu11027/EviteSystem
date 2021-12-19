
import java.time.LocalDateTime;

public class Performance extends Party implements HasDressCode, java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1751851857697821826L;
	private DressCode dressCode;
	private PerformanceType performanceType;
	
	
	
	
	public Performance(Host host, String name, String venue, LocalDateTime time, DressCode dressCode,
			PerformanceType performanceType) {
		super(host, name, venue, time);
		this.dressCode = dressCode;
		this.performanceType = performanceType;
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
	 * @return the performanceType
	 */
	public PerformanceType getPerformanceType() {
		return performanceType;
	}
	/**
	 * @param performanceType the performanceType to set
	 */
	public void setPerformanceType(PerformanceType performanceType) {
		this.performanceType = performanceType;
	}
	
	public String toString() {
		return "(" + performanceType.getDisplayString() + ") " + super.toString();
	}
}
