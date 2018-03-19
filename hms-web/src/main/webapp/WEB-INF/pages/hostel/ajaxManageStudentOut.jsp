<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jQuery/jquerySession.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_14 commomnTabs">
	<div class="grid_14 subMenus">
	<ul>
		<li class="active" style="background-image: none;">
			<s:url id="doAddNewStudent"
				action="ajaxDoManageStudentOut" namespace="/hostel">
			</s:url>
			<sj:a href="%{doAddNewStudent}" indicator="indicator"
				targets="stepHostelStudentInAndOut" button="false">Student Out</sj:a>
		</li>
		<li>
			<s:url id="doAddNewStudentIn"action="ajaxSearchStudentIn" namespace="/hostel" >
			</s:url>
			<sj:a href="%{doAddNewStudentIn}" indicator="indicator"
				targets="stepHostelInOutRegister" button="false">Student In</sj:a>
		</li>
		<li>
			<s:url id="doAddNewStudent" action="ajaxSearchVisitorsIn" namespace="/hostel">
				<s:param name="tempString ">Visitors</s:param>
			</s:url>
			<sj:a href="%{doAddNewStudent}" indicator="indicator"
				targets="stepHostelInOutRegister" button="false">Visitors</sj:a>
		</li>
		<li>
			<s:url id="importStudentOutExcelSheet"
				action="ajaxDoImportStudentExcelSheet" namespace="/hostel" />
			<sj:a href="%{importStudentOutExcelSheet}"
				targets="stepHostelInOutRegister">Import/Export Existing Student Out</sj:a>
		</li>
	</ul>
</div>
<div id="stepHostelInOutRegister" class="grid_14">
		<jsp:include
			page="/WEB-INF/pages/hostel/ajaxViewStudentOutList.jsp"></jsp:include>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Hostel Student In & Out");
</script>
