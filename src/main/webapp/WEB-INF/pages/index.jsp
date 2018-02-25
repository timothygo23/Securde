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
	</head>
	
	<body>
	
		<!--header-->
		<%@include file="../html/navigationBar.html" %>
	
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
					<div class="col-md-6 col-md">
						<div class="col-1">
							<a href="product" class="b-link-stroke b-animate-go  thickbox">
		 	  					<img src="${pageContext.request.contextPath}/resources/images/pi.jpg" class="img-responsive" alt=""/>
		 	  					<div class="b-wrapper1 long-img"><p class="b-animate b-from-right    b-delay03 ">Lorem ipsum</p><label class="b-animate b-from-right    b-delay03 "></label><h3 class="b-animate b-from-left    b-delay03 ">Trendy</h3></div>
							</a>
						</div>
					</div>
					
					<div class="col-md-6 col-md1">
						<div class="col-3">
							<a href="product"><img src="${pageContext.request.contextPath}/resources/images/pi1.jpg" class="img-responsive" alt="">
								<div class="col-pic">
									<p>Lorem Ipsum</p>
									<label></label>
									<h5>For Men</h5>
								</div>
							</a>
						</div>
						
						<div class="col-3">
							<a href="product"><img src="${pageContext.request.contextPath}/resources/images/pi2.jpg" class="img-responsive" alt="">
								<div class="col-pic">
									<p>Lorem Ipsum</p>
									<label></label>
									<h5>For Kids</h5>
								</div>
							</a>
						</div>
						
						<div class="col-3">
							<a href="product"><img src="${pageContext.request.contextPath}/resources/images/pi3.jpg" class="img-responsive" alt="">
								<div class="col-pic">
									<p>Lorem Ipsum</p>
									<label></label>
									<h5>For Women</h5>
								</div>
							</a>
						</div>
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