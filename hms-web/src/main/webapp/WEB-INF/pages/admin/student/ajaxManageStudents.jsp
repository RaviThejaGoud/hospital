<%@ include file="/common/taglibs.jsp"%>
<div class="block grid_14">
	<div class="block_head"  id="topMenu"style="width: 825px;">
		<h2>
			Manage Students
		</h2>
		<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
			<ul>
	       	<li>
				<s:url id="doAddStudent" action="ajaxDoAddStudent" includeParams="all" escapeAmp="false" namespace="/student"/>
				<sj:a href="%{doAddStudent}"  targets="studentsList">
						Add Student
				</sj:a>
			</li>
			<li>							
				<s:url id="importGroupExcelSheet" action="ajaxDoImportStudentExcelSheet" namespace="/student"/>
				<sj:a href="%{importGroupExcelSheet}" targets="studentsList">Import Students</sj:a>
			</li>
		</ul>
		</s:if>
	</div>
	<div class="block_content" id="studentsList" style="width: 825px;">
		<jsp:include page="/WEB-INF/pages/admin/student/ajaxViewStudentsList.jsp"/>
	</div>
</div>

