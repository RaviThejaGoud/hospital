<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="grid_12 omega">
	<div class="block_head">
		<h2>
			My Subjects
		</h2>
		<div id="topMenu">
			<ul>
				<li>
					<s:url id="urlLeaveLink" action="ajaxDoStaffSubjects" />
					<sj:a href="%{urlLeaveLink}" targets="staffSelectedSubjects"
						indicator="indicator">Add Subject</sj:a>
				</li>
			</ul>
		</div>
	</div>
	<div class="block_content" id="staffSelectedSubjects">
		<jsp:include page="/WEB-INF/pages/admin/academic/ajaxStaffSubjectsList.jsp" />
	</div>
</div>
<script type="text/javascript">
	changePageTitle("Add Subjects");
</script>