package systemUsers;

public interface ISystemUserForLogin extends SystemUserModel 
{
	String getName();
	String getSurname();
	String getID();
	String getType();
	void setName(String name);
	void setSurname(String surname);
	void setID(String ID);
	void setType(String type);
	Boolean validate(String name, String surname, String ID);
}
