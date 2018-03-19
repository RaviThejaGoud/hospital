
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%><%@ include file="/common/taglibs.jsp"%>
<%@ include file ="/common/messages.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/common/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>

<div class="alpha omega" id="searchNeighborhood"
	style="font-size: 14px; line-height: 25px; margin-bottom: 10px; font-weight: bold;">
	<div class="grid_3 alpha" >
		<b> Criteria </b>
	</div>
	A - Excellent &nbsp;	B - Very Good	&nbsp; C - Good &nbsp;	D - Needs Improvement
</div>

	<s:form action="ajaxAddFeedbackForm" theme="css_xhtml" id="addNonTeachingStaff">
		
		<input type="hidden" name="studentAccountId" value="<s:property value="viewStudentPersonAccountDetails.accountId"/>" />
		<input type="hidden" name="studyClassId" value="<s:property value="viewStudentPersonAccountDetails.classId"/>" />
		
		<s:if test="%{feedbackQuestionsList != null && !feedbackQuestionsList.isEmpty()}">
	<div style="padding-top: 1px">
		<div id="resultsPage">
			<s:iterator value="feedbackQuestionsList">
			<s:set name="questionId" value="%{strId}" />
				<table class="striped" width="100%" style="margin-bottom: 0;"
					cellpadding="1" cellspacing="1">
					<tr class='loaded'>
						<td width="22%" class="head">
							<B><s:property value="description" />:-</B>
						</td>
					</tr>
				</table>
				<s:if test="%{feedbackOptions != null && !feedbackOptions.isEmpty()}">
					<s:iterator value="feedbackOptions">
					<table class="striped" width="100%" style="margin-bottom: 0;"
						cellpadding="1" cellspacing="1">
						<tr class='loaded'>
							<td width="22%" class="head">
								<s:property value="description" />
							</td>
							<td>
							<s:set name="optionsId" value="%{strId}" />
							<s:if test="%{feeTypeList != null && !feeTypeList.isEmpty()}">
								<s:iterator value="feeTypeList">
									<td width="22%" class="head">
										<input type="radio" name="feedbackGradeIds" value='<s:property value="title" />' /> <s:property value="title" />
									</td>
								</s:iterator>
								</s:if>
							</td>
						</tr>
					</table>
				</s:iterator>
			</s:if>
			<s:if test='%{"ACD" == activityType }'>
				<s:iterator value="studySubjectSet" status="status">
					<table class="striped" width="100%" style="margin-bottom: 0;"
						cellpadding="1" cellspacing="1">
						<tr class='loaded'>
							<td width="22%" class="head">
								<s:property value="description" />:
							</td>
							<td width="22%" class="head">
								<input type="radio" name='studySubjectIds[<s:property value="questionId"/>][<s:property value="id"/>]' value="A"> A
							</td>
							<td width="22%" class="head">
								<input type="radio" name='studySubjectIds[<s:property value="questionId"/>][<s:property value="id"/>]' value="B"> B
							</td>
							<td width="22%" class="head">
								<input type="radio" name='studySubjectIds[<s:property value="questionId"/>][<s:property value="id"/>]' value="C"> C
							</td>
							<td width="22%" class="head">
								<input type="radio" name='studySubjectIds[<s:property value="questionId"/>][<s:property value="id"/>]' value="D"> D
							</td>
						</tr>
					</table>
				</s:iterator>
				</s:if>
				<s:elseif test="%{feedbackOptions == null || feedbackOptions.isEmpty()}">
					<table class="striped" width="100%" style="margin-bottom: 0;"
						cellpadding="1" cellspacing="1">
						<tr class='loaded'>
							<td width="22%" class="head">
								<s:property value="description" />:
							</td>
							<td width="22%" class="head">
								<input type="radio" name='feedbackQuestionsIds[<s:property value="questionId"/>]' value="A"> A&nbsp;
							</td>
							<td width="22%" class="head">
								<input type="radio" name='feedbackQuestionsIds[<s:property value="questionId"/>]' value="B"> B&nbsp;
							</td>
							<td width="22%" class="head">
								<input type="radio" name='feedbackQuestionsIds[<s:property value="questionId"/>]' value="C"> C&nbsp;
							</td>
							<td width="22%" class="head">
								<input type="radio" name='feedbackQuestionsIds[<s:property value="questionId"/>]' value="D"> D&nbsp;
							</td>
						</tr>
					</table>
			</s:elseif>
		</s:iterator>
		</div>

	</div>
</s:if>
<s:else>
	<div style="padding: 10px">
		currently there are no child absent messages.
	</div>
</s:else>
		
			<div class="grid_12">
				&nbsp;
			</div>
			<div style="float: left;" class="grid_2">
				<sj:submit   cssClass="submit small" value="Submit" targets="staffList"
					onClickTopics="nonTeachingStaffFormValidation"/>
			</div>
	</s:form>
<script language="JavaScript" type="text/javascript">
changePageTitle("Add Staff Details");
$(document)
		.ready(
				function() {
					$.subscribe('teachingStaffFormValidation', function(event,
							data) {
						if ($('#addTeachingStaff').valid())
							return true;
						else
							return false;
					});
					$.subscribe('nonTeachingStaffFormValidation', function(
							event, data) {
						if ($('#addNonTeachingStaff').valid())
							return true;
						else
							return false;
					});
					$("input[name=empId]")
							.autoCheck(
									"${pageContext.request.contextPath}/common/ajaxCheckEmailId.do",
									{
										minChars : 3,
										min : "no"
									});
					$('.numeric').numeric();
					$('.alphabet').alpha( {
						allow : "a-z,A-Z.?/~!@#)() "
					});
					$('.numericDot').numeric( {
						allow : "."
					});

				});

function changeQualification(staffType) {
	if (staffType == 'teaching') {
		$('#teachingDiv').show();
		$('#nonTeachingDiv').hide();
	} else {
		$('#teachingDiv').hide();
		$('#nonTeachingDiv').show();
	}
}

function ajaxShowInchargeList(staffType) {
	var pars = "staffType=" + staffType;
	//alert(staffType);
	var url = jQuery.url.getChatURL("/admin/ajaxDoGetSupervisorList.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			if ("ROLE_PRINCIPAL" == staffType) {
				$("#staffTypeDiv").html("");
			} else {
				$("#staffTypeDiv").html(html);
			}
		}
	});
}

function showInchargeList(staffType) {
	if (staffType == 'ROLE_TEACHER') {
		$('#staffTypeDiv').show();
		//$('#nonTeachingDiv').hide();
	} else {
		$('#staffTypeDiv').hide();
		//$('#nonTeachingDiv').show();
	}
}
function formatPhoneNumber(object, e) {
	var keynum;
	var keychar;
	var numcheck;
	if (window.event) // IE
	{
		keynum = e.keyCode;
	} else if (e.which) // Netscape/Firefox/Opera
	{
		keynum = e.which;
	}
	if (keynum == 8) {
		return true;
	} else if (keynum < 47 || keynum > 57) {
		return false;
	}
	var phoneString = object.value;
	if (phoneString.length == 1) {
		phoneString = "+91-" + phoneString;
	}
	object.value = phoneString;
}
</script>