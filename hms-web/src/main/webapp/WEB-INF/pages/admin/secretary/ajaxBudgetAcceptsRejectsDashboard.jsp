<%@ include file="/common/taglibs.jsp"%>
<div class="portlet box blue">
	<div class="portlet-title">
		<div class="caption"><i class="fa fa-globe"></i>Budget Accepts / Rejects</div>
	</div>
	<div class="portlet-body">
	<%@ include file="/common/messages.jsp"%>
	<s:if test="%{messList!= null && !messList.isEmpty()}">	
		<table class="table table-striped table-bordered table-hover table-full-width" id="sample_4">
			<thead>
				<tr>
					<th>Date</th>
					<th>Requested Month</th>
					<th>School Name</th>
					<th>Total</th>
					<th>Manager</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
		
			<s:iterator value="messList">
				<tr>
					<td><s:property value="userFormattedCreatedDate"/></td>
					<td><s:property value="monthName"/></td>
					<td><s:property value="organization"/></td>
					<td><i class="fa fa-rupee"></i><s:property value="totalBudgetAmount"/></td>
					<td><s:property value="fullName"/></td>
					<td><s:property value="budgetStatusDesc"/></td>
					
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