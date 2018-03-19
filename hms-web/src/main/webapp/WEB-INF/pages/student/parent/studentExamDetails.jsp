<%@ include file="/common/taglibs.jsp"%>
<div class="block_head">
	<h2>
		Upcoming Schedules & My Children's Performance
	</h2>
</div>
<div class="block_content">
	<div id="commonTabContent" class="grid_11">
		<div id="commonTabWrapper">
			<div id="commonStep">
				<fieldset>
					<div class="grid_11">
						<s:if test="%{objectList != null && !objectList.isEmpty()}">
							<s:if test="%{objectList.size>1}">
								<div class="grid_4" style="text-align: left; margin-top: -15px;">
									<b> Child Name: </b>
									<br />
									<s:select id="sectionId" list="objectList" listKey="id"
										onchange="javascript:getMyChildExamView(this.value);"
										listValue="idAndName" label="Child Name"
										cssClass="textfield required" theme="simple" required="true"
										name="anyId" />
								</div>
							</s:if>
						</s:if>
						<s:else>
							<s:if
								test="%{examScheduleList != null && !examScheduleList.isEmpty()}">
								<s:iterator value="examScheduleList">
									<h3 style="margin-top: 0px;">
										<s:property value="examTypeId" />
										-
										<s:property value="classId" />
									</h3>
									<div>
										Start Date:
										<s:property value="startTime" />
									</div>
									<div>
										End Date:
										<s:property value="endTime" />
									</div>
								</s:iterator>
								<div>
									<a href="doStudentActivitiesHome.do">More Details</a>
								</div>
							</s:if>
							<s:else>
			`					Currently there are no up coming exam Schedules.
							</s:else>
						</s:else>
						<div style="padding-top: 1px; margin-left: 5px;" id="">
							<div id="myChildSchedules">
								<s:if
									test="%{examScheduleList != null && !examScheduleList.isEmpty()}">
									<s:iterator value="examScheduleList">
										<h3 style="margin-top: 0px;">
											<s:property value="classId.classAndSection" />
											:
											<s:property value="examType.examType" />
										</h3>
										<div>
											Start Date:
											<s:property value="startTime" />
										</div>
										<div>
											End Date:
											<s:property value="endTime" />
										</div>
									</s:iterator>
									<div>
										<a href="doStudentActivitiesHome.do">More Details</a>
									</div>
								</s:if>
								<s:else>
			Currently there are no up coming exam Schedules.
		</s:else>
							</div>
						</div>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
	<div class="grid_11">
	<jsp:include page="/WEB-INF/pages/student/parent/studentMarksDetails.jsp" />
	</div>
</div>
<script LANGUAGE="JavaScript">
function getMyChildExamView(studAccountId) {
	var studentAccountId = studAccountId;
	var str = "";
	var url = jQuery.url
			.getChatURL("/student/ajaxGetViewChildExamSchedules.do?accountId="
					+ studentAccountId);
	$.ajax( {
		url : url,
		cache : false,
		data : 'html',

		success : function(html) {
			$("#myChildSchedules").html(html);
			$("#myChildSchedules").show();
		}
	});
}
</script>
