<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Reports
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						 <li class="active" id="ManageGovtReports">
							<s:url id="urlmanageReports" action="ajaxDoGetAllReports" namespace="/govt"/>
							<sj:a id="manageReports" href="%{urlmanageReports}"
								targets="reportsContent"  data-toggle="tab">Manage Reports</sj:a>
						</li>
						 <li>
							<s:url id="urlMyManageReports" action="ajaxGovtReports" namespace="/govt">
							</s:url>
							<sj:a id="viewManageReports" href="%{urlMyManageReports}" targets="mainContentDiv" data-toggle="tab">View My Reports</sj:a> 	
						 </li>
					</ul>
					<div id="reportsContent" class="tab-content">
					    <%@ include file="/common/messages.jsp"%>
						<jsp:include page="/WEB-INF/pages/govt/ajaxGetAllGovtReports.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$("li a#manageReports").click();
	changePageTitle("Manage Reports");
});
</script><!--


<div class="wrapper container_18">
	<div class="wrapper">
		<div class="grid_18 block grid_18MarginLeft">
			<div class="grid_4 alpha">
				<div class="block_head">
					<h2>
						
					</h2>
				</div>
				<div class="block_content" id="sideMenu">
					<ul>
						
						<%-- <li>
						<s:url id="urlMyReportsLink" action="ajaxDoGetAllReports" 
							includeParams="all" namespace="/govt">
						<s:param name="adminAtt" value="adminAtt" /> 
						</s:url>
							<sj:a href="%{urlMyReportsLink}"
								targets="reportsContent" indicator="indicator">My Reports</sj:a>
						</li> --%>
					</ul>
				</div>
			</div>
			
		</div>
	</div>
</div>
<script Language="Javascript1.2" type="text/javascript">
$(document).ready(
		function() {
			changePageTitle("Manage Reports");
			$("a#manageReports").click();
		});
</script>-->