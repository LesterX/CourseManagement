package operations.instructorOperations;

import customDatatypes.Marks;
import customDatatypes.Weights;
import offerings.CourseOffering;
import offerings.ICourseOffering;
import registrar.Register;
import system.systemStatus;
import systemUsers.InstructorModel;
import systemUsers.StudentModel;
import systemUsers.SystemUserModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalculateGrades {
    public CalculateGrades(){}

    public static void execute(SystemUserModel user) throws IOException
    {
    	//If the system is closed or the user is not Instructor type, return
    	if (!systemStatus.instance().status())
		{
			System.out.println("System is closed");
			return;
		}
        if (user == null || !user.get_type().equals("Instructor"))
        {
            System.out.println(("Only instructors can calculate final grades"));
            return;
        }

        InstructorModel tutor = (InstructorModel) Register.getInstance().getRegisteredUser(user.getID());        
        
        //Read course id from user input
        System.out.println("Enter the course ID:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String course_id = br.readLine();

        CourseOffering course = Register.getInstance().getRegisteredCourse(course_id);
        if (course == null)
        {
        	System.out.println("Course not found");
        	return;
        }
        
        //User needs to be the tutor of this course
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
        StudentModel target = null;
        Double finalGrade;
        //The student has to be enrolled in this course
        for(StudentModel studentModel : course.getStudentsEnrolled())
            if (studentModel.getID().equals(student_id))
                target = studentModel;
        finalGrade = 0D;
        Weights weights = course.getEvaluationStrategies().get(target.getEvaluationEntities().get(course));
        Marks marks  = target.getPerCourseMarks().get(course);
        weights.initializeIterator();
        System.out.println("Course: " + course.getCourseID() + "\nStudent: " + target.getID() + "    " + target.getName() + "  " + target.getSurname());
        while(weights.hasNext()){
            weights.next();
            //Initialize the mark to 0 if it is not created yet
            if (marks.getValueWithKey(weights.getCurrentKey()) == null)
            	marks.addToEvalStrategy(weights.getCurrentKey(), 0.0);
            
            //Calculations
            System.out.println(weights.getCurrentKey() + "      " + marks.getValueWithKey(weights.getCurrentKey()) + " * " + weights.getCurrentValue() / 100);
            finalGrade += weights.getCurrentValue() / 100 * marks.getValueWithKey(weights.getCurrentKey());
        }
        
        System.out.println("Final Grade: " + finalGrade);
    }
    
    public static void execute(SystemUserModel user, CourseOffering course, StudentModel target)
    {
    	if (!systemStatus.instance().status())
		{
			System.out.println("System is closed");
			return;
		}
        if (user == null || !user.get_type().equals("Instructor"))
        {
            System.out.println(("Only instructors can calculate final grades"));
            return;
        }
        
    	double finalGrade = 0D;
    	
    	InstructorModel tutor = (InstructorModel) Register.getInstance().getRegisteredUser(user.getID());
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
        
        found = false;
        for(StudentModel studentModel : course.getStudentsEnrolled())
            if (studentModel.equals(target))
                 found = true;
        if (!found)
        {
        	System.out.println("Student not found");
        	return;
        }
        
        Weights weights = course.getEvaluationStrategies().get(target.getEvaluationEntities().get(course));
        Marks marks  = target.getPerCourseMarks().get(course);
        weights.initializeIterator();
        System.out.println("Course: " + course.getCourseID() + "\nStudent: " + target.getID() + "    " + target.getName() + "  " + target.getSurname());
        while(weights.hasNext()){
            weights.next();
            if (marks.getValueWithKey(weights.getCurrentKey()) == null)
            	marks.addToEvalStrategy(weights.getCurrentKey(), 0.0);
            
            System.out.println(weights.getCurrentKey() + "      " + marks.getValueWithKey(weights.getCurrentKey()) + " * " + weights.getCurrentValue() / 100);
            finalGrade += weights.getCurrentValue() / 100 * marks.getValueWithKey(weights.getCurrentKey());
        }
        
        System.out.println("Final Grade: " + finalGrade);
    }
    
    public Double calculateFinalGrade(SystemUserModel user, CourseOffering course, String ID)
    {

        if (user == null || !user.get_type().equals("Instructor"))
        {
            System.out.println(("Only instructors can calculate final grades"));
            return null;
        }

        StudentModel target = null;
        Double finalGrade;
        for(StudentModel studentModel : course.getStudentsEnrolled())
            if (studentModel.getID().equals(ID))
                target = studentModel;
        finalGrade = 0D;
        Weights weights = course.getEvaluationStrategies().get(target.getEvaluationEntities().get(course));
        Marks marks  = target.getPerCourseMarks().get(course);
        weights.initializeIterator();
        while(weights.hasNext()){
            weights.next();
            finalGrade += weights.getCurrentValue() * marks.getValueWithKey(weights.getCurrentKey());
        }
        return finalGrade;
    }
}
