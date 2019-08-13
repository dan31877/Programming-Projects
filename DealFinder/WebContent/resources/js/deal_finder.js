

jQuery(document).ready(function($) {
	$('[data-href]').click(function() {
		window.location = $(this).data("href");
	});
});

$(document).ready(function(){
	$(".userMessage").fadeTo(3000, 0);
});

