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
	
		<title>Shopin A Ecommerce Category Flat Bootstrap Responsive Website Template | Login :: w3layouts</title>
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
		<!---//End-rate---->
		
		<script>
			$(document).ready(function(){
				$("#loginLink").click(function(){
					window.location = "${pageContext.request.contextPath}/login";
				});
			})
		</script>
		
		<style>
			#loginLink:hover{
				cursor: pointer;
			}
		</style>
	</head>
	
	<body>
	
		<!--header-->
		<%@include file="../html/navigationBar.html"%>
	
		<!--banner-->
		<div class="banner-top">
			<div class="container">
				<h1>Login</h1>
				<em></em>
				<h2><a href="index">Home</a><label>/</label>Login</h2>
			</div>
		</div>
	
		<!--login-->
		<div class="container">	
			<div id="forgotPasswordContainer" class="login">
				<form action="${pageContext.request.contextPath}/secret_question" method="post">
					<div class="col-md-6 login-do">
						<div style="margin-bottom: 20px;">
							Email: <span style="font-weight: bold;"> <c:out value="${email}"></c:out> </span>,
						</div>
						
						<div style="margin-bottom: 20px;">
							<span style="font-weight: bold;">Secret Q</span>: <c:out value="${question}"></c:out>
						</div>
						
						<div class="login-mail">
							<input type="text" name="answer" placeholder="Answer" required="" pattern="[a-zA-Z0-9\s]+" oninvalid="SpecialCharInvalidMsg(this)">
							<i  class="glyphicon glyphicon-lock"></i>
						</div>
						
						<input type="hidden" name="email" value="${email}">
						
						<div style="margin-top: -20px; margin-bottom: 15px; float: right; font-size: 10pt; color: #0000EE;">
							<span id="loginLink">Login Now?</span>
						</div>
						
						<label class="hvr-skew-backward">
							<input type="submit" value="Submit">
						</label>
					</div>
				
					<div class="clearfix"> </div>
				</form>
			</div>
		</div>
		<!--//login-->
	
		<!--brand-->
		<%@include file="../html/brands.html" %>
			
		<!--//content-->
		
		<!--footer-->
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