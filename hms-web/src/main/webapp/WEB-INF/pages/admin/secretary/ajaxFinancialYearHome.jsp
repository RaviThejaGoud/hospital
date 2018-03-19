<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
				<i class="fa fa-globe"></i>
					Financial Year Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						
					<li class="dropdown active">
							<a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#"> View Financial Year <b class="caret"></b>
							</a>
							<ul role="menu" class="dropdown-menu pull-right">
								<li class="active">
									<s:url id="doManageOverAllGrades"
										action="ajaxFinancialYearHome" includeParams="all"
										escapeAmp="false" namespace="/admin">
										<s:param name="tempString">overAllGrades</s:param>
									</s:url>
									<sj:a href="%{doManageOverAllGrades}" targets="mainContentDiv"
										data-toggle="tab">View Financial Year</sj:a>
								</li>
								<s:if test="%{financialYear == null ||  financialYear == ''}">
									<li>
										<s:url id="doAddNewOverAllGradesTypeList"
											action="ajaxDoCreateFinancialYear" includeParams="all" namespace="/admin"
											escapeAmp="false">
											<s:param name="overAllGrades.id" value="0"></s:param>
										</s:url>
										<sj:a href="%{doAddNewOverAllGradesTypeList}"
											indicator="indicator" targets="financialYearDivId"
											button="false" >Add Financial Year</sj:a>
									</li>
								</s:if>
									
							</ul>
						</li>
				</ul> 
			<div id="financialYearDivId" class="tab-content">
				<jsp:include page="/WEB-INF/pages/admin/secretary/ajaxViewFinancialYearDetails.jsp"></jsp:include>
			</div>
			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
		changePageTitle("Financial Year Details");
		
	});
</script>
