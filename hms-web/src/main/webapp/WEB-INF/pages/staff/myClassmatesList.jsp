<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studentsList !=null}">
	<div class="row">
		<div class="col-md-12">
			<s:form id="findStudentUsingName"
				action="ajaxFindStudentProfileUsingNameOrId" method="post"
				theme="simple" cssClass="form-horizontal" namespace="/staff">
				<s:hidden name="tempId1" id="tempId1" value="%{tempId1}"/>
				<s:hidden name="anyTitle" id="anyTitle" value="%{anyTitle}"/>
				<div class="form-group">
					<label class="col-md-2 control-label" style="width: 120px;">
					</label>
					<div class="col-md-3">
						<select name="stuFirstName" id="select2_sample4"
							class="required form-control select2">
							<s:iterator value="studentsList">
								<option value='<s:property />'>
									<s:property />
								</option>
							</s:iterator>
						</select>
						<span class="hintMessage">(Key at least 3 chars and hit
							submit to get closer match.)</span>
					</div>
					<sj:submit targets="profileId" cssClass="btn blue clickbtn" value="Find"
						validate="true" />
				</div>
			</s:form>
		</div>
	</div>
</s:if>
<div class="spaceDiv">
	&nbsp;
</div>
<s:else>
	<div class="alert alert-info">
		Currently there are no Students.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	FormComponents.init();
	$('#studentsList').focus();
	$('input.clickbtn').click();
});
</script>