<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">	
	<s:form id="storeitemDetailform" cssClass="form-horizontal"
		theme="simple">	
		<input type="hidden" name="selectedStore" value="selectedStore" id="selectedStore" />	
		<s:if test="%{storeDataList != null && !storeDataList.isEmpty()}">
			<p class="text-primary">
				<b>Note : Choose store to view the available items.</b>
			</p>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<tbody>	
					<tr>
						<td>
						<input type="hidden" id="yearvalue" value="${session.previousYear}" />
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label col-md-4">
										<span class="required">*</span>Available Stores :
									</label>
									<div class="col-md-7">
								
									  <s:select id="storeslist" list="storeDataList" label="storeList"
											cssClass="required form-control input-medium" listKey="id"
											listValue="storeName" headerKey="" headerValue="- Select -"
											name="storeName" onchange="getIssuedItems(this.value)"
											/>									
									</div>
								 </div>
							 </div>
						</div>			
						</td>							
					</tr>
						
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">Currently there are no stores available.</div>
		</s:else>
	</s:form>
</div>
<div id="issuedItems"></div>
<script>
function getIssuedItems(storeId){	
	var url = jQuery.url.getChatURL("/store/ajaxGetIssuedItemsDetails.do");
	
		
	$.ajax( {
		url : url,
		cache : false,	
		data :"store.id=" + storeId,
		success : function(html) {
			$("#issuedItems").html(html);
		}
		});
}
</script>