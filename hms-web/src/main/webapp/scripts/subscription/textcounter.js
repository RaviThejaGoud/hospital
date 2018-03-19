$(document).ready(function(){
	/**
	 * Character Counter for inputs and text areas showing characters left.
	 */
	$('.word_count').each(function(){
	    //maximum limit of characters allowed.
		var maxlimit = 120;
		
		//bellow code $(this).attr('style');used to dinamic char from page
		
		/*var limit=$(this).attr('style');
		 if(isNonEmpty(limit)){
			 maxlimit=limit;
		 }*/
		 
		 //bellow code $(this).attr('style');used to dinamic char from page
		var limit=$(this).attr('maxCharsData');
		 if(isNonEmpty(limit)){
			 maxlimit=limit;
		 }
		 
	    // get current number of characters
	    var length = $(this).val().length;
	    if(length >= maxlimit) {
			$(this).val($(this).val().substring(0, maxlimit));
			length = maxlimit;
		}
	    // update count on page load
	    //$(this).parent().find('.counter').html( (maxlimit - length) + ' characters left');
	    $('.counter').html("("+(maxlimit - length) + ' characters left'+")");
	    // bind on key up event
	    $(this).keyup(function(){
		// get new length of characters
		var new_length = $(this).val().length;
		if(new_length >= maxlimit) {
				$(this).val($(this).val().substring(0, maxlimit));
				//update the new length
				new_length = maxlimit;
			}
		// update count
		//$(this).parent().find('.counter').html( (maxlimit - new_length) + ' characters left');
		$('.counter').html("("+(maxlimit - new_length) + ' characters left'+")");
	    });
	});
	$('.smsWord_count').each(function(){
	    //maximum limit of characters allowed.
		var maxlimit = 120;
		 var limit=$(this).attr('maxCharsData');
		 if(isNonEmpty(limit)){
			 maxlimit=Number(limit);
		 }
	    // get current number of characters
	    var length = $(this).val().length;
	    if(length >= maxlimit) {
			$(this).val($(this).val().substring(0, maxlimit));
			length = maxlimit;
		}
	    // update count on page load
	    //$(this).parent().find('.counter').html( (maxlimit - length) + ' characters left');
	    $('.smsCounter').html("("+(maxlimit - length) + ' characters left'+")");
	    // bind on key up event
	    $(this).unbind("keyup").keyup(function(event){
	    	var new_length = 0;
	    	var titleLimit=$('input#messageDescription').val();
	    	if(titleLimit == "Dear Parent, Your Son/Daughter"){
	    		 new_length = $(this).val().length+titleLimit.length+Number(33);
	    	}else{
	    		 new_length = $(this).val().length+Number(38);
	    	}
    		if(new_length >= maxlimit) {
				$(this).val($(this).val().substring(0, maxlimit));
				//update the new length
				new_length = maxlimit;
			}
    		if(new_length == 161){
    			alert('SMS sent count will depends upon your message characters counts. 160 characters message will be treated as 1 SMS.');
    		}
    		if(new_length == 38 || new_length == 64){
    			var length = $(this).val().length;
    	    	$('.smsCounter').html("("+(maxlimit - length) + ' characters left'+")");
    		}else{
    			// update count
        		//$(this).parent().find('.counter').html( (maxlimit - new_length) + ' characters left');
        		$('.smsCounter').html("("+(maxlimit - new_length) + ' characters left'+")");
    		}
	    });
	});

});