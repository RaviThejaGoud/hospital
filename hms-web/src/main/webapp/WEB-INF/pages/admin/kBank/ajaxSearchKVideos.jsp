<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="searchDiv commomnTabs">
	<s:form id="searchKVideosByKeywords"
		action="ajaxSearchKVideosByKeywords" theme="simple" namespace="/admin"
		cssClass="form-horizontal">
		<div class="form-group">
			<label class="col-md-2 control-label">
				<span class="required">*</span>Search KVideos :
			</label>
			<div class="col-md-4">
				<div class="input-group">
					<sj:textfield name="keyword" id="keyword"
						value="Please enter keyword"
						cssClass="form-control required defaultValue" />
					<span class="input-group-btn"> <sj:submit  
							targets="searchKVideosByKeywordssList" value="Find KVideos"
							cssClass="blue btn" onBeforeTopics="searchKVideosByKeywordsForm"
							validate="true" formIds="searchKVideosByKeywords"
							resetForm="true" /> </span>
				</div>
				<span class="hintMessage">(Key at least 3 chars and hit
					submit to get closer match.)</span>
			</div>
		</div>
	</s:form>

</div>

<div id="searchKVideosByKeywordssList">
</div>
<script type="text/javascript">
changePageTitle('Search KVideos');
$(document).ready(
		function() {
			$.subscribe('searchKVideosByKeywordsForm', function(event, data) {
				var keyword = $('#keyword').val();
				if (keyword == null || keyword == ''|| keyword == 'Please enter keyword') {
					alert("Please Enter Keyword");
					event.originalEvent.options.submit=false;
			} else if (keyword.length < 3) {
				alert("Please enter minimum 3 characters.");
				$('#keyword').val('Please enter keyword');
				event.originalEvent.options.submit=false;
			}  
			return true;
			});
		});
</script>
