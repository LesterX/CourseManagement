package offerings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import customDatatypes.EvaluationTypes;
import customDatatypes.Weights;
import registrar.ModelRegister;
import systemUserModelFactories.InstructorModelFactory;
import systemUserModelFactories.StudentModelFactory;
import systemUserModelFactories.SystemUserModelFactory;
import systemUserModelFactories.ISystemUserModelFactory;
import systemUsers.InstructorModel;
import systemUsers.StudentModel;
import systemUsers.SystemUserModel;

public class OfferingFactory {

//	Instantiate 
	public CourseOffering createCourseOffering(BufferedReader br) {
		// TODO Auto-generated method stub
		try{
		String line = br.readLine();
		CourseOffering course = new CourseOffering();
//		Using the ModelRegister - its a utility class that allows us to keep track of which IDs have already been populated
//		if we don't have it registered
		if(!ModelRegister.getInstance().checkIfCourseHasAlreadyBeenCreated(line.split("\t")[1])){
//			we populate the course object and initialize all fields
			course.setCourseName(line.split("\t")[0]);
			course.setCourseID(line.split("\t")[1]);
			course.setSemester(Integer.parseInt(line.split("\t")[2]));
			course.setInstructor(new ArrayList<InstructorModel>());
			course.setStudentsAllowedToEnroll(new ArrayList<StudentModel>());
			course.setEvaluationStrategies(new HashMap<EvaluationTypes, Weights>());
			course.setStudentsEnrolled(new ArrayList<StudentModel>());
//			and add the new course to the register
			ModelRegister.getInstance().registerCourse(course.getCourseID(), course);
		}
//			otherwise we load the existing instance from the register
			course = ModelRegister.getInstance().getRegisteredCourse(line.split("\t")[1]);
			line = br.readLine();
//			We create an instance of an InstructorModelFactory to create InstructorModel instances
			ISystemUserModelFactory theFactory = new InstructorModelFactory();
			for (int i=0;i<Integer.parseInt(line.split("\t")[2]);i++) {
//				the actual create instance method call
				course.getInstructor().add((InstructorModel)theFactory.createSystemUserModel(br, course));
			}
			line = br.readLine();
//			create an instance of StudentModelFactory and allocate it to the theFactory variable (same interface) to create StudentModel instances 
			theFactory = new StudentModelFactory();
			for (int i=0;i<Integer.parseInt(line.split("\t")[2]);i++) {
//				the actual create instance method call
				course.getStudentsAllowedToEnroll().add((StudentModel)theFactory.createSystemUserModel(br, course));
			}
//			consuming EVALUATION ENTITIES tag (you'll notice there are other standalone br.readLine() calls 
//			that consume and generally advance the input to the desirable line
			line = br.readLine();
			for(int i=0;i<4;i++) {
				line = br.readLine();
//				Check how this works || we select he appropriate enum value
				EvaluationTypes evaluationTypes = EvaluationTypes.fromString(line);
//				weights is an object we created for encapsulating the bare minimum of Map functionality that you'll probably need
				Weights weights = new Weights();
				line = br.readLine();
				int numberOfEvaluationWeights = Integer.parseInt(line.split("\t")[2]);
//				Read all the evaluation strategies that are available for a particular evaluation type and
				for(int j=0; j<numberOfEvaluationWeights;j++){
					line = br.readLine();
					weights.addToEvalStrategy(line.split("\t")[0], Double.parseDouble(line.split("\t")[1]));
				}
//				add them to the course
				course.getEvaluationStrategies().put(evaluationTypes, weights);
			}
			return course;
		}catch(IOException ioe){
			System.out.println(ioe.getMessage() + "exception thrown at CourseOfferingCreation"); 
			return null;
		}
	}
	
	//Create course from a line in a file
	//Each course input should have its own file
	public CourseOffering generate_from_file(String file_name)
	{
		try 
		{
			//Read course data from a file with preset format
			BufferedReader br = new BufferedReader(new FileReader(new File(file_name)));
			String id = br.readLine();
			String name = br.readLine();
			int semester = Integer.parseInt(br.readLine());
			String line = br.readLine();
			List<InstructorModel> instructors = new ArrayList<InstructorModel>();
			
			while (!line.equals("Students Allowed"))
			{
				InstructorModel tutor = (InstructorModel) ModelRegister.getInstance().getRegisteredUser(line);
				instructors.add(tutor);
				line = br.readLine();
			}
			
			line = br.readLine();
			List<StudentModel> allowed = new ArrayList<StudentModel>();
			
			while(!line.equals("Student Enrolled"))
			{
				StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(line);
				allowed.add(student);
				line = br.readLine();
			}
			
			line = br.readLine();
			List<StudentModel> enrolled = new ArrayList<StudentModel>();
			
			while(!line.equals("Evaluation"))
			{
				StudentModel student = (StudentModel) ModelRegister.getInstance().getRegisteredUser(line);
				enrolled.add(student);
				line = br.readLine();
			}
			
			Map<EvaluationTypes, Weights> strategy = new HashMap<EvaluationTypes, Weights>();
			line = br.readLine();
			while (line.equals("FC")||line.equals("FA")||line.equals("PC")||line.equals("PA"))
			{
				EvaluationTypes et = EvaluationTypes.fromString(line);
				Weights w = new Weights();
				
				while (!(line.equals("FC")||line.equals("FA")||line.equals("PC")||line.equals("PA")))
				{
					String[] l = line.split(" ");
					w.addToEvalStrategy(l[0],Double.parseDouble(l[1])/100);
				}
				
				strategy.put(et, w);
			}
			
			//Create new course
			CourseOffering course = new CourseOffering();
			course.setCourseID(id);
			course.setSemester(semester);
			course.setCourseName(name);
			course.setInstructor(instructors);
			course.setStudentsAllowedToEnroll(allowed);
			course.setStudentsEnrolled(enrolled);
			course.setEvaluationStrategies(strategy);
			
			return course;
		} catch (FileNotFoundException e) 
		{
			System.out.println(e.getMessage());
		} catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	
		
		return null;
	}
	
}
