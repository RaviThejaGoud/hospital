<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Download / Upload Marks Sheet
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li id="downloadMarkSheet">
							<s:url id="urlDoAddMarks" action="ajaxDoAddMarks"
								includeParams="all" escapeAmp="false" namespace="/exam">
							</s:url>
							<sj:a href="%{urlDoAddMarks}"
								targets="urlStudentMarksSheetDiv" data-toggle="tab">Add Marks</sj:a>
						</li>
						
						<li id="downloadMarkSheet">
							<s:url id="urlDownloadMarksSheet" action="ajaxDownloadMarksSheet"
								includeParams="all" escapeAmp="false" namespace="/exam">
							</s:url>
							<sj:a href="%{urlDownloadMarksSheet}"
								targets="urlStudentMarksSheetDiv" data-toggle="tab">Download Marks Sheet</sj:a>
						</li>
						<li class="active">
								<s:url id="urlStudentMarksSheet" action="ajaxManageStudentMarks"
									namespace="/exam" />
								<sj:a href="%{urlStudentMarksSheet}"
									targets="mainContentDiv" data-toggle="tab">
								 Upload Marks Sheet</sj:a>
							</li>
					</ul>
					<div class="tab-content" id="urlStudentMarksSheetDiv">
						<jsp:include
							page="/WEB-INF/pages/exam/admin/ajaxUploadMarksSheet.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Download / Upload Marks Sheet
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
				<ul class="nav nav-tabs">
				<li id="downloadMarkSheet">
					<s:url id="urlDownloadMarksSheet" action="ajaxDownloadMarksSheet" includeParams="all"
					escapeAmp="false">
				</s:url>
				<sj:a href="%{urlDownloadMarksSheet}" 
					indicator="indicator" targets="studentMarksContent" data-toggle="tab">Download Marks Sheet</sj:a>
				</li>
				<li id="downloadMarkSheet" class="active">
					<s:url id="urlDownloadMarksSheet" action="ajaxDownloadMarksSheet" includeParams="all"
					escapeAmp="false">
				</s:url>
				<sj:a href="%{urlDownloadMarksSheet}" 
					indicator="indicator" targets="studentMarksContent"  data-toggle="tab">Download Marks Sheet</sj:a>
				</li>
			</ul>
	<div class="tab-content" id="studentMarksContent">
		<jsp:include page="/WEB-INF/pages/exam/admin/ajaxUploadMarksSheet.jsp" />
	</div></div>
</div></div></div></div>
--><script type="text/javascript">
$(document).ready(function() {
changePageTitle('Download / Upload Marks Sheet');
});
</script>