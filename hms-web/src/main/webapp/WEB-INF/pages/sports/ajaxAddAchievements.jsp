<%@ include file="/common/taglibs.jsp"%>
	<s:form action="ajaxAddorUpdateAchievements" theme="simple" id="addahievement"
		method="post" cssClass="form-horizontal" namespace="/sports">
		<s:hidden name="sports.id" value="%{Sports.id}" id="SportsId" />
		<div class="form-body">
		<h4 class="pageTitle bold form-section">ACHIEVEMENT INFORMATION</h4>
			<div class="row">	
			<div class="col-md-6">
					<div class="form-group">
						<label class="col-md-4 control-label"> <span
							class="required">*</span>Select Team :
						</label>
						<div class="col-md-6">
							<s:select list="teamList" listKey="id" id="teamId" listValue="teamWithSport" theme="simple" cssClass="required form-control input-medium"
								name="achievementVO.teamVO.id" headerKey="" headerValue="- Select Team -" >
							</s:select>
						</div>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label class="col-md-4 control-label"> <span
							class="required">*</span>Select Tournament :
						</label>
						<div class="col-md-8">
							<s:select list="tourList" listKey="tournamentId" id="tourId" listValue="tournamentName" theme="simple"
								cssClass="required form-control input-medium" name="achievementVO.tournamentVO.tournamentId" headerKey="" headerValue="- Select Tournament -">
							</s:select>
						</div>
					</div>
				</div>
				</div>	
				<div class="row">
				<%-- <div class="col-md-6">
					<div class="form-group">
						<label class="col-md-4 control-label"> <span
							class="required">*</span>Select Team :
						</label>
						<div class="col-md-8">
							<s:select list="objectList" listKey="id" id="teamId" listValue="teamName" theme="simple"
								cssClass="required form-control input-medium" name="achievementVO.teamName" headerKey="" headerValue="- Select Team -">
							</s:select>
						</div>
					</div>
				</div> --%>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Description : </label>
						<div class="col-md-5">
							<sj:textarea rows="3" cols="50" name="achievementVO.description" maxCharsData="1028" tabindex="14"
								cssClass="form-control word_count"></sj:textarea>
							<span class="help-block">
								<div class="counter"></div>
							</span>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> 
						Upload Photos :
						</label>
					<div class="col-md-8">
							<span class="btn default"><s:file name="achievementVO.fileUpload" id="uploadphotos" onchange="validateImage(this);"
											cssClass="btn default" accept="image/*"
											multiple="multiple"></s:file><%-- <s:file name="achievementVO.fileUpload" id="enrollmentForm" onchange="uploadPhotosValidation"  cssClass="fileName" multiple="multiple"  accept="image/*" > --%><%-- </s:file> --%>
											</span>
					</div>
					</div>
				</div>
				
			</div>
			<div class="form-actions fluid">
				<div class="col-md-6">
					<div class="col-md-offset-4 col-md-9">
						<sj:submit cssClass="submitBt btn blue" value="Submit"  id="clickSubmit"  validate="true" indicator="indicator"
							targets="mainContentDiv" formIds="addahievement" />
					
							<s:url id="doViewAchievement" action="ajaxSportAchievementInfoHome" includeParams="all" escapeAmp="false" namespace="/sports">
							</s:url>
							<sj:a href="%{doViewAchievement}" cssClass="btn default" indicator="indicator" targets="mainContentDiv">Cancel</sj:a>
						
					</div>
				</div>
			</div>
		</div>
	</s:form>
<script type="text/javascript">
$.subscribe('uploadPhotosValidation', function(achievement, data) {
	if ($('#enrollmentForm').valid()) {
		$('button.close').click();
		return true;
	} else {
		achievement.originalAchievement.options.submit=false;
	}
});
</script>
