<%@ include file="/common/taglibs.jsp"%>
	<span class='scheduleDisplayType' id='<s:property value="anyTitle"/>'></span>
<s:if test="%{anyTitle == null  ||  anyTitle == ''}">
	<h4 class="pageTitle bold">
		Active Exam Schedules
	</h4>
	<div class="col-md-12">
		<p>
			<span class="label label-danger"> NOTE : </span>&nbsp;
			<s:if test="%{examScheduleList != null && !examScheduleList.isEmpty()}">
		 		These are the active exam schedules. You can view exam schedules by click on class name. 
		 	</s:if>
			You can visit the completed schedules at
			<s:url id="doViewArchiveSchedules"
				action="ajaxClassTeacherExamSchedules" includeParams="all"
				escapeAmp="false" namespace="/exam">
				<s:param name="anyTitle">completed</s:param>
			</s:url>
			<sj:a href="%{doViewArchiveSchedules}" targets="classContentDiv">
							Completed Schedules
			</sj:a>
		</p>
		
	</div>
</s:if>
<s:else>
		<h4 class="pageTitle bold">
			Completed Exam Schedules
		</h4>
		<div class="col-md-12">
			<p>
				<span class="label label-danger"> NOTE : </span>&nbsp;
			<s:if test="%{examScheduleList != null && !examScheduleList.isEmpty()}">
		 		These are the completed exam schedules.You can view exam schedules by click on class name.
		 	</s:if>
			You can visit the active schedules at
			<s:url id="doViewArchiveSchedules"
				action="ajaxClassTeacherExamSchedules" includeParams="all"
				escapeAmp="false" namespace="/exam">
				<s:param name="anyTitle" value=""></s:param>
			</s:url>
			<sj:a href="%{doViewArchiveSchedules}" indicator="indicator"
				targets="classContentDiv">
						Active Schedules
			</sj:a>
			</p>
			
		</div>
	</s:else>
	<div>&nbsp;</div>
	<s:if test="%{examScheduleList != null && !examScheduleList.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Class
					</th>
					<th>
						Exam Type
					</th>
					<th>
						Start Date
					</th>
					<th>
						End Date
					</th>
					<th>
						Marks Sheet 
					</th>
					<th>
					 	Schedule 
					</th>

				</tr>
			</thead>
			<tbody>
				<s:iterator value="examScheduleList">
					<tr>
						<td>
						<a data-toggle="modal" href="#examSchedulesDetialsDiv"
							onclick="javascript:PopupExanSchedulesDetials(<s:property value="%{classSectionId}" />,<s:property value="%{examTypeId}" />
								,'<s:property value="%{anyTitle}" />','<s:property value="%{examTypeName}" />'
								,'<s:property value="%{maxMarks}" />'
								,'<s:property value="%{classAndSection}" />');"><s:property value="classAndSection" />
						</a>
							<!--<s:url id="doViewExamSchedules" action="ajaxViewExamSchedules"
								includeParams="all" escapeAmp="false">
								<s:param name="classId" value="%{classSectionId}"></s:param>
								<s:param name="examType" value="%{examTypeId}"></s:param>
								<s:param name="anyTitle" value="%{anyTitle}"></s:param>
								<s:param name="examTypes.examType" value="%{examTypeName}"></s:param>
								<s:param name="examTypes.maxMarks" value="%{examType.maxMarks}"></s:param>
								<s:param name="studyClass.className" value="%{classAndSection}"></s:param>
							</s:url>
							<sj:a href="%{doViewExamSchedules}" targets="stepExamSchedules">
								<s:property value="classAndSection" />
							</sj:a>
						--></td>
						<td>
							<s:property value="examTypeName" />
						</td>
						<td>
							<s:property value="startDateStr" />
						</td>
						<td>
							<s:property value="endDateStr" />
						</td>
						<td>
							<s:if test='%{#session.previousYear=="N"}'>
								<a
									href='${pageContext.request.contextPath}/exam/ajaxDownloadExamsMarkSheet.do?anyId=<s:property value="examTypeId"/>&classId=<s:property value="classSectionId"/>&anyTitle=<s:property value="classAndSection"/>&tempString=<s:property value="examTypeName"/>&tempBoolean=<s:property value='tempBoolean'/> &selectedId=M'>
									Download </a>
							</s:if>
						</td>
						<td>
							<s:if test='%{(#session.previousYear=="N")}'>
								<a
									href="${pageContext.request.contextPath}/reports/printExamSchedules.do?examType=<s:property value='examTypeId'/>&classId=<s:property value='classSectionId'/>&section=<s:property value='classAndSection'/>&anyTitle=<s:property value='examTypeName'/>"
									><!-- <img alt="Print" title="Print"
										src="../images/common/printer.png"> --> Download </a>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You do not have any
			<s:if test="%{anyTitle == null  ||  anyTitle == ''}">
			active 
		</s:if>
			<s:else>
			archive
		</s:else>
			exam schedules
		</div>
	</s:else>
<div id="examSchedulesDetialsDiv"></div>
	<script type="text/javascript">
	
function PopupExanSchedulesDetials(id,examType,anyTitle,examTypes,maxMarks,studyClass) {
		var url = jQuery.url.getChatURL("/exam/ajaxViewExamSchedules.do");
		var pars=  "classId="+id+"&examType="+examType+"&anyTitle="+anyTitle+"&examTypes.examType="+examTypes+"&examTypes.maxMarks="+maxMarks+"&studyClass.className="+studyClass;
	//	alert(pars);
		$('#examSchedulesDetialsDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#examSchedulesDetialsDiv").html(html);
				}
			});
		} 
	
$(document).ready(function() {
	TableAdvanced.init();
	FormAdvanced.init();
});
</script>