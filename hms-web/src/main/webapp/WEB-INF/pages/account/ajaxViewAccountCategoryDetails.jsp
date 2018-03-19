<%@ include file="/common/taglibs.jsp"%>
<div>
	  <s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>Account Type</th>
					<th>Statement Type</th>
					<th>Category Name</th>
					<th>Category Code</th>
					<s:if test='%{#session.previousYear=="N"}'>
						<th>Edit</th>
						<!-- <th>Delete</th> -->
					</s:if>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="tempList1">
					<tr>
						<td><s:property value="financialAccountType.accountType"/></td>
						<td>
							<s:if test="%{financialAccountType.id ==1}">
								<s:property value="financialAccountStatement.statementName"/></td>
							</s:if>
							<s:else>
								<canter>-</canter>
							</s:else>
							
						<td><s:property value="cartegoryName"/></td>
						<td>
							<s:if test="%{financialAccountType.id ==1}">
								<s:property value="financialAccountStatement.statmentCode" />
							</s:if>
							<s:else>
								<canter>-</canter>
							</s:else>
						</td>
						<s:if test='%{#session.previousYear=="N"}'>
						<td>
							<s:url id="editcartegoryName" action="ajaxDoAddCategories" includeParams="all" escapeAmp="false" namespace="/account">
								<s:param name="financialAccountCategoryVO.id" value="%{id}" />
							</s:url>
							<sj:a href="%{editcartegoryName}" 
								targets="accountMasterContentDiv" cssClass="btn btn-xs purple" title="Edit"><i class="fa fa-edit"></i>Edit
							</sj:a>
						</td>
						<%-- <td>
							<s:url id="removeCategory" action="ajaxRemoveCategory" includeParams="all"
								escapeAmp="false" namespace="/account">
								<s:param name="tempId" value="%{id}" />
							</s:url>
							<s:div cssClass="btn btn-xs red"
								onclick="javascript:confirmDialogWithTarget(this,'accountMasterContentDiv');"
								id='%{removeCategory}'
								title="Delete these combined periods"><i class="fa fa-times"></i>Delete
							</s:div>
						</td> --%>
					</s:if>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	 </s:if>
	<s:else>
		<div class="alert alert-info">Currently there are no account categories.</div>
	</s:else> 
</div>
<script type="text/javascript">
TableAdvanced.init();
 </script>