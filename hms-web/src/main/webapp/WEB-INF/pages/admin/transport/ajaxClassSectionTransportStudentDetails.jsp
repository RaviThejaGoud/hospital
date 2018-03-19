<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{((studyClassList != null || !studyClassList.isEmpty()) || (objectList != null || !objectList.isEmpty()))}">
	<div class="table-scrollable">
	<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_7">
		<thead>
			<tr>
				<th>Admission Number</th>
				<th>Student Name</th>
				<th>Boarding Type</th>
				<s:iterator value="schoolTermsList">
					<th>
						<s:property value="termName"/><%--  (<s:property value="fromDateStr"/>) --%>
					</th>
				</s:iterator>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="studentsList">
			
				<tr class="studTransportDetails unTouch">	
					<td><span id='<s:property value='studId'/>' class='studentId'></span><s:property value="admissionNumber"/></td>				
					<td><span id='<s:property value='studId'/>' class='studentId'></span><s:property value="fullName"/></td>
					<td class="enableTdList YesNo"><s:select id="studentId%{studId}" list="#{'Y':'Same Pickup & Drop','N':'Different Pickup & Drop'}" cssStyle="width:175px;" name="personVo.bloodGroup" cssClass="m-wrap" 
					theme="simple" onchange="javascript:changeStudentBoardingPointOption(this,this.value,'%{studId}');"/></td>
					<s:iterator value="schoolTermsList">
						
						<s:if test="%{termActive}">
						<td class="enableTdList">
							<s:select list="objectList" listKey="routeBoardingPointVechileIds" listValue="routeBoardingPointVechileName" 
							id="%{studId}-%{id}" headerKey="" headerValue="- Select -"  name="%{routeBoardingPointVechileName}" 
							cssClass="m-wrap input-small studPickUpOption%{studId}" theme="simple" onchange="javascript:changeStudentBoardingPoint(this,this.value,'%{studId}');"/>
							<div id="studentTransportOptional<s:property value='%{studId}'/>" style="display: none;" class="studentTransportOptional<s:property value='%{studId}'/>">
								<s:select list="objectList" listKey="routeBoardingPointVechileIds" listValue="routeBoardingPointVechileName" 
								id="%{studId}-%{id}" headerKey="" headerValue="- Select -"  name="%{routeBoardingPointVechileName}" 
								cssClass="m-wrap input-small studDropOption%{studId}" theme="simple" onchange="javascript:changeStudentPickupPoint(this,this.value,'%{studId}');"/> 
							</div>
							</td>						
						</s:if>
						<s:else>
						<td class="disableTdList">
							<s:select list="objectList" listKey="routeBoardingPointVechileIds" listValue="routeBoardingPointVechileName" 
							id="%{studId}-%{id}" headerKey="" headerValue="- Select -"  name="%{routeBoardingPointVechileName}" 
							cssClass="m-wrap input-small studPickUpOption" theme="simple" onchange="javascript:changeStudentBoardingPoint(this.value,'%{studId}');" disabled="true"/>
							<div id="studentTransportOptional<s:property value='%{studId}'/>" style="display: none;" class="studentTransportOptional<s:property value='%{studId}'/>">
								<s:select list="objectList" listKey="routeBoardingPointVechileIds" listValue="routeBoardingPointVechileName" 
								id="%{studId}-%{id}" headerKey="" headerValue="- Select -"  name="%{routeBoardingPointVechileName}" 
								cssClass="m-wrap input-small studDropOption" theme="simple" onchange="javascript:changeStudentPickupPoint(this.value,'%{studId}');" disabled="true"/> 
							</div>
							</td>
						</s:else>
						
					</s:iterator>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="form-actions fluid">
		<div class="col-md-6">
			<div class="col-md-offset-3 col-md-12">
				<sj:submit value="Submit" id="submitButtonMainContent" targets="mainContentDiv" indicator="indicator"
					cssClass="submitBt btn blue" formIds="assignStudentBoardingDetails" onBeforeTopics="assignStudentBoardingDetailsValidation" />
				
				<s:url id="doCancelClassFee" action="ajaxDoAssignStudentForm" includeParams="all" escapeAmp="false">
					<s:param name="anyId" value="%{anyTitle}"></s:param>
				</s:url>
				<sj:a href="%{doCancelClassFee}" id="cancelMainContent" cssClass="btn default" targets="mainContentDiv">Cancel</sj:a>
			</div>
		</div>
	</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">No students from the selected class are assigned to school transport.</div>
	</s:else>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">No classes found for assigning
		students to vehicles.</div>
</s:else>

<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	TableEditable.init();
	UIExtendedModals.init();
	$("input:checkbox, input:radio").uniform();
	var classSectionId = $('#classSectionId').val();
	//var transportType = $('#transportType').val();
	var transportType = $("input[name='transportType']:checked"). val();
	//alert("transportType "+transportType)
	$.destroyTopic('assignStudentBoardingDetailsValidation');
	var feeURL = jQuery.url.getChatURL("/admin/ajaxEditStudentTransportDetails.do?classSectionId="+ classSectionId + "&transportType=" +transportType);
	$.ajax( {
		url : feeURL,
		cache : false,
		dataType : 'json',
		success : function(response) {
			if (response.data) {
				var data = response.data;
				var studId;
				var schoolTermId;
				var pickUpBoardingAndVechileIds;
				var dropBoardingAndVechileIds;
				var studTransportId;
				var studentPaymentId;
				var status;
				var inputObj;
				var inputObj1;
				var studentId;
				//alert(data);
				if (data.length >= 1) {
					for ( var i = 0; i < data.length; i++) {
						studTransportId = data[i].studTransportId;
						studId = data[i].studId;
						schoolTermId = data[i].termId;
						pickUpBoardingAndVechileIds = data[i].pickUpBoardingAndVechileIds;
						dropBoardingAndVechileIds = data[i].dropBoardingAndVechileIds;
						status = data[i].status;
						//alert("dropBoardingAndVechileIds  :"+dropBoardingAndVechileIds)
						if(studId!=studentId){
							inputObj = $('select#studentId' + studId);
							if (inputObj) {
								inputObj.val(status);
								inputObj.attr('id',studId);
								changeStudentBoardingPointOption("",status,studId);
							}
						}
						studentPaymentId = data[i].studentPaymentId;
						inputObj = $('select#' + studId + '-' + schoolTermId);
						if (inputObj) {
							inputObj.val(pickUpBoardingAndVechileIds);
							inputObj.attr('id',studId + '-'+ schoolTermId+ '-'+ studTransportId);
							if (studentPaymentId != 0) {
								inputObj.attr('class'," m-wrap input-small studPickUpOption");
								inputObj.attr('disabled',true);
							}
						}
						if(status = "N"){
							inputObj1 = $("div#studentTransportOptional"+studId).find("select#"+ studId + '-' + schoolTermId);
							if (inputObj1) {
								inputObj1.val(dropBoardingAndVechileIds);
								inputObj1.attr('id',studId + '-'+ schoolTermId+ '-'+ studTransportId);
								if (studentPaymentId != 0) {
									inputObj1.attr('class',"m-wrap input-small studDropOption");
									inputObj1.attr('disabled',true);
								}
							}
						}
						studentId=studId;
					}
				}
			}
		}
	});
	$.subscribe('assignStudentBoardingDetailsValidation',function(event, data) {
		var jsonObj = [];
		if($("tr.touch").length>0){
			$("tr.touch").each(function(){
				var transportType=null;
				var YesNo='';
				$(this).find("td.enableTdList").each(function(){
					var pickUpBoardingPointId=null;
					var dropBoardingPointId=null;
				if($(this).hasClass("YesNo")){
					YesNo=$(this).find('select.m-wrap option:selected').val();
					//return;
				}
				else if(YesNo=="Y"){
					jsonObj.push( {
						"studentId" : $(this).find('select.m-wrap').attr("id").split("-")[0],
						"pickUpPointId" : $(this).find('select.m-wrap option:selected').val(),
						"dropPointId" : $(this).find('select.m-wrap option:selected').val(),
						"termId" : $(this).find('select.m-wrap').attr("id").split("-")[1],
						"status" : YesNo,
					});
				}
				else if(YesNo=="N"){
					jsonObj.push( {
						"studentId" : $(this).find('select.m-wrap').attr("id").split("-")[0],
						"pickUpPointId" : $(this).find('select.m-wrap option:selected').val(),
						"dropPointId" : $(this).find("div#studentTransportOptional"+$(this).find('select.m-wrap').attr("id").split("-")[0]).find('select.m-wrap option:selected').val(),
						"termId" : $(this).find('select.m-wrap').attr("id").split("-")[1],
						"status" : YesNo,
				});
				}
				else{
					// Nothing to do.
				}
				}); 
				//alert("JSon :: :"+" status :"+YesNo +"---"+ JSON.stringify(jsonObj));
				var jsono = {
						"data" : jsonObj
					}
					if (jsonObj.length > 0 ) {
						$('input[name=studentTransportData]').val(JSON.stringify(jsono));
						
					} else {
						event.originalEvent.options.submit = false;
						alert('Please select at least one student data.');
					}
			});
		}else{
			event.originalEvent.options.submit = false;
			alert('No changes to save.');
		}
		//alert("JSon :: :"+" status :"+YesNo +"---"+ JSON.stringify(jsonObj));
		});
});
function changeStudentBoardingPointOption(obj,studBoardOption,studId){
	if(studBoardOption == "Y"){
		$('.studentTransportOptional'+studId).hide();
	}else{
		$('.studentTransportOptional'+studId).show();
	}
	$(obj).parents("tr").removeClass("unTouch");
	$(obj).parents("tr").addClass("touch");
}
function changeStudentBoardingPoint(obj,boardingPointId,studId){
	$(obj).parent("td").nextAll('td').each(function(){
		$(this).find('select.studPickUpOption'+studId).val(boardingPointId);
	});
	
	$(obj).parents("tr").removeClass("unTouch");
	$(obj).parents("tr").addClass("touch");
	
}
function changeStudentPickupPoint(obj,pickupPointId,studId){
	$(obj).parents("td").nextAll('td').each(function(){
		$(this).find('select.studDropOption'+studId).val(pickupPointId);
	});
	 
	$(obj).parents("tr").removeClass("unTouch");
	$(obj).parents("tr").addClass("touch");
}
</script>