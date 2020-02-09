<html>
	<head>
		<meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.16/angular.min.js"></script>
        <script>
        	var m = angular.module("newshunt",[]);
	 		m.controller("accountinfo",function($scope,$http)
	 		{
	 			$scope.updateInfo = function(info)
				{
			 		$http.post("updateAccountInfo",info).then(function(resp)
			 		{
	 					if(resp.data==1)
	 					{
	 						alert("Details changed");
	 					}
	 				});
		 		}
		 		$scope.menubar = function()
		 		{
			 		$http.post("userMenuList").then(function(resp)
			 		{
 						$scope.menuData = resp.data;
 					}); 
			 		
					$http.post("fetchAccountInfo").then(function(resp)
					{
	 					$scope.userData=resp.data;
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
				
			.box 
			{
			  border: 2px solid #ccc;
			  background-color: #eee;
			  border-radius: 5px;
			  padding: 16px;
			  margin-top: 70px;
			  margin-left:280px; 
			  margin-right:280px; 
			}
			
			.box::after
			{
			  content: "";
			  clear: both;
			  display: table;
			}
			
			.box img 
			{
			  float: left;
			  margin-right: 20px;
			  border-radius: 50%;
			}
			
			.box span 
			{
			  font-size: 20px;
			  margin-right: 15px;
			}
			
			@media (max-width: 20000px) 
			{
			  .box 
			  {
			      text-align: center;
			  }
			
			  .box img 
			  {
			      margin: auto;
			      float: none;
			      display: block;
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
			  
			  .button
			  {
			  	margin-top: 500px;
			  }
			  
			  .main 
			  {
			  	margin-top:70px;
			  	margin-left: 210px; 
			  	font-size: 28px; 
			  	padding: 0px 10px;
			  }
			}
		</style>
	</head>
	<body ng-app="newshunt">
		<div ng-controller="accountinfo" ng-init="menubar()">
		
			<nav class="navbar navbar-light navbar-fixed-top" style="background-color: #e3f2fd;">
	            <div class="container_fluid">
	                <div class="navbar-header">
	                    <a class="navbar-brand" href="home">NewsHunt</a>
	                </div>
	                <ul class="nav navbar-nav">
	                    <li></li>
	                </ul>
	                <ul class="nav navbar-nav navbar-right">
	                    <li ng-repeat = "a in username" style="padding-right : 16px;"><a href="#">{{a}}</a></li>
	                </ul>
	            </div>
	        </nav>
        
	        <div class="sidenav" >
	            <div ng-repeat = "x in menuData">
	            	<a href="{{x.url}}">{{x.name}}</a>
	            </div>
	        </div>
	        
	        <div class="main">
	        	<form>
	        	<h3>
	        		EMAIL ID : &nbsp;
	        		<input type="text" ng-model = "userData.email" disabled>
	        		<br>
	        		<br>
	        		NAME : &nbsp;
	        		<input type="text" ng-model="userData.name">
	        		<br>
	        		<br>
	        		PASSWORD : &nbsp;
	        		<input type="text" ng-model="userData.password">
	        		<br>
	        		<br>
	        		PHONE NO : &nbsp;
	        		<input type="text" ng-model="userData.phone">
	        		<br>
	        		<br>
	        		<button type="button" class="btn btn-success" ng-click="updateInfo(userData)">UPDATE</button>
	        	</h3>
	        	</form>
	        </div>
	        
		</div>
	</body>
</html>