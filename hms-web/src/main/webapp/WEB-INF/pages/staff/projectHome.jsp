<%@ include file="/common/taglibs.jsp"%>
<title>Teacher | Class Exam Details</title>
<div class="block grid_12">
	<div class="block_head">
		<h2>
			My Projects
		</h2>
		<ul>
			<li>
				<s:url id="urlDoAddProject" action="ajaxDoAddProject" />
				<sj:a id="urlDoAddProject" href="%{urlDoAddProject}"
					targets="addProject" indicator="indicator">Add Projects</sj:a>
			</li>
		</ul>
	</div>
	<div class="block_content" id="addProject">
		<div>
			<jsp:include page="/WEB-INF/pages/staff/ajaxViewAddProject.jsp"></jsp:include>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Exam Schedules & Results");
</script>









