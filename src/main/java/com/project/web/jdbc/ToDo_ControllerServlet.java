package com.project.web.jdbc;


import java.util.List;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.sql.DataSource;

/**
 * Servlet implementation class ToDo_ControllerServlet
 */
@jakarta.servlet.annotation.WebServlet("/ToDo_ControllerServlet")
public class ToDo_ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ListDBUtil listDBUtil;
	
	// Define datasource connection pool for resource injection
	@Resource(name="jdbc/to_do_list")
	private DataSource dataSource;
	
	

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		// create our to do list db util ... and pass in the conn pool / datasource
		try {
			listDBUtil = new ListDBUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// list the students ... in mvc fashion
				try {
					// read the comment parameter from html
					String theCommand = request.getParameter("command");
					
					//if the command is missing, then default to do list 
					if (theCommand == null) {
						theCommand = "LIST";
					}
					
					//route the appropiate parameter
					switch (theCommand)
					{
					
					case "LIST":
						ToDo(request, response);
						break;
					
						
					case "ADD":
						add_ToDo(request, response);
						break;
						
					case "UPDATE":
						update_ToDo(request, response);
						break; 
						
					case "LOAD":
						load_ToDo(request, response);
						break;
						
					case "DELETE":
						delete_ToDo(request, response);
						break;
						
					default:
						ToDo(request, response);
					}
					
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
	}
	
	
	
	
	private void delete_ToDo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read todo id from form data
		String theListID = request.getParameter("todoId");
		
		//delete todo from database
		listDBUtil.deleteToDo(theListID);
		
		
		//send them back to the "to do list page"
		ToDo(request, response);
		
	}


	private void update_ToDo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read list info from form data
		int id = Integer.parseInt(request.getParameter("todoId"));
		String todo = request.getParameter("todo");
		String status = request.getParameter("status");
		
		// create a new to do list object 
		To_do_list the_todo = new To_do_list(id, todo, status);
		
		//perform update on database
		listDBUtil.updateList(the_todo);
		
		
		// send them back to the "list" page
		ToDo(request, response);
		
		
	}


	private void load_ToDo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read list id from form data
		String theListID = request.getParameter("todoId");
		
		// get to do list from database (db util)
		To_do_list the_todo =  listDBUtil.get_todo(theListID);
		
		
		//place student in the request attribute 
		request.setAttribute("THE_LIST", the_todo);
		
		// send to jsp page : update list to do .jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-list.jsp");
		dispatcher.forward(request, response);
		
	}


	private void add_ToDo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		// read todo info from form data
		String todo = request.getParameter("todo");
		String status = request.getParameter("status");
		// create a to do object
		To_do_list the_todo = new To_do_list(todo, status); 
		//add to do to  database
		listDBUtil.add_ToDo(the_todo);
		
		//send back to to-do list page
		ToDo(request, response);
		
		
	}

	private void ToDo(HttpServletRequest request, HttpServletResponse response) 
		// TODO Auto-generated method stub
		throws Exception {

			// get to-do from db util
			List<To_do_list> lists = listDBUtil.getLists();
			
			// add to-do to the request
			request.setAttribute("TODO_LIST", lists);
					
			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-TODO.jsp");
			dispatcher.forward(request, response);
		}
	
	
		


}
