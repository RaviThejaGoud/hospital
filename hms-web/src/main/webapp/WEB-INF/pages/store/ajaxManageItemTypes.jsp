<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Item Types
				</div>
				
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">				
					<ul class="nav nav-tabs">
					<s:if test='%{user.isStoreKeeper !="Y"}'>			
						<li>		
						 	<s:url id="doAddItemType" action="ajaxDoAddItemType" includeParams="all" escapeAmp="false" namespace="/store"/>
										<sj:a href="%{doAddItemType}"  targets="storeContentsDiv" data-toggle="tab">
												Add Item Type </sj:a>				
						</li>
					</s:if>	
					<li class="active">
							<s:url id="doViewItemType" action="ajaxViewItemTypes" includeParams="all" escapeAmp="false" namespace="/store"/>
									<sj:a href="%{doViewItemType}"  targets="mainContentDiv" data-toggle="tab">
											View Item Type </sj:a>						
						</li>		
					</ul>
				
					<div id="storeContentsDiv" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<jsp:include page="/WEB-INF/pages/store/ajaxViewEditItemTypeDetails.jsp"/> 							
					</div>
				</div>
			</div>
		</div>
	</div>
</div> 
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Manage Item Types");
});
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 30, function() {
    $(this).animate({ scrollTop: 0 }, 30);
});

</script>
