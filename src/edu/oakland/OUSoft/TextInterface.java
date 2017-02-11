package edu.oakland.OUSoft;

import edu.oakland.OUSoft.database.OUSoft;
import edu.oakland.OUSoft.items.Course;
import edu.oakland.OUSoft.items.Instructor;
import edu.oakland.OUSoft.items.Person;
import edu.oakland.OUSoft.items.Student;

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
					}
				}
				this.db.printAllPeople(true);
				return true;
			
			case "add":
			case "new":
				String personType;
				if (tokens.length > 1) { //Type is given
					personType = tokens[1].toLowerCase();
				} else {
					personType = this.getInput("Student or Instructor?\nadd: ").toLowerCase();
				}
				System.out.println("Enter the following information:");
				String ID = this.getInput("ID: ");
				String firstName = this.getInput("First Name: ");
				String lastName = this.getInput("Last Name: ");
				
				if (personType.startsWith("s")) { //Student
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
						return true;
					}
					return false;
					
				} else if (personType.startsWith("i")) { //Instructor
					Instructor instructor = new Instructor(ID, firstName, lastName);
					instructor.setOfficeBuilding(this.getInput("Office Building: "));
					instructor.setOfficeNumber(this.getInput("Office Number: "));
					instructor.setTenured(this.getBooleanInput("Has tenure (y/n): "));
					
					//Confirmation
					System.out.println("\nAdd this instructor?\n" + instructor.toString());
					if (this.getBooleanInput()) {
						this.db.addPerson(instructor);
						return true;
					}
					return false;
				}
				//Other (Not student or instructor)
				Person person = new Person(ID, firstName, lastName);
				//Confirmation
				System.out.println("\nAdd this person?\n" + person.toString());
				if (this.getBooleanInput()) {
					this.db.addPerson(person);
					return true;
				}
				return false;
			
			case "get":
			case "retrieve":
				if (tokens.length > 1) { //ID is given
					Person p = this.db.getPersonByID(tokens[1]);
					if (p != null) {
						System.out.println("Found someone with this ID:\n" + p.toString());
						return true;
					} else {
						System.out.println("Could not find someone with that ID!");
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
					if (p != null) {
						System.out.println("Really remove this person?\n" + p.toString());
						if (this.getBooleanInput()) {
							this.db.removePerson(p);
							System.out.println("Removed!");
							return true;
						}
						return false;
					} else {
						System.out.println("Could not find someone with that ID!");
						return false;
					}
				}
				System.out.println("Usage: remove <ID>");
				return false;
			
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
	 * Print the big list of commands
	 */
	private void printBigHelp() {
		StringBuilder helpBuilder = new StringBuilder();
		helpBuilder.append("Available commands:\n");
		helpBuilder.append("add [type]\t\tAdd a person to the database\n");
		helpBuilder.append("get <ID>\t\tRetrieve a person from the database\n");
		helpBuilder.append("help [command]\tGet help\n");
		helpBuilder.append("list [type]\t\tList people in the database\n");
		helpBuilder.append("quit\t\t\tExit the program\n");
		helpBuilder.append("remove <ID>\t\tRemove a person from the database\n");
		
		System.out.print(helpBuilder.toString());
	}
	
}
