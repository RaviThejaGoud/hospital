<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList!= null && !objectList.isEmpty()}">
	<p>
		<span class="label label-danger"> NOTE : </span>&nbsp;You can
		edit/update existing mess settings by clicking on edit pen icon in
		each row at right side.
	</p>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Merchant Name
				</th>
				<th>
					Mobile Number
				</th>
				<th>
					Address
				</th>
				<th>
					Edit
				</th>
				<th>
					Delete
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="objectList">
				<tr>
					<td>
						<s:property value="merchantName" />
					</td>
					<td>
						<s:property value="mobileNumber" />
					</td>
					<td>
						<s:property value="merchantAddress.strAddress" />
					</td>
					<td>
						<s:url id="editMerchantDetails" action="ajaxDoAddMerchant" includeParams="all" escapeAmp="false" namespace="/hostel">
							<s:param name="merchant.id" value="%{id}" />
						</s:url>
						<sj:a href="%{editMerchantDetails}" targets="messSettingContent"  cssClass="btn btn-xs purple" title="Edit"><i class="fa fa-edit"></i>Edit
						</sj:a>
					</td>
					<td>
						<s:if
							test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
							<s:url id="removeMessMerchant" action="ajaxDeleteMerchant"
								includeParams="all" escapeAmp="false" namespace="/hostel">
								<s:param name="tempId" value="%{id}"></s:param>
							</s:url>
							<s:div cssClass="btn btn-xs red" id='%{removeMessMerchant}' theme="simple"
								onclick="javascript:confirmDialogWithTarget(this,'messSettingContent');"
								title="Delete this mess">
								<i class="fa fa-times"></i>Delete</s:div>
						</s:if>
					</td>
					
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no merchants.
	</div>
</s:else>

<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	UIExtendedModals.init();
});
changePageTitle("Merchant Details");
</script>