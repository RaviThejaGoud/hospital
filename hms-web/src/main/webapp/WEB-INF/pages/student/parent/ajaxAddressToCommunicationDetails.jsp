<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Address To Communication
				</div>
			</div>
			<div class="portlet-body">
				<div id="sclViewTimeTableCont" class="tab-content">
					<s:if test="%{objectList != null && !objectList.isEmpty()}">
						<jsp:include page="/common/messages.jsp"></jsp:include>
						<div class="form-body form-horizontal">
							<div class="row">
								<div class="col-md-6">
									<label class="control-label col-md-5">
										Student Name :
									</label>
									<div class="col-md-7">
										<s:select id="sectionId" list="objectList" listKey="id"
											cssClass="form-control input-medium"
											listValue="studentNameAndUserName" name="tempId" theme="simple"
											onchange="javascript:getStudentExamSchedules(this.value);" />
									</div>
								</div>
							</div>
						</div>
					</s:if>
				</div>
				<div id="studentCommunicationDetails">
					<jsp:include
						page="/WEB-INF/pages/student/parent/ajaxCommunicationDetails.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
 $(document).ready(function() {
	changePageTitle("Student Communication Details");
});		
function getStudentExamSchedules(studentId){
		 if(isNonEmpty(studentId)){
			$('#studentCommunicationDetails').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
					url : jQuery.url.getChatURL("/student/ajaxCommunicationDetails.do?tempId="+studentId),
					cache : true,
					success : function(response) {
					 	if(isNonEmpty(response)){
					 		$('#studentCommunicationDetails').html(response);
						}
					}
				});
		}
	}
</script>
