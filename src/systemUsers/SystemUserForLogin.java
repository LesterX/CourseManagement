package systemUsers;

public class SystemUserForLogin implements ISystemUserForLogin 
{
	private String name;
	private String surname;
	private String ID;
	private String type;
	
	public SystemUserForLogin(){}
	
	public SystemUserForLogin(String n, String s, String id, String t)
	{
		name = n;
		surname = s;
		ID = id;
		type = t;
	}
	
	public String getName() {return name;}
	public String getSurname() {return surname;}
	public String getID() {return ID;}
	public String getType() {return type;}
	public void setName(String n) {name = n;}
	public void setSurname(String s) {surname = s;}
	public void setID(String id) {ID = id;}
	public void setType(String t) {type = t;}
	
	public Boolean validate(String n, String s, String id)
	{
		if (name == null || surname == null || ID == null)
			return false;
		
		if (name.equals(n) && surname.equals(s) && ID.equals(id))
			return true;
		else
			return false;
	}
}
