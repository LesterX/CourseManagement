package operations.instructorOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import customDatatypes.EvaluationTypes;
import customDatatypes.Marks;
import customDatatypes.Weights;
import offerings.CourseOffering;
import offerings.ICourseOffering;
import registrar.Register;
import system.systemStatus;
import systemUsers.InstructorModel;
import systemUsers.StudentModel;
import systemUsers.SystemUserModel;

public class ModifyMarks {
    public ModifyMarks(){}

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
            System.out.println(("Only instructors can modify marks"));
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
        
        //The user has to be the tutor of this course
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
        
        //The student has to be enrolled in this course
        for (StudentModel s : course.getStudentsEnrolled())
        {
        	if (s.getID().equals(student_id))
        		student = s;
        }
        
        if (student == null)
        {
        	System.out.println("Student not found");
        	return;
        }
        
        //Same as add marks except the mark to be modified must have been created already
        EvaluationTypes eval_type = student.getEvaluationEntities().get(course);
        Weights weight = course.getEvaluationStrategies().get(eval_type);
        weight.initializeIterator();
        
        System.out.println("Available evaluation: ");
        while (weight.hasNext())
        {
        	weight.next();
        	System.out.println(weight.getCurrentKey());
        }
        
        System.out.println("Enter the name of the evaluation you want to modify: ");
        String title = br.readLine();
        weight.initializeIterator();
        Map<ICourseOffering, Marks> marks = student.getPerCourseMarks();
        while (weight.hasNext())
        {
        	weight.next();
        	if (weight.getCurrentKey().equals(title))
        	{
        		Marks mark = marks.get(course);
        		if (mark == null || marks.get(course).getValueWithKey(title) == null)
        		{
        			System.out.println("Mark does not exists under this title, please choose add mark instead of modify mark");
        			return;
        		}
        		
        		System.out.println("Enther the grade: ");
        		double grade = Double.parseDouble(br.readLine());
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
    
    public void execute(SystemUserModel user, Marks mark, CourseOffering course, String studentID) throws RuntimeException{

        if (user == null || !user.get_type().equals("Instructor"))
        {
            throw new RuntimeException("Only instructors can modify marks");
        }
        StudentModel target = null;
        for(StudentModel studentModel : course.getStudentsEnrolled())
            if (studentModel.getID().equals(studentID))
                target = studentModel;

        target.getPerCourseMarks().get(course).changeEvalStrategy(mark.getCurrentKey(),mark.getCurrentValue());

    }
}
