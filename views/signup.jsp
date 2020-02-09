<html>
    <head>
        <title>Sign up</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.16/angular.min.js"></script>
        <script>
        	var m = angular.module("newshunt",[]);
            m.controller("signup",function($scope , $http)
            {
            	$scope.saveInfo = function()
            	{
            		data = {name:$scope.name , email:$scope.email , password:$scope.password , confirmPassword:$scope.cpassword ,phone:$scope.phno};
            	    $http.post("saveUserInfo",data).then(function(res)
            	    {
            	    	if(res.data==0)
   							alert("Password missmatch");
   						else if(res.data==1)
   							alert("Fill out mandatory fields");
   						else if(res.data==2)
       						alert("Username is already present");
   						else if(res.data==3)
   							alert("Data is saved");
            	    });	
            	}
            });  
        </script>
        <style>
            .card
            {
                background-color: floralwhite !important;
                width: 400px;
                height: 570px;
                margin-top: 100px;
                margin-left: 25%;
                position: absolute;
            }
            
            .container
            {
                align-content:center;
            }
            
            .card-header
            {
                position: relative;
                align-content: center;
                text-align: center;
                color: black;
                font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
                margin-top: 20px;
                font-size: 30px;
            }
            
            .card-footer
            {
                position: relative;
                text-align: center;
                color: black;

            }
            
            input
            {
                position: relative;
                height: 37px;
                width: 70%;
                background-color: lightgrey;
                margin-left: 15%;
                border: 2px solid lightslategray;
                border-radius: 3px;
                padding: 10px;
            }
            
            input[type=checkbox]
            {
                height: 15px;
                width: 15px;
                margin-left: 15%;
                position: relative; 
            }
            
            button
            {
                position: relative;
                align-content: center;
                margin-left: 38%;
                width: 25%;
            }
            
            button:hover
            {
                background-color: #45a049;
            }
        </style>
    </head>
    <body ng-app="newshunt">
        <div class="container" ng-controller="signup">  
          
            <div class="card">
                <div class="card-header">
                    <p><strong>Sign up</strong> for free !</p>
                </div>
                <br>
                <br>
                <div class="card-body">
                    <form>
                    	<input type="text" placeholder="Enter Email ID" ng-model="email">
                        <br>
                        <br>
                        <input type="text" placeholder="Enter Name" ng-model="name">
                        <br>
                        <br>
                        <input type="text" placeholder="Enter phone number" ng-model = "phno">
                        <br>
                        <br>
                        <input type="password" placeholder="Enter password" ng-model="password">
                        <br>
                        <br>
                        <input type="password" placeholder="Confirm password" ng-model="cpassword">
                        <input type="checkbox" checked> I agree to all terms and conditions
                        <br>
                        <br>
                        <button type="button" class="btn btn-success" ng-click="saveInfo()">SIGN UP</button>
                    </form>
                </div>
                <div class="card-footer">
                    <br>
                    <p>Have an account?</p>
                    <a href="http://localhost:8080/news_hunt/" target="_self">Login</a>
                </div>
            </div>
            
        </div>
    </body>
</html>