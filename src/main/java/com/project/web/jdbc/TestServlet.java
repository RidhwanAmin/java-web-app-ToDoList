package com.project.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.sql.DataSource;

import java.sql.Statement;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Define datasource/ connection pool for Resource Injection 
	
	@Resource(name="jdbc/to_do_list")
	private DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	
	// Step 1 : Set up the printwriter
		
	PrintWriter out = response.getWriter();
	response.setContentType("text/plain");
		
	// Step 2 : Get a connection to the database
	
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRS = null;
	
	
	try {
		myConn = dataSource.getConnection();
		
		String sql = "select * from list"; 
		myStmt =  myConn.createStatement();
		
		myRS = myStmt.executeQuery(sql);
		
		while (myRS.next()) {
			
			String to_do = myRS.getString("to_do");
			out.println(to_do);
			
		}
		
	}
	
	catch(Exception exc) {
		exc.printStackTrace();
	}
		

	// Step 3 : 
	
	
	
	}
	
}
