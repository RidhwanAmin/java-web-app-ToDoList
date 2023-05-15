package com.project.web.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ListDBUtil {

	private DataSource dataSource;

	public ListDBUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<To_do_list> getLists() throws Exception {
		
		List<To_do_list> lists = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from list";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String todo = myRs.getString("to_do");
				String status = myRs.getString("status");
				
				// create new student object
				To_do_list tempLists = new To_do_list(id, todo, status);
				
				
				// add it to the list of students
				lists.add(tempLists);				
			}
			
			return lists;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	
	public void add_ToDo(To_do_list the_todo) throws SQLException {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection 
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into list"
					+ "(to_do, status)"
					+ "values (?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the to do list 
			myStmt.setString(1, the_todo.getTo_do());
			myStmt.setString(2, the_todo.getStatus());
			
			// execute sql insert 
			myStmt.execute();
			
		}
		// clean up JDBC object 
		finally {
			close(myConn, myStmt, null);
		}
		
		
		
		
		
		
		
	}

	public To_do_list get_todo(String theListID) throws Exception {
		// TODO Auto-generated method stub
		To_do_list the_todo = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		int todoId;
		
		try {
			//convert student id to int
			todoId = Integer.parseInt(theListID);
			
			//get connection to database
			myConn = dataSource.getConnection();	
			
			//create sql to get selected to do
			String sql = "select * from list where id=?";
					
			
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set the parameters
			myStmt.setInt(1, todoId);
			
			//execute statement
			myRs = myStmt.executeQuery();
			
			//retrieve data from the result set row
			if(myRs.next()) {
				String todo = myRs.getString("to_do");
				String status = myRs.getString("status");
				
				// use the to do id during construction
				
				the_todo = new To_do_list(todoId, todo, status);
			}
			else {
				throw new Exception("Could not find student id: " + todoId);
			}		
			return the_todo;
		}
		finally {
			//clean up JDBC
			close(myConn, myStmt, myRs);
		}
		
	
	}

	public void updateList(To_do_list the_todo) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
		// get db connection 
		myConn = dataSource.getConnection();
		
		// create SQL update statement
	
		
		String sql = "update list "
				+ "set to_do=?, status=?"
				+ "where id=?";
		//prepare statement
		myStmt = myConn.prepareStatement(sql);
		//set params
		
		myStmt.setString(1, the_todo.getTo_do());
		myStmt.setString(2, the_todo.getStatus());
		myStmt.setInt(3, the_todo.getId());
		//execute SQL statement
		myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}
	
	}
	
	public void deleteToDo(String theListID) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			
			//Convert todo id to int
			int todoId = Integer.parseInt(theListID); 
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student 
			String sql = "delete from list where id=?";
			
			//prepare starement 
			myStmt = myConn.prepareStatement(sql);
			
			//set params 
			myStmt.setInt(1, todoId);
			
			//execute sql statement 
			myStmt.execute();
		}
		finally {
			// clean JDBC code
			close(myConn, myStmt, null);
		}
	} 

}
















