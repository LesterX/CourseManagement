package database;

import systemUsers.SystemUserModel;

public interface IDatabase 
{
	void initiate();
	void clear_tables();
	void insert_user(String id, String password, String first_name, String last_name, String type);
	void delete_user(String id);
	boolean authenticate(String id, String password);
	void insert_course(String id, String name, int semester);
	void add_tutor(String instructor_id, String course_id);
	void add_student_allowed(String student_id, String course_id);
	void add_student_enrolled(String student_id, String course_id);
	SystemUserModel get_user(String id);
	
}
