function loadBanner(catalog, product){
	let catalogPath = "catalog?catalog=" + catalog.catalog_id;
	
	$("#brandName").html(product.brand_name);
	$("#directory").append("<a href='" + catalogPath + "'>" + catalog.catalog_name + "</a>");
	$("#directory").append("<label>/</label>");
	$("#directory").append(product.product_name);
}

function loadProduct(product, productAvailability){
	var price = "P" + product.price + ".00";
	
	$("#productName").html(product.product_name);
	$("#productBrandName").html(product.brand_name);
	$("#productPrice").html(price);
	$("#productDescription").html(product.product_description);
}

function loadProductInfo(data){
	var catalog = data.catalog;
	var product = data.product;
	var productAvailability = data.productAvailability;
	
	loadBanner(catalog, product);
	loadProduct(product, productAvailability);
}

function getProductInfo(){
	$.ajax({
		url: contextPath + "/product/get_product_info",
		type: "get",
		data: {
			product_id: product_id
		},
		cache: false,
		success: function(data){
			loadProductInfo(data);
		}
	})
}