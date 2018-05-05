package systemUsers;

public interface SystemUser{
	
	void setName(String name);
	void setSurname(String surname);
	void setID(String ID);
	void setPassword(String password);
	
	String getName();
	String getSurname();
	String getID();
	String get_type();
	String getPassword();
}
