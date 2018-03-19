<%@ include file="/common/taglibs.jsp"%>
<div class="portlet box blue">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-globe"></i>School Time Settings
		</div>
	</div>
	<div class="portlet-body form">
	  <div class="form-body">
	    <div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-6">
						School Timings :
					</label>
					<div class="col-md-6">
						<div class="make-switch has-switch" data-id="ST" data-value="CT"
							style="width: 180px" data-off="warning" data-on="success"
							data-off-label="ClassWise" data-on-label="SchoolWise">
							<input type="radio" class="toggle" id="schoolTimings"
								checked="checked"
								onclick="javascript:GetSchoolTimings(this.value);">
							<input type="hidden" name="schoolTimings" value="ST">
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-6">
						Day Wise Timings :
					</label>
					<div class="form-control-static">
					<input type="checkbox" value="DT" name="dayTimings"
							class="checkbox" />
					</div>
				</div>
			</div>
		</div>
		<div class="portlet">
			<div class="portlet-body">
				<div class="table-responsive">
					<table
						class="table table-striped table-bordered table-advance table-hover">
						<tbody>
							<tr>
								<td class="hidden-xs">
									<label>
										SST : School Start Time
									</label>
								</td>
								<td>
									<label>
										MBST : Morning Break Start Time
									</label>
								</td>
								<td>
									<label>
										MBET : Morning Break End Time
									</label>
								</td>
							</tr>
							<tr>
								<td>
									<label>
										LBST : Lunch Break Start Time
									</label>
								</td>
								<td>
									<label>
										LBET : Lunch Break End Time
									</label>
								</td>
								<td>
									<label>
										EBST : Evening Break Start Time
									</label>
								</td>
							</tr>
							<tr>
								<td>
									<label>
										EBET : Evening Break End Time
									</label>
								</td>
								<td>
									<label>
										SET : School End Time
									</label>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<table class="table table-striped table-bordered table-hover table-full-width">
			<tr>
				<td style="width: 45px;">
					<label style="width: 45px;" id="schoolOrClass">
						DAYS
					</label>
				</td>
				<td style="width: 45px;">
					SST
				</td>
				<td style="width: 45px;">
					MBST
				</td>
				<td style="width: 45px;">
					MBET
				</td>
				<td style="width: 45px;">
					LBST
				</td>
				<td style="width: 45px;">
					LBET
				</td>
				<td style="width: 45px;">
					EBST
				</td>
				<td style="width: 45px;">
					EBET
				</td>
				<td style="width: 45px;">
					SET
				</td>
				<td style="width: 24px;">
				</td>
			</tr>
		</table>
		<div id="SchoolWiseResults">
			<table
				class="table table-striped table-bordered table-hover table-full-width classWiseList">
				<tr class="dayWiseList" style="margin-left: 94px;">
					<td style="width: 130px;"></td>
					<td class="grid_2 timeEntry" style="width: 70px;">
							<s:textfield id="schoolStartTime" name="startTime" size="10"
								cssClass="timeChange SST form-control input-small" />
					</td>

					<td class="grid_2 timeEntry" style="width: 70px;">
							<s:textfield id="breakfastStartTime" name="morningBreakStartTime"
								size="10" cssClass="timeChange MBST form-control input-small" />
					</td>
					<td class="grid_2 timeEntry" style="width: 70px;">
							<s:textfield id="breakfastEndTime" name="morningBreakEndTime"
								size="10" cssClass="timeChange MBET form-control input-small" />
					</td>

					<td class="grid_2 timeEntry" style="width: 70px;">
							<s:textfield id="lunchStartTime" name="lunchStartTime" size="10"
								cssClass="timeChange LBST form-control input-small" />
					</td>
					<td class="grid_2 timeEntry" style="width: 70px;">
							<s:textfield id="lunchEndTime" name="lunchEndTime" size="10"
								cssClass="timeChange LBET form-control input-small" />
					</td>
					<td class="grid_2 timeEntry" style="width: 70px;">
							<s:textfield id="eveningBreakStartTime"
								name="eveningBreakStartTime" size="10"
								cssClass="timeChange EBST form-control input-small" />
					</td>
					<td class="grid_2 timeEntry" style="width: 70px;">
							<s:textfield id="eveningBreakEndTime" name="eveningBreakEndTime"
								size="10" cssClass="timeChange EBET form-control input-small" />
					</td>
					<td class="grid_2 timeEntry" style="width: 70px;">
							<s:textfield cssStyle="width: 70px;" id="schoolEndTime"
								name="endTime" size="10"
								cssClass="timeChange SET form-control input-small" />
					</td>
					<td class="grid_2 timeEntry" style="width: 60px;">
					</td>					
				</tr>
			</table>
		</div>
		<div id="ClassWiseResults" style="display: none;">
			<table
				class="table table-striped table-bordered table-hover table-full-width classWiseList">
				<s:iterator value="classList">
					<tr class="dayWiseList">
						<td class="<s:property value="className"/>"
							id="<s:property value="id"/>" style="width: 5px;display: none;"></td>
						<td class="grid_2 timeEntry" style="width: 12.4%;">
							<label id="<s:property value="id"/>" class="className">
								<s:property value="className" />
							</label>
						</td>
						<td class="grid_2 timeEntry" style="width: 60px;">
								<s:textfield id="cwschoolStartTime%{id}" name="startTime"
									size="10" cssClass="form-control input-small timeChange SST" />
						</td>
						<td class="grid_2 timeEntry" style="width: 60px;">
								<s:textfield id="cwbreakfastStartTime%{id}"
									name="morningBreakStartTime" size="10"
									cssClass="form-control input-small timeChange MBST" />
						</td>
						<td class="grid_2 timeEntry" style="width: 60px;">
								<s:textfield id="cwbreakfastEndTime%{id}"
									name="morningBreakEndTime" size="10"
									cssClass="form-control input-small timeChange MBET" />
						</td>
						<td class="grid_2 timeEntry" style="width: 60px;">
								<s:textfield id="cwlunchStartTime%{id}" name="lunchStartTime"
									size="10" cssClass="form-control input-small timeChange LBST" />
						</td>
						<td class="grid_2 timeEntry" style="width: 60px;">
								<s:textfield id="cwlunchEndTime%{id}" name="lunchEndTime"
									size="10" cssClass="form-control input-small timeChange LBET" />
						</td>
						<td class="grid_2 timeEntry" style="width: 60px;">
								<s:textfield id="cweveningBreakStartTime%{id}"
									name="eveningBreakStartTime" size="10"
									cssClass="form-control input-small timeChange EBST " />
						</td>
						<td class="grid_2 timeEntry" style="width: 60px;">
								<s:textfield id="cweveningBreakEndTime%{id}"
									name="eveningBreakEndTime" size="10"
									cssClass="form-control input-small timeChange EBET" />
						</td>
						<td class="grid_2 timeEntry" style="width: 60px;">
								<s:textfield cssStyle="width: 60px;" id="cwschoolEndTime%{id}"
									name="endTime" size="10"
									cssClass="form-control input-small timeChange SET" />
						</td>
						<td id="copyPasteDiv" style="width: 60px;">
									<input type="checkbox" name="copyPaste"
										class="copyPaste" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="SchoolDaywiseResults" style="display: none;">
			<table
				class="table table-striped table-bordered table-hover table-full-width classWiseList">
				<s:iterator value="weekDayList">
					<tr class="dayWiseList" id="<s:property value="dayName"/>">
						<td class="grid_2 timeEntry" style="width: 130px;">
							<label id="<s:property value="id"/>" class="dayName">
								<s:property value="dayName" />
							</label>
						</td>
						<td class="grid_2 timeEntry" style="width: 60px;">
								<s:textfield id="sdschoolStartTime%{id}" name="startTime"
									size="10" cssClass="form-control input-small timeChange SST" />
						</td>
						<td class="grid_2 timeEntry" style="width: 60px;">
								<s:textfield id="sdbreakfastStartTime%{id}"
									name="morningBreakStartTime" size="10"
									cssClass="form-control input-small timeChange MBST" />
						</td>
						<td class="grid_2 timeEntry" style="width: 60px;">
								<s:textfield id="sdbreakfastEndTime%{id}"
									name="morningBreakEndTime" size="10"
									cssClass="form-control input-small timeChange MBET" />
						</td>
						<td class="grid_2 timeEntry" style="width: 60px;">
								<s:textfield id="sdlunchStartTime%{id}" name="lunchStartTime"
									size="10" cssClass="form-control input-small timeChange LBST" />
						</td>
						<td class="grid_2 timeEntry" style="width: 60px;">
								<s:textfield id="sdlunchEndTime%{id}" name="lunchEndTime"
									size="10" cssClass="form-control input-small timeChange LBET" />
						</td>
						<td class="grid_2 timeEntry" style="width: 60px;">
								<s:textfield id="sdeveningBreakStartTime%{id}"
									name="eveningBreakStartTime" size="10"
									cssClass="form-control input-small timeChange EBST" />
						</td>
						<td class="grid_2 timeEntry" style="width: 60px;">
								<s:textfield id="sdeveningBreakEndTime%{id}"
									name="eveningBreakEndTime" size="10"
									cssClass="form-control input-small timeChange EBET" />
						</td>
						<td class="grid_2 timeEntry" style="width: 60px;">
								<s:textfield cssStyle="width: 60px;" id="sdschoolEndTime%{id}"
									name="endTime" size="10"
									cssClass="form-control input-small timeChange SET" />
						</td>
						<td class="grid_2 timeEntry" id="copyPasteDiv" style="width: 60px;">
								<input type="checkbox" name="copyPaste" style="width: 60px;"
									class="copyPaste" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="ClassDaywiseResults" style="display: none;">
			<s:iterator value="classList">
				<table
					class="table table-striped table-bordered table-hover table-full-width classWiseList">
					<tr class="<s:property value="className"/>"
						id="<s:property value="id"/>">
						<s:set var="classId" value="%{id}"></s:set>
						<td class="parentClass closed">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<div class="grid_2 timeEntry col-md-7">
											<label id="<s:property value="id"/>" class="className"
												style="margin-left: 15px;">
												<a href="#!" id="<s:property value="id"/>"
													class="linkClassName"><s:property value="className" /><strong
													style="float: right;">[Show Timings]</strong> </a>
											</label>
										</div>
										<div class="col-md-3">
											<a name="copyPasteTable" class="topopup" href="#!">Copy/Paste</a>
										</div>
									</div>
								</div>
							</div>
						</td>
						</tr>
							<s:iterator value="weekDayList">
								<tr class="dayWiseList weekDayMultiple"
									style="float: left; display: none;"
									id="<s:property value="dayName"/>">
									<td class="grid_2 timeEntry" style="display: none;">
										<label id='<s:property value="#classId" />' class="className"></label>
									</td>
									<td class="grid_2 timeEntry" style="width: 130px;">
										<label id="<s:property value="id"/>" class="dayName">
											<s:property value="dayName" />
										</label>
									</td>
									<td class="grid_2 timeEntry" style="width: 60px;">
											<s:textfield id='cdschoolStartTime%{id}%{#classId}'
												name="startTime" size="10"
												cssClass="form-control input-small timeChange SST" />
									</td>
									<td class="grid_2 timeEntry" style="width: 60px;">
											<s:textfield id='cdbreakfastStartTime%{id}%{#classId}'
												name="morningBreakStartTime" size="10"
												cssClass="form-control input-small timeChange MBST" />
									</td>
									<td class="grid_2 timeEntry" style="width: 60px;">
											<s:textfield id="cdbreakfastEndTime%{id}%{#classId}"
												name="morningBreakEndTime" size="10"
												cssClass="form-control input-small timeChange MBET" />
									</td>
									<td class="grid_2 timeEntry" style="width: 60px;">
											<s:textfield id="cdlunchStartTime%{id}%{#classId}"
												name="lunchStartTime" size="10"
												cssClass="form-control  input-small timeChange LBST" />
									</td>
									<td class="grid_2 timeEntry" style="width: 60px;">
											<s:textfield id="cdlunchEndTime%{id}%{#classId}"
												name="lunchEndTime" size="10"
												cssClass="form-control  input-small timeChange LBET" />
									</td>
									<td class="grid_2 timeEntry" style="width: 60px;">
											<s:textfield id="cdeveningBreakStartTime%{id}%{#classId}"
												name="eveningBreakStartTime" size="10"
												cssClass="form-control input-small timeChange EBST" />
									</td>
									<td class="grid_2 timeEntry" style="width: 60px;">
											<s:textfield id="cdeveningBreakEndTime%{id}%{#classId}"
												name="eveningBreakEndTime" size="10"
												cssClass="form-control input-small timeChange EBET" />
									</td>
									<td class="grid_2 timeEntry" style="width: 60px;">
											<s:textfield cssStyle="width: 60px;"
												id="cdschoolEndTime%{id}%{#classId}" name="endTime"
												size="10" cssClass="form-control input-small timeChange SET" />
									</td>
									<td class="grid_2" id="copyPasteDiv" style="width: 60px;">
											<input type="checkbox" name="copyPaste" style="width: 60px;"
												class="copyPaste" />
									</td>
								</tr>
							</s:iterator>
				</table>
			</s:iterator>
		</div>
		<div id="toPopup" data-width="760" class="modal fade modal-overflow in" style="display: none; width: 760px; margin-left: -329px; margin-top: 200px;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">Select Classes to copy/paste timings</h4>
			</div>
			<div class="modal-body">
				<div id="chkBoxesToSelect"></div>
				<div class="form-actions fluid">
					<div class="col-md-6">
						<div class="col-md-offset-3 col-md-9">
							<a href="#" class="livebox"><input type="button" id="applyBtn"
								class="submit small btn blue" value="Apply" /> </a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="loader"></div>
		<div id="backgroundPopup"></div>
   </div>
  </div>
</div>
<style type="text/css">
.input-small {
	width: 100px !important;
}
</style>

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/timeEntry/jquery.timeentry.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/popup/popup.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio:not('.toggle')").uniform();
	FormComponents.init();
	FormAdvanced.init();
	$('tr.dayWiseList').each(function(){
	   $(this).find('td.timeEntry').each(function(){
		   $(this).find('input.timeChange').each(function(){
		   $('#'+$(this).attr('id')).val('');
		   $('#'+$(this).attr('id')).timeEntry();
   });
  }); 
 });
 });

$('table.classWiseList').each(function(){
	$(this).find('tr.dayWiseList:last').each(function(){
	    $(this).find('td#copyPasteDiv').hide();
	  });
	});
$('input[name="chkBoxSelectedIds"]').each(function(){
    var divClass1=$(this).parents('label').text().trim();
    if($(this).is(":checked")){
		 $('tr.dayWiseList').each(function(){		    
		   if($(this).attr('id')==divClass1)
			 		$(this).show();
			});
	  }
	  else{
	    $('tr.dayWiseList').each(function(){
	    	 if($(this).attr('id')==divClass1)
			 	$(this).hide();
		 });
		}	  
});
$('div.make-switch').find("label[for='schoolTimings']").click(function(){	
		  if($(this).parent().hasClass('switch-on')==true){
			  GetSchoolTimings("CT");
		  }
		  else{
		     GetSchoolTimings("ST");
		  }
 });
 
var dataURL = jQuery.url.getChatURL("/admin/ajaxGetAcademicYearTimings.do?academicYearId="+ $('input#academicYearId').val());
          $.ajax({
				url : dataURL,
				cache : false,
				dataType : 'json',
				success : function(response) {
					if (response.data) {
						var data = response.data;
						if (data.length > 0) {
						  var boolVal=true;
						  for ( var i = 0; i < data.length; i++) {
						  if(data[i].STATUS=="CD"){
						   if(boolVal){
						      $('input[name="dayTimings"]').click();
						      $('input[name="schoolTimings"]').val("CT");						       
						      $('div.make-switch').find("label[for='schoolTimings']").parent("div.switch-animate").removeClass("switch-on").addClass("switch-off");
						       GetSchoolTimings("CT");
						       //alert("ccc");
						       boolVal=false;
						    } 
						    $('table.classWiseList:visible').each(function(){
							    $(this).find('tr#'+data[i].CLASSID).each(function(){
							  //  $(this).nextAll('tr.dayWiseList:visible').show();
							    $(this).find('td.parentClass').removeClass('closed');
							    $(this).find('td.parentClass').find('strong').html('[Hide Timings]');
							    $(this).nextAll('tr.dayWiseList:visible:eq('+i+')').each(function(){
		   						$(this).find('td.timeEntry:visible:gt(0)').each(function(){
								if($(this).find('input').attr('class')!='undefined'){
								   if($(this).find('input').hasClass('SST')){
							        $(this).find('input.SST').val(data[i].SST);
							       }
							        else if($(this).find('input').hasClass('MBST')){
							        $(this).find('input.MBST').val(data[i].MBST);
							       }
							        else if($(this).find('input').hasClass('MBET')){
							        $(this).find('input.MBET').val(data[i].MBET);
							       }
							       else if($(this).find('input').hasClass('LBST')){
							        $(this).find('input.LBST').val(data[i].LBST);
							       }
							       else if($(this).find('input').hasClass('LBET')){
							        $(this).find('input.LBET').val(data[i].LBET);
							       }
							       else if($(this).find('input').hasClass('EBST')){
							        $(this).find('input.EBST').val(data[i].EBST);
							       }
							       else if($(this).find('input').hasClass('EBET')){
							        $(this).find('input.EBET').val(data[i].EBET);
							       }
							       else{
							        $(this).find('input.SET').val(data[i].SET);
							       }
							       }
								  });
							     });
						      });
							});
						   }
						   else if(data[i].STATUS=="SD"){
						     if(boolVal){
						      $('input[name="dayTimings"]').click();
						       $('input[name="schoolTimings"]').val("ST");						       
						       $('div.make-switch').find("label[for='schoolTimings']").parent("div.switch-animate").removeClass("switch-off").addClass("switch-on");
						       GetSchoolTimings("ST");
						     // $('input#schoolTimingsST').parent('div#schoolTimings').find('label:first').addClass('ui-state-active');
						      boolVal=false;
						    }
						    $('table.classWiseList:visible').each(function(){
							$(this).find('tr.dayWiseList:visible:eq('+i+')').each(function(){
	   						$(this).find('td.timeEntry:visible:gt(0)').each(function(){
	   						  if($(this).find('input').attr('class')!='undefined'){
						       if($(this).find('input').hasClass('SST')){
						        $(this).find('input.SST').val(data[i].SST);
						       }
						        else if($(this).find('input').hasClass('MBST')){
						        $(this).find('input.MBST').val(data[i].MBST);
						       }
						        else if($(this).find('input').hasClass('MBET')){
						        $(this).find('input.MBET').val(data[i].MBET);
						       }
						       else if($(this).find('input').hasClass('LBST')){
						        $(this).find('input.LBST').val(data[i].LBST);
						       }
						       else if($(this).find('input').hasClass('LBET')){
						        $(this).find('input.LBET').val(data[i].LBET);
						       }
						       else if($(this).find('input').hasClass('EBST')){
						        $(this).find('input.EBST').val(data[i].EBST);
						       }
						       else if($(this).find('input').hasClass('EBET')){
						        $(this).find('input.EBET').val(data[i].EBET);
						       }
						       else{
						        $(this).find('input.SET').val(data[i].SET);
						       }
						       }
						  	 });
						    });
						   });
						   }
						    else if(data[i].STATUS=="ST"){
						    if(boolVal){
						     $('div.make-switch').find("label[for='schoolTimings']").parent("div.switch-animate").removeClass("switch-off").addClass("switch-on");
						      GetSchoolTimings("ST");
						     // $('input#schoolTimingsST').parent('div#schoolTimings').find('label:first').addClass('ui-state-active');
						      boolVal=false;
						    }
						    $('table.classWiseList:visible').each(function(){
							$(this).find('tr.dayWiseList:visible').each(function(){
	   						$(this).find('td.timeEntry:visible').each(function(){
	   						  if($(this).find('input').attr('class')!='undefined'){
						       if($(this).find('input').hasClass('SST')){
						        $(this).find('input.SST').val(data[i].SST);
						       }
						        else if($(this).find('input').hasClass('MBST')){
						        $(this).find('input.MBST').val(data[i].MBST);
						       }
						        else if($(this).find('input').hasClass('MBET')){
						        $(this).find('input.MBET').val(data[i].MBET);
						       }
						       else if($(this).find('input').hasClass('LBST')){
						        $(this).find('input.LBST').val(data[i].LBST);
						       }
						       else if($(this).find('input').hasClass('LBET')){
						        $(this).find('input.LBET').val(data[i].LBET);
						       }
						       else if($(this).find('input').hasClass('EBST')){
						        $(this).find('input.EBST').val(data[i].EBST);
						       }
						       else if($(this).find('input').hasClass('EBET')){
						        $(this).find('input.EBET').val(data[i].EBET);
						       }
						       else{
						        $(this).find('input.SET').val(data[i].SET);
						       }
						       }
						  	 });
						    });
						   });
						   }
						   else{
						    if(boolVal){
						      $('div.make-switch').find("label[for='schoolTimings']").parent("div.switch-animate").removeClass("switch-on").addClass("switch-off");
						      GetSchoolTimings("CT");
						     // $('input#schoolTimingsST').parent('div#schoolTimings').find('label:last').addClass('ui-state-active');
						      boolVal=false;
						    }
    					    $('table.classWiseList:visible').each(function(){
							$(this).find('tr.dayWiseList:visible:eq('+i+')').each(function(){
	   						$(this).find('td.timeEntry:visible:gt(0)').each(function(){
	   						
	   						  if($(this).find('input').attr('class')!='undefined'){
						       if($(this).find('input').hasClass('SST')){
						        $(this).find('input.SST').val(data[i].SST);
						       }
						        else if($(this).find('input').hasClass('MBST')){
						        $(this).find('input.MBST').val(data[i].MBST);
						       }
						        else if($(this).find('input').hasClass('MBET')){
						        $(this).find('input.MBET').val(data[i].MBET);
						       }
						       else if($(this).find('input').hasClass('LBST')){
						        $(this).find('input.LBST').val(data[i].LBST);
						       }
						       else if($(this).find('input').hasClass('LBET')){
						        $(this).find('input.LBET').val(data[i].LBET);
						       }
						       else if($(this).find('input').hasClass('EBST')){
						        $(this).find('input.EBST').val(data[i].EBST);
						       }
						       else if($(this).find('input').hasClass('EBET')){
						        $(this).find('input.EBET').val(data[i].EBET);
						       }
						       else{
						        $(this).find('input.SET').val(data[i].SET);
						       }
						       }
						  	 });
						    });
						   });
						   }
						  }
						}
					}
				 }
				});
        	 

$('input.copyPaste').click(function(){
	if($(this).is(":checked")){
       var answer = confirm('Do you want to copy and paste for next row ?');
		if (answer)
		{
		 $(this).parents('td').prevAll('td:not(:last)').each(function(index) {
			$(this).parents('tr.dayWiseList').next('tr.dayWiseList').find('input.copyPaste')
			 .parents('td').prevAll('td:eq(' + index + ')').find('input.timeChange').val($(this).find('input.timeChange ').val());															});
			 } else {
			 	console.log('cancel');
		 }
	  	} 
});
 
$('a.linkClassName').click(function(){
	var thisParent = $(this).parents('td.parentClass');
	if (thisParent.hasClass('closed')) {
		thisParent.parent('tr').nextAll('tr').show();
		thisParent.removeClass('closed');
		$(this).children('strong').html('[Hide Timings]');
	} else {
		thisParent.parent('tr').nextAll('tr').hide();
		thisParent.addClass('closed');
		$(this).children('strong').html('[Show Timings]');
	}
 });


 function  GetSchoolTimings(obj){
$('label#schoolOrClass').html('Days');
 if(obj=="ST" && $('input[name="dayTimings"]').is(":checked")){
	   	$('div#ClassDaywiseResults').hide();
	  	$('div#SchoolDaywiseResults').show();
	  	$('div#SchoolWiseResults').hide();
	  	$('div#ClassWiseResults').hide();
	  	$('input#selectedToggle').val("SD");
	  	
   }else if(obj=="CT" && $('input[name="dayTimings"]').is(":checked")){
	   $('div#ClassDaywiseResults').show();
	   $('div#SchoolDaywiseResults').hide();
	   $('div#SchoolWiseResults').hide();
	   $('div#ClassWiseResults').hide();
	   $('input#selectedToggle').val("CD");
	   $('label#schoolOrClass').html('Classes');
   }
   else{
   if(obj=="ST"){
        $('div#ClassDaywiseResults').hide();
        $('div#ClassWiseResults').hide();
	    $('div#SchoolDaywiseResults').hide();
	    $('div#SchoolWiseResults').show(); 
	    $('input#selectedToggle').val("ST"); 
   }
   else{
       $('div#ClassDaywiseResults').hide();
       $('div#ClassWiseResults').show();
       $('div#SchoolDaywiseResults').hide();
       $('div#SchoolWiseResults').hide (); 
       $('input#selectedToggle').val("CT"); 
       $('label#schoolOrClass').html('Classes');
     }
   }
 }
  $('input[name="dayTimings"]').click(function(){
     if($(this).is(":checked")){
       if($('input[name="schoolTimings"]').val()=="ST"){
       	$('div#ClassDaywiseResults').hide();
	  	$('div#SchoolDaywiseResults').show();
	  	$('div#SchoolWiseResults').hide();
	  	$('div#ClassWiseResults').hide();
	  	$('input#selectedToggle').val("SD");
       }
       else{
        $('div#ClassDaywiseResults').show();
	    $('div#SchoolDaywiseResults').hide();
	    $('div#SchoolWiseResults').hide();
	    $('div#ClassWiseResults').hide();
	    $('input#selectedToggle').val("CD");
	    $('label#schoolOrClass').html('Classes');
       }
	  }else{
	    $('div#ClassDaywiseResults').hide();
	    $('div#SchoolDaywiseResults').hide();
	    if($('input[name="schoolTimings"]').val()=="ST"){
	     $('div#SchoolWiseResults').show();
	     $('div#ClassWiseResults').hide();
	     $('input#selectedToggle').val("ST");
	    }
	    else{
	     $('div#SchoolWiseResults').hide();
	     $('div#ClassWiseResults').show();
	     $('input#selectedToggle').val("CT");
	     $('label#schoolOrClass').html('Classes');
	    }
	  }
  });
 
 $('input[name="chkBoxSelectedIds"]').click(function(){
   var divClass=$(this).parents('label').text().trim(); 
	  if($(this).is(":checked")){
		$('tr.dayWiseList').each(function(){
		  $('tr#'+divClass).show();
		});
	  }else{
	  $('tr.dayWiseList').each(function(){
		  $('tr#'+divClass).hide();
		});
	  }
 });

 
 
function checkSchoolTimes(event) {
	var selectedTime = $(event).val();
	var previousTime = '';
	if (isNonEmpty(selectedTime)) {
		$('select.timePkr option:selected[value != ""]').each(
				function() {
					if (isNonEmpty(previousTime)) {
						var previousSelectedDate = new Date("1/1/2007 "
								+ previousTime);
						var selectedDate = new Date("1/1/2007 "
								+ $(this, 'option:selected').val());
						if (selectedDate <= previousSelectedDate) {
							alert("Please change selected time.");
							event.value = '';
						}
					}
					previousTime = $(this, 'option:selected').val();
				});
	}
}	 
</script>
 