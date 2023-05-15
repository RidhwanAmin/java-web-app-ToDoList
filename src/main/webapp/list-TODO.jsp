<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>To Do List App</title>	
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>


<body>


	<div id="wrapper">
		<div id="header">
			<h2>To do list</h2>
		</div>
	</div>
	
	<div class="container">
	
		<div class="content">
			<!-- put button : add to do list -->
			 <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">Add To-Do-List</button>
			 
		     <div class="modal" id="myModal" >
		            <div class="modal-dialog">
		                <div class="modal-content">
		                    <div class="modal-header">
		                        <h5 class="modal-title">Add To-Do-List</h5>
		                    </div>
		                    <div class="modal-body">
		                        <form action = "ToDo_ControllerServlet" method = "GET">
		                        	<input type="hidden" name="command" value="ADD" />
		                            <div class="mb-3">
		                                <label class="form-label required">T0 DO</label>
		                                <input type="text" name = "todo" class="form-control">
		                            </div>
		                            
		                            
		                            <select class="form-select form-select-sm" aria-label=".form-select-sm example" name = "status">
									  <option selected >Open this select menu</option>
									  <option value="Not Done">Not Done</option>
									  <option value="In Progress">In Progress</option>
									  <option value="Finished">Finished</option>
									</select>
		                            
		                            <!--  
		                                <label class="form-label required">Status</label>
		                                <input type="text" name = "status" class="form-control">-->
		                            
		                   
		                        
		                    
		                    <div class="modal-footer">
		                        <button type="submit" value="Save" class="btn btn-primary">Submit</button>
		                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancel</button>
		                    </div>
		                    </form></div>
		                </div>
		            </div>
		      </div>
    
			
		
			<table class="table">
			
			<thead>
			
				<tr>
					<th>TODO List</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempLists" items="${TODO_LIST}">
					<!-- set up link for each to do list -->
					<c:url var="tempLink" value="ToDo_ControllerServlet">
						<c:param name = "command" value="LOAD" />
						<c:param name = "todoId" value="${tempLists.id}" />
					
					</c:url>
					
					<!-- set up link to delete a to do list -->
					<c:url var="deleteLink" value="ToDo_ControllerServlet">
						<c:param name = "command" value="DELETE" />
						<c:param name = "todoId" value="${tempLists.id}" />
					
					</c:url>
					
					<tr>
						<!-- <td> ${tempLists.id} </td> -->
						<td> ${tempLists.to_do} </td>
						<td> ${tempLists.status} </td>
						<td>
						<!-- 
						<a class="btn btn-primary" href="${tempLink}" role="button" class="btn btn-primary" data-toggle="modal" data-target="#edit">Edit</a>
						 -->
						<button type="button" class="btn btn-primary" onclick="location.href = '${tempLink}';">Update</button>
						<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#delete">Delete</button>
						
						<!-- Modal -->
						<div class="modal fade" id="delete" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-body">
						        Are you sure to delete?
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						        <button type="button" class="btn btn-danger" onclick="location.href = '${deleteLink}';">Delete</button>
						      </div>
						    </div>
						  </div>
						</div>
				
						
						
					
						
						</td>
					</tr>
				</c:forEach>
			
				</thead>
				
			</table>
		
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>

</body>


</html>








