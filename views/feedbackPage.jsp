<html>
    <head>
        <title>Feedback</title>
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
					
					$http({
		   				url:"username",
		     			method:"post",
		      			}).then(function(res){
		  					$scope.username = res.data;
		  				});
	        	}
        		$scope.feedback = function()
      			{      		
      				data = {email:$scope.email, subject:$scope.subject ,message:$scope.message};
      	    		$http.post("feedbackform",data).then(function(response)
      	    		{
      	    			if(response.data==1)
      	    			{      	    		
      	    	  			alert("Thankyou !");
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
	        
	        <div class="main">
	        	<h2>FEEDBACK FORM</h2><br>
	        		<form><h4>
						<label>Email: <br> <input type="text" ng-model="email"></label>
						<br>
						<br> 
						<label>Subject: <br> <input type="text" ng-model="subject"></label>
						<br>
						<br>
						<label> Message: <br> <textarea  rows="5"  name="message" ng-model="message"></textarea></label>
						<br>
						<br>
						<br>
						<button type="button" class="btn btn-success" ng-click="feedback()">SUBMIT</button>
					</h4></form>
        	</div>
        </div>
    </body>
</html>