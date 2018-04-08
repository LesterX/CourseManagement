package operations;

import authenticatedUsers.LoggedInAuthenticatedUser;
import customDatatypes.Marks;
import customDatatypes.Weights;
import offerings.CourseOffering;
import systemUsers.StudentModel;
import java.io.IOException;

public class CalculateGrades {
    public CalculateGrades(){}

    public Double calculateFinalGrade(LoggedInAuthenticatedUser user, CourseOffering course, String ID) throws RuntimeException{

        if (user == null || !user.getAuthenticationToken().getUserType().equals("Instructor"))
        {
            throw new RuntimeException("Only instructors can modify marks");
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
