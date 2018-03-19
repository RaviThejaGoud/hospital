<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
				<i class="fa fa-globe"></i>
					Fee Settings
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:url id="urlFeeCategory" action="ajaxDoFeeCategory" namespace="/schoolfee"></s:url>
							<sj:a id="feeCategory" href="%{urlFeeCategory}" targets="feeSettingsContent" data-toggle="tab">Class Fee</sj:a> 	
						</li>
						<li class="dropdown">
							<a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#">View Fee Terms <b class="caret"></b> </a>
							<ul role="menu" class="dropdown-menu pull-right">
								<li>
									<s:url id="doManageFeeTerms"
										action="ajaxViewSelectedFeeSettings" includeParams="all"
										escapeAmp="false">
										<s:param name="tempString">feeTerms</s:param>
									</s:url>
									<sj:a href="%{doManageFeeTerms}" targets="feeSettingsContent"
										data-toggle="tab">View Fee Terms</sj:a>
								</li>
								<s:if test='%{#session.previousYear=="N"}'>
									<li>
										<s:url id="urlCreateSchoolTermsLink"
											action="ajaxDoCreateSchoolTerms" includeParams="all"
											escapeAmp="false">
											<s:param name="feeType.id" value="0" />
										</s:url>
										<sj:a href="%{urlCreateSchoolTermsLink}"
											 targets="feeSettingsContent"  data-toggle="tab">Add Fee Term</sj:a>
									</li>
								</s:if>
							</ul>
						</li>
						<li class="dropdown">
							<a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#"> View Fee Particulars <b class="caret"></b> </a>
							<ul role="menu" class="dropdown-menu pull-right">
								<li>
									<s:url id="doManageFeeParticulars"
										action="ajaxViewSelectedFeeSettings" includeParams="all"
										escapeAmp="false">
										<s:param name="tempString">feeParticulars</s:param>
									</s:url>
									<sj:a href="%{doManageFeeParticulars}" targets="feeSettingsContent"
										data-toggle="tab">View Fee Particulars</sj:a>
								</li>
								<s:if test='%{#session.previousYear=="N"}'>
									<li>
										<s:url id="urlCreateParticularsLink"
											action="ajaxDoCreateParticular" includeParams="all"
											escapeAmp="false">
											<s:param name="feeType.id" value="0" />
										</s:url>
										<sj:a href="%{urlCreateParticularsLink}" 
											targets="feeSettingsContent" data-toggle="tab">Add Fee Particulars</sj:a>
									</li>
								</s:if>
							</ul>
						</li>
						<li class="dropdown active">
							<a data-hover="dropdown" data-toggle="dropdown"
								class="dropdown-toggle js-activated" href="#"> View Categories <b class="caret"></b> </a>
							<ul role="menu" class="dropdown-menu pull-right">
								<li class="active">
									<s:url id="doManageFeeSettings"
										action="ajaxViewSelectedFeeSettings" includeParams="all"
										escapeAmp="false">
										<s:param name="tempString">categories</s:param>
									</s:url>
									<sj:a href="%{doManageFeeSettings}" targets="feeSettingsContent"
										data-toggle="tab">View Categories</sj:a>
								</li>
								<s:if test='%{#session.previousYear=="N"}'>
									<li>
										<s:url id="urlCreateCategoryLink"
											action="ajaxDoCreateCategory" includeParams="all"
											escapeAmp="false">
											<s:param name="schoolCategory.id" value="0" />
										</s:url>
										<sj:a href="%{urlCreateCategoryLink}"
											targets="feeSettingsContent" data-toggle="tab">Add Category</sj:a>
									</li>
								</s:if>
							</ul>
						</li>
					</ul>
					<div id="feeSettingsContent" class="tab-content">
						<jsp:include page="/WEB-INF/pages/schoolfee/ajaxViewSchoolFeeSettings.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Terms And Fees");
	 $('.js-activated').dropdownHover().dropdown();
	$('.blockHeader h2').html('Manage Academics');
});
</script>
