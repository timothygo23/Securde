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
	
		<title>Shopin A Ecommerce Category Flat Bootstrap Responsive Website Template | Home :: w3layouts</title>
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
		
		<!-- GLOBAL SCRIPTS -->
		<script src="${pageContext.request.contextPath}/resources/js/servicejs/global.js"></script>
		
		<!-- SETTING CONTEXT PATH -->
		<script>var contextPath = "${pageContext.request.contextPath}"</script>
		
		<!-- CATALOG SCRIPT -->
		<script src="${pageContext.request.contextPath}/resources/js/servicejs/catalog.js"></script>
		
		<script>
			$(document).ready(function(){
				getCatalogs();
			});
		</script>
	</head>
	
	<body>
	
		<!--header-->
		<c:choose>
			<c:when test="${account eq null}">
				<%@include file="../html/navigationBar.html"%>
			</c:when>
			
			<c:otherwise>
				<%@include file="../html/navigationBar_customer.html"%>
			</c:otherwise>
		</c:choose>
		
		<!--banner-->
		<div class="banner">
			<div class="container">
				<section class="rw-wrapper">
					<h1 class="rw-sentence">
						<span>Fashion &amp; Beauty</span>
						
						<div class="rw-words rw-words-1">
							<span>Beautiful Designs</span>
							<span>Sed ut perspiciatis</span>
							<span> Totam rem aperiam</span>
							<span>Nemo enim ipsam</span>
							<span>Temporibus autem</span>
							<span>intelligent systems</span>
						</div>
						
						<div class="rw-words rw-words-2">
							<span>We denounce with right</span>
							<span>But in certain circum</span>
							<span>Sed ut perspiciatis unde</span>
							<span>There are many variation</span>
							<span>The generated Lorem Ipsum</span>
							<span>Excepteur sint occaecat</span>
						</div>
						
					</h1>
				</section>
			</div>
		</div>
		
		<!--content-->
		<div class="content">
			<div class="container">
				<div class="content-top">
					
					<div id="leftCatalogs" class="col-md-6 col-md1">
						
					</div>
					
					<div id="rightCatalogs" class="col-md-6 col-md1">
						
					</div>
					
					<div class="clearfix"></div>
				</div>
				
				<!--brand-->
				<%@include file="../html/brands.html" %>
			</div>	
		</div>
		<!--//content-->
		
		<!--footer-->
		<%@include file="../html/footer.html" %>
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="${pageContext.request.contextPath}/resources/js/simpleCart.min.js"> </script>
		<!-- slide -->
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
		<!--light-box-files -->
		<script src="${pageContext.request.contextPath}/resources/js/jquery.chocolat.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/chocolat.css" type="text/css" media="screen" charset="utf-8">
		<!--light-box-files -->
		<script type="text/javascript" charset="utf-8">
			$(function() {
				$('a.picture').Chocolat();
			});
		</script>
	
	</body>
	
</html>