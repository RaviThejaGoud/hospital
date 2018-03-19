<%@ include file="/common/taglibs.jsp"%>
<s:form action="#" cssClass="form-horizontal" theme="simple">
	<div class="row">
		<div class="col-md-12">
			<%--<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>Issued Books
					</div>
				</div>
				--%>
			<div class="portlet-body">
				<div class="tab-content">
					<div class="form-group">
						<label class="col-md-2 control-label">
							Issued To :
						</label>
						<div class="col-md-9">
							<div class="col-md-9 radio-list">
								<label class="col-md-2 radio-inline">
									<input type="radio" checked="checked" value="teaching"
										onclick="changeQualification(this.value);"
										name="addGroupLeader" />
									Student
								</label>
								<label class="radio-inline">
									<input type="radio" value="nonTeaching"
										onclick="changeQualification(this.value);"
										name="addGroupLeader" />
									Staff
								</label>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<s:if test="%{classList != null && !classList.isEmpty()}">
								<div class="form-group">
									<div id="studentDiv1" style="display: block;">
										<label class="control-label col-md-2">
											<span class="required">*</span>Select Class :
										</label>
										<div class="col-md-5">
											<s:select list="classList" listKey="id" listValue="className"
												cssClass="required form-control input-medium as-input"
												theme="simple" name="classId" headerKey="0"
												headerValue="- Select Class Name-" id="className"
												onchange="javascript:getAllIssuedBooks(this.value);">
											</s:select>
										</div>
									</div>
								</div>
							</s:if>
						</div>
					</div>
					<div id="resultsDiv1" style="display: none;">
						<jsp:include
							page="/WEB-INF/pages/library/viewAllIssuedStudentBooksList.jsp"></jsp:include>
					</div>
					<div id="staffDiv" style="display: none;">
						<jsp:include
							page="/WEB-INF/pages/library/viewAllIssuedStaffBooksList.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript">
$(document).ready(function() {
$("input:checkbox, input:radio").uniform();
var studyClassId=$('select#className').val();
	if(isNonEmpty(studyClassId)){
		getAllIssuedBooks(studyClassId);
	}else if(studyClassId != '0' || studyClassId == ""){
		$('#resultsDiv1').html('<div class="alert alert-info">Currently there are no issued books.</div>');
		$('#resultsDiv1').show();
	}
});
function getAllIssuedBooks(classId) {
if (classId == '0') {
		$("#resultsDiv1").hide();
	} else {
	//var classId = studyClassId.value;
	var url = jQuery.url
			.getChatURL("/library/ajaxViewAllPendingStudentBooks.do");
	if (classId.length == 0) {
		alert("!Oops select class name.");
	} else {
		$("#resultsDiv1") .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "classId=" + classId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#resultsDiv1").html(html);
				$("#resultsDiv1").show();
			}
		});
	}
  }
}

function changeQualification(staffType) {
	if (staffType == 'teaching') {
	var studyClassId=$('select#className').val();
	 getAllIssuedBooks(studyClassId);
		$('#studentDiv1').show();
		$('#staffDiv').hide();
		$('#classId').val('');
	} else if (staffType == 'nonTeaching') {
		$('#studentDiv1').hide();
		$('#staffDiv').show();
		$('#resultsDiv1').hide();
		var url = jQuery.url
				.getChatURL("/library/ajaxViewAllPendingStaffBooks.do");
		$("#staffDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			success : function(html) {
				$("#staffDiv").html(html);
				$("#staffDiv").show();
			}
		});
	}
}
changePageTitle("View All Issued Books");
</script>