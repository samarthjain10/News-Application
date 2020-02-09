<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.16/angular.min.js"></script>
<script>
	var m = angular.module("newshunt", []);
	m.controller("login", function($scope, $http, $window) {
		$scope.check = 1;
		$scope.login = function() {
			$scope.check = 0;
			data = {
				email : $scope.email,
				password : $scope.password
			};
			$http.post("checkInfo", data).then(function(response) {
				$scope.check = 1;
				if (response.data == 1) {
					$window.location.href = "home";
				} else if (response.data == 2) {
					$window.location.href = "AdminHome";
				} else if (response.data == 0) {
					alert("invalid info");
				}
			});
		}
	});
</script>
<style>
.card {
	background-color: floralwhite !important;
	width: 400px;
	height: 370px;
	margin-top: 100px;
	margin-left: 25%;
	position: absolute;
}

.container {
	align-content: center;
}

.card-header {
	position: relative;
	align-content: center;
	text-align: center;
	color: black;
	font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
	margin-top: 20px;
	font-size: 30px;
}

.card-footer {
	position: relative;
	text-align: center;
	color: ablack;
}

input {
	position: relative;
	height: 37px;
	width: 70%;
	background-color: lightgrey;
	margin-left: 15%;
	border: 2px solid lightslategray;
	border-radius: 3px;
	padding: 10px;
}

button {
	position: relative;
	align-content: center;
	margin-left: 38%;
	width: 25%;
}

button:hover {
	background-color: #45a049;
}
</style>
</head>
<body ng-app="newshunt">
	<div class="container" ng-controller="login">

		<div class="card">
			<div class="card-header">
				<p>
					<strong>Login</strong> to get going !
				</p>
			</div>
			<br> <br>
			<div class="card-body">
				<form>
					<input type="text" placeholder="Enter Email ID" ng-model="email">
					<br> <br> <input type="password"
						placeholder="Enter password" ng-model="password"> <br>
					<br>
					<button type="button" class="btn btn-success" ng-click="login()">LOGIN</button>
				</form>
			</div>
			<div class="card-footer">
				<br>
				<p>Don't have an account?</p>
				<a href="signup" target="_self">Sign up</a>
			</div>
		</div>
		<div ng-if="check==0">
			<img src="${pageContext.request.contextPath}/resources/pc.gif">

		</div>
	</div>
</body>
</html>