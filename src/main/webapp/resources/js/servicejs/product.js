var productAvailability;

function loadBanner(catalog, product){
	let catalogPath = "catalog?catalog=" + catalog.catalog_id;
	
	$("#brandName").html(product.brand_name);
	$("#directory").append("<a href='" + catalogPath + "'>" + catalog.catalog_name + "</a>");
	$("#directory").append("<label>/</label>");
	$("#directory").append(product.product_name);
}

function loadProduct(product){
	var price = "P" + product.price + ".00";
	
	$("#productName").html(product.product_name);
	$("#productBrandName").html(product.brand_name);
	$("#productPrice").html(price);
	$("#productDescription").html(product.product_description);
	
}

function getQuantity(size){
	for(var i = 0; i < productAvailability.length; i++){
		let pAvail = productAvailability[i];
		if(pAvail.size == size)
			return pAvail.quantity;
	}
	// returns 0 if there's no available size
	return 0;
}

function loadProductAvailability(){
	var size = $('#sizeSelect').find(':selected').val();
	var quantity = getQuantity(size);
	
	if(quantity <= 0){
		//out of stock
		//hide quantityCartContainer and availability
		$("#quantityCartContainer").hide();
		$("#availability").hide();
		//show out of stock div
		$("#outOfStock").show();
	}else{
		//in stock
		$("#available").html(quantity+"");

		//hide out of stock div
		$("#outOfStock").hide();
		//show quantityCartContainer and availability
		$("#quantityCartContainer").show();
		$("#availability").show();
	}
}

function loadProductInfo(data){
	var catalog = data.catalog;
	var product = data.product;
	
	productAvailability = data.productAvailability;
	
	loadBanner(catalog, product);
	loadProduct(product);
	loadProductAvailability();
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