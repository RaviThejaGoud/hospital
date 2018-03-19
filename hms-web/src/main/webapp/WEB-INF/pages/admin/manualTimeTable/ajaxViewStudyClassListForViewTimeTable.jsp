<%@ include file="/common/taglibs.jsp"%>
<s:if test='%{timeTableSettings != null}'>
	<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
		<s:form id="viewTimeTable" action="#" namespace="/timeTable" theme="simple" cssClass="form-horizontal">
			<div class="row" id="assignSubjectsToPeriods11">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Select Class:
					</label>
					<div class="col-md-5">
						<s:select list="studyClassList" name="studyClassId" listKey="id"
							id="studyClassId" listValue="classAndSection" headerKey="0" headerValue="- Select Class -" theme="simple"
							onchange="javascript:getViewTimeTableYearWise(this.value);" cssClass="required form-control input-medium">
						</s:select>
					</div>
				</div>
			</div>
			<div id="timeTableDiv"></div>
		</s:form>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no classes assigned for you.
		</div>
	</s:else>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no timetable settings.
	</div>
</s:else>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle("View Timetable");
		//UIJQueryUI.init();
	});
	function getViewTimeTableYearWise(studyClassId){
		if(studyClassId>0){
			$("#timeTableDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "studyClassId="+studyClassId+"&anyTitle=Y";
			var url = jQuery.url.getChatURL("/timeTable/ajaxDoViewTimeTableDetails.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(response) {
					if(isNonEmpty(response)){
						$('#timeTableDiv').html(response);
					}else{
						$('#timeTableDiv').show();
					}
				}
			});
		}else{
			alert("Please select class.");
			$('#timeTableDiv').html("");
		}
	}
</script>