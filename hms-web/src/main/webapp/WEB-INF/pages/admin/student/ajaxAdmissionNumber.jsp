<%@ include file="/common/taglibs.jsp"%>
<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Admission Number :
						</label>
						<div class="col-md-7">
							<sj:textfield name="userVo.admissionNumber" id="admission"
								cssClass="required form-control input-medium as-input admissionNumber"
								maxlength="50"></sj:textfield>
						</div>
					</div>
				</div>
<script type="text/javascript">
$("#admission").unbind('#admission').autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckAdmissionNumberAvailableOrNot.do?selectedId="+$('#studyClassHiddId').val(),
		{
			minChars : 1,
			min : "no",
		});
</script>