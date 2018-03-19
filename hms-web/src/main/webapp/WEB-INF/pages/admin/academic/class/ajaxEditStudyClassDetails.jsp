<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/json2.js"> </script>
	<jsp:include page="/common/messages.jsp"></jsp:include>
	<div class="row">
	<div class="col-md-6">
	<div class="form-group">
		<label class="col-md-3 control-label">
			Class Name :
		</label>
		<div class="col-md-5">
			<s:select list="studyClassList" listKey="id" cssClass="form-control input-medium"
				listValue="classAndSection" name="classId" theme="simple"
				onchange="javascript:getClassDetails(this.value);" />
		</div>
	</div>	
	</div>
		<!--<div class="col-md-6">
			<s:url id="urlBackManageClass" action="ajaxDoManageClassSections"  />
			<sj:a id="backManageClass" href="%{urlBackManageClass}"
				cssClass="btn default button-previous"
				cssStyle="text-align:right;float:right;" targets="mainContentDiv"
				indicator="indicator">
				<i class="m-icon-swapleft"></i>Back to Class &amp; Sections</sj:a>
		</div>
	--></div>
<div id="classSubjectsContent">
		<jsp:include
			page="/WEB-INF/pages/admin/academic/class/editClassSubjects.jsp" />
	</div>
	<jsp:include
		page="/WEB-INF/pages/admin/academic/class/editStaffSubjects.jsp" />
	<!--<div id="classSyllabusContent">
		<jsp:include
			page="/WEB-INF/pages/admin/academic/class/editClassSyllabus.jsp" />
	</div>
-->
<script type="text/javascript">
$(document).ready(function() {
	$.subscribe('doInitClassDetails', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	$.subscribe('doInitClassSyllabusDetails', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
});
function getClassDetails(classSectionId){
		if (isNonEmpty(classSectionId)) {
			$('#classContentDiv').html(
							'<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "classId=" + classSectionId;
			$.ajax({
				url : jQuery.url.getChatURL("/admin/ajaxDoEditClassDetails.do"),
				cache : false,
				data : pars,
				success : function(response) {
					$('#classContentDiv').html(response);
					$('#classContentDiv').show();
				}
			});
		}
}
</script>