package adminServices;
import systemUsers.StudentModel;
import authenticatedUsers.LoggedInAuthenticatedUser;
public class createStudent {
	private StudentModel student;
	
	public createStudent(LoggedInAuthenticatedUser user, String name, String surName, String ID) {
		if(user.getAuthenticationToken().getUserType()=="admin") {
			student = new StudentModel();
			student.setName(name);
			student.setSurname(surName);
			student.setID(ID);
		}
		
	}

}
