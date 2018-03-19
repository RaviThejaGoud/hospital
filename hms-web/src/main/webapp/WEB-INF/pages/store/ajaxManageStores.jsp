<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Stores
				</div>
				
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">				
					<ul class="nav nav-tabs">
					<s:if test='%{user.isStoreKeeper !="Y"}'>					
					   <li>
							<s:url id="doAddStore" action="ajaxDoAddStore" includeParams="all" escapeAmp="false" namespace="/store">
							 		<s:param name="store.id" value="0" />
							</s:url>
									<sj:a href="%{doAddStore}"  targets="storeContentsDiv" data-toggle="tab">
											Add Store </sj:a>	
														
						</li>
						</s:if>								
						<li class="active">
							<s:url id="viewStoreData" action="ajaxDoManageStores" namespace="/store">
							</s:url>
							<sj:a id="viewStore" href="%{viewStoreData}" targets="mainContentDiv" data-toggle="tab">View Store Details</sj:a> 	
						</li>
						
					</ul>
				
					<div id="storeContentsDiv" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<jsp:include page="/WEB-INF/pages/store/ajaxViewEditStoreDetails.jsp"/> 							
					</div>
				</div>
			</div>
		</div>
	</div>
</div> 
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Manage Stores");
});
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 30, function() {
    $(this).animate({ scrollTop: 0 }, 30);
});

</script>
