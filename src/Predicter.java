
public class Predicter {

	public Predicter(){
		
	}
	
	/*
	 * Takes in an array of all the Assignment Categories and how many assignments are left for each category as well as a desired grade
	 * gets the current grade of each category and calculates the total possible for that category using the amount of assignments possible per category
	 * sees what % is required of each category in order to get desired grade then divides category % into the assignments in that category
	 */
	public double[] predict(AssignmentCategory[] categories, double desiredPercentage){
		 double[] requiredPercentage = new double[categories.length];

		 for(int i = 0; i < categories.length; i ++){
			int incompleteAssignments = categories[i].getAssignmentCounter() - categories[i].getNumCompleteAssignments();
			double currentTotal = categories[i].getNumCompleteAssignments() * categories[i].getGrade();
			double possibleTotal = (desiredPercentage * (categories[i].getWeight()/100.0)) * categories[i].getAssignmentCounter();
			double percentagePerAssignment = ((possibleTotal - currentTotal) / incompleteAssignments) / (categories[i].getWeight()/100.0);
			requiredPercentage[i] = percentagePerAssignment * 100;
		}
		return requiredPercentage;
	}
}
