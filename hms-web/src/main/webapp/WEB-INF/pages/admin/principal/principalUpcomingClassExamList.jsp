<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="block grid_17">

	<div class="block_head">
		<h2>
			Exam Schedules:
		</h2>
	</div>
	<div id="staffLeaveApproval" class="block_content">
		<div class="grid_17">
		<div class="grid_2">
			<label>
				<b>Select Class :</b>
			</label>
		</div>
		<div class="grid_4">
			<s:select list="classList" listKey="id" listValue="className"
				cssClass="required" required="true" name="classId" headerKey=""
				headerValue="- Select -" cssStyle="width:150px;"
				requiredposition="first" theme="simple"
				onchange="javascript:onClassChange(this.value);">
			</s:select>
		</div>
		</div>
		<br/><br/>
		<div id="priExamSchedules">
			<s:if
				test="%{viewClassExamDetails != null && !viewClassExamDetails.isEmpty()}">
				<s:if test="%{examTypeList != null && !examTypeList.isEmpty()}">
						<table class="striped" width="100%" style="margin-bottom: 0;"
							cellpadding="1" cellspacing="1">
							<thead>
								<tr>
									<!--<th width="15%" class="head">
									Exam Type
								</th>-->
									<th width="15%" class="head">
										Class Name
									</th>
									<th width="25%" class="head">
										Subjects
									</th>
									<th class="head" width="15%">
										Exam Date
									</th>
									<th class="head" width="15%">
										Start Time
									</th>
									<th width="15%" class="head">
										End Time
									</th>
								</tr>
							</thead>
							<s:iterator value="examTypeList">
								<s:set name="examTypeName" value="examType"></s:set>
								<tr>
									<td colspan="5" style="text-align: center; background: #ccc">
										<label style="text-align: center">
											<b><s:property value="examTypeName" /> Schedules</b>
										</label>
									</td>
								</tr>
							<s:iterator value="viewClassExamDetails">
								<s:if test="%{examType == #examTypeName}">
									<tr class="loaded odd">
										<!--<td width="15%">
							<s:property value="examType" />
						</td>-->
										<td width="15%">
											<s:property value="className" />
										</td>
										<td width="25%">
											<s:property value="name" />
										</td>
										<td width="15%">
											<s:property value="examDateStr" />
										</td>
										<td width="15%">
											<s:property value="startTime" />
										</td>
										<td width="15%">
											<s:property value="endTime" />
										</td>
									</tr>

								</s:if>

							</s:iterator>
							</s:iterator>
						</table>
					
				</s:if>

			</s:if>
			<s:else>
		Exam Schedule not prepared
	</s:else>
		</div>
	</div>
</div>
<style type="text/css">
.active {
	color: #0033CC;
	text-decoration: none;
}

.inactive {
	font-weight: bold;
	text-decoration: underline;
	cursor: default;
}

.paginator {
	text-align: center;
	color: #FFF;
}
</style>
<script type="text/javascript">
changePageTitle('Principal View All Exam Schedules');
$.subscribe('doInitAddGroupMember', function(event, data) {
	var rowObj = $('#' + data.id);
	if (rowObj.is(":hidden")) {
		rowObj.show();
	} else {
		rowObj.hide();
	}
});


function onClassChange(className) {
	 $('#priExamSchedules').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	    var id = className;
	    var complete = '<s:property value="examType"/>';
		var pars = "classId=" + id;
       $.ajax( {
	        url : "ajaxViewPriSelectedSchedules.do",
			cache : false,
			data: pars,
			 success : function(response) {
			 $('#priExamSchedules').html(response);
			}
		});  
	}
</script>
