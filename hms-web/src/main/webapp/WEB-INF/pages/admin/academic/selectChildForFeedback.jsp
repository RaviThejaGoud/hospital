<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/scripts/starrate/js/jquery.ui.stars.js'>
</script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/scripts/starrate/css/ui.stars.css" />
<div id="staffList">
	<s:if test="%{feeTypeList != null && !feeTypeList.isEmpty()}">
		<span class="label label-danger"> NOTE : &nbsp;</span>
		<s:iterator value="feeTypeList">
			<b><s:property value="title" />:</b>
			<s:property value="description" />&nbsp;
		</s:iterator>
	</s:if>
	<div class="spaceDiv"></div>
	<s:if
		test="%{feedbackQuestionsList != null && !feedbackQuestionsList.isEmpty()}">
		<div id="busResultsPage" class='<s:property value="tempString" />'>
			<s:set var="checkRoledes" value=""></s:set>
			<s:if test="%{tempString == 'School Feedback Questions'}">
				<h4 class="pageTitle bold">
					School feedback questions
				</h4>
			</s:if>
			<s:else>
				<h4 class="pageTitle bold">
					Staff feedback questions
				</h4>
			</s:else>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>
							Name
						</th>
						<th>
							Description
						</th>
						<th>
							Rating
						</th>
						<th>
							Edit
						</th>
						<th>
							Delete
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="feedbackQuestionsList">
						<tr>
							<td>
								<s:property value="feedbackGradeId" />
							</td>
							<td>
								<a data-toggle="modal" href="#popupQuestionRatingDiv"
									onclick="javascript:popupQuestionRatingDetails(<s:property value="id" />);">Description
								</a>
							</td>
							<td>
								<span class="stars"><s:property value="totalRatingVal" /><span
									style=""></span> </span>
							</td>
							<td>
								<a data-toggle="modal" href="#popupPostFeedbackDiv"
									class="btn btn-xs purple"
									onclick="javascript:popupPostFeedbackDetails(<s:property value="id" />);"><i
									class="fa fa-edit"></i>Edit </a>
							</td>
							<td>
								<s:url id="removeFeedback" action="ajaxDeleteFeedback"
									escapeAmp="false" namespace="/admin">
									<s:param name="id" value="id"></s:param>
								</s:url>
								<s:div cssClass="btn btn-xs red"
									onclick="javascript:confirmDialogWithTarget(this,'feedbackContent');"
									id='%{removeFeedback}' title="Delete this class">
									<i class="fa fa-times"></i>Delete
												</s:div>
							</td>
						</tr>
						<s:set var="checkRoledes" value="description"></s:set>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no feedback questions.
		</div>
	</s:else>
</div>
<div id="popupPostFeedbackDiv"></div>
<div id="popupQuestionRatingDiv"></div>
<div id="popupTeacherCLassRatingDiv"></div>
<div id="popupTeacherDiv"></div>
<script type="text/javascript">
$(document).ready(
		function() {
		$('li#feedbackQuestion').removeClass('active');
		if($('div#busResultsPage').hasClass('School Feedback Questions')){
		$('li#schoolDiv').addClass('active');
		$('li#teacherDiv').removeClass('active');
		}
		else{
		$('li#teacherDiv').addClass('active');
		$('li#schoolDiv').removeClass('active');
		}
			TableAdvanced.init();
			UIExtendedModals.init();
			return $('span.stars').each(
					function() {
						var text = $(this).text();
						$(this).html(
								$('<span />').width(
										Math.max(0, (Math.min(5,
												parseFloat(text)))) * 16));
					});
		});
function popupPostFeedbackDetails(id) {
	var url = jQuery.url.getChatURL("/admin/ajaxDoPostFeedback.do");
	$('#popupPostFeedbackDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "tempId="+id,
			success : function(html) {
				$("#popupPostFeedbackDiv").html(html);
			}
		});
	}
function popupQuestionRatingDetails(id) {
	var tempString = '<s:property value="tempString"/>'
	var url = jQuery.url.getChatURL("/admin/ajaxTeacherClassRatingDescription.do");
	$('#popupQuestionRatingDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "feedBackQuesId="+id+"&staffId=0"+"&tempString="+tempString,
			success : function(html) {
				$("#popupQuestionRatingDiv").html(html);
			}
		});
	}
/*function popupTeacherClassRatingDetails(id) {
	var url = jQuery.url.getChatURL("/admin/ajaxTeacherClassRatingDescription.do");
	$('#popupTeacherCLassRatingDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "staffId="+id,
			success : function(html) {
				$("#popupTeacherCLassRatingDiv").html(html);
			}
		});
	}
function popupTeacherClassRating(id) {
	var url = jQuery.url.getChatURL("/admin/ajaxTeacherClassRating.do");
	$('#popupTeacherDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
			url : url,
			cache : false,
			data : "staffId="+id,
			success : function(html) {
				$("#popupTeacherDiv").html(html);
			}
		});
	}*/
</script>
