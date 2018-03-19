<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Provision Store
				</div>
			</div>
			<div class="portlet-body">
			<s:if test="%{(hostelList != null && !hostelList.isEmpty())}">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						 <li>
							<s:url id="viewAvailableItemsInMess"
								action="ajaxViewAvailableItemsInMess" includeParams="all"
								namespace="/hostel" escapeAmp="false" >
							</s:url>
							<sj:a href="%{viewAvailableItemsInMess}" data-toggle="tab"
								targets="itemsToStoreDivId">View Available Items In Mess</sj:a>
						</li>
						<li class="dropdown" id="manageActive">
								<a data-hover="dropdown" data-toggle="dropdown"
									class="dropdown-toggle js-activated" href="#">Issue Store Items<b class="caret"></b> </a>
								<ul role="menu" class="dropdown-menu pull-right">
									<li>
										<s:url id="urlViewAllIssueItemsToMess"
											action="ajaxViewAllIssueItemsToMess" includeParams="all"
											namespace="/hostel" escapeAmp="false">
										</s:url>
										<sj:a href="%{urlViewAllIssueItemsToMess}"
											targets="itemsToStoreDivId" data-toggle="tab">View Issue Items</sj:a>
									</li>
									<s:if test="%{(messList != null && !messList.isEmpty())}">
										<li>
											<s:url id="urlDoAddIssueProvisonItemsToMess"
												action="ajaxDoIssueProvisonItemsToMess" includeParams="all"
												escapeAmp="false" namespace="/hostel">
											</s:url>
											<sj:a href="%{urlDoAddIssueProvisonItemsToMess}"
												targets="itemsToStoreDivId" data-toggle="tab">Issue Provision Items to Mess</sj:a>
										</li>
									</s:if>
								</ul>
							</li> 
							
						<li class="dropdown active" id="manageActive">
								<a data-hover="dropdown" data-toggle="dropdown"
									class="dropdown-toggle js-activated" href="#">Manage
									Store Items<b class="caret"></b> </a>
								<ul role="menu" class="dropdown-menu pull-right">
									<li class="active">
										<s:url id="urlViewProvisonStoreHome"
											action="ajaxManageProvisionStoreHome" includeParams="all"
											namespace="/hostel" escapeAmp="false">
										</s:url>
										<sj:a href="%{urlViewProvisonStoreHome}"
											targets="mainContentDiv" data-toggle="tab">Manage Store Items</sj:a>
									</li>
									<s:if test="%{(messList != null && !messList.isEmpty()) && (tempList != null && !tempList.isEmpty())}">
										<li>
											<s:url id="urlAddProvisionStore"
												action="ajaxDoAddProvisionItemsToStrore" includeParams="all"
												escapeAmp="false" namespace="/hostel">
											</s:url>
											<sj:a href="%{urlAddProvisionStore}"
												targets="itemsToStoreDivId" data-toggle="tab">Add Items To Store</sj:a>
										</li>
									</s:if>
								</ul>
							</li>
					</ul>
					<div id="itemsToStoreDivId" class="tab-content">
						<jsp:include page="/WEB-INF/pages/hostel/messManagement/ajaxViewProvisionItemsList.jsp"></jsp:include>
					</div>
				</div>
				</s:if>
				<s:else>
				<div class="alert alert-info">
					Hostels are not available.
				</div>
				</s:else>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Manage Provision Items Store");
	});
</script>