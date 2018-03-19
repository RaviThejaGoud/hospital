<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Buy SMS
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear=="N"}'>
						<li>
						<s:url id="doDoBuySms"	action="ajaxDoBuySms" escapeAmp="false"	namespace="/admin" />
							<sj:a id="doDoBuySms" href="%{doDoBuySms}" targets="contentDiv" data-toggle="tab"> 
								Buy SMS  </sj:a>
						</li>
						</s:if>
						<li  class="active">
							<s:url id="viewBuySms" action="ajaxViewBuySmsDetails" namespace="/admin" />
							<sj:a href="%{viewBuySms}" targets="mainContentDiv" >
								View Buy SMS History
							</sj:a>
						</li>
					</ul>
					<div class="tab-content" id="contentDiv">
						<jsp:include page="/WEB-INF/pages/admin/ajaxViewBuySmsDetailsList.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
