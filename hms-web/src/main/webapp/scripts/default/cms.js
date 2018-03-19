$(document).ready(function() {			
		$("a.addMen").fancybox({ 
			 'width' : '68%',
			 'height' : '75%',
			 'autoScale' : true,
	         'transitionIn' : 'none',
	         'transitionOut' : 'none',
	         'autoDimensions' : true,
	         'showCloseButton' : true,
	         'titleShow' : true,
	         'autoDimensions' : false,	         
	         'titleFormat' : 'formatTitle'
	       });
}); 


$('div.ui-widget-header > ul > li').click(function() {
	$('div.ui-widget-header > ul > li').removeClass('ui-state-active');
	$(this).addClass('ui-state-active');
}, function() {
});
$('div.ui-widget-header > ul > li').hover(function() {
	$(this).addClass('ui-state-hover');
}, function() {
	$(this).removeClass('ui-state-hover');
});