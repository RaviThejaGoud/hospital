<%@ include file="/common/taglibs.jsp"%>
 <s:if test="%{admissionInquiryList!= null && !admissionInquiryList.isEmpty()}">
	
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_5">
		<thead>
			<tr>
				<th>Enquiry Date</th>
				<th>
					Academic Year
				</th>
				
				<th>Class</th>
				
				<th>Student Name</th>
				<th>Parent Name</th>
				<th>Parent Mobile No</th>
				<th>Previous School Name</th>
				<th>Student Type</th>
				<th>Download</th>
				<th>
					Add Application
				</th>
				<%-- <s:if test='%{user.IsSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y"}'>
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</s:if> --%>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="admissionInquiryList">
				<tr>
					<td><s:property value="createdDateStr" /></td>
					<td>
						<s:property value="academicYear.academicYear" />
					</td>
					<td>
						<s:property value="classId.className" />
					</td>
					<td><s:property value="studentName" /></td>
					<td><s:property value="parentName" /></td>
					<td><s:property value="parentMobileNumber" /></td>
					<td><s:property value="previousSchoolName" /></td>
					<td><s:if test='%{studentType =="D"}'>Day Scholar</s:if>
					<s:else>Hosteler</s:else></td>
					<td>
					<s:if test="%{admissionSettings.admissionFormEmptyTemplatepath!= null}">
					 	<a target="_blank;"
								href="${pageContext.request.contextPath}<s:property value="admissionSettings.admissionFormEmptyTemplatepath" />/<s:property value="admissionSettings.admissionFormEmptyTemplateFileName" />"
								title="Download">Download</a>
					 </s:if>
					<s:else>
						--
					</s:else>	
					</td>
					
					<td><s:url id="doEditClassSubjects"
							action="ajaxApplyOfflineAdmission" includeParams="all"
							escapeAmp="false" namespace="/admin">
							<s:param name="admissionInquiry.id" value="%{id}" />
						</s:url> <sj:a href="%{doEditClassSubjects}"
							targets="admissionsContent"
							cssClass="btn btn-xs purple">
							<i class="fa fa-edit"></i>Add Application</sj:a></td>
						
					<%-- <s:if test='%{user.IsSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y"}'>
						<td>
							<s:if test='%{#session.previousYear=="N"}'>
								<s:if test='%{startDate.compareTo(toDate) > 0}'>
								
								<a data-toggle="modal" href="#EditHolidaysListDiv"
										class="btn btn-xs purple"
										onclick="javascript:PopupEditHolidaysList(<s:property value="%{id}" />,<s:property value="%{academicYear.id}" />,<s:property value="%{selectedId}" />);"><i
										class="fa fa-edit"></i>Edit </a>
								
								
									
								</s:if>
							</s:if>
						</td>
						<td>
							<s:if test='%{#session.previousYear=="N"}'>
								<s:if test='%{startDate.compareTo(toDate) > 0}'>
									<s:url id="deleteSchoolHolidays"
										action="ajaxdeleteSchoolHolidays" escapeAmp="false"
										includeParams="all" namespace="/admin">
										<s:param name="anyId" value="%{id}"></s:param>
										<s:param name="tempString" value="%{description}"></s:param>
										<s:param name="academicYearId" value="%{#session.academicYear}"></s:param>
										<s:param name = "startDateFormate" value="%{startDateFormate}"></s:param>
										<s:param name = "endDateFormate" value="%{endDateFormate}"></s:param>
									</s:url>
									<s:div id='%{deleteSchoolHolidays}'
										cssClass="btn btn-xs red" title="Delete this leave"
										onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');">
										<i class="fa fa-times"></i>Delete</s:div>
								</s:if>
							</s:if>
						</td>
					</s:if> --%>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no admission enquiries.
	</div>
</s:else> 
<div id="EditHolidaysListDiv"></div>
<script type="text/javascript">
changePageTitle('Manage School Holidays');
TableAdvanced.init();
function PopupEditHolidaysList(anyId,academicYearId,studyClassId) {
		var url = jQuery.url.getChatURL("/admin/ajaxDoEditSchoolHolidays.do");
		$('#EditHolidaysListDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "anyId=" + anyId+"&academicYearId="+academicYearId+"&studyClassId="+studyClassId,
				success : function(html) {
					$("#EditHolidaysListDiv").html(html);
				}
			});
	} 
</script>
