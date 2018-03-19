<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div class="form-body">
<s:form action="ajaxDoAddOrEditDepartments" theme="simple"
	id="addOrEditDepartments" method="post" cssClass="form-horizontal"
	enctype="multipart/form-data" namespace="/admin">
	<s:hidden type="hidden" name="tempString" id="tempString" />
	<s:hidden name="tempId" value="%{tempId}" />
	<div class="row">
		<div class="col-md-6">
			<div class="form-group ">
				<label class="control-label col-md-4"> <span
					class="required">*</span>Department Name : </label>
				<div class="col-md-5">
					<sj:textfield name="departments.departmentName" id="organizationName"
						tabindex="10" cssClass="organizationName required form-control"
						maxlength="50"></sj:textfield>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4"><span class="required">*</span> select Department head : </label>
				<div class="col-md-8">
					<s:select id="state" list="staffsList" label="staff"
						cssClass="required form-control input-medium" listKey="accountIdAndFullName"
						listValue="personFirstLastNameOnly" headerKey="" headerValue="- Select -"
						name="anyTitle" />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4"> <span
					class="required">*</span>Contact Number :
				</label>
				<div class="col-md-8">
					<sj:textfield name="departments.mobileNumber" id="mobileNumber" maxlength="10"
						cssClass="numeric required form-control input-medium as-input"
						onchange="javascript:validateMobNumbers(this.value)"></sj:textfield>
				</div>
			</div>
		</div>
	</div>
	<br />
	<s:if test='%{studyClassList != null && !studyClassList.isEmpty()}'>
		<div class="form-group">
			<div class="col-md-12">
				<div class="checkbox-list">
					<label class="checkbox-inline">
							<input type="checkbox" name=""
									value="" onClick="checkAllClasses()"
									class="checkbox allClasses">
						All Classes
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<!-- <label class="checkbox-inline"> Select Classes : </label> -->
			<div class="spaceDiv"></div>
			<div class="col-md-12">
				<s:checkboxlist name="chkBoxSelectedIds" theme="ems"
					cssClass="checkbox required form-control allClassesId"
					list="studyClassList" listKey="id" listValue="classAndSection" />
			</div>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">Currently there is no class and section.</div>
	</s:else>
	<div class="form-actions fluid">
		<div class="col-md-offset-2 col-md-5">
			<sj:submit cssClass="submitBt btn blue " value="Submit" targets="mainContentDiv" validate="true" formIds="addOrEditDepartments" onBeforeTopics="createDepartmentHead" />
			<s:url id="urlViewDepartmentHead" action="ajaxViewDepartmentHead" namespace="/admin" />
			<sj:a href="%{urlViewDepartmentHead}" targets="mainContentDiv" cssClass="btn default">Cancel</sj:a>
		</div>
	</div>
</s:form>
</div>
<script type="text/javascript">
	$(document)	.ready(	function() {
		$('.numeric').numeric();
		$("input:checkbox, input:radio").uniform();
		var selectedValues = [];
		var unSelectedValues = [];
		$("input[name=chkBoxSelectedIds]").click(
				function() {
					if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
						$(".allClassesId").parent('span').removeClass("checked");
						$(".allClassesId").attr("checked", false);
					} else {
						$(".allClassesId").attr("checked", true);
						$(".allClassesId").parent('span').addClass("checked");
					}
					selectedValues = $("input[name=chkBoxSelectedIds]:checked").map(function() {
								return this.value;
							}).get().join(",");
					$('#tempString').val(selectedValues);
					/* unSelectedValues = $("input[name=chkBoxSelectedIds]:unchecked").map(function() {
						return this.value;
					}).get().join(",");
					$('#tempString').val(unSelectedValues); */
				});
		
	});
		$.destroyTopic('createDepartmentHead');
			$("input#organizationName").autoCheck("${pageContext.request.contextPath}/masterAdmin/ajaxCheckOrganizationName.do",
			{
				minChars : 3,
				min : "no"
			});
	function validateMobNumbers(txtMobId) {
		/* var mob = /^(\+91-|\+91|0)?\d{10}$/;
		if (mob.test($.trim(txtMobId)) == false) {
			alert("Please enter valid mobile number.");
			$("#mobileNumber").val('');
			$("#mobileNumber").focus();
			return false;
		} else */ if (!(txtMobId.length == 10)) {
			alert("The phone number is the wrong length. \nPlease enter 10 digit mobile no.");
			$("#mobileNumber").val("");
			$("#mobileNumber").focus();
			return false;
		} else if ((txtMobId.length == 10)) {
			return true;
		}
	}
	$(document).ready(function() {
			$.subscribe('createDepartmentHead', function(event, data) {
					var selectedChkCount = $("input[name=chkBoxSelectedIds]:checked").length;
					if (selectedChkCount > 0){
						var secletCustIds = [];
						$("input:checkbox[name=chkBoxSelectedIds]:checked").each(function()
						{
							secletCustIds.push($(this).val());
						});
						$('#tempString').val(secletCustIds);	
					}
					else {
						alert("Please select at least one class.");
						event.originalEvent.options.submit=false;
					}
						 $('p.word-taken').each(function() {
						  if($(this).html()=='Already taken!!!'){
						     event.originalEvent.options.submit=false;
						   }
						 });
						 $('button.close').click();
			});
		});
	
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		    $(".allClasses").parent('span').removeClass("checked");
			$(".allClasses").attr("checked", false);
		} else {
			$(".allClasses").attr("checked", true);
			$(".allClasses").parent('span').addClass("checked");
		}
});
function checkAllClasses() {
	if ($(".allClasses").is(':checked')){
	    $("[name='chkBoxSelectedIds']:not(':disabled')").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		});
	}	
}
</script>
