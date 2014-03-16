import java.io.Serializable;


public class AssignmentCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5986894717390349184L;
	private String name;
	private int weight, fat;//secondary weight variable.
	private Assignment[] assignments;
	private int assignmentCounter;
	private double grade, requiredGrade;
	private int numCompleteAssignments;
	private Assignment assignment;

	public AssignmentCategory(String name, int weight){
		this.name = name;
		this.weight = weight;
		this.assignments = new Assignment[5];
		assignmentCounter = 0;
		grade = 0;
		assignment = null;
	}

	public void addAssignment(Assignment assignment){
		if(assignmentCounter >= assignments.length){
			expandArray();
		}

		assignments[assignmentCounter] = assignment;
		assignmentCounter ++;
	}

	public void expandArray(){
		Assignment[] tempArray = new Assignment[assignments.length + 2];

		for(int i = 0; i < assignments.length; i ++){
			tempArray[i]  = assignments[i];
		}

		assignments = tempArray;
	}

	public void removeAssignment(int assignmentNum){
		assignments[assignmentNum] = null;
		assignmentCounter --;
		
		Assignment[] temp = new Assignment[assignmentCounter];
		int counter = 0;
		
		for(int i = 0; i < assignments.length; i ++){
			if(assignments[i] != null){
				temp[counter] = assignments[i];
				counter ++;
			}
		}
		assignments = temp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public double getGrade() {
		double totalPossibleGrade = 0;
		double totalAcquiredGrade = 0;
		getNumCompleteAssignments();

		for(int i = 0; i < assignmentCounter; i ++){
			if(assignments[i].getAcquiredGrade() >= 1){
			totalPossibleGrade += assignments[i].getPossibleGrade();
			totalAcquiredGrade += assignments[i].getAcquiredGrade();
			}
		}

		grade = (totalAcquiredGrade/totalPossibleGrade) * (weight/100.0);
		return grade;
	}

	public double calculateFat(int weightLeft){
		fat = weight + weightLeft;
		double totalPossibleGrade = 0;
		double totalAcquiredGrade = 0;
		getNumCompleteAssignments();

		for(int i = 0; i < assignmentCounter; i ++){
			if(assignments[i].getAcquiredGrade() >= 1){
			totalPossibleGrade += assignments[i].getPossibleGrade();
			totalAcquiredGrade += assignments[i].getAcquiredGrade();
			}
		}

		double newGrade = (totalAcquiredGrade/totalPossibleGrade) * (fat/100.0);
		return newGrade;
		
	}
	
	/*
	 * returns the total possible grade from the current assignments.
	 */
	public double getTotalGrade(){
		int totalPossibleGrade = 0;
		for(int i = 0; i < numCompleteAssignments; i ++){
			if(assignments[i].getAcquiredGrade() >= 1){
			totalPossibleGrade += assignments[i].getPossibleGrade();
			}
		}
		
		return totalPossibleGrade;
	}
	
	public Assignment getAssignment(int assignmentNum) {

		return assignments[assignmentNum];
	}

	public int getAssignmentCounter(){
		return assignmentCounter;
	}

	private boolean find(String assignmentName){
		boolean found = false;
		int i = 0;

		while(!found && i < assignments.length){
			if(assignments[i] != null){
				if(assignments[i].getName().equals(assignmentName)){
					assignment = assignments[i];
					found = true;
				}
			}
			i++;
		}
		return found;
	}

	public boolean exists(String assignmentName){
		return find(assignmentName);
	}

	public String[] getAssignmentNames(){
		String[] names = new String[assignmentCounter];
		for(int i = 0; i < assignmentCounter; i ++){
			if(assignments[i].getAcquiredGrade() >= 0){
			names[i] = assignments[i].getName() + " - " + (int)Math.round(assignments[i].getTotalGrade()) + "%";
			}
			else{
				names[i] = assignments[i].getName() + " - need " + (int)this.requiredGrade + "%";
			}
		}
		
		return names;
	}
	
	public Assignment[] getAssignments(){
		return assignments;
	}
	
	public String toString(){
		String assignmentString = "";
		for(int i = 0; i < assignmentCounter; i ++){
			assignmentString += assignments[i].getName() + " Grade: " + (assignments[i].getTotalGrade()/assignments[i].getPossibleGrade()) + "/n";
		}

		return assignmentString;
	}

	public int getNumCompleteAssignments(){
		numCompleteAssignments = 0;
		
		for(int i = 0; i < assignmentCounter; i ++){
			if(assignments[i].getAcquiredGrade() >= 0){
				numCompleteAssignments ++;
			}
		}
		return numCompleteAssignments;
	}

	public void setRequiredGrade(double requiredGrade){
		this.requiredGrade = requiredGrade;
	}
}
