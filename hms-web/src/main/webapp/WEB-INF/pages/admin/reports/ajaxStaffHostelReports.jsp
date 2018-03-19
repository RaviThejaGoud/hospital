<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Admin--->Hostel Staff Details
				</div>
			</div>
			<div class="portlet-body">
				<div id="staffHostelContentDiv" class="tab-content">
					<s:if test="%{(buildingList != null && !buildingList.isEmpty())}">
						<s:form action="ajaxGenerateStaffHostelReports"
							namespace="/reports" theme="simple"
							onsubmit="return generateClassBuildingIds();"
							cssClass="form-horizontal" id="hostelReports" method="post">
							<s:hidden id="buildingIds" name="anyTitle" />
							<s:hidden id="genderTypes" name="tempString" />
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-2 control-label">
										<span class="required">*</span> Available Buildings:
									</label>
									<div class="col-md-3">
										<label class="checkbox">
											<input type="checkbox" name="" value=""
												onClick="checkAllBuildings()"
												class="checkbox allCommunities">
											All Buildings
										</label>
										<s:checkboxlist name="buildingCheckBoxes" list="buildingList"
											listKey="id" listValue="buildingName" theme="ems"
											cssClass="small" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">
										<span class="required">*</span> Select Gender :
									</label>
									<div class="col-md-3">
										<label class="checkbox">
											<input type="checkbox" value="" onClick="checkAllGenders()"
												class="checkbox allGenders">
											All Gender
										</label>
										<input type="checkbox" name="genderName" value="M">
										Male
										<input type="checkbox" name="genderName" value="F">
										Female
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">
										Sort By :
									</label>
									<div class="col-md-3">
										<s:select id="sortOpt"
											list="#{'firstName,lastName':'Staff Wise', 'floorName,floorLevel':'Floor Wise','roomName,roomLevel':'Room Wise','bedName,bedLevel':'Bed Wise'}"
											cssClass="form-control small" headerKey=""
											headerValue="- Select -" name="queryString" />
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-2 col-md-12">
										<s:submit id="submitButton" value="Generate Pdf"
											cssClass="submitBt btn blue" title="generate report"
											cssStyle="cursor:pointer" />
									</div>
								</div>
							</div>
						</s:form>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Classes and hostel details are not available.
						</div>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
changePageTitle("Hostel Staff Details")
$("input:checkbox, input:radio:not('.toggle')").uniform(); 
	$("input[name=buildingCheckBoxes]").click(function() {
		if ($("input[name=buildingCheckBoxes]:unchecked").length > 0) {
			$(".allCommunities").parent('span').removeClass("checked");
			$(".allCommunities").attr("checked", false);
		} else {
			$(".allCommunities").parent('span').addClass("checked");
			$(".allCommunities").attr("checked", true);
		}
	});
	
	$("input[name=genderName]").click(function() {
		if ($("input[name=genderName]:unchecked").length > 0) {
			$(".allGenders").parent('span').removeClass("checked");
			$(".allGenders").attr("checked", false);
		} else {
			$(".allGenders").parent('span').addClass("checked");
			$(".allGenders").attr("checked", true);
		}
	});
});
function generateClassBuildingIds() {
	if (($("input[name=buildingCheckBoxes]:checked").length > 0) && ($("input[name=genderName]:checked").length > 0)) {
		var buildingIds = $("input[name=buildingCheckBoxes]:checked");
		var selectedBuildingIds = '';
		if (buildingIds.length > 0) {
			selectedBuildingIds = '(';
			for ( var i = 0; i < buildingIds.length; i++) {
				selectedBuildingIds += buildingIds[i].value + ', ';
			}
			selectedBuildingIds += '0)';
		}
		$("#buildingIds").val(selectedBuildingIds);
		var genderTypes = $("input[name=genderName]:checked");
		var selectedGenderTypes = '';
		if (genderTypes.length > 0) {
			selectedGenderTypes = '(';
			for ( var i = 0; i < genderTypes.length; i++) {
				selectedGenderTypes += "'" + genderTypes[i].value + "', ";
			}
			selectedGenderTypes += "' ')";
		}
		$("#genderTypes").val(selectedGenderTypes);
		return true;
	}   else if (($("input[name=buildingCheckBoxes]:checked").length == 0) && ($("input[name=genderName]:checked").length > 0)) {
		alert("Please select at least one building");
		return false;
	} else if (($("input[name=buildingCheckBoxes]:checked").length > 0) && ($("input[name=genderName]:checked").length == 0)) {
		alert("Please select gender.");
		return false;
	} else if (($("input[name=buildingCheckBoxes]:checked").length == 0) && ($("input[name=genderName]:checked").length > 0)) {
		alert("Please select at least one class and building");
		return false;
	} else if (($("input[name=buildingCheckBoxes]:checked").length == 0) && ($("input[name=genderName]:checked").length == 0)) {
		alert("Please select at least one building and gender");
		return false;
	} else if (($("input[name=buildingCheckBoxes]:checked").length > 0) && ($("input[name=genderName]:checked").length == 0)) {
		alert("Please select at least one class and gender");
		return false;
	} else if (($("input[name=buildingCheckBoxes]:checked").length == 0) && ($("input[name=genderName]:checked").length == 0)) {
		alert("Please select at least one class ,building and gender");
		return false;
	} else {
		return false;
	}
}
 
  function checkAllBuildings() {
	if ($(".allCommunities").is(':checked')) {
		$("[name='buildingCheckBoxes']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("[name='buildingCheckBoxes']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}
  function checkAllGenders() {
	if ($(".allGenders").is(':checked')) {
		$("[name='genderName']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("[name='genderName']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}
</script>
