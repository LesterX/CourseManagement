package operations.studentOperations;

import registrar.ModelRegister;
import system.systemStatus;
import systemUsers.StudentModel;
import authenticatedUsers.LoggedInAuthenticatedUser;
import customDatatypes.Marks;
import customDatatypes.Weights;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import offerings.CourseOffering;

public class PrintRecord 
{
	public PrintRecord(){}
	
	public static void execute(LoggedInAuthenticatedUser user) throws IOException
	{
		//If the system is closed or the user is not Student type, return
		if (!systemStatus.instance().status())
		{
			System.out.println("System is closed");
			return;
		}
		if (user == null || !user.getAuthenticationToken().getUserType().equals("Student"))
		{
			System.out.println("Only student can print record");
			return;
		}
		
		//Read course id from user input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the course ID: ");
		String course_id = br.readLine();
		
		CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(course_id);
		StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(user.getID());
		
		if (course == null)
		{
			System.out.println("No course has been found");
			return;
		}

		//List and calculate grades, the same as what is done in instructor operations
		double finalGrade = 0.0;
		System.out.println("Course ID : " + course.getCourseID() + "\nCourse name : " + course.getCourseName());
		System.out.println("\nStudent ID : " + student.getID() + "\nStudent Name" + student.getName() + " " + student.getSurname());
		Weights weights = course.getEvaluationStrategies().get(student.getEvaluationEntities().get(course));
        Marks marks  = student.getPerCourseMarks().get(course);
        weights.initializeIterator();
        while(weights.hasNext()){
            weights.next();
            if (marks.getValueWithKey(weights.getCurrentKey()) == null)
            	marks.addToEvalStrategy(weights.getCurrentKey(), 0.0);
            
            System.out.println(weights.getCurrentKey() + "      " + marks.getValueWithKey(weights.getCurrentKey()) + " * " + weights.getCurrentValue() / 100);
            finalGrade += weights.getCurrentValue() / 100 * marks.getValueWithKey(weights.getCurrentKey());
        }
        System.out.println("Final Grade up to now: " + finalGrade);
	}
	
	public static void execute(LoggedInAuthenticatedUser user, CourseOffering course)
	{
		if (user == null || !user.getAuthenticationToken().getUserType().equals("Instructor"))
		{
			throw new RuntimeException("Only instructors can print record");
		}

		if (course == null)
		{
			throw new RuntimeException("No course has been found");
		}

		System.out.println("ID : " + course.getCourseID() + "\nCourse name : " + course.getCourseName() + "\nSemester : " +
				course.getSemester());
		System.out.println("Students allowed to enroll\n");
		for(StudentModel student_temp : course.getStudentsEnrolled()){
			System.out.println("Student name : " + student_temp.getName() + "\nStudent surname : " + student_temp.getSurname() +
					"\nStudent ID : " + student_temp.getID() + "\nStudent EvaluationType : " +
					student_temp.getEvaluationEntities().get(course) + "\n\n");
		}

	}
	
	public void execute(LoggedInAuthenticatedUser user, String course_id)
	{
		if (user == null || !user.getAuthenticationToken().getUserType().equals("Instructor"))
		{
			throw new RuntimeException("Only instructors can print record");
		}

		CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(course_id);

		if (course == null)
		{
			throw new RuntimeException("No course has been found");
		}

		System.out.println("ID : " + course.getCourseID() + "\nCourse name : " + course.getCourseName() + "\nSemester : " +
				course.getSemester());
		System.out.println("Students allowed to enroll\n");
		for(StudentModel student_temp : course.getStudentsEnrolled()){
			System.out.println("Student name : " + student_temp.getName() + "\nStudent surname : " + student_temp.getSurname() +
					"\nStudent ID : " + student_temp.getID() + "\nStudent EvaluationType : " +
					student_temp.getEvaluationEntities().get(course) + "\n\n");
		}

	}
}
