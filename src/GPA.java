import java.io.Serializable;


public class GPA implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5271235761780989299L;
	private int totalCredits;
	private double totalGradePoints;
	
	public GPA(){
		
	}

	public double calculateSemesterGPA(Course[] courses, int numOfCourses){
		totalCredits = 0;
		totalGradePoints = 0;
		for(int i = 0; i < numOfCourses; i ++){
			totalCredits += courses[i].getCredits();
			if(courses[i].getTotalCharGrade() == "A"){
				totalGradePoints += 4.0 * courses[i].getCredits();
			}
			else if(courses[i].getTotalCharGrade() == "B"){
				totalGradePoints += 3.0 * courses[i].getCredits();
			}
			else if(courses[i].getTotalCharGrade() == "C"){
				totalGradePoints += 2.0 * courses[i].getCredits();
			}
			else if(courses[i].getTotalCharGrade() == "D"){
				totalGradePoints += 1.0 * courses[i].getCredits();
			}
			else if(courses[i].getTotalCharGrade() == "F"){
				totalGradePoints += 0.0 * courses[i].getCredits();
			}
			else{
				totalGradePoints += 4.0 * courses[i].getCredits();
			}
		}

		double GPA = totalGradePoints/totalCredits;
		return GPA;
	}
	
	public int getTotalCredits() {
		return totalCredits;
	}

	public void setTotalCredits(int totalCredits) {
		this.totalCredits = totalCredits;
	}

	public double getTotalGradePoints() {
		return totalGradePoints;
	}

	public void setTotalGradePoints(double totalGradePoints) {
		this.totalGradePoints = totalGradePoints;
	}
}
