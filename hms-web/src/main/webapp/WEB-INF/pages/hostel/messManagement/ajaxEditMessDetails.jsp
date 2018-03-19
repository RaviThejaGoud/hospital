<%@ include file="/common/taglibs.jsp"%>
<s:form id="editClassSubjects" action="ajaxAddMess"
	cssClass="form-horizontal" method="post" theme="simple"
	namespace="/hostel">
	<s:hidden name="tempId" value="%{tempId}" />
	<s:hidden id="hostelNameIds" name="anyTitle" />
	<h4 class="bold pageTitle">Update Mess Details</h4>
	<div class="form-body">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span>Mess Name :
					</label>
					<div class="col-md-7">
						<sj:textfield name="mess.messName" id="messName"
							cssClass="required form-control input-medium as-input"
							maxlength="50"></sj:textfield>
						<span class="help-block">(Enter at least three characters
							for Mess Name.)</span>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-3"> Description :</label>
					<div class="col-md-7">
						<sj:textarea rows="3" cols="20" name="mess.messDescription"
							maxCharsData="1000" tabindex="3"
							cssClass="form-control word_count"></sj:textarea>
						<span class="help-block">
							<div class="counter"></div>
						</span>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> <span class="required">*</span>
				Available Hostels :
			</label>
			<div class="col-md-9">
				<div class="checkbox-list">
					<label class="checkbox-inline"> <input type="checkbox"
						name="" value="" onClick="checkallHostels()"
						class="checkbox allHostels"> All Hostels
					</label>
				</div>
				<s:checkboxlist name="chkBoxSelectedIds" list="hostelList" id="checkHostel" 
					listKey="id" listValue="hostelName" theme="ems" cssClass="small" />
			</div>
		</div>
		<s:if test='%{#session.previousYear == "N"}'>
			<div class="form-actions fluid">
				<div class="col-md-6">
					<div class="col-md-offset-3 col-md-12">
						<sj:submit value="Submit"
							cssClass="submitBt btn blue" formIds="editClassSubjects"
							onBeforeTopics="editClassValidation" indicator="indicator1" targets="mainContentDiv"
							validate="true" />
							<s:url id="urlMessManagement" includeParams="all" escapeAmp="false"
										action="ajaxViewMessManagementHome" namespace="/hostel"></s:url>
									<sj:a href="%{urlMessManagement}" cssClass="btn default"
										indicator="indicator" targets="mainContentDiv"> Cancel</sj:a>
					</div>
				</div>
			</div>
		</s:if>
	</div>
</s:form>
<script language="JavaScript" type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('generateHostelIdsTopic');
	$("input:checkbox, input:radio").uniform();
	FormAdvanced.init();
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		   $(".allHostels").parent('span').removeClass("checked");
			$(".allHostels").attr("checked", false);
		} else {
		    $(".allHostels").parent('span').addClass("checked");
			$(".allHostels").attr("checked", true);
		}
	});
	$("#messName").focus();
	
/* 	var selectedChkCount = $("input[name=chkBoxSelectedIds]:checked").length;
	 if (selectedChkCount > 0){
		 var selectedMessHostelIds = [];
			$("input:checkbox[name=chkBoxSelectedIds]:checked").each(function()
			{
				selectedMessHostelIds.push($(this).val('<s:property value="%{id}" />'));
				$(this).parent('span').addClass('checked');
				$(this).attr("checked", "true");
			});
			$('#checkHostel').val(selectedMessHostelIds);
	 } */
});
$.subscribe('editClassValidation',function(event, data) {
		 var HostelIds = $("input[name=chkBoxSelectedIds]:checked");
			if (HostelIds.length > 0){
				var selectedHostelIds = '';
					selectedHostelIds = '(';
					for ( var i = 0; i < HostelIds.length; i++) {
						if (i == (HostelIds.length - 1))
							selectedHostelIds += HostelIds[i].value;
						else {
							selectedHostelIds += HostelIds[i].value + ', ';
						}
					}
					selectedHostelIds += ')';
			$("#hostelNameIds").val(selectedHostelIds);
			return true;
			}
			else {
				alert("Please select at least one hostel");
				event.originalEvent.options.submit=false;
			}
	});
	 function checkallHostels() {
			if ($(".allHostels").is(':checked')){
			    $("[name='chkBoxSelectedIds']").each(function(){
				   $(this).parent('span').addClass('checked');
				    $(this).attr("checked", "true");
				});
			}
			else{
			 $("[name='chkBoxSelectedIds']").each(function(){
				   $(this).parent('span').removeClass('checked');
				    $(this).removeAttr("checked");
				});
			}	
		}
</script>