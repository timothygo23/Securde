
$(document).ready(function() {
	
	$("a.add-to").click(function() {
		let temp = window.location.href.split("=")
		let productId = temp[1][0]; //0 index to make sure the ID would always be the value.
		let size = $("#sizeSelect").find(":selected").text();
		
		let qty = $("#prodQTY").text();
		addToCart(productId, qty, size);
	});
	
	//Delete whole contents of the cart.
	$("a#emptyTheCart").click(function() {
		deleteCartProducts();
	});
	
})

function addToCart(productId, qty, size) {
	$.ajax({
		url: contextPath + "/saveProductToCart",
		type: "post",
		data: {
			productId: productId,
			quantity: qty,
			size: size
		},
		cache: false,
		success: function(data){
			console.log(data);
		}
	})
}

function deleteCartProducts() {
	$.ajax({
		url: contextPath + "/removeAllProductsFromCart",
		type: "post",
		cache: false,
		success: function(data){
			if(data === "DONE") {
				window.location.reload(true); 
			}
		}
	})
}

function deleteCertainProductFromCart(cartItemID) {
	console.log(cartItemID)
	console.log(contextPath)
	$.ajax({
		url: contextPath + "/removeOneProdCart",
		type: "post",
		cache: false,
		data: {
			cartItemID: cartItemID,
		},
		success: function(data){
			if(data === "DONE") {
				window.location.reload(true); 
			}
		}
	})
}