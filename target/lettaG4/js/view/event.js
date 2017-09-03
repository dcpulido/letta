var i = 0;

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

function search(){
	var busqueda = ($(".text").val());
	$(".searchResultsItem").empty();
	$.getScript('js/dao/event.js', function() {
		searchByName(busqueda,function(events) {
			if(events.length==0){
				console.log("NO EVENTS RESULTS");
			}

			$.each(events, function(key, event) {
			   $(".searchResultsItem").append(
			   	'<div class="col-xs-12 col-sm-6 col-md-3 col-lg-2 feature-grid">\
				 <a href="product.html">\
        			<img class="icon" src="images/icons/TV.svg" title="Camera Automation" />\
					 <div class="arrival-info">\
						 <h4>' + event.name + '</h4>\
						 <p>' + event.place + '</p>\
						 <span class="pric1">Fecha: ' + milisecondsToDate(event.timestamp) + '</span>\
						 <span class="disc">' + milisecondsToHour(event.timestamp) + '</span>\
					 </div>\
					 <div class="viw">\
						<a href="product.html"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>Ver más</a>\
					 </div>\
				  </a>\
			 	</div>'
			   	);
			});
		});
	});
};

function initEvents(){
	$.getScript('js/dao/event.js', function() {
		listEvents(function(events) {
			$.each(events, function(key, event) {
			    $('#events-list').append(
			    '<div class="col-xs-12 col-sm-6 col-md-3 col-lg-2 feature-grid">\
				 <a href="product.html">\
        			<img class="icon" src="images/icons/TV.svg" title="Camera Automation" />\
					 <div class="arrival-info">\
						 <h4>' + event.name + '</h4>\
						 <p>' + event.place + '</p>\
						 <span class="pric1">Fecha: ' + milisecondsToDate(event.timestamp) + '</span>\
						 <span class="disc">' + milisecondsToHour(event.timestamp) + '</span>\
					 </div>\
					 <div class="viw">\
						<a href="product.html"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>Ver más</a>\
					 </div>\
				  </a>\
			 	</div>'
			    );
			});
			$.each(events.slice(0,2), function(key, event) {
			   $(".slide" + i).html(
			   		event.name
			   	);
			   i++;
			});
		});	
	});
}