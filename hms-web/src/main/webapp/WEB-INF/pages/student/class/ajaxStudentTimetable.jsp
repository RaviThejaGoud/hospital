<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>My Timetable
				</div>
			</div>
			<div class="portlet-body">
				<div id="studentContent" class="tab-content">
					<s:if test="%{user.parent}">
						<div id="studentsListContent">
							<%-- <jsp:include page="/WEB-INF/pages/student/class/ajaxMyChildList.jsp"></jsp:include> --%>
							
							<s:if test='%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}'>
								<div class="row">
									<div class="form-group form-horizontal" align="center">
										<label class="control-label col-md-3">
											Student Name :
										</label>
										<div class="col-md-3">
											<s:select id="classSectionId" list="viewStudentPersonAccountDetailsList"
												listKey="classSectionId" label="Student Name" cssClass="form-control"
												listValue="idAndName" name="tempId1" theme="simple"
												onchange="javascript:getAjaxDoGetStudyClassTimetable(this.value);" />
										</div>
									</div>
								</div>
							</s:if>
							<%-- <s:if test='%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}'>
	
								<div class="row">
									<div class="col-md-10">
										<div class="form-group">
											<label class="col-md-3 control-label"> Select Child : </label>
											<div class="col-md-9">
												<div class="clearfix">
													<s:radio list="viewStudentPersonAccountDetailsList" listKey="classSectionId" theme="ems" cssClass="syllabsMultiOption" listValue="idAndName" name="syllabusId" id="classSectionId" onclick="getAjaxDoGetStudyClassTimetable(this.value)"/>
												</div>
											</div>
										</div>
									</div>
								</div>
							</s:if> --%>
						</div>
					</s:if>
					<s:else>
						<s:hidden name="viewStudentPersonAccountDetails.classSectionId" id="classSectionId"></s:hidden>
					</s:else>
							
					<div class="spaceDiv"></div>
					<div id="viewTimetableDivId">
						<jsp:include page="/WEB-INF/pages/admin/academic/timeTable/ajaxViewStudyclassWiseTimetable.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script language="JavaScript" type="text/javascript">


function getAjaxDoGetStudyClassTimetable(studyClassId) {
  	if (studyClassId == "") {
		alert("Please select the class.")
	} else {
		var pars = "studyClassId=" + studyClassId;
		$("#viewTimetableDivId")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/timeTable/ajaxViewStudyclassWiseTimetable.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#viewTimetableDivId").html(html);
			}
		});
	}
}
		
</script>