<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_13 border">
	<div class="grid_5">
		<h2>
			<span class="noteMassage"></span>Create Food Types:
		</h2>
	</div>
	<div>
		<h3>
			<sj:a indicator="indicator" cssStyle="cursor: pointer;" id="createMessTimings" cssClass="right">Add Food Menu </sj:a>
		</h3>
	</div>
</div>
<span class="buildingId" id="<s:property value='tempId2'/>"></span>

<div class="grid_12">
	<span class="messTimingsData">
		<div class="grid_13" id="TextBoxDiv1">
		<div id="removeMenuDiv">
			<span class="hostelFoodTimeId1 messTimes" id=''></span>
				<div class="grid_5">
					<label>
						<span class="required">*</span>Mess Food Menu Type #1
					</label>
					<sj:textfield name="messMenuTime.messFoodType" required="true" 
						id="messFoodType1" cssClass="messFoodType required textfield"
						maxlength="25"></sj:textfield>
				</div>
				<div class="grid_3">
					<label>
						<span class="required">*</span>Start Time #1
					</label>
					<s:select name="messMenuTime.startTime" cssClass="textfield startTime foodTimes"  id="startTime1"
						required="true" cssStyle="width:100px;" theme="css_xhtml" headerKey="" headerValue="- Select -" onchange="checkFoodTypeTimings(this);"
						list="#{'05:00 AM':'05:00 AM','05:15 AM':'05:15 AM','05:30 AM':'05:30 AM','05:45 AM':'05:45 AM', '06:00 AM':'06:00 AM', '06:15 AM':'06:15 AM','06:30 AM':'06:30 AM','06:45 AM':'06:45 AM','07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 PM':'12:00 PM','12:15 PM':'12:15 PM','12:30 PM':'12:30 PM','12:45 PM':'12:45 PM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM','06:15 PM':'06:15 PM','06:30 PM':'06:30 PM','06:45 PM':'06:45 PM','07:00 PM':'07:00 PM','07:15 PM':'07:15 PM','07:30 PM':'07:30 PM','07:45 PM':'07:45 PM','08:00 PM':'08:00 PM','08:15 PM':'08:15 PM','08:30 PM':'08:30 PM','08:45 PM':'08:45 PM','09:00 PM':'09:00 PM','09:15 PM':'09:15 PM','09:30 PM':'09:30 PM','09:45 PM':'09:45 PM','10:00 PM':'10:00 PM','10:15 PM':'10:15 PM','10:30 PM':'10:30 PM','10:45 PM':'10:45 PM','11:00 PM':'11:00 PM','11:15 PM':'11:15 PM','11:30 PM':'11:30 PM','11:45 PM':'11:45 PM'}"></s:select>
				</div>
				<div class="grid_3">
					<label>
						<span class="required">*</span>End Time #1
					</label>
					<s:select name="messMenuTime.endTime" cssClass="textfield endTime foodTimes" id="endTime1"
						required="true"  cssStyle="width:100px;" theme="css_xhtml" headerKey="" headerValue="- Select -" onchange="checkFoodTypeTimings(this);"
						list="#{'05:00 AM':'05:00 AM','05:15 AM':'05:15 AM','05:30 AM':'05:30 AM','05:45 AM':'05:45 AM', '06:00 AM':'06:00 AM', '06:15 AM':'06:15 AM','06:30 AM':'06:30 AM','06:45 AM':'06:45 AM','07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 PM':'12:00 PM','12:15 PM':'12:15 PM','12:30 PM':'12:30 PM','12:45 PM':'12:45 PM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM','06:15 PM':'06:15 PM','06:30 PM':'06:30 PM','06:45 PM':'06:45 PM','07:00 PM':'07:00 PM','07:15 PM':'07:15 PM','07:30 PM':'07:30 PM','07:45 PM':'07:45 PM','08:00 PM':'08:00 PM','08:15 PM':'08:15 PM','08:30 PM':'08:30 PM','08:45 PM':'08:45 PM','09:00 PM':'09:00 PM','09:15 PM':'09:15 PM','09:30 PM':'09:30 PM','09:45 PM':'09:45 PM','10:00 PM':'10:00 PM','10:15 PM':'10:15 PM','10:30 PM':'10:30 PM','10:45 PM':'10:45 PM','11:00 PM':'11:00 PM','11:15 PM':'11:15 PM','11:30 PM':'11:30 PM','11:45 PM':'11:45 PM'}"></s:select>
	
				</div>
				<div class="right">
					<a id="searchPlace" 
						onclick="javascript:removieFoodMenuItems(this,'removeMenuDiv','1');" style="cursor: pointer;" >Remove</a>
				</div>
			</div> 
		</span>
	</div>
<script type="text/javascript">
	$(document).ready(function() {  
			var counter = 2;
			var buildingId =$("span.buildingId").attr("id");
			if(isNonEmpty(buildingId)){
					var url = "ajaxGetEditFoodTimes.do?tempId1="+buildingId;
					$.ajax( {
						url : url,
						cache : false,
						dataType : 'json',
						success : function(response) {
						var hostelFoodTimingsList=response.messTimeingsList;
						if(hostelFoodTimingsList){
								var hostelFoodTimingsCount=hostelFoodTimingsList.length;
								if(isNonEmpty(hostelFoodTimingsCount)){
									for(var i=1; i<hostelFoodTimingsCount; i++) {
										counter=addMessTimeings(counter);
									}
									for ( var j = 0; j <= hostelFoodTimingsList.length; j++) {
										$("span.hostelFoodTimeId"+(j+1)).attr('id',hostelFoodTimingsList[j].id);
										$('#messFoodType'+(j+1)).val(hostelFoodTimingsList[j].messFoodTypeName);
										$('#startTime'+(j+1)).val(hostelFoodTimingsList[j].startTime);
										$('#endTime'+(j+1)).val(hostelFoodTimingsList[j].endTime);
									}
								}
								
							}
						
						}
					});
				}
			$("#createMessTimings").click(
				function() {
					var divLength=$('div#TextBoxDiv1').children('div').length;
					if (divLength > 7) {
							alert("Only 7 Food Type allow");
							return false;
						}
						else{
						   counter=addMessTimeings(counter);
						}
					});
					
     });
	
     /*  function removieFoodMenuItems(counter){ onclick="javascript:confirmDialog(this,'stepClassSections');" 
     alert(counter);
     	if (counter == 1) {
				alert("you want Remove this Food Type ?");
				return false;
			}
		//	counter--;
			$("#TextBoxDiv" + counter).remove();
			alert("TextBoxDiv : " + $("#TextBoxDiv" + counter).remove().g);
     }*/
    
    function removieFoodMenuItems(event,target,counter) {
		var messFoodTypeId = $("span.hostelFoodTimeId"+counter).attr("id");
		var divLength=$('div#TextBoxDiv1').children('div').length;
		if(divLength==1){
				alert('at least you need to have one food item.');
				return false;
			}
		else{
			if ($(event).next('.question').length <= 0) {
			  $(event).after('<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
		     }
		}
		$(event).next('.question').animate( { opacity : 1 }, 300);
		$('.yes').unbind('click');
		$('.yes').bind('click', function() {
			var prdDiv = $(this).parents('.question');
			prdDiv.html('Processing...');
			if(isNonEmpty(messFoodTypeId)){
			var pars = "messFoodTypeId=" + messFoodTypeId;
			$.ajax( {
					url : jQuery.url.getChatURL("/hostel/ajaxRemovieMenuTimes.do"),
					cache : false,
					data : pars,
					success : function(response) {
					}
				});
			}
		   $("#TextBoxDiv" + counter).remove();
				
			//window.reload();
		});
		$('.cancel').unbind('click');
		$('.cancel').bind('click', function() {
			$(this).parents('.question').fadeOut(300, function() {
				$(this).remove();
			});
			return false;
		});
    }
     
    function addMessTimeings(counter){
	   var newTextBoxDiv = $(document.createElement('div')).attr("id",'TextBoxDiv' + counter);
			newTextBoxDiv.html('<span class="messTimingsData"> <div class="grid_13"><span id="" class="hostelFoodTimeId'+ counter+' messTimes"></span><div class="grid_5"> <label>Mess Food Menu Type #'
										+ counter
										+ ' : </label>'
										+ '<input type="text"  style="width:200px;" name="messMenuTime.messFoodType' 
										+ counter
										+ '" id="messFoodType'
										+ counter
										+ '" value="" class="messFoodType"/> </div>'
										+ '<div class="grid_3"><label>Start Time #'
										+ counter
										+ ' : </label>'
										+ '<select name="messMenuTime.startTime'
										+ counter
										+ '" id="startTime'
										+ counter
										+ '" value="" onchange="checkFoodTypeTimings(this);" class="startTime foodTimes" style="width:100px;"><option value="">- Select -</option><option value="05:00 AM">05:00 AM</option><option value="05:15 AM">05:15 AM</option><option value="05:30 AM">05:30 AM</option><option value="05:45 AM">05:45 AM</option><option value="06:00 AM">06:00 AM</option><option value="06:15 AM">06:15 AM</option><option value="06:30 AM">06:30 AM</option><option value="06:45 AM">06:45 AM</option><option value="07:00 AM">07:00 AM</option><option value="07:15 AM">07:15 AM</option><option value="07:30 AM">07:30 AM</option><option value="07:45 AM">07:45 AM</option><option value="08:00 AM">08:00 AM</option><option value="08:15 AM">08:15 AM</option><option value="08:30 AM">08:30 AM</option><option value="08:45 AM">08:45 AM</option><option value="09:00 AM">09:00 AM</option><option value="09:15 AM">09:15 AM</option><option value="09:30 AM">09:30 AM</option><option value="09:45 AM">09:45 AM</option><option value="10:00 AM">10:00 AM</option><option value="10:15 AM">10:15 AM</option><option value="10:30 AM">10:30 AM</option><option value="10:45 AM">10:45 AM</option><option value="11:00 AM">11:00 AM</option><option value="11:15 AM">11:15 AM</option><option value="11:30 AM">11:30 AM</option><option value="11:45 AM">11:45 AM</option><option value="12:00 PM">12:00 PM</option><option value="12:15 PM">12:15 PM</option><option value="12:30 PM">12:30 PM</option><option value="12:45 PM">12:45 PM</option><option value="01:00 PM">01:00 PM</option><option value="01:15 PM">01:15 PM</option><option value="01:30 PM">01:30 PM</option><option value="01:45 PM">01:45 PM</option><option value="02:00 PM">02:00 PM</option><option value="02:15 PM">02:15 PM</option><option value="02:30 PM">02:30 PM</option><option value="02:45 PM">02:45 PM</option><option value="03:00 PM">03:00 PM</option><option value="03:15 PM">03:15 PM</option> <option value="03:30 PM">03:30 PM</option><option value="03:45 PM">03:45 PM</option><option value="04:00 PM">04:00 PM</option><option value="04:15 PM">04:15 PM</option><option value="04:30 PM">04:30 PM</option><option value="04:45 PM">04:45 PM</option><option value="05:00 PM">05:00 PM</option><option value="05:15 PM">05:15 PM</option><option value="05:30 PM">05:30 PM</option><option value="05:45 PM">05:45 PM</option><option value="06:00 PM">06:00 PM</option><option value="06:15 PM">06:15 PM</option><option value="06:30 PM">06:30 PM</option><option value="06:45 PM">06:45 PM</option><option value="07:00 PM">07:00 PM</option><option value="07:15 PM">07:15 PM</option><option value="07:30 PM">07:30 PM</option><option value="07:45 PM">07:45 PM</option><option value="08:00 PM">08:00 PM</option><option value="08:15 PM">08:15 PM</option><option value="08:30 PM">08:30 PM</option><option value="08:45 PM">08:45 PM</option><option value="09:00 PM">09:00 PM</option><option value="09:15 PM">09:15 PM</option><option value="09:30 PM">09:30 PM</option><option value="09:45 PM">09:45 PM</option><option value="10:00 PM">10:00 PM</option><option value="10:15 PM">10:15 PM</option><option value="10:30 PM">10:30 PM</option><option value="10:45 PM">10:45 PM</option><option value="11:00 PM">11:00 PM</option><option value="11:15 PM">11:15 PM</option><option value="11:30 PM">11:30 PM</option><option value="11:45 PM">11:45 PM</option></select> </div>'
										+ '<div class="grid_3"><label>End Time #'
										+ counter
										+ ' : </label>'
										+ '<select name="messMenuTime.endTime'
										+ counter
										+ '" id="endTime'
										+ counter
										+ '" value="" onchange="checkFoodTypeTimings(this);" class="endTime foodTimes" style="width:100px;"><option value="">- Select -</option><option value="05:00 AM">05:00 AM</option><option value="05:15 AM">05:15 AM</option><option value="05:30 AM">05:30 AM</option><option value="05:45 AM">05:45 AM</option><option value="06:00 AM">06:00 AM</option><option value="06:15 AM">06:15 AM</option><option value="06:30 AM">06:30 AM</option><option value="06:45 AM">06:45 AM</option><option value="07:00 AM">07:00 AM</option><option value="07:15 AM">07:15 AM</option><option value="07:30 AM">07:30 AM</option><option value="07:45 AM">07:45 AM</option><option value="08:00 AM">08:00 AM</option><option value="08:15 AM">08:15 AM</option><option value="08:30 AM">08:30 AM</option><option value="08:45 AM">08:45 AM</option><option value="09:00 AM">09:00 AM</option><option value="09:15 AM">09:15 AM</option><option value="09:30 AM">09:30 AM</option><option value="09:45 AM">09:45 AM</option><option value="10:00 AM">10:00 AM</option><option value="10:15 AM">10:15 AM</option><option value="10:30 AM">10:30 AM</option><option value="10:45 AM">10:45 AM</option><option value="11:00 AM">11:00 AM</option><option value="11:15 AM">11:15 AM</option><option value="11:30 AM">11:30 AM</option><option value="11:45 AM">11:45 AM</option><option value="12:00 PM">12:00 PM</option><option value="12:15 PM">12:15 PM</option><option value="12:30 PM">12:30 PM</option><option value="12:45 PM">12:45 PM</option><option value="01:00 PM">01:00 PM</option><option value="01:15 PM">01:15 PM</option><option value="01:30 PM">01:30 PM</option><option value="01:45 PM">01:45 PM</option><option value="02:00 PM">02:00 PM</option><option value="02:15 PM">02:15 PM</option><option value="02:30 PM">02:30 PM</option><option value="02:45 PM">02:45 PM</option><option value="03:00 PM">03:00 PM</option><option value="03:15 PM">03:15 PM</option> <option value="03:30 PM">03:30 PM</option><option value="03:45 PM">03:45 PM</option><option value="04:00 PM">04:00 PM</option><option value="04:15 PM">04:15 PM</option><option value="04:30 PM">04:30 PM</option><option value="04:45 PM">04:45 PM</option><option value="05:00 PM">05:00 PM</option><option value="05:15 PM">05:15 PM</option><option value="05:30 PM">05:30 PM</option><option value="05:45 PM">05:45 PM</option><option value="06:00 PM">06:00 PM</option><option value="06:15 PM">06:15 PM</option><option value="06:30 PM">06:30 PM</option><option value="06:45 PM">06:45 PM</option><option value="07:00 PM">07:00 PM</option><option value="07:15 PM">07:15 PM</option><option value="07:30 PM">07:30 PM</option><option value="07:45 PM">07:45 PM</option><option value="08:00 PM">08:00 PM</option><option value="08:15 PM">08:15 PM</option><option value="08:30 PM">08:30 PM</option><option value="08:45 PM">08:45 PM</option><option value="09:00 PM">09:00 PM</option><option value="09:15 PM">09:15 PM</option><option value="09:30 PM">09:30 PM</option><option value="09:45 PM">09:45 PM</option><option value="10:00 PM">10:00 PM</option><option value="10:15 PM">10:15 PM</option><option value="10:30 PM">10:30 PM</option><option value="10:45 PM">10:45 PM</option><option value="11:00 PM">11:00 PM</option><option value="11:15 PM">11:15 PM</option><option value="11:30 PM">11:30 PM</option><option value="11:45 PM">11:45 PM</option></select> </div>'
                                        +'<div class="right" id="removeBoardingPoints1"><a style="cursor: pointer;" id="searchPlace" onclick="javascript:removieFoodMenuItems(this,\'removeMenuDiv\','+counter+');">Remove</a></div>'
                                                                                                                               +'</div></span>');
										newTextBoxDiv.appendTo("#TextBoxDiv1");
										counter++;
	     return counter;
}
function checkFoodTypeTimings(event){
	var selectedTime = $(event).val();
	var previousTime = '';
	if(isNonEmpty(selectedTime)){
	$('select.foodTimes option:selected[value != ""]').each(function()
		{
			if(isNonEmpty(previousTime)){
				var previousSelectedDate= new Date("1/1/2007 " + previousTime);
				var selectedDate = new Date("1/1/2007 " + $(this,'option:selected').val());
				if(selectedDate <= previousSelectedDate){
				    	 alert("Please change selected time.");
				    	 event.value='';
				}
			}
			previousTime = $(this,'option:selected').val();
		});		
	}
}
</script>