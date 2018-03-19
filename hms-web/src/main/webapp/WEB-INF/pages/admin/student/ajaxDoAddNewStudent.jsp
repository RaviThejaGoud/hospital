<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"src="${pageContext.request.contextPath}/scripts/common/textcounter.js"></script>
<jsp:include page="/common/messages.jsp" />
<div class="form-body">
	<s:form action="ajaxAddStudent" theme="simple" id="addStudent"
		method="post" enctype="multipart/form-data" cssClass="form-horizontal"
		namespace="/student">
		<input type="hidden" name="studyClassId"
			value="<s:property value="studyClassId"/>">
		<s:hidden name="tempId1" id='<s:property value="tempId1"/>'></s:hidden>
		<s:hidden name="tempId2" id='<s:property value="tempId2"/>'></s:hidden>
		<s:hidden name="plTitle" id='<s:property value="plTitle"/>'></s:hidden>
		<h4 class="pageTitle bold form-section">CASTE AND COMMUNITY
			INFORMATION</h4>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Nationality : </label>
					<div class="col-md-7">
						<sj:textfield name="personVo.nationality" id="nationality"
							cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Select Religion : </label>
					<div class="col-md-7">
						<s:select list="tempList1" listKey="id" listValue="skillTypeName"
							cssClass="form-control input-medium" theme="simple"
							name="personVo.religionId" headerKey="" headerValue="- Select -">
						</s:select>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Community : </label>
					<div class="col-md-7">
						<s:select id="castName" list="castSettingList" listKey="id"
							name="personVo.castId" theme="simple"
							cssClass="form-control input-medium" listValue="castName"
							headerKey="" headerValue="- Select -"
							onchange="javascript:getSubCastDetailsByCast(this.value);" />
					</div>
				</div>
			</div>
			<div id="resultsDiv2" class="col-md-6">
				<jsp:include
					page="/WEB-INF/pages/admin/student/ajaxStudentSubCastListByCast.jsp" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Community Number : </label>
					<div class="col-md-7">
						<sj:textfield name="personVo.communityNumber" id="communityNumber"
							cssClass="form-control input-medium as-inpu" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Ration Card Number :
					</label>
					<div class="col-md-7">
						<sj:textfield name="personVo.rationCardNumber" id="rationCardNumber"
							cssClass="form-control input-medium as-inpu" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<!-- <div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Select Category :
				</label>
				<div class="col-md-7">
					<s:select id="categoryId" list="schoolCategoriesList"
						cssClass="required form-control input-medium" listKey="id"
						listValue="categoryName" theme="simple" headerKey=""
						headerValue="- Select Category-" name="categoryName" />
				</div>
			</div>
		</div> -->
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Select Mother Tongue
						: </label>
					<div class="col-md-7">
						<s:select list="objectList" listKey="id" listValue="name"
							cssClass="form-control input-medium" label="Select MotherToung"
							name="personVo.motherToungId" headerKey="" headerValue="- Select -">
						</s:select>
					</div>
				</div>
			</div>
		</div>

		<h4 class="pageTitle bold form-section">STUDENT IDENTIFICATION</h4>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Personal
						Identification1 : </label>
					<div class="col-md-7">
						<sj:textfield name="personVo.identification1" id="identification1Id"
							cssClass="form-control input-medium as-inpu" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Personal
						Identification2 : </label>
					<div class="col-md-7">
						<sj:textfield name="personVo.identification2" id="identification2Id"
							cssClass="form-control input-medium as-inpu" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<h4 class="pageTitle bold form-section">COLLECTED DOCUMENTS</h4>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Upload Documents : </label>
					<div class="col-md-7">
						<input id="photoURL" class="btn default" type="file" value=""
							multiple="multiple" name="fileUpload" id="uploadScannedDocs">
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Summary : </label>
					<div class="col-md-7">
						<sj:textarea name="personVo.summary" id="maxlength_textarea"
							cssClass="form-control"
							placeholder="This textarea has a limit of 200 chars." rows="4"
							cols="20" maxlength="200"></sj:textarea>
						<span class="help-block"> Maxlength is 200 chars. </span>
					</div>
				</div>
			</div>
		</div>
		<h4 class="pageTitle bold form-section">SELF AWARENESS :</h4>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Goals : </label>
					<div class="col-md-7">
						<sj:textarea name="studentVo.goals" id="maxlength_textarea1"
							cssClass="form-control"
							placeholder="This textarea has a limit of 200 chars." rows="4"
							cols="20" maxlength="200"></sj:textarea>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Strengths : </label>
					<div class="col-md-7">
						<sj:textarea name="studentVo.strengths" id="maxlength_textarea2"
							cssClass="form-control"
							placeholder="This textarea has a limit of 200 chars." rows="4"
							cols="20" maxlength="200"></sj:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Interests and
						Hobbies : </label>
					<div class="col-md-7">
						<sj:textarea name="studentVo.interestsAndHobbies"
							id="maxlength_textarea3" cssClass="form-control"
							placeholder="This textarea has a limit of 200 chars." rows="4"
							cols="20" maxlength="200"></sj:textarea>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Responsibilities
						Discharged : </label>
					<div class="col-md-7">
						<sj:textarea name="studentVo.responsibilities"
							id="maxlength_textarea4" cssClass="form-control"
							placeholder="This textarea has a limit of 200 chars." rows="4"
							cols="20" maxlength="200"></sj:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Exceptional
						Achievements : </label>
					<div class="col-md-7">
						<sj:textarea name="studentVo.achievements" id="maxlength_textarea5"
							cssClass="form-control"
							placeholder="This textarea has a limit of 2000 chars." rows="4"
							cols="20" maxlength="200"></sj:textarea>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Remarks : </label>
					<div class="col-md-7">
						<sj:textarea name="studentVo.remarks"
							id="maxlength_textarea4" cssClass="form-control"
							placeholder="This textarea has a limit of 200 chars." rows="4"
							cols="20" maxlength="200"></sj:textarea>
					</div>
				</div>
			</div>
		</div>
		<div>&nbsp;</div>
		<div class="form-actions fluid">
			<div class="col-md-6" style="float: right; width: 200px;">
				<div class="col-md-offset-0 col-md-13">

					<sj:submit cssClass="btn blue" value="Submit" indicator="indicator"
						targets="mainContentDiv" validate="true" formIds="addStudent"
						onCompleteTopics="validateStuPhdetails" />
					<s:url id="doCancelStudent" action="ajaxGetStudyClassList"
						includeParams="all" namespace="/student"></s:url>
					<sj:a href="%{doCancelStudent}" cssClass="btn default"
						indicator="indicator" targets="mainContentDiv" button="false">Cancel</sj:a>
				</div>
			</div>
			<div class="col-md-6" style="float: left; width: 150px;">
				<div class="col-md-offset-2 col-md-5">

					<s:url id="doAddStudentBackLink" action="ajaxDoAddStudent"
						includeParams="all" escapeAmp="false" namespace="/student">
					</s:url>
					<sj:a href="%{doAddStudentBackLink}" cssClass="btn default"
						targets="staffList" indicator="indicator"> Back</sj:a>
				</div>
			</div>
		</div>
	</s:form>
</div>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script language="JavaScript" type="text/javascript">
changePageTitle('Add Student Details');
FormAdvanced.init();
FormComponents.init();

/* $('li#doAddStudentBackLink').click( function() {
	window.history.back()
}); */

/*$(document).ready(function() {
	 $("#emailAddress").focus();
	$('.numeric').numeric();
});*/
function getSubCastDetailsByCast(selectBox) {
	var castId = $("select#castName").val();
	var url = jQuery.url
			.getChatURL("/admin/ajaxStudentSubCastDetailsByCast.do");
	if (castId.length == 0) {
		alert("!Oops select Cast.");
	} else {
		$("#resultsDiv2")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "personVo.castId=" + castId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#resultsDiv2").html(html);
				document.getElementById('resultsDiv2').style.display = "block";
				//document.getElementById('schoolBooksList').style.display="none";
		}
		});
	}
}
function back_page(){
	history.back(-1)
}
/* function back_block() {
	window.location.hash = "target=ES.ajaxify AMCS";
	window.history.back(-1)
	}
 function goBack() {
	alert("dsds");
	window.history.back(-1) = "target=MSTI.ajaxify MSTI";
    //window.history.back(-1)
}  */
/*this script only for chrome*/
/*$("#mobileNumber").change(function(){ 
	var text = $(this).val();
	var moble=text.replace('+','');
	var number=moble.replace('-','');
     if (Math.floor(number) != number) {
      alert("Please enter numbers.");
      $("#mobileNumber").val('');
      return  false;
     }
});
$("#phoneNumber").change(function(){ 
	var text = $(this).val();
     if (Math.floor(text) != text) {
      alert("Please enter numbers.");
      $("#phoneNumber").val('');
      return  false;
     }
});
$("#pincode").change(function(){ 
	var text = $(this).val();
     if (Math.floor(text) != text) {
      alert("Please enter numbers.");
      $("#pincode").val('');
      return  false;
     }
});

function validateMobNumbers(txtMobId) {
	var mob = /^(\+91-|\+91|0)?\d{10}$/;
	if (mob.test($.trim(txtMobId)) == false) {
		alert("Please enter valid mobile number.");
		$("#mobileNumber").val('');
		$("#mobileNumber").focus();
		return false;
	}else if (!(txtMobId.length == 14)) {
		alert("The phone number is the wrong length. \nPlease enter 10 digit mobile no.");
		$("#mobileNumber").val("");
		$("#mobileNumber").focus();
		return false;
	}else if((txtMobId.length == 14)) {
	return true;
	}
}*/
</script>