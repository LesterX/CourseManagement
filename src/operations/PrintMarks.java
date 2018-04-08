package operations;

import authenticatedUsers.LoggedInAuthenticatedUser;
import customDatatypes.Marks;
import offerings.CourseOffering;
import registrar.ModelRegister;
import systemUsers.StudentModel;

public class PrintMarks {

    public PrintMarks(){}

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
