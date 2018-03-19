<%@ include file="/common/taglibs.jsp"%>
<div class="classContentDiv">
<%@ include file="/common/messages.jsp"%>
	<s:if test="%{classList != null && !classList.isEmpty()}">
	<h4 class="bold pageTitle">
		Change classes order
	</h4>
	<p>
	<span class="label label-danger">NOTE :</span>&nbsp;You can change the class order by click on class name and Drag and drop in respective position.</p>
	<div class="spaceDiv"></div>
		<div class="dd-handle">
			<div class="col-md-6">
				<strong>Order</strong>
			</div>
			<div class="col-md-6">
				<strong>Class Name</strong>
			</div>
		</div>
		<ul class="sortable" style="padding-left:0px;">
			<s:iterator value="classList">
				<div class="dd-handle">
					<li class='ui-state-default<s:property value="id"/> col-md-11' id='<s:property value="id" />'>
						<div class="col-md-7">
							<s:property value="sortingOrder" />
						</div>
						<div class="col-md-5">
							<s:property value="className" />
						</div>
					</li>
				</div>
			</s:iterator>
		</ul>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You have not created any Classes. You can able to create Class &amp;
			Section through Add Class link.
		</div>
	</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Change Classes Order");
	$(".sortable").sortable( {
		revert : true,
		stop : function(event, ui) {
		var jsonObj = [];
		var classId = '';
		var sortingOrder = '';
			$(".sortable li").each(function(i) {
				classId=$(this).attr("id");
				sortingOrder=(i+1);
				if(isNonEmpty(classId)){
					jsonObj.push( {
								"classId" : classId,
								"sortingOrder" : sortingOrder
							});	
				}
			});
		var tempString=JSON.stringify(jsonObj);
		if(isNonEmpty(tempString)){
			var url = jQuery.url.getChatURL("/admin/ajaxUpdateClassesOrder.do?tempString="+tempString);
			$('#classContentDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
				url : url,
				data : "tempString"+tempString,
				success : function(html) {
				    $('#classContentDiv').html(html);
					}
				});		
			}
		}
	});
});

</script>