package operations;


import authenticatedUsers.LoggedInAuthenticatedUser;
import customDatatypes.Marks;
import offerings.CourseOffering;
import systemUsers.StudentModel;

public class ModifyMarks {
    public ModifyMarks(){}

    public void execute(LoggedInAuthenticatedUser user, Marks mark, CourseOffering course, String studentID) throws RuntimeException{

        if (user == null || !user.getAuthenticationToken().getUserType().equals("Instructor"))
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
