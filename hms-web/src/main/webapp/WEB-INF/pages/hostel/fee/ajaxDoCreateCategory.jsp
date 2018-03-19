<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/jQuery/jquerySession.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js">
</script>
<div class="grid_7">
	<s:form action="ajaxAddHostelCategory" id="addHostelCategory"
		method="post" theme="css_xhtml" namespace="/hostel">
		<s:hidden name="hostelCategory.id" value="%{HostelCategory.id}"></s:hidden>
		<s:hidden name="anyTitle"></s:hidden>
		<h1>
			<s:if test="%{hostelCategory.id  != 0}">
				Update Category
			</s:if>
			<s:else>
				Create Category
			</s:else>
		</h1>
		<p>
			This is used to determine the discount options like employee,
			religious etc, for the fees.
		</p>
		<fieldset>
			<div class="grid_6">
				<div>
					<label class="grid_3">
						<span class="required">*</span>Category Name:
					</label>
				</div>
				<div class="grid_4">
					<sj:textfield name="hostelCategory.categoryName" required="true"
						id="categoryName" cssClass="required textfield" maxlength="60"></sj:textfield>
				</div>
			</div>
			<div class="grid_6">
				<div class="grid_5">
					&nbsp;
				</div>
				<s:url id="doCancelHostelCategory"
					action="ajaxViewHostelFeeSettings" includeParams="all" namespace="/hostel"></s:url>
				<sj:a href="%{doCancelHostelCategory}" cssClass="cancelButton"
					indicator="indicator" targets="stepHostelFees">Cancel</sj:a>
				<sj:submit   targets="feeSettingContent" value="Submit"
					resetForm="true" indicator="indicator" cssClass="submit small"
					validate="true" />
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Create Category");
	$('.blockHeader h2').html('Manage Academics');
});
$.subscribe('doInitAddFee', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});
function CancelFeeSettings() {
	$('.cancelButton').targets('');
}
$(document)
		.ready(
				function() {
					$("#categoryName")
							.autoCheck(
									"${pageContext.request.contextPath}/hostel/ajaxCheckHostelFeeCategoryType.do",
									{
										minChars : 2,
										min : "no"
									});
				});
</script>