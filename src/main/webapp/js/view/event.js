var j = 0;

function milisecondsToHour(miliseconds){
	var date = new Date(miliseconds);
	var dateToStr = date.toString().split(' ');
	return dateToStr[4];
}

function milisecondsToDate(miliseconds){
	var date = new Date(miliseconds);
	var dateToStr = date.toString().split(' ');
	return dateToStr[1] + '/' + dateToStr[2] + '/' + dateToStr[3];
}

function fillModalWindow(id){
	console.log("Inside fill" + id);
	$.getScript('js/dao/event.js', function() {
		console.log("Id: " + id);
		getById(id,function(event){
			$(".modal-detalle").empty();
			$(".modal-detalle").append(
				'<div class="row">\
					<img class="icon myImage2 img-circle" src="'+ event.foto + '"/>\
				</div>\
				<div class="row">\
					<div class="col-xs-12">\
						<h2>\
							<strong> ' + event.name + '</strong>\
						</h2>\
					</div>\
				</div>\
				<div class="row">\
					<div class="col-xs-12">\
						<h4>' + event.categoria + '</h4>\
					</div>\
				</div>\
				<div class="row">\
					<div class="col-xs-12">\
						<h4>' + event.place + '</h4>\
					</div>\
				</div>\
				<div class="row">\
					<div class="col-xs-12">\
						<p>' + event.description + '</p>\
					</div>\
				</div>'
			);
		});
	});	
}

function addEventJs(){

	var name= $("#edName").val();
	var descripcion= $("#edDesc").val();
	var ciudad= $("#edCity").val();
	var local= $("#edLoc").val();
	var fecha= $("#edFech").val();
	var categoria= $("#edCat").val();
	var foto= $("#edURL").val();
	var loc=ciudad+", "+local;
	console.log(foto);
	var event={
		"name":name,
		"descripcion":descripcion,
		"place":loc,
		"date":fecha,
		"foto":foto,
		"categoria":categoria
	};
	$.getScript('js/dao/event.js', function() {
		addEvent(event);
	});
	
	alert("Evento creado satisfactoriamente");
	window.location.href = "index.html";
	


}

function checkTime(event){
	var date = new Date().getTime();
	if(date<=event.timestamp){
		return true;	
	}
	else{
		return false;
	}
}

function appendEvent(event, id){
	if(checkTime(event)){
		$(id).append(
			   	'<div class="col-xs-12 col-sm-6 col-md-3 col-lg-2 feature-grid">\
			   	 <div class="caja-evento">\
				 <a >\
        			<img class="icon myImage" src="'+event.foto + '" title="Camera Automation" />\
					 <div class="arrival-info">\
						 <h4>' + event.name + '</h4>\
						 <p>' + event.place + '</p>\
						 <p>' + event.categoria + '</p>\
						 <span class="pric1">Fecha: ' + milisecondsToDate(event.timestamp) + '</span>\
						 <span class="disc">' + milisecondsToHour(event.timestamp) + '</span>\
					 </div>\
					 <div class="viw">\
						<a onclick="fillModalWindow('+event.id+')" href="#" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> Ver más</a>\
					 </div>\
				  </a>\
				  </div>\
				  <button class="btn btn-success btn-apuntate" href="#" disabled>Apuntate</button>\
			 	</div>'
			   	);
	}	
}


function search(){
	$('#searchResults').css("display","block");	
	$('.my-slider').css("display","none");
	$('#all-events').css("display","none");
	var busqueda = ($(".text").val());
	$(".searchResultsItem").empty();
	$.getScript('js/dao/event.js', function() {
		searchByName(busqueda,function(events) {
			if(events.length==0){
				console.log("NO EVENTS RESULTS");
			}

			$.each(events, function(key, event) {
			    appendEvent(event, ".searchResultsItem");
			});
		});
	});
};

function initEvents(){
	$.getScript('js/dao/event.js', function() {
		listEvents(function(events) {
			var i = 0;
			$.each(events, function(key, event) {;				
				if ( i < 20){
					appendEvent(event, "#events-list");					
					i++;
				}				
			});
			
			$.each(events, function(key, event) {
			if(j<2){
				if(checkTime(event)){
				 $(".slide" + j).html(
			   		'<h3>' + event.name + '</h3>'
			   		 + '<div class="sliderDate">' + milisecondsToDate(event.timestamp) + ' ' + milisecondsToHour(event.timestamp) + '</div>'
			   		 + '<div class="sliderPlace">' + event.place + '</div>'
			   		 + '<div class="sliderDescription">' + event.description + '</div>'
			   	);
			   	j++;
				}		
			}	  
			});
		});	
	});
}