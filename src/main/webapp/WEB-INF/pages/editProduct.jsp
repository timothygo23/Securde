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
		<!---//End-rate---->
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
				<h1>Register</h1>
				<em></em>
				<h2><a href="index.html">Home</a><label>/</label>Register</h2>
			</div>
		</div>
		
		<!--login-->
		<div class="container">
			<div class="login">
			
				<form action="${pageContext.request.contextPath}/edit_get_product" method="get">
					<div class="col-md-6 login-do">
						<div class="login-mail">
							<select name="product_id" required>
								<c:forEach items="${products}" var="product">
									<option value="${product.product_id}"><c:out value="${product.product_name}" /></option>
								</c:forEach>
							</select>
						</div>
						<label class="hvr-skew-backward">
							<input type="submit" value="Select Product">
						</label>
					</div>
				</form>
				
				<form action="${pageContext.request.contextPath}/update_product" method="post">
					<div class="col-md-6 login-do">
					
						<input type="hidden" name="product_id" value="${product.product_id}"/>
						
						<div class="login-mail">
							<input type="text" name="product_name" placeholder="Product Name" value="${product.product_name}" required="">
						</div>
						
						<div class="login-mail">
							<input type="text" name="product_description" placeholder="Product Description" value="${product.product_description}" required="">
						</div>
						
						<div class="login-mail">
							<input type="text" name="catalog_id" placeholder="Catalog ID" value="${product.catalog_id}" required="">
						</div>
						
						<div class="login-mail">
							<input type="text" name="price" placeholder="Price" value="${product.price}" required="">
						</div>
						
						<div class="login-mail">
							<input type="text" name="brand_name" placeholder="Brand Name"value="${product.brand_name}"  required="">
						</div>
						
						<label class="hvr-skew-backward">
							<input type="submit" value="Submit">
						</label>
					</div>
				
					<div class="col-md-6 login-right">
						 <h3>Edit Product</h3>
						 
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
	 
	</body>
	
</html>