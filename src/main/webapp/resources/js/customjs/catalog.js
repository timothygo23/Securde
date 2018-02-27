/*
 * Script used for catalogs
 */
let left = true;

function createCatalogDiv(catalog){
	let catalogDiv = $("<div/>");
	catalogDiv.addClass("col-3");
	
	let link = $("<a href='catalog'></a>");
	
	let image = $("<img src='" + contextPath + "/resources/images/pi1.jpg' alt=''/>");
	image.addClass("img-responsive");
	
	let tagDiv = $("<div/>");
	tagDiv.addClass("col-pic");
	
	tagDiv.append("<p>" + encode(catalog.catalog_name) + "</p>");
	tagDiv.append("<label></label>");
	tagDiv.append("<h5>" + encode(catalog.target_gender) + "</h5>");
	
	catalogDiv.append(link);
	link.append(image);
	link.append(tagDiv);
	
	if(left == true){
		$('#leftCatalogs').append(catalogDiv);
	}else{
		$('#rightCatalogs').append(catalogDiv);
	}

	left = !left;
}

function loadCatalogs(catalogs){
	for(var i = 0; i < catalogs.length; i++){
		let catalog = catalogs[i];
		
		createCatalogDiv(catalog);
	}
}

function getCatalogs(){
	console.log("GET CATALOGS")
	$.ajax({
		url: contextPath + "/catalog/get_catalogs",
		type: "get",
		cache: false,
		success: function(data){
			loadCatalogs(data);
		}
	})
}