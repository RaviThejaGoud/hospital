<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
	<div style="color: red;" class="alert alert-info col-md-12">
		You have been used all your allotted
		<s:property value="smsAlloted" />
		SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
	</div>
</s:if>
<s:form id="selectStudentForm" action="#" theme="simple" cssClass="form-horizontal">
	<div class="row">
		<div class="col-md-12">
			<div class="form-group">
				<label class="control-label col-md-2"> <span
					class="required"> * </span>Select Academic Year :
				</label>
				<div class="col-md-6">
					<s:select list="academicYearList" listKey="id" listValue="academicYear" id="academicYearId" name="tempId2"
						theme="simple" headerKey="null" cssClass="required form-control input-medium"
						onchange="javascript:academicAdmissionDetails(this.value,'Y');" />
				</div>
			</div>
		</div>
	</div>
</s:form>
<div class="row form-horizontal">
		<div class="col-md-12">
	<div class="form-group">
		<label class="col-md-2 control-label"> Search Student By : </label>
		<div class="col-md-9 radio-list">
			<label class="col-md-2 radio-inline"> 
				<input type="radio" name="SelectType" id="SelectType" value="Search Admission Wise"
					checked="checked" onclick="handleClick(this.value);" /> All Applications
			</label> 
			<label class="col-md-2 radio-inline"> 
				<input type="radio" name="SelectType" value="Search Student"
					onclick="handleClick(this.value);" /> Student Name
			</label> 
			<label class="col-md-3 radio-inline"> 
				<input type="radio" name="SelectType" value="Search Admission Number"
				onclick="handleClick(this.value);" /> Admission Number
			</label> 
			<label class="col-md-2 radio-inline"> 
				<input type="radio" name="SelectType" id="lastClassWiseType" value="Search Class Wise"
				onclick="handleClick(this.value);" /> Class Wise
			</label>
		</div>
	</div>
  </div>
</div>
<div class="searchDiv" id="searchAdmitStudent" style="display: none;">
	<s:form id="searchStudentByNumber" cssClass="form-horizontal" action="ajaxAdmittedStudents" theme="simple" namespace="/admin">
		<input type="hidden" class="academicYearId" name="academicYearId">
		<div class="form-group">
			<div class="col-md-2">&nbsp;</div>
			<div class="col-md-5">
				<div class="input-group">
					<sj:textfield name="anyTitle" id="rollNumber" placeholder="Student First or Last Name"
						cssClass="form-control medium"></sj:textfield>

					<span class="input-group-btn"> 
						<sj:submit targets="academicSettingsContent" value="Find Student"
							cssClass="btn blue long" indicator="indicator" cssStyle="float:none;vertical-align:middle;"
							onBeforeTopics="searchAdmitStudentForm" formIds="searchStudentByNumber" validate="true" />
					</span>
				</div>
				<span class="help-block">(Key at least 3 chars and hit 'Find Student' to get closer match.) </span>
			</div>
		</div>
	</s:form>
</div>
<div class="searchDiv" id="searchAdmitStudentAdmissionNumber" style="display: none;">
	<s:form id="searchStudentByAdmissionNumber" cssClass="form-horizontal" action="ajaxAdmittedStudents" theme="simple" namespace="/admin">
		<input type="hidden" class="academicYearId" name="academicYearId">
		<div class="form-group">
			<div class="col-md-2">&nbsp;</div>
			<div class="col-md-5">
				<div class="input-group">
					<sj:textfield name="selectedId" id="admissionNumber" cssClass="form-control medium"
						placeholder="Student Admission Number"></sj:textfield>

					<span class="input-group-btn"> 
						<sj:submit targets="academicSettingsContent" value="Find Student"
							cssClass="btn blue long" indicator="indicator" cssStyle="float:none;vertical-align:middle;"
							onBeforeTopics="searchAdmitStudentAdmissionForm" formIds="searchStudentByAdmissionNumber" validate="true" />
					</span>
				</div>
				<span class="help-block">(Type admission number and hit 'Find Student'.)</span>
			</div>
		</div>
	</s:form>
</div>
<%@ include file="/common/messages.jsp"%>
<div id="academicSettingsContent">
	<%-- <jsp:include page="/WEB-INF/pages/admin/admission/ajaxViewAdmittedStudents.jsp"></jsp:include> --%>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/jquery.blockUI.js">
</script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("input:checkbox, input:radio:not('.toggle')").uniform();
				$.destroyTopic('searchAdmitStudentForm');
				$.destroyTopic('searchAdmitStudentAdmissionForm');
				var content = $('span.navSelectionSpan').attr('id');
				if (isNonEmpty(content) && "viewAdmittedStudents" == content) {
					$('li#admittedStudsNav').addClass('active');
					$('li#applicationsNav').removeClass('active');
					$('li#approvedStudsNav').removeClass('active');
				} else if (isNonEmpty(content)
						&& "admissionPaymentCalcel" == content) {
					$('li#approvedStudsNav').addClass('active');
					$('li#applicationsNav').removeClass('active');
					$('li#approvedStudsNav').removeClass('active');
				} else {
					$('li#applicationsNav').removeClass('active');
					$('li#admittedStudsNav').addClass('active');
					$('li#approvedStudsNav').removeClass('active');
				}

				$("input:checkbox, input:radio").uniform();
				var selected = $('input[name=SelectType]:radio:checked').val();
				handleClick(selected);
				$.subscribe('searchAdmitStudentForm', function(event, data) {
					var rollNumber = $('#rollNumber').val();
					if (rollNumber == null || rollNumber == ''
							|| rollNumber == 'Student Admission Number'
							|| rollNumber == 'Student First or Last Name') {
						alert("Please enter first name or last name.");
						event.originalEvent.options.submit = false;

					} else if (rollNumber.length < 3) {
						alert("Please enter minimum 3 characters.");
						//$('#rollNumber').val('Student First or Last Name');
						event.originalEvent.options.submit = false;
					} else {
						$('#academicSettingsContent').show();
						return true;
					}
				});
				$.subscribe('searchAdmitStudentAdmissionForm', function(event,
						data) {
					$('#makePayment').hide();
					var admissionNumber = $('#admissionNumber').val();
					if (admissionNumber == null || admissionNumber == ''
							|| admissionNumber == 'Student Admission Number') {
						alert("Please enter student admission number.");
						event.originalEvent.options.submit = false;

					} else if (admissionNumber.length < 3) {
						alert("Please enter minimum 3 characters.");
						//$('#admissionNumber').val('Student Admission Number');
						event.originalEvent.options.submit = false;
					} else {
						$('#academicSettingsContent').show();
						return true;
					}
				});
			});
	function academicAdmissionDetails(academicYearId, status) {
		if (status == "Y") {
			$('input[name=SelectType]').each(function() {
				$(this).attr('checked', false);
				$(this).parent('span').removeClass('checked');
			});
			$('input#SelectType').attr("checked", true);
			$('input#SelectType').parent('span').addClass("checked");
			$('div.alert-success').hide();
		}
		$('input.academicYearId').val(academicYearId);
		var pars = "academicYearId=" + academicYearId;
		var url = jQuery.url.getChatURL("/admin/ajaxAdmittedStudents.do");
		$("#academicSettingsContent").show();
		$.ajax({
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#academicSettingsContent").html(html);
				$("#academicSettingsContent").show();
				$('#selectAdmitStudentClass').hide();

			}
		});
	}
	function handleClick(value) {
		$('input.academicYearId').val(
				$('select#academicYearId option:selected').val());
		if (isNonEmpty(value)) {
			$
					.blockUI({
						message : '<img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;'
					});
			setTimeout($.unblockUI, 100);
			if ("Search Student" == value) {
				$('#searchAdmitStudentAdmissionNumber').hide();
				$('#selectAdmitStudentClass').hide();
				$('#academicSettingsContent').hide();
				$('#searchAdmitStudent').show();
			} else if ("Search Admission Number" == value) {
				$('#searchAdmitStudentAdmissionNumber').show();
				$('#selectAdmitStudentClass').hide();
				$('#academicSettingsContent').hide();
				$('#searchAdmitStudent').hide();
			} else if ("Search Class Wise" == value) {
				$('#searchAdmitStudentAdmissionNumber').hide();
				$('#selectAdmitStudentClass').show;
				$('#academicSettingsContent').hide();
				$('#searchAdmitStudent').hide();
				var classId = $('select#classId option:selected').val();
				academicClassWiseAdmissionDetails(classId)
			} else {
				var academicYearId = $('.academicYearId').val();
				academicAdmissionDetails(academicYearId, "N");
				$('#searchAdmitStudentAdmissionNumber').hide();
				$('#searchAdmitStudent').hide();
				$('#selectAdmitStudentClass').hide();
				//$('#academicSettingsContent').hide();
			}
		}
	}
	function academicClassWiseAdmissionDetails(classId) {
		var academicYearId = $('.academicYearId').val();
		var pars = "academicYearId=" + academicYearId + "&classId=" + classId;
		var url = jQuery.url.getChatURL("/admin/ajaxAdmittedStudents.do");
		$("#academicSettingsContent").show();
		$.ajax({
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#academicSettingsContent").html(html);
				$("#academicSettingsContent").show();
			}
		});
	}
	$('a.admittedApplications').click(function() {
		window.location.hash = "target=ADMS.ajaxify ADST";
		window.location.reload();
	});
</script>
