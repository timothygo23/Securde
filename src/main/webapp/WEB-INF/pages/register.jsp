<!--A Design by W3layouts 
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
	
		<title>Shopin A Ecommerce Category Flat Bootstrap Responsive Website Template | Register :: w3layouts</title>
		<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
	
		<!-- Custom Theme files -->
		<!--theme-style-->
		<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" media="all" />	
		<!--//theme-style-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="Shopin Responsive web template, Bootstrap Web Templates, Flat Web Templates, AndroId Compatible web template, 
		Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		<!--theme-style-->
		<link href="${pageContext.request.contextPath}/resources/css/style4.css" rel="stylesheet" type="text/css" media="all" />	
		<!--//theme-style-->
		<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	
		<!--- start-rate---->
		<script src="${pageContext.request.contextPath}/resources/js/jstarbox.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jstarbox.css" type="text/css" media="screen" charset="utf-8" />
		<script type="text/javascript">
			jQuery(function() {
				jQuery('.starbox').each(function() {
					var starbox = jQuery(this);
					starbox.starbox({
						average: starbox.attr('data-start-value'),
						changeable: starbox.hasClass('unchangeable') ? false : starbox.hasClass('clickonce') ? 'once' : true,
						ghosting: starbox.hasClass('ghosting'),
						autoUpdateAverage: starbox.hasClass('autoupdate'),
						buttons: starbox.hasClass('smooth') ? false : starbox.attr('data-button-count') || 5,
						stars: starbox.attr('data-star-count') || 5
					}).bind('starbox-value-changed', function(event, value) {
						if(starbox.hasClass('random')) {
							var val = Math.random();
							starbox.next().text(' '+val);
							return val;
						} 
					})
				});
			});
		</script>
		
		<c:if test="${error ne null}">
			<script>
				$(document).ready(function(){
					alert("${error}");
				});
			</script>
		</c:if>
		
		<script>
			function validateNum(event){
				var key = String.fromCharCode(event.keyCode);
				var regex = /[0-9]/;
				if( !regex.test(key) ) {
					event.returnValue = false;
				}
			}
			
			function submitForm(){
				var inputs = $("#registerForm :input");
				var values = {};
				var emptyFields = false;
				inputs.each(function(){
					values[this.name] = $(this).val();
					if(values[this.name].trim() == ""){
						emptyFields = true;
					}
				})
				
				if(emptyFields == true){
					alert("Empty fields.");
					return false;
				}
				
				if(values["phoneNum"].length > 11){
					alert("Invalid phone number.");
					return false;
				}
				
				if(values["password"] != values["cpassword"]){
					alert("Passwords does not match.");
					return false;
				}
				
				return true;
			}
		</script>
		<!---//End-rate---->
	</head>
	
	<body>
	
		<!--header-->
		<%@include file="../html/navigationBar.html" %>
	
		<!--banner-->
		<div class="banner-top">
			<div class="container">
				<h1>Register</h1>
				<em></em>
				<h2><a href="index.html">Home</a><label>/</label>Register</h2>
			</div>
		</div>
		
		<!--login-->
		<div class="container">
			<div class="login">
				<form id="registerForm" action="${pageContext.request.contextPath}/registerAccount" onsubmit="return submitForm()" method="post">
					<div class="col-md-6 login-do">
						<div class="login-mail">
							<input type="text" name="fName" placeholder="Firstname" required="" pattern="[A-Za-z\s]{1,50}" oninvalid="NameInvalidMsg(this)">
							<i  class="glyphicon glyphicon-user"></i>
						</div>
						
						<div class="login-mail">
							<input type="text" name="lName" placeholder="Lastname" required="" pattern="[A-Za-z\s]{1,50}" oninvalid="NameInvalidMsg(this)">
							<i  class="glyphicon glyphicon-user"></i>
						</div>
						
						<div class="login-mail">
							<input type="text" name="phoneNum" onkeypress="validateNum(event)" placeholder="Phone Number" required="" pattern="[0-9]+" oninvalid="SpecialCharInvalidMsg(this)">
							<i  class="glyphicon glyphicon-phone"></i>
						</div>
						
						<div class="login-mail">
							<input type="email" name="email" placeholder="Email" required="">
							<i  class="glyphicon glyphicon-envelope"></i>
						</div>
						
						<div class="login-mail">
							<input type="password" name="password" placeholder="Password" pattern="^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{9,}" title="Min 9 char. 1 Uppercase, 1 Number, and 1 Special Character." required>
							<i class="glyphicon glyphicon-lock"></i>
						</div>
						
						<div class="login-mail">
							<input type="password" name="cpassword" placeholder="Confirm password" pattern="^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{9,}" title="Min 9 char. 1 Uppercase, 1 Number, and 1 Special Character." required>
							<i class="glyphicon glyphicon-lock"></i>
						</div>
						
						<div class="login-mail">
							<select name="question" form="registerForm">
								<option value="What was the name of your elementary / primary school?">What was the name of your elementary / primary school?</option>
								<option value="What is the last name of the teacher who gave you your first failing grade?">What is the last name of the teacher who gave you your first failing grade?</option>
								<option value="In what city or town does your nearest sibling live?">In what city or town does your nearest sibling live?</option>
							</select>
							<i  class="glyphicon glyphicon-lock"></i>
						</div>
						
						<div class="login-mail">
							<input type="text" name="answer" placeholder="Answer" required="" pattern="[a-zA-Z0-9\s]+" oninvalid="SpecialCharInvalidMsg(this)">
							<i  class="glyphicon glyphicon-lock"></i>
						</div>
						
						<label class="hvr-skew-backward">
							<input type="submit" value="Submit">
						</label>
					</div>
				
					<div class="col-md-6 login-right">
						 <h3>Completely Free Account</h3>
						 
						 <p>Pellentesque neque leo, dictum sit amet accumsan non, dignissim ac mauris. Mauris rhoncus, lectus tincidunt tempus aliquam, odio 
						 libero tincidunt metus, sed euismod elit enim ut mi. Nulla porttitor et dolor sed condimentum. Praesent porttitor lorem dui, in pulvinar enim rhoncus vitae. Curabitur tincidunt, turpis ac lobortis hendrerit, ex elit vestibulum est, at faucibus erat ligula non neque.</p>
						 
						 <a href="login.html" class="hvr-skew-backward">Login</a>
					</div>
				
					<div class="clearfix"> </div>
				</form>
			</div>
		</div>
	
		<!--//login-->
	
		<!--brand-->
		<%@include file="../html/brands.html" %>
		
		<!--//content-->
		
		<!--//footer-->
		<%@include file="../html/footer.html" %>
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="${pageContext.request.contextPath}/resources/js/simpleCart.min.js"> </script>
		<!-- slide -->
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
		
		<script>
			function NameInvalidMsg(textbox) {
	
			    if(textbox.validity.patternMismatch){
			       textbox.setCustomValidity('Numbers and special characters are not allowed.');
			   }    
			   else {
			       textbox.setCustomValidity('');
			   }
			   return true;
			}
	
			function SpecialCharInvalidMsg(textbox) {
	
			    if(textbox.validity.patternMismatch){
			       textbox.setCustomValidity('Special characters are not allowed.');
			   }    
			   else {
			       textbox.setCustomValidity('');
			   }
			   return true;
			}
		</script>
	 
	</body>
	
</html>