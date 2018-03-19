<%@ include file="/common/taglibs.jsp"%>
<div>
	<s:if test="%{tempBoolean}">
		<div align="right">
			<a
				href='${pageContext.request.contextPath}/common/ajaxDownloadScorecardFromUserFiles.do?tempString=<s:property value="tempString"/>'
				title="Score Card Download" target="Blank" class="btn green">Download
				Score Card</a>
		</div>
	</s:if>
	<s:if
		test="%{viewStudentPersonAccountDetailsList != null && !viewStudentPersonAccountDetailsList.isEmpty()}">
		<s:if test="%{viewStudentPersonAccountDetailsList.size > 1 }">
			<div class="row">
				<div class="form-group form-horizontal">
					<label class="control-label col-md-2">
						<span class="required">*</span>Student Name :
					</label>
					<div class="col-md-3">
						<s:select id="sectionId"
							list="viewStudentPersonAccountDetailsList" listKey="studentId"
							listValue="idAndName" name="tempId" theme="simple"
							cssClass="required form-control input-medium"
							onchange="javascript:getStudentExamResults(this.value);" />

					</div>
				</div>
			</div>
		</s:if>
	</s:if>
	<div class="spaceDiv"></div>
	<s:if
		test="%{(examTypeList != null && !examTypeList.isEmpty()) && (subTypesList != null && !subTypesList.isEmpty()) && (subjectsList != null && !subjectsList.isEmpty())}">
		<div id="viewClassResults" class="studentDetailsDiv">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_2">
				<thead>
					<tr>
						<th>
							Subjects
						</th>
						<th>
							SubType
						</th>
						<s:iterator value="examTypeList">
							<th>
								<div id="eType_<s:property value='id'/>">
									<s:property value="examType" />
								</div>
							</th>
						</s:iterator>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="subjectsList">
						<s:set name="subjectId" value="id" />
						<s:iterator value="subTypesList">
							<s:set name="subTypeId" value="id" />
							<tr  class="subjectClassMonths">
								<td>
									<s:property value="name" />
								</td>
								<td>
									<s:property value="subTypeName" />
								</td>
								<s:iterator value="examTypeList">
									<td id="subjMonth<s:property value='#subjectId'/>_<s:property value='#subTypeId'/>_<s:property value='id'/>">
										<div class="eType_<s:property value='id'/> hidingDivs"
											id="<s:property value='#subjectId'/>SM<s:property value='#subTypeId'/>SM<s:property value='id'/>" >
										</div>
									</td>
								</s:iterator>
							</tr>
						</s:iterator>
					</s:iterator>
					<%-- <tr>
						<th>
							<b>Total</b>
						</th>
						<td></td>
						<s:iterator value="examTypeList">
							<td>
								<div align="center"
									class="col-md-2 eType_<s:property value='id'/> hidingDivs"
									id="term<s:property value='id'/>Total">
									0
								</div>
							</td>
						</s:iterator>
					</tr> --%>
				</tbody>
			</table>
			<div class="spaceDiv"></div>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			currently there are no marks uploaded for this class.
		</div>
	</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
  		var feeURL = jQuery.url.getChatURL("/exam/ajaxGetStudentMarksList.do?tempId1="+<s:property value='viewStudentPersonAccountDetails.studentId'/>);
			$.ajax({
			url : feeURL,
			cache : false,
			dataType : 'json',
			success : function(response) {				
				var data=response.studentMarksSettingsData;				
				var examTypeId='';
				var subjectId='';
				var subTypeId='';
				var total=0;
				if(data != null){
						
							/*examTypeId = marksList[i].examTypeId;
								subjectId = marksList[i].subjectId;
								subTypeId = marksList[i].subTypeId;
								alert(marksList[i].present)
								 if(marksList[i].present){
									$('#'+subjectId+"_"+subTypeId+"_"+examTypeId).val(marksList[i].obtainedMarks+marksList[i].moderationMarks);
									 	$('#term'+examTypeId+'Total').text(Number($('#term'+examTypeId+'Total').text())+Number(marksList[i].obtainedMarks));
								}
								else
									$('#'+subjectId+"_"+subTypeId+"_"+examTypeId).val("<font color='red'>AB</font>"); */
									for ( var i = 0; i < data.length; i++) {
										$( 'tr.subjectClassMonths:visible') .each( function() {
															$(this) .find( "td#subjMonth" +  data[i].SUBJECTID  + "_" + data[i].EXAM_SUB_TYPE_ID  + "_" + data[i].EXAM_TYPE_ID) .each( function() {
																//$(this) .find( 'input#studentMarks') .val(data[i].INPUTVALUE);
																//alert(data[i].INPUTVALUE);
																$(this) .find('div[id^=' + data[i].SUBJECTID + 'SM' +  data[i].EXAM_SUB_TYPE_ID +'SM' +  data[i].EXAM_TYPE_ID +']').append(data[i].INPUTVALUE);
															});
														});
									}
						
						 
					}
				}
			});
});


function getStudentExamResults(studentId){
		 if(isNonEmpty(studentId)){
			$('#examsContentDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
					url : jQuery.url.getChatURL("/student/ajaxStudentMarksDetails.do?tempId="+studentId),
					cache : true,
					success : function(response) {
					 	if(isNonEmpty(response)){
					 		$('#examsContentDiv').html(response);
						}
					}
				});
		}
	}
</script>