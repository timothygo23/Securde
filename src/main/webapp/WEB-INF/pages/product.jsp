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
	
		<title>Shopin A Ecommerce Category Flat Bootstrap Responsive Website Template | Single :: w3layouts</title>
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
		<link href="${pageContext.request.contextPath}/resources/css/form.css" rel="stylesheet" type="text/css" media="all" />
		
		<!-- GLOBAL SCRIPTS -->
		<script src="${pageContext.request.contextPath}/resources/js/servicejs/global.js"></script>
		
		<!-- SETTING CONTEXT PATH -->
		<script>var contextPath = "${pageContext.request.contextPath}"</script>
		
		<!-- PRODUCT LIST SCRIPT -->
		<script>
			var product_id = "${param.product}";
		</script>
		<script src="${pageContext.request.contextPath}/resources/js/servicejs/product.js"></script>
		
		<script>
			$(document).ready(function(){
				getProductInfo();
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
				<h1 id="brandName">Product</h1>
				<em></em>
				<h2 id="directory"><a href="index">Home</a><label>/</label></h2>
			</div>
		</div>
		
		<div class="single">
			<div class="container">
				<div class="col-md-12">
					<div class="col-md-5 grid">		
						<div class="flexslider">
						  	<ul class="slides">
							    <li data-thumb="${pageContext.request.contextPath}/resources/images/si.jpg">
							        <div class="thumb-image"> <img src="${pageContext.request.contextPath}/resources/images/si.jpg" data-imagezoom="true" class="img-responsive"> </div>
							    </li>
						    
							    <!-- <li data-thumb="${pageContext.request.contextPath}/resources/images/si1.jpg">
							         <div class="thumb-image"> <img src="${pageContext.request.contextPath}/resources/images/si1.jpg" data-imagezoom="true" class="img-responsive"> </div>
							    </li>
						    
							    <li data-thumb="${pageContext.request.contextPath}/resources/images/si2.jpg">
							       <div class="thumb-image"> <img src="${pageContext.request.contextPath}/resources/images/si2.jpg" data-imagezoom="true" class="img-responsive"> </div>
							    </li> --> 
						  	</ul>
						</div>
					</div>	
					
					<div class="col-md-7 single-top-in">
						<div class="span_2_of_a1 simpleCart_shelfItem">
							<h3 id="productName">Nam liber tempor cum</h3>
							
							<p id="productBrandName" class="in-para"> There are many variations of passages of Lorem Ipsum.</p>
							    
						    <div class="price_single">
								<span id="productPrice" class="reducedfrom item_price">$140.00</span>
								<div class="clearfix"></div>
							</div>
							
							<h4 class="quick">Quick Overview:</h4>
							
							<p id="productDescription" class="quick_desc"> Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; es</p>
							
							<c:if test="${empty account || account.account_type == 3}">
								<div>
									<span> Sizes: </span>
									<select id="sizeSelect" onchange="loadProductAvailability()">
										<option value="S">Small</option>
										<option value="M">Medium</option>
										<option value="L">Large</option>
									</select>
								</div>
								
								<div id="availability" style="margin-top: 10px; color:#FF4646">
									<span>Only </span>
									<span id="available">0</span>
									<span>items in stock</span>
								</div>
								
								<div id="outOfStock" style="display: none; margin-top: 20px; color: #FF4646; font-size: 25px">
									<span>Item is currently out of stock</span>
								</div>
								
								<div id="quantityCartContainer">
									<div class="quantity"> 
										<div class="quantity-select">                           
											<div class="entry value-minus">&nbsp;</div>
											<div class="entry value" id="prodQTY"><span>1</span></div>
											<div class="entry value-plus active">&nbsp;</div>
										</div>
									</div>
									
									<!--quantity-->
									<script>
										$('.value-plus').on('click', function(){
											let availableQuantity = $("#available").html();
											if(parseInt(availableQuantity) > 0){}
											var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10)+1;
											
											if(newVal <= parseInt(availableQuantity))
												divUpd.text(newVal);
										});
											
										$('.value-minus').on('click', function(){
											var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10)-1;
											if(newVal>=1) {
												divUpd.text(newVal);
											}
										});
									</script>
									<!--quantity-->
										 
									<a href="#" class="add-to item_add hvr-skew-backward" disabled>Add to cart</a>
									
								</div>
							</c:if>
							<div class="clearfix"> </div>
						</div>
					</div>
					
					<div class="clearfix"> </div>
					<!---->
					
					<!-- <div class="tab-head">
					 	<nav class="nav-sidebar">
							<ul class="nav tabs">
					          <li class="active"><a href="#tab1" data-toggle="tab">Product Description</a></li>
					          <li class=""><a href="#tab2" data-toggle="tab">Reviews</a></li>  
							</ul>
						</nav>
					
					<div class="tab-content one">
						<div class="tab-pane active text-style" id="tab1">
				 			<div class="facts">
								<p > There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined </p>
								<ul>
									<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Research</li>
									<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Design and Development</li>
									<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Porting and Optimization</li>
									<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>System integration</li>
									<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Verification, Validation and Testing</li>
									<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Maintenance and Support</li>
								</ul>         
					        </div>
						</div>
				
						<div class="tab-pane text-style" id="tab2">		
							<div class="facts">
								<p > There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined </p>
								<ul>
									<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Research</li>
									<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Design and Development</li>
									<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Porting and Optimization</li>
									<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>System integration</li>
									<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Verification, Validation and Testing</li>
									<li><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>Maintenance and Support</li>
								</ul>     
							</div>	
				 		</div>
				  	</div>
				  
				  <div class="clearfix"></div>
				  
				  </div> -->
				  <!---->	
				</div>
			</div>
		</div>
		
		<div style="margin-top: 85px;">
		</div>
		<!----->		
		<!--//content-->
		
		<!--footer-->
		<%@include file="../html/footer.html" %>
		
		<script src="${pageContext.request.contextPath}/resources/js/imagezoom.js"></script>
	
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script defer src="${pageContext.request.contextPath}/resources/js/jquery.flexslider.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/flexslider.css" type="text/css" media="screen" />
	
		<script>
			// Can also be used with $(document).ready()
			$(window).load(function() {
			  $('.flexslider').flexslider({
			    animation: "slide",
			    controlNav: "thumbnails"
			  });
			});
		</script>
		
		<script src="${pageContext.request.contextPath}/resources/js/simpleCart.min.js"> </script>
		<!-- slide -->
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	
	</body>
	
</html>