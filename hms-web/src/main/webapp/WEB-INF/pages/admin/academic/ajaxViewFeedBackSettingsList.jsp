<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Feedback Settings
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear=="N"}'>
							<s:if test="%{objectList.size() == 0}">
								<li>
									<s:url id="doAddFeedBackSettings"
										action="ajaxDoAddNewFeedbackSettings" includeParams="all"
										escapeAmp="false">
										<s:param name="feedBackSettingId" value="0" />
										<s:param name="academicYearId"
											value="%{#session.academicYear}" />
									</s:url>
									<sj:a href="%{doAddFeedBackSettings}"
										targets="stepStaffFeedBackQues" data-toggle="tab">Add Feedback Settings</sj:a>
								</li>
							</s:if>
						</s:if>
						<li class="active">
							<s:url id="viewSettings" action="ajaxGetFeedbackSettings"
								namespace="/admin">
							</s:url>
							<sj:a id="viewSettings" href="%{viewSettings}"
								targets="mainContentDiv" data-toggle="tab">View Settings</sj:a>
						</li>
					</ul>
					<div id="stepStaffFeedBackQues" class="tab-content">
					<%@ include file="/common/messages.jsp"%>
						<s:if test="%{academicYear != null && academicYear != empty}">
							<s:if test="%{objectList != null && !objectList.isEmpty()}">
								<p>
								<span class="label label-danger"> NOTE : </span>&nbsp; You can
								edit and delete only non completed feedback settings.You can't
								edit or delete completed feedback settings.
								</p>
								<table
									class="table table-striped table-bordered table-hover table-full-width"
									id="sample_2">
									<thead>
										<tr>
											<th>
												Attendance %
											</th>
											<th>
												<b>From</b>
											</th>
											<th>
												<b>To</b>
											</th>
											<th>
												Edit
											</th>
											<th>
												Delete
											</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="objectList">
											<tr>
												<td>
													<s:property value="attendancePercentage" />
												</td>
												<td>
													<s:property value="startDateStr" />
												</td>
												<td>
													<s:property value="endDateStr" />
												</td>
												<td>
													<s:if test='%{#session.previousYear == "N"}'>
														<a data-toggle="modal" href="#popUpEditFeddBackDiv"
															class="btn btn-xs purple"
															onclick="javascript:popUpEditFeddBackDetails(<s:property value="%{id}" />,<s:property value="%{academicYear.id}" />);"><i
															class="fa fa-edit"></i>Edit </a>
													</s:if>
												</td>
												<td>
													<s:if test='%{#session.previousYear == "N"}'>
														<s:url id="removeStudySubject"
															action="ajaxDeleteFeedBackSetting" includeParams="all"
															escapeAmp="false" namespace="/admin">
															<s:param name="feedBackSettingId" value="%{id}"></s:param>
															<s:param name="academicYearId" value="%{academicYear.id}"></s:param>
														</s:url>
														<s:div cssClass="btn btn-xs red"
															onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
															id='%{removeStudySubject}' title="Delete this subject">
															<i class="fa fa-times"></i>Delete
														</s:div>
													</s:if>
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</s:if>
							<s:else>
								<div class="alert alert-info">
									currently there are no feedback settings.
								</div>
							</s:else>
						</s:if>
						<s:else>
							<div class="alert alert-info">
								Please add academic planner details.Then only you can add or
								view school holidays.
								<a
									href='${pageContext.request.contextPath}/admin/schoolSettings.do'>Click
									here</a> to add academic planner details.
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="popUpEditFeddBackDiv"></div>
<script type="text/javascript">
changePageTitle('Manage School Holidays');
function popUpEditFeddBackDetails(feedBackSettingId, academicYearId) {
	var url = jQuery.url.getChatURL("/admin/ajaxDoEditFeedBackSettings.do");
	$('#popUpEditFeddBackDiv')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "feedBackSettingId=" + feedBackSettingId + "&academicYearId=" + academicYearId,
		success : function(html) {
			$("#popUpEditFeddBackDiv").html(html);
		}
	});
}
</script>
