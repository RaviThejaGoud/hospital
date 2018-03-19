<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_14 commomnTabs">
	<div class="grid_14 subMenus">
		<ul>
			<li>
				<s:url id="importStudentsExcelSheet"
					action="ajaxDoImportStudentExcelSheet" includeParams="all"
					escapeAmp="false" namespace="/hostel">
				</s:url>
				<sj:a href="%{importStudentsExcelSheet}"
					targets="stepHostelInOutRegister">Student Out</sj:a>
			</li>
			<li class="active" style="background-image: none;">
				<s:url id="importStudentExcelSheet"
					action="ajaxDoImportStudentInExcelSheet" includeParams="all"
					escapeAmp="false" namespace="/hostel">
				</s:url>
				<sj:a href="%{importStudentExcelSheet}"
					targets="stepHostelStudentInAndOut">Student In</sj:a>
			</li>
		</ul>
	</div>
	<s:form action="ajaxUploadHostelStudentInData"
		id="importStudentExcelSheet" namespace="/hostel" method="post"
		theme="css_xhtml" enctype="multipart/form-data" name="">
		<%@ include file="/common/messages.jsp"%>
		<fieldset>
			<h1>
				Download Student-In Details
			</h1>
			<p>
				If you do not have a Student-Out template (xls), please download,
				add Students to sheet then import the sheet. System can't process
				any records, if the data is not in specified format.
			</p>
			<div class="grid_12 right">
				<a class="linkRight" rel="nofollow"
					href='<c:url value='/hostel/ajaxDownloadStudentInFiles.do'/>'>
					Download Student-In Template (Excel Sheet)</a>
				<br />
			</div>
			<div class="grid_7">
				<div class="grid_7">
					<label class="large">
						<span class="required">*</span>Select Students-In Template (Excel)
					</label>
				</div>
				<div class="grid_5">
					<s:file name="upload" id="photoURL" required="true"
						requiredposition="first" cssClass="textfield required">
					</s:file>
				</div>
				<div class="grid_2">
					<sj:submit   targets="stepHostelInOutRegister" value="Submit"
						formIds="importStudentExcelSheet" indicator="indicator"
						cssClass="submit small" validate="true" />
				</div>
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
changePageTitle('Import Staff Template');
</script>


