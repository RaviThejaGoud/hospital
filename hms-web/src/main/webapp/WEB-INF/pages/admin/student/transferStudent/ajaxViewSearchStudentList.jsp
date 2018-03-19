<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div id="commonStep">
	<fieldset>
		<%@ include file="/common/messages.jsp"%>
		<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>
							Roll Number
						</th>
						<th>
							Admission Number
						</th>
						<th>
							Student Name
						</th>
						<th>
							Class Name & Section
						</th>
						<th>
							Action
						</th>
					</tr>
				</thead>
				<tbody>
					<s:set name="studyClassId" value=""></s:set>
					<s:iterator value="studentsList">
						<%-- <tr>
							<s:if test="%{classSectionId != #studyClassId}">
								<td colspan="7">
									<center>
										<strong>Class Name & Section : <s:property
												value="className" />-<s:property value="section" /> </strong>
									</center>
								</td>
							</s:if>
						</tr> --%>
						<tr>
							<td>
								<s:property value="rollNumber" />
							</td>
							<td>
								<s:property value="admissionNumber" />
							</td>
							<td>
								<s:property value="firstName" />
								&nbsp;
								<s:property value="lastName" />
							</td>
							<td>
								<s:property value="className" />-<s:property value="section" />
							</td>
							<td>
								<a data-toggle="modal" href="#studentTransforDetailsDiv"
									class="btn btn-xs purple"
									onclick="javascript:PopupStudentTransforDetails(<s:property value="%{studId}" />);"><i
									class="fa fa-edit"></i>Transfer Student </a>
								<!--<s:url id="studentAcademics" namespace="/student"
									action="ajaxDoStudentTransfer" includeParams="all"
									escapeAmp="false">
									<s:param name="tempId" value="%{studId}" />
								</s:url>
								<sj:a href="%{studentAcademics}" targets="studentTransfer%{studId}"
									onCompleteTopics="doInitReceiptDetails" onBeforeTopics="cleanOpenDivs" button="false"
									buttonIcon="ui-icon-plus" indicator="indicator"> Transfer Student </sj:a>
					-->
							</td>
							<!--<div id="studentTransfer<s:property value="studId"/>" style="display: none;" class="load"></div>
				-->
						</tr>
						<s:set name="studyClassId" value="classSectionId"></s:set>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Oops! system couldn't find any match with keyword. Try by correcting
				the word
			</div>
		</s:else>
	</fieldset>
</div>
<div id="studentTransforDetailsDiv"></div>
<script type="text/javascript">
function PopupStudentTransforDetails(studId) {
		var url = jQuery.url.getChatURL("/student/ajaxDoStudentTransfer.do");
		$('#studentTransforDetailsDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "tempId=" + studId,
				success : function(html) {
					$("#studentTransforDetailsDiv").html(html);
				}
			});
		} 
</script>

