package operations.instructorOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import authenticatedUsers.LoggedInAuthenticatedUser;
import customDatatypes.Marks;
import offerings.CourseOffering;
import offerings.ICourseOffering;
import registrar.ModelRegister;
import system.systemStatus;
import systemUsers.InstructorModel;
import systemUsers.StudentModel;

public class PrintMarks {

    public PrintMarks(){}

    public static void execute(LoggedInAuthenticatedUser user) throws IOException
    {
    	//If the system is closed or the user is not Instructor type, return
    	if (!systemStatus.instance().status())
		{
			System.out.println("System is closed");
			return;
		}
        if (user == null || !user.getAuthenticationToken().getUserType().equals("Instructor"))
        {
            System.out.println(("Only instructors can print marks"));
            return;
        }
        InstructorModel tutor = (InstructorModel) ModelRegister.getInstance().getRegisteredUser(user.getID());
       
        //Read course id
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter course ID: ");
        String course_id = br.readLine();
        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(course_id);
        if (course == null)
        {
        	System.out.println("Course not found");
        	return;
        }
        
        //Needs to be tutor
        boolean found = false;
        for (ICourseOffering i_course : tutor.getIsTutorOf())
        {
        	if (i_course.getCourseID().equals(course.getCourseID()))
        		found = true;
        }
        if (!found)
        {
        	System.out.println("You are not the tutor of this course");
        	return;
        }
        
        //User has two choices
        System.out.println("1. Print marks for all students\n2. Print marks for one student");
        String in = br.readLine();
        
        if (in.equals("1"))
        {
        	//Print marks for all students
        	for (StudentModel student : course.getStudentsEnrolled())
        		CalculateGrades.execute(user, course, student);
        }else if (in.equals("2"))
        {
        	//Print marks for one student, same as CalculateGrades
            System.out.println("Enter the student ID");
            String student_id = br.readLine();
            StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(student_id);
            CalculateGrades.execute(user, course, student);
        }
    }
    
    public static void execute(LoggedInAuthenticatedUser user, CourseOffering course) throws RuntimeException
    {
        if (user == null || !user.getAuthenticationToken().getUserType().equals("Instructor"))
        {
            throw new RuntimeException("Only instructors can modify marks");
        }

        StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(user.getID());

        if (course == null)
        {
            throw new RuntimeException("course has not been found");
        }

        Marks mark = student.getPerCourseMarks().get(course);
        mark.initializeIterator();

        System.out.println(course.getCourseName());

        while (mark.hasNext())
        {
            System.out.println(mark.getCurrentKey() + "		" + mark.getCurrentValue());
            mark.next();
        }
    }

    public void execute(LoggedInAuthenticatedUser user, String course_id)
    {
        if (!user.getAuthenticationToken().getUserType().equals("Student"))
        {
            throw new RuntimeException("course has not been found");

        }

        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(course_id);
        StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(user.getID());

        if (course == null)
        {
            throw new RuntimeException("course has not been found");

        }

        Marks mark = student.getPerCourseMarks().get(course);
        mark.initializeIterator();

        System.out.println(course.getCourseName());

        while (mark.hasNext())
        {
            System.out.println(mark.getCurrentKey() + "		" + mark.getCurrentValue());
            mark.next();
        }
    }
}
