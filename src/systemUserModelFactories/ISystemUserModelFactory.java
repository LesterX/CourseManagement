package systemUserModelFactories;

import java.io.BufferedReader;

import offerings.ICourseOffering;
import systemUsers.SystemUserModel;
import database.IDatabase;

public interface ISystemUserModelFactory {
	SystemUserModel createSystemUserModel(BufferedReader br, ICourseOffering course);
	void generate_from_file(String file_name);
	void generate_from_db(IDatabase db);
}
