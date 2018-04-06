package testHarness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import offerings.CourseOffering;
import offerings.ICourseOffering;
import offerings.OfferingFactory;
import operations.Enroll;
import operations.Login;
import registrar.ModelRegister;
import systemUsers.StudentModel;
import others.GenerateUsers;
import authenticatedUsers.LoggedInAuthenticatedUser;

public class TestStudentModelFactory_1 {

//	public static void main(String[] args) throws IOException{
//		// TODO Auto-generated method stub
//		SystemUserModelFactory factory = new StudentModelFactory();
//		BufferedReader br = new BufferedReader(new FileReader(new File("note_1.txt")));
//		factory.createSystemUserModel(br, null);
//	}

	public static void main(String[] args) throws IOException{
		GenerateUsers.generate_users("users.txt");
		GenerateUsers.generate_users("note_1.txt", "users.txt");
		GenerateUsers.generate_users("note_2.txt", "users.txt");
		
//		Create an instance of an OfferingFactory
		OfferingFactory factory = new OfferingFactory();
		BufferedReader br = new BufferedReader(new FileReader(new File("note_1.txt")));
//		Use the factory to populate as many instances of courses as many files we've got.
		CourseOffering	courseOffering = factory.createCourseOffering(br);
		br.close();
//		Loading 1 file at a time
		br = new BufferedReader(new FileReader(new File("note_2.txt")));
//		here we have only two files
		courseOffering = factory.createCourseOffering(br);
		br.close();
//		code to perform sanity checking of all our code
//		by printing all of the data that we've loaded
		for(CourseOffering course : ModelRegister.getInstance().getAllCourses()){
			System.out.println("ID : " + course.getCourseID() + "\nCourse name : " + course.getCourseName() + "\nSemester : " + 
			course.getSemester());
			System.out.println("Students allowed to enroll\n");
			for(StudentModel student : course.getStudentsAllowedToEnroll()){
				System.out.println("Student name : " + student.getName() + "\nStudent surname : " + student.getSurname() + 
						"\nStudent ID : " + student.getID() + "\nStudent EvaluationType : " + 
						student.getEvaluationEntities().get(course) + "\n\n");
			}
			
			for(StudentModel student : course.getStudentsAllowedToEnroll()){
				for(ICourseOffering course2 : student.getCoursesAllowed())
				System.out.println(student.getName() + "\t\t -> " + course2.getCourseName());
			}
		}
		
		LoggedInAuthenticatedUser user = Login.execute();
		Enroll.execute(user);
		
	}
}
