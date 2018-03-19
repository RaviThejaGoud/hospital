<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">	
	<s:form id="storeitemDetailform" cssClass="form-horizontal"
		theme="simple">	
		<input type="hidden" name="selectedStore" value="selectedStore" id="selectedStore" />	
			<s:if test="%{itemsList != null && !itemsList.isEmpty()}">
			  <div id="storeItems" >		
				<table class="table table-striped table-bordered table-hover table-full-width" id="sample_3">
				<thead>
					<tr><th>Item Type</th>
					<th>Item Name </th>
					<th>Item Code </th>
					<th>Available Quantity / Total Quantity </th>
					<th>Edit</th>
					<th>Issue</th>
					<th>Delete</th>										
					</tr>
				</thead>
				<tbody >
					<s:iterator value="itemsList">
					<tr>
					  <td>
					   <s:property value="itemTypeName"/>
					   </td><td>
					   <s:property value="itemName"/> </td>
					   <td><s:property value="itemCode"/></td>
					   <td><s:property value="availableQuantity"/> / <s:property value="quantity"/>
					    <s:set var="avalue" value="availableQuantity" />
							
						</td>
					   <td>
						<s:if test='%{#session.previousYear == "N"}'>
							<a data-toggle="modal" href="#responsive2"
								class="btn btn-xs purple"
								onclick="javascript:popupUpdateItemInfo(<s:property value="%{itemId}" />);"><i
								class="fa fa-edit"></i>Edit </a>
						</s:if>
						</td>
						<td>
						 <s:if test='%{#session.previousYear == "N"}'>
								<a data-toggle="modal" href="#responsive2"
									class="btn btn-xs purple"
									onclick="javascript:popupIssueItem(<s:property value="%{itemId}"/>,'<s:property value="%{itemName}"/>',<s:property value="%{availableQuantity}"/>);"><i
									class="fa fa-edit"></i>Issue </a>
							</s:if>
						</td>
					
						<td>
						<s:if test='availableQuantity.equals(quantity)'>
							<s:url id="removeStore" action="ajaxDeleteItemFromStore"
								includeParams="all" escapeAmp="false" namespace="/store">
								<s:param name="itemId" value="%{itemId}" />
								<s:param name="storeId" value="%{storeId}" />
							</s:url>
							<s:div cssClass="btn btn-xs red"
								onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
								id='%{removeStore}' title="Delete this item">
								<i class="fa fa-times"></i>Delete
							</s:div>
						</s:if>
						<s:else>	
								Item issued.Can't be Delete.
						</s:else>
					 </td>
					</s:iterator>
				</tbody>
				</table>				
				</div>   
			</s:if>
			<s:else>			
				<div class="alert alert-info">There are no items found for the selected store.</div>
			</s:else>
	               
	</s:form>
</div>
<div id="responsive2"></div>

<script>
function popupUpdateItemInfo(id) {	
	var storeId = $('#storeslist').val();	
	var url = jQuery.url.getChatURL("/store/ajaxUpdateItemToStore.do");
	$('#responsive2')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "itemId=" + id ,
		success : function(html) {
			$("#responsive2").html(html);
		}
	});
}

function popupIssueItem(id,itemName,quantity){
	
	var storeId = $('#storeslist').val();
	var url = jQuery.url.getChatURL("/store/ajaxIssueItemFromStore.do");
	$('#responsive2')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : "itemId=" + id+ "&itemName="+ itemName + "&storeId="+ storeId +"&availableQuantity="+quantity,
		success : function(html) {
			$("#responsive2").html(html);
		}
	});
}
</script>