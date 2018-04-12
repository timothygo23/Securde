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
	
		<title>Shopin A Ecommerce Category Flat Bootstrap Responsive Website Template | Checkout :: w3layouts</title>
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
			function dateToString(date){
				return date.getDate()+'/'+ (date.getMonth()+1) +'/'+date.getFullYear();
			}
			
			$(document).ready(function(){
				var date = new Date();
				date.setDate(date.getDate() + 4);
				$("#eta").append(dateToString(date));
				$("#eta").append(" - ");
				date.setDate(date.getDate() + 3);
				$("#eta").append(dateToString(date));
			});
		</script>
	</head>
	
	<body>
		
		<!--header-->
		<c:choose>
			<c:when test="${account eq null}">
				<%@include file="../html/navigationBar.html"%>
			</c:when>
			
			<c:when test="${account.account_type eq 1}">
				<%@include file="../html/navigationBar_admin.html"%>
			</c:when>
			
			<c:when test="${account.account_type eq 2}">
				<%@include file="../html/navigationBar_brandManufacturer.html"%>
			</c:when>
			
			<c:otherwise>
				<%@include file="../html/navigationBar_customer.html"%>
			</c:otherwise>
		</c:choose>
		
		<!--banner-->
		<div class="banner-top">
			<div class="container">
				<h1>Order & Payment</h1>
				<em></em>
				<h2><a href="home">Home</a><label>/</label><a href="checkout">Checkout</a><label>/</label>Order & Payment</h2>
			</div>
		</div>
		
		<!--login-->
		<script>
			$(document).ready(function(c) {
				$('.close1').on('click', function(c){
					$('.cart-header').fadeOut('slow', function(c){
						$('.cart-header').remove();
					});
				});	  
			});
		</script>

		<script>
			$(document).ready(function(c) {
				$('.close2').on('click', function(c){
					$('.cart-header1').fadeOut('slow', function(c){
						$('.cart-header1').remove();
					});
				});	  
			});
		</script>
				   
		<script>
			$(document).ready(function(c) {
				$('.close3').on('click', function(c){
					$('.cart-header2').fadeOut('slow', function(c){
						$('.cart-header2').remove();
					});
				});	  
			});
		</script>
		
		<div class="check-out">
			<div class="container">
		   		<h3>ORDER PLACED!</h3>
		   		<p>Your order has been placed. ORDER #: <c:out value="${orderNum}"/></p>
		   		<a href="home" class="hvr-skew-backward">Continue Shopping!</a> 				 				 
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
	 
	</body>
	
</html>