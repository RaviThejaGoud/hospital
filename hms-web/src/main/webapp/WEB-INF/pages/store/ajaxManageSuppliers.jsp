<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Supplier Information
				</div>
				
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">				
					<ul class="nav nav-tabs">					
					<s:if test='%{user.isStoreKeeper !="Y"}'>			
					     <li>
							<s:url id="doAddSupplier" action="ajaxAddSuppliers" includeParams="all" escapeAmp="false" namespace="/store">
								<s:param name="supplier.id" value="0" />
							</s:url>
									<sj:a href="%{doAddSupplier}"  targets="storeContentsDiv" data-toggle="tab">
											Add Supplier </sj:a>						
						</li>	
					</s:if>							
						<li class="active">
							<s:url id="viewSupplierData" action="ajaxManageSuppliers" namespace="/store">
							</s:url>
							<sj:a id="viewSupplier" href="%{viewSupplierData}" targets="mainContentDiv" data-toggle="tab">View Supplier Details</sj:a> 	
						</li>
						
					</ul>
				
					<div id="storeContentsDiv" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<jsp:include page="/WEB-INF/pages/store/ajaxViewEditSupplierDetails.jsp"/> 							
					</div>
				</div>
			</div>
		</div>
	</div>
</div> 
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Manage Suppliers");
});
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 30, function() {
    $(this).animate({ scrollTop: 0 }, 30);
});

</script>
