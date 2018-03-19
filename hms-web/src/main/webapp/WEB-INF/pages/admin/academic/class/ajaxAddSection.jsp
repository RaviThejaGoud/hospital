<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
	<s:form action="ajaxAddSections" theme="simple" id="addSections" method="post" cssClass="form-horizontal" namespace="/admin">
	<div class="form-body">
		<div class="row">
			<div class="col-md-6">
				<h4 class="bold pageTitle">
					Add section
				</h4>
			</div>
			<!--<div class="col-md-6">
			<s:url id="urlViewClassSection" action="ajaxDoManageClassSections" namespace="/admin"/>
			<sj:a id="viewClassSec" href="%{urlViewClassSection}"
				cssClass="btn default button-previous"
				cssStyle="text-align:right;float:right;" targets="mainContentDiv"
				indicator="indicator">
				<i class="m-icon-swapleft"></i>&nbsp;Back to Class &amp; Sections</sj:a>
		</div>-->
		</div>
		<p>
		<span class="label label-danger">NOTE :</span> Simple..! just type
		section(s) name and hit submit.</p>
		<div class="spaceDiv"></div>
		<s:hidden name="section" id="secNames"></s:hidden>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"><span
							class="required">*</span>
						Section Names :
					</label>
					<div class="col-md-6">
						<sj:textfield name="classSections" id="sectionName" size="40"
							maxlength="10"
							cssClass="alphanumeric required form-control input-mediums">
						</sj:textfield>
						<span class="help-block">(Enter section name with comma
							separator.)</span>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-4 col-md-6">
					<sj:submit   targets="classContentDiv" value="Submit"
						onBeforeTopics="formSectionsValidation" resetForm="true"
						cssClass="submitBt btn blue" formIds="addSections" validate="true"
						indicator="indicator" />
				</div>
			</div>
		</div>
		<br/>
		<br/>
		<div id="sectionsListContent">
		<jsp:include  page="/WEB-INF/pages/admin/academic/class/ajaxViewSections.jsp" />
	</div>
	</div>
</s:form>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"> </script>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Add Section");
		$("#sectionName").focus();
		$('.blockHeader h2').html('Manage Academics');
	
	});
	$(document).ready(function() {
		var validator;
		$.subscribe('formSectionsValidation', function(event, data) {
			var sectionName = $("#sectionName").val();
			var iChars = "!@#$%^&*()+=-[]\\\';./{}|\":<>Ç?";
			if(isNonEmpty(sectionName)){
				var selectedSecNames = [];
				for ( var i = 0; i < sectionName.length; i++) {
					if (iChars.indexOf(sectionName.charAt(i)) != -1) {
						alert("!Oops Section Name contains special characters. Please remove them and try again");
						event.originalEvent.options.submit=false;
					}
				}
				var sections = sectionName.split(',');
				$.each(sections, function(){
					if(isNonEmpty(this.replace(/^\s+|\s+$/g,''))){
						selectedSecNames.push(this.replace(/^\s+|\s+$/g,''));
					}
				});
				$('#secNames').val(selectedSecNames);
			}
			//return true;
		});
	});
</script>
