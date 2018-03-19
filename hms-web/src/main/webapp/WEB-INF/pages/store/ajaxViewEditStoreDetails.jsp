<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">	
	<s:form id="storedetailform" cssClass="form-horizontal"
		theme="simple">
		<s:if test="%{storeBasicDetailsList != null && !storeBasicDetailsList.isEmpty()}">
			<p class="text-primary">
				<b>Total Available Stores : <s:property value="storeDataList.size" /></b>
			</p>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr><th>Store Name</th>
						<th>Store Address</th>
						<th>Store Keeper Name</th>
						<s:if test='%{user.isStoreKeeper !="Y"}'>
							<th>Edit</th>
							<th>Delete</th>
						</s:if>
					</tr>
				</thead>
				<tbody>					
					<s:iterator value="storeBasicDetailsList" >
						<tr>
							<td>
								<s:property value="storeName" />
							</td>
							 <td>							  
								<s:property value="storeAddress" />													
							</td>	 					
							<td >
								<s:property value="storeKeeperName" />  
							</td>
							<s:if test='%{user.isStoreKeeper !="Y"}'>
								<td>
										<a data-toggle="modal" href="#responsive2"
											class="btn btn-xs purple"
											onclick="javascript:PopupUpdateStoreInfo(<s:property value="%{storeId}" />);"><i
											class="fa fa-edit"></i>Edit </a>
									
							</td>
							<td>
								 <s:if test='%{ itemsAdded == "O"}'>
										<s:url id="removeStore" action="ajaxRemoveStore"
											includeParams="all" escapeAmp="false" namespace="/store">
											<s:param name="store.id" value="%{storeId}" />
										</s:url>
										<s:div cssClass="btn btn-xs red"
											onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
											id='%{removeStore}' title="Delete this store">
											<i class="fa fa-times"></i>Delete
										</s:div>						 
								</s:if>
							</td>
						</s:if>
					</tr>
				</s:iterator>
			</tbody>
		</table>
			<div>
				<p class="text-primary">
				Note : Delete button will be enabled when there are no items under the store.
				</p>
			</div>
		</s:if>
		<s:else>
			<div class="alert alert-info">Currently there are no stores available.</div>
		</s:else>
	</s:form>
	
	
	
</div>
<div id="responsive2"></div>
<script>
function PopupUpdateStoreInfo(id) {
	var url = jQuery.url.getChatURL("/store/ajaxDoAddStore.do");
	$('#responsive2')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "store.id=" + id,
		success : function(html) {
			$("#responsive2").html(html);
		}
	});
}

</script>