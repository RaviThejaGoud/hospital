<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
				<i class="fa fa-globe"></i>
					Perticular Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						
						<li class="dropdown">
						<a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#"> View
								Particular Types <b class="caret"></b>
							</a>
							<ul role="menu" class="dropdown-menu pull-right">
								<li>
									<s:url id="doManageGrades" action="ajaxViewParticualrTypes"
										includeParams="all" escapeAmp="false" namespace="/admin">
									</s:url>
									<sj:a href="%{doManageGrades}" targets="perticularDetailsDivId"
										data-toggle="tab">View Particular Type</sj:a>
								</li>
								<li>
									<s:url id="doAddNewGradesTypeList"
										action="ajaxDoCreateParticularType" includeParams="all" namespace="/admin"
										escapeAmp="false">
										<s:param name="schoolGrades.id" value="0"></s:param>
									</s:url>
									<sj:a href="%{doAddNewGradesTypeList}" indicator="indicator"
										targets="perticularDetailsDivId" button="false">Add Particular Type</sj:a>
								</li>	
							</ul>
						</li>
					<li class="dropdown active">
							<a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#"> View Particulars <b class="caret"></b>
							</a>
							<ul role="menu" class="dropdown-menu pull-right">
								<li class="active">
									<s:url id="doManageOverAllGrades"
										action="ajaxParticularDetailsHome" includeParams="all"
										escapeAmp="false" namespace="/admin">
										<s:param name="tempString">overAllGrades</s:param>
									</s:url>
									<sj:a href="%{doManageOverAllGrades}" targets="mainContentDiv"
										data-toggle="tab">View Particulars</sj:a>
								</li>
									<li>
										<s:url id="doAddNewOverAllGradesTypeList"
											action="ajaxDoCreateParticular" includeParams="all" namespace="/admin"
											escapeAmp="false">
											<s:param name="overAllGrades.id" value="0"></s:param>
										</s:url>
										<sj:a href="%{doAddNewOverAllGradesTypeList}"
											indicator="indicator" targets="perticularDetailsDivId"
											button="false" >Add Particulars</sj:a>
									</li>
							</ul>
						</li>
				</ul> 
			<div id="perticularDetailsDivId" class="tab-content">
				<jsp:include page="/WEB-INF/pages/admin/secretary/ajaxViewPerticularDetails.jsp"></jsp:include>
			</div>
			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
		changePageTitle("Add Perticular Types");
		 $('.js-activated').dropdownHover().dropdown();
		$('.blockHeader h2').html('Manage Academics');
		// alert(getUrlVars()["id"]);
	});
</script>
