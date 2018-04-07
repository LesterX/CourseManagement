package testHarness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import offerings.CourseOffering;
import offerings.OfferingFactory;
import operations.*;
import authenticatedUsers.LoggedInAuthenticatedUser;

public class TestStudentModelFactory_1 {

	public static void main(String[] args) throws IOException{
		//GenerateUsers.generate_users("note_1.txt");
		//GenerateUsers.generate_users("note_2.txt");
		
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
		
		/**
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
		*/
		
		//Simple user interface
		System.out.println("Please login: ");
		LoggedInAuthenticatedUser user = Login.execute();
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		switch (user.get_type())
		{
			case "Admin":
			{
				//TODO
			}
			case "Instructor":
			{
				//TODO
			}
			case "Student":
			{
				System.out.println("Authentication Compelte: Student");
				while (true)
				{
					System.out.println("Select the service: ");
					System.out.println("1.Enroll\n2.Print Records of a course \n3.Print all courses enrolled \n4.Set notification preference\n5.Quit");
					int line = Integer.parseInt(br.readLine());
					
					if (line < 1 || line > 4)
						break;
					
					switch (line)
					{
						case 1: 
							Enroll.execute(user);
							break;
						case 2: 
							PrintRecord.execute(user);
							break;
						case 3: 
							PrintCoursesEnrolled.execute(user);
							break;
						case 4: 
							SetNotifPref.execute(user);
							break;
						default:  
							break;
					}
				}
			}
		}
	}
}
