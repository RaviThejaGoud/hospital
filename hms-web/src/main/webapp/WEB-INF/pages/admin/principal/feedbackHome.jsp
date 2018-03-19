<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/starrate/js/jquery-ui.custom.min.js">
</script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/scripts/starrate/js/jquery.uni-form.js'>
</script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/scripts/starrate/js/jquery.ui.stars.js'>
</script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/scripts/starrate/css/ui.stars.css" />
<div class="block grid_17">

	<div class="block_head">
		<h2>
			School & Staff Feedback Performance
		</h2>
	</div>
	<div class="block_content" id="staffLeaveApproval">
			<div class="grid_17">
				<s:if test="%{feeTypeList != null && !feeTypeList.isEmpty()}">
					<s:iterator value="feeTypeList">
							<b><s:property value="title" />:</b><s:property value="description"/>,&nbsp;
					</s:iterator>
				</s:if>
				<div class="grid_10">
					<div class="grid_3">
						<label style="text-align: left;">
							<b>School Feedback:-</b>
						</label>
					</div>

				</div>
				<s:if test="%{feedbackQuestionsList != null && !feedbackQuestionsList.isEmpty()}">
					<s:iterator value="feedbackQuestionsList">
						<table class="striped" width="100%"
							style="margin-bottom: 0; border-width: 1px 1px 1px;"
							cellpadding="1" cellspacing="1">
							<tr class='loaded'>
								<td width="80%" class="head">
									<s:url id="removeFeedback" action="ajaxDeleteFeedback" escapeAmp="false" namespace="/admin">
										<s:param name="id" value="id"></s:param>
									</s:url>
									<s:div cssStyle="margin-top:-1px;" cssClass="close emsRemove"
										id='%{removeFeedback}' theme="simple" title="Delete this Feedback"></s:div>
									<s:property value="feedbackGradeId" />
								</td>
								<td>
									<form id="rat<s:property value="id" />" action="" method="post">
										<c:forEach var="i" begin="1" end="5">
											<c:choose>
												<c:when test="${i <= feedbackQuestionId}">
													<input type="radio" name="rate"
														value='<c:out value="${i}" />' title="" checked='checked' />
												</c:when>
												<c:otherwise>
													<input type="radio" name="rate" value='<c:out value="${i}" />' title="" />
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</form>

									<script type="text/javascript">
										if($("#rat"+'<s:property value='id' />'))
										{
											$("#rat"+'<s:property value='id' />').stars({
												starWidth: 18, // only needed in "split" mode
												//split: 2,
												oneVoteOnly: true,
												callback: function(ui, type, value)
												{
													// Hide Stars while AJAX connection is active
													//var kBankId=$("#rat .knowledgeBankId"+'<s:property value='id' />').html();
													//var response = receiveRequest.evalJSON(true);
													ui.select(feedbackQuestionId);
													$("#loader"+'<s:property value='id' />').hide();
													$("#rat"+'<s:property value='id' />').show();
												}
											});
										}
									</script>
								</td>
							</tr>
						</table>
					</s:iterator>
				</s:if>
				<div class="grid_10">
					<div class="grid_3">
						<label style="text-align: left;">
							<b>Staff Performance:-</b>
						</label>
					</div>

				</div>
				<s:if test="%{staffsList != null && !staffsList.isEmpty()}">
				
					<s:iterator value="staffsList">
						<table class="striped" width="100%" style="margin-bottom: 0; border-width: 1px 1px 1px;" cellpadding="1" cellspacing="1">
							<tr class='loaded'>
								<td width="80%" class="head">
									<s:url id="doViewTeacherCLassRating" action="ajaxTeacherClassRating" includeParams="all" escapeAmp="false" namespace="/admin">
										<s:param name="staffId" value="%{id}" />									
									</s:url>
									<sj:a href="%{doViewTeacherCLassRating}"
										onBeforeTopics="cleanOpenRows" onCompleteTopics="doInitTeacherClassRating" indicator="indicator"
										targets="displayTeacherClassRating%{id}">
										<s:property value="firstName" /> <s:property value="lastName" /> 
									</sj:a>
									
								</td>
								<td>
									<form id="rat<s:property value="username" />" action="" method="post">
										<c:forEach var="i" begin="1" end="5">
											<c:choose>
												<c:when test="${i <= version}">
													<input type="radio" name="rate"
														value='<c:out value="${i}" />' title="" checked='checked' />
												</c:when>
												<c:otherwise>
													<input type="radio" name="rate" value='<c:out value="${i}" />' title="" />
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</form>

									<script type="text/javascript">
										if($("#rat"+'<s:property value='username' />'))
										{
											$("#rat"+'<s:property value='username' />').stars({
												starWidth: 18, // only needed in "split" mode
												//split: 2,
												oneVoteOnly: true,
												callback: function(ui, type, value)
												{
													// Hide Stars while AJAX connection is active
													//var kBankId=$("#rat .knowledgeBankId"+'<s:property value='id' />').html();
													//var response = receiveRequest.evalJSON(true);
													ui.select(version);
													$("#loader"+'<s:property value='username' />').hide();
													$("#rat"+'<s:property value='username' />').show();
												}
											});
										}
									</script>
								</td>
							</tr>
							<tr id="displayTeacherClassRating<s:property value='id' />" style="display: none;" class="load"></tr>
						</table>
					</s:iterator>
				</s:if>
			</div>
		</div>
</div>
<script type="text/javascript">
  $(document).ready(function() {
  	$('div#viewClassresults').click(function() {
			var data=$(this).attr('class');
			if ($('#' +data).is(":hidden")) {
				  $('#' +data).show();	
			}else{
				  $('#' +data).hide();
			}
		});
  
   	$('div#viewClassSchedules').click(function() {
			var data=$(this).attr('class');
			if ($('#' +data).is(":hidden")) {
				  $('#' +data).show();	
			}else{
				  $('#' +data).hide();
			}
		});
  $.subscribe('doInitTeacherClassRating', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		 }
	});
});
 </script>
		
	