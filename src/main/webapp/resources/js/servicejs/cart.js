$(document).ready(function() {
	
	$("a.add-to").click(function() {
		let temp = window.location.href.split("=")
		let productId = temp[1][0]; //0 index to make sure the ID would always be the value.
		
		addToCart(productId);
	});
	
	//Delete whole contents of the cart.
	$("a#emptyTheCart").click(function() {
		deleteCartProducts();
	});
})

function addToCart(productId) {
	$.ajax({
		url: contextPath + "/saveProductToCart",
		type: "post",
		data: {
			productId: productId,
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
			console.log(data);
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
			console.log(data);
		}
	})
}