package systemUsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import customDatatypes.EvaluationTypes;
import customDatatypes.Marks;
import customDatatypes.NotificationTypes;
import offerings.ICourseOffering;

public class StudentModel implements IStudentModel{
	
	private String type = "Student";
	private String eva_type;
	private String name;
	private String surname;
	private String ID;
	private String password;
	private List<ICourseOffering> coursesAllowed;
	private List<ICourseOffering> coursesEnrolled;
	private Map<ICourseOffering, EvaluationTypes> evaluationEntities;
//	check the comments at the Marks Class this map should contain as many pairs of <CourseOffering, Marks> as course that 
//	the student has enrolled in.
	private Map<ICourseOffering, Marks> perCourseMarks;
	private NotificationTypes notificationType;
	
	public StudentModel() {}
	
	public String get_type()
	{
		return type;
	}
	
	public String get_eva_type()
	{
		return eva_type;
	}
	
	public void set_eva_type(String et)
	{
		eva_type = et; 
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String p)
	{
		password = p;
	}
	
	public List<ICourseOffering> getCoursesAllowed() {
		if (coursesAllowed == null)
			coursesAllowed = new ArrayList<ICourseOffering>();
		return coursesAllowed;
	}
	
	public void setCoursesAllowed(List<ICourseOffering> coursesAllowed) {
		this.coursesAllowed = coursesAllowed;
	}
	
	public List<ICourseOffering> getCoursesEnrolled() {
		if (coursesEnrolled == null)
			coursesEnrolled = new ArrayList<ICourseOffering>();
		return coursesEnrolled;
	}
	
	public void setCoursesEnrolled(List<ICourseOffering> coursesEnrolled) {
		this.coursesEnrolled = coursesEnrolled;
	}
	
	public Map<ICourseOffering, EvaluationTypes> getEvaluationEntities() {
		return evaluationEntities;
	}
	
	public void setEvaluationEntities(Map<ICourseOffering, EvaluationTypes> evaluationEntities) {
		this.evaluationEntities = evaluationEntities;
	}
	
	public Map<ICourseOffering, Marks> getPerCourseMarks() {
		return perCourseMarks;
	}
	
	public void setPerCourseMarks(Map<ICourseOffering, Marks> perCourseMarks) {
		this.perCourseMarks = perCourseMarks;
	}
	
	public NotificationTypes getNotificationType() {
		return notificationType;
	}
	
	public void setNotificationType(NotificationTypes notificationType) {
		this.notificationType = notificationType;
	}
	
	
	
}
