<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="searchDiv">

	<s:form id="searchKVideosByKeywords"
		action="ajaxSearchKVideosByKeywords" theme="css_xhtml">
		<div class="grid_11">
			<div class="grid_3">
				<b class="labelRight">Search Knowledge Videos:</b>
			</div>
			<div class="grid_4">
				<sj:textfield name="keyword" id="keyword"
					value="Plese enter keyword" onfocus="clearText(this)"
					cssClass="textfield large required defaultValue" required="true"
					onblur="clearText(this)" />
			</div>
			<div class="grid_4">
				<sj:submit   targets="searchKVideosByKeywordssList"
					value="Find KVideos" cssClass="submit long" indicator="indicator"
					onClickTopics="searchKVideosByKeywordsForm" validate="true"
					formIds="searchKVideosByKeywords" resetForm="true" />
			</div>
		</div>
	</s:form>
</div>
<div id="searchKVideosByKeywordssList" class="grid_11">
</div>
<script type="text/javascript">
changePageTitle('Search KVideos');
$(document).ready(function() {
	$.subscribe('searchKVideosByKeywordsForm', function(event, data) {
		var keyword = $('#keyword').val();
		if (keyword == null || keyword == '' || keyword == 'Enter Keyword') {
			alert("Please Enter Keyword");
			return false;
		} else
			return true;
	});
	$.subscribe('doInitEditStudent', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
});
</script>