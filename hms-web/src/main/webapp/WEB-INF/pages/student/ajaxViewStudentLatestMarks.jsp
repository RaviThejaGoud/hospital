<%@ include file="/common/taglibs.jsp"%>
   
   
   <div class="modal fade modal-overflow in" data-width="760"
	id="responsiveVision"
	style="display: block; width: 550px; height: 505px; top: 0px;"
	aria-hidden="true">
   <div id="addChildDiv">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption"><i class="fa fa-globe"></i>
				Examination Results
			</div>
			
			<button type="button" class="close" data-dismiss="modal" id="closeButton"
				aria-hidden="true"></button>
				
		</div>
		<div class="portlet-body">
			<div id="site_statistics_content">
				<div id="studentExamMarksDiv"> 
				<s:hidden name="student.id" id="studentHiddenId"></s:hidden>
				<h1><s:property value="tempString" /> - RESULTS</h1>
					<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
						<thead>
							<tr>
								<th>
									Subject Name
								</th>
								<th>
									Grade/Marks
								</th>
								 <th>
									Result
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="tempList">
								<tr class="optionalSubj">
									<td>
										<s:property value="subjectName" />
									</td>
									<td>
										<s:property value="subjectResult" />
									</td>
									 <td>
										<s:property value="passOrFail" />
									</td>
									
										
									</tr>
							</s:iterator>
						</tbody>
					</table>
				
					<b>Over all Grade:</b> <s:property value="tempString2" /><br/>
					<b>Result:</b> <s:property value="anyId" /><br/>
					
					<b>Remarks:</b>  <s:if test='%{anyId == "Pass"}'>
											Good keep it up
									</s:if>
									<s:else>
									Need to improve this subjects. <b><s:property value="tempString3" /></b>
									
									</s:else>
					 <br/>
				
				
					<div class="row">
						<div class="form-group col-md-6">
							
							<div class="col-md-12">
								<label class="checkbox">
								<div class="checker">
									<span id="eservice"> <input type="checkbox"
										name="customer.checkEmailService" id="emailService"
										onclick="javascript:dontshowConfirmDialog(this,'serviceDiv');"
										class="changeServicesStatus" /> <!--<input type="checkbox"
											class="checkbox changeServicesStatus"
											name="customer.checkEmailService" id="emailService"
											data="${pageContext.request.contextPath}/admin/ajaxChangeEmailServiceStatus" />
									--></span>
								</div> Do not show in next login
							</label>
							</div>
						</div>
					</div>
								
								
					<button type="button" class="btn default" id="closeButton">Close </button>  
					<!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>	 -->
								
								
				
				</div>
			</div>
		</div>
	</div>
</div>
</div>	

<script type="text/javascript">

$('button.visionMision').click(function(){
	   $('a#showVisionMission').removeAttr('data-toggle');
	   $('a#showVisionMission').removeAttr('href');
	});
	
	
$(document).ready(function() {
	 	//TableAdvanced.init();
		UIExtendedModals.init();
		
		 $('div.modal-header').click();
		 if($("div#responsiveVision:hidden")){
		  $('a#showVisionMission').removeAttr('href');
		 }
		 
		
});


$("button#closeButton").click(function(){
	$('#responsiveVision').hide();
});


function dontshowConfirmDialog(event, target) {
	if ($(".submitBt").val() != "Submit") {
		$('div#mess').show;
	} else {
		$('div#mess').hide;
	}
	var url = '';
	var pars = '';
	
	var studentHiddenId  =  $('#studentHiddenId').val();
	if ($(event).parent().parent().next('.question').length <= 0) {
		$(event)
				.parent()
				.parent()
				.after(
						'<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).parent('span').addClass("checked");
	$(event).attr("checked", true);
	$(event).parent().parent().next('.question').animate({
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes')
			.bind(
					'click',
					function() {
						var prdDiv = $(this).parents('.question');
						prdDiv.html('Processing...');
						if ($("#emailService").is(':checked')) {
							url = jQuery.url.getChatURL("/student/ajaxUpdateStudentPopupDisplay.do?student.popUpDisplay=A&student.id="+studentHiddenId);
						} else {
							url = jQuery.url.getChatURL("/student/ajaxUpdateStudentPopupDisplay.do?student.popUpDisplay=R&student.id="+studentHiddenId);
						}
						$.ajax({
							url : url,
							cache : false,
							data : pars,
							success : function(html) {
								$('#responsiveVision').hide();
								/*if ($('table.striped')) {
									$('table.striped tr').removeClass('odd even');
									$('table.striped tr.loaded:even').addClass('odd');
									$('table.striped tr.loaded:odd').addClass('even');
								}*/
							}
						});
					});
	$('.cancel').unbind('click');
	$('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).remove();
		});
		if ($(event).val() == "true") {
			$(event).attr("checked", "checked");
		} else {
			$(event).removeAttr("checked");
			$(event).parent('span').removeClass('checked');
		}
		return false;
	});
}


</script>
