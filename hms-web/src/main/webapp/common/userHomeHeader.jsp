<%@ include file="/common/taglibs.jsp"%>
<script  type="text/javascript">
$(document).ready(function() {	
var imagePath =  $('img#customerLogoDiv').attr("src");
		if(isNonEmpty(imagePath) && imagePath !=null && imagePath != "/images/common/photo_notAvailable.jpg"){
			$('img#customerLogoDiv').prop('src',"");
			$('img#customerLogoDiv').prop('src', imagePath + '?' + Math.random())
			 //alert("ssssss="+imagePath+"----"+Math.random());
		} 
		var profileImagePath =  $('img#userProfileImageDiv').attr("src");
		if(isNonEmpty(profileImagePath) && profileImagePath !=null && profileImagePath != "/images/common/photo_notAvailable.jpg"){
			$('img#userProfileImageDiv').prop('src',"");
			$('img#userProfileImageDiv').prop('src', profileImagePath+ '?' + Math.random())
			 //alert("ssssss="+imagePath+"----"+Math.random());
		} 
});
</script>
<s:if test='%{user.isParent=="Y" || user.isSchoolStudent=="Y" || user.isSecretary=="Y" || user.isSecretaryPA=="Y" || user.isSchoolManager=="Y"}'>
	<s:if test="%{#session.SessionIsOldStudent != null && !#session.SessionIsOldStudent.isEmpty()}">
		<jsp:include page="/common/alumneeUsersHeaderPage.jsp" />
	</s:if>
	<s:else>
		<jsp:include page="/common/parentAndStudentHeaderPage.jsp" />
	</s:else>
</s:if>
<s:else>
		<jsp:include page="/common/adminHeaderPage.jsp" />
</s:else>
<script  type="text/javascript">
$(document).ready(function() {
	changePageTitle("Manage Academics");
	$('a.academicYearsId').html($('ul#themes_selector').find('li#' + $('input#currentSelectedAcademicYear').val()).attr('class'));
	if (typeof (getUrlVars()["id"]) != "undefined") {
		$('.page-sidebar-menu li').removeClass('active');
		$('.navbar-nav li').removeClass('active');
		$('a#' + getUrlVars()["id"]).parent('li').addClass('active');
	}
	 
	<%  
	session.setAttribute( "customerVision" , "" );
	session.setAttribute( "customerMission" , "" );   
	session.setAttribute( "dateExceeded" , "" );  
	session.setAttribute( "passwordStatus" , "" );
	%>  
});
function getAcademicYearView(liObj) {
	$("#acedemicId").val($(liObj).attr('id'));
	var academicYearName = $(liObj).html().trim();
	if (isNonEmpty(academicYearName)) {
		$("#academicYearName").val(academicYearName);
	}
	document.myform.submit();
}

function loadBranchData(liObj) {
	$("#customerId").val($(liObj).attr('id'));
	document.branchesForm.submit();
}
$("a#urlDownloadStudTT").click(function() {
	$("span.hidden-481").html("Student-->Time Table");
});
$category = $('#SchoolName');
$category.change(function() {
	var searchWord = $( "#SchoolName option:selected" ).text();
	 if (searchWord.length == 0 || searchWord == 'Search for school name') {
		alert("!Oops enter school name.");
	} else { 
	var url = jQuery.url.getChatURL("/admin/ajaxSchoolInfo.do");
	// alert(url);
	$("#mainContentDiv") .html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars = "tempString=" + searchWord;
	//alert(pars);
	$.ajax( {
	url : url,
	cache : false,
	data : pars,
	success : function(html) {
		$("#mainContentDiv").html(html);
	}
	});
	}
		$("#searchString").val('');
}); 
function adminSearchResultsDiv() {
	var searchWord = $('#searchString').val();
	//alert(searchWord);
	if (searchWord.length == 0
			|| searchWord == 'Oops enter student information .') {
		alert("!Oops enter student information.");
	} else {
		var url = jQuery.url.getChatURL("/admin/ajaxShowStudentDetails.do");
		// alert(url);
		$("#mainContentDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "anyTitle=" + searchWord;
		//alert(pars);
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#mainContentDiv").html(html);
				//$("#searchString").val('');
		}
		});
	}
	$("#searchString").val('');
}
$('#globalSearchFormId').submit(function(event){
	adminSearchResultsDiv();
	event.preventDefault(); // stop the actual submit
});

function validateExcelSheet(obj){
	 var val = $("input#"+$(obj).attr("id")).val();
	if(isNonEmpty(val)){
		var allowedExtensions = ['xls'];
		var ext = val.substring(val.lastIndexOf('.') + 1).toLowerCase(); 
		if (allowedExtensions.indexOf(ext) === -1) {
    	 	alert('Invalid file Format. Only ' + allowedExtensions.join(', ') + ' files are allowed.');
    	 $("input#"+$(obj).attr("id")).val('');
    	 	return false; 
  	 	}
	}
}

function validateExcelSheetXlsNdXLSX(obj){
	 var val = $("input#"+$(obj).attr("id")).val();
	if(isNonEmpty(val)){
		var allowedExtensions = ['xls','xlsx'];
		var ext = val.substring(val.lastIndexOf('.') + 1).toLowerCase(); 
		if (allowedExtensions.indexOf(ext) === -1) {
   	 	alert('Invalid file Format. Only ' + allowedExtensions.join(', ') + ' files are allowed.');
   	 $("input#"+$(obj).attr("id")).val('');
   	 	return false; 
 	 	}
	}
}


function validateImage(obj){
	 var val = $("input#"+$(obj).attr("id")).val();
	if(isNonEmpty(val)){
		var allowedExtensions = ['jpg','jpeg','png'];
		var ext = val.substring(val.lastIndexOf('.') + 1).toLowerCase(); 
		if (allowedExtensions.indexOf(ext) === -1) {
    	 	alert('Invalid file Format. Only ' + allowedExtensions.join(', ') + ' files are allowed.');
    	 $("input#"+$(obj).attr("id")).val('');
    	 	return false; 
  	 	}
	}
}

</script>