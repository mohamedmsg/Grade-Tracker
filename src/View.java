import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class View implements ActionListener{

	private JFrame mainWindow, secondWindow;
	private JButton loginBtn, newAccountBtn, createAccountBtn, exitBtn, addSemesterBtn, addCourseBtn, addAssignmentBtn, createCourse, semesterCancel, 
	courseCancel, categoryCancel, assignmentCancel, addCategory, createCategory, createAssignment, setGradeBtn, removeAssignmentBtn, removeSemesterBtn, 
	removeCourseBtn, removeCategoryBtn, createSemesterBtn, courseBackBtn, categoryBackBtn, assignmentBackBtn, viewAssignments, setDesiredGrade, viewCourseBtn, 
	viewCategories;
	private JComboBox assignmentCategoriesBox;
	private JButton[] courses, semesterButtons, assignments, categoryButtons;
	private JLabel usernameLbl, passwordLbl, nameLbl, newUsernameLbl, newPasswordLbl, confirmPasswordLbl, creditsLbl, weightLbl, possibleGradeLbl, 
	assignmentCategoriesLbl, gradeLbl;
	private JTextField username, name, newUsername, credits, weightTxt, possibleGrade, grade;
	private JPasswordField password, newPassword, confirmPassword;
	private int currentSemester, currentCourse, assignmentYBtnPosition, currentCategory, currentAssignment, courseYBtnPosition, categoryYBtnPosition, semesterYBtnPosition;
	private Users currentUser;
	private ArrayList<Semester> semesters;
	private Background courseBackground, loginBackground;
	private ImageIcon buttonIcon, buttonClickedIcon, miniButton, miniButtonClicked, miniRedButtonClicked, loginButton;
	private int[] categoryWeights;
	private boolean courseBoolean, semesterBoolean, categoryBoolean, assignmentBoolean, secondaryCourseBoolean, setGradeBoolean, secondarySemesterBoolean;

//TODO: Clean up code
	
	public View(){
		//JFrames
		this.mainWindow = new JFrame();
		this.mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainWindow.setSize(600, 500);
		this.secondWindow = new JFrame();
		this.secondWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.secondWindow.setSize(600, 500);

		//JButtons
		this.addAssignmentBtn = new JButton("Add an assignment");
		this.addAssignmentBtn.addActionListener(this);

		this.addCategory = new JButton("Add Category");
		this.addCategory.addActionListener(this);

		this.addCourseBtn = new JButton("Add a course");
		this.addCourseBtn.addActionListener(this);

		this.addSemesterBtn = new JButton("Add a Semester");
		this.addSemesterBtn.addActionListener(this);

		this.assignmentBackBtn = new JButton("Back");
		this.assignmentBackBtn.addActionListener(this);

		this.assignmentCancel = new JButton("Cancel");
		this.assignmentCancel.addActionListener(this);

		this.categoryBackBtn = new JButton("Back");
		this.categoryBackBtn.addActionListener(this);

		this.categoryCancel = new JButton("Cancel");
		this.categoryCancel.addActionListener(this);

		this.courseBackBtn = new JButton("Back");
		this.courseBackBtn.addActionListener(this);

		this.courseCancel = new JButton("Cancel");
		this.courseCancel.addActionListener(this);

		this.createAccountBtn = new JButton("Create Account");
		this.createAccountBtn.addActionListener(this);

		this.createAssignment = new JButton("Create Assignment");
		this.createAssignment.addActionListener(this);

		this.createCategory = new JButton("Create Category");
		this.createCategory.addActionListener(this);

		this.createCourse = new JButton("Create Course");
		this.createCourse.addActionListener(this);

		this.createSemesterBtn = new JButton("Create Semester");
		this.createSemesterBtn.addActionListener(this);

		this.exitBtn = new JButton("Exit");
		this.exitBtn.addActionListener(this);

		this.loginBtn = new JButton("login");
		this.loginBtn.addActionListener(this);

		this.newAccountBtn = new JButton("New Account");
		this.newAccountBtn.addActionListener(this);

		this.removeAssignmentBtn = new JButton("Remove");
		this.removeAssignmentBtn.addActionListener(this);
		
		this.removeCategoryBtn = new JButton("Remove");
		this.removeCategoryBtn.addActionListener(this);
		
		this.removeCourseBtn = new JButton("Remove");
		this.removeCourseBtn.addActionListener(this);
		
		this.removeSemesterBtn = new JButton("Remove");
		this.removeSemesterBtn.addActionListener(this);
		
		this.semesterCancel = new JButton("Cancel");
		this.semesterCancel.addActionListener(this);

		this.setDesiredGrade = new JButton("Set Desired Grade");
		this.setDesiredGrade.addActionListener(this);

		this.setGradeBtn = new JButton("Set Grade");
		this.setGradeBtn.addActionListener(this);

		this.viewAssignments = new JButton("View Assignments");
		this.viewAssignments.addActionListener(this);
		
		this.viewCategories = new JButton("View Assignments");
		this.viewCategories.addActionListener(this);
		
		this.viewCourseBtn = new JButton("View Courses");
		this.viewCourseBtn.addActionListener(this);

		//JTextFields and JPasswordFields
		this.username = new JTextField(10);
		this.password = new JPasswordField();
		this.name = new JTextField(10);
		this.newUsername = new JTextField(10);
		this.credits = new JTextField(10);
		this.weightTxt = new JTextField(10);
		this.possibleGrade = new JTextField(10);
		this.grade = new JTextField();
		this.newPassword = new JPasswordField();
		this.confirmPassword = new JPasswordField();

		//JLabels
		this.usernameLbl = new JLabel("Username");
		this.passwordLbl = new JLabel("Password");
		this.nameLbl = new JLabel("  Name:");
		this.newUsernameLbl = new JLabel("Username:");
		this.newPasswordLbl = new JLabel("Password:");
		this.confirmPasswordLbl = new JLabel("Confirm");
		this.creditsLbl = new JLabel("Credits:");
		this.weightLbl = new JLabel("Weight");
		this.possibleGradeLbl = new JLabel("Possible Grade:");
		this.assignmentCategoriesLbl = new JLabel("    Categories: ");
		this.gradeLbl = new JLabel("Grade:");

		this.currentSemester = 0;
		courseBackground = new Background("/CoursesPage.png");
		loginBackground = new Background("/LoginWindow.png");

		//Images
		this.buttonIcon = new ImageIcon(getClass().getResource("/Button.png"));
		this.buttonClickedIcon = new ImageIcon(getClass().getResource("/ButtonClicked.png"));
		this.miniButton = new ImageIcon(getClass().getResource("/smallBlueButton.png"));
		this.miniButtonClicked = new ImageIcon(getClass().getResource("/MiniButtonClicked.png"));
		this.miniRedButtonClicked = new ImageIcon(getClass().getResource("/MiniRedButtonClicked.png"));
		this.loginButton = new ImageIcon(getClass().getResource("/LoginButton.png"));
		this.courseYBtnPosition = 88 + (int)(buttonIcon.getIconHeight() - 1);
		this.assignmentYBtnPosition = 88 + (int)(buttonIcon.getIconHeight() - 1);
		this.categoryYBtnPosition = 88 + (int)(buttonIcon.getIconHeight() - 1);

		//Booleans
		this.courseBoolean = true;
		this.semesterBoolean = true;
		this.categoryBoolean = true;
		this.assignmentBoolean = true;
		this.secondaryCourseBoolean = true;
		this.setGradeBoolean = true;
		this.secondarySemesterBoolean = true;

		//Other
		this.semesters = new ArrayList<Semester>();
		this.assignmentCategoriesBox = new JComboBox();
		this.categoryWeights = new int[1];
		this.categoryWeights[0] = 100;
	}

	/*
	 * Displays the login screen
	 */
	@SuppressWarnings("static-access")
	public void start(){
		loginBackground.removeAll();
		loginBackground.revalidate();
		loginBackground.setLayout(null);

		//Textbox Icon creation
		username.setBackground(new Color(26, 101, 199));
		password.setBackground(new Color(26, 101, 199));

		//Button Icon creation
		loginBtn.setIcon(loginButton);
		loginBtn.setPressedIcon(loginButton);
		loginBtn.setHorizontalTextPosition(loginBtn.CENTER);
		loginBtn.setVerticalTextPosition(loginBtn.CENTER);
		loginBtn.setBorder(null);

		newAccountBtn.setIcon(loginButton);
		newAccountBtn.setPressedIcon(loginButton);
		newAccountBtn.setHorizontalTextPosition(newAccountBtn.CENTER);
		newAccountBtn.setVerticalTextPosition(newAccountBtn.CENTER);
		newAccountBtn.setBorder(null);

		//Adding to panel
		usernameLbl.setBounds(62, 193, (int)usernameLbl.getPreferredSize().getWidth(), (int)usernameLbl.getPreferredSize().getHeight());
		usernameLbl.setFont(new Font("Serif", Font.PLAIN, 14));
		loginBackground.add(usernameLbl);

		username.setBounds(131, 188, 154, 32);
		username.setFont(new Font("Serif", Font.PLAIN, 14));
		loginBackground.add(username);

		passwordLbl.setBounds(288, 193, (int)passwordLbl.getPreferredSize().getWidth(), (int)passwordLbl.getPreferredSize().getHeight());
		passwordLbl.setFont(new Font("Serif", Font.PLAIN, 14));
		loginBackground.add(passwordLbl);

		password.setBounds(354, 189, 154, 32);
		loginBackground.add(password);

		loginBtn.setBounds(131, 237, (int)loginBtn.getPreferredSize().getWidth(), (int)loginBtn.getPreferredSize().getHeight());
		loginBtn.setFont(new Font("Serif", Font.PLAIN, 14));
		loginBackground.add(loginBtn);

		newAccountBtn.setBounds(354, 237, (int)newAccountBtn.getPreferredSize().getWidth(), (int)newAccountBtn.getPreferredSize().getHeight());
		newAccountBtn.setFont(new Font("Serif", Font.PLAIN, 14));
		loginBackground.add(newAccountBtn);

		mainWindow.add(loginBackground);
		mainWindow.setVisible(true);
		mainWindow.repaint();
	}

	/*
	 * Account Window
	 */
	@SuppressWarnings("static-access")
	private void accountWindow(){
		loginBackground.removeAll();
		loginBackground.revalidate();
		loginBackground.setLayout(null);

		//Textbox Icon creation
		name.setBackground(new Color(26, 101, 199));
		newUsername.setBackground(new Color(26, 101, 199));
		newPassword.setBackground(new Color(26, 101, 199));
		confirmPassword.setBackground(new Color(26, 101, 199));

		//Button Icon creation
		createAccountBtn.setIcon(loginButton);
		createAccountBtn.setPressedIcon(loginButton);
		createAccountBtn.setHorizontalTextPosition(createAccountBtn.CENTER);
		createAccountBtn.setVerticalTextPosition(createAccountBtn.CENTER);
		createAccountBtn.setBorder(null);

		exitBtn.setIcon(loginButton);
		exitBtn.setPressedIcon(loginButton);
		exitBtn.setHorizontalTextPosition(exitBtn.CENTER);
		exitBtn.setVerticalTextPosition(exitBtn.CENTER);
		exitBtn.setBorder(null);

		JLabel label = new JLabel("password:");
		//Adding to panel
		nameLbl.setBounds(68, 186, (int)nameLbl.getPreferredSize().getWidth(), (int)nameLbl.getPreferredSize().getHeight());
		nameLbl.setFont(new Font("Serif", Font.PLAIN, 14));
		loginBackground.add(nameLbl);

		name.setBounds(135, 182, 154, 32);
		name.setFont(new Font("Serif", Font.PLAIN, 14));
		loginBackground.add(name);

		newUsernameLbl.setBounds(292, 190, (int)newUsernameLbl.getPreferredSize().getWidth(), (int)newUsernameLbl.getPreferredSize().getHeight());
		newUsernameLbl.setFont(new Font("Serif", Font.PLAIN, 14));
		loginBackground.add(newUsernameLbl);

		newUsername.setBounds(358, 182, 154, 32);
		loginBackground.add(newUsername);

		newPasswordLbl.setBounds(75, 232, (int)newPasswordLbl.getPreferredSize().getWidth(), (int)newPasswordLbl.getPreferredSize().getHeight());
		newPasswordLbl.setFont(new Font("Serif", Font.PLAIN, 14));
		loginBackground.add(newPasswordLbl);

		newPassword.setBounds(135, 222, 154, 32);
		newPassword.setFont(new Font("Serif", Font.PLAIN, 14));
		loginBackground.add(newPassword);

		confirmPasswordLbl.setBounds(296, 222, (int)confirmPasswordLbl.getPreferredSize().getWidth(), (int)confirmPasswordLbl.getPreferredSize().getHeight());
		confirmPasswordLbl.setFont(new Font("Serif", Font.PLAIN, 14));
		loginBackground.add(confirmPasswordLbl);

		label.setBounds(296, 237, (int)label.getPreferredSize().getWidth(), (int)label.getPreferredSize().getHeight());
		label.setFont(new Font("Serif", Font.PLAIN, 14));
		loginBackground.add(label);

		confirmPassword.setBounds(358, 222, 154, 32);
		confirmPassword.setFont(new Font("Serif", Font.PLAIN, 14));
		loginBackground.add(confirmPassword);

		createAccountBtn.setBounds(135, 264, (int)createAccountBtn.getPreferredSize().getWidth(), (int)createAccountBtn.getPreferredSize().getHeight());
		createAccountBtn.setFont(new Font("Serif", Font.PLAIN, 14));
		loginBackground.add(createAccountBtn);

		exitBtn.setBounds(358, 264, (int)exitBtn.getPreferredSize().getWidth(), (int)exitBtn.getPreferredSize().getHeight());
		exitBtn.setFont(new Font("Serif", Font.PLAIN, 14));
		loginBackground.add(exitBtn);

		mainWindow.add(loginBackground);
		mainWindow.setVisible(true);
		mainWindow.repaint();
	}

	//TODO: fix horizontally aligned semesters.
	/*
	 * Semester Window
	 */
	@SuppressWarnings("static-access")
	private void semesterWindow(){
		if(semesterBoolean){
			courseBackground.removeAll();
			courseBackground.revalidate();
			courseBackground.setLayout(null);

			semesterYBtnPosition = 88 + (int)(buttonIcon.getIconHeight() - 1);
			semesterButtons = new JButton[semesters.size()];
//			buttons.setBounds(12, semesterYBtnPosition, 279, (450 - semesterYBtnPosition));
//			buttons.setOpaque(false);
//			buttons.getViewport().setOpaque(false);
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			panel.setLayout(new GridLayout(semesters.size(), 1));
			
			for(int i = 0; i < semesterButtons.length; i ++){
				semesterButtons[i] = new JButton(semesters.get(i).getName() + " GPA: " + semesters.get(i).getGPA());
				semesterButtons[i].setIcon(buttonIcon);
				semesterButtons[i].setPressedIcon(buttonClickedIcon);
				semesterButtons[i].setHorizontalTextPosition(semesterButtons[i].CENTER);
				semesterButtons[i].setVerticalTextPosition(semesterButtons[i].CENTER);
				semesterButtons[i].setFont(new Font("Serif", Font.PLAIN, 14));
				semesterButtons[i].setForeground(new Color(51, 204, 255));
				semesterButtons[i].setBorder(null);
				semesterButtons[i].setBounds(12, semesterYBtnPosition, (int)semesterButtons[i].getPreferredSize().getWidth(), (int)semesterButtons[i].getPreferredSize().getHeight());
				semesterButtons[i].addActionListener(this);
				courseBackground.add(semesterButtons[i]);
				semesterYBtnPosition += (int)(buttonIcon.getIconHeight() - 1);
			}
			addSemesterBtn.setIcon(buttonIcon);
			addSemesterBtn.setPressedIcon(buttonClickedIcon);
			addSemesterBtn.setHorizontalTextPosition(addSemesterBtn.CENTER);
			addSemesterBtn.setVerticalTextPosition(addSemesterBtn.CENTER);
			addSemesterBtn.setForeground(new Color(51, 204, 255));
			addSemesterBtn.setBorder(null);
			addSemesterBtn.setBounds(12, semesterYBtnPosition, (int)addSemesterBtn.getPreferredSize().getWidth(), (int)addSemesterBtn.getPreferredSize().getHeight());
			courseBackground.add(addSemesterBtn);

			secondWindow.add(courseBackground);
			secondWindow.setVisible(true);
			secondWindow.repaint();
		}
	}

	/*
	 * Course Window
	 */
	@SuppressWarnings("static-access")
	private void courseWindow(int semesterIndex){
		secondWindow.getContentPane().removeAll();
		courseBackground.removeAll();
		courseBackground.revalidate();
		secondWindow.repaint();
		secondWindow.setLayout(null);

		courseBackground.setLayout(null);

		courseYBtnPosition = 88 + (int)(buttonIcon.getIconHeight() - 1);
		courses = new JButton[semesters.get(semesterIndex).getNumOfCourses()];

		for(int i = 0; i < courses.length; i ++){
			courses[i] = new JButton(semesters.get(semesterIndex).getCourseName(i) + " - " + semesters.get(semesterIndex).getCourse(i).getTotalCharGrade());
			courses[i].addActionListener(this);
			courses[i].setIcon(buttonIcon);
			courses[i].setPressedIcon(buttonClickedIcon);
			courses[i].setHorizontalTextPosition(courses[i].CENTER);
			courses[i].setVerticalTextPosition(courses[i].CENTER);
			courses[i].setFont(new Font("Serif", Font.PLAIN, 14));
			courses[i].setForeground(new Color(51, 204, 255));
			courses[i].setBorder(null);
			courses[i].setBounds(12, courseYBtnPosition, (int)courses[i].getPreferredSize().getWidth(), (int)courses[i].getPreferredSize().getHeight());
			courseYBtnPosition += (int)(buttonIcon.getIconHeight() - 1);

			courseBackground.add(courses[i]);
		}

		addCourseBtn.setIcon(buttonIcon);
		addCourseBtn.setPressedIcon(buttonClickedIcon);
		addCourseBtn.setHorizontalTextPosition(addCourseBtn.CENTER);
		addCourseBtn.setVerticalTextPosition(addCourseBtn.CENTER);
		addCourseBtn.setForeground(new Color(51, 204, 255));
		addCourseBtn.setBorder(null);
		addCourseBtn.setBounds(12, courseYBtnPosition, (int)addCourseBtn.getPreferredSize().getWidth(), (int)addCourseBtn.getPreferredSize().getHeight());
		courseBackground.add(addCourseBtn);
		courseYBtnPosition += (int)(buttonIcon.getIconHeight() - 1);

		courseBackBtn.setIcon(buttonIcon);
		courseBackBtn.setPressedIcon(buttonClickedIcon);
		courseBackBtn.setHorizontalTextPosition(courseBackBtn.CENTER);
		courseBackBtn.setVerticalTextPosition(courseBackBtn.CENTER);
		courseBackBtn.setForeground(new Color(51, 204, 255));
		courseBackBtn.setBorder(null);
		courseBackBtn.setBounds(12, courseYBtnPosition, (int)courseBackBtn.getPreferredSize().getWidth(), (int)courseBackBtn.getPreferredSize().getHeight());
		courseBackground.add(courseBackBtn);

		courseBackground.setOpaque(false);
		courseBackground.setBounds(0, 0, (int)courseBackground.getPreferredSize().getWidth(), (int)courseBackground.getPreferredSize().getHeight());

		secondWindow.add(courseBackground);
		secondWindow.setVisible(true);
		secondWindow.repaint();
	}

	/*
	 * Category Window
	 */
	@SuppressWarnings("static-access")	
	private void categoryWindow(int semesterIndex, int courseIndex){
		courseBackground.removeAll();
		courseBackground.revalidate();
		courseBackground.setLayout(null);

		AssignmentCategory[] assignmentCategories = semesters.get(semesterIndex).getCourse(courseIndex).getCategories();
		categoryButtons = new JButton[assignmentCategories.length];
		categoryYBtnPosition = 88 + (int)(buttonIcon.getIconHeight() - 1);

		for(int i = 0; i < categoryButtons.length; i ++){
			categoryButtons[i] = new JButton(assignmentCategories[i].getName() + " - " + assignmentCategories[i].getWeight());
			categoryButtons[i].addActionListener(this);
			categoryButtons[i].setIcon(buttonIcon);
			categoryButtons[i].setPressedIcon(buttonClickedIcon);
			categoryButtons[i].setHorizontalTextPosition(categoryButtons[i].CENTER);
			categoryButtons[i].setVerticalTextPosition(categoryButtons[i].CENTER);
			categoryButtons[i].setForeground(new Color(51, 204, 255));
			categoryButtons[i].setFont(new Font("Serif", Font.PLAIN, 14));
			categoryButtons[i].setBorder(null);
			categoryButtons[i].setBounds(12, categoryYBtnPosition, (int)categoryButtons[i].getPreferredSize().getWidth(), (int)categoryButtons[i].getPreferredSize().getHeight());
			categoryYBtnPosition += (int)(buttonIcon.getIconHeight() - 1);

			courseBackground.add(categoryButtons[i]);
			assignmentCategoriesBox.addItem(assignmentCategories[i].getName());
		}

		addCategory.setIcon(buttonIcon);
		addCategory.setPressedIcon(buttonClickedIcon);
		addCategory.setHorizontalTextPosition(addCategory.CENTER);
		addCategory.setVerticalTextPosition(addCategory.CENTER);
		addCategory.setForeground(new Color(51, 204, 255));
		addCategory.setBorder(null);
		addCategory.setBounds(12, categoryYBtnPosition, (int)addCategory.getPreferredSize().getWidth(), (int)addCategory.getPreferredSize().getHeight());
		courseBackground.add(addCategory);
		categoryYBtnPosition += (int)(buttonIcon.getIconHeight() - 1);

		categoryBackBtn.setIcon(buttonIcon);
		categoryBackBtn.setPressedIcon(buttonClickedIcon);
		categoryBackBtn.setHorizontalTextPosition(categoryBackBtn.CENTER);
		categoryBackBtn.setVerticalTextPosition(categoryBackBtn.CENTER);
		categoryBackBtn.setForeground(new Color(51, 204, 255));
		categoryBackBtn.setBorder(null);
		categoryBackBtn.setBounds(12, categoryYBtnPosition, (int)categoryBackBtn.getPreferredSize().getWidth(), (int)categoryBackBtn.getPreferredSize().getHeight());
		courseBackground.add(categoryBackBtn);

		courseBackground.setOpaque(false);
		courseBackground.setBounds(0, 0, (int)courseBackground.getPreferredSize().getWidth(), (int)courseBackground.getPreferredSize().getHeight());

		secondWindow.add(courseBackground);
		secondWindow.setVisible(true);
		secondWindow.repaint();
	}

	/*
	 * Assignment Window
	 */
	@SuppressWarnings("static-access")	
	private void assignmentWindow(int semesterIndex, int courseIndex, int categoryIndex){
		courseBackground.removeAll();
		courseBackground.revalidate();

		courseBackground.setLayout(null);

		this.assignmentYBtnPosition = 88 + (int)(buttonIcon.getIconHeight() - 1);

		String[] names = semesters.get(semesterIndex).getCourse(courseIndex).getCategory(categoryIndex).getAssignmentNames();
		assignments = new JButton[names.length];

		assignmentYBtnPosition = 88 + (int)(buttonIcon.getIconHeight() - 1);

		for(int i = 0; i < assignments.length; i ++){
			assignments[i] = new JButton(names[i]);
			assignments[i].addActionListener(this);
			assignments[i].setIcon(buttonIcon);
			assignments[i].setPressedIcon(buttonClickedIcon);
			assignments[i].setHorizontalTextPosition(assignments[i].CENTER);
			assignments[i].setVerticalTextPosition(assignments[i].CENTER);
			assignments[i].setFont(new Font("Serif", Font.PLAIN, 14));
			assignments[i].setForeground(new Color(51, 204, 255));
			assignments[i].setBorder(null);
			assignments[i].setBounds(12, assignmentYBtnPosition, (int)assignments[i].getPreferredSize().getWidth(), (int)assignments[i].getPreferredSize().getHeight());
			assignmentYBtnPosition += (int)(buttonIcon.getIconHeight() - 1);

			courseBackground.add(assignments[i]);
		}

		addAssignmentBtn.setIcon(buttonIcon);
		addAssignmentBtn.setPressedIcon(buttonClickedIcon);
		addAssignmentBtn.setHorizontalTextPosition(addAssignmentBtn.CENTER);
		addAssignmentBtn.setVerticalTextPosition(addAssignmentBtn.CENTER);
		addAssignmentBtn.setForeground(new Color(51, 204, 255));
		addAssignmentBtn.setBorder(null);
		addAssignmentBtn.setBounds(12, assignmentYBtnPosition, (int)addAssignmentBtn.getPreferredSize().getWidth(), (int)addAssignmentBtn.getPreferredSize().getHeight());
		courseBackground.add(addAssignmentBtn);
		assignmentYBtnPosition += (int)(buttonIcon.getIconHeight() - 1);

		assignmentBackBtn.setIcon(buttonIcon);
		assignmentBackBtn.setPressedIcon(buttonClickedIcon);
		assignmentBackBtn.setHorizontalTextPosition(assignmentBackBtn.CENTER);
		assignmentBackBtn.setVerticalTextPosition(assignmentBackBtn.CENTER);
		assignmentBackBtn.setForeground(new Color(51, 204, 255));
		assignmentBackBtn.setBorder(null);
		assignmentBackBtn.setBounds(12, assignmentYBtnPosition, (int)assignmentBackBtn.getPreferredSize().getWidth(), (int)assignmentBackBtn.getPreferredSize().getHeight());
		courseBackground.add(assignmentBackBtn);

		courseBackground.setOpaque(false);
		courseBackground.setBounds(0, 0, (int)courseBackground.getPreferredSize().getWidth(), (int)courseBackground.getPreferredSize().getHeight());

		secondWindow.add(courseBackground);
		secondWindow.setVisible(true);
		secondWindow.repaint();
	}

	/*
	 * Set Assignment Grade
Displays side Menu for setting assignmentGrades
	 */
	@SuppressWarnings("static-access")
	private void assignmentSideMenu(int assignmentNum){
		if(setGradeBoolean){//Tries to make sure multiple buttons aren't displayed on top of each other
			courseBackground.remove(name);
			courseBackground.remove(possibleGradeLbl);
			courseBackground.remove(possibleGrade);
			courseBackground.remove(assignmentCategoriesBox);
			courseBackground.remove(createAssignment);
			courseBackground.remove(assignmentCancel);
			courseBackground.remove(assignmentCategoriesLbl);

			int sideYBtnPosition = 88 + (int)(buttonIcon.getIconHeight() - 1); //sets Y space between components

			//Acquired Grade
			gradeLbl.setBounds(340, (sideYBtnPosition + 4), (int)gradeLbl.getPreferredSize().getWidth(), (int)gradeLbl.getPreferredSize().getHeight());
			courseBackground.add(gradeLbl);

			this.grade.setBackground(new Color(51, 204, 255));
			this.grade.setOpaque(false);
			this.grade.setBounds(391, sideYBtnPosition, 119, (int)grade.getPreferredSize().getHeight());
			this.grade.setText("" + semesters.get(currentSemester).getCourse(currentCourse).getCategory(currentCategory).getAssignment(currentAssignment).getAcquiredGrade());
			courseBackground.add(grade);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);

			//PossibleGrade
			this.possibleGrade.setBackground(new Color(51, 204, 255));
			this.possibleGrade.setOpaque(false);
			this.possibleGrade.setBounds(391, sideYBtnPosition, 119, (int)possibleGrade.getPreferredSize().getHeight());
			this.possibleGrade.setText(semesters.get(currentSemester).getCourse(currentCourse).getCategory(currentCategory).getAssignment(assignmentNum).getPossibleGrade() + "");
			courseBackground.add(possibleGrade);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);

			//Buttons
			setGradeBtn.setIcon(miniButton);
			setGradeBtn.setPressedIcon(miniButtonClicked);
			setGradeBtn.setHorizontalTextPosition(setGradeBtn.CENTER);
			setGradeBtn.setVerticalTextPosition(setGradeBtn.CENTER);
			setGradeBtn.setBorder(null);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() - 1);
			this.setGradeBtn.setBounds(391, sideYBtnPosition, (int)setGradeBtn.getPreferredSize().getWidth(), (int)setGradeBtn.getPreferredSize().getHeight());
			courseBackground.add(setGradeBtn);

			removeAssignmentBtn.setIcon(miniButton);
			removeAssignmentBtn.setPressedIcon(miniRedButtonClicked);
			removeAssignmentBtn.setHorizontalTextPosition(removeAssignmentBtn.CENTER);
			removeAssignmentBtn.setVerticalTextPosition(removeAssignmentBtn.CENTER);
			removeAssignmentBtn.setBorder(null);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);
			this.removeAssignmentBtn.setBounds(391, sideYBtnPosition, (int)removeAssignmentBtn.getPreferredSize().getWidth(), (int)removeAssignmentBtn.getPreferredSize().getHeight());
			courseBackground.add(removeAssignmentBtn);

			assignmentCancel.setIcon(miniButton);
			assignmentCancel.setPressedIcon(miniRedButtonClicked);
			assignmentCancel.setHorizontalTextPosition(assignmentCancel.CENTER);
			assignmentCancel.setVerticalTextPosition(assignmentCancel.CENTER);
			assignmentCancel.setBorder(null);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);
			this.assignmentCancel.setBounds(391, sideYBtnPosition, (int)assignmentCancel.getPreferredSize().getWidth(), (int)assignmentCancel.getPreferredSize().getHeight());
			courseBackground.add(assignmentCancel);

			removeAssignmentBtn.setIcon(miniButton);
			removeAssignmentBtn.setPressedIcon(miniRedButtonClicked);
			removeAssignmentBtn.setHorizontalTextPosition(removeAssignmentBtn.CENTER);
			removeAssignmentBtn.setVerticalTextPosition(removeAssignmentBtn.CENTER);
			removeAssignmentBtn.setBorder(null);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);
			this.removeAssignmentBtn.setBounds(391, sideYBtnPosition, (int)removeAssignmentBtn.getPreferredSize().getWidth(), (int)removeAssignmentBtn.getPreferredSize().getHeight());
			courseBackground.add(removeAssignmentBtn);
			
			secondWindow.repaint();
		}
	}

	/*
	 * Login
	 * Makes sure user is a valid user
	 * Loads current user's information
	 */
	public void login(String username, String password){
		currentUser = findUser(username);

		if(currentUser != null && currentUser.checkPassword(password)){
			loadSemesters();
			semesterWindow();
			mainWindow.setVisible(false);
		}
		else{
			JOptionPane.showMessageDialog(null, "Incorrect username or password.");
		}
	}

	/*
	 * Find User
	 * finds returning user's files
	 */
	public Users findUser(String username){
		Users user = null;

		try {
			FileInputStream userFile = new FileInputStream("src/" + username + ".gt");
			ObjectInputStream in = new ObjectInputStream(userFile);
			user = (Users) in.readObject();
			in.close();
			userFile.close();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}

		return user;
	}

	/*
	 * Create Account
	 * Creates a brand new user
	 */
	public void createAccount(String username, String password, String passwordCheck){
		String pattern= "^[a-zA-Z0-9]*$";
		if(username.matches(pattern) && password.matches(pattern)){
		if(findUser(username) == null){//makes sure user doesn't already exist
			if(password.equals(passwordCheck)){//makes sure passwords match
				try {
					currentUser = new Users(username, password, name.getText());
					FileOutputStream userFile = new FileOutputStream("src/" + username + ".gt");
					ObjectOutputStream out = new ObjectOutputStream(userFile);
					out.writeObject(currentUser);
					out.close();
					userFile.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
				loadSemesters();//loads the newly created semester
				semesterWindow();
			}
			else
				JOptionPane.showMessageDialog(null, "Passwords do not match");
		}
		else
			JOptionPane.showMessageDialog(null, "Username already exists");
		}
		else
			JOptionPane.showMessageDialog(null, "Please enter an alphanumeric");
	}

	/*
	 * Load Semesters
	 * Loads the semester for the current user. 
	 */
	@SuppressWarnings("unchecked")

	public void loadSemesters(){
		try {
			FileInputStream semestersFile = new FileInputStream("src/" + currentUser.getUsername() + "Semesters.gt");
			ObjectInputStream in = new ObjectInputStream(semestersFile);
			semesters = (ArrayList<Semester>) in.readObject();
			in.close();
			semestersFile.close();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}

	}

	/*
	 * Write Semesters
	 * Writes a serializable file for the current semester.
	 */
	public void writeSemesters(){
		try {
			FileOutputStream semestersFile = new FileOutputStream("src/" + currentUser.getUsername() + "Semesters.gt");
			ObjectOutputStream out = new ObjectOutputStream(semestersFile);
			out.writeObject(semesters);
			out.close();
			semestersFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Create Semester
	 * Creats a new semester
	 */
	@SuppressWarnings("static-access")
	public void createSemester(String semesterName){
		semesters.add(new Semester(semesterName));
		JButton[] temp = semesterButtons;
		semesterButtons = new JButton[semesterButtons.length + 1];

		for(int i = 0; i < temp.length; i ++){
			semesterButtons[i] = temp[i];
		}

		semesterButtons[semesterButtons.length -1] = new JButton(semesterName);
		semesterButtons[semesterButtons.length -1].addActionListener(this);
		semesterButtons[semesterButtons.length -1].setIcon(buttonIcon);
		semesterButtons[semesterButtons.length -1].setPressedIcon(buttonClickedIcon);
		semesterButtons[semesterButtons.length -1].setHorizontalTextPosition(semesterButtons[semesterButtons.length -1].CENTER);
		semesterButtons[semesterButtons.length -1].setVerticalTextPosition(semesterButtons[semesterButtons.length -1].CENTER);
		semesterButtons[semesterButtons.length -1].setForeground(new Color(51, 204, 255));
		semesterButtons[semesterButtons.length -1].setBorder(null);
		semesterButtons[semesterButtons.length -1].setBounds(12, semesterYBtnPosition, (int)semesterButtons[semesterButtons.length -1].getPreferredSize().getWidth(), (int)semesterButtons[semesterButtons.length -1].getPreferredSize().getHeight());
		courseBackground.add(semesterButtons[semesterButtons.length -1]);

		semesterYBtnPosition += (int)(buttonIcon.getIconHeight() - 1);
		addSemesterBtn.setBounds(12, semesterYBtnPosition, (int)addSemesterBtn.getPreferredSize().getWidth(), (int)addSemesterBtn.getPreferredSize().getHeight());

		secondWindow.repaint();
		writeSemesters();
	}

	/*
	 * Add Course Menu
	 * Creates a window for adding courses.
	 */
	@SuppressWarnings("static-access")
	public void addCourseMenu(){
		if(courseBoolean){
			int sideYBtnPosition = 88 + (int)(buttonIcon.getIconHeight() - 1);

			nameLbl.setBounds(340, (sideYBtnPosition + 4), (int)nameLbl.getPreferredSize().getWidth(), (int)nameLbl.getPreferredSize().getHeight());
			courseBackground.add(nameLbl);

			this.name.setBackground(new Color(51, 204, 255));
			this.name.setOpaque(false);
			this.name.setBounds(391, sideYBtnPosition, 119, (int)name.getPreferredSize().getHeight());
			courseBackground.add(name);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);

			creditsLbl.setBounds(340, (sideYBtnPosition + 4), (int)creditsLbl.getPreferredSize().getWidth(), (int)creditsLbl.getPreferredSize().getHeight());
			courseBackground.add(creditsLbl);

			this.credits.setBackground(new Color(51, 204, 255));
			this.credits.setBounds(391, sideYBtnPosition, 119, (int)credits.getPreferredSize().getHeight());
			courseBackground.add(credits);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 6);

			createCourse.setIcon(miniButton);
			createCourse.setPressedIcon(miniButtonClicked);
			createCourse.setHorizontalTextPosition(createCourse.CENTER);
			createCourse.setVerticalTextPosition(createCourse.CENTER);
			createCourse.setBorder(null);
			this.createCourse.setBounds(391, sideYBtnPosition, (int)createCourse.getPreferredSize().getWidth(), (int)createCourse.getPreferredSize().getHeight());
			courseBackground.add(createCourse);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);


			courseCancel.setIcon(miniButton);
			courseCancel.setPressedIcon(miniButtonClicked);
			courseCancel.setHorizontalTextPosition(courseCancel.CENTER);
			courseCancel.setVerticalTextPosition(courseCancel.CENTER);
			courseCancel.setBorder(null);
			this.courseCancel.setBounds(391, sideYBtnPosition, (int)courseCancel.getPreferredSize().getWidth(), (int)courseCancel.getPreferredSize().getHeight());
			courseBackground.add(courseCancel);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);
			secondWindow.repaint();
		}
	}

	/*
	 * Secondary Course Menu
	 */
	@SuppressWarnings("static-access")
	private void secondaryCoursesMenu(){
		if(secondaryCourseBoolean){
			int sideYBtnPosition = 88 + (int)(buttonIcon.getIconHeight() - 1);
			viewCategories.setIcon(miniButton);
			viewCategories.setPressedIcon(miniButtonClicked);
			viewCategories.setHorizontalTextPosition(viewCategories.CENTER);
			viewCategories.setVerticalTextPosition(viewCategories.CENTER);
			viewCategories.setBorder(null);
			this.viewCategories.setBounds(391, sideYBtnPosition, (int)viewCategories.getPreferredSize().getWidth(), (int)viewCategories.getPreferredSize().getHeight());
			courseBackground.add(viewCategories);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);


			setDesiredGrade.setIcon(miniButton);
			setDesiredGrade.setPressedIcon(miniButtonClicked);
			setDesiredGrade.setHorizontalTextPosition(setDesiredGrade.CENTER);
			setDesiredGrade.setVerticalTextPosition(setDesiredGrade.CENTER);
			setDesiredGrade.setBorder(null);
			this.setDesiredGrade.setBounds(391, sideYBtnPosition, (int)setDesiredGrade.getPreferredSize().getWidth(), (int)setDesiredGrade.getPreferredSize().getHeight());
			courseBackground.add(setDesiredGrade);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);
			
			removeCourseBtn.setIcon(miniButton);
			removeCourseBtn.setPressedIcon(miniRedButtonClicked);
			removeCourseBtn.setHorizontalTextPosition(removeCourseBtn.CENTER);
			removeCourseBtn.setVerticalTextPosition(removeCourseBtn.CENTER);
			removeCourseBtn.setBorder(null);
			removeCourseBtn.setBounds(391, sideYBtnPosition, (int)removeCourseBtn.getPreferredSize().getWidth(), (int)removeCourseBtn.getPreferredSize().getHeight());
			courseBackground.add(removeCourseBtn);
			secondWindow.repaint();
		}
	}
	
	/*
	 * Add Category Menu
	 * Creates a window for adding Categories.
	 */
	@SuppressWarnings("static-access")
	private void addCategoryMenu(){
		if(categoryBoolean){
			int sideYBtnPosition = 88 + (int)(buttonIcon.getIconHeight() - 1);

			nameLbl.setBounds(340, (sideYBtnPosition + 4), (int)nameLbl.getPreferredSize().getWidth(), (int)nameLbl.getPreferredSize().getHeight());
			courseBackground.add(nameLbl);

			this.name.setBackground(new Color(51, 204, 255));
			this.name.setOpaque(false);
			this.name.setBounds(391, sideYBtnPosition, 119, (int)name.getPreferredSize().getHeight());
			courseBackground.add(name);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);

			weightLbl.setBounds(340, (sideYBtnPosition + 4), (int)weightLbl.getPreferredSize().getWidth(), (int)weightLbl.getPreferredSize().getHeight());
			courseBackground.add(weightLbl);

			this.weightTxt.setBackground(new Color(51, 204, 255));
			this.weightTxt.setBounds(391, sideYBtnPosition, 119, (int)weightTxt.getPreferredSize().getHeight());
			courseBackground.add(weightTxt);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 6);

			createCategory.setIcon(miniButton);
			createCategory.setPressedIcon(miniButtonClicked);
			createCategory.setHorizontalTextPosition(createCategory.CENTER);
			createCategory.setVerticalTextPosition(createCategory.CENTER);
			createCategory.setBorder(null);
			this.createCategory.setBounds(391, sideYBtnPosition, (int)createCategory.getPreferredSize().getWidth(), (int)createCategory.getPreferredSize().getHeight());
			courseBackground.add(createCategory);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);

			categoryCancel.setIcon(miniButton);
			categoryCancel.setPressedIcon(miniButtonClicked);
			categoryCancel.setHorizontalTextPosition(categoryCancel.CENTER);
			categoryCancel.setVerticalTextPosition(categoryCancel.CENTER);
			categoryCancel.setBorder(null);
			this.categoryCancel.setBounds(391, sideYBtnPosition, (int)categoryCancel.getPreferredSize().getWidth(), (int)categoryCancel.getPreferredSize().getHeight());
			courseBackground.add(categoryCancel);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);
			secondWindow.repaint();
		}
	}

	private void secondaryCategoryMenu(){
		if(secondarySemesterBoolean){
			int sideYBtnPosition = 88 + (int)(buttonIcon.getIconHeight() - 1);
			viewAssignments.setIcon(miniButton);
			viewAssignments.setPressedIcon(miniButtonClicked);
			viewAssignments.setHorizontalTextPosition(viewAssignments.CENTER);
			viewAssignments.setVerticalTextPosition(viewAssignments.CENTER);
			viewAssignments.setBorder(null);
			this.viewAssignments.setBounds(391, sideYBtnPosition, (int)viewAssignments.getPreferredSize().getWidth(), (int)viewAssignments.getPreferredSize().getHeight());
			courseBackground.add(viewAssignments);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);
			
			removeCategoryBtn.setIcon(miniButton);
			removeCategoryBtn.setPressedIcon(miniRedButtonClicked);
			removeCategoryBtn.setHorizontalTextPosition(removeCategoryBtn.CENTER);
			removeCategoryBtn.setVerticalTextPosition(removeCategoryBtn.CENTER);
			removeCategoryBtn.setBorder(null);
			removeCategoryBtn.setBounds(391, sideYBtnPosition, (int)removeCategoryBtn.getPreferredSize().getWidth(), (int)removeCategoryBtn.getPreferredSize().getHeight());
			courseBackground.add(removeCategoryBtn);
			secondWindow.repaint();
		}
	}
	
	/*
	 * Add Assignment Menu
	 * Creates a window for adding Assignments.
	 */
	@SuppressWarnings("static-access")
	private void addAssignmentMenu(){
		if(assignmentBoolean){
			courseBackground.remove(grade);
			courseBackground.remove(possibleGrade);
			courseBackground.remove(setGradeBtn);
			courseBackground.remove(removeAssignmentBtn);
			courseBackground.remove(gradeLbl);
			courseBackground.revalidate();

			int sideYBtnPosition = 88 + (int)(buttonIcon.getIconHeight() - 1);

			nameLbl.setBounds(340, (sideYBtnPosition + 4), (int)nameLbl.getPreferredSize().getWidth(), (int)nameLbl.getPreferredSize().getHeight());
			courseBackground.add(nameLbl);

			this.name.setBackground(new Color(51, 204, 255));
			this.name.setOpaque(false);
			this.name.setBounds(391, sideYBtnPosition, 119, (int)name.getPreferredSize().getHeight());
			courseBackground.add(name);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);

			possibleGradeLbl.setBounds(295, (sideYBtnPosition + 4), (int)possibleGradeLbl.getPreferredSize().getWidth(), (int)possibleGradeLbl.getPreferredSize().getHeight());
			courseBackground.add(possibleGradeLbl);

			this.possibleGrade.setBackground(new Color(51, 204, 255));
			this.possibleGrade.setBounds(391, sideYBtnPosition, 119, (int)possibleGrade.getPreferredSize().getHeight());
			courseBackground.add(possibleGrade);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 6);

			createAssignment.setIcon(miniButton);
			createAssignment.setPressedIcon(miniButtonClicked);
			createAssignment.setHorizontalTextPosition(createAssignment.CENTER);
			createAssignment.setVerticalTextPosition(createAssignment.CENTER);
			createAssignment.setBorder(null);
			this.createAssignment.setBounds(391, sideYBtnPosition, (int)createAssignment.getPreferredSize().getWidth(), (int)createAssignment.getPreferredSize().getHeight());
			courseBackground.add(createAssignment);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);

			assignmentCancel.setIcon(miniButton);
			assignmentCancel.setPressedIcon(miniButtonClicked);
			assignmentCancel.setHorizontalTextPosition(assignmentCancel.CENTER);
			assignmentCancel.setVerticalTextPosition(assignmentCancel.CENTER);
			assignmentCancel.setBorder(null);
			this.assignmentCancel.setBounds(391, sideYBtnPosition, (int)assignmentCancel.getPreferredSize().getWidth(), (int)assignmentCancel.getPreferredSize().getHeight());
			courseBackground.add(assignmentCancel);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);
			secondWindow.repaint();
		}
	}

	/*
	 * Add Semester Menu
	 */
	@SuppressWarnings("static-access")
	private void addSemesterMenu(){
		if(semesterBoolean){
			int sideYBtnPosition = 88 + (int)(buttonIcon.getIconHeight() - 1);

			nameLbl.setBounds(340, (sideYBtnPosition + 4), (int)nameLbl.getPreferredSize().getWidth(), (int)nameLbl.getPreferredSize().getHeight());
			courseBackground.add(nameLbl);

			this.name.setBackground(new Color(51, 204, 255));
			this.name.setOpaque(false);
			this.name.setBounds(391, sideYBtnPosition, 119, (int)name.getPreferredSize().getHeight());
			courseBackground.add(name);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);

			createSemesterBtn.setIcon(miniButton);
			createSemesterBtn.setPressedIcon(miniButtonClicked);
			createSemesterBtn.setHorizontalTextPosition(createSemesterBtn.CENTER);
			createSemesterBtn.setVerticalTextPosition(createSemesterBtn.CENTER);
			createSemesterBtn.setBorder(null);
			this.createSemesterBtn.setBounds(391, sideYBtnPosition, (int)createSemesterBtn.getPreferredSize().getWidth(), (int)createSemesterBtn.getPreferredSize().getHeight());
			courseBackground.add(createSemesterBtn);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);


			semesterCancel.setIcon(miniButton);
			semesterCancel.setPressedIcon(miniButtonClicked);
			semesterCancel.setHorizontalTextPosition(semesterCancel.CENTER);
			semesterCancel.setVerticalTextPosition(semesterCancel.CENTER);
			semesterCancel.setBorder(null);
			this.semesterCancel.setBounds(391, sideYBtnPosition, (int)semesterCancel.getPreferredSize().getWidth(), (int)semesterCancel.getPreferredSize().getHeight());
			courseBackground.add(semesterCancel);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);
			secondWindow.repaint();
		}
	}
	
	private void secondarySemesterMenu(){
		if(secondarySemesterBoolean){
			int sideYBtnPosition = 88 + (int)(buttonIcon.getIconHeight() - 1);
			viewCourseBtn.setIcon(miniButton);
			viewCourseBtn.setPressedIcon(miniButtonClicked);
			viewCourseBtn.setHorizontalTextPosition(viewCourseBtn.CENTER);
			viewCourseBtn.setVerticalTextPosition(viewCourseBtn.CENTER);
			viewCourseBtn.setBorder(null);
			this.viewCourseBtn.setBounds(391, sideYBtnPosition, (int)viewCourseBtn.getPreferredSize().getWidth(), (int)viewCourseBtn.getPreferredSize().getHeight());
			courseBackground.add(viewCourseBtn);
			sideYBtnPosition += (int)(buttonIcon.getIconHeight() + 4);
			
			removeSemesterBtn.setIcon(miniButton);
			removeSemesterBtn.setPressedIcon(miniRedButtonClicked);
			removeSemesterBtn.setHorizontalTextPosition(removeSemesterBtn.CENTER);
			removeSemesterBtn.setVerticalTextPosition(removeSemesterBtn.CENTER);
			removeSemesterBtn.setBorder(null);
			removeSemesterBtn.setBounds(391, sideYBtnPosition, (int)removeSemesterBtn.getPreferredSize().getWidth(), (int)removeSemesterBtn.getPreferredSize().getHeight());
			courseBackground.add(removeSemesterBtn);
			secondWindow.repaint();
		}
	}
	
	/*
	 * Add Course
	 */
	@SuppressWarnings("static-access")
	private void addCourse(int semesterNum, int credits, String courseName){
		semesters.get(semesterNum).addCourse(credits, courseName);
		JButton[] temp = courses;
		courses = new JButton[courses.length + 1];

		for(int i = 0; i < temp.length; i ++){
			courses[i] = temp[i];
		}

		courseYBtnPosition -= (int)(buttonIcon.getIconHeight() - 1);

		courses[courses.length -1] = new JButton(courseName);
		courses[courses.length -1].addActionListener(this);
		courses[courses.length -1].setIcon(buttonIcon);
		courses[courses.length -1].setPressedIcon(buttonClickedIcon);
		courses[courses.length -1].setHorizontalTextPosition(courses[courses.length -1].CENTER);
		courses[courses.length -1].setVerticalTextPosition(courses[courses.length -1].CENTER);
		courses[courses.length -1].setForeground(new Color(51, 204, 255));
		courses[courses.length -1].setBorder(null);
		courses[courses.length -1].setBounds(12, courseYBtnPosition, (int)courses[courses.length -1].getPreferredSize().getWidth(), (int)courses[courses.length -1].getPreferredSize().getHeight());
		courseBackground.add(courses[courses.length -1]);
		courseYBtnPosition += (int)(buttonIcon.getIconHeight() - 1);
		addCourseBtn.setBounds(12, courseYBtnPosition, (int)addCourseBtn.getPreferredSize().getWidth(), (int)addCourseBtn.getPreferredSize().getHeight());

		courseYBtnPosition += (int)(buttonIcon.getIconHeight() - 1);
		courseBackBtn.setBounds(12, courseYBtnPosition, (int)courseBackBtn.getPreferredSize().getWidth(), (int)courseBackBtn.getPreferredSize().getHeight());
		secondWindow.repaint();
		writeSemesters();
	}

	/*
	 * Add Assignment
	 */
	@SuppressWarnings("static-access")
	public void addAssignment(String name, double possibleGrade){
		semesters.get(currentSemester).getCourse(currentCourse).addAssignment(name, currentCategory, possibleGrade);
		JButton[] temp = assignments;
		assignments = new JButton[assignments.length + 1];

		for(int i = 0; i < temp.length; i ++){
			assignments[i] = temp[i];
		}

		assignmentYBtnPosition -= (int)(buttonIcon.getIconHeight() - 1);

		assignments[assignments.length -1] = new JButton(name);
		assignments[assignments.length -1].addActionListener(this);
		assignments[assignments.length -1].setIcon(buttonIcon);
		assignments[assignments.length -1].setPressedIcon(buttonClickedIcon);
		assignments[assignments.length -1].setHorizontalTextPosition(assignments[assignments.length -1].CENTER);
		assignments[assignments.length -1].setVerticalTextPosition(assignments[assignments.length -1].CENTER);
		assignments[assignments.length -1].setForeground(new Color(51, 204, 255));
		assignments[assignments.length -1].setFont(new Font("Serif", Font.PLAIN, 14));
		assignments[assignments.length -1].setBorder(null);
		assignments[assignments.length -1].setBounds(12, assignmentYBtnPosition, (int)assignments[assignments.length -1].getPreferredSize().getWidth(), (int)assignments[assignments.length -1].getPreferredSize().getHeight());
		courseBackground.add(assignments[assignments.length -1]);
		assignmentYBtnPosition += (int)(buttonIcon.getIconHeight() - 1);
		addAssignmentBtn.setBounds(12, assignmentYBtnPosition, (int)addAssignmentBtn.getPreferredSize().getWidth(), (int)addAssignmentBtn.getPreferredSize().getHeight());

		assignmentYBtnPosition += (int)(buttonIcon.getIconHeight() - 1);
		assignmentBackBtn.setBounds(12, assignmentYBtnPosition, (int)assignmentBackBtn.getPreferredSize().getWidth(), (int)assignmentBackBtn.getPreferredSize().getHeight());

		semesters.get(currentSemester).getCourse(currentCourse).setRequiredGrade();
		String[] names = semesters.get(currentSemester).getCourse(currentCourse).getCategory(currentCategory).getAssignmentNames();

		for(int i = 0; i < assignments.length; i ++){
			assignments[i].setText(names[i]);
			assignments[i].setBorder(null);
		}
		writeSemesters();
		secondWindow.repaint();
	}

	
	/*
	 * Create Category
	 */
	@SuppressWarnings("static-access")
	private void createCategory(String name, int weight){
		assignmentCategoriesBox.addItem(name);
		semesters.get(currentSemester).getCourse(currentCourse).addAssignmentCategory(name, weight);

		JButton[] temp = categoryButtons;
		categoryButtons = new JButton[categoryButtons.length + 1];

		for(int i = 0; i < temp.length; i ++){
			categoryButtons[i] = temp[i];
		}

		categoryYBtnPosition -= (int)(buttonIcon.getIconHeight() - 1);
		categoryButtons[categoryButtons.length -1] = new JButton(name + " - " + semesters.get(currentSemester).getCourse(currentCourse).getCategory(categoryButtons.length -1).getWeight());
		categoryButtons[categoryButtons.length -1].addActionListener(this);
		categoryButtons[categoryButtons.length -1].setIcon(buttonIcon);
		categoryButtons[categoryButtons.length -1].setPressedIcon(buttonClickedIcon);
		categoryButtons[categoryButtons.length -1].setHorizontalTextPosition(categoryButtons[categoryButtons.length -1].CENTER);
		categoryButtons[categoryButtons.length -1].setVerticalTextPosition(categoryButtons[categoryButtons.length -1].CENTER);
		categoryButtons[categoryButtons.length -1].setForeground(new Color(51, 204, 255));
		categoryButtons[categoryButtons.length -1].setBorder(null);
		categoryButtons[categoryButtons.length -1].setBounds(12, categoryYBtnPosition, (int)categoryButtons[categoryButtons.length -1].getPreferredSize().getWidth(), (int)categoryButtons[categoryButtons.length -1].getPreferredSize().getHeight());

		categoryYBtnPosition += (int)(buttonIcon.getIconHeight() - 1);
		addCategory.setBounds(12, categoryYBtnPosition, (int)addCategory.getPreferredSize().getWidth(), (int)addCategory.getPreferredSize().getHeight());

		categoryYBtnPosition += (int)(buttonIcon.getIconHeight() - 1);
		categoryBackBtn.setBounds(12, categoryYBtnPosition, (int)categoryBackBtn.getPreferredSize().getWidth(), (int)categoryBackBtn.getPreferredSize().getHeight());

		courseBackground.add(categoryButtons[categoryButtons.length -1]);
		secondWindow.repaint();
		writeSemesters();
	}

	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		//Login
		if(e.getSource() == loginBtn){
			login(username.getText(), password.getText());
		}

		//New Account
		if(e.getSource() == newAccountBtn){
			accountWindow();
		}

		if(e.getSource() == createAccountBtn){
			name.setText(""); 
			createAccount(newUsername.getText(), newPassword.getText(), confirmPassword.getText());
		}
		
		if(e.getSource() == exitBtn){
			start();
		}

		//Semester Buttons
		try{//The try catch is needed due to the fact that semester buttons aren't initialed in the constructor
			for(int i = 0; i < semesterButtons.length; i ++){
				if(e.getSource() == semesterButtons[i]){
					currentSemester = i;
					secondarySemesterMenu();
				}
			}
		}
		catch(NullPointerException exception){
			//do nothing
		}

		//Course Buttons
		try{
			for(int i = 0; i < courses.length; i ++){
				if(e.getSource() == courses[i]){
					if(!courseBoolean){
						courseBackground.remove(nameLbl);
						courseBackground.remove(name);
						courseBackground.remove(creditsLbl);
						courseBackground.remove(credits);
						courseBackground.remove(createCourse);
						courseBackground.remove(courseCancel);
					}

					currentCourse = i;
					secondaryCoursesMenu();
					secondaryCourseBoolean = false;
					courseBoolean = true;
				}
			}
		}
		catch(NullPointerException exception){
		}

		//Category Buttons
		try{
			for(int i = 0; i < categoryButtons.length; i ++){
				if(e.getSource() == categoryButtons[i]){
					currentCategory = i;
					secondaryCategoryMenu();
				}
			}
		}catch(NullPointerException exception){
		}

		//Assignment Buttons
		try{
			for(int i = 0; i < assignments.length; i ++){
				if(e.getSource() == assignments[i]){
					currentAssignment = i;
					assignmentSideMenu(currentAssignment);
					setGradeBoolean = false;
					assignmentBoolean = true;
				}
			}
		}catch(NullPointerException exception){
		}
		
		//Shows the category window
		if(e.getSource() == viewCategories){
			semesters.get(currentSemester).getCourse(currentCourse).setRequiredGrade();
			categoryWindow(currentSemester, currentCourse);
			secondaryCourseBoolean = true;
		}

		//Shows the Assignments window
		if(e.getSource() == viewAssignments){
			assignmentWindow(currentSemester, currentCourse, currentCategory);
		}
		
		if(e.getSource() == viewCourseBtn){
			courseWindow(currentSemester);
			semesterBoolean = true;
		}
		
		//Set Desired Grade
		if(e.getSource() == setDesiredGrade){
			double newDesiredGrade = 0;
			try{//attempts to fix issue with JOptionPane showing up twice
				newDesiredGrade = Double.parseDouble(JOptionPane.showInputDialog("Enter the grade you want to get"));
			}catch(Exception exception){
				//Do nothing
			}
			if(newDesiredGrade > 0 && newDesiredGrade <= 100){
				semesters.get(currentSemester).getCourse(currentCourse).setDesiredGrade(newDesiredGrade);
			}
			else{
				JOptionPane.showMessageDialog(secondWindow, "Invalid Desired Grade", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}

		//Add semester
		if(e.getSource() == addSemesterBtn){
			addSemesterMenu();
			semesterBoolean = false;
		}

		if(e.getSource() == createSemesterBtn){
			String semesterName = name.getText().trim();

			if(!semesterName.equals("")){
				createSemester(semesterName);
				courseBackground.remove(nameLbl);
				courseBackground.remove(name);
				courseBackground.remove(createSemesterBtn);
				courseBackground.remove(semesterCancel);
				name.setText("");
			}
			semesterBoolean = true;
		}

		//Add course
		if(e.getSource() == addCourseBtn){
			if(!secondaryCourseBoolean){//Checks to make sure the second menu isn't loaded
				courseBackground.remove(viewAssignments);
				courseBackground.remove(setDesiredGrade);
				courseBackground.revalidate();
			}
			addCourseMenu();
			courseBoolean = false;
			secondaryCourseBoolean = true;
		}

		if(e.getSource() == createCourse){
			String courseName = name.getText().trim();
			int numOfCredits = 0;
			try{
				numOfCredits = Integer.parseInt(credits.getText().trim());
			}
			catch(NumberFormatException exception){
				//do nothing
			}

			if(numOfCredits > 0 && (numOfCredits == Math.round(numOfCredits))){
				addCourse(currentSemester, numOfCredits, courseName);
				name.setText("");
				credits.setText("");
				courseBackground.remove(nameLbl);
				courseBackground.remove(name);
				courseBackground.remove(creditsLbl);
				courseBackground.remove(credits);
				createCourse.setBounds(600, 500,100, 100);
				courseBackground.remove(createCourse);
				courseBackground.remove(courseCancel);
				courseBackground.repaint();
				courseBoolean = true;
			}
			else{
				JOptionPane.showMessageDialog(secondWindow,"Invalid Number of Credits", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}

		//Add Category
		if(e.getSource() == addCategory){
			addCategoryMenu();
			categoryBoolean = false;
		}

		if(e.getSource() == createCategory){
			String category = name.getText().trim();
			int weight = 0;
			try{
				weight = Integer.parseInt(weightTxt.getText().trim());
			}
			catch(NumberFormatException exception){
				//Do nothing
			}

			if(weight > 0 && (weight == Math.round(weight))){
				if((semesters.get(currentSemester).getCourse(currentCourse).getTotalWeight() + weight) <= 200){//Makes sure that the total weight is less than 100
					createCategory(category, weight);
					courseBackground.remove(nameLbl);
					courseBackground.remove(name);
					courseBackground.remove(weightLbl);
					courseBackground.remove(weightTxt);
					courseBackground.remove(createCategory);
					courseBackground.remove(categoryCancel);
					name.setText("");
					weightTxt.setText("");
					categoryBoolean = true;
				}
				else{
					JOptionPane.showMessageDialog(secondWindow,"Total credits is greater than 100", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(secondWindow,"Invalid Weight", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}

		//Add Assignment
		if(e.getSource() == addAssignmentBtn){
			courseBackground.remove(grade);
			courseBackground.remove(setGradeBtn);
			courseBackground.remove(removeAssignmentBtn);
			courseBackground.remove(gradeLbl);
			courseBackground.revalidate();
			addAssignmentMenu();
			assignmentBoolean = false;
			setGradeBoolean = true;
		}

		if(e.getSource() == createAssignment){
			String assignmentName = name.getText().trim();
			double Grade = 0.0;
			try{
				Grade = Double.parseDouble(possibleGrade.getText().trim());
			}catch(NumberFormatException exception){
				//Do nothing
			}

			if(Grade > 0){
				addAssignment(assignmentName, Grade);
				courseBackground.remove(nameLbl);
				courseBackground.remove(name);
				courseBackground.remove(possibleGradeLbl);
				courseBackground.remove(possibleGrade);
				courseBackground.remove(assignmentCategoriesBox);
				courseBackground.remove(createAssignment);
				courseBackground.remove(assignmentCancel);
				possibleGrade.setText("");
				name.setText("");
				assignmentBoolean = true;
			}
			else{
				JOptionPane.showMessageDialog(secondWindow,"Invalid Possible Grade", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}

		//Set Grade
		if(e.getSource() == setGradeBtn){
			double acquiredGrade = 0;
			double possibleGrade = 0;
			try{
				acquiredGrade = Double.parseDouble(this.grade.getText());
				possibleGrade = Double.parseDouble(this.possibleGrade.getText());
			}catch(NumberFormatException exception){
			}

			if(acquiredGrade >= 0 && possibleGrade >= 0){
				semesters.get(currentSemester).getCourse(currentCourse).getCategory(currentCategory).getAssignment(currentAssignment).setAcquiredGrade(acquiredGrade);
				semesters.get(currentSemester).getCourse(currentCourse).getCategory(currentCategory).getAssignment(currentAssignment).setPossibleGrade(possibleGrade);
				writeSemesters();
				semesters.get(currentSemester).getCourse(currentCourse).setRequiredGrade();
				String[] names = semesters.get(currentSemester).getCourse(currentCourse).getCategory(currentCategory).getAssignmentNames();

				for(int i = 0; i < assignments.length && i < names.length; i ++){
					assignments[i].setText(names[i]);
					assignments[i].setBorder(null);
				}
				this.grade.setText("");
			}
			else{
				JOptionPane.showMessageDialog(secondWindow,"Invalid Grade", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}

		//Back Buttons
		if(e.getSource() == courseBackBtn){
			courseBackground.removeAll();
			courseBackground.revalidate();
			semesterWindow();
			courseBoolean = true;
			secondaryCourseBoolean = true;
		}

		if(e.getSource() == categoryBackBtn){
			courseBackground.removeAll();
			courseBackground.revalidate();
			courseWindow(currentSemester);
			categoryBoolean = true;
		}

		if(e.getSource() == assignmentBackBtn){
			categoryWindow(currentSemester, currentCourse);
			courseBackground.remove(nameLbl);
			courseBackground.remove(name);
			courseBackground.remove(possibleGradeLbl);
			courseBackground.remove(possibleGrade);
			courseBackground.remove(createAssignment);
			courseBackground.remove(assignmentCancel);
			assignmentBoolean = true;
			setGradeBoolean = true;
		}

		if(e.getSource() == this.assignmentCancel){
			courseBackground.remove(grade);
			courseBackground.remove(possibleGrade);
			courseBackground.remove(setGradeBtn);
			courseBackground.remove(removeAssignmentBtn);
			courseBackground.remove(nameLbl);
			courseBackground.remove(name);
			courseBackground.remove(possibleGradeLbl);
			courseBackground.remove(possibleGrade);
			courseBackground.remove(assignmentCategoriesBox);
			courseBackground.remove(createAssignment);
			courseBackground.remove(assignmentCancel);
			courseBackground.remove(assignmentCategoriesLbl);
			courseBackground.remove(gradeLbl);
			secondWindow.repaint();
		}
		
		//Remove Buttons
		if(e.getSource() == removeCourseBtn){
			semesters.get(currentSemester).removeCourse(currentCourse);
			courseBackground.removeAll();
			courseWindow(currentSemester);
			writeSemesters();
			courseBoolean = true;
			secondaryCourseBoolean = true;
		}
		
		if(e.getSource() == removeSemesterBtn){
			semesters.remove(currentSemester);
			courseBackground.removeAll();
			semesterWindow();
			semesterBoolean = true;
			secondarySemesterBoolean = true;
		}
		
		if(e.getSource() == removeCategoryBtn){
			semesters.get(currentSemester).getCourse(currentCourse).removeCategory(currentCategory);
			categoryWindow(currentSemester, currentCourse);
		}
		
		if(e.getSource() == removeAssignmentBtn){
			semesters.get(currentSemester).getCourse(currentCourse).getCategory(currentCategory).removeAssignment(currentAssignment);
			assignmentWindow(currentSemester, currentCourse, currentCategory);
		}
	}
}
