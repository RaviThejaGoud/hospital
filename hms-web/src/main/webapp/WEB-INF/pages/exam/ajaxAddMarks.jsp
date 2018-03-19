<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:if test="%{examScheduleList != null && !examScheduleList.isEmpty()}">
<s:hidden name="tempString" cssClass='tempString' />

<div id="loadingImage"> </div>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_7">
		<thead>
			<tr>
				<th>
					Admission Number
				</th>
				<th>
					Student Name
				</th>
				<s:iterator value="examScheduleList">
					<th>
						<s:hidden name="id" value="%{scheduleMaxMarks}" id="%{scheduleId}"/>
						<s:property value="subjectName" />(Max Marks: <s:property value="scheduleMaxMarks" />)
					</th>
				</s:iterator>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
			<tr class="subjectClassMonths">
					<td>
						<s:property value="admissionNumber" />
					</td>
					<td id="<s:property value="studId"/>">
						<s:property value="personFullName" />
					</td>
					<s:iterator value="examScheduleList">
					
					<td id="subjMonth<s:property value="studId"/><s:property value="subjectId"/>">
						<%-- <sj:textfield name="%{studId}%{studySubjectId}" id="studentMarks" value="" id="%{studId}%{studySubjectId}"
							cssClass="form-control input-small" ></sj:textfield> --%>
							<s:if test='%{subjectType == "Y"}'>
								<s:if test="%{optionalSubjectId == 0}">
									<sj:textfield name="studentMarks" value="" id="%{studId}SM%{subjectId}SM%{scheduleId}"  cssClass="form-control input-small charCheckining" maxlength="7"></sj:textfield>
								</s:if>
								<s:elseif test="%{subjectId == optionalSubjectId}">
									<sj:textfield name="studentMarks" value="" id="%{studId}SM%{subjectId}SM%{scheduleId}"  cssClass="form-control input-small charCheckining" maxlength="7"></sj:textfield>
								</s:elseif>
								<s:else>
									<sj:textfield name="studentMarks" value="" id="%{studId}SM%{subjectId}SM%{scheduleId}"  cssClass="form-control input-small charCheckining" maxlength="7" disabled="true"></sj:textfield>
								</s:else>
							</s:if>
							<s:else>
								<sj:textfield name="studentMarks" value="" id="%{studId}SM%{subjectId}SM%{scheduleId}"  cssClass="form-control input-small charCheckining" maxlength="7"></sj:textfield>	
							</s:else>
						
					</td>
					</s:iterator>
				</tr>
				</s:iterator>
		</tbody>
	</table>
	<s:if test='%{user.isSchoolTeacher=="Y"}'>
		<sj:submit value="Submit" id="submitButtonMainContent"
				targets="classContentDiv" 
				cssClass="submitBt btn blue" formIds="addStudentMarksFormId"
				onBeforeTopics="classFeeFormValidation" /> 
	</s:if>
	<s:else>
		 <sj:submit value="Submit" id="submitButtonMainContent"
		targets="urlStudentMarksSheetDiv" indicator="indicator"
		cssClass="submitBt btn blue" formIds="addStudentMarksFormId"
		onBeforeTopics="classFeeFormValidation" /> 
	</s:else>
								
	<%-- <div class="form-actions fluid">
			<div class="col-md-8">
				<div class="col-md-offset-3 col-md-9">
					<sj:submit value="Submit" id="submitButtonMainContent"
								targets="urlStudentMarksSheetDiv" indicator="indicator"
								cssClass="submitBt btn blue" formIds="addStudentMarksFormId"
								onBeforeTopics="classFeeFormValidation" />
				</div>
			</div>
		</div> --%>
		
			</s:if>
			<s:else>
				<div class="alert alert-info">
					Currently there are no examschedules.
				</div>
			</s:else>
			<div id="responsive2"></div>
<script type="text/javascript">
TableAdvanced.init();
TableEditable.init();
UIExtendedModals.init();
$(document).ready(function() {
	var studyClassId = $('#classSection').val();
	var examTypeId = $('#examTypeListId').val();
	var subtypeId = $('#subtypeId').val();
	
	//alert(studyClassId);
	$('.numeric').numeric();
	$('.numericDot').numeric( {allow : "."});
	$('.numericMinus').numeric( {allow : "-"});
	
	var dataURL = jQuery.url.getChatURL("/exam/ajaxGetStudentMarksList.do?studyClassId=" + studyClassId +"&examTypeId="+examTypeId +"&subtypeId="+subtypeId);
	$('#loadingImage').html( '<div align="center" style="margin:150px 0px 4px 500px;position:absolute;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
				url : dataURL,
				cache : false,
				dataType : 'json',
				success : function(response) {
					if (response.studentMarksSettingsData) {
						$('#loadingImage').hide();
						var data = response.studentMarksSettingsData;
						if (data.length > 0) {
							for ( var i = 0; i < data.length; i++) {
								$( 'tr.subjectClassMonths:visible') .each( function() {
													$(this) .find( "td#subjMonth" + data[i].STUDENTID  + data[i].SUBJECTID) .each( function() {
														//$(this) .find( 'input#studentMarks') .val(data[i].INPUTVALUE);
														
														$(this) .find('input[id^=' + data[i].STUDENTID + 'SM' +  data[i].SUBJECTID +'SM' +  data[i].EXAMSCHEDULEID +']').val(data[i].INPUTVALUE);
													});
												});
							}
						}
						//$('#divId').html(null);
					}
				}
			});
	
	$.subscribe('classFeeFormValidation',function(event, data) {
		var studentMarks = '';
		var isAllCorrectMarks=true;
		var studentNames='';
		var jsonObj = [];
		var objIds;
		var allids;
		$('input[name="studentMarks"]').each(function() {
			studentMarks = $(this).val();
			/* if (!isNonEmpty(studentMarks)) {
				studentMarks = "0";
			} */
			objIds = $(this).attr('id');
			if (isNonEmpty(objIds)) {
				allids = objIds.split('SM');
				if (allids.length > 2) {
					
					var maxMarks = $('#'+allids[2]).val();
					if(isNonEmpty(studentMarks))
					{
						if(parseInt(maxMarks) >= parseInt(studentMarks))
						{
							jsonObj.push( {
								"EXAMSCHEDULEID" : allids[2],
								"SUBJECTID" : allids[1],
								"STUDENTID" : allids[0],
								"STUDENTMARKS" : studentMarks
							});
						}
						else if(parseInt(maxMarks) < parseInt(studentMarks))
						{
							isAllCorrectMarks = false;
							//alert("Dont give more than max marks to '" + $('#'+allids[0]).html() + "'");
							studentNames = studentNames + $.trim($('#'+allids[0]).html()) + ",";
							event.originalEvent.options.submit = false;
							//return false;
						}
						else if($.type(studentMarks) == "string")
						{
							var n = studentMarks.length;
							if(n == 1)
							{
								jsonObj.push( {
									"EXAMSCHEDULEID" : allids[2],
									"SUBJECTID" : allids[1],
									"STUDENTID" : allids[0],
									"STUDENTMARKS" : studentMarks
								});
							}
							else
							{
								isAllCorrectMarks = false;
								alert("Please enter valid character, If student was absent then give 'A'.");
								event.originalEvent.options.submit = false;
								return false;
							}
						}
						
					}
					else {
						jsonObj.push( {
							"EXAMSCHEDULEID" : allids[2],
							"SUBJECTID" : allids[1],
							"STUDENTID" : allids[0],
							"STUDENTMARKS" : ""
						});
					}
				} /* else {
					jsonObj.push( {
						"EXAMSCHEDULEID" : "0",
						"SUBJECTID" : allids[1],
						"STUDENTID" : allids[0],
						"STUDENTMARKS" : null
					});
				} */
			}
		});
		//$('.tempString').val(JSON.stringify(jsonObj));
			
			var jsono = {
				"data" : jsonObj
			}
			if(!isAllCorrectMarks)
			{
				alert("Entered marks must be lesser than or equal to subject max marks for" + studentNames.slice(0, -1) + "");
				event.originalEvent.options.submit = false;
				return false;
			}
			else if (jsonObj.length > 0  && isAllCorrectMarks) {
				$('#submitButtonMainContent').val('Saving...');
				//$('#divId').html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
				$('input[name=tempString]').val(JSON.stringify(jsono));
			} 
			else {
				event.originalEvent.options.submit = false;
				alert('Please add student marks');
			}
		});
	
	//https://css-tricks.com/snippets/javascript/javascript-keycodes/
	$('.charCheckining').bind('keypress', function (event) {
	   
	    //alert(event.charCode + "  " + event.keyCode);
	    /*alert($.inArray(event.keyCode,allowedKeyCodesArr)); */
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    var allowedKeyCodesArr = [8,9,37,39,46,48,96,97,98,99,100,101,102,103,104,105,109,110];  // allowed keys
	    
	    var allowedCharCodesArr = [65,97];  // allowed Char keys
	    
	    if(key =='-' || key =='.' || $.isNumeric( key ) || $.inArray(event.keyCode,allowedKeyCodesArr) != -1 || $.inArray(event.charCode,allowedCharCodesArr) != -1)
    	{
    		return true;
    	}
	    else
    	{
	    	 event.preventDefault();
		     return false;
    	}
	});
	
	
	/* $('.charCheckining').bind('keypress', function (event) {
	    var regex = new RegExp("^[aA0-9\b]+$");
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key)) {
	       event.preventDefault();
	       return false;
	    }
	}); */
	
	}); 
</script>