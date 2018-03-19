<%@ include file="/common/taglibs.jsp"%>
	<div data-width="760" class="modal fade modal-overflow in"
		style="display: block; width: 760px; margin-left: -379px; margin-top: 100px;"
		aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
					<h4>Exam Schedules</h4>
		</div>
		<div class="modal-body">
		<div class="form-body">
			<div class="row">
				<div class="col-md-3">
					<label>
						<strong>Class Name : <s:property
								value="studyClass.className" /> </strong>
					</label>
				</div>
				<div class="col-md-5">
					<label>
						<strong>Exam Type : <s:property value="examTypes.examType" />
						</strong>
					</label>
				</div>
				<div class="col-md-3">
					<label>
						<strong>Max Marks : <s:property
								value="examTypes.maxMarks" /> </strong>
					</label>
				</div><!--
				<div class="col-md-4">
					<s:url id="doViewAllClassTeacherExamSchedules"
						action="ajaxClassTeacherExamSchedules" includeParams="all"
						escapeAmp="false">
						<s:param name="anyTitle" value="%{anyTitle}"></s:param>
					</s:url>
					<sj:a href="%{doViewAllClassTeacherExamSchedules}"
						targets="mainContentDiv" cssClass="btn default button-previous"
						cssStyle="text-align:right;float:right;">
						<i class="m-icon-swapleft"></i>
 			Back to exam schedules
 	</sj:a>
				</div>
			--></div>
			<s:if
				test="%{examScheduleList != null && !examScheduleList.isEmpty()}">
				<table
					class="table table-striped table-bordered table-hover table-full-width"
					id="sample_2">
					<thead>
						<tr>
							<th>
								Subject Name
							</th>
							<th>
								SubType Name
							</th>
							<th>
								Exam Date
							</th>
							<th>
								Max Marks
							</th>
							<th>
								Start Time
							</th>
							<th>
								End Time
							</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="examScheduleList">
							<tr>
								<td>
									<s:property value="subjectName" />
								</td>
								<td>
									<s:property value="subTypeName" />
								</td>
								<td>
									<s:property value="examDateStr" />
								</td>
								<td>
									<s:property value="maxMarks" />
								</td>
								<td>
									<s:property value="startTime" />
								</td>
								<td>
									<s:property value="endTime" />
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</s:if>
			<s:else>
				<div class="alert alert-info">
					There are no exam schedules for this class.
				</div>
			</s:else>
			</div>
		</div>
	</div>