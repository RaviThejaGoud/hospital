<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">	
	<s:form id="storeitemDetailform" cssClass="form-horizontal"
		theme="simple">
		<s:if test="%{itemTypesList != null && !itemTypesList.isEmpty()}">
			<p class="text-primary">
				<b>Total Available Item Types : <s:property value="itemTypesList.size" /></b>
			</p>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr><th>Item Type Name </th>
						<th>Measurement Type</th>						
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>					
					<s:iterator value="itemTypesList" >
						<tr>
							<td>
								<s:property value="typeName" />
							</td>
							 <td >							  
							   <s:property value="measurementType"/>
							</td>	
							<td>
							<s:if test='%{#session.previousYear == "N"}'>
								<a data-toggle="modal" href="#responsive2"
									class="btn btn-xs purple"
									onclick="javascript:PopupUpdateItemTypeInfo(<s:property value="%{id}"/>)"><i
									class="fa fa-edit"></i>Edit </a>
							</s:if>
							</td>
					
						<td>
							<s:if test='%{#session.previousYear == "N"}'>							
								<s:url id="removeItemType" action="ajaxRemoveItem"
									includeParams="all" escapeAmp="false" namespace="/store">
									<s:param name="itemType.id" value="%{id}" />
								</s:url>
								<s:div cssClass="btn btn-xs red"
									onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
									id='%{removeItemType}' title="Delete this ItemType">
									<i class="fa fa-times"></i>Delete
								</s:div>							 
							</s:if>
						</td>
								
							
						</tr>
						
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">Currently there are no stores available.</div>
		</s:else>
	</s:form>
			
</div>
<div id="responsive2"></div>
<script>

function PopupUpdateItemTypeInfo(id) {	
	var url = jQuery.url.getChatURL("/store/ajaxEditUpdateItems.do");	
	$('#responsive2')
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,	
		data :"itemType.id=" + id,
		success : function(html) {
			$("#responsive2").html(html);
		}
	});
}

</script>