<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Library Settings
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<s:if test='%{#session.previousYear == "N"}'>
						<ul class="nav nav-tabs">
							<s:if test='%{objectList.size<1}'>
								<li>
									<s:url id="urlSchoolWideBooksLink"
										action="ajaxDoAddLibrarySettings" namespace="/library" />
									<sj:a href="%{urlSchoolWideBooksLink}"
										targets="commonTabContent" 
										data-toggle="tab">Add Library Settings</sj:a>
								</li>
								<li class="active">
									<s:url id="urlCreateSchoolTermsLink"
										action="ajaxViewLibrarySettings" escapeAmp="false"
										includeParams="all" namespace="/library"/>
									<sj:a href="%{urlCreateSchoolTermsLink}"
										targets="mainContentDiv" 
										data-toggle="tab">
										 View</sj:a>
								</li>
							</s:if>
						</ul>
						<div id="commonTabContent" class="tab-content">
							<%@ include file="/common/messages.jsp"%>
							<jsp:include
								page="/WEB-INF/pages/library/librarySettings/ajaxViewStudyLibraryBooks.jsp"></jsp:include>
						</div>
					</s:if>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Books");
});
</script>
