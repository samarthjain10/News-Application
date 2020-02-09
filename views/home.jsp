<html>
    <head>
        <title>Home page</title>
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
        			$http.post("userMenuList").then(function(resp)
        			{
        				$scope.menuData = resp.data;
        			}); 
        			 
					$http.post("news").then(function(resp)
					{
		        		$scope.news = resp.data;
	        		}); 
					
					$http({
		   				url:"username",
		     			method:"post",
		      			}).then(function(res){
		  					$scope.username = res.data;
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
       </style>
    </head>
    <body ng-app = "newshunt">
        <div ng-controller="homecontroller" ng-init="home()">
        
	        <nav class="navbar navbar-light navbar-fixed-top" style="background-color: #e3f2fd;">
	            <div class="container_fluid">
	                <div class="navbar-header">
	                    <a class="navbar-brand" href="home">NewsHunt</a>
	                </div>
	                <ul class="nav navbar-nav">
	                    <li></li>
	                </ul>
	                <ul class="nav navbar-nav navbar-right">
	                    <li ng-repeat = "a in username" style="padding-right : 16px;"><a href="accountInfo">{{a}}</a></li>
	                </ul>
	            </div>
	        </nav>
	        
	        <div class="sidenav" >
	            <div ng-repeat = "x in menuData">
	            	<a href="{{x.url}}">{{x.name}}</a>
	            </div>
	        </div>
	        
	        <div class="main" ng-repeat="x in news">
	        	<div class="newsbox">
	            	Channel : {{x.channelName}}<br>
	            	Date : {{x.date}} <br>
	            	<a href = {{x.link}}>{{x.title}}</a>
	            </div>
        	</div>
        	
        </div>
    </body>
</html>