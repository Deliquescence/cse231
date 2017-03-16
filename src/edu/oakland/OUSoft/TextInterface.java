package edu.oakland.OUSoft;

import edu.oakland.OUSoft.database.OUSoft;
import edu.oakland.OUSoft.items.Course;
import edu.oakland.OUSoft.items.Instructor;
import edu.oakland.OUSoft.items.Person;
import edu.oakland.OUSoft.items.Student;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class to contain everything relating to command line interface
 */
public class TextInterface {
	
	private final OUSoft db;
	
	private final Scanner scan;
	private boolean running;
	
	/**
	 * Create a new Textual Interface that will interact with the user and the given database
	 *
	 * @param db The OUSoft database to work with
	 */
	public TextInterface(OUSoft db) {
		this.db = db;
		this.scan = new Scanner(System.in);
	}
	
	/**
	 * Print header and accept commands from the user in an infinite loop
	 */
	public void startup() {
		this.running = true;
		System.out.print("Welcome to the OUSoft textual user interface.\nType 'help' for available commands\n");
		while (this.running) {
			this.doCommand(this.getInput("OUSoft: "));
		}
	}
	
	/**
	 * Attempt to run the given command at the root prompt level.
	 *
	 * @param input The command (and arguments) to attempt
	 * @return Success
	 */
	public boolean doCommand(String input) {
		
		String[] tokens = input.split(" ");
		switch (tokens[0].toLowerCase()) {
			case "list":
			case "ls":
				if (tokens.length > 1) { //Type is given
					if (tokens[1].startsWith("i")) { //Instructor
						this.db.printAllInstructors(true);
						return true;
					} else if (tokens[1].startsWith("s")) { //Student
						this.db.printAllStudents(true);
						return true;
					} else if (tokens[1].startsWith("c")) { //Course
						System.out.println("Courses in the database: " + db.getCourses().size());
						this.db.printAllCourses();
						return true;
					}
				}
				this.db.printAllPeople(true);
				return true;
			
			case "add":
			case "new":
				String itemType;
				if (tokens.length > 1) { //Type is given
					itemType = tokens[1].toLowerCase();
				} else {
					itemType = this.getInput("Student, Instructor, or Course?\nadd: ").toLowerCase();
				}
				System.out.println("Enter the following information:");
				String ID = this.getInput("ID: ");
				
				if (itemType.startsWith("s")) { //Student
					String firstName = this.getInput("First Name: ");
					String lastName = this.getInput("Last Name: ");
					Student student = new Student(ID, firstName, lastName);
					student.setMajor(this.getInput("Major: "));
					student.setNumYearsAttended(this.getIntegerInput("Years Attended: "));
					
					//Handle GPA validation
					//NB student.setGPA() has validation checking
					boolean validGPA = false;
					while (!validGPA) {
						String sGPA = this.getInput("GPA: ");
						try {
							double dGPA = Double.parseDouble(sGPA);
							student.setGPA(dGPA);
							validGPA = true;
						} catch (IllegalArgumentException ex) {
							System.out.printf("Error parsing '%s' into GPA, try again\n", sGPA);
						}
					}
					//Confirmation
					System.out.println("\nAdd this student?\n" + student.toString());
					if (this.getBooleanInput()) {
						this.db.addPerson(student);
						System.out.println("Added!");
						return true;
					}
					return false;
					
				} else if (itemType.startsWith("i")) { //Instructor
					String firstName = this.getInput("First Name: ");
					String lastName = this.getInput("Last Name: ");
					Instructor instructor = new Instructor(ID, firstName, lastName);
					instructor.setOfficeBuilding(this.getInput("Office Building: "));
					instructor.setOfficeNumber(this.getInput("Office Number: "));
					instructor.setTenured(this.getBooleanInput("Has tenure (y/n): "));
					
					//Confirmation
					System.out.println("\nAdd this instructor?\n" + instructor.toString());
					if (this.getBooleanInput()) {
						this.db.addPerson(instructor);
						System.out.println("Added!");
						return true;
					}
					return false;
					
				} else if (itemType.startsWith("c")) { //Course
					String name = this.getInput("Course Name: ");
					Course course = new Course(ID, name);
					
					Instructor instructor = getInstructorFromID("The ID of the instructor of this course: ");
					if (instructor == null) {
						return false;
					}
					course.setInstructor(instructor);
					
					LocalTime startTime = LocalTime.of(this.getIntegerInput("Starting hour (0-23): "),
					                                   this.getIntegerInput("Starting minute (0-59): "));
					
					LocalTime endTime = LocalTime.of(this.getIntegerInput("Ending hour (0-23): "),
					                                 this.getIntegerInput("Ending minute (0-59): "));
					
					course.setTimeStart(startTime);
					course.setTimeEnd(endTime);
					
					//Confirmation
					System.out.println("\nAdd this course?\n" + course.toString());
					if (this.getBooleanInput()) {
						this.db.addCourse(course);
						System.out.println("Added!");
						return true;
					}
					return false;
					
				} else {
					System.out.println("Unexpected type.");
					return false;
				}
			
			case "withdraw":
				Student student = getStudentFromID("Enter the ID of the student: ");
				if (student == null) {
					return false;
				}
				Course course = getCourseFromID("Enter the ID of the course: ");
				if (course == null) {
					return false;
				}
				
				db.withdraw(student, course);
				System.out.println("Withdrawn!");
				return true;
			
			case "enroll":
				student = getStudentFromID("Enter the ID of the student: ");
				if (student == null) {
					return false;
				}
				course = getCourseFromID("Enter the ID of the course: ");
				if (course == null) {
					return false;
				}
				
				db.enroll(student, course);
				System.out.println("Enrolled!");
				return true;
			
			case "get":
			case "retrieve":
				if (tokens.length > 1) { //ID is given
					Person p = this.db.getPersonByID(tokens[1]);
					Course c = this.db.getCourseByID(tokens[1]);
					if (p != null) {
						System.out.println("Found someone with that ID:\n" + p.toString());
						return true;
					} else if (c != null) {
						System.out.println("Found a course with that ID:\n" + c.toString());
						return true;
					} else {
						System.out.println("Could not find anything with that ID!");
						return false;
					}
				}
				System.out.println("Usage: get <ID>");
				return false;
			
			case "remove":
			case "rm":
			case "delete":
				if (tokens.length > 1) { //ID is given
					Person p = this.db.getPersonByID(tokens[1]);
					Course c = this.db.getCourseByID(tokens[1]);
					if (p != null) {
						System.out.println("Really remove this person?\n" + p.toString());
						if (this.getBooleanInput()) {
							this.db.removePerson(p);
							System.out.println("Removed!");
							return true;
						}
						return false;
					} else if (c != null) {
						System.out.println("Really remove this course?\n" + c.toString());
						if (this.getBooleanInput()) {
							this.db.removeCourse(c);
							System.out.println("Removed!");
							return true;
						}
						return false;
					} else {
						System.out.println("Could not find anything with that ID!");
						return false;
					}
				}
				System.out.println("Usage: remove <ID>");
				return false;
			
			case "save":
				String savePath = "";
				if (tokens.length > 1) { //Path is given
					savePath = concatArray(Arrays.copyOfRange(tokens, 1, tokens.length)); //Handle spaces in path
				}
				this.db.savePeople(savePath);
				this.db.saveCourses(savePath);
				this.db.saveEnrollments(savePath);
				
				System.out.println("Saved!");
				
				return true;
			
			case "load":
				String loadPath = "";
				if (tokens.length > 1) { //Path is given
					loadPath = concatArray(Arrays.copyOfRange(tokens, 1, tokens.length)); //Handle spaces in path
				}
				this.db.loadPeople(loadPath);
				this.db.loadCourses(loadPath);
				this.db.loadEnrollments(loadPath);
				
				System.out.println("Loaded!");
				
				return true;
			
			case "quit":
			case "exit":
			case "end":
				System.out.println("Goodbye");
				this.running = false;
				return true;
			
			case "help":
			case "?":
			case "h":
			case "man":
				if (tokens.length > 1) {
					System.out.println("Detailed help to be implemented.");
					return true;
				} else {
					this.printBigHelp();
					return true;
				}
			
			default:
				if (Objects.equals(tokens[0], "")) { //No command
					return true; //Successfully did nothing
				} else {
					System.out.println(tokens[0] + ": command not found");
					return false;
				}
		}
	}
	
	/**
	 * Get the user's input
	 *
	 * @return The user's response
	 */
	public String getInput() {
		return this.scan.nextLine();
	}
	
	/**
	 * Print <code>prompt</code> to the screen and then get the user's input.
	 * No newline is added
	 *
	 * @param prompt The text to write before getting input
	 * @return The user's response
	 */
	public String getInput(String prompt) {
		System.out.print(prompt);
		return this.getInput();
	}
	
	/**
	 * Print <code>prompt</code> to the screen and then get the user's input.
	 * Again, and again, until they give a good integer.
	 *
	 * @param prompt The text to write before getting input
	 * @return The user's integer
	 */
	public int getIntegerInput(String prompt) {
		boolean good = false;
		
		String string;
		int integer = 0;
		while (!good) {
			string = this.getInput(prompt);
			try {
				integer = Integer.parseInt(string);
				good = true;
			} catch (NumberFormatException ex) {
				System.out.printf("Error parsing '%s' into an integer, try again\n", string);
			}
		}
		return integer;
	}
	
	/**
	 * Get the user's input, and make sure it's a boolean.
	 * Use a default prompt of "y/n : "
	 *
	 * @return The user's boolean
	 */
	public boolean getBooleanInput() {
		return this.getBooleanInput("y/n : ");
	}
	
	/**
	 * Print <code>prompt</code> to the screen and then get the user's boolean input
	 *
	 * @return The user's boolean
	 */
	public boolean getBooleanInput(String prompt) {
		
		String string;
		while (true) {
			string = this.getInput(prompt);
			
			switch (string.toLowerCase()) {
				case "y":
				case "yes":
				case "true":
					return true;
				
				case "n":
				case "no":
				case "false":
					return false;
			}
			
			System.out.println("Enter 'y' or 'n'");
		}
	}
	
	/**
	 * Get an instructor from the ID the user enters.
	 * If they enter a bad ID, try again.
	 * If they give up, they can enter 'abort' and this will return null.
	 *
	 * @return An Instructor, or null
	 */
	public Instructor getInstructorFromID(String prompt) {
		Instructor instructor = null;
		while (instructor == null) {
			String instructorID = this.getInput(prompt);
			if (instructorID.equalsIgnoreCase("abort")) {
				return null;
			}
			instructor = db.getInstructorByID(instructorID);
			if (instructor == null) {
				System.out.println("Could not find that instructor! (Enter 'abort' if you need)");
			}
		}
		return instructor;
	}
	
	/**
	 * Get a student from the ID the user enters.
	 * If they enter a bad ID, try again.
	 * If they give up, they can enter 'abort' and this will return null.
	 *
	 * @return A Student, or null
	 */
	public Student getStudentFromID(String prompt) {
		Student student = null;
		while (student == null) {
			String studentID = this.getInput(prompt);
			if (studentID.equalsIgnoreCase("abort")) {
				return null;
			}
			student = db.getStudentByID(studentID);
			if (student == null) {
				System.out.println("Could not find that student! (Enter 'abort' if you need)");
			}
		}
		return student;
	}
	
	/**
	 * Get a course from the ID the user enters.
	 * If they enter a bad ID, try again.
	 * If they give up, they can enter 'abort' and this will return null.
	 *
	 * @return A Course, or null
	 */
	public Course getCourseFromID(String prompt) {
		Course course = null;
		while (course == null) {
			String courseID = this.getInput(prompt);
			if (courseID.equalsIgnoreCase("abort")) {
				return null;
			}
			course = db.getCourseByID(courseID);
			if (course == null) {
				System.out.println("Could not find that course! (Enter 'abort' if you need)");
			}
		}
		return course;
	}
	
	/**
	 * Given an array of strings, concatenate them into one string
	 *
	 * @param array the array to concatenate
	 * @return the concatenated string
	 */
	public String concatArray(String[] array) {
		StringBuilder sb = new StringBuilder();
		for (String str : array) {
			sb.append(str);
		}
		return sb.toString();
	}
	
	/**
	 * Print the big list of commands
	 */
	private void printBigHelp() {
		System.out.print("Available commands:\n" +
		                 "add [type]\t\tAdd a person or course to the database\n" +
		                 "enroll\t\t\tEnroll a student in a course\n" +
		                 "get <ID>\t\tRetrieve a person from the database\n" +
		                 "help [command]\tGet help\n" +
		                 "list [type]\t\tList people or courses in the database\n" +
		                 "load [path]\t\tLoad the database from the path (default path if not given)\n" +
		                 "quit\t\t\tExit the program\n" +
		                 "remove <ID>\t\tRemove a person or course from the database\n" +
		                 "save [path]\t\tSave the database to the path (default path if not given)\n" +
		                 "withdraw\t\tWithdraw a student from a course\n");
	}
}
