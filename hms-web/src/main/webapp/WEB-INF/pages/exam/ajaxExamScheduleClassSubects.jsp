	<%@ include file="/common/taglibs.jsp"%>
	<span class="deleteExamSchedule" id="<s:property value='admissionFee'/>"></span>
	<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
	<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
	<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
	<span id='<s:property value='examType'/>' class='examTypeSpan'></span>
	<s:if test="%{(startDate != null && startDate != empty) && (endDate != null && endDate != empty)}">
		 <s:if test='%{toDate.compareTo(endDate) > 0}'>
		 	<jsp:include page="/common/messages.jsp" />
		</s:if>
		
		<s:if test="%{subjectsList!= null && !subjectsList.isEmpty()}">
		
			<s:if test="%{examScheduleList!= null && !examScheduleList.isEmpty()}">
			<div class="row">
				<div class="form-group form-horizontal">
					<label class="col-md-3 control-label">Copy Exam schedules to all :
					</label>
					<div class="radio-list">
						<label class="radio-inline"> 
							<input type="radio" value="S" name="copyExamSchedules" checked="checked" /> Section Wise
						</label> 
						<label class="radio-inline"> 
							<input type="radio" value="C" name="copyExamSchedules"/> Class Wise
						</label>
					</div>
				</div>
			</div>
			<div class="form-group" id="copyAllExamSchedulesSectionWise">
			<label class="control-label col-md-3">
				Copy schedules to all sections :</label>
				 <p class="form-control-static">
					<s:checkbox name="title" cssClass="copySchedules" theme="simple" checked="checked"/>
				</p>
					<div class="panel-body col-md-12">
						<div class="col-md-1">
							<span class="label label-danger"> NOTE : </span>
						</div>
						<div class="col-md-10">
							<ul>
								<li>
									Please select above check box for copying these exam schedules to remaining sections.These exam schedules will copy only if remaining sections have same subjects.
								</li>
								<li>
									If exam schedules are already defined to remaining sections those schedules will be overridden.
								</li>
							</ul>
						</div>
					</div>
				<div class="form-body"></div>
			</div>
			<div class="row" id="copyAllExamSchedulesClassWise"
					style="display: none;">
					<s:if test="%{(classList != null && !classList.isEmpty())}">
						
							<div class="form-group">
							<label class="col-md-2 control-label" style="line-height: 10px;"> <span
									class="required">*</span>All Classes :
								</label>
								<div class="col-md-10">
									<div class="checkbox-list">
										 <input type="checkbox"
											name="" value="" onClick="checkAllClasses()"
											class="checkbox allClasses">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label"> <span
									class="required">*</span>Classes With Students :
								</label>
								<div class="col-md-10">
									<div class="checkbox-list">
										<s:checkboxlist name="chkBoxSelectedIds" list="classList"
											listKey="id" listValue="className" theme="ems"
											cssClass="small" />
									</div>
								</div>
							</div>
						</s:if>
						<s:else>
						<div class="alert alert-info">
							classes not assigned to selected exam type.
						</div>
						</s:else>
				<div class="form-body"></div>
			</div>
			<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
				<thead>
					<tr>
						<th>
							Subject
						</th>
						<th>
							SubType
						</th>
						<th>
						   Max Marks	
						</th>
						<th>
							Exam Date (MM/DD/YYYY)
						</th>
						<th>
							Start Time
						</th>
						<th>
							End Time
						</th>
						<!-- <th>
							HT
						</th> -->
						<s:if test='%{toDate.compareTo(endDate) <=0}'>
						<th>
							Delete
						</th>
						</s:if>
					</tr>
				</thead>
				<tbody>
				<s:set var="subjectIds" value="-1"></s:set>
					<s:iterator value="examScheduleList" status="itStatus">
						<div id="dispaySelectedDate" style="display: none;"> </div>	
							<tr class="scheduleData subTypeMaxMarks<s:property value='subjectId'/>">						
							<td id='<s:property value='scheduleId'/>' class='scheduleId'>
								<%-- <s:if test="%{subjectId != #subjectIds}"> --%>
								<label>
									<s:property value="subjectName" />
								</label>
								<%-- </s:if> --%>
							</td>
								<s:if test="%{subTypeSchedule}">
									<td style="width:100px;"> 
										<s:if test='%{toDate.compareTo(examDate) > 0}'>
											<div style="width:23px;float: left;"> 
												<s:checkbox name="scheduled" cssClass="checkbox" title="subTypeChk" theme="simple" disabled="true" 
													id="subTypesContent_%{subTypeId}_%{subjectId}" onclick="javascript:checkMaxMarks(%{subjectId},%{examTypeMaxMarks},%{subTypeId},'%{subjectName}');">
												</s:checkbox>
											</div>
											<div>
												 <s:property value="subTypeName" />
											</div>
										</s:if>
										<s:else>
											<div style="width:23px;float: left;"> 
												<s:checkbox name="scheduled" cssClass="checkbox" title="subTypeChk" theme="simple"
													id="subTypesContent_%{subTypeId}_%{subjectId}" onclick="javascript:checkMaxMarks(%{subjectId},%{examTypeMaxMarks},%{subTypeId},'%{subjectName}');">
												</s:checkbox>
											</div>
											<div>
												 <s:property value="subTypeName" />
											 </div>
										</s:else>
									</td>
									
									<td  id='<s:property value='subjectId'/>' class='subjectId'>
									<s:if
										test='%{scheduleMaxMarks != null && scheduleMaxMarks != "" && scheduleMaxMarks != "0.0"}'>
										<s:if test='%{toDate.compareTo(examDate) > 0}'>
											<sj:textfield name="scheduleMaxMarksLongType" id="maxMarks_%{subjectId}_%{subTypeId}" disabled="true"
											cssClass="form-control input-small maxMarks numeric" onkeyup="javascript:checkValidation(this);"
											maxlength="4" cssStyle="width:35px;" onblur="javascript:checkMaxMarks(%{subjectId},%{examTypeMaxMarks},%{subTypeId},'%{subjectName}');"></sj:textfield>
										</s:if>
										<s:else>
											<sj:textfield name="scheduleMaxMarksLongType" id="maxMarks_%{subjectId}_%{subTypeId}"
											cssClass="form-control input-small maxMarks numeric" onkeyup="javascript:checkValidation(this);"
											maxlength="4" cssStyle="width:35px;" onblur="javascript:checkMaxMarks(%{subjectId},%{examTypeMaxMarks},%{subTypeId},'%{subjectName}');"></sj:textfield>										
										</s:else>
									</s:if>
									<s:elseif test='%{predefinedSubType}'>
										<s:if test='%{toDate.compareTo(examDate) > 0}'>
											<sj:textfield name="examTypeMaxMarksLongType" id="maxMarks_%{subjectId}_%{subTypeId}" disabled="true"
											cssClass="form-control input-small maxMarks numeric" onkeyup="javascript:checkValidation(this);"
											maxlength="4" cssStyle="width:35px;" onblur="javascript:checkMaxMarks(%{subjectId},%{examTypeMaxMarks},%{subTypeId},'%{subjectName}');"></sj:textfield>
										</s:if>
										<s:else>
											<sj:textfield name="examTypeMaxMarksLongType" id="maxMarks_%{subjectId}_%{subTypeId}"
											cssClass="form-control input-small maxMarks numeric" onkeyup="javascript:checkValidation(this);"
											maxlength="4" cssStyle="width:35px;" onblur="javascript:checkMaxMarks(%{subjectId},%{examTypeMaxMarks},%{subTypeId},'%{subjectName}');"></sj:textfield>									
										</s:else>
									</s:elseif>
									<s:else>
									 <s:if test='%{toDate.compareTo(examDate) > 0}'>
										<sj:textfield name="examTypeMaxMarksLongType" id="maxMarks_%{subjectId}_%{subTypeId}" disabled="true"
											cssClass="form-control input-small maxMarks numeric" onkeyup="javascript:checkValidation(this);"
											value="" maxlength="4" cssStyle="width:35px;" onblur="javascript:checkMaxMarks(%{subjectId},%{examTypeMaxMarks},%{subTypeId},'%{subjectName}');"></sj:textfield>
									 </s:if>
									 <s:else>
									 		<sj:textfield name="examTypeMaxMarksLongType" id="maxMarks_%{subjectId}_%{subTypeId}"
											cssClass="form-control input-small maxMarks numeric" onkeyup="javascript:checkValidation(this);"
											value="" maxlength="4" cssStyle="width:35px;" onblur="javascript:checkMaxMarks(%{subjectId},%{examTypeMaxMarks},%{subTypeId},'%{subjectName}');"></sj:textfield>
									 </s:else>
									</s:else>
									<label class="error" id="examTypeLabelmaxMarks_<s:property value='subjectId'/>_<s:property value='subTypeId'/>" style="display: none;">This field is required.</label>
								  </td>
								
									
									<td id="selectedDateDiv">
										<s:if test='%{toDate.compareTo(examDate) > 0}'>
											<div data-date-start-date="-100d"  data-date-format="mm/dd/yyyy"
												class="input-group input-medium date date-picker">	
												<input type="text" class="form-control examDate" name="examDate" value='<s:property value="examDateStr"/>' id="examDateSchedule%{subjectId}_%{subTypeId}"  disabled="disabled">
												<span class="input-group-btn">	
													<button type="button" class="btn default"><i class="fa fa-calendar"></i></button>
												</span>
											</div> 
										</s:if>
										<s:else>
											<div data-date-start-date="-100d" data-date-format="mm/dd/yyyy" 
												class="input-group input-medium date date-picker">	
												<input type="text" class="form-control examDate" name="examDate"  value='<s:property value="examDateStr"/>' id="examDateSchedule%{subjectId}_%{subTypeId}" readonly="readonly" >
												<span class="input-group-btn">	
												<button type="button" class="btn default"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
										</s:else>
										<label class="error" id="LableexamDateSchedule<s:property value='subjectId'/>_<s:property value='subTypeId'/>" style="display: none;">This field is required.</label>
									</td>
									<td id='<s:property value='classSectionId'/>' class='classSectionId'>
										 <s:if test='%{toDate.compareTo(examDate) > 0}'>
										 	<s:select list="#{'07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 PM':'12:00 PM','12:15 PM':'12:15 PM','12:30 PM':'12:30 PM','12:45 PM':'12:45 PM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM'}"
												name="startTime" cssClass="form-control input-small numeric startTime" headerKey="" disabled="true" cssStyle="width:100px;" headerValue="- Select -"
												theme="simple"></s:select>
										 </s:if>
										 <s:else>
										 	<s:select list="#{'07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 PM':'12:00 PM','12:15 PM':'12:15 PM','12:30 PM':'12:30 PM','12:45 PM':'12:45 PM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM'}"
												id="startTime_%{subjectId}_%{subTypeId}" name="startTime" cssClass="form-control input-small numeric startTime" headerKey="" cssStyle="width:100px;" headerValue="- Select -" onchange="javascript:checkStartAndEndTime(%{subjectId},%{subTypeId},this);"
												theme="simple"></s:select>
										 </s:else>
									</td>
									<td id='<s:property value='subTypeId'/>' class='subTypeId'>
										 <s:if test='%{toDate.compareTo(examDate) > 0}'>
										 	<s:select list="#{'07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 PM':'12:00 PM','12:15 PM':'12:15 PM','12:30 PM':'12:30 PM','12:45 PM':'12:45 PM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM'}"
											 disabled="true" name="endTime" cssStyle="width:100px;" cssClass="form-control input-small numeric endTime" headerKey="" headerValue="- Select -" theme="simple"></s:select>
										 </s:if>
										 <s:else>
										 	<s:select id="endTime_%{subjectId}_%{subTypeId}"
											list="#{'07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 PM':'12:00 PM','12:15 PM':'12:15 PM','12:30 PM':'12:30 PM','12:45 PM':'12:45 PM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM'}"
											name="endTime" cssStyle="width:100px;" cssClass="form-control input-small numeric endTime" onchange="javascript:checkStartAndEndTime(%{subjectId},%{subTypeId},this);"
											headerKey="" headerValue="- Select -" theme="simple"></s:select>
										 </s:else>
									</td>
								</s:if>
								<s:else>
									<td style="width:100px;">
										<div style="width:23px;float: left;"> 
											<s:checkbox name="scheduled" cssClass="checkbox"
												title="subTypeChk" theme="simple"
												id="subTypesContent_%{subTypeId}_%{subjectId}"  onclick="javascript:checkMaxMarks(%{subjectId},%{examTypeMaxMarks},%{subTypeId},'%{subjectName}');">
											</s:checkbox>
										</div>
										<div>
											<s:property value="subTypeName" />
										</div>
									</td>
									<td>
									  <sj:textfield name="examTypeMaxMarksLongType" id="maxMarks_%{subjectId}_%{subTypeId}"
											cssClass="form-control input-small maxMarks numeric" onkeyup="javascript:checkValidation(this);"
											value="" maxlength="4" cssStyle="width:35px;" onblur="javascript:checkMaxMarks(%{subjectId},%{examTypeMaxMarks},%{subTypeId},'%{subjectName}');"></sj:textfield>
									</td>
									<td id="selectedDateDiv">
									<div data-date-start-date="-100d" data-date-format="mm/dd/yyyy" 
												class="input-group input-medium date date-picker">	
												<input type="text" class="form-control examDate" name="examDate"  value='<s:property value="examDateStr"/>' id="examDateSchedule%{subjectId}_%{subTypeId}" readonly="readonly" >
												<span class="input-group-btn">	
												<button type="button" class="btn default"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
									<label class="error" id="LableexamDateSchedule<s:property value='subjectId'/>_<s:property value='subTypeId'/>" style="display: none;">This field is required.</label>		
									</td>
									<td><s:select list="#{'07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 PM':'12:00 PM','12:15 PM':'12:15 PM','12:30 PM':'12:30 PM','12:45 PM':'12:45 PM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM'}"
												id="startTime_%{subjectId}_%{subTypeId}" name="startTime" cssClass="form-control input-small numeric startTime" headerKey="" cssStyle="width:100px;" headerValue="- Select -" onchange="javascript:checkStartAndEndTime(%{subjectId},%{subTypeId},this);"
												theme="simple"></s:select>
									</td>
									<td><s:select id="endTime_%{subjectId}_%{subTypeId}"
											list="#{'07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 PM':'12:00 PM','12:15 PM':'12:15 PM','12:30 PM':'12:30 PM','12:45 PM':'12:45 PM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM'}"
											name="endTime" cssStyle="width:100px;" cssClass="form-control input-small numeric endTime" onchange="javascript:checkStartAndEndTime(%{subjectId},%{subTypeId},this);"
											headerKey="" headerValue="- Select -" theme="simple"></s:select>
									</td>
								</s:else>
								<%-- <td>
									<div style="width:23px;float: left;"> 
									<s:if test='%{showInHT == "Y"}'>
											<s:checkbox name="showHt" cssClass="checkbox" title="showHt" theme="simple" id="showHt_%{subTypeId}_%{subjectId}" checked="checked" >
										</s:checkbox>								
									</s:if>
									<s:else>
										<s:checkbox name="showHt" cssClass="checkbox" title="showHt" theme="simple" id="showHt_%{subTypeId}_%{subjectId}">
										</s:checkbox>
									</s:else>
									</div>
								</td> --%>
								<td>
									<s:if test='%{#session.previousYear == "N" && scheduleId!= 0 && toDate.compareTo(endDate) <=0}'>
 										  <a style="cursor: pointer;"  id="removeExamSchedule<s:property value="scheduleId"/>" onclick="javascript:doGetExamScheduleMarks(<s:property value="scheduleId"/>,'<s:property value="examType"/>','<s:property value="classAndSection"/>');" class="btn btn-xs red"><i class="fa fa-times"></i> Delete </a>
										  <img style="display: none;" alt="Loading..." src="${pageContext.request.contextPath}/images/indicator.gif" id="indicator<s:property value="scheduleId"/>">  
 							      </s:if>
								</td>
							</tr>
							<s:set var="subjectIds" value="%{subjectId}"></s:set>
					</s:iterator>
				</tbody>
				</table>
			</s:if>
			<s:else>
				<div class="alert alert-info noExamSchedules">
					You have not scheduled sub types for this exam type to view the exam schedules.				
				</div>
			</s:else>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Please Add subjects for classes.				
			</div>
		</s:else>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Please add academic planner details. Then only you can add or edit exam schedules.
			<s:url id="urlSendSmsLink" action="ajaxAcademicSchoolSettings"
					includeParams="all" namespace="/admin" />
				<sj:a href="%{urlSendSmsLink}" targets="mainContentDiv" cssClass="academicPlannedId"
					indicator="indicator">Click here</sj:a>
				to add academic planner details.
		 </div>	
	</s:else>
 <script type="text/javascript">
	$(document).ready(function() {
		//TableAdvanced.init();
		if($("div.noExamSchedules").is(":visible")){
			$("div.examScheduleSubjectsButtons").find("input.submitBt").attr("disabled",true);
		}
		else{
			$("div.examScheduleSubjectsButtons").find("input.submitBt").attr("disabled",false);
		}
		 $("input:checkbox, input:radio:not('.toggle')").uniform(); 
		var startDate=$('span#startDateSpan').attr("class");
		var endDate=$('span#endDateSpan').attr("class");
		var toDayDate=$('span#toDateSpan').attr("class");
		
		dateRestrictionWithinAcademicYear(startDate,endDate);
		$(this).find('div.timeEntry').each(function(){
			 $(this).find('input.timeChange').each(function(){
	   			$('#'+$(this).attr('id')).timeEntry();
			 });
 	 	}); 
	    $('.date-picker').datepicker({ 
	       format: "mm/dd/yy"
	    }).on('change', function(){
	        $('.datepicker').hide();
	    });
		if(isNonEmpty(startDate) && isNonEmpty(endDate) && isNonEmpty(toDayDate)){
			if ((Date.parse(toDayDate) >=  Date.parse(startDate) && Date.parse(toDayDate) <=  Date.parse(endDate)) || (Date.parse(toDayDate) <  Date.parse(startDate))) {
				$('.date_picker').datepicker( "option" , 'minDate',startDate);
				$('.date_picker').datepicker( "option" , 'maxDate',endDate);
			}else if(Date.parse(toDayDate) >  Date.parse(endDate)){
				$(".date_picker").datepicker('disable');
			}
		}
		$('.numeric').numeric();
		var classId = $("span.classIdSpan").attr('id');
		var examTypeId = $("span.examTypeSpan").attr('id');
		$('.examTypeId').val(examTypeId);
		$('.classId').val(classId);
		$('input[type="radio"]').click(function(){
	        if($(this).attr("value")=="S"){
	        	 $('div#copyAllExamSchedulesSectionWise').show();
				   $('div#copyAllExamSchedulesClassWise').hide();
	        }
	        if($(this).attr("value")=="C"){
	        	 $('div#copyAllExamSchedulesSectionWise').hide();
				   $('div#copyAllExamSchedulesClassWise').show();
	        }
	    });
		$("input:checkbox, input:radio:not('.toggle')").uniform();  
		$("input[name=chkBoxSelectedIds]").click(function() {
			if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
			   $(".allClasses").parent('span').removeClass("checked");
				$(".allClasses").attr("checked", false);
			} else {
			    $(".allClasses").parent('span').addClass("checked");
				$(".allClasses").attr("checked", true);
			}
		});
		
	});
	function checkAllClasses() {
		if ($(".allClasses").is(':checked')){
		    $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
	 function doGetExamScheduleMarks(id,eTypeName,className) {
		 var classSectionId = $("td.classSectionId").attr('id');
		var eId = $("span.examTypeSpan").attr('id');
		var url = jQuery.url.getChatURL("/exam/ajaxGetExamscheduleMarks.do?anyId="+id);
		$('#indicator'+id).show();
		$.ajax( {
			url : url,
			cache : false,
			dataType : 'json',
				success : function(response) {
				var data= response.marksAvailable;
				var answer = '';
				if(isNonEmpty(data)) {
			        answer = confirm(""+data);
			     }else{
			    	   var answer = confirm("Are you sure you want to delete ?");
					 if (answer) {
					 var pars = "anyId="+id+"&classId="+classSectionId+"&examType="+eId+"&studyClass.className=&examTypes.examType=";
		    	   		var url = jQuery.url.getChatURL("/exam/ajaxDeleteExamScheduleMarks.do");
			    	  		$.ajax( {
								url : url,
								cache : false,
								data : pars,
								success : function(responce) {
									$("#classContentDiv").html(responce);
									}
							});
					 	return true;
					 } else {
						$('#indicator'+id).hide();
					 	return false;
					 }
		     	}
				if(isNonEmpty(answer)) {
					if (!answer) {
			               $('#removeExamSchedule'+id).attr("href", "javascript:void(0);");
			               $('#indicator'+id).hide();
			       } else {
			       		var pars = "anyId="+id+"&classId="+classSectionId+"&examType="+eId+"&studyClass.className="+className+"&examTypes.examType="+eTypeName;		       
			    	   var url = jQuery.url.getChatURL("/exam/ajaxDeleteExamScheduleMarks.do");
			    	   $.ajax( {
								url : url,
								cache : false,
								data : pars,
								success : function(responce) {
									$("#classContentDiv").html(responce);
									}
							});
			       }
				}else{
					$('#indicator'+id).hide();
				}
			}
		});
	}
	
	function checkStartAndEndTime(subjectId,subTypeId,event){
		var startTime = $('#startTime_'+subjectId+'_'+subTypeId).val();
		var endTime= $('#endTime_'+subjectId+'_'+subTypeId).val();
		checkExamScheduleTimings(event.id,startTime,endTime);
		if(isNonEmpty(startTime) && isNonEmpty(endTime)){
			var startDate = new Date("1/1/2007 " + startTime);
		   	var endDate=new Date("1/1/2007 "+endTime);
			if(endDate <= startDate){
			     alert("Start time should be less than end time.");
			     event.value= "";
			}
		}
	}
	function checkValidation(event){
		if(isNonEmpty(event.id)){
			if(isNonEmpty($('examTypeLabel'+event.id))){
				if(!($('label#examTypeLabel'+event.id).is(":hidden"))){
					if(isNonEmpty(event.value)){
						if(event.value.length > 0){
							$('label#examTypeLabel'+event.id).hide();
						}
					}
				}
			}
		}
	}
	function checkDateValidation(event){
		if(isNonEmpty(event.id)){
		//alert($('input#'+event.id).parent().next().find($('select.endTime')).val());
		$('select#startTime_'+ event.id.substring(16)).val('');
		$('select#endTime_'+ event.id.substring(16)).val('');
	     checkHolidayDates(event.id);
			if(isNonEmpty($('Lable'+event.id))){
				if(!($('label#Lable'+event.id).is(":hidden"))){
					if(isNonEmpty(event.value)){
							$('label#Lable'+event.id).hide();
					}
				}
			}
		}
	} 
		function checkMaxMarks(subjectId,maxMarks,subType,subject){
			var totalMarks=0;
			var description = [];
			$('.subTypeMaxMarks'+subjectId).each(function(i,row) {
				if($(row).find(':input:checkbox[name=scheduled]').attr('checked')){
					//description.push($(row).find('div.checkBox').text().trim()+' : '+ $(row).find(':input:text.maxMarks').val());
					if(isNonEmpty($(row).find(':input:text.maxMarks').val()))
						totalMarks += Number($(row).find(':input:text.maxMarks').val());			
				}
			});
			if(totalMarks > maxMarks){
				description.push('Total marks of '+subject+' subject is greater than exam type max marks.Please change subtype max marks of this subject.Total marks should be equal to '+maxMarks+'.');
				$('#maxMarks_'+subjectId+'_'+subType).val('');
				alert("Sum of total subtype max marks is greater than subject max marks");
			}
		}
		function checkHolidayDates(DateId){
			var startDate = $('input#'+DateId).val();
			$('#dispaySelectedDate').hide();
			if(isNonEmpty(startDate)){
					$.ajax( {
						url : jQuery.url.getChatURL("/staff/ajaxCheckHolidaysDates.do?startDate="+startDate),
						cache : true,
						dataType : 'json',
						success : function(response) {
							if(isNonEmpty(response)){
								if(isNonEmpty(response.holidays)){
								$('#dispaySelectedDate').html("<div class='block'><div style='display: block' class='message errormsg'>"
											+ response.holidays + "</div></div>");
									loadCloseIcon();
									$('#dispaySelectedDate').show();
									$('input#'+DateId).val('');
									return false;
								}  
								else{
									return true;
								}
							}
						}
					});				
				}
	  }
	
	function checkExamScheduleTimings(timeId,selectedStartTime,selectedEndTime){
		var selectedDate=$('select#'+timeId).parents("tr").find('td#selectedDateDiv').find('input.examDate').val();
		if(isNonEmpty(selectedDate)){
			var selectedStartDateTime=new Date(selectedDate + " " +selectedStartTime);
			var selectedEndDateTime=new Date(selectedDate + " " +selectedEndTime);
			var selectedTime = $('select#'+timeId).val();
			//alert(selectedStartDateTime+"======"+selectedEndDateTime);
			$('tr.scheduleData').each(function() {
				 var datePickerDates=$(this).find('div').children('div input.examDate').val();
				 var startTime=$(this).find('div').children('div select.startTime').val();
				 var endTime=$(this).find('div').children('div select.endTime').val();
				 if(isNonEmpty(datePickerDates) && isNonEmpty(startTime) && isNonEmpty(endTime)){
				 		var startDateTime = new Date(datePickerDates + " " + startTime);
			   			var endDateTime = new Date(datePickerDates + " " + endTime);
				     	if(selectedDate==datePickerDates){
					     	if(selectedTime == startTime){
					     		alert("Please change the times.This time already assigned another exam.");
					     		$('select#'+timeId).val('');
				     		 	return false;
			     		 	}
			     		 	if(selectedStartDateTime >startDateTime && selectedEndDateTime < endDateTime && selectedEndDateTime > selectedStartDateTime){
					     		alert("Please change the times , on this time already assigned another exam.");
					     		$('select#'+timeId).val('');
				     		 	return false;
					     	}		     		 	
			     		 	if(selectedStartDateTime >startDateTime && selectedEndDateTime <endDateTime && selectedEndDateTime > selectedStartDateTime){
					     		alert("Please change the times , on this time already assigned another exam.");
					     		$('select#'+timeId).val('');
				     		 	return false;
					     	}
				     		if(selectedStartDateTime >startDateTime && selectedStartDateTime <endDateTime){
					     		alert("Please change the times , on this time already assigned another exam.");
					     		$('select#'+timeId).val('');
				     		 	return false;
					     	}
					     	if(selectedEndDateTime >startDateTime && selectedEndDateTime <endDateTime){
					     		alert("Please change the times , on this time already assigned another exam.");
					     		$('select#'+timeId).val('');
				     		 	return false;
					     	}
					     	if(selectedStartDateTime <startDateTime && selectedEndDateTime >endDateTime){
					     		alert("Please change the times , on this time already assigned another exam.");
					     		$('select#'+timeId).val('');
				     		 	return false;
					     	}
				     	 	else{
				     	 		 return true;
				     	 	}
				     } 
		      } 
		  });
		}else{
			alert("Please select date.");
			$('select#'+timeId).val('');
		} 
	}
	$('a.academicPlannedId').click(function(){
	window.location.hash="target=ES.ajaxify AAP";
	window.location.reload();
});
</script>
