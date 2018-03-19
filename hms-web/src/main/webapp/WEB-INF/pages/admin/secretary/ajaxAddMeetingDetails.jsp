<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/timeEntry/jquery.timeentry.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jQuery/jquery.multiple.select.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/newCss/multiple-select.css" type="text/css" media="screen"/>
	<s:form action="ajaxAddMeetingDetails" theme="simple" id="ui_element" method="post" cssClass="form-horizontal" namespace="/admin" >
		<s:hidden name="meetingDetails.id" value="%{meetingDetails.id}"></s:hidden>
	<div class="form-body">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Meeting Date : </label>
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="meetingDate" readonly=""
								class="form-control required"  name="meetingDetails.meetingDate"> <span
								class="input-group-btn required">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label">
						<span class="required">*</span>Start Time :
					</label>
					<div class="col-md-5">
						<s:textfield id="meetingStartTime" name="meetingDetails.startTime" size="10" cssClass="timeChange SST form-control input-small required" />
					</div>
				</div>
			</div>
			<%--  --%>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label">
						<span class="required">*</span>Agenda :
					</label>
					<div class="col-md-5">
						<sj:textfield name="meetingDetails.meetingAgenda" id="meetingAgenda" cssClass="form-control required" maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label">
						<span class="required">*</span>Venue :
					</label>
					<div class="col-md-5">
						<sj:textfield name="meetingDetails.place" id="place" cssClass="form-control required" maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> comments : </label>
					<div class="col-md-5">
						<sj:textarea rows="3" cols="20" name="meetingDetails.comments"
							maxCharsData="1000" tabindex="4"
							cssClass="form-control word_count"></sj:textarea>
						<span class="help-block">
							<div class="counter"></div>
						</span>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4 required"> Select Schools : </label>
					<div class="col-md-5">
					<s:if test="%{objectList != null && !objectList.isEmpty()}">
						<select id="ms" multiple="multiple" name="tempString" class="tempString">
						     <s:iterator value="objectList">
								<option value="<s:property value='id' />"><s:property value='organization'/></option>
							</s:iterator>
					        </select>
						</s:if>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-4 col-md-12">
					<sj:submit cssClass="submitBt btn blue" value="Submit" validate="true" 
						indicator="indicator" targets="mainContentDiv"  onBeforeTopics="validateSchoolNames"/>
						<s:url id="doCancelForm" action="ajaxMeetingDetailsHome" namespace="/admin" />
						<sj:a href="%{doCancelForm}" targets="mainContentDiv" cssClass="btn default" tabindex="3">
							Cancel </sj:a>
				</div>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('validateSchoolNames');
	changePageTitle("Add Meeting Details");
	FormComponents.init();
	FormAdvanced.init();
	$('#meetingStartTime').timeEntry();
});
$.subscribe('validateSchoolNames', function(event, data) {
	var x = $("div.ms-parent").children('button').find('span').text();
	if (x==null || x=="" || x=="undefined"){
	   	alert("Please select a one school.");
	   	event.originalEvent.options.submit=false;
	}
});

 $(function() {
     $('#ms').change(function() {
         console.log($(this).val());
     }).multipleSelect({
         width: '100%'
     });
 });
</script>

 