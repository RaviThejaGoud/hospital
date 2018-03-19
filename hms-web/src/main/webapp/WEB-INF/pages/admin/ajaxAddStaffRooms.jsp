<%@ include file="/common/taglibs.jsp"%>

<s:form action="ajaxAddOrUpdateStaffRoomsForSchool" theme="simple" id="addStaffRoomsForSchool" method="post" cssClass="form-horizontal"
	namespace="/admin">
	<s:hidden name="academicYearId" value="%{#session.academicYear}"></s:hidden>
	<s:hidden name="staffRoom.id"></s:hidden>
	<%-- <div class="spaceDiv"></div>
	<p>
		<span class="label label-danger"> NOTE : </span>&nbsp;
		<s:if test="%{schoolHolidays.id != 0}">
				You can update school staff rooms with in the academic year.
			</s:if>
			<s:else>
				You can create school staff rooms with in the academic year.
			</s:else>
	</p> --%>
	<div class="form-body">
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>No Of Rooms For Headmaster :
			</label>
			<div class="col-md-3">
				<sj:textfield name="staffRoom.noofRoomsForHeadMasters" id="headMaster"  cssClass="required form-control input-medium numeric"
					maxlength="40"></sj:textfield>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>No Of Rooms For Teachers :
			</label>
			<div class="col-md-3">
				<sj:textfield name="staffRoom.noofRoomsForTeachers" id="teacher"  cssClass="required form-control input-medium numeric"
					maxlength="40"></sj:textfield>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>No Of Rooms For Non Teaching Staff :
			</label>
			<div class="col-md-3">
				<sj:textfield name="staffRoom.noofRoomsForNonTeachers" id="nonTeachers" cssClass="required form-control input-medium numeric"
					maxlength="40"></sj:textfield>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit cssClass="submitBt btn blue" value="Submit" targets="changeSchoolInfoContent" validate="true" formIds="addStaffRoomsForSchool" />
				<s:if test="%{staffRoom.id != 0}">
					<s:url id="doViewStaffRoom" action="ajaxDoAddStaffRoomDetails"
					includeParams="all" escapeAmp="false" namespace="/admin">
					<s:param name="anyTitle" value="" />
					</s:url>
					<sj:a href="%{doViewStaffRoom}" cssClass="btn default"
						indicator="indicator" targets="changeSchoolInfoContent">Cancel</sj:a>
				</s:if>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript">
$(document).ready(function() {
	FormAdvanced.init();
	$('.numeric').numeric();
});
changePageTitle('Staff Room Details');
</script>