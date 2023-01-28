package com.miniproject.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class RetreiveQuestions {

	//Declaring Global Variables
	int correctanswer ;
	int wronganswer;
	
	//Creating non static method
	public RetreiveQuestions getConnection() {

		//Printing Quiz Exam on Console
		System.out.println("Quiz Exam");
		
		//Creating object of Scanner class to take the user input
		Scanner sc = new Scanner(System.in);
	
		
		//Creating Connection of Java application with the MySql Database with the help of JDBC
		try {
			//Load the driver class
			Class.forName("com.mysql.jdbc.Driver");

			//Establish the connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
			
			//Using for loop to examine multiple students
			for(int i=1;i<=2;i++) {
				
				System.out.println();
				System.out.println("Student "+i+" Exam begins");
				
				
			//Preparing the Query Statement
			PreparedStatement ps = con.prepareStatement("SELECT * FROM QUESTIONS");
		
			//Submit and Execute Statement
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				System.out.println();
				System.out.print(rs.getString(1));
				System.out.println("."+rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getString(4));
				System.out.println(rs.getString(5));
				System.out.println(rs.getString(6));
				System.out.println();
				
				
				System.out.println("Enter the Option");
				
				//Storing user input in String
				String answer = sc.next();
		
				//Using if else to check the entered answer by user
				//equalsIgnoreCase() method compares two strings, ignoring lowercase and uppercase
				if(answer.equalsIgnoreCase(rs.getString(7))) {
					System.out.println("Correct Answer");
					correctanswer++;
				}else {
					System.out.println("Wrong Answer");
					wronganswer++;
				}
				System.out.println();
			}
			
			System.out.println("Exam Completed ");
			System.out.println("Score obtained by Student in Exam >>"+correctanswer);
			
			
			//Using if else if ladder to calculate the grade
			if(correctanswer >= 8 && correctanswer <= 10) {
				System.out.println("Grade of Student >> Class A");
			} else if (correctanswer >= 6 && correctanswer < 8) {
				System.out.println("Grade of Student >> Class B");
			} else if (correctanswer >= 5 && correctanswer<6 ) {
				System.out.println("Grade of Student >> Class C");
			} else if (correctanswer == 5) {
				System.out.println("Grade of Student >> Class D");
			}else {
				System.out.println("Student failed in Exam");
			}
			
			correctanswer=0;
			wronganswer=0;
			
		}
		}catch (Exception e) {
			e.getMessage();
		}

		return new RetreiveQuestions();
	}
	public static void main(String[] args) {
		
		//Creating the object of the class
		RetreiveQuestions retreive = new RetreiveQuestions();
		
		//Calling a non static method using a object
		retreive.getConnection();
	}

}
