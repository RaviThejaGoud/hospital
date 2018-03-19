<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{examTypeList != null && !examTypeList.isEmpty()}">
	<span id='<s:property value='classId'/>' class='classIdSpan'></span>
	<span id='<s:property value='anyTitle'/>' class='anyTitleSpan'></span>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-6">
					<span class="required">*</span>Select Exam Type :
				</label>
				<div class="col-md-5">
					<s:select list="examTypeList" listKey="id" listValue="examType"
						cssClass="required form-control input-medium" name="examType"
						onchange="javascript:getSchedulesByClassIdAndExamTypeId();"
						id="examTypeListId" theme="simple" />
				</div>
			</div>
		</div>
	</div>
</s:if>
<s:else>
	<s:if test='%{user.isSchoolTeacher=="Y"}'>
	<div class="alert alert-info">
			<font>Exam Types are not created for this class. Please contact your administrator to create the exam types.</font>
		</div>
	</s:if>
	<s:else>
	<div class="alert alert-info">
		<font> You do not have any active Exam Types. Please create <b><a href="#" id="createExamType">Exam Type </a></b> </font> <!--<a href="#">Exam Type</a>-->
	</div>
	</s:else>
</s:else>
<script type="text/javascript">
	$(document).ready(function() {
		getSchedulesByClassIdAndExamTypeId();
		var examTypeListSize='<s:property value="examTypeList.size"/>';
		if(examTypeListSize>0){
			$('#examScheduleSubjects').show();
		}else{
			$('#examScheduleSubjects').hide();
		}
	});
	$('a#createExamType').click(function(){
		window.location.hash="target=ADM.ajaxify GAE";
		$('li#examSchedulesDiv').parent('li').removeClass('active');
		$('li#gradesAndExamTypes').addClass('open active');
		$('li#gradesAndExamTypes').addClass('active');
		$('li#gradesAndExamTypes a').click();
	});
	
	function getSchedulesByClassIdAndExamTypeId(){
		var examTypeId = $('#examTypeListId').val();
		$('.examTypeId').val(examTypeId);
		var classId = $("span.classIdSpan").attr('id');
		var anyTitle= $("span.anyTitleSpan").attr('id');
		if (isNonEmpty(examTypeId) && isNonEmpty(classId)) {
			$('#examScheduleSubjects').html(
							'<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "examType=" + examTypeId + "&classId="+ classId+"&eventId="+anyTitle;
			if(anyTitle=='S')
			{
				$.ajax({
					url : jQuery.url.getChatURL("/exam/ajaxGetClassExamSyllabus.do"),
					cache : false,
					data : pars,
					success : function(response) {
						$('#examScheduleSubjects').html(response);
						$('#examScheduleSubjects').show();
						
						if(isNonEmpty($("div#examScheduleSubjects").find("div.alert-info").html())){
							$("div.sectionExamTypeButtons").find("input.submitBt").attr("disabled",true);
						}
						else{
							$("div.sectionExamTypeButtons").find("input.submitBt").attr("disabled",false);
						}
					}
				});
			}
			else
			{
				$.ajax({
					url : jQuery.url.getChatURL("/exam/ajaxGetExamShedules.do"),
					cache : false,
					data : pars,
					success : function(response) {
						$('#examScheduleSubjects').html(response);
						$('#examScheduleSubjects').show();
						
						if(isNonEmpty($("div#examScheduleSubjects").find("div.alert-info").html())){
							$("div.sectionExamTypeButtons").find("input.submitBt").attr("disabled",true);
						}
						else{
							$("div.sectionExamTypeButtons").find("input.submitBt").attr("disabled",false);
						}
					}
				});
			}
		}
	}
</script>