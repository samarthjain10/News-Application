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
        			
        			$http.post("userListCount").then(function(resp)
                	{
                		$scope.userListCount = resp.data;
                	});
        			
        			$http.post("ChannelListCount").then(function(resp)
                    {
                        $scope.ChannelListCount = resp.data;
                    });
        			
        			$http.post("SuscribedListCount").then(function(resp)
                    {
                    	$scope.SuscribedListCount = resp.data;
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
	                    <a class="navbar-brand" href="#">NewsHunt</a>
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
	        	<th>No of users</th>
	        	<th>No of channels</th>
	        	<th>No of suscribed channels</th>
	        	<th>No of unsuscribed channels</th>
	        	</tr>
	        	</thead>
	        	<tbody>
	        	<tr>
	        	<td>{{userListCount}}</td>
	        	<td>{{ChannelListCount}}</td>
	        	<td>{{SuscribedListCount}}</td>
	        	<td>{{ChannelListCount-SuscribedListCount}}</td>
	        	</tr>
	        	</tbody>
	        	</table>
	            </h1>
        	</div>
        	
        </div>
    </body>
</html>