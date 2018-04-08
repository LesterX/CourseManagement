package operations;


import authenticatedUsers.LoggedInAuthenticatedUser;
import customDatatypes.Marks;
import offerings.CourseOffering;
import systemUsers.StudentModel;
import java.io.IOException;

public class AddMarks {

    public AddMarks(){}

    public static void execute(LoggedInAuthenticatedUser user, Marks mark, CourseOffering course, String studentID) throws RuntimeException{

        if (user == null || !user.getAuthenticationToken().getUserType().equals("Instructor"))
        {
            throw new RuntimeException("Only instructors can modify marks");
        }
        StudentModel target = null;
        for(StudentModel studentModel : course.getStudentsEnrolled())
            if (studentModel.getID().equals(studentID))
                target = studentModel;

        target.getPerCourseMarks().get(course).addToEvalStrategy(mark.getCurrentKey(),mark.getCurrentValue());

    }
}
