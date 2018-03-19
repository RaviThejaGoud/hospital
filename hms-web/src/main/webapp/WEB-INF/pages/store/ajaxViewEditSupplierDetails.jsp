<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<s:form id="storedetailform" cssClass="form-horizontal" theme="simple">
		<s:if test="%{supplierDetailsList != null && !supplierDetailsList.isEmpty()}">
			<p class="text-primary">
				<b>Total Available Suppliers : <s:property
						value="supplierDetailsList.size" /></b>
			</p>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>Supplier Name</th>
						<th>Mobile Number</th>
						<th>Email Id</th>
						<th>Supplier Address</th>
						<s:if test='%{user.isStoreKeeper !="Y" }'>	
							<th>Edit</th>
							<th>Delete</th>
						</s:if>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="supplierDetailsList">
						<tr>
							<td><s:property value="supplierName" /></td>
							<td><s:property value="mobileNumber" /></td>
							<td><s:property value="email" /></td>
							<td><s:property	value="supplierAddress" />	</td>
							<s:if test='%{user.isStoreKeeper !="Y" }'>	
								<td>
									<a data-toggle="modal" href="#responsive2"
										class="btn btn-xs purple"
										onclick="javascript:PopupUpdateSupplierInfo(<s:property value="%{supplierId}" />);"><i
										class="fa fa-edit"></i>Edit </a>
								</td>
								<td>
									<s:if test='%{itemsSupplied == "N"}'>
										<s:url id="removeSupplier" action="ajaxRemoveSupplier"
											includeParams="all" escapeAmp="false" namespace="/store">
											<s:param name="supplier.id" value="%{supplierId}" />
										</s:url>
										<s:div cssClass="btn btn-xs red"
											onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
											id='%{removeSupplier}' title="Delete this supplier">
											<i class="fa fa-times"></i>Delete
										</s:div>
									</s:if>
									<s:else>
										Items Supplied. Can't be Delete.
									</s:else>
								</td>
							</s:if>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">Currently there are no suppliers
				available.</div>
		</s:else>
	</s:form>
</div>
<div id="responsive2"></div>
<script>
function PopupUpdateSupplierInfo(id) {
	var url = jQuery.url.getChatURL("/store/ajaxAddSuppliers.do");
	$('#responsive2')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "supplier.id=" + id,
		success : function(html) {
			$("#responsive2").html(html);
		}
	});
}

</script>