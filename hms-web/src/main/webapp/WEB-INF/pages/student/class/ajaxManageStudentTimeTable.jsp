<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Timetable
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear == "N"}'>
							<s:if test='%{!anyTitle.isEmpty()}'>
								<li>
									<s:if test='%{user.isParent=="Y"}'>
										<a href="../<s:property value="anyTitle" />"
											target="_new">My Children Timetable </a>									
									</s:if>
									<s:else>
										<a href="../<s:property value="anyTitle" />"
											target="_new">My Timetable </a>
									</s:else>
								</li>
							</s:if>
							<li class="active">
								<s:url id="studentTimeTable" action="ajaxViewStudentTimeTable"
									namespace="/student">
								</s:url>
								<sj:a href="%{studentTimeTable}" targets="mainContentDiv"
									data-toggle="tab">
										View Weekly Timetable</sj:a>
							</li>
						</s:if>
					</ul>
					<div id="studentTimeTableContent" class="tab-content">
						<jsp:include
							page="/WEB-INF/pages/student/class/ajaxViewStudentTimeTable.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
