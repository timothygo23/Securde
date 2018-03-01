/*
 * Script used for products
 */
function createProductThumbnail(product){
	let name = product.product_name;
	let brand = product.brand_name;
	let price = "P" + product.price + ".00";
	let productPath = "product?product=" + product.product_id;
	let addToCartPath = "";
	
	let productDiv = $("<div></div>");
	productDiv.addClass("col-md-4 item-grid1 simpleCart_shelfItem");
	
	let containerDiv = $("<div></div>");
	containerDiv.addClass("mid-pop");
	
	/* image div */
	let imageDiv = $("<div></div>");
	imageDiv.addClass("pro-img");
	let image = $("<img src='" + contextPath + "/resources/images/pc.jpg' alt=''></img>");
	image.addClass("img-responsive");
	let hoverDiv = $("<div></div>");
	hoverDiv.addClass("zoom-icon");
	let leftIcon = $("<a class='picture' href='" + contextPath + "/resources/images/pc.jpg' rel='title' class='b-link-stripe b-animate-go  thickbox'><i class='glyphicon glyphicon-search icon '></i></a>");
	let rightIcon = $("<a href='" + productPath + "'><i class='glyphicon glyphicon-menu-right icon'></i></a>");
	
	hoverDiv.append(leftIcon);
	hoverDiv.append(rightIcon);
	imageDiv.append(image);
	imageDiv.append(hoverDiv);
	
	/* lower div with the details and stuff */
	//main container
	let lowerDiv = $("<div></div>");
	lowerDiv.addClass("mid-1");
	
	/* cart icon + name & brand container */
	let brandNameCartContainer = $("<div></div>");
	brandNameCartContainer.addClass("women");
	
	let nameContainer = $("<div></div>");
	nameContainer.addClass("women-top");
	let span = $("<span>" + brand + "</span>");
	let link = $("<h6><a href='" + productPath + "'>" + name + "</a></h6>");
	
	//appending to nameContainer
	nameContainer.append(span);
	nameContainer.append(link);
	
	let cartIconContainer = $("<div></div>");
	cartIconContainer.addClass("img item_add");
	cartIconContainer.append("<a href='" + addToCartPath + "'><img src='" + contextPath + "/resources/images/ca.png' alt=''></a>")
	
	//append to brandNameCarContainer
	brandNameCartContainer.append(nameContainer);
	brandNameCartContainer.append(cartIconContainer);
	brandNameCartContainer.append("<div class='clearfix'></div>");
	
	/* price and rating container */
	let priceRatingContainer = $("<div></div>");
	priceRatingContainer.addClass("mid-2");
	
	let priceContainer = $("<p></p>");
	priceContainer.append("<em class='item_price'>" + price + "</em>");
	
	let ratingContainer = $("<div></div>");
	ratingContainer.addClass("block");
	ratingContainer.append("<div class='starbox small ghosting'> </div>");
	
	//appending to priceratingContainer
	priceRatingContainer.append(priceContainer);
	priceRatingContainer.append(ratingContainer);
	priceRatingContainer.append("<div class='clearfix'></div>");
	
	//appending to lower div
	lowerDiv.append(brandNameCartContainer);
	lowerDiv.append(priceRatingContainer);
	
	//appending main components
	containerDiv.append(imageDiv);
	containerDiv.append(lowerDiv);
	productDiv.append(containerDiv);
	
	$("#productList").append(productDiv);
}

/*
 * used to create list of products thumbnails
 */
function loadProducts(products){
	$("#productList").empty();
	for(var i = 0; i < products.length; i++){
		let p = products[i];
		createProductThumbnail(p);
	}
}

/*
 * used to set the catalog name and the directory name
 */
function setCatalogName(catalogName, directoryName){
	$("#catalogName").html(catalogName);
	$("#directoryContainer").append(directoryName);
}

/*
 * used to get the list of products to show inside a catalog
 */
function getProductList(){
	$.ajax({
		url: contextPath + "/catalog/get_products_catalogs",
		type: "get",
		data: {
			catalog: catalog_id
		},
		cache: false,
		success: function(data){
			loadProducts(data.products);
			setCatalogName(data.catalogName, data.catalogName);
		}
	})
}

/*
 * used to get the list of products search by the user
 */
function getSearchedProductList(){
	$.ajax({
		url: contextPath + "/search/get_searched_products",
		type: "get",
		data: {
			search_key: search_key,
		},
		cache: false,
		success: function(data){
			loadProducts(data);
			setCatalogName("\"" + search_key +"\"", "Search Results");
		}
	})
}

/*
 * used to get the filtered list of products
 */
function getFilteredProductList(){
	//not an arrays because i dont know how to receive arrays from ajax
	var brands = "";
	var sizes = "";
	var prices = "";
	
	var url;
	var value; //value is either the catalog_id or search_key
	
	//get filtered options
	$("input[type=checkbox]").each(function(){
		//checks all the input check boxes
		if($(this).is(':checked')){
			//brands
			if($(this).attr('name') == "brand"){
				brands += $(this).val() + "/";
			}else if($(this).attr('name') == "size"){
				sizes += $(this).val() + "/";
			}else if($(this).attr('name') == "priceRange"){
				prices += $(this).val();
			}
		}
	});
	
	if(typeof catalog_id != "undefined"){
		//its a catalog
		url = contextPath + "/catalog/get_products_catalogs";
		value = catalog_id;
	}else{
		//its a search
		url = contextPath + "/search/get_searched_products";
		value = search_key;
	}
	
	$.ajax({
		url: url,
		type: "get",
		data: {
			filter: "true",
			value: value,
			brands: brands,
			sizes: sizes,
			priceRange: prices
		},
		cache: false,
		success: function(data){
			if(typeof catalog_id != "undefined"){
				loadProducts(data.products);
				console.log("filter through catalog");
			}else{
				loadProducts(data);
				console.log("filter through search");
			}
		}
	})
}

/*
 * creates a side bar label for the brands list
 */
function createSidebarLabel(brand){
	var label = $("<label></label>");
	label.addClass("checkbox");
	
	var input = $("<input type='checkbox' name='brand' value='" + brand.brand_name + "'>");
	var iTag = $("<i></i>");
	
	label.append(input);
	label.append(iTag);
	label.append(brand.brand_name);
	
	$("#brandList").append(label);
}

/*
 * used to create list of sidebar labels
 */
function loadSidebarBrands(brands){
	for(var i = 0; i < brands.length; i++){
		let brand = brands[i];
		createSidebarLabel(brand);
	}
}

/*
 * used to get the brands show at the side bar
 */
function getSidebarBrands(){
	$.ajax({
		url: contextPath + "/catalog/get_all_brands",
		type: "get",
		cache: false,
		success: function(data){
			loadSidebarBrands(data);
		}
	})
}