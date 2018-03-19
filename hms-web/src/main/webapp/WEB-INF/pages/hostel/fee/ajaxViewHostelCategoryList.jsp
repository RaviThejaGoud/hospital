<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="searchDiv grid_14 commonFormTabs">
	<div class="hideSearchStudentBody">
		<s:if
			test="%{hostelCategoriesList!= null || !hostelCategoriesList.isEmpty}">
			<s:form id="addSchoolWideBooks" action="#" method="post"
				theme="css_xhtml">
				<div class="grid_13 alpha" style="width: 785px;">
				<jsp:include page="/common/messages.jsp"></jsp:include>
					<div class="grid_4 alpha">
						<h1>
							Select Staff/Student Type:
						</h1>
					</div>
					<div class="grid_6">
						<div class="grid_2">
							<input type="radio" value="Student" id="Student"
								onclick="changeStudnetOrStaff(this.value);" name="addStaffToHostel" class="radio" checked="checked">
							Student
						</div>
						<div class="grid_2">
							<input type="radio" value="Staff" id="Staff"
								onclick="changeStudnetOrStaff(this.value);" name="addStaffToHostel" class="radio">
							Staff
						</div>
					</div>
				</div>
				<fieldset>
					<div class="grid_8" id="Staffs">
						<div class="grid_9">
							<div class="grid_4">
								<label class="labelRight">
									Select Staff Category:
								</label>
							</div>
							<div class="grid_5">
								<s:select list="hostelCategoriesList" listKey="id"
									listValue="categoryName" required="true" cssClass="categoriesCont"
									onchange="javascript:getAjaxDoHostelCategoryFee(this.value);"
									name="tempId1" theme="simple" headerKey="null"
									headerValue="-Select Category-" requiredposition="first">
								</s:select>
							</div>
						</div>
					</div>
					<div class="grid_8" id="Students">
						<div class="grid_9">
							<div class="grid_4">
								<label class="labelRight">
									Select Student Category:
								</label>
							</div>
							<div class="grid_5">
								<s:select list="hostelCategoriesList" listKey="id"
									listValue="categoryName" required="true" cssClass="categoriesCont"
									onchange="javascript:getAjaxDoHostelCategoryFee(this.value);"
									name="tempId1" theme="simple" headerKey="null"
									headerValue="-Select Category-" requiredposition="first">
								</s:select>
							</div>
						</div>
					</div>
				</fieldset>
			</s:form>
		</s:if>
	</div>
</div>
<div id="searchStudentsCategoryList" class="grid_14"></div>
<script type="text/javascript">
$('#Staffs').hide();
function changeStudnetOrStaff(staffType) {
	$('.categoriesCont').val('');
	if (staffType == 'Staff') {
		$('#searchStudentsCategoryList').hide();
		$('#Students').hide();
		$('#Staffs').show();
		//$('input#tempId1').val('Staff First or Last Name');
	} else {
		$('#searchStudentsCategoryList').hide();
		$('#Students').show();
		$('#Staffs').hide();
		//$('input#tempId1').val('Student First or Last Name');
	}
}

$(document).ready(function() {
	changePageTitle("Hostel Student & Staff Fee");
	$('.blockHeader h2').html('Manage Academics');
	var hostelCategoryId =$('.categoriesCont').val();
	if (isNonEmpty(hostelCategoryId)) {
		getAjaxDoHostelCategoryFee(hostelCategoryId);
	}
});
$.subscribe('doDisplayFee', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});
function getAjaxDoHostelCategoryFee(hostelCategoryId) {
	var type = $('input[name="addStaffToHostel"]:checked').attr('id');
	if (isNonEmpty(hostelCategoryId)) {
		var pars = "hostelCategoryId=" + hostelCategoryId +"&type="+type;
		$("#searchStudentsCategoryList")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url
				.getChatURL("/hostel/ajaxAdminGetHostelCategoryFee.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#searchStudentsCategoryList").html(html);
				$("#searchStudentsCategoryList").show();
			}
		});
	}
}
</script>
