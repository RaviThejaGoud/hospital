<%@ include file="/common/taglibs.jsp"%>
<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Roll Number :
						</label>
						<div class="col-md-7">
							<sj:textfield name="studentVo.rollNumber" id="rollNumber"
								cssClass="form-control input-medium as-input numeric rollNumber"
								maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
<script type="text/javascript">
$("#rollNumber").unbind('#rollNumber').autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckStudentRoleNumber.do?selectedId="+$('#studyClassHiddId').val(),
		{
			minChars : 1,
			min : "no",
		});
</script>