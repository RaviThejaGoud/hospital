<%@ include file="/common/taglibs.jsp"%>
<s:form id="studentGenerateHTInfoForm" method="post" theme="simple" action="ajaxGenerateHallTicket"
	onsubmit="return prepareNonSelectedStudIds();" cssClass="form-horizontal" namespace="/exam">
	<s:hidden name="classId" id="classId" value="%{classId}"></s:hidden>
	<s:hidden name="tempString" value="%{tempString}" />
	<s:hidden name="studentNumber" id="studNumbers"></s:hidden>
	<s:hidden name="tempString2" id="studyClassIds"></s:hidden>
	<s:hidden name="tempString1" id="subTypeIds"></s:hidden>
	<s:hidden name="tempString3" id="subTypeSubjIds"></s:hidden>
	<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
		
		<div class="form-group ">
			<label class="control-label col-md-2"> Select Section : </label>
			<div class="col-md-10"> 
				<s:checkboxlist name="chkBoxClassSelectedIds" id="chkBoxClassSelectedIds"  onclick=""
						list="studyClassList" listKey="id" listValue="sectionName" cssClass="required checkbox checked"  theme="ems" onchange="javascript:getClassStudentDetails()"/>
			</div>
		</div>
		<div id="examStudentList"></div>
	</s:if>
	<s:else>
	<div class="alert alert-info">
		Hall ticket template is not uploaded for selected. Please upload the template. 
	</div>
</s:else>
	
</s:form>
<div id="responsive"></div>
<script type="text/javascript">
	$(document).ready(function(){
		 $("input:checkbox, input:radio").uniform();
		 
		 $("input[name='chkBoxClassSelectedIds']").each(function(){
			$(this).attr("checked",true); 
			$(this).parent("span").addClass("checked");
		 });
		 var studyClassLength = '<s:property value="studyClassList.size"/>';
		 
		 if(isNonEmpty(studyClassLength) && studyClassLength >0){
			 getClassStudentDetails();
		 }
	}) 
	UIExtendedModals.init();
	function prepareNonSelectedStudIds(){
		var studentId = 0;
		var nonSelectedStudIds = '(';
		$('tr.feeNotPadiStudsDate').each(function() {
			studentId = $(this).find("td.studentId").attr('id');
			if(isNonEmpty(studentId) && studentId >0){
				if($('#payMent_'+studentId).attr("checked")){
					nonSelectedStudIds += (studentId+",");
				}
			}
		});
		nonSelectedStudIds += "0)";
		$('#studNumbers').val(nonSelectedStudIds);
		
		var subTypeIds=$("input[name=chkBoxFeeSelectedIds]:checked").map(function () {return this.value;}).get().join(",");
		if(subTypeIds.length>0){
			$('#subTypeIds').val("("+subTypeIds+",0)");
		}
		var subTypeSubjIds=$("input[name=selectBoxIdList]:checked").map(function () {
			return $(this).attr('id');
		}).get().join(",");
		if(subTypeSubjIds.length>0){
			$('#subTypeSubjIds').val("("+subTypeSubjIds+",0)");
		}
		if ($("input[name=chkBoxClassSelectedIds]:checked").length == 0) {
			alert("Please select at least one study class.");
			return false;
		}
		if ($("input[name=chkBoxFeeSelectedIds]:checked").length == 0) {
			alert("Please select at least one sub type.");
			return false;
		}
		if ($("input[name=selectBoxIdList]:checked").length == 0) {
			alert("Please select at least one subject.");
			return false;
		}
		if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
			alert("Please select at least one student.");
			return false;
		} 
	}
	var includeIds = '';
	function checkAllStudents() {
	if ($(".allInvoices").is(':checked')){
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
	 includeIds=$("input[name=chkBoxSelectedIds]:checked").map(function () {return this.value;}).get().join(",");
		if(includeIds.length>0){
			$('.studNumbers').val("("+includeIds+",0)");
		}else{
			$('.studNumbers').val("("+includeIds+"0)");
			alert("Please select at least one student.")
		}
	}
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
			 $(".allInvoices").parent('span').removeClass("checked");
			$(".allInvoices").attr("checked", false);
		} else {
		 $(".allInvoices").parent('span').addClass("checked");
			$(".allInvoices").attr("checked", true);
		}
		includeIds = $("input[name=chkBoxSelectedIds]:checked").map(function() {
			return this.value;
		}).get().join(",");
		if (includeIds.length > 0) {
			$('#studNumbers').val("(" + includeIds + ",0)");
		} else {
			$('#studNumbers').val("(" + includeIds + "0)");
			alert("Please select at least one student.")
		}
	});
	
	function getAjaxDoStudentFeeDuesList(eid) {
		var className = $('#classNameId :selected').text();
		var classId = $("#classId").val();
		var studyClassIds=$("input[name=chkBoxClassSelectedIds]:checked").map(function () {return this.value;}).get().join(",");
		if(studyClassIds.length>0){
			$('#studyClassIds').val("("+studyClassIds+",0)");
			studyClassIds = "("+studyClassIds+",0)";
		}
		if (studyClassId == "" || studyClassId == 0) {
			$("#studentDuesList").hide();
		} else {
			var pars = "classId=" + classId + "&tempString=" + className+ "&plTitle=HT" + "&tempId=" + eid+"&tempString2="+studyClassIds;
			$("#examStudentList").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/exam/ajaxGetStudentExamPendingDues.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#examStudentList").html(html);
					$("#examStudentList").show();
				}
			});
		}
	} 
	function PopupDueFeeDetials(id,classId) {
		var pars = "student.id=" + id + "&anyId=(1,2,3,4)";
			var url = jQuery.url.getChatURL("/schoolfee/ajaxViewFeeDetails.do");
			$('#responsive').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(html) {
						$("#responsive").html(html);
					}
				});
			}
	function getClassStudentDetails() {
		var className = $('#classNameId :selected').text();
		var classId = $("#classId").val();
		var eid = $("#eid").val();
		var studyClassIds=$("input[name=chkBoxClassSelectedIds]:checked").map(function () {return this.value;}).get().join(",");
		if(studyClassIds.length>0){
			$('#studyClassIds').val("("+studyClassIds+",0)");
			studyClassIds = "("+studyClassIds+",0)";
			$('#examStudentList').html('<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/exam/ajaxGetStudentExamPendingDues.do");
			var pars = "classId=" + classId + "&tempString=" + className+ "&plTitle=HT&anyTitle=S&tempString2="+studyClassIds;
			$.ajax( {
				type : "POST",
				url : url,
				data : pars,
				cache : false,
				success : function(html) {
					$("#examStudentList").html(html);
					$("#examStudentList").show();
				}
			});
		}
		
	  else{
		  $("#examStudentList").html('');
		  $("#examStudentList").html('<div class="alert alert-info">Please select atleast one study class.</div>');
	     alert('Please select atleat one study class.');
		 return false;
	  }
	}
</script>