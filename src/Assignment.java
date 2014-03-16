import java.io.Serializable;


public class Assignment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8564188559841415255L;
	private String name;
	private double totalGrade;
	private double possibleGrade;
	private double acquiredGrade;
	
	public Assignment(String name, double possibleGrade){
		this.name = name;
		this.totalGrade = 0;
		this.possibleGrade =  possibleGrade;
		this.acquiredGrade = -1;
	}
	
	public double getTotalGrade() {
		totalGrade = acquiredGrade/possibleGrade;
		totalGrade *= 100;
		return totalGrade;
	}

	public void setTotalGrade(double totalGrade) {
		this.totalGrade = totalGrade;
	}

	public double getPossibleGrade() {
		return possibleGrade;
	}

	public void setPossibleGrade(double possibleGrade) {
		this.possibleGrade = possibleGrade;
	}

	public double getAcquiredGrade() {
		return acquiredGrade;
	}

	public void setAcquiredGrade(double acquiredGrade) {
		this.acquiredGrade = acquiredGrade;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNameGrade(){
		return this.name + " - " + getTotalGrade() + "%";
	}
	
}
