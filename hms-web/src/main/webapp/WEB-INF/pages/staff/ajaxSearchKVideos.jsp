<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div class="searchDiv">
	<s:form id="searchKVideosByKeywords"
		action="ajaxSearchKVideosByKeywords" theme="css_xhtml" namespace="/admin">
		<div style="float: right;">
			<s:url id="doCancelGroup" action="ajaxGetKhanPlayList"
				includeParams="all"></s:url>
			<sj:a href="%{doCancelGroup}" indicator="indicator"
				targets="kBankContent" button="false">Back</sj:a>
		</div>
		<div class="grid_4">
			<sj:textfield name="keyword" id="keyword"
				 value="Enter Keyword"
				cssClass="text small required" required="true"
				label="Search Knowledge Videos" 
				cssStyle="width: 165px;color:#ccc;text-align:center"></sj:textfield>
		</div>
		<sj:submit   targets="searchKVideosByKeywordssList" value="Find KVideos"
			cssClass="submit long" indicator="indicator"
			cssStyle="margin-top:30px"
			onClickTopics="searchKVideosByKeywordsForm"
			formIds="searchKVideosByKeywords" resetForm="true" />
	</s:form>
</div>
<div id="searchKVideosByKeywordssList" class="grid_11">
</div>
<script type="text/javascript">
changePageTitle('Search Knowledge Videos');
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
function clearTextField(field) {
	if (field.defaultValue == field.value)
		field.value = '';
	else if (field.value == '')
		field.value = field.defaultValue;

}
</script>