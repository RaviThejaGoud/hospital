<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Promote Students
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="urlDoPromoteStudents" action="ajaxDoPromoteStudents"
								includeParams="all" escapeAmp="false" namespace="/admin">
								<s:param name="usrChgedAcademicId" value="%{usrChgedAcademicId}"></s:param>
							</s:url>
							<sj:a href="%{urlDoPromoteStudents}"
								targets="promoteStudentsContent" data-toggle="tab">Promote Students</sj:a>
						</li>
						<li class="active">
							<s:url id="urlPromoteStudents" action="ajaxManagePromoteStudents"
								namespace="/admin" />
							<sj:a href="%{urlPromoteStudents}" targets="mainContentDiv"
								data-toggle="tab">
							 Promote Settings</sj:a>
						</li>
					</ul>
					<div class="tab-content" id="promoteStudentsContent">
						<jsp:include
							page="/WEB-INF/pages/admin/academic/class/ajaxPromotionClassSettings.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Promote Students");
</script>