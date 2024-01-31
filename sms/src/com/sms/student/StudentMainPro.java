package com.sms.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentMainPro {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Scanner s1=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url="jdbc:mysql://localhost:3306/sms_db";
		Connection con=DriverManager.getConnection(url, "root", "12345678");
		//switch cases
		while(true) {
			intro();
		System.out.println("******************************");
		System.out.println("Enter your operations: ");
		int operations=s1.nextInt();
		switch(operations) {
		case 1:
			System.out.println("-------------------------------------");
			System.out.println("*    *     INSERT RECORD     *      *");
			System.out.println("-------------------------------------");
			insert();
			break;
		case 2:
			System.out.println("-------------------------------------");
			System.out.println("*    *      EDIT RECORD     *       *");
			System.out.println("-------------------------------------");
			edit();
			break;
		case 3:
			System.out.println("-------------------------------------");
			System.out.println("*    *      VIEW RECORD     *       *");
			System.out.println("-------------------------------------");
			view();
			break;
		case 4:
			System.out.println("------------------------------------ ");
			System.out.println("*    *     DELETE RECORD     *      *");
			System.out.println("-------------------------------------");
			delete();
			break;
		case 5:
			System.out.println("--------------------------------------");
			System.out.println("*     *    PROGRAM STOPPED    *      *");
			System.out.println("--------------------------------------");
			System.out.println("Program stopped......");
			System.exit(0);
			break;
			
		default:
			System.out.println("Enter the valid number");
		}
		}
		
	}
	public static void delete() throws SQLException {
		String url2="jdbc:mysql://localhost:3306/sms_db";
		Connection con2=DriverManager.getConnection(url2, "root", "12345678");
		
		String query="DELETE FROM student_info WHERE (id=?)";
		PreparedStatement ps=con2.prepareStatement(query);
		
		Scanner s=new Scanner(System.in);
		System.out.println("Select Id to delete: ");
		int id=s.nextInt();
		
		ps.setInt(1, id);
		ps.executeUpdate();
		System.out.println("Record deleted successfully.......");
	}
	
 	public static void edit() throws SQLException {
		String url4="jdbc:mysql://localhost:3306/sms_db";
		Connection con4=DriverManager.getConnection(url4, "root", "12345678");
		
		
		String query="UPDATE student_info SET Name=?,std=?,fname =?,mobile=? WHERE(id =?);";
		PreparedStatement ps=con4.prepareStatement(query);
		Scanner s=new Scanner(System.in);
		System.out.println("Select the id to edit: ");
		int i=s.nextInt();
		s.nextLine();
		System.out.println("Enter Name: ");
		String n=s.nextLine();
		System.out.println("Enter Std: ");
		String c=s.nextLine();
		System.out.println("Enter Fname:");
		String fn=s.nextLine();
		System.out.println("Enter Mobileno. :");
		String m=s.nextLine();
		
		ps.setString(1, n);
		ps.setString(2, c);
		ps.setString(3, fn);
		ps.setString(4, m);
		ps.setInt(5, i);
		
		ps.executeUpdate();
		System.out.println("Data inserted successfully.....");
		
		
	}
	
	
	
 	public static void view() throws SQLException {
		String url1="jdbc:mysql://localhost:3306/sms_db";
		Connection con1=DriverManager.getConnection(url1, "root", "12345678");
		
		Statement st=con1.createStatement();
		ResultSet rs=st.executeQuery("select * from student_info");
		System.out.println("ID | Name | std | Father | Mobileno.");
		System.out.println("_____________________________________");
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getString(3)+" | "+rs.getString(4)+" | "+rs.getString(5));
	}
	}
 	

	public static void insert() throws SQLException {
		Scanner s=new Scanner(System.in);
		String url="jdbc:mysql://localhost:3306/sms_db";
		Connection con=DriverManager.getConnection(url, "root", "12345678");
		
		System.out.println("Enter your Name: ");
		String n=s.nextLine();
		System.out.println("Enter your Class: ");
		String c=s.nextLine();
		System.out.println("Enter your Father Name ; ");
		String f=s.nextLine();
		System.out.println("Enter your Mobile no. :");
		String m=s.nextLine();
		
		String query="insert into student_info (name,std,fname,mobile) "
				+ "value (?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, n);
		ps.setString(2, c);
		ps.setString(3, f);
		ps.setString(4, m);
		
		ps.executeUpdate();
	    System.out.println("Date inserted successfully......");
	}
	
	public static void intro() {
		System.out.println("*****************************************");
		System.out.println("*      *     STUDENTS MODULE     *      *");
		System.out.println("*****************************************");
		System.out.println("\n 1.Insert ");
		System.out.println("2. Edit ");
		System.out.println("3. View ");
		System.out.println("4. Delete ");
		System.out.println("5. Stop ");
	}
}
