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
	
		<title>Shopin A Ecommerce Category Flat Bootstrap Responsive Website Template | Products :: w3layouts</title>
	
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
			if("${request}" == "catalog"){
				var catalog_id = "${param.catalog}";
			}else if("${request}" == "search"){
				var search_key =  "${param.search_key}";
			}
		</script>
		<script src="${pageContext.request.contextPath}/resources/js/servicejs/product_list.js"></script>
		
		<script>
			$(document).ready(function(){
				if("${request}" == "catalog"){
					getProductList();	
				}else if("${request}" == "search"){
					getSearchedProductList();
				}
				getSidebarBrands();
				
				//for the price check boxes so that only 1 is checked at a time
				$(".priceCBox").click(function(){
					var priceCBox = $(this);
					
					if (priceCBox.is(":checked")) {
						var otherPriceCBox = $("input[name='priceRange']");
						otherPriceCBox.prop("checked", false);
						priceCBox.prop("checked", true);
					}else{
						priceCBox.prop("checked", false);
					}
				})
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
				<h1 id="catalogName">Catalog</h1>
				<em></em>
				<h2 id="directoryContainer"><a href="home">Home</a><label>/</label></h2>
			</div>
		</div>
		
		<!--content-->
		<div class="product">
			<div class="container">
			
				<div class="col-md-9">
					<div id="productList" class="mid-popular">
						
					</div>
				</div>
			
				<div class="col-md-3 product-bottom">
					<section  class="sky-form">
						<h4 class="cate">Brands</h4>
						
						<div class="row row1 scroll-pane">
							<div id="brandList" class="col col-4">
								
							</div>
						</div>
				   	</section>
				   	
				   	<!-- SIZES -->
				   	<section  class="sky-form" style="display:none;">
						<h4 class="cate">Sizes</h4>
						
						 <div class="row row1 scroll-pane">
							 <div class="col col-4">
									<label class="checkbox"><input type="checkbox" name="size" value="S"><i></i>Small</label>
									<label class="checkbox"><input type="checkbox" name="size" value="M"><i></i>Medium</label>
									<label class="checkbox"><input type="checkbox" name="size" value="L"><i></i>Large</label>
							 </div>
						 </div>
				 	</section> 		
				   	
					<!-- PRICES -->
 					<section  class="sky-form">
						<h4 class="cate">Price Range</h4>
						
						 <div class="row row1 scroll-pane">
							 <div class="col col-4">
									<label class="checkbox"><input type="checkbox" class="priceCBox" name="priceRange" value="1-999"><i></i>1 - 999</label>
									<label class="checkbox"><input type="checkbox" class="priceCBox" name="priceRange" value="1000-1999"><i></i>1000 - 1999</label>
									<label class="checkbox"><input type="checkbox" class="priceCBox" name="priceRange" value="2000-4999"><i></i>2000 - 4999</label>
									<label class="checkbox"><input type="checkbox" class="priceCBox" name="priceRange" value="5000"><i></i>5000 ~</label>
							 </div>
						 </div>
				 	</section>
				 	
				 	<button class="hvr-skew-backward" style="float: right; border: none;" onclick="getFilteredProductList()">Filter</button> 				 				 
				</div>
				
				<div class="clearfix"></div>
				
			</div>
				<!--products-->
			
			<!--//products-->
		
			<!--brand-->
			<%@include file="../html/brands.html" %>
			
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