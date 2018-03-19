<%@ include file="/common/taglibs.jsp"%>
<div id="cancelTimeTableButtonDiv">
	<div id="message"><%@ include file="/common/messages.jsp"%></div>
	<s:if test='%{timeTableSettings != null}'>
		<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
			<s:form id="addSubjectForPeriod" action="ajaxSaveSubjectForDayEachPeriod" namespace="/timeTable" theme="simple" cssClass="form-horizontal">
				<s:hidden name="tempString" id="weekPeriodsSubjectVal"></s:hidden>
				<div class="row" id="assignSubjectsToPeriods11">
					<div class="form-group">
						<label class="control-label col-md-3">
							<span class="required">*</span>Select Class:
						</label>
						<div class="col-md-5">
							<s:select list="studyClassList" name="studyClassId" listKey="id"
								id="studyClassId" listValue="classAndSection" headerKey="0" headerValue="- Select Class -" theme="simple"
								onchange="javascript:getSubjectsByStudyClassId(this.value);" cssClass="required form-control input-medium">
							</s:select>
						</div>
					</div>
				</div>
				<div id="subjectsListDiv"></div>
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
</div>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Assign Subjects To Periods");
		$.destroyTopic('addSubjectForPeriodValidation');
		/* var classId=$("#classId").val();
		alert("classId: "+classId);
		var submit=true;
		if(classId>0){
			getSubjectsByStudyClassId(studyClassId,submit);
		} */
	});
	$.subscribe('addSubjectForPeriodValidation', function(event, data){
		if ($('#addSubjectForPeriod').valid()) {
			var educationJsonObj = [];
			var weekId='';
			var periodId='';
			var weekPeriodId='';
			var subjectId= '';
			var dayId= '';
			$('tr.daySubjectPeriod:visible').each(function(){
				$(this).find("td").each(function(){
					var inputNameVal=$(this).attr('id');
					if(isNonEmpty(inputNameVal)){
						 weekPeriodId=inputNameVal.replace(/\D/g, '');
						 subjectId=$(this).find("div#labOrSubject").find("select#subjectId").val();
						 if(isNonEmpty(subjectId)){
							 weekPeriodId=inputNameVal.replace(/\D/g, '');
							 periodId= $(this).find("input#periodId").val();
							 weekId= $(this).find("input#weekId").val();
							 subjectId=$(this).find("div#labOrSubject").find('select#subjectId option:selected').val();
							 if(isNonEmpty(subjectId)){
								 if(subjectId>0){
									educationJsonObj.push({
										"weekPeriodId" : weekPeriodId,
										"dayId" : weekId,
										"periodId" : periodId,
										"subjectId" : subjectId
									});
								 }
							  var val= parseInt($(this).find("span#eachTdVal").text()) +1 ;
							  var i=1;
							  $(this).find("div#displayAnotherLabGroup"+val).find("div.displayAnotherLabDivNewData").each(function(){
								  var anotherLabSubjectId=$(this).find('select#subjectId option:selected').val();
							    	if(isNonEmpty(anotherLabSubjectId)){
							    		if(anotherLabSubjectId>0){
							    			if(i>1){
							    				educationJsonObj.push({
								    				"weekPeriodId" : weekPeriodId,
													"dayId" : weekId,
													"periodId" : periodId,
													"subjectId" : anotherLabSubjectId
												});
							    			}
							    		}
							    	}
							    	i++;
							    });
							  var electVal= parseInt($(this).find("span#eachTdElectiveVal").text()) +1 ;
  							  var j=1;
  							  $(this).find("div#displayElectiveGroup"+electVal).find("div.displayAnotherElectiveDivNewData").each(function(){
  								  var anotherElectiveSubjectId=$(this).find('select#subjectId option:selected').val();
  							    	if(isNonEmpty(anotherElectiveSubjectId)){
  							    		if(anotherElectiveSubjectId>0){
  							    			if(j>1){
  							    				educationJsonObj.push({
  								    				"weekPeriodId" : weekPeriodId,
  													"dayId" : weekId,
  													"periodId" : periodId,
  													"subjectId" : anotherElectiveSubjectId
  												});
  							    			}
  							    		}
  							    	}
  							    	j++;
  							    });
							 }
							// alert("educationJsonObj:-"+JSON.stringify(educationJsonObj));
						 }
					}
				});
			});	
			$("input#weekPeriodsSubjectVal").val(JSON.stringify(educationJsonObj));
			return true;
		}
	});
	/* Get Subjects by studyclassId */
	function getSubjectsByStudyClassId(studyClassId){
		if(studyClassId>0){
			/* if(!submit){
				$("#message").hide();
			} */
			$("#subjectsListDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "studyClassId="+studyClassId;
			var url = jQuery.url.getChatURL("/timeTable/ajaxAssignSubjectsToPeriodForTimeTable.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(response) {
					$('#subjectsListDiv').html(response);
				}
			});
		} else{
			alert("Please Select Year & Section.");
			$('#subjectsListDiv').html("");
		}
	}
</script>