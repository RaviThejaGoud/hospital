<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<span class='scheduleDisplayType' id='<s:property value="anyTitle"/>'></span>
<s:if test="%{anyTitle == null  ||  anyTitle == ''}">
	<h4 class="pageTitle bold">
		Active exam schedules
	</h4>
	<div class="panel-body col-md-12">
		<div class="col-md-1">
			<span class="label label-danger"> NOTE : </span>
		</div>
		<div class="col-md-10">
			<ul>
				<li>
					The following are the active exam schedules. You can view detailed
					exam schedule by click on the class name.
				</li>
				<li>
					You can see the completed exam schedules at 
					<s:url id="doViewArchiveSchedules" action="ajaxDoExamShedules"
						includeParams="all" escapeAmp="false" namespace="/exam">
						<s:param name="anyTitle">completed</s:param>
					</s:url>
					<sj:a href="%{doViewArchiveSchedules}" indicator="indicator"
						targets="mainContentDiv">
						Completed Exam Schedules
					</sj:a>
				</li>
				<li>
					Click download link to download the respective class exam schedule
				</li>
			</ul>
		</div>
	</div>
	<div>&nbsp;</div>
</s:if>
<s:else>
	<h4 class="pageTitle bold">
		Completed exam schedules
	</h4>
	<div class="panel-body col-md-12">
		<div class="col-md-1">
			<span class="label label-danger"> NOTE : </span>
		</div>
		<div class="col-md-10">
			<ul>
				<li>
					The following are the completed exam schedules. You can view detailed
					exam schedule by click on the class name.
				</li>
				<li>
					You can see the active exam schedules at 
					<s:url id="doViewArchiveSchedules" action="ajaxDoExamShedules"
						includeParams="all" escapeAmp="false" namespace="/exam">
						<s:param name="anyTitle" value=""></s:param>
					</s:url>
					<sj:a href="%{doViewArchiveSchedules}" indicator="indicator"
						targets="mainContentDiv">
			Active Exam Schedules
		</sj:a>
				</li>
				<li>
					Click download link to download the respective class exam schedule
				</li>
			</ul>
		</div>
	</div>
	<div>&nbsp;</div>
</s:else>
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
				<s:if test="%{anyTitle == null  ||  anyTitle == ''}">
					<th>
						Marks Sheet
					</th>
				</s:if>
				<s:else>
					<th>
						Marks Sheet
					</th>
				</s:else>
				<th>
					Schedule
				</th>
				<s:if test="%{anyTitle == null  ||  anyTitle == ''}">
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</s:if>
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
								,'<s:property value="%{classAndSection}" />');"><s:property
								value="classAndSection" /> </a>
					</td>
					<td>
						<s:property value="examTypeName" />
					</td>
					<td>
						<s:property value="startDateStr" />
					</td>
					<td>
						<s:property value="endDateStr" />
					</td>
					<s:if test="%{anyTitle == null  ||  anyTitle == ''}">
						<td>
					</s:if>
					<s:else>
						<td>
					</s:else>
					<s:if test='%{#session.previousYear=="N"}'>
						<a
							href='${pageContext.request.contextPath}/exam/ajaxDownloadExamsMarkSheet.do?anyId=<s:property value="examTypeId"/>&classId=<s:property value="classSectionId"/>&anyTitle=<s:property value="classAndSection"/>&tempString=<s:property value="examTypeName"/>&selectedId=M'>
							Download </a>
					</s:if>
					<s:else>
						     	&nbsp;
						    </s:else>
					<s:if test='%{(#session.previousYear=="N")}'>
						<td>
							<a
								href="${pageContext.request.contextPath}/reports/printExamSchedules.do?examType=<s:property value='examTypeId'/>&classId=<s:property value='classSectionId'/>&section=<s:property value='classAndSection'/>&anyTitle=<s:property value='examTypeName'/>"
								>Download</a>
						</td>
						<s:if test="%{anyTitle == null  ||  anyTitle == ''}">
							<td>
								<s:if test='%{#session.previousYear=="N"}'>
									<s:url id="doEditExamTypes" action="ajaxDoAddExamSchedules"
										includeParams="all" escapeAmp="false" namespace="/exam">
										<s:param name="classId" value="%{classSectionId}"></s:param>
										<s:param name="examType" value="%{examTypeId}"></s:param>
									</s:url>
									<sj:a href="%{doEditExamTypes}" targets="classContentDiv"
										cssClass="btn btn-xs purple">
										<i class="fa fa-edit"></i>Edit
									</sj:a>
								</s:if>
							</td>
							<td>
								<a id="removeExamSchedule<s:property value="classSectionId"/>"
									onclick="javascript:doGetExamScheduleMarks(<s:property value="classSectionId"/>,<s:property value='examTypeId'/>,'<s:property value="examTypeName"/>','<s:property value="classAndSection"/>');"
									class="btn btn-xs red"> <i class="fa fa-times"></i>Delete </a>
							</td>
						</s:if>
					</s:if>
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
			completed
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
function doGetExamScheduleMarks(classSectionId, eId, eTypeName, className) {
	var url = jQuery.url
			.getChatURL("/exam/ajaxGetExamscheduleMarks.do?classId="
					+ classSectionId + "&examType=" + eId);
	var displayScheduletype = $('span.scheduleDisplayType').attr('id');
	$
			.ajax( {
				url : url,
				cache : false,
				dataType : 'json',
				success : function(response) {
					var data = response.marksAvailable;
					var answer = '';
					if (isNonEmpty(data)) {
						answer = confirm("" + data);
					} else {
						var answer = confirm("Are you sure you want to delete ?");
						if (answer) {
							var pars = "classSectionId="
									+ classSectionId
									+ "&examType="
									+ eId
									+ "&studyClass.className=&examTypes.examType=&anyTitle="
									+ displayScheduletype;
							var url = jQuery.url
									.getChatURL("/exam/ajaxDeleteExamScheduleMarks.do");
							$.ajax( {
								url : url,
								cache : false,
								data : pars,
								success : function(responce) {
									$("#mainContentDiv").html(responce);
								}
							});
							return true;
						} else {
							return false;
						}
					}
					if (isNonEmpty(answer)) {
						if (!answer) {
							$('#removeExamSchedule' + id).attr("href",
									"javascript:void(0);");
						} else {
							var pars = "classSectionId=" + classSectionId
									+ "&examType=" + eId
									+ "&studyClass.className=" + className
									+ "&examTypes.examType=" + eTypeName
									+ "&anyTitle=" + displayScheduletype;
							var url = jQuery.url
									.getChatURL("/exam/ajaxDeleteExamScheduleMarks.do");
							$.ajax( {
								url : url,
								cache : false,
								data : pars,
								success : function(responce) {
									$("#mainContentDiv").html(responce);
								}
							});
						}
					}
				}
			});
}
</script>
