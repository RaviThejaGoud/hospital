<%@ include file="/common/taglibs.jsp"%>
<div class="portlet box blue">
	<div class="portlet-title">
		<div class="caption"><i class="fa fa-globe"></i>Budget Request from managers</div>
	</div>
	<div class="portlet-body">
	<s:if test="%{tempList1!= null && !tempList1.isEmpty()}">	
		<table class="table table-striped table-bordered table-hover table-full-width" id="sample_3">
			<thead>
				<tr>
					<th>Request Date</th>
					<th>Requested</th>
					<th>School</th>
					<th>Total</th>
					<th>Manager</th>
					<th>Mobile</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="tempList1">
				<tr>
					<td><s:property value="userFormattedCreatedDate"/></td>
					<td><s:property value="monthName"/></td>
					<td><s:property value="organization"/></td>
					<td><i class="fa fa-rupee"></i><s:property value="totalBudgetAmount"/></td>
					<td><s:property value="fullName"/></td>
					
					<td><s:property value="mobileNumber"/></td>
					<td>
						<s:url id="urlAcceptOrRejectLinks" action="ajaxViewBudgetDetailsBySecratary"
							namespace="/admin" escapeAmp="false" includeParams="all">
							<s:param name="budgetRequest.id" value="%{budgetRequestId}" />
							<s:param name="tempString">dashboard</s:param>
						</s:url>
						<sj:a href="%{urlAcceptOrRejectLinks}" targets="mainContentDiv"
							cssClass="ajaxify btn btn-xs yellow"><i class="fa fa-file-o"></i>View</sj:a>
				    </td>
				</tr>
			</s:iterator>
				
			</tbody>
		</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				No budget requests for this financial year.
			</div>
		</s:else>
	</div>
</div>	