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
			});
		</script>
		
	</head>
	
	<body>
	
		<!--header-->
		<c:choose>
			<c:when test="${account eq null}">
				<%@include file="../html/navigationBar.html"%>
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
					<!--categories-->
					<div class=" rsidebar span_1_of_left">
						<h4 class="cate">Categories</h4>
						
						<ul class="menu-drop">
							<li class="item1"><a href="#">Men </a>
								<ul class="cute">
									<li class="subitem1"><a href="product.html">Cute Kittens </a></li>
									<li class="subitem2"><a href="product.html">Strange Stuff </a></li>
									<li class="subitem3"><a href="product.html">Automatic Fails </a></li>
								</ul>
							</li>
							
							<li class="item2"><a href="#">Women </a>
								<ul class="cute">
									<li class="subitem1"><a href="product.html">Cute Kittens </a></li>
									<li class="subitem2"><a href="product.html">Strange Stuff </a></li>
									<li class="subitem3"><a href="product.html">Automatic Fails </a></li>
								</ul>
							</li>
							
							<li class="item3"><a href="#">Kids</a>
								<ul class="cute">
									<li class="subitem1"><a href="product.html">Cute Kittens </a></li>
									<li class="subitem2"><a href="product.html">Strange Stuff </a></li>
									<li class="subitem3"><a href="product.html">Automatic Fails</a></li>
								</ul>
							</li>
							
							<li class="item4"><a href="#">Accessories</a>
								<ul class="cute">
									<li class="subitem1"><a href="product.html">Cute Kittens </a></li>
									<li class="subitem2"><a href="product.html">Strange Stuff </a></li>
									<li class="subitem3"><a href="product.html">Automatic Fails</a></li>
								</ul>
							</li>
									
							<li class="item4"><a href="#">Shoes</a>
								<ul class="cute">
									<li class="subitem1"><a href="product.html">Cute Kittens </a></li>
									<li class="subitem2"><a href="product.html">Strange Stuff </a></li>
									<li class="subitem3"><a href="product.html">Automatic Fails </a></li>
								</ul>
							</li>
						</ul>
					</div>
					
					<!--initiate accordion-->
					<script type="text/javascript">
						$(function() {
						    var menu_ul = $('.menu-drop > li > ul'),
						           menu_a  = $('.menu-drop > li > a');
						    menu_ul.hide();
						    menu_a.click(function(e) {
						        e.preventDefault();
						        if(!$(this).hasClass('active')) {
						            menu_a.removeClass('active');
						            menu_ul.filter(':visible').slideUp('normal');
						            $(this).addClass('active').next().stop(true,true).slideDown('normal');
						        } else {
						            $(this).removeClass('active');
						            $(this).next().stop(true,true).slideUp('normal');
						        }
						    });
						});
					</script>
					
					<!--//menu-->
 					<section  class="sky-form">
						<h4 class="cate">Discounts</h4>
						
						 <div class="row row1 scroll-pane">
							 <div class="col col-4">
									<label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>Upto - 10% (20)</label>
							 </div>
							 
							 <div class="col col-4">
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>40% - 50% (5)</label>
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>30% - 20% (7)</label>
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>10% - 5% (2)</label>
									<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Other(50)</label>
							 </div>
						 </div>
				 	</section> 				 				 
				 
					<!---->
					<section  class="sky-form">
						<h4 class="cate">Type</h4>
						
						<div class="row row1 scroll-pane">
							<div class="col col-4">
								<label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>Sofa Cum Beds (30)</label>
							</div>
							
							<div class="col col-4">
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Bags  (30)</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Caps & Hats (30)</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Jackets & Coats   (30)</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Jeans  (30)</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Shirts   (30)</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Sunglasses  (30)</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Swimwear  (30)</label>
							</div>
						</div>
				    </section>
				    
				   	<section  class="sky-form">
						<h4 class="cate">Brand</h4>
						
						<div class="row row1 scroll-pane">
							<div class="col col-4">
								<label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>Roadstar</label>
							</div>
							
							<div class="col col-4">
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Levis</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Persol</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Nike</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Edwin</label>
								<label class="checkbox"><input type="checkbox" name="checkbox" ><i></i>New Balance</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Paul Smith</label>
								<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Ray-Ban</label>
							</div>
						</div>
				   	</section>	
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