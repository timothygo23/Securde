$(document).ready(function() {
	
	$("a.add-to").click(function() {
		let temp = window.location.href.split("=")
		let productId = temp[1][0]; //0 index to make sure the ID would always be the value.
		
		addToCart(productId);
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