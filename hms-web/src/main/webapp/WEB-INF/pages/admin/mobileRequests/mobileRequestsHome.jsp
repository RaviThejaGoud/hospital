<%@ include file="/common/taglibs.jsp"%>
<s:if test='%{user.isOnlySchoolAdmin == "Y" || user.isSchoolPrincipal == "Y" || user.isSchoolDirector == "Y"}'>
<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>Mobile Requests
					</div>
				</div>
				<div class="portlet-body">
					<div class="tabbable tabbable-custom tabbable-full-width">
						<ul class="nav nav-tabs">
							<%-- <li>
								<s:url id="stepStaffLeavesRequest" action="ajaxViewStudentAndStaffLeaves" includeParams="all"
										escapeAmp="false" namespace="/staff">
										<s:param name="tempString">staff</s:param>
									</s:url>
								 <sj:a href="%{stepStaffLeavesRequest}" targets="stepStaffLeavesDiv"
										data-toggle="tab">Staff Leave Request</sj:a>
							</li>
							<li class="active">
								<s:url action="ajaxDoGetStaffAndStudenTLeaveReq"
									id="ajaxDoGetLeaveDetailsLeftLink" namespace="/staff"
									includeParams="all" escapeAmp="false">
									<s:param name="tempString">student</s:param>
								</s:url>
								<sj:a href="%{ajaxDoGetLeaveDetailsLeftLink}" targets="mainContentDiv"
								data-toggle="tab">Student Leave Request</sj:a>
							</li> --%>
						</ul>
						<div id="stepStaffLeavesDiv" class="tab-content">
							<jsp:include page="/WEB-INF/pages/admin/mobileRequests/ajaxViewMobileRequestsList.jsp" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</s:if>


<script type="text/javascript">
$(document).ready(function() {
	$('li#staffLeavesId a').click();
});
</script>

<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();
	var selected = $('input[name=SelectType]:radio:checked').val();
	//getAjaxDoGetStaffLeaves(selected);
});
function getAjaxDoGetStaffLeaves(selected) {
	if(isNonEmpty(selected) && selected!="undefined"){
	//alert(selected);
	var pars = "tempString=" + selected;
	$("#adminLeavesViewDiv")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/staff/ajaxViewStudentAndStaffLeaves.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#adminLeavesViewDiv").html(html);
		}
	});
	}
}
</script>
