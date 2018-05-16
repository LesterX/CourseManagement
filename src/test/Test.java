/**
The University of Western Ontario
Computer Science 2212
Group 45
Course Management System
	Group Members:
		Yuming Dong
		Tianyuan Liu	250864391
		Mounib Samara	250868676
		Yimin Xu		250876566
*/
package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import offerings.CourseOffering;
import offerings.OfferingFactory;
import operations.loginOperations.*;
import operations.adminOperations.*;
import operations.instructorOperations.*;
import operations.studentOperations.*;
import registrar.Register;
import system.systemStatus;
import systemUsers.AdminModel;
import systemUsers.SystemUserModel;
import authenticatedUsers.LoggedInAuthenticatedUser;
import database.IDatabase;
import database.DatabaseServer;

public class Test {

	public static void main(String[] args) throws IOException{
		/**
		//Load the course file into the registrar, can also be done by admin service 
//		Create an instance of an OfferingFactory
		OfferingFactory factory = new OfferingFactory();
		BufferedReader br = new BufferedReader(new FileReader(new File("note_1.txt")));
//		Use the factory to populate as many instances of courses as many files we've got.
		CourseOffering	courseOffering = factory.createCourseOffering(br);
		ModelRegister.getInstance().registerCourse(courseOffering.getCourseID(), courseOffering);
		br.close();
//		Loading 1 file at a time
		br = new BufferedReader(new FileReader(new File("note_2.txt")));
//		here we have only two files
		courseOffering = factory.createCourseOffering(br);
		ModelRegister.getInstance().registerCourse(courseOffering.getCourseID(), courseOffering);
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

		//Add a admin account because there is no admin user in the input files
		AdminModel admin = new AdminModel();
		admin.setID("0000");
		admin.setName("Admin");
		admin.setSurname("Admin");
		ModelRegister.getInstance().registerUser("0000", admin);
		
		//Simple user interface
		boolean status;
			
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please login: ");
		//Read user input
		LoggedInAuthenticatedUser user = Login.execute();
		while (user != null)
		{
			switch (user.get_type())
			{
				case "Admin":
				{
					//No need to check system status here for admin
					//because admin needs to access the start system service
					System.out.println("Authentication Compelte: Admin");
					while (true)
					{
						System.out.println("\n\nSelect the service: ");
						System.out.println("1.Start system\n2.Stop system\n3.Read course file\n4.Create user\n5.Print all users \n6.Print all courses\n7.Logout\n");
						int line = Integer.parseInt(br.readLine());
						
						if (line < 1 || line > 6)
							break;
						
						switch (line)
						{
							case 1: 
								startSystem.execute(user);
								break;
							case 2: 
								stopSystem.execute(user);
								break;
							case 3: 
								readCourseFile.execute(user);
								break;
							case 4:
								createUser.execute(user);
								break;
							case 5:
								PrintAllUsers.execute(user);
								break;
							case 6:
								PrintAllCourses.execute(user);
							default:  
								break;
						}
					}
					break;
				}
				case "Instructor":
				{
					//If the system is stopped, block all services
					status = system.systemStatus.instance().status();
					if (!status)
					{
						System.out.println("System is closed");
						break;
					}
					System.out.println("Authentication Compelte: Instructor");
					while (true)
					{
						System.out.println("\n\nSelect the service: ");
						System.out.println("1.Add mark\n2.Calculate grade \n3.Modify mark \n4.Print mark\n5.Logout\n");
						int line = Integer.parseInt(br.readLine());
						
						if (line < 1 || line > 4)
							break;
						
						switch (line)
						{
							case 1: 
								AddMarks.execute(user);
								break;
							case 2: 
								CalculateGrades.execute(user);
								break;
							case 3: 
								ModifyMarks.execute(user);
								break;
							case 4: 
								PrintMarks.execute(user);
								break;
							default:  
								break;
						}
					}
					break;
				}
				case "Student":
				{
					//Same as above
					status = system.systemStatus.instance().status();
					if (!status)
					{
						System.out.println("System is closed");
						break;
					}
					System.out.println("Authentication Compelte: Student");
					while (true)
					{
						System.out.println("\n\nSelect the service: ");
						System.out.println("1.Enroll\n2.Print Records of a course \n3.Print all courses enrolled \n4.Set notification preference\n5.Logout\n");
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
					break;
				}
				default:
					break;
			}
			
			//Ask user to login if input is "Y" or "y"
			System.out.println("Do you want to login another account? [Y/N]");
			String in = br.readLine();
			if (!in.toUpperCase().equals("Y"))
				break;
			else
			{
				System.out.println("Please login: ");
				user = Login.execute();
			}
		}
		*/
		
		IDatabase db = new DatabaseServer("course_management");
		db.clear_tables();
		db.initiate();
		db.insert_user("0000", "0000", "admin", "admin", "Admin");
		db.insert_user("0001", "0001", "yx1", "yx1", "Instructor");
		db.insert_user("0002", "0002", "yx2", "yx2", "Student");
		db.insert_course("3360", "Intermediate Accounting", 1);
		db.add_tutor("0001", "3360");
		db.add_student_allowed("0002", "3360");
	}
}
