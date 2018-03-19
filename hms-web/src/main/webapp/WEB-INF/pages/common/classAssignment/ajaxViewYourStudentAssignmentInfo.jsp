<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Class Assignment Information
				</div>
			</div>
			<div class="portlet-body">
				<div id="sclViewTimeTableCont" class="tab-content">
					<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
						<div class="form-group form-horizontal">
							<label class="control-label col-md-2">
								Student Name :
							</label>
							<div class="col-md-5">
								<s:select id="sectionId" list="tempList2" listKey="id"
									cssClass="form-control input-medium"
									listValue="studentNameAndUserName" name="tempId" theme="simple"
									onchange="javascript:getStudentExamSchedules(this.value);" />
							</div>
						</div>
					</s:if>
				</div>
				<div id="studentAssignmentDetails">
					<jsp:include
						page="/WEB-INF/pages/common/classAssignment/ajaxStudentAssignmentInfo.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
 $(document).ready(function() {
	changePageTitle("Student Assignment Details");
});		
function getStudentExamSchedules(studentId){
		 if(isNonEmpty(studentId)){
			$('#studentAssignmentDetails').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
					url : jQuery.url.getChatURL("/student/ajaxSelectedStudentInfo.do?tempId="+studentId),
					cache : true,
					success : function(response) {
					 	if(isNonEmpty(response)){
					 		$('#studentAssignmentDetails').html(response);
						}
					}
				});
		}
	}
</script>