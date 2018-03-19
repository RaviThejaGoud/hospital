<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:form id="inActiveCustomerList" cssClass="form-horizontal"
	theme="simple">
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<h4 class="bold text-success">
			Total InActive Customers Count :
			<s:property value="objectList.size" /> ( National : <s:property value="tempId" /> , International: <s:property value="tempId1" /> ) 
		</h4>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th style="display:none;"></th>
					<th>Registration Date</th>
					<th>Cust Id</th>
					<th>Organization Name</th>
					<th>Email Id</th>
					<th>Contact Number</th>
					<!-- <th>Status</th> -->
					<th>Country</th>
					<th style="white-space:normal">Reason/Cause</th>
					<th>Active</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="objectList">
					<tr>
						<td style="display:none;"></td>	
						<td><s:date name="createdDate" format="dd/MMM/yyyy"/>
						<td><s:property value="id" /></td>
						<td><s:property value="organization" /></td>
						<td><s:property value="custEmail" /></td>
						<td><s:property value="contactNumber" /></td>
						<%-- <td><s:property value="customerStatus" /></td> --%>
						<td><s:property value="countryName" /></td>
						<td style="white-space:normal"><s:property value="customerInActiveDescription" /></td>
						<%-- <td><s:url id="enableCustomer"
								action="ajaxEnableCustomerfDetails" includeParams="all"
								escapeAmp="false" namespace="/masterAdmin">
								<s:param name="tempId" value="%{id}"></s:param>
							</s:url> <s:div id='%{enableCustomer}' theme="simple"
								title="Enable Customer" cssClass="btn btn-xs green"
								onclick="javascript:confirmDialogWithTarget(this,'contentDiv');">
								 Active
							</s:div>
						</td> --%>
						<td><a data-toggle="modal" href="#activeCustomerDiv"
							class="btn btn-xs green"
							onclick="javascript:popupActiveCustomer(<s:property value="%{id}" />);"> Active </a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">Currently there are no inactive
			Customer's.</div>
	</s:else>
</s:form>
<div id="activeCustomerDiv"></div>

<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	changePageTitle("Active Customer's");
});
function popupActiveCustomer(custId){
	if(isNonEmpty(custId)){
	  var pars = "tempId=" + custId;
        $.ajax( {
	        url : jQuery.url.getChatURL("/masterAdmin/ajaxDoEnableCustomer.do"),
			cache : true,
			data : pars,
			success : function(html) {
			$("#activeCustomerDiv").html(html);
			}
		});
	}
 }
</script>