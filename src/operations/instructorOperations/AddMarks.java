package operations.instructorOperations;

import authenticatedUsers.LoggedInAuthenticatedUser;
import customDatatypes.EvaluationTypes;
import customDatatypes.Marks;
import customDatatypes.Weights;
import offerings.CourseOffering;
import offerings.ICourseOffering;
import registrar.ModelRegister;
import system.systemStatus;
import systemUsers.InstructorModel;
import systemUsers.StudentModel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.io.IOException;

public class AddMarks {

    public AddMarks(){}

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
            System.out.println(("Only instructors can add marks"));
            return;
        }
        
        InstructorModel tutor = (InstructorModel) ModelRegister.getInstance().getRegisteredUser(user.getID());        
        
        //Read course name from user input
        System.out.println("Enter the course ID:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String course_id = br.readLine();

        CourseOffering course = ModelRegister.getInstance().getRegisteredCourse(course_id);
        if (course == null)
        {
        	System.out.println("Course not found");
        	return;
        }
        
        //Determine whether the user is the tutor of this course
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
        
        //Read student id from user input
        System.out.println("Enter the student ID: ");
        String student_id = br.readLine();
        StudentModel student = null;
        
        for (StudentModel s : course.getStudentsEnrolled())
        {
        	if (s.getID().equals(student_id))
        		student = s;
        }
        
        //Student has to be enrolled in this course
        if (student == null)
        {
        	System.out.println("Student not found in enrolled list");
        	return;
        }
        
        //Read weights
        EvaluationTypes eval_type = student.getEvaluationEntities().get(course);
        Weights weight = course.getEvaluationStrategies().get(eval_type);
        weight.initializeIterator();
        
        //The title for each mark has to be consistent with what is in the weights
        System.out.println("Available evaluation: ");
        while (weight.hasNext())
        {
        	weight.next();
        	System.out.println(weight.getCurrentKey());
        }
        
        //Read title from user input
        System.out.println("Enter the name of the evaluation you want to modify: ");
        String title = br.readLine();
        weight.initializeIterator();
        Map<ICourseOffering, Marks> marks = student.getPerCourseMarks();
        while (weight.hasNext())
        {
        	weight.next();
        	//Find the title of the mark that the user wants to modify
        	if (weight.getCurrentKey().equals(title))
        	{
        		System.out.println("Enther the grade: ");
        		double grade = Double.parseDouble(br.readLine());
        		Marks mark = student.getPerCourseMarks().get(course);
        		if (mark == null)
        			mark = new Marks();
        		mark.addToEvalStrategy(title, grade);
        		marks.put(course, mark);
        		student.setPerCourseMarks(marks);
        		System.out.println("Mark added");
        		return;
        	}
        }
        
        System.out.println("Evaluation name not found in this course");
        return;
    }
    
    public static void execute(LoggedInAuthenticatedUser user, Marks mark, CourseOffering course, String studentID)
    {
    	if (!systemStatus.instance().status())
		{
			System.out.println("System is closed");
			return;
		}
        if (user == null || !user.getAuthenticationToken().getUserType().equals("Instructor"))
        {
            System.out.println(("Only instructors can modify marks"));
            return;
        }
        InstructorModel tutor = (InstructorModel) ModelRegister.getInstance().getRegisteredUser(user.getID());
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
        
        StudentModel target = null;
        for(StudentModel studentModel : course.getStudentsEnrolled())
            if (studentModel.getID().equals(studentID))
                target = studentModel;

        target.getPerCourseMarks().get(course).addToEvalStrategy(mark.getCurrentKey(),mark.getCurrentValue());

    }
}
