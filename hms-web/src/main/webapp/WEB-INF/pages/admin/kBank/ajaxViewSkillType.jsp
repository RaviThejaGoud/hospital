<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<s:form action="ajaxDoAddSkillType" theme="simple" id="addSkills" cssClass="form-horizontal"
		method="post" namespace="/admin">
		<fieldset>
			<div class="form-group">
				<label class="col-md-2 control-label">
					<span class="required">*</span>Skill Type Name :
				</label>
				<div class="col-md-4">
					<div class="input-group">
						<sj:textfield name="skillTypeName" id="className1"
							cssClass="alphanumeric form-control required defaultValue">
						</sj:textfield>
						<span class="input-group-btn">
						 <sj:submit   targets="caseStudyView" value="submit"
								cssClass="submitBt btn blue" formIds="addSkills"
								indicator="indicator" validate="true" /></span>
								<div class="spaceDiv"></div>
					</div>
					<span class="hintMessage">(Key at least 3 chars and hit
						submit to get closer match.)</span>
				</div>
			</div>
		</fieldset>
	</s:form>
</div>
<div id="viewStudySkillType">
 <jsp:include page="/common/messages.jsp" />
	<jsp:include page="/WEB-INF/pages/admin/kBank/ajaxViewAllSkillTypes.jsp"></jsp:include>
</div>
<script type="text/javascript">
$(document).ready(function() {
$("#className1").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckskillTypeNames.do",
	{
		minChars : 3,
		min : "no"
	}).focus();
	});
</script>


