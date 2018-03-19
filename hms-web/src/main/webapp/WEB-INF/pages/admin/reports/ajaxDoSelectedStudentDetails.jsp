<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-481"> </span>
				</div>
			</div>
			<div class="portlet-body" id="studRegister">
					<div class="tab-content">
						<s:form id="downloadTemplate"
							action="ajaxDownloadSelectedStudentDetails"
							enctype="multipart/form-data" method="post" theme="simple"
							cssClass="form-horizontal" namespace="/reports"
							onsubmit="javascript:return generateClassIds();">
							<h4 class="pageTitle bold">
								Download Students Details
							</h4>
							<div class="spaceDiv"></div>
								<p>
									<span class="label label-danger">NOTE :</span>&nbsp; Please
									select the classes and fields which you want to show in the
									report and click " Download" button to download the report.
								</p>
							<s:hidden id="classNameIds" name="selectedId" />
							<s:hidden id="classNames" name="tempString" />
							<s:hidden id="selectedColumnNames" name="anyId" />
							<s:hidden id="selectedColumnNamesDesc" name="plSubjectName" />
							
							<s:hidden id="selectedFilterNames" name="filterName"/>
							<div class="form-body">
								<div class="form-group">
									<div class="col-md-12">
										<div class="checkbox-list">
											<label class="checkbox-inline">
												<input type="checkbox" name="" value=""
													onClick="allStudentFileds()" class="checkbox studentFileds">
												All Student Fields
											</label>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4">
										<input type="checkbox" value="admissionNumber" alt="Admission Number"
											name="columnNameChkBoxSelectedIds" />
										Admission Number
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="firstName" alt="First Name"
											name="columnNameChkBoxSelectedIds" />
										First Name
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="lastName" alt="Last Name"
											name="columnNameChkBoxSelectedIds" />
										Last Name
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="dateOfBirth" alt="Date Of Birth"
											name="columnNameChkBoxSelectedIds" />
										Date Of Birth
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="dateOfJoining" alt="Date Of Joining"
											name="columnNameChkBoxSelectedIds" />
										Date Of Joining
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="gender" alt="Gender"
											name="columnNameChkBoxSelectedIds" />
										Gender
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="bloodGroup" alt="Blood Group"
											name="columnNameChkBoxSelectedIds" />
										Blood Group
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="className" alt="Class Name"
											name="columnNameChkBoxSelectedIds" />
										Class Name
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="section" alt="Section"
											name="columnNameChkBoxSelectedIds" />
										Section
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="rationCardNumber"  alt="RationCard Number"
											name="columnNameChkBoxSelectedIds" />
										RationCard Number
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="communityNumber" alt="Community Number"
											name="columnNameChkBoxSelectedIds" />
										Community Number
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="sslcNumber" alt="SSLC Number"
											name="columnNameChkBoxSelectedIds" />
										SSLC Number
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="tmrNumber" alt="TMR Number"
											name="columnNameChkBoxSelectedIds" />
										TMR Number
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="fatherName"  alt="Father Name"
											name="columnNameChkBoxSelectedIds" />
										Father Name
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="fatherOccupation" alt="Father Occupation"
											name="columnNameChkBoxSelectedIds" />
										Father Occupation
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="motherName" alt="Mother Name"
											name="columnNameChkBoxSelectedIds" />
										Mother Name
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="motherOccupation" alt="Mother Occupation"
											name="columnNameChkBoxSelectedIds" />
										Mother Occupation
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="parentEmail" alt="Parent Email"
											name="columnNameChkBoxSelectedIds" />
										Parent Email
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="phoneNumber" alt="Phone Number"
											name="columnNameChkBoxSelectedIds" />
										Phone Number
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="mobileNumber" alt="Mobile Number"
											name="columnNameChkBoxSelectedIds" />
										Mobile Number
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="motherToung" alt="Mother Tongue"
											name="columnNameChkBoxSelectedIds" />
										Mother Tongue
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="nationality" alt="Nationality"
											name="columnNameChkBoxSelectedIds" />
										Nationality
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="teeth" alt="Teeth"
											name="columnNameChkBoxSelectedIds" />
										Teeth
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="oralHygiene" alt="Oral Hygiene"
											name="columnNameChkBoxSelectedIds" />
										Oral Hygiene
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="height" alt="Height"
											name="columnNameChkBoxSelectedIds" />
										Height
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="addressLine1" alt="Address Line1"
											name="columnNameChkBoxSelectedIds" />
										Address Line1
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="city" alt="City"
											name="columnNameChkBoxSelectedIds" />
										City
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="stateName" alt="State Name"
											name="columnNameChkBoxSelectedIds" />
										State Name
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="postalCode" alt="Postal Code"
											name="columnNameChkBoxSelectedIds" />
										Postal Code
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="transportMode" alt="Transport Mode"
											name="columnNameChkBoxSelectedIds" />
										Transport Mode
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="familyDoctor" alt="Family Doctor"
											name="columnNameChkBoxSelectedIds" />
										Family Doctor
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="prefferedHospital"  alt="Preffered Hospital"
											name="columnNameChkBoxSelectedIds" />
										Preffered Hospital
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="castName" alt="Community Name"
											name="columnNameChkBoxSelectedIds" />
										Community Name
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="religion" alt="Religion"
											name="columnNameChkBoxSelectedIds" />
										Religion
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="relievingDate" alt="Relieving Date"
											name="columnNameChkBoxSelectedIds" />
										Relieving Date
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="scholarShipInfo" alt="ScholarShip Info"
											name="columnNameChkBoxSelectedIds" />
										ScholarShip Info
									</div>
									
									<div class="col-md-4">
										<input type="checkbox" value="annualIncome" alt="Annual Income"
											name="columnNameChkBoxSelectedIds" />
										Annual Income
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="weight" alt="Weight"
											name="columnNameChkBoxSelectedIds" />
										Weight
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="identification1" alt="Identification1"
											name="columnNameChkBoxSelectedIds" />
										Identification1
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="registerNumber" alt="Register Number"
											name="columnNameChkBoxSelectedIds" />
										Register Number
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="identification2" alt="Identification2"
											name="columnNameChkBoxSelectedIds" />
										Identification2
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="placeOfBirth" alt="Place Of Birth"
											name="columnNameChkBoxSelectedIds" />
										Place Of Birth
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="lastSchool" alt="Last School Name"
											name="columnNameChkBoxSelectedIds" />
										Last School Name
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="studentMobile" alt="Student Mobile Number"
											name="columnNameChkBoxSelectedIds" />
										Student Mobile Number 
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="studentEmail" alt="Student E-Mail "
											name="columnNameChkBoxSelectedIds" />
										Student E-Mail 
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="aadharNumber" alt="Aadhaar Card No"
											name="columnNameChkBoxSelectedIds" />
										Aadhaar Card No 
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="bplStatus" alt="Below Poverty Line (BPL)"
											name="columnNameChkBoxSelectedIds" />
										Below Poverty Line (BPL)  
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="rteStatus" alt="RTE Student"
											name="columnNameChkBoxSelectedIds" />
										RTE Student
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="phId" alt="Student Disability"
											name="columnNameChkBoxSelectedIds" />
										Student Disability
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="physicallyHandicappedDesc" alt="Disability Details"
											name="columnNameChkBoxSelectedIds" />
										Disability Details
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="houseType" alt="House Type"
											name="columnNameChkBoxSelectedIds" />
										House Type
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="studentBankId" alt="Student BankId"
											name="columnNameChkBoxSelectedIds" />
										Student BankId
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="mediumOfStudy" alt="Medium Of Instruction"
											name="columnNameChkBoxSelectedIds" />
										Medium Of Instruction
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="stsNumber" alt="STS Number"
											name="columnNameChkBoxSelectedIds" />
										 STS Number
									</div>
									<!--  Below two elements should be in last only. If we need to add any new elements please add above this Div. -->
										<div class="col-md-4">
										<input type="checkbox" value="coreSubjects" 
											name="columnNameChkBoxSelectedIds" alt="Core Subjects"/>
										Core Subjects
									</div>
									<div class="col-md-4">
										<input type="checkbox" value="languageSubject" alt="Language Offered"
											name="columnNameChkBoxSelectedIds" />
										Language Offered
									</div>
								
									
								</div>							
								<div class="spaceDiv"></div>
								<div class="form-group">
									<div class="col-md-12">
										<div class="checkbox-list">
											<label class="checkbox-inline">
											<input type="checkbox" name="" value=""
												onClick="checkAllFilters()" class="checkbox allFilters">
												All Filters
											</label>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="conLable col-md-3 control-label ">
										Select Filter :
									</label>
										<div class="col-md-12">
											<div class="checkbox-list">
												<label class="checkbox-inline">
													<input type="checkbox" name="chkBoxSelectedfilters" value="RTE" >
													 RTE
												</label>
									 	 </div>
									</div>
								</div>
								<div class="spaceDiv"></div>
								<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
									<div class="form-group">
										<div class="col-md-12">
											<div class="checkbox-list">
												<label class="checkbox-inline">
													<input type="checkbox" name="" value=""
														onClick="checkAllClasses()" class="checkbox allClasses">
													All Class & Sections
												</label>
											</div>
										</div>
									</div>
								
								<div class="form-group">
									<label class="conLable col-md-3 control-label">
										Class With Students :
									</label>
									<div class="col-md-12">
										<div class="checkbox-list">
											<s:checkboxlist name="chkBoxSelectedIds"
												list="studyClassList" listKey="id"
												listValue="classAndSection" theme="ems" cssClass="small" />
										</div>
									</div>
								</div>
								</s:if>
								<s:else>
									<div class="alert alert-info">
										Currently there are no class with students.
									</div>
								</s:else>
								<s:if test='%{tempList2.size >0}'>
									<div class="form-group">
										<label class="conLable col-md-3 control-label">
											Class With Out Students :
										</label>
										<div class="col-md-12">
											<div class="checkbox-list">
												<s:checkboxlist name="chkBoxNotStudents" list="tempList2"
													listKey="id" listValue="classAndSection" theme="ems"
													cssClass="small" disabled="true" />
											</div>
										</div>
									</div>
								</s:if>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<s:submit type="submit small" value="Download Excel"
											cssClass="submitBt btn blue long"
											title="Downlaod excel report" />
									</div>
								</div>
							</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
$(document).ready(
		function() {
			$("input:checkbox, input:radio").uniform();
			var title = '';
			$('span.hidden-481').html($('.page-sidebar-menu li.active').find('li.active').children('a').children('span.title').text().trim() + "-->"
							+ $('.page-sidebar-menu li.active').find( 'li.active').find('ul.sub-menu').find('li.active').children('a').text().trim())
			  title = $('.page-sidebar-menu li.active').find('li.active').find('ul.sub-menu').find('li.active').children('a').text().trim();
			if(!isNonEmpty(title)){
					 $('span.hidden-481').html($('.navbar-nav li.active').children('a').children('span.title').text().trim() + "-->"
								+ $('.navbar-nav li.active').find('li.active').find('ul.dropdown-menu').find('li.active').children('a').text().trim())
				  title = $('.navbar-nav li.active').find('li.active').find('ul.dropdown-menu').find('li.active').children('a').text().trim();
			}
			changePageTitle(title);
		});
function generateClassIds() {
	if (($("input[name=chkBoxSelectedIds]:checked").length == 0)
			&& ($("input[name=columnNameChkBoxSelectedIds]:checked").length == 0)) {
		alert("Please select at least two column names and one class name.");
		return false;
	} else if (($("input[name=chkBoxSelectedIds]:checked").length > 0)
			&& ($("input[name=columnNameChkBoxSelectedIds]:checked").length < 2)) {
		alert("Please select at least two column names .");
		return false;
	} else if ((($("input[name=chkBoxSelectedIds]:checked").length == 0)
			&& ($("input[name=columnNameChkBoxSelectedIds]:checked").length > 0) 
			&& ($("input[name=chkBoxSelectedfilters]:checked").length == 0)) || (($("input[name=chkBoxSelectedIds]:checked").length == 0) 
			&& ($("input[name=columnNameChkBoxSelectedIds]:checked").length > 0) && ($("input[name=chkBoxSelectedfilters]:checked").length > 0)))  {
		alert("Please select at least one class name.");
		return false;
	} else {
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var columnNameIds = $("input[name=columnNameChkBoxSelectedIds]:checked");
		var filterNameIds = $("input[name=chkBoxSelectedfilters]:checked");
		var selectedClassIds = '';
		var selectedClassNames = '';
		var selectedColumnNameIds = [];
		var selectedColumnNameValues = [];
		var selectFilterIds = [];
		var selectedColumnNameIdsq = [];
		if (classIds.length > 0) {
			selectedClassIds = '(';
			$(classIds).each(
					function() {
						selectedClassIds += $(this).val() + ', ';
						if (isNonEmpty($(this).parents('label').text()))
							selectedClassNames += $(this).parents('label')
									.text().replace(/^\s+|\s+$/g, ' ');
					});
			selectedClassIds += '0)';
		}
		if (columnNameIds.length > 0) {	
			$(columnNameIds).each(function() {
				if (isNonEmpty($(this).val()))
				selectedColumnNameIds.push($(this).val().trim());
				selectedColumnNameValues.push($(this).attr('alt').trim());
				
				/* if($(this).val().trim()=="phId")
					selectedColumnNameIds += ',physicallyHandicappedDesc'; */
			});
		} 
		if(filterNameIds.length > 0) {
			$(filterNameIds).each(function() {
			if(isNonEmpty($(this).val()))
				selectFilterIds.push($(this).val().trim());
			});
		}
		$("#classNameIds").val(selectedClassIds);
		$("#classNames").val(selectedClassNames);
		$("#selectedColumnNames").val(selectedColumnNameIds);
	   $("#selectedColumnNamesDesc").val(selectedColumnNameValues);
		$("#selectedFilterNames").val(selectFilterIds);
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
$("input[name=chkBoxSelectedIds]").click(function() {
	if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		$(".allClasses").parent('span').removeClass("checked");
		$(".allClasses").attr("checked", false);
	} else {
		$(".allClasses").parent('span').addClass("checked");
		$(".allClasses").attr("checked", true);
	}
});
function checkAllFilters(){
	if($(".allFilters").is(':checked')){
	   $("[name='chkBoxSelectedfilters']").each(function(){
		   $(this).parent('span').addClass('checked');
		   $(this).attr("checked", "true");
	   });
	} else {
		$("[name='chkBoxSelectedfilters']").each(function(){
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}
$("input[name=chkBoxSelectedfilters]").click(function() {
	if ($("input[name=chkBoxSelectedfilters]:unchecked").length > 0) {
		$(".allFilters").parent('span').removeClass("checked");
		$(".allFilters").attr("checked", false);
	} else {
		$(".allFilters").parent('span').addClass("checked");
		$(".allFilters").attr("checked", true);
	}
});
function allStudentFileds() {
	if ($(".studentFileds").is(':checked')) {
		$("[name='columnNameChkBoxSelectedIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("[name='columnNameChkBoxSelectedIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
}
$("input[name=columnNameChkBoxSelectedIds]").click(function() {
	if ($("input[name=columnNameChkBoxSelectedIds]:unchecked").length > 0) {
		$(".studentFileds").parent('span').removeClass("checked");
		$(".studentFileds").attr("checked", false);
	} else {
		$(".studentFileds").parent('span').addClass("checked");
		$(".studentFileds").attr("checked", true);
	}
});
</script>