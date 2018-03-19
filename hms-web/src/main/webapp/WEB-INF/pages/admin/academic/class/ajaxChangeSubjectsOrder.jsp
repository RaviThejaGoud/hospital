<%@ include file="/common/taglibs.jsp"%>
<div class="subjectsContentDiv">
<%@ include file="/common/messages.jsp"%>
		<h4 class="bold pageTitle">
			Change subjects order
		</h4>
	<p> <span class="label label-danger">NOTE :</span>&nbsp;You can change the subject order by click on the subject name and drag and drop to respective position.
	<div class="spaceDiv"></div>
	<s:if test="%{studySubjectList != null && !studySubjectList.isEmpty()}">
		<div class="dd-handle">
			<div class="col-md-4">
				<strong>Order</strong>
			</div>
			<div class="col-md-4">
				<strong>Subject Name</strong>
			</div>
			<div class="col-md-4">
				<strong>Subject Code</strong>
			</div>
		</div>
		<ul class="sortable" style="padding-left:0px;">
			<s:iterator value="studySubjectList">
				<div class="dd-handle">
					<li class='ui-state-default<s:property value="id"/> col-md-13' id='<s:property value="id" />'>
						<div class="col-md-4">
							<s:property value="sortingOrder" />
						</div>
						<div class="col-md-4">
							<s:property value="name" />
						</div>
						<div class="col-md-4">
							<s:property value="subjectNumber" />
						</div>
					</li>
				</div>
			</s:iterator>
		</ul>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You have not created any Subjects. You can able to create subject through Add Subject link.
		</div>
	</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
changePageTitle("Change Subjects Order");
	$(".sortable").sortable( {
		revert : true,
		stop : function(event, ui) {
		var jsonObj = [];
		var subId = '';
		var sortingOrder = '';
			$(".sortable li").each(function(i) {
				subId=$(this).attr("id");
				sortingOrder=(i+1);
				if(isNonEmpty(subId)){
					jsonObj.push( {
						"subId" : subId,
						"sortingOrder" : sortingOrder
					});	
				}
			});
		var tempString=JSON.stringify(jsonObj);
		if(isNonEmpty(tempString)){
			var url = jQuery.url.getChatURL("/admin/ajaxUpdateSubjectsOrder.do?tempString="+tempString);
			$('#subjectsContentDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
				url : url,
				success : function(html) {
				    $('#subjectsContentDiv').html(html);
					}
				});		
			}
		}
	});
});

</script>