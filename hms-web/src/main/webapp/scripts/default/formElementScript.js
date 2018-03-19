var FormAdvanced = function () {
  var initForm1 = function() {
	  
	  // Radio Buttons ON/OFF functionality
		$('div.make-switch').find('label').click(function(){
		  if($(this).parent().hasClass('switch-on')==true){
		   	$(this).parent().siblings('input[type="hidden"]').val($(this).parents('div.make-switch').attr('data-value'));
		    }
		   else{
		     $(this).parent().siblings('input[type="hidden"]').val($(this).parents('div.make-switch').attr('data-id'));
		   }
		});
		
	// Auto dropdown and clicked link enable with active state
      $('li.dropdown li').click(function(){
	  $('ul.nav-tabs li').removeClass('active');
	  $('.dropdown li').removeClass('active');
	  $(this).parents('li.dropdown').addClass('active');
	  $(this).parents('li.dropdown').children('.js-activated').html($(this).find('a').html()+'<b class="caret"></b>');
	  $(this).addClass('active');
	});
    } 
    return {
        //main function to initiate the module
        init: function () {
            initForm1();
        }
    };

}();