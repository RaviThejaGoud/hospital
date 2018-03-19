<%@ include file="/common/taglibs.jsp"%>
<div class="block grid_11">
       <div class="block_head">
		<div class="header">
				Add Book Details
			<ul>
				<li>
					<a href="${pageContext.request.contextPath}/library/libraryHome.do" id="libraryHome">Back To Book Details</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="block_content">
	<s:form id="addCastFormDetails" action="ajaxAddSchoolWideBooks" method="post" theme="css_xhtml" namespace="/admin">
	<div class="grid_6">
			<s:select id="state" list="statesList" label="State"
						cssClass="required" required="true" listKey="stateCode"
						listValue="stateName" headerKey="" headerValue="- Select -"
						name="onlineApplicationDetailsView.state" />
		</div>
		<div class="grid_6">
			<sj:textfield name="bookTitle.bookName" id="bookName" label="Caste Name"
				required="true" cssClass="required textfield small" cssStyle="width:270px;" maxlength="60"></sj:textfield>
				<span style="position: relative; top: -5px; font-size: 0.8em;">(OC , BC-A , BC-B , SE , ST...)</span>
		</div>
		<div class="grid_11">
			<div class="grid_4" style="float: left;">
				 <s:url id="urlGetCastSettings"
					action="ajaxCastSettingsHome" namespace="/admin"/>
				<sj:a id="castDetails" href="%{urlGetCastSettings}"
					targets="admissionContentDetails" indicator="indicator" >Cancel</sj:a>
				<sj:submit   cssClass="submit small" formIds="addCastFormDetails"
					value="Submit" indicator="indicator" targets="studentLibraryContent"
					onClickTopics="formValidationForAddCast" />
			</div>
		</div>
	</s:form>
</div>
</div>
<style type="text/css">
	.searchDiv {
		background: #ddd;
		float: left; 
		padding: 10px;
		margin-bottom: 10px;
		margin-top: 10px;
		width: 580px;
	}
</style>
<script type="text/javascript">
	changePageTitle("Add School Wide Book");
	$(document).ready(function() {
		$.subscribe('formValidationForAddCast', function(event, data) {
			if ($('#addCastFormDetails').valid())
				return true;
			else
				return false;
		});
	$('.numeric').numeric();
	});
</script>
