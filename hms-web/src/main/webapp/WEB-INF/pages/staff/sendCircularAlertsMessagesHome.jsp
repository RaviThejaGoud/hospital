<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Circular Messages
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li>
							<s:if test='%{user.IsSchoolAdmin=="Y" || user.isSchoolDirector == "Y"}'>
								<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
										<s:url id="urlCircularMessagesLink" action="ajaxDoSendCircularMessages" namespace="/common"/>
										<sj:a href="%{urlCircularMessagesLink}" targets="circularMessagesHome"  data-toggle="tab">Add Circular</sj:a>
								</s:if>
							</s:if>
						</li>
						 <li class="active" id="schoolWideMessage">
							<s:url id="viewCircularAlert" action="ajaxDoGetCircularMessagesList" namespace="/common">
							</s:url>
							<sj:a id="viewCircularAlert" href="%{viewCircularAlert}" targets="mainContentDiv" data-toggle="tab">View Circulars</sj:a> 	
						 </li>
					</ul>
					<div id="circularMessagesHome" class="tab-content">
						<jsp:include page="/common/messages.jsp" />
						<div id="autoCompleterRollText">
								<jsp:include page="/WEB-INF/pages/staff/ajaxViewCircularMessages.jsp"></jsp:include>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Circular Messages ');
});
</script>
