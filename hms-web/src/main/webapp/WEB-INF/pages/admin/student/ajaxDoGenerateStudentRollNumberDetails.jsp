<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Student Roll Numbers
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li class="active"><s:url
								action="ajaxDoGenerateStudentRollNumber"
								id="ajaxDoGenerateStudentRollNumber" namespace="/admin"
								includeParams="all" escapeAmp="false">
							</s:url> <sj:a href="%{ajaxDoGenerateStudentRollNumber}"
								targets="mainContentDiv">Manage Student Roll Numbers</sj:a></li>
					</ul>
					<div class="tab-content">
						<jsp:include page="/WEB-INF/pages/admin/student/ajaxDoGenerateStudentRollNumber.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>