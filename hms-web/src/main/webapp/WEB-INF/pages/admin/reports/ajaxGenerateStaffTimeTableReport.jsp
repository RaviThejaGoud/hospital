<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<s:form action="ajaxGenerateTimeTableForTeachers" theme="simple"
		onsubmit="return generateClassIds();" cssClass="form-horizontal"
		id="generateStaffTTForm" method="post" namespace="/reports">
		<s:hidden id="teacherIds" name="selectedId" />
		<div class="form-body">
			<div class="form-group">
				<div class="col-md-12">
					<div class="checkbox-list">
						<label class="checkbox-inline">
							<input type="checkbox" name="" value=""
									onClick="checkAllTeachers()" class="checkbox allTeachers">
							All staffs
						</label>
					</div>
				</div>
			</div>
			<s:if test="%{tempList != null && !tempList.isEmpty()}">
				<div class="form-group">
					<label class="conLable col-md-3 control-label">
						Please select staff :
					</label>
					<div class="col-md-12">
						<div class="checkbox-list">
							<s:checkboxlist name="chkBoxSelectedIds" list="tempList"
								listKey="staffId" listValue="staffFullNameWithRole" theme="ems"
								cssClass="small" />
						</div>
					</div>
				</div>
			</s:if>
			<div class="form-actions fluid">
				<div class="col-md-6">
					<div class="col-md-offset-3 col-md-9">
						<s:submit type="submit small" value="Download Timetable"
							onclick="reportType()" cssClass="submitBt btn blue long"
							title="generate report" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
</s:if>
<s:else>
	<div class="alert alert-info">
		Classes are not available.
	</div>		
</s:else>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle("Staff Wise Timetable Report");
		 $("input:checkbox, input:radio").uniform();
		 
		 $("input[name=chkBoxSelectedIds]").click(function() {
			if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
				$(".allTeachers").attr("checked", false);
				$(".allTeachers").parent('span').removeClass("checked");
			} else {
			    $(".allTeachers").parent('span').addClass("checked");
				$(".allTeachers").attr("checked", true);
			}
		});
	});
	function checkAllTeachers() {
		if ($(".allTeachers").is(':checked')){
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
	function generateClassIds() {
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			var staffIds = $("input[name=chkBoxSelectedIds]:checked");
			var selectedStaffIds = '';
			if (staffIds.length > 0) {
				selectedStaffIds = '(';
				for ( var i = 0; i < staffIds.length; i++) {
					selectedStaffIds += staffIds[i].value + ', ';
				}
				selectedStaffIds += '0)';
			}
			$("#teacherIds").val(selectedStaffIds);
			return true;
		} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one teacher");
			return false;
		} else {
			return false;
		}
	}
</script>