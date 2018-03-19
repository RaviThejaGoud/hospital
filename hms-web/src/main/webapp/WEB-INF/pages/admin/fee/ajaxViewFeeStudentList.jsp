<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
	<div style="padding-top: 1px" id="editPaymentTypeForm">
		<table class="striped" width="100%" style="margin-bottom: 0;" cellpadding="1" cellspacing="1">
			<thead>
				<tr>
					<th width="25%">
						Roll Number
					</th>
					<th width="23%">
						Class
					</th>
					<th width="30%">
						Student Name
					</th>
					<th>
						Edit Payment Type
					</th>
				</tr>
			</thead>
		</table>
		<s:iterator value="studentsList">
			<table class="striped" width="100%" style="margin-bottom: 0;"
				cellpadding="1" cellspacing="1" id="submittingFee">
				<tr class='loaded'>
					<td width="25%">
						<s:url id="removeStudent" action="ajaxDeleteStudent"
							includeParams="all" escapeAmp="false" namespace="/admin">
							<s:param name="id" value="rollNumber"></s:param>
						</s:url>
						<s:div cssClass="close emsRemove" id='%{removeStudent}'
							theme="simple" title="Delete this Student Record"></s:div>
						<s:property value="rollNumber" />
					</td>
					<td width="23%">
						<s:property value="className" />
						-
						<s:property value="section" />
					</td>
					<td width="40%">
						<s:property value="firstName" />
						&nbsp;
						<s:property value="lastName" />
					</td>
					<td>
						<s:url id="editPaymentType"
							action="ajaxSearchStudentFeeByRollNumber" includeParams="all"
							escapeAmp="false">
							<s:param name="id" value="%{rollNumber}" />
						</s:url>
						<sj:a href="%{editPaymentType}" targets="editPaymentTypeForm%{id}"
							onBeforeTopics="cleanOpenRows"
							onCompleteTopics="doInitEditStudent" indicator="indicator"  cssClass="normalEdit" title="Edit">
						</sj:a>
					</td>
				</tr>
				<tr id="editPaymentTypeForm<s:property value='id' />" style="display: none;" class='load'></tr>
			</table>
		</s:iterator>
	</div>
</s:if>
<s:else>
	<div class="grid_9">
		There are no Students for this RollNumber.
	</div>
</s:else>
<script type="text/javascript">
	$.subscribe('doInitEditStudent', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
</script>