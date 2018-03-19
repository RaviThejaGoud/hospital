<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
<s:hidden name="tempString" cssClass='tempString' />

<div id="divId"> </div>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_editable_3">
		<thead>
			<tr>
			<th style="display: none;">
			    </th>
				<th>
					
				</th>
				<s:iterator value="objectList">
					<th>
						<s:hidden name="id" value="%{timePeriod}" id="%{id}"/>
						<s:property value="timePeriod" />
					</th>
				</s:iterator>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="tempList">
			<tr class="subjectClassMonths">
			<s:set var="weekdayId" value="%{id}"></s:set>
			
				<td style="display: none;">
					 <s:property value="sortingOrder"/>
				</td>
								
					<td id="<s:property value="studId"/>">
						<s:property value="dayName" />
					</td>
					<s:iterator value="objectList">
					
					<td
						id="subjMonth<s:property value="#weekdayId" /><s:property value="id"/>">
						
						<p id="<s:property value="#weekdayId" />SM<s:property value="id"/>"></p>
						<%-- <sj:textfield name="studentMarks" value="" id="%{weekdayId}SM%{id}"  cssClass="form-control input-small charCheckining" maxlength="3"></sj:textfield> --%>
					</td>
					</s:iterator>
				</tr>
				</s:iterator>
		</tbody>
	</table>
		
			</s:if>
			<br/>
			<s:else>
				<div class="alert alert-info">
					Currently Timetable is not generated for the selected class.
				</div>
			</s:else>
			<div id="responsive2"></div>
<script type="text/javascript">
//TableAdvanced.init();
TableEditable.init();
UIExtendedModals.init();
$(document).ready(function() {
	var studyClassId = $('#classSectionId').val();
	
	var dataURL = jQuery.url.getChatURL("/timeTable/ajaxGetStudyClassWiseTimetableData.do?studyClassId=" + studyClassId);
	$.ajax( {
				url : dataURL,
				cache : false,
				dataType : 'json',
				success : function(response) {
					if (response.studentMarksSettingsData) {
						//$('#divId').html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
						var data = response.studentMarksSettingsData;
						if (data.length > 0) {
							for ( var i = 0; i < data.length; i++) {
								$( 'tr.subjectClassMonths:visible') .each( function() {
									$(this) .find( "td#subjMonth"   + data[i].WEEKDAYID + data[i].TIMETABLEPERIODSID ) .each( function() {
										
										//$(this) .find('input[id^=' + data[i].TIMETABLEPERIODSID + 'SM' +  data[i].WEEKDAYID +']').val(data[i].INPUTVALUE);
										
										$(this) .find('p#' + data[i].WEEKDAYID + 'SM' +  data[i].TIMETABLEPERIODSID +'').html(data[i].INPUTVALUE);
									});
								});
							}
						}
						//$('#divId').html(null);
					}
				}
			});
	
	}); 
</script>