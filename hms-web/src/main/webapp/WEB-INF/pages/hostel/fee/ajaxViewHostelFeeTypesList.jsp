<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jQuery/jquerySession.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="grid_14 commomnTabs">
	<div class="grid_14 subMenus">
		<ul>
			<li class="active" style="background-image: none;">
				<s:url id="doLoadManageCategory" action="ajaxLoadManageInfoByFee"
					includeParams="all" escapeAmp="false" namespace="/hostel">
					<s:param name="anyTitle">category</s:param>
				</s:url>
				<sj:a href="%{doLoadManageCategory}" targets="feeSettingContent">Manage Category</sj:a>
			</li>
			<li>
				<s:url id="doLoadManageParticulars" action="ajaxLoadManageInfoByFee"
					includeParams="all" escapeAmp="false" namespace="/hostel">
					<s:param name="anyTitle">particulars</s:param>
				</s:url>
				<sj:a href="%{doLoadManageParticulars}" targets="feeSettingContent">Manage Fee Particulars</sj:a>
			</li>
			<li>
				<s:url id="doLoadManageTerm" action="ajaxLoadManageInfoByFee"
					includeParams="all" escapeAmp="false" namespace="/hostel">
					<s:param name="anyTitle">terms</s:param>
				</s:url>
				<sj:a href="%{doLoadManageTerm}" targets="feeSettingContent">Manage Fee Term</sj:a>
			</li>
		</ul>
	</div>
	<div id="feeSettingContent" class="grid_14">
		<jsp:include
			page="/WEB-INF/pages/hostel/fee/ajaxHostelFeeSettings.jsp"></jsp:include>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Hostel Fee & Terms");
	$('.blockHeader h2').html('Manage Hostel');
});
</script>