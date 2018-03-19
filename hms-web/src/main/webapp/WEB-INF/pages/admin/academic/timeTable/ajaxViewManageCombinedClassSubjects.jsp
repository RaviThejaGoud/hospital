<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Combined Classes
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear=="N"}'>
							 <li>
								<s:url id="urlSclCombinedClassSub" action="ajaxDoAddCombinedClassSubjects" includeParams="all" escapeAmp="false" namespace="/admin">
									<s:param name="subjectId" value="0"/>
									<s:param name="tempId" value="0"></s:param>
								</s:url>
								<sj:a id="addCombClasSub" href="%{urlSclCombinedClassSub}" targets="sclCombinedClasCont" data-toggle="tab" >Add Combined Classes</sj:a> 	
							 </li>
						 </s:if>
						 <li class="active">
							<s:url id="viewCombinedClass" action="ajaxViewCombinedClassSubjects" namespace="/admin">
							</s:url>
							<sj:a  href="%{viewCombinedClass}" targets="mainContentDiv" data-toggle="tab">View Classes</sj:a> 	
						 </li>
					</ul>
					<div id="sclCombinedClasCont" class="tab-content">
						<jsp:include page="/WEB-INF/pages/admin/academic/timeTable/ajaxViewCombinedClassSubjects.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
if ($('div input.changeServicesStatus')) {
		$('div input.changeServicesStatus').unbind('click');
		$("div input.changeServicesStatus").click(function() {
			confirmServicesStatusDialog(this);
		});
	}
});
</script>
 