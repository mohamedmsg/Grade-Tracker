import java.io.Serializable;


public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1445804494477956759L;
	private int credits, totalWeight;
	private String corseName;
	private double totalGrade;
	private double[] requiredGrades;
	private int assignmentCounter;
	private AssignmentCategory[] scale;
	private final AssignmentCategory[] bannanas = {new AssignmentCategory("Uncategorized", 100)}; //TODO: rename the category to some arbitrary name
	private double desiredGrade;

	public Course(int credits, String corseName) {
		this.credits = credits;
		this.corseName = corseName;
		this.totalGrade = 0;
		this.assignmentCounter = 0;
		this.scale = bannanas;
		this.desiredGrade = 0.0;
	}

	public void setDesiredGrade(double newDesiredGrade){
		this.desiredGrade = newDesiredGrade/100;
	}
	
	public void addAssignment(String name, int assignmentCategory, double possibleGrade){
		scale[assignmentCategory].addAssignment(new Assignment(name, possibleGrade));
	}

	public void removeCategory(int categoryNum){
		scale[categoryNum] = null;
		
		AssignmentCategory[] temp = new AssignmentCategory[scale.length -1];
		int counter = 0;
		
		for(int i = 0; i < scale.length; i ++){
			if(scale[i] != null){
				temp[counter] = scale[i];
				counter ++;
			}
		}
		
		scale = temp;
	}
	
	private void expandArray(){

			AssignmentCategory [] temp = new AssignmentCategory[scale.length + 1];

			for(int i = 0; i < scale.length; i ++){
				temp[i] = scale[i];
			}

			scale = temp;
	}

	public void addAssignmentCategory(String name, int weight){
		expandArray();
		scale[scale.length -1] = new AssignmentCategory(name, weight);
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getCorseName() {
		return corseName;
	}

	public void setCorseName(String corseName) {
		this.corseName = corseName;
	}

	public String getTotalCharGrade() {
		String characterGrade = "";
		totalGrade = 0.0;
		int missingPoints = 0;
		int numCategories = 0;

		if(scale.length == 1){
			totalGrade += scale[0].getGrade();
			totalGrade *= 100;
		}
		
		totalGrade = 0;
		for(int i = 1; i < scale.length; i ++){
			if(scale[i].getNumCompleteAssignments() > 0){
				totalGrade += scale[i].getGrade();
				numCategories++;
			}
			else{
				missingPoints += scale[i].getWeight();
			}
		}
		
		totalGrade = 0;
		if(numCategories > 0){
			int perCategory = missingPoints/numCategories;

			for(int i = 0; i < scale.length; i ++){
				if(scale[i].getNumCompleteAssignments() > 0){
					totalGrade += scale[i].calculateFat(perCategory);
				}
			}
			totalGrade *= 100;

		}
		else{
			totalGrade = -1;
		}

		if ((totalGrade > 89))
			characterGrade = "A";
		else if ((totalGrade > 79) && (totalGrade < 90))
			characterGrade = "B";
		else if ((totalGrade > 69) && (totalGrade < 80))
			characterGrade = "C";
		else if ((totalGrade > 59) && (totalGrade < 70))
			characterGrade = "D";
		else if ((totalGrade >= 0) && (totalGrade < 60))
			characterGrade = "F";
		else
			characterGrade = "N/A";

		return characterGrade;
	}

	public double getTotalNumericalGrade(){
		totalGrade = 0.0;

		for(int i = 0; i < scale.length; i ++){
			totalGrade += scale[i].getGrade();
		}

		return totalGrade;
	}

	public void setTotalGrade(double totalGrade) {
		this.totalGrade = totalGrade;
	}

	public int getAssignmentCounter(){
		this.assignmentCounter = 0;

		for(int i = 0; i < scale.length; i ++){
			assignmentCounter += scale[i].getAssignmentCounter();
		}

		return assignmentCounter;
	}

	public void predictGrade(){
		Predicter predict = new Predicter();
		requiredGrades = predict.predict(scale, this.desiredGrade);
	}

	public void setRequiredGrade(){
		predictGrade();
		for(int i = 0; i < scale.length; i ++){
			scale[i].setRequiredGrade(requiredGrades[i]);
		}
	}

	public AssignmentCategory[] getCategories(){
		return scale;
	}

	public AssignmentCategory getCategory(int category){
		return scale[category];
	}

	public int getTotalWeight() {
		totalWeight = 0;
		
		for(int i = 0; i < scale.length; i ++){
			totalWeight += scale[i].getWeight();
		}
		
		return totalWeight;
	}

	public void setTotalWeight(int totalWeight) {
		this.totalWeight = totalWeight;
	}

	public String toString(){
		String assignmentString = "";

		for(int i = 0; i < scale.length; i ++){
			assignmentString += scale[i].toString();
		}

		return assignmentString;
	}
}
