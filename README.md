Course Management System
============
The University of Western Ontario</br>
Computer Science 2212 Group Project</br>
Group 45</br>
---------
Group Members:</br>
Yimin Xu</br>
Tianyuan Liu</br>
Mounib Samara</br>
Yuming Dong</br>

Note: This assignment was submitted on April 8th 2018. Any new commits after that are personal improvements by myself.
</br>

Introduction</br>
This is a simplified course management system. It provides the user with services based on their user type. All users have to login before they can use the services and we used names and IDs to authenticate them. Users' information is from the input file and we haven't built a database to store the information, which will be my next improvement. To run the program, run the main class in Test.java. Here are the services for the users.


</br></br>
Admin:</br>
	Start system</br>
	Start the system. All other services can not be performed if the system is not started.</br></br>
	Stop system</br>
	Stop the system</br></br>
	Read course file</br>
	Read the file that contains the information of the course and the user list related to it</br></br>
	Create user</br>
	Create a new user and add it to the register</br></br>
	Print all users</br>
	Print the user list in the register</br></br>
	Print all courses</br>
	Print the course list in the register</br></br></br>
Instructor:</br>
	Add/modify marks</br>
	Add or modify marks for a specified student in the course that the instructor is responsible for</br></br>
	Calculate grade</br>
	Calculate and print the grade for a student based on the weights</br></br>
	PrintMarks</br>
	Print the marks for one or all stutents in the course</br></br></br>
Student:</br>
	Enroll</br>
	Enroll into a course</br></br>
	Print courses</br>
	Print the courses that the student is already enrolled in</br></br>
	Print record</br>
	Print the marks for a course</br></br>
	Set notification Preference</br>
	Set the notification preference (Real notification, like mail of text, cannot be done in this program)</br></br>