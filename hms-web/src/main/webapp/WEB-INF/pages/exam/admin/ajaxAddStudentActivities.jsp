<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studentActivities.id != 0}">
	<div data-width="760" class="modal fade modal-overflow in"
		style="display: block; width: 900px; margin-left: -490px; margin-top: 120px;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">
				Update activity
			</h4>
		</div>
		<div class="modal-body">
</s:if>
<div class="form-body">
	<s:form action="ajaxAddStudentActivity" method="post" theme="simple"
		id="addActivity" cssClass="form-horizontal" namespace="/exam">
		<s:hidden name="studentActivities.id" value="%{studentActivities.id}"></s:hidden>
		<s:if test="%{studentActivities == null}">
			<h4 class="modal-title">
				Add activity
			</h4>
			<hr/>
		</s:if>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span> Category Name :
					</label>
					<div class="col-md-7">
						<s:if test="%{objectList != null && !objectList.isEmpty()}">
							<s:select list="objectList" listKey="categoryName"
								listValue="categoryName" id="category categoryTxt" theme="simple"
								cssClass="form-control input-medium hideOrShow" name="categoryName" onclick="getCategoryName(this.value);"
								onchange="$('input#categoryTxt').val($(this).find('option:selected').text());"
								headerKey="" headerValue="" cssStyle="padding: 3px 10px;"></s:select>
							<sj:textfield id="categoryTxt"
								name="studentActivities.categoryName"
								cssClass="form-control promoteClass required hideOrShow" 
								cssStyle="float: left; margin-left: 1px; margin-top: -31px; width: 207px;background-color : #FFF;"></sj:textfield>
							<div id="hideOrShowForSelect">
								<s:hidden name="studentActivities.categoryName" id="hideOrShowForVal"></s:hidden>
							</div>		
						</s:if>
						<s:else>
							<sj:textfield id="categoryTxt"
								name="studentActivities.categoryName"
								cssClass="form-control input-medium promoteClass required"></sj:textfield>
						</s:else>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span> Activity Name :
					</label>
					<div class="col-md-4">
						<sj:textfield name="studentActivities.activityName"
							id="activityName" cssClass="required form-control input-medium">
						</sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Activity Description :
					</label>
					<div class="col-md-4">
						<sj:textarea rows="4" cols="30"
							name="studentActivities.activityDescription"
							cssClass="textSmall form-control input-medium"></sj:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-4 col-md-6">
					<sj:submit cssClass="submitBt btn blue" value="Submit"
						onBeforeTopics="addPrograsFormValidation"
						targets="studentsActivitiesContent" formIds="addActivity"
						validate="true" />
					<s:if test="%{studentActivities.id != 0}">
						<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
					</s:if>
					<s:else>
						<s:url id="doViewActivities" action="ajaxManageStudentActivities"
							namespace="/exam">
						</s:url>
						<sj:a href="%{doViewActivities}" cssClass="btn default"
							targets="mainContentDiv">Cancel</sj:a>
					</s:else>
				</div>
			</div>
		</div>
	</s:form>
</div>
<s:if test="%{studentActivities.id != 0}">
	</div>
	</div>
</s:if>

<script type="text/javascript">
$( document ).ready(function() {
	$("input#hideOrShowForVal").attr('disabled','disabled');
});

$.subscribe('addPrograsFormValidation',function(event, data) {
	$('button.close').click();
});
function getCategoryName(categoryVal){
	if(isNonEmpty(categoryVal)){
		$("input#categoryTxt").attr('disabled','disabled');
		$("input#hideOrShowForVal").val(categoryVal);
		$("div#hide").removeAttr('disabled');
		$("input#hideOrShowForVal").removeAttr('disabled');
	}else{
		$("input#hideOrShowForVal").hide;
		$("input#categoryTxt").removeAttr('disabled');
		$("div#hide").attr('disabled');
	}
} 
$( "input.hideOrShow" ).keypress(function() {
	$("input#hideOrShowForVal").attr('disabled','disabled');
});
</script>