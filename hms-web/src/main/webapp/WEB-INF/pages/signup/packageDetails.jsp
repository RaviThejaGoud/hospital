<%@ include file="/common/taglibs.jsp"%>

<head>
	<title>Eazy School SIGN UP (Step 1 of 2)</title>
</head>
<div id="signupStep1" class="wrapper">
	<div class="block grid_14">
		<div class="block_head">
			<h2>
				School packages
			</h2>
		</div>
		<!-- .block_head ends -->
		<div class="block_content" id="signupContent">
			<s:form action="ajaxGetPackageDetails" id="pakageForm" method="post"
				theme="simple">
				<div class="grid_12">
					<s:if
						test="%{packageDetailsList != null && !packageDetailsList.isEmpty()}">
						<span class="required">*</span>Students Range(Upto):
					<div>
							<s:radio name="packageDetails_" cssClass="required"
								required="true" list="packageDetailsList" listKey="id"
								listValue="studentsRange" />
						</div>
						<div>
							<sj:submit   targets="signupContent" value="Next"
								cssClass="button2 submit small" formIds="pakageForm"
								onClickTopics="packageDetailsFormValidation" />
						</div>
					</s:if>
					<s:else>
				Currently there are no packages.
			</s:else>
				</div>
			</s:form>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	$.subscribe('packageDetailsFormValidation', function(event, data) {
		if ($('#pakageForm').valid()) {
			return true;
		} else
			return false;
	});
});
</script>