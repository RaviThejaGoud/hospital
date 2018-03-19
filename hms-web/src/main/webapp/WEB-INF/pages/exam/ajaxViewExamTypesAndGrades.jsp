<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{tempString == 'overAllGrades'}">
	<s:if
		test="%{overAllGradesList!= null && !overAllGradesList.isEmpty()}">
		<h4 class="bold pageTitle">
			Exam grades
		</h4>
		<s:if test='%{#session.previousYear=="N"}'>
			<p>
				<span class="label label-danger">NOTE :</span>&nbsp; You can edit/update
				existing exam grades by clicking on edit pen icon in each row at right
				side.
			</p>
		</s:if>
		<div class="spaceDiv"></div>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<!--<div data-target="overAllGradesCont">
				-->
			<thead>
				<tr>
					<th>
						Grade Name
					</th>
					<th>
						Min Marks (%)
					</th>
					<th>
						Max Marks (%)
					</th>
					<th>
						Description
					</th>
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</tr>
			</thead>
			<!--<div id="overAllGradesCont">
				-->
			<tbody>
				<s:iterator value="overAllGradesList">
					<!--<div overalGrade="<s:property value='gradeName' />" class="item">
						-->
					<tr>
						<td>
							<s:property value="gradeName" />
						</td>
						<td>
							<s:property value="minMarks" />
						</td>
						<td>
							<s:property value="maxMarks" />
						</td>
						<td>
							<s:property value="description" />
						</td>
						<td>
							<s:if test='%{#session.previousYear=="N"}'>
								<a data-toggle="modal" href="#responsive3"
									class="btn btn-xs purple"
									onclick="javascript:PopupExamGradesDetials(<s:property value="%{id}" />);"><i
									class="fa fa-edit"></i>Edit </a>
							</s:if>
						</td>
						<td>
							<s:if test='%{#session.previousYear=="N"}'>
								<s:url id="removeOverallGrade" action="ajaxRemoveOverAllGrades"
									includeParams="all" escapeAmp="false" namespace="/exam">
									<s:param name="overAllGrades.id" value="%{id}" />
									<s:param name="tempString">overAllGrades</s:param>
								</s:url>
								<s:div cssClass="btn btn-xs red"
									onclick="javascript:confirmDialogWithTarget(this,'examContentInfo');"
									id='%{removeOverallGrade}' title="Delete this grade">
									<i class="fa fa-times"></i>Delete
									</s:div>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You have not created exam grades. Creating exam grades is
			simple process and system would guide you.
		</div>
	</s:else>
</s:if>
<s:elseif test="%{tempString == 'grades'}">
	<!--<s:if test='%{#session.previousYear=="N"}'>
				<div align="left">
					<s:url id="doAddNewGradesTypeList" action="ajaxDoAddSchoolGrades"
						includeParams="all" escapeAmp="false">
						<s:param name="schoolGrades.id" value="0"></s:param>
					</s:url>
					<sj:a href="%{doAddNewGradesTypeList}" indicator="indicator"
						targets="examContentInfo" button="false" cssClass="linkRight">Add Subject Grades</sj:a></div>
				</s:if>
				-->
	<s:if test="%{schoolGradesList!= null && !schoolGradesList.isEmpty()}">
		<h4 class="bold pageTitle">
			Subject individual grades
		</h4>
		<s:if test='%{#session.previousYear=="N"}'>
			<p>
				<span class="label label-danger">NOTE :</span>&nbsp; You can edit/update
				existing grades by clicking on edit pen icon in each row at right
				side.
			</p>
		</s:if>
		<div class="spaceDiv"></div>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_3">
			<thead>
				<tr>
					<th>
						Grade Name
					</th>
					<th>
						Min Marks (%)
					</th>
					<th>
						Max Marks (%)
					</th>
					<th>
						Grade Points
					</th>
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</tr>
			</thead>
			<!--<div id="schoolGradesCont">
					-->
			<tbody>
				<s:iterator value="schoolGradesList">
					<!--<div gradeName="<s:property value='gradeName' />" class="item">
						-->
					<tr>
						<td>
							<s:property value="gradeName" />
						</td>
						<td>
							<s:property value="minMarks" />
						</td>
						<td>
							<s:property value="maxMarks" />
						</td>
						<td>
							<s:property value="gradePoints" />
						</td>
						<td>
							<s:if test='%{#session.previousYear=="N"}'>
								<a data-toggle="modal" href="#responsive2"
									class="btn btn-xs purple"
									onclick="javascript:PopupSchoolGradesDetials(<s:property value="%{id}" />);"><i
									class="fa fa-edit"></i>Edit </a>
							</s:if>
						</td>
						<td>
							<s:if test='%{#session.previousYear=="N"}'>
								<s:url id="removeSubjGrade" action="ajaxRemoveSchoolGrades"
									includeParams="all" escapeAmp="false" namespace="/exam">
									<s:param name="schoolGrades.id" value="%{id}" />
									<s:param name="tempString" value="%{tempString}" />
								</s:url>
								<s:div cssClass="btn btn-xs red"
									onclick="javascript:confirmDialogWithTarget(this,'examContentInfo');"
									id='%{removeSubjGrade}' title="Delete this grade">
									<i class="fa fa-times"></i>Delete</s:div>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You have not created Subject grades. Creating Subject grades is simple
			process and system would guide you.
		</div>
	</s:else>
</s:elseif>
<s:elseif test="%{tempString == 'examTypes'}">
	<s:if test="%{examTypeList!= null && !examTypeList.isEmpty()}">
		<h4 class="bold pageTitle">
			Current exam types
		</h4>
		<p>
			<span class="label label-danger">NOTE :</span>&nbsp; You can edit exam
			types by clicking on the edit pen icon in each row at right side.
		</p>
		<div class="spaceDiv"></div>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<!--  Email and SMS Alert count indicators, how many alerts sent so far by system to Parents. -->
			<thead>
				<tr>
				<th style="display: none;">
			    </th>
					<th>
						Exam Type
					</th>
					<th>
						Pass Marks
					</th>
					<th>
						Max Marks
					</th>
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="examTypeList">
					<!--<div examType="<s:property value='examType' />"
								maxMarks="<s:property value='maxMarks' />" class="item">
								-->
					<tr>
					<td style="display: none;">
						 <s:property value="sortingOrder"/>
						</td>
						<td>
							<s:property value="examType" />
						</td>
						<td>
							<s:property value="minMarks" />
						</td>
						<td>
							<s:property value="maxMarks" />
						</td>
						<td>

							<s:url id="doViewExamTypesList" action="ajaxDoAddExamType"
								includeParams="all" escapeAmp="false" namespace="/exam">
								<s:param name="examTypes.id" value="%{id}" />
							</s:url>
							<sj:a href="%{doViewExamTypesList}" indicator="indicator"
								targets="examContentInfo" cssClass="btn btn-xs purple"
								title="Edit">
								<i class="fa fa-edit"></i>Edit</sj:a>
						</td>
						<td>
							<s:if test='%{#session.previousYear=="N"}'>
								<s:url id="removeExamType" action="ajaxRemoveExamType"
									includeParams="all" escapeAmp="false" namespace="/exam">
									<s:param name="examTypes.id" value="%{id}" />
									<s:param name="tempString" value="%{tempString}" />
								</s:url>
								<s:div cssClass="btn btn-xs red"
									onclick="javascript:confirmDialogWithTarget(this,'examContentInfo');"
									id='%{removeExamType}' title="Delete this exam type">
									<i class="fa fa-times"></i>Delete
													</s:div>
							</s:if>
						</td>
					</tr>
				</s:iterator>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You have not created exam types, Creating exam type is simple process
			and system would guide you.
		</div>
	</s:else>
</s:elseif>
<s:elseif test="%{tempString == 'subTypes'}">
	<h4 class="bold pageTitle">
		Current exam subtypes
	</h4>
	<s:if test="%{subTypesList != null && !subTypesList.isEmpty()}">
		<p>
			<span class="label label-danger">NOTE :</span>&nbsp; You can edit exam
			subtypes by clicking on the edit pen icon in each row at right side.
		</p>
		<div class="spaceDiv"></div>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
				<th style="display: none;">
			    </th>
					<th>
						Subtype
					</th>
					<th>
						Is Scheduled Type
					</th>
					<th>
						Manage Subtype Grades
					</th>
					<th>
						Edit
					</th>
					<th>
						Delete
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="subTypesList">
					<tr>
					<td style="display: none;">
						 <s:property value="sortingOrder"/>
						</td>
						<td>
							<s:property value="subTypeName" />
						</td>
						<td>
							<s:if test='%{schedule ==true}'>
							Yes
						</s:if>
							<s:else>
						 	 No
						</s:else>
						</td>
						<td>
							<a data-toggle="modal" href="#responsive5"
								class="btn btn-xs purple"
								onclick="javascript:PopupExamSubTypesGradesDetials('<s:property value="%{id}" />','<s:property value="subTypeName" />');"><i
								class="fa fa-edit"></i>Manage</a>
						</td>
						<td>
							<s:if test='%{(#session.previousYear=="N") && !(predefinedSubType)}'>
								<a data-toggle="modal" href="#responsive4"
									class="btn btn-xs purple"
									onclick="javascript:PopupExamSubTypesDetials(<s:property value="%{id}" />);"><i
									class="fa fa-edit"></i>Edit </a>
							</s:if>
						</td>
						<td>
							<s:if
								test='%{(#session.previousYear=="N") && !(predefinedSubType)}'>
								<s:url id="removeSubType" action="ajaxRemoveSubType"
									includeParams="all" escapeAmp="false" namespace="/exam">
									<s:param name="subType.id" value="%{id}" />
									<s:param name="tempString" value="%{tempString}" />
								</s:url>
								<s:div cssClass="btn btn-xs red"
									onclick="javascript:confirmDialogWithTarget(this,'examContentInfo');"
									id='%{removeSubType}' title="Delete this subtype">
									<i class="fa fa-times"></i>Delete
											</s:div>
							</s:if>
						</td>
					</tr>
					<!--<div id="viewSubTypeGrades<s:property value='id' />"
						style="display: none;" class='load'>
				</div>
			-->
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You have not created Subtypes, Creating Subtype is simple process and
			system would guide you.
		</div>
	</s:else>
</s:elseif>
<div id="responsive2"></div>
<div id="responsive3"></div>
<div id="responsive4"></div>
<div id="responsive5"></div>
<script type="text/javascript">
$(document).ready(function() {
	TableAdvanced.init();
	TableEditable.init();
	UIExtendedModals.init();
	$('.blockHeader h2').html('Manage Academics');
	if ('<s:property value="tempString"/>' == "overAllGrades") {
		changePageTitle("Manage OverAll Grades");
	 }else if ('<s:property value="tempString"/>' == "grades") {
		 changePageTitle("Manage Subject Grades");
	}else if ('<s:property value="tempString"/>' == "examTypes") {
		changePageTitle("Manage Exam Types");
	}else if ('<s:property value="tempString"/>' == "subTypes") {
		changePageTitle("Manage Subtypes");
	}

	$.subscribe('toggleSubTypeGradesDiv', function(event, data) {
		var rowObj = $('#' + data.id);
		if (rowObj.is(":hidden")) {
			//$('#addBlocksError').hide();
			rowObj.show();
		} else {
			rowObj.hide();
		}
	});
	});
	function PopupExamGradesDetials(id) {
		var url = jQuery.url.getChatURL("/exam/ajaxDoAddOverAllGrades.do");
		$('#responsive3').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "overAllGrades.id=" + id,
				success : function(html) {
					$("#responsive3").html(html);
				}
			});
		} 
		function PopupSchoolGradesDetials(id) {
		var url = jQuery.url.getChatURL("/exam/ajaxDoAddSchoolGrades.do");
		$('#responsive2').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "schoolGrades.id=" + id,
				success : function(html) {
					$("#responsive2").html(html);
				}
			});
		}
		function PopupExamSubTypesDetials(id) {
		var url = jQuery.url.getChatURL("/exam/ajaxDoAddSubTypesList.do");
		$('#responsive4').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "subType.id=" + id,
				success : function(html) {
					$("#responsive4").html(html);
				}
			});
		}
		function PopupExamSubTypesGradesDetials(id,subTypeName) {
		var url = jQuery.url.getChatURL("/exam/ajaxManageSubTypeGrades.do");
		$('#responsive5').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "tempId=" + id+"&tempString="+subTypeName,
				success : function(html) {
					$("#responsive5").html(html);
				}
			});
		}

</script>
