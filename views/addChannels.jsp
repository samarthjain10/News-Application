<!DOCTYPE html>
<html>
	<head>
		<title>Add Channels</title>
		<meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.16/angular.min.js"></script>
		
		<script>
	 		var m = angular.module("newshunt",[]);
	 		m.controller("channelcontroller",function($scope,$http)
	 		{		
		 		$scope.channelData = function()
		 		{
			 		$http.post("channellist").then(function(response)
			 		{
						$scope.channeldata = response.data;
					});
			 		
			 		$http.post("userMenuList").then(function(resp)
			 		{
 						$scope.menuData = resp.data;
 					}); 
			 	
			 		$http({
   						url:"username",
     					method:"post",
      				}).then(function(res){
  						$scope.username = res.data;
  					});
		 		}
		 		
		 		$scope.subscribe = function()
		 		{			 
			 		var i;
             		var m = "";
		      		for(i=0 ; i<$scope.channeldata.length;i++)
		      		{
		    	    	if($scope.channeldata[i].selected==true)
		    	    	{
		    	      		m=m+$scope.channeldata[i].id+","; 	
		    	    	}
		      		}
		      		console.log(m);
			  		rec = {mychannel:m}
					$http({
						url:"subscribe",
						method:"post",
						data:rec
					}).then(function(res){
			     		alert("channels are subscribed");
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
			}
		</style>
	</head>
	<body ng-app="newshunt">
		<div ng-controller="channelcontroller" ng-init="channelData()">
		
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
	        
	        <div class="main">
				<form>
	        		<div class="container_fluid">
			    		<div class="box" ng-repeat="x in channeldata">
			        		<p><span>{{x.title}}</span></p>
			        		<p>{{x.description}}</p>
			        		<div class="checkbox">
			          		<label><input type="checkbox" ng-model="x.selected">Subscribe</label>
			     	   		</div>
			    		</div>
			    		<br>
			    		<br>
			        	<center>
			        		<button type="button" class="btn btn-success" ng-click="subscribe()">subscribe</button>
			        	</center>
			    	</div>
				</form>
			</div>
			
		</div>
	</body>
</html>