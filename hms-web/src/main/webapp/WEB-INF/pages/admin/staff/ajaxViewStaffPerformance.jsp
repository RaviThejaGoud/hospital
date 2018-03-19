<%@ include file="/common/taglibs.jsp"%>
<s:if test='%{user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
 <div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Performance
				</div>
			</div>
			<div class="portlet-body">
			<div class="tabbable tabbable-custom tabbable-full-width">
				<ul class="nav nav-tabs">
					<li>
						<span id='<s:property value="staff.id"/>' class="staffId"></span>
						<s:url id="urlSubjectsComparison"
							action="ajaxMySubjectsComparison" namespace="/staff"
							escapeAmp="false" includeParams="all">
							<s:param name="staff.id" value="%{staff.id}"></s:param>
						</s:url>
						<sj:a href="%{urlSubjectsComparison}" targets="StaffPerformance"
							indicator="indicator" data-toggle="tab">Subjects Comparison</sj:a>
					</li>
					<li class="active">
						<s:url id="viewPerformance" action="ajaxStaffPerformance"
							namespace="/admin">
						</s:url>
						<sj:a id="viewPerformance" href="%{viewPerformance}"
							targets="mainContentDiv" data-toggle="tab">View Performance</sj:a>
					</li>
				</ul>
				<div class="tab-content" id="StaffPerformance">
					<jsp:include page="/WEB-INF/pages/staff/staffPerformance.jsp" />
				</div>
			</div>
		</div>
		</div>
	</div>
</div>
</s:if>
<s:if test='%{user.isOnlySchoolAdmin == "Y"}'>
	<div class="row">
		<div class="col-md-12">
			<span id='<s:property value="staff.id"/>' class="staffId"></span>
			<s:url id="urlSubjectsComparison" action="ajaxMySubjectsComparison"
				namespace="/staff" escapeAmp="false" includeParams="all">
				<s:param name="staff.id" value="%{staff.id}"></s:param>
			</s:url>
			<sj:a href="%{urlSubjectsComparison}" targets="staffEditContentDiv"
				cssClass="btn green" cssStyle="float:right;">View Subjects Comparison</sj:a>
		</div>
	</div>
	<div class="spaceDiv"></div>
	<jsp:include page="/WEB-INF/pages/staff/staffPerformance.jsp" />
</s:if>
