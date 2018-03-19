<%@ include file="/common/taglibs.jsp"%>
<s:form id="downloadAdmintedAdmissionTemplate"
	action="ajaxDownloadOnlineApplicationDetails"
	onsubmit="javascript:return generateClassIds();" method="post"
	enctype="multipart/form-data" namespace="/student" theme="simple"
	cssClass="form-horizontal">
	<h4 class="pageTitle bold">Download students details</h4>
	<jsp:include page="/common/messages.jsp"></jsp:include>
	<s:hidden id="academicYearId" name="academicYearId" />
	<s:hidden name="anyId" value="%{tempString}"></s:hidden>
	<s:hidden name="tempString" id="classNames" />
	<s:hidden name="selectedId" id="classNameIds" />
	<div class="panel-body col-md-12">
		<div class="col-md-1">
			<span class="label label-danger"> NOTE : </span>
		</div>
		<div class="col-md-10">
			<ul>
				<li>Please select the class and download the students
					admissions excel sheet by clicking the "Download Student" button
					below.</li>
				<li>This excel has marked with set of column names that are
					supported by the system.</li>
				<li><font color="red">Please do not add new columns or
						delete the marked columns</font>. If you want add more columns, please
					contact EazySchool support team(support@eazyschool.com).</li>
			</ul>
		</div>
	</div>
	<div class="form-body">
		<s:if test="%{tempList != null && !tempList.isEmpty()}">
			<div class="row">
				<div class="form-group">
					<div class="checkbox">
						<label class="checkbox-inline"> All Class & Sections : </label>
						<!--
					 <input type="checkbox" value=""
								class="allClasses" onClick="checkAllClasses()" name="" />
					--><label class="checkbox">
						<input type="checkbox" value="" class="allClasses"
							onClick="checkAllClasses()" name="" /></label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<label class=" col-md-2 control-label"> Class With Students
						: </label>
					<div class="col-md-9">
						<div class="checkbox-list">
							<s:checkboxlist name="chkBoxSelectedIds" list="tempList"
								listKey="classId" listValue="className" theme="ems"
								cssClass="small" />
							
						</div>
					</div>
				</div>
			</div>
		</s:if>
		<div class="form-actions fluid">
			<div class="col-md-7">
				<div class="col-md-offset-3 col-md-12">
					<s:if test="%{tempList != null && !tempList.isEmpty()}">
						<sj:submit value="Download Student" cssClass="submitBt btn blue" />
						<s:url id="urlmanageStaff" action="ajaxGetEditOnlineAdmissions"
							namespace="/admin" includeParams="all" escapeAmp="false" />
						<sj:a href="%{urlmanageStaff}" targets="mainContentDiv"
							cssClass="btn default">Cancel</sj:a>
					</s:if>
				</div>
			</div>
		</div>
	</div>
	<s:else>
		<div class="alert alert-info">There is no students.</div>
	</s:else>
</s:form>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Download Student Details');
	$("input:checkbox, input:radio").uniform();
	/*$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
			$(".allClasses").attr("checked", false);
		} else {
			$(".allClasses").attr("checked", true);
		}
	});*/

});
$("input[name=chkBoxSelectedIds]").click(function() {
	if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		   $(".allClasses").parent('span').removeClass("checked");
			$(".allClasses").attr("checked", false);
		} else {
		    $(".allClasses").parent('span').addClass("checked");
			$(".allClasses").attr("checked", true);
		}
});
function checkAllClasses() {
	if ($(".allClasses").is(':checked')){
	    $("[name='chkBoxSelectedIds']").each(function(){
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
/* function checkAllClasses() {
	if ($(".allClasses").is(':checked'))
		$("input[name='chkBoxSelectedIds']").attr("checked", "true");
	else
		$("input[name='chkBoxSelectedIds']").removeAttr("checked");
}
*/
function generateClassIds() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedClassIds = '';
		var selectedClassNames = '';
		if (classIds.length > 0) {
			selectedClassIds = '(';
			$(classIds).each(function(){
				selectedClassIds += $(this).val() + ', ';
				if(isNonEmpty($(this).parents('label').text()))
				    selectedClassNames +=  $(this).parents('label').text().trim().replace(/^\s+|\s+$/g, ' '); 
			});
			selectedClassIds += '0)';
		}
		$("#classNameIds").val(selectedClassIds);
		$("#classNames").val(selectedClassNames);
		return true;
	} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one Class");
		return false;
	} else {
		return false;
	}
}
</script>