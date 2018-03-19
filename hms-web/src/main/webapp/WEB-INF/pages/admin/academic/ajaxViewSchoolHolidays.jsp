<%@ include file="/common/taglibs.jsp"%>
 <s:if test="%{holidayBoardMessagesList!= null && !holidayBoardMessagesList.isEmpty()}">
	<div class="col-md-12">
		<div class="col-md-7" style="padding-top: 10px;">
			<s:if test='%{user.IsSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
				<span class="label label-danger"> NOTE : </span>&nbsp; You
				cannot find 'Edit' or 'Delete' icons for current and past
				holidays details.
			</s:if>
		</div>
		<div class="col-md-5">
			<div class="form-group">
				<div align="right">
			<s:if test='%{selectedId=="0" || selectedId == "" || selectedId == null}'>
					<a href="${pageContext.request.contextPath}/admin/printHolidaysList.do?selectedId=0" class="btn green"> Download Holidays</a>
				</s:if>
				<s:else>
					<a href='${pageContext.request.contextPath}/admin/printHolidaysList.do?selectedId=<s:property value="selectedId"/>' class="btn green"> Download Holidays</a>
				</s:else>
					
				</div>
			</div>
		</div>
	</div>
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_5">
		<thead>
			<tr>
				<th style="display: none;">
			    </th>
				<th>
					Holiday
				</th>
				
				<th>Start - End date</th>
				
				<th>No.of days</th>
				
				<s:if test='%{user.IsSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</s:if>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="holidayBoardMessagesList">
				<tr>
				<td style="display: none;">
						 <s:property value="sortingDateFormat"/>
						</td>
					<td>
						<s:property value="description" />
					</td>
					<td>
						<s:property value="startDateFormat" /> - <s:property value="endDateFormat" />
					</td>
					<td><s:property value="noOfHolidayDays" /></td>
					<s:if test='%{user.IsSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
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
					</s:if>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>  
	<s:if test='%{user.IsSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'> 
		<div class="alert alert-info">
			Currently there are no holidays,you can add holidays by clicking on Add holidays .
		</div>
	</s:if>
	<s:else> <div class="alert alert-info">
			Currently there are no holidays added.
			</div> 
	</s:else>
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
