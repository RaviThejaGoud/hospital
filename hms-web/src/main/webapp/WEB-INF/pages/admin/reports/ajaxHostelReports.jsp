<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Admin--->Hostel Student Details
				</div>
			</div>
			<div class="portlet-body">
				<div id="mainContentDiv" class="tab-content">
					<s:if test="%{(buildingList != null && !buildingList.isEmpty()) && (classList != null && !classList.isEmpty())}">
						<s:form action="ajaxGenerateHostelReports" theme="simple"
							cssClass="form-horizontal" namespace="/reports"
							onsubmit="return generateClassBuildingIds();" id="hostelReports"
							method="post">
							<s:hidden id="classNameIds" name="anyId" />
							<s:hidden id="buildingIds" name="anyTitle" />
							<s:hidden id="genderTypes" name="tempString" />
							<div class="form-body">
								<div class="form-group">
									<label class="conLable col-md-3 control-label">
										<span class="required">*</span> Available Classes :
									</label>
									<div class="col-md-12">
										<div class="checkbox-list">
											<label class="checkbox-inline">
												<input type="checkbox" name="" value=""
													onClick="checkAllClasses()" class="checkbox allClasses">
												All Classes
											</label>
										</div>
										<s:checkboxlist name="chkBoxSelectedIds" list="classList"
											listKey="id" listValue="className" theme="ems"
											cssClass="small" />
									</div>
								</div>
								<div class="form-group">
									<label class="conLable col-md-3 control-label">
										<span class="required">*</span> Available Buildings :
									</label>
									<div class="col-md-12">
										<div class="checkbox-list">
											<label class="checkbox-inline">
												<input type="checkbox" name="" value=""
													onClick="checkAllBuildings()"
													class="checkbox allCommunities">
												All Buildings
											</label>
										</div>
										<s:checkboxlist name="buildingCheckBoxes" list="buildingList"
											listKey="id" listValue="buildingName" theme="ems"
											cssClass="small" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label"> <span
										class="required">*</span> Select Gender :
									</label>
									<div class="col-md-8">
										<div class="checkbox">
											<label class="checkbox"> <input type="checkbox"
												value="" onClick="checkAllGenders()"
												class="checkbox allGenders"> All Gender
											</label> <label class="checkbox"> <input type="checkbox"
												name="genderName" value="M"> Male
											</label> <label class="checkbox"> <input type="checkbox"
												name="genderName" value="F"> Female
											</label>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">
										Sort By :
									</label>
									<div class="col-md-3">
										<s:select id="sortOpt"
											list="#{'firstName,lastName':'Student Wise','className,section':'Class Wise','floorName,floorLevel':'Floor Wise','roomName,roomLevel':'Room Wise','bedName,bedLevel':'Bed Wise'}"
											cssClass="form-control" headerKey="" headerValue="- Select -"
											name="queryString" />
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-6">
										<div class="col-md-offset-3 col-md-9">
											<s:submit id="submitButton" value="Generate Pdf"
												cssClass="btn blue" title="generate report"
												onclick="reportType()" cssStyle="cursor:pointer" />
										</div>
									</div>
								</div>
							</div>
						</s:form>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Hostel details are not available. Please login as a hostler to add the details.
						</div>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
changePageTitle('Hostel Student Details');
$("input:checkbox, input:radio:not('.toggle')").uniform(); 
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
			$(".allClasses").parent('span').removeClass("checked");
			$(".allClasses").attr("checked", false);
		} else {
			$(".allClasses").parent('span').addClass("checked");
			$(".allClasses").attr("checked", true);
		}
	});
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
	if ((($("input[name=chkBoxSelectedIds]:checked").length > 0)
			&& ($("input[name=buildingCheckBoxes]:checked").length > 0) && ($("input[name=genderName]:checked").length > 0))) {
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedClassIds = '';
		if (classIds.length > 0) {
			selectedClassIds = '(';
			for ( var i = 0; i < classIds.length; i++) {
				selectedClassIds += classIds[i].value + ', ';
			}
			selectedClassIds += '0)';
		}
		$("#classNameIds").val(selectedClassIds);
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
	} else if ((($("input[name=chkBoxSelectedIds]:checked").length == 0)
			&& ($("input[name=buildingCheckBoxes]:checked").length > 0) && ($("input[name=genderName]:checked").length > 0))) {
		alert("Please select at least one Class");
		return false;
	} else if ((($("input[name=chkBoxSelectedIds]:checked").length > 0)
			&& ($("input[name=buildingCheckBoxes]:checked").length == 0) && ($("input[name=genderName]:checked").length > 0))) {
		alert("Please select at least one building");
		return false;
	} else if ((($("input[name=chkBoxSelectedIds]:checked").length > 0)
			&& ($("input[name=buildingCheckBoxes]:checked").length > 0) && ($("input[name=genderName]:checked").length == 0))) {
		alert("Please select at least gender.");
		return false;
	} else if ((($("input[name=chkBoxSelectedIds]:checked").length == 0)
			&& ($("input[name=buildingCheckBoxes]:checked").length == 0) && ($("input[name=genderName]:checked").length > 0))) {
		alert("Please select at least one class and building");
		return false;
	} else if ((($("input[name=chkBoxSelectedIds]:checked").length > 0)
			&& ($("input[name=buildingCheckBoxes]:checked").length == 0) && ($("input[name=genderName]:checked").length == 0))) {
		alert("Please select at least one building and gender");
		return false;
	} else if ((($("input[name=chkBoxSelectedIds]:checked").length == 0)
			&& ($("input[name=buildingCheckBoxes]:checked").length > 0) && ($("input[name=genderName]:checked").length == 0))) {
		alert("Please select at least one class and gender");
		return false;
	} else if ((($("input[name=chkBoxSelectedIds]:checked").length == 0)
			&& ($("input[name=buildingCheckBoxes]:checked").length == 0) && ($("input[name=genderName]:checked").length == 0))) {
		alert("Please select at least one class ,building and gender");
		return false;
	} else {
		return false;
	}
}
function checkAllClasses() {
	if ($(".allClasses").is(':checked')) {
		$("[name='chkBoxSelectedIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("[name='chkBoxSelectedIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
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
function reportType() {
	$('.anyId').val('PDF');
}
</script>
