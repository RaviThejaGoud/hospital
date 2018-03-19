<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<s:form action="ajaxEditStudentVehicleInformation" theme="simple"
		cssClass="form-horizontal" id="editStudentVehicleInfo" method="post">
		<div id="studentEditContentDiv">
			<jsp:include page="/common/messages.jsp"></jsp:include>
		</div>
		<s:hidden name="student.id"
			value="%{viewStudentPersonAccountDetails.studentId}" />
		<s:hidden name="academicYearId" value="%{academicYearId}" />
		<span class='studBoardingPoint'
			id='<s:property value="viewStudentPersonAccountDetails.boardingPointId"/>'></span>
		<span class='vehicleAcademicDetailsId'
			id='<s:property value="viewStudentPersonAccountDetails.vehicleAcademicDetailsId"/>'></span>
		<div class="row">
			<%@ include
				file="/WEB-INF/pages/common/ajaxAddStudentTransportInformation.jsp"%>
		</div>
		<div class="spaceDiv"></div>
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<s:if test='%{#session.previousYear=="N"}'>
					<sj:submit   cssClass="btn blue" value="Save" targets="studentEditContentDiv"
						validate="true" onBeforeTopics="checkEditStudentInfo" />
				</s:if>
			</div>
		</div>
	</s:form>
</div>
<script type="text/javascript">
$.subscribe('checkEditStudentInfo', function(event, data) {
	if($("#boardingId1").is(":hidden")){
		$("#boardingId1").removeClass('required');
	}
	if($("#vehicleNumber").is(":hidden")){
		$("#vehicleNumber").removeClass('required');
	}
	if($("#vehicleId").is(":hidden")){
		$("#vehicleId").removeClass('required');
	}
	if($("#boardingId").is(":hidden")){
		$("#boardingId").removeClass('required');
	}
});
</script>
