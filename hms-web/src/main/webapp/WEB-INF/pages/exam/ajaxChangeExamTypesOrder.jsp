<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div id="examTypeContentDiv">
	<s:if test="%{examTypeList != null && !examTypeList.isEmpty()}">
		<h4 class="bold pageTitle">
			Change exam types order
		</h4>
		<p>
		<span class="label label-danger">NOTE :</span>&nbsp; You can change exam types order click on the exam type and drag and drop into respective position.</p>
		<div class="form-body"></div>
		<div class="dd-handle">
			<div class="col-md-4">
				<strong>Order</strong>
			</div>
			<div class="col-md-4">
				<strong>Exam Type</strong>
			</div>
		</div>
		<ul class="sortable" style="padding-left: 0px;">
			<s:iterator value="examTypeList">
				<div class="dd-handle">
					<li class='ui-state-default<s:property value="id"/> col-md-13' id='<s:property value="id" />'>
						<div class="col-md-4">
							<s:property value="sortingOrder" />
						</div>
						<div class="col-md-5">
							<s:property value="examType" />
						</div>
					</li>
				</div>
			</s:iterator>
		</ul>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You have not created any Exam Types. You can able to create Exam
			types through Add Exam Types link.
		</div>
	</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Change Exam Types Order");
	$(".sortable").sortable( {
		revert : true,
		stop : function(event, ui) {
		var jsonObj = [];
		var examTypeId = '';
		var sortingOrder = '';
			$(".sortable li").each(function(i) {
				examTypeId=$(this).attr("id");
				sortingOrder=(i+1);
				if(isNonEmpty(examTypeId)){
					jsonObj.push( {
								"examTypeId" : examTypeId,
								"sortingOrder" : sortingOrder
							});	
				}
			});
		var anyTitle=JSON.stringify(jsonObj);
		if(isNonEmpty(anyTitle)){
			var url = jQuery.url.getChatURL("/exam/ajaxUpdateExamTypesOrder.do?anyTitle="+anyTitle);
			$('#examTypeContentDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
				url : url,
				success : function(html) {
				    $('#examTypeContentDiv').html(html);
					}
				});		
			}
		}
	});
});
</script>