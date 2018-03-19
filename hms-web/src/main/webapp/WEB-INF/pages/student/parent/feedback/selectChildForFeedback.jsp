<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="schoolRateDiv">
	<div class="row">
		<div class="col-md-12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>Feedback Form
					</div>
				</div>
				<div class="portlet-body">
					<div id="tempDiv" style="display: none;">
						<div class="tabbable tabbable-custom tabbable-full-width">
							<ul class="nav nav-tabs">
								<s:if test="%{anyTitle=='School'}">
									<li class="active">
								</s:if>
								<s:else>
									<li>
								</s:else>
								<s:url id="doAddSchoolRating" namespace="/student"
									action="ajaxDoAddSchoolFeedbackRating" includeParams="all"
									escapeAmp="false">
									<s:param name="anyTitle">School</s:param>
								</s:url>
								<sj:a href="%{doAddSchoolRating}" targets="mainContentDiv"
									data-toggle="tab">Add School Rating</sj:a>
								</li>
								<s:if test="%{anyTitle=='Teacher'}">
									<li class="active">
								</s:if>
								<s:else>
									<li>
								</s:else>
								<s:if test='%{user.isSchoolStudent=="Y"}'>
									<s:url id="doAddTeacherRating" namespace="/student"
										action="ajaxDoAddTeacherRating" includeParams="all"
										escapeAmp="false">
										<s:param name="anyTitle">Teacher</s:param>
									</s:url>
									<sj:a href="%{doAddTeacherRating}" targets="mainContentDiv"
										data-toggle="tab">Add Teacher Rating</sj:a>
								</s:if>
								</li>
							</ul>
							<div class="tab-content">
								<table id="sample_2" class="">
									<tbody>
										<tr>
											<td>
												<span class="label label-danger">NOTE!</span>&nbsp;
											</td>
											<td>
												Excellent = 5&nbsp;&nbsp;&nbsp;
											</td>
											<td>
												Very Good = 4&nbsp;&nbsp;&nbsp;
											</td>
											<td>
												Good = 3&nbsp;&nbsp;&nbsp;
											</td>
											<td>
												Fair = 2&nbsp;&nbsp;&nbsp;
											</td>
											<td>
												Average = 1&nbsp;&nbsp;&nbsp;
											</td>

										</tr>
									</tbody>
								</table>
								<div class="spaceDiv"></div>
								<s:if
									test="%{objectList != null && !objectList.isEmpty() || (feedbackQuestionsList != null && !feedbackQuestionsList.isEmpty() && staffsList != null && !staffsList.isEmpty())}">
									<s:form id="addParentFeedBack"
										action="ajaxAddParentFeedbackForm" method="post"
										theme="simple" cssClass="form-horizontal" namespace="/student">
										<div class="form-body">
											<s:if test="%{anyTitle=='School'}">
												<jsp:include page="/common/messages.jsp"></jsp:include>
												<s:if test="%{objectList != null && !objectList.isEmpty()}">
													<%
														int i = 0;
													%>
													<div>
														<s:set var="checkRoledes" value=""></s:set>
														<table
															class="striped table table-striped table-bordered table-hover table-full-width"
															id="sample_2">
															<s:iterator value="objectList">
																<s:set name="scQuestionId" value="%{strId}" />
																<span class="feedBackData"> <s:hidden
																		name="questionAnswerId" value="%{id}"></s:hidden> <s:set
																		name="feedBackQuestionId" value="%{''+id}"></s:set> <s:if
																		test="%{roleDescription != #checkRoledes}">
																		<label>
																			<strong><b> <s:property
																						value="roleDescription" />:</b> </strong>
																		</label>
																	</s:if>
																	<tr class='loaded'>
																		<td width="2%">
																			<%
																				i++;
																			%><%=i%></td>
																		<td width="70%">
																			<s:property value="description" />
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2">
																			<s:if
																				test="%{tempList != null && !tempList.isEmpty()}">
																				<s:set name="parentFeedbackId" value="0" />
																				<s:iterator value="tempList">
																					<s:if test="%{feedbackQuestionId == #scQuestionId}">
																						<s:if
																							test="%{feeTypeList != null && !feeTypeList.isEmpty()}">
																							<div class="form-group">
																								<label class="col-md-3 control-label"></label>
																								<div class="col-md-9" style="margin-left: 20px;">
																									<div class="radio-list">
																										<s:iterator value="feeTypeList">
																											<label class="radio-inline">
																												<s:set name="description"
																													value="%{description}" />
																												<s:set name="scGradeId" value="%{id}" />
																												<s:if
																													test="%{feedbackGradeId == #scGradeId}">
																													<input type="radio"
																														class="feedBackAnswerId required"
																														checked='checked'
																														name="feedbackGradeAns<s:property value="#scQuestionId"/>"
																														value='<s:property value="id" />' />
																													<s:property value="#description" />
																												</s:if>
																												<s:else>
																													<input type="radio"
																														class="feedBackAnswerId required"
																														name="feedbackGradeAns<s:property value="#scQuestionId"/>"
																														value='<s:property value="id" />' />
																													<s:property value="#description" />
																												</s:else>
																											</label>
																										</s:iterator>
																									</div>
																								</div>
																							</div>
																						</s:if>
																						<s:set name="parentFeedbackId"
																							value="%{#parentFeedbackId +',' + parentFeedbackId}" />
																					</s:if>
																				</s:iterator>
																			</s:if>
																			<s:else>
																				<s:if
																					test="%{feeTypeList != null && !feeTypeList.isEmpty()}">
																					<div class="form-group">
																						<label class="col-md-3 control-label"></label>
																						<div class="col-md-9" style="margin-left: 20px;">
																							<div class="radio-list">
																								<s:iterator value="feeTypeList">
																									<label class="radio-inline">
																										<s:set name="description"
																											value="%{description}" />
																										<s:set name="scGradeId" value="%{id}" />
																										<input type="radio"
																											class="feedBackAnswerId required"
																											name="feedbackGradeAns<s:property value="#scQuestionId"/>"
																											value='<s:property value="id" />' />
																										<s:property value="#description" />
																									</label>
																								</s:iterator>
																							</div>
																						</div>
																					</div>
																				</s:if>
																			</s:else>
																			<s:if test="%{(#parentFeedbackId) == 0}">
																				<s:if
																					test="%{feeTypeList != null && !feeTypeList.isEmpty()}">
																					<div class="form-group">
																						<label class="col-md-3 control-label"></label>
																						<div class="col-md-9" style="margin-left: 20px;">
																							<div class="radio-list">
																								<s:iterator value="feeTypeList">
																									<label class="radio-inline">
																										<s:set name="description"
																											value="%{description}" />
																										<s:set name="scGradeId" value="%{id}" />
																										<input type="radio"
																											class="feedBackAnswerId required"
																											name="feedbackGradeAns<s:property value="#scQuestionId"/>"
																											value='<s:property value="id" />' />
																										<s:property value="#description" />
																									</label>
																								</s:iterator>
																							</div>
																						</div>
																					</div>
																				</s:if>
																			</s:if>
																		</td>
																	</tr> </span>
																<s:set var="checkRoledes" value="roleDescription"></s:set>
															</s:iterator>
														</table>
													</div>
												</s:if>
											</s:if>
											<s:else>
												<s:if test="%{staffsList != null && !staffsList.isEmpty()}">
													<s:if test='%{user.isSchoolStudent=="Y"}'>
														<jsp:include page="/common/messages.jsp"></jsp:include>
														<div>
															<label>
																<strong><b> Teacher Feedback Questions:</b> </strong>
															</label>
														</div>
														<div>
															<%
																int i = 0;
															%>
															<table
																class="striped table table-striped table-bordered table-hover table-full-width"
																id="sample_4">
																<s:iterator value="staffsList">
																	<span class="feedBackData"> <s:hidden
																			name="staffId" value="%{staffId}">
																		</s:hidden> <s:set name="teacherId" value="%{''+staffId}"></s:set>
																		<tr class='loaded'>
																			<td width="2%">
																				<%
																					i++;
																				%><%=i%></td>
																			<td width="70%">
																				<b><s:property value="lastName" /> <s:property
																						value="firstName" /> </b>
																			</td>
																		</tr>
																		<tr>
																			<td colspan="2">
																				<s:if
																					test="%{feedbackQuestionsList != null && !feedbackQuestionsList.isEmpty()}">
																					<%
																						int j = 0;
																					%>
																					<table
																						class="striped table table-striped table-bordered table-hover table-full-width"
																						id="sample_2">
																						<s:iterator value="feedbackQuestionsList">
																							<s:set name="questionOpId" value="%{id}" />
																							<tr>
																								<td width="2%">
																									<%
																										j++;
																									%><%=j%></td>
																								<td width="70%">
																									<s:property value="description" />
																								</td>
																							</tr>
																							<tr>
																								<td colspan="2">
																									<s:if
																										test="%{parentFeedbackList != null && !parentFeedbackList.isEmpty()}">
																										<s:set name="parentFeedbackId" value="0" />
																										<s:iterator value="parentFeedbackList">
																											<s:if
																												test="%{feedbackQuestionId == #questionOpId && staffId == #teacherId}">
																												<s:if
																													test="%{feeTypeList != null && !feeTypeList.isEmpty()}">
																													<div class="form-group">
																														<label class="col-md-3 control-label"></label>
																														<div class="col-md-9"
																															style="margin-left: 20px;">
																															<div class="radio-list">
																																<s:iterator value="feeTypeList">
																																	<label class="radio-inline">
																																		<s:set name="description"
																																			value="%{description}" />
																																		<s:set name="optionsId" value="%{id}" />
																																		<s:if
																																			test="%{feedbackGradeId == #optionsId && staffId == #teacherId}">
																																			<input type="radio"
																																				class="feedBackAnswerId required"
																																				checked='checked'
																																				name="studentStaffGradeAns<s:property value="#questionOpId"/><s:property value="teacherId"/>"
																																				value='<s:property value="id" />' />
																																			<s:property value="#description" />
																																		</s:if>
																																		<s:else>
																																			<input type="radio"
																																				class="feedBackAnswerId required"
																																				name="studentStaffGradeAns<s:property value="#questionOpId"/><s:property value="teacherId"/>"
																																				value='<s:property value="id" />' />
																																			<s:property value="#description" />
																																		</s:else>
																																	</label>
																																</s:iterator>
																															</div>
																														</div>
																													</div>
																												</s:if>
																												<s:set name="parentFeedbackId"
																													value="%{#parentFeedbackId +',' + parentFeedbackId}" />
																											</s:if>
																										</s:iterator>
																									</s:if>
																									<s:else>
																										<s:if
																											test="%{feeTypeList != null && !feeTypeList.isEmpty()}">
																											<div class="form-group">
																												<label class="col-md-3 control-label"></label>
																												<div class="col-md-9"
																													style="margin-left: 20px;">
																													<div class="radio-list">
																														<s:iterator value="feeTypeList">
																															<label class="radio-inline">
																																<s:set name="description"
																																	value="%{description}" />
																																<s:set name="optionsId" value="%{id}" />
																																<input type="radio"
																																	class="feedBackAnswerId required"
																																	name="studentStaffGradeAns<s:property value="#questionOpId"/><s:property value="teacherId"/>"
																																	value='<s:property value="id" />' />
																																<s:property value="#description" />
																															</label>
																														</s:iterator>
																													</div>
																												</div>
																											</div>
																										</s:if>
																									</s:else>
																									<s:if test="%{(#parentFeedbackId) == 0}">
																										<s:if
																											test="%{feeTypeList != null && !feeTypeList.isEmpty()}">
																											<div class="form-group">
																												<label class="col-md-3 control-label"></label>
																												<div class="col-md-9"
																													style="margin-left: 20px;">
																													<div class="radio-list">
																														<s:iterator value="feeTypeList">
																															<label class="radio-inline">
																																<s:set name="description"
																																	value="%{description}" />
																																<s:set name="optionsId" value="%{id}" />
																																<input type="radio"
																																	class="feedBackAnswerId required"
																																	name="studentStaffGradeAns<s:property value="#questionOpId"/><s:property value="teacherId"/>"
																																	value='<s:property value="id" />' />
																																<s:property value="#description" />
																															</label>
																														</s:iterator>
																													</div>
																												</div>
																											</div>
																										</s:if>
																									</s:if>

																								</td>
																							</tr>
																						</s:iterator>
																					</table>
																				</s:if>
																			</td>
																		</tr> </span>
																</s:iterator>
															</table>
														</div>
													</s:if>
												</s:if>
											</s:else>
											<div class="form-actions fluid">
												<div class="col-md-offset-2 col-md-9">
													<img src="../img/bg/bigWaiting.gif" alt="Loading..."
														title="Loading..." id="indicator"
														style="display: none; background-repeat: no-repeat; position: relative; z-index: 1000; bottom: 200px; margin-left: 240px;" />
													<sj:submit targets="schoolRateDiv" value="Submit"
														validate="true" cssClass="submitBt btn blue"
														formIds="addParentFeedBack"
														onBeforeTopics="feedbackQuestionFormValidation" />
												</div>
											</div>
										</div>
									</s:form>
								</s:if>
								<s:else>
									<s:if test="%{anyTitle=='School'}">
										<div class="alert alert-info">
											Currently there are no school feedback question.
										</div>
									</s:if>
									<s:else>
										<div class="alert alert-info">
											Currently there are no teacher feedback question.
										</div>
									</s:else>
								</s:else>
							</div>
							<div id="feedbackForm"></div>
						</div>
					</div>
					<div id="errorDiv" style="display: none;">
						<div class="alert alert-info">
							You dont have a sufficient attendance to give feed back.
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<style type="text/css">
form label.error {
	width: 115px;;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	var status = '<s:property value="user.isSchoolStudent"/>';
	if(status == 'Y'){
		 var tempString = '<s:property value="tempString"/>';
		 if(isNonEmpty(tempString)){
	 		$('#tempDiv').show();
		 }
		 else{
			 $('#errorDiv').show();
	 	 }
	}else{
		$('#tempDiv').show();
	}
$("input:checkbox, input:radio:not('.toggle')").uniform();  
	changePageTitle("Student Feedback");
	/*$.subscribe('feedbackQuestionFormValidation', function(event, data) {
		var inId = '';
		var isChecked = false;
		$('span.feedBackData').each(function() {
			inId = $(this).find('input.feedBackAnswerId').is(':checked');
			if (inId == true) {

				isChecked = true;
			}
		});
		if (isChecked == false) {
			alert("Please select at least one grade.");
			return false;
		} else
			return true;
	});*/
	$('.numeric').numeric();
	$('.alphabet').alpha();
});
</script>
