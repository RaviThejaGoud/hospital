$(document).ready(function() {
	// This would handle hiding the default value if any in the text box
	$('.defaultValue').each(function() {
		var default_value = this.value;
		$(this).css('color', '#D2D1D1');
		$(this).focus(function() {
			if (this.value == default_value) {
				this.value = '';
				$(this).css('color', '#000');
			}
		});
		$(this).blur(function() {
			if (this.value == '') {
				$(this).css('color', '#D2D1D1');
				this.value = default_value;
			}
		});
	});

	// This would slide the menu to select if any
	$("ul.tooltipDiv>li").hover(function() {
		$('div').find("ul.tooltipSubDiv").hide();
		$(this).find("ul.tooltipSubDiv").show(); 
		$('div').find(".popover").show();
		$(this).find(".popover").show();
	}, function(){	
		$('div').find(".popover").hide();
		$(this).find(".popover").hide();
		$('div').find("ul.tooltipSubDiv").hide();
		$(this).find("ul.tooltipSubDiv").hide(); 
		
	});
});
