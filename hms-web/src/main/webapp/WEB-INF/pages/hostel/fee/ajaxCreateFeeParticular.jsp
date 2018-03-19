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
<div class="grid_6">
	<%@ include file="/common/messages.jsp"%>
	<s:form action="ajaxAddHostelFeeSettings" id="addFeeParticular"
		method="post" theme="css_xhtml" namespace="/hostel">
		<s:hidden name="hostelFeeType.id" value="%{hostelFeeType.id}"></s:hidden>
		<s:hidden name="anyTitle"></s:hidden>
		<h1>
			<s:if test="%{hostelFeeType.id  != 0}">
					Update Fee Particulars
				</s:if>
			<s:else>
					Create Fee Particulars
				</s:else>
		</h1>
		<h1>
		</h1>
		<p>
			Simple! just type Fee Particulars and hit submit.
		</p>
		<fieldset>
			<div class="grid_6">
				<div>
					<label class="grid_3">
						<span class="required">*</span>Create Fee Particular:
					</label>
				</div>
				<div class="grid_4">
					<sj:textfield name="hostelFeeType.hostelFeeType" required="true"
						id="hostelFeeType" cssClass="required textfield" maxlength="60"></sj:textfield>
				</div>
			</div>
			<div class="grid_5">
				<div class="grid_5">
					&nbsp;
				</div>
				<s:url id="doCancelFeeParticular" action="ajaxLoadManageInfoByFee"
					includeParams="all" escapeAmp="false" namespace="/hostel"></s:url>
				<sj:a href="%{doCancelFeeParticular}" cssClass="cancelButton"
					indicator="indicator" targets="feeSettingContent">Cancel</sj:a>
				<sj:submit   targets="feeSettingContent" value="Submit"
					resetForm="true" indicator="indicator" cssClass="submit small"
					validate="true" />
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Create Fee Particulars");
	$('.blockHeader h2').html('Manage Academics');

	$.subscribe('doInitAddFee', function(event, data) {
		$('#stepFees').show();
	});
});
$(document)
		.ready(
				function() {
					$("#hostelFeeType")
							.autoCheck(
									"${pageContext.request.contextPath}/hostel/ajaxCheckHostelFeeParticularType.do",
									{
										minChars : 3,
										min : "no"
									});
				});
</script>
