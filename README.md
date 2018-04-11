Course Management System
============
The University of Western Ontario
Computer Science 2212 Group Project
Group 45
---------
Group Members:
Yimin Xu,
Tianyuan Liu,
Mounib Samara,
Yuming Dong

Note: This assignment was submitted on April 8th 2018. Any new commits after that are personal improvements by myself.


Introduction
This is a simplified course management system. It provides the user with services based on their user type. All users have to login before they can use the services and we used names and IDs to authenticate them. Users' information is from the input file and we haven't built a database to store the information, which will be my next improvement. To run the program, run the main class in Test.java. Here are the services for the users.



Admin:


Start system

Start the system. All other services can not be performed if the system is not started.


Stop system

Stop the system


Read course file

Read the file that contains the information of the course and the user list related to it


Create user

Create a new user and add it to the register


Print all users

Print the user list in the register


Print all courses

Print the course list in the register


Instructor:


Add/modify marks

Add or modify marks for a specified student in the course that the instructor is responsible for


Calculate grade

Calculate and print the grade for a student based on the weights


PrintMarks

Print the marks for one or all stutents in the course


Student:


Enroll

Enroll into a course


Print courses

Print the courses that the student is already enrolled in


Print record

Print the marks for a course


Set notification Preference

Set the notification preference (Real notification, like mail of text, cannot be done in this program)