<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Items
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">				
					<ul class="nav nav-tabs">					
					<li>
							<s:url id="doAddItem" action="ajaxAddItemToStore" includeParams="all" escapeAmp="false" namespace="/store"/>
									<sj:a href="%{doAddItem}"  targets="storeContentsDiv" data-toggle="tab">
											Add Item </sj:a>						
						</li>								
						<li class="nav nav-tabs">
							<s:url id="viewIssuedItemsData" action="ajaxIssuedItemsDetails" namespace="/store">
							</s:url>
							<sj:a id="issuedItem" href="%{viewIssuedItemsData}" targets="mainContentDiv" data-toggle="tab">View Issued Items</sj:a> 	
						</li>
						<li class="active">
							<s:url id="viewItemsData" action="ajaxManageStoreItems" namespace="/store">
							</s:url>
							<sj:a id="viewItem" href="%{viewItemsData}" targets="mainContentDiv" data-toggle="tab">View / Issue Items</sj:a> 	
						</li>
					</ul>
				
					<div id="storeContentsDiv" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<jsp:include page="/WEB-INF/pages/store/ajaxViewEditItemsDetails.jsp"/> 							
					</div>
				</div>
			</div>
		</div>
	</div>
</div> 
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Manage Items");
});
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 30, function() {
    $(this).animate({ scrollTop: 0 }, 30);
});

</script>
