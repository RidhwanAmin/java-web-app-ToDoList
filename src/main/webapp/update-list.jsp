<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Update List</title>	
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>

<body>

	<div id="wrapper">
		<h3>Update List</h3>
		
         
         
         <form action="ToDo_ControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="todoId" value="${THE_LIST.id}" />
			
			<div class="mb-3">
			
                  <label class="form-label required">T0 DO</label>
                  <input type="text" name = "todo" value="${THE_LIST.to_do}" class="form-control">
                  
		    </div>
		    
		    
		     <select class="form-select form-select-sm" aria-label=".form-select-sm example" name = "status">
		     						
									  <option selected disabled>Current Selected Status : ${THE_LIST.status}</option>
									  
									  <option value="Not Done">Not Done</option>
									  <option value="In Progress">In Progress</option>
									  <option value="Finished">Finished</option>
			
									</select>
		    <!--  
		    <div class="mb-3">
		    
                 <label class="form-label required">Status</label>
                 <input type="text" name = "status" value="${THE_LIST.status}"  class="form-control">
                 
		    </div>-->
		    
		    <div class="modal-footer">
		                        <button type="submit" value="Save" class="btn btn-primary">Save Changes</button>
		    </div>
		    
	
		</form>
	

		

		
		<div style="clear: both;"></div>
		
		<p>
			<a href="ToDo_ControllerServlet">Back to List</a>
		</p>
	
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>

</html>











