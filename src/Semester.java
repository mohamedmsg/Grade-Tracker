import java.io.Serializable;


public class Semester implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8565487231189243216L;
	private Course[] courses;
	private String name;
	private GPA GPA;
	private int numOfCourses;

	public Semester(String name) {
		this.courses = new Course[5];
		this.name = name;
		this.GPA = new GPA();
		this.numOfCourses = 0;
	}

	public void addCourse(int credits, String courseName){
		if(numOfCourses >= courses.length - 1){
			expandArray();
		}
		courses[numOfCourses] = new Course(credits, courseName);

		numOfCourses ++;
	}
	
	public Course getCourse(int courseNum){
		return courses[courseNum];
	}
	
	public void expandArray(){
		Course[] tempArray = new Course[courses.length + 2];

		for(int i = 0; i < courses.length; i ++){
			tempArray[i]  = courses[i];
		}

		courses = tempArray;
	}

	public void removeCourse(int courseNum){
		courses[courseNum] = null;
		numOfCourses --;
		
		Course[] temp = new Course[numOfCourses];
		int counter = 0;
		
		for(int i = 0; i < courses.length; i ++){
			if(courses[i] != null){
				temp[counter] = courses[i];
				counter ++;
			}
		}
		
		courses = temp;
	}

	public String getCourseName(int courseNum){
		return courses[courseNum].getCorseName();
	}
	
	public String toString(){
		String output = "";
		for(int i = 0; i < courses.length; i ++){
			if(courses[i] != null){
			output += courses[i].getCorseName() + "\n";
			}
		}
		return output;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGPA() {
		return GPA.calculateSemesterGPA(courses, numOfCourses);
	}

	public void setGPA(GPA gPA) {
		GPA = gPA;
	}
	
	public int getNumOfCourses(){
		return this.numOfCourses;
	}

}
