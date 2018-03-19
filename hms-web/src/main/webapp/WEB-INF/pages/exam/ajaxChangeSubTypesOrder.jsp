<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div id="examSubTypeDiv">
	<h4 class="bold pageTitle">
		Change subtypes order
	</h4>
	<p>
	<span class="label label-danger">NOTE :</span>&nbsp; You can change subtypes order click on the subtype and drag and drop into respective position.</p>
	<div class="form-body"></div>
	<s:if test="%{subTypesList != null && !subTypesList.isEmpty()}">
		<div class="dd-handle">
			<div class="col-md-4">
				<strong>Order</strong>
			</div>
			<div class="col-md-4">
				<strong>Subtype</strong>
			</div>
		</div>
		<ul class="sortable" style="padding-left:0px;">
			<s:iterator value="subTypesList">
				<div class="dd-handle">
					<li class='ui-state-default<s:property value="id"/> col-md-13' id='<s:property value="id" />'>
						<div class="col-md-4">
							<s:property value="sortingOrder" />
						</div>
						<div class="col-md-4">
							<s:property value="subTypeName" />
						</div>
					</li>
				</div>
			</s:iterator>
		</ul>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You have not created any subtypes. You can able to create subtypes
			through Add Subtype link.
		</div>
	</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Change Subtypes Order")
	$(".sortable").sortable( {
		revert : true,
		stop : function(event, ui) {
		var jsonObj = [];
		var subTypeId = '';
		var sortingOrder = '';
			$(".sortable li").each(function(i) {
				subTypeId=$(this).attr("id");
				sortingOrder=(i+1);
				if(isNonEmpty(subTypeId)){
					jsonObj.push( {
								"subTypeId" : subTypeId,
								"sortingOrder" : sortingOrder
							});	
				}
			});
		var subtypeData=JSON.stringify(jsonObj);
		if(isNonEmpty(subtypeData)){
			var url = jQuery.url.getChatURL("/exam/ajaxUpdateSubTypesOrder.do?anyTitle="+subtypeData);
			$('#examSubTypeDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
				url : url,
				success : function(html) {
				    $('#examSubTypeDiv').html(html);
					}
				});		
			}
		}
	});
});

</script>
