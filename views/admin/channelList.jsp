<html>
    <head>
        <title>Feedback List</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.16/angular.min.js"></script>
        <script>
        	var m =angular.module("newshunt",[]);
        	m.controller("homecontroller",function($scope,$http)
        	{
        		$scope.home = function()
        		{
        			$http.post("AdminMenuList").then(function(resp)
                	{
                		$scope.adminMenuData = resp.data;
                	}); 
        			 
					$http.post("ShowChannelList").then(function(resp)
					{
		        		$scope.ShowChannelList = resp.data;
	        		}); 
	        	}
        		
        		$scope.del = function(rec)
        		{
        			$http.post("deleteChannelInfo",rec).then(function(resp)
        			 		{
        	 					if(resp.data==1)
        	 					{
        	 						alert("Details deleted");
        	 					}
        	 				});
        		}
        		
        		$scope.update = function(selectRecord)
        		{
        			$scope.rec = selectRecord;
        			$('#myModal').modal('show');
        			 
         		}
        		$scope.SaveUpdateInfo=function(rec){
        			$http.post("updateChannelInfo",rec).then(function(resp)
        			 		{
        	 					if(resp.data==1)
        	 					{
        	 						alert("Details changed");
        	 					}
        	 				});
        			
        			
        		}
        	});	
       </script>
       <style>
            body
            {
            	font-family: "Lato", sans-serif;
            }

            .sidenav 
            {
	            height: 100%;
	            width: 200px;
	            position: fixed;
	            z-index: 1;
	            top: 0;
	            left: 0;
	            background-color: khaki;
	            overflow-x: hidden;
	            padding-top: 80px;
            }

            .sidenav a
            {
	            padding: 6px 8px 6px 16px;
	            text-decoration: none;
	            font-size: 16px;
	            color: #818181;
	            display: block;
            }

            .sidenav a:hover 
            {
            	color: black;
            }

            .main
            {
	            margin-top:70px;
	            margin-left: 210px; 
	            font-size: 28px; 
	            padding: 0px 10px;
            }
            
            .newsbox
            {
            	border: 2px solid #ccc;
			  	background-color: #eee;
				border-radius: 5px;
				padding: 16px;
				margin-top: 70px;
				margin-left:80px; 
				margin-right:80px; 
            }
            
            table, th , td 
            {
                border: 2px solid grey;
                border-collapse: collapse;
                padding: 5px;
            }
       </style>
    </head>
    <body ng-app = "newshunt">
        <div ng-controller="homecontroller" ng-init="home()">
        
	        <nav class="navbar navbar-light navbar-fixed-top" style="background-color: #e3f2fd;">
	            <div class="container_fluid">
	                <div class="navbar-header">
	                    <a class="navbar-brand" href="AdminHome">NewsHunt</a>
	                </div>
	                <ul class="nav navbar-nav">
	                    <li></li>
	                </ul>
	                <ul class="nav navbar-nav navbar-right">
	                    <li style="padding-right : 16px;"><a href="#">Admin</a></li>
	                </ul>
	            </div>
	        </nav>
	        
	        <div class="sidenav" >
	            <div ng-repeat = "x in adminMenuData">
	            	<a href="{{x.url}}">{{x.name}}</a>
	            </div>
	        </div>
	        
	        <div class="main">
	        	<h1>
	        	<table border="">
	        	<thead>
	        	<tr>
	        	<th>id</th>
	        	<th>title</th>
	        	<th>description</th>
	        	<th>url</th>
	        	<th>image</th>
	        	<th></th>
	        	</tr>
	        	</thead>
	        	<tbody>
	        	<tr ng-repeat="x in ShowChannelList">
	        	<td>{{x.id}}</td>
	        	<td>{{x.title}}</td>
	        	<td>{{x.description}}</td>
	        	<td>{{x.url}}</td>
	        	<td>{{x.image}}</td>
	        	<td>
	        	<button ng-click="update(x)">update</button>
	        	<button ng-click="del(x)">delete</button>
	        	
	        	
	        	  				<!-- The Modal -->
					  <div class="modal" id="myModal">
					    <div class="modal-dialog">
					      <div class="modal-content">
					      
					        <!-- Modal Header -->
					        <div class="modal-header">
					          <h4 class="modal-title">Update Record</h4>
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					        </div>
					        
					        <!-- Modal body -->
					        <div class="modal-body">
					          title <input type = "text" ng-model="rec.title"/>
					          url <input type = "text" ng-model="rec.url"/>
					          description <input type = "text" ng-model="rec.description"/>
					          image <input type = "text" ng-model="rec.image"/>
					        </div>
					        
					        <!-- Modal footer -->
					        <div class="modal-footer">
					        <button type="button" class="btn btn-danger" ng-click="SaveUpdateInfo(rec)">update</button>
					          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					        </div>
					        
					      </div>
					    </div>
					  </div>
					  
					  
					  
	        	</td>
	        	</tr>
	        	</tbody>
	        	</table><br>
	            </h1>
        	</div>
        	
        </div>
    </body>
</html>