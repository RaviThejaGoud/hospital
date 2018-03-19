<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{tempId > 0}">
	<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
		<s:form id="studentGenerateTcInfoForm" method="post" theme="simple"
			action="ajaxGenerateStudentsPromotionReport" onsubmit="javascript:return validateSelectedClassName();"
			cssClass="form-horizontal" namespace="/exam">
				<h4 class="bold pageTitle">
					Generate Promotion Report
				</h4>
				<p>
					Select class and click on 'Generate Report' system will generate
					promotion report for that class based on template you have
					uploaded.
				</p>
				<hr />
				<div class="panel-body col-md-12">
					<div class="col-md-1">
						<span class="label label-danger">NOTE :</span>
					</div>
					<div class="col-md-10">
						<ul>
							<li>
								Before generating score card please upload template for that
								class.
							</li>
							<li>
								After uploading template if you have changed any examtype,
								subject names and subtype names details in system, system can't
								generate promotion report.
							</li>
						</ul>
					</div>
				</div>
				<s:hidden name="anyTitle" id="classSecParam"></s:hidden>
				<div class="form-body">
						<div class="form-group">
							<label class="control-label col-md-3">
								<span class="required">*</span>Select Class :
							</label>
							<div class="col-md-9">
								<s:select list="studyClassList"
									listKey="classNameWithClassSectionId"
									listValue="classAndSection" id="selectedClassName"
									name="tempString" headerKey="" headerValue="- Select -"
									theme="simple" cssClass="form-control input-medium required">
								</s:select>
							</div>
						</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-9">
						<sj:submit   value="Generate Report" cssClass="submit long btn blue"  validate="true" 
							cssStyle="float:none;" formIds="studentGenerateTcInfoForm"  />
					</div>
				</div>
			</div>
		</s:form>
	</s:if>
</s:if>
 <s:else>
 <div  class="alert alert-info">
 	You have not uploaded promotion report template. <a href="#" onclick="javascript:managePromotionReportTemplate()"><b><font color="red">Click here</font></b></a> to upload promotion report template.
 </div>
 </s:else>
<script type="text/javascript">
$(document).ready(function() {
});

function validateSelectedClassName(){
	var className = $('#selectedClassName').val();
	if(isNonEmpty(className)){
		$("#classSecParam").val($("select[id='selectedClassName'] option:selected").text());	
		return true;
	}else{
		alert("Please select class.");
		return false;
	}
}

function managePromotionReportTemplate(){
	$('a#promotionTemplate').click();
}
</script>
 