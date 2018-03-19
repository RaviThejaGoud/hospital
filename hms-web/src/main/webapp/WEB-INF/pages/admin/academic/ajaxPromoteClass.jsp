<%@ include file="/common/taglibs.jsp"%>
<div class="grid_12 omega">
	<div class="block_head">
		<h2>
			Promote Class
		</h2>
		<div id="topMenu">
			<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
			<ul id="topMenu">
				<li>
					<s:url id="urlDoCreatePromoteClass"
						action="ajaxDoCreatePromoteClass" />
					<sj:a id="cretePromoteClass" href="%{urlDoCreatePromoteClass}"
						targets="classPromoteContent" indicator="indicator">Create Promote Class</sj:a>
				</li>
			</ul>
			</s:if>
		</div>
	</div>
	<div class="block_content" id="classPromoteContent" >
		<jsp:include page="/WEB-INF/pages/admin/academic/ajaxViewPromoteClass.jsp"></jsp:include>
	</div>
</div>
<script type="text/javascript">
changePageTitle('Promote Class');
</script>

