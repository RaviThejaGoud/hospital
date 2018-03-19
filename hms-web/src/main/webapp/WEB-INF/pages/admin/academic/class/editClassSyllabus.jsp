<%@ include file="/common/taglibs.jsp"%>
<div class="left examTabBorder">
	<s:form id="editClassSyllabus" action="ajaxEditClassSyllabus"
		method="post" theme="css_xhtml" enctype="multipart/form-data">
		<s:hidden name="classId" value="%{studyClass.id}"/>
		<s:hidden name="academicYearId" value="%{studyClass.academicYear.id}"/>
		<h1>
			Update Class Syllabus 
		</h1>
		<p>
			Upload syllabus sheet, if syllabus is already available for this class it will be removed and new syllabus will be added.
		</p>
			<s:if test="%{syllabusList != null && !syllabusList.isEmpty()}">
				<div class="grid_14 header">
					<div class="grid_3">
						Chapter Name
					</div>
					<div class="grid_2">
						Contents
					</div>
					<div class="grid_2">
						Book Title
					</div>
					<div class="grid_2">
						Publisher Name
					</div>
					<div class="grid_2">
						Author Name
					</div>
					<div class="grid_2">
						Reference Name
					</div>
				</div>
				<div id="syllabusPaginator">
					<s:iterator value="syllabusList">
						<div class="grid_13 normal">
							<div class="grid_3">
								<s:property value="chapterName" />
							</div>
							<div class="grid_2">
								<s:property value="contentName" />
							</div>
							<div class="grid_2">
								<s:property value="bookTitle" />
							</div>
							<div class="grid_2">
								<s:property value="publisherName" />
							</div>
							<div class="grid_2">
								<s:property value="autherName" />
							</div>
							<div class="grid_2">
								<s:property value="referenceName" />
							</div>
						</div>
					</s:iterator>
				</div>
			</s:if>
			<s:else>
				<div>
					<b> No syllabus added for this class.</b>
				</div>
			</s:else>
			<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
				<div class="link grid_13 right">
					<h1><a href="${pageContext.request.contextPath}/admin/doGenerateSubjectSyllabus.do?classId=<s:property value='studyClass.id'/>">
					Download Syllabus Template </a></h1>
				</div>
				<div class="grid_13">
				
					<div class="grid_13">
						<label class="labelRight">
							Upload Syllabus:
						</label>
						<s:file name="upload" theme="simple" id="photoURL" required="true"
							cssClass="text small required"></s:file>
					</div>
					<div class="grid_13">&nbsp;</div>
					<div class="grid_6">
						<sj:submit   targets="classSyllabusContent" value="Submit"
							formIds="editClassSyllabus" cssClass="submit emsRemove" validate="true"
							indicator="indicator2" onClickTopics="editClassSyllabusValidation" />
					</div>
				</div>
			</s:if>
	</s:form>
	<div class="clear"></div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/paginator.js">
</script>
<script type="text/javascript">
$(document)
		.ready(
				function() {
					$("#syllabusPaginator").pagination();
					$
							.subscribe(
									'editClassSyllabusValidation',
									function(event, data) {
											var r = confirm("New syllabus upload would replaces the existing syllabus.");
											if (r == true) {
												//alert("Uploading data would replace the existing syllabus");
												return true;
											} else {
												//alert("You pressed Cancel!");
												return false;
											}
									});
				});
</script>
