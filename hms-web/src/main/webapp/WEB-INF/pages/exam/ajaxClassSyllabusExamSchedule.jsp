<%@ include file="/common/taglibs.jsp"%>
	<span id='<s:property value='examType'/>' class='examTypeSpan'></span>
	<s:if test="%{(startDate != null && startDate != empty) && (endDate != null && endDate != empty)}">
		<s:if test="%{examScheduleList!= null && !examScheduleList.isEmpty()}">
		<div class="form-group">
		<label class="control-label col-md-3">Copy syllabus to all sections</label>
		 <p class="form-control-static">
			<s:checkbox name="title" cssClass="copySchedules" theme="simple"></s:checkbox>
		</p>	
		</div>	
		<div class="panel-body col-md-12">
			<div class="col-md-1"><span class="label label-danger"> NOTE : </span></div>
			<div class="col-md-10">
				<ul>
					<li>
						Please select this check box for copying these exam syllabus to remaining sections if available. 
					</li>
					<li>
						These exam syllabus will copy only if remaining sections have same subjects. 
					</li>
					<li>
						If exam syllabus is already defined to remaining sections that syllabus will be overwritten. 	
					</li>
				</ul>
				</div>
			</div>
			 
		<table class="table table-striped table-bordered table-hover table-full-width">
		<thead>
			<tr>
				<th>
					Subject
				</th>
				<th>
					Exam Date
				</th>
				<th>
					SubType
				</th>
				<th>
					Syllabus
				</th>
			</tr>
			</thead>
			<tbody>
				<s:iterator value="examScheduleList">
				<span class="scheduleData"> 
				<span id='<s:property value='scheduleId'/>' class='scheduleId'></span>
						<span id='<s:property value='subTypeId'/>' class='subTypeId'></span>
						<span id='<s:property value='classSubjectId'/>' class='classSubjectId'></span>  
					 	<div class="subTypeMaxMarks<s:property value='classSubjectId'/>">			
						<div id="dispaySelectedDate" style="display: none;"> </div>		
							<tr>
								<td id="subName">
									<label>
										<s:property value="name" />
									</label>
								</td>
								<td>
									<label>
										<s:property value="examDateStr" />
									</label>
								</td>
								<td>
									<s:if test="%{schedule}">
										<label>
											<s:property value="subTypeName" />
										</label>
									</s:if>
									<s:else>
										<label>
											N/A
										</label>
									</s:else>
								</td>
								<td>
									<sj:textarea name="syllabus" id="syllabus_%{classSubjectId}_%{subTypeId}" cssClass="form-control input-medium syllabus" />
								</td>
							</tr>
					 	</div> 
					</span>  
				</s:iterator>
			</tbody>
		</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				You have not assigned any exam dates for the selected class and exam type.  
			</div>
		</s:else>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Please add academic planner details. Then only you can add or edit exam schedules.
				<s:url id="urlSendSmsLink" action="ajaxAcademicSchoolSettings"
					includeParams="all" namespace="/admin" />
				<sj:a href="%{urlSendSmsLink}" targets="mainContentDiv"
					indicator="indicator" cssClass="academicPlannedId">Click here</sj:a>
			to add academic planner details.
		</div>	
	</s:else>
<script type="text/javascript">    
	$(document).ready(function() {
	TableAdvanced.init();
	    $("input:checkbox, input:radio").uniform();
		var classId = $("span.classIdSpan").attr('id');
		var examTypeId = $("span.examTypeSpan").attr('id');
		$('.examTypeId').val(examTypeId);
		$('.classId').val(classId);
	});
	$('a.academicPlannedId').click(function(){
	window.location.hash="target=ES.ajaxify AAP";
	window.location.reload();
});
</script>
