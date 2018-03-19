<%@ include file="/common/taglibs.jsp"%>
<div id="addChildDiv">
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-file"></i> Student Assignments
			</div>
		</div>
		<div class="portlet-body">
			<div id="site_statistics_content">
				<s:if test='%{classAssignment != null}'>
					<span id="tempIdSpan" class="<s:property value='tempId'/>"></span>
					<div class="form-group form-horizontal">
						<label class="control-label row-md-3" style="font: bold;">
							Subject Name &nbsp;&nbsp;&nbsp;&nbsp;: </label> &nbsp;
						<s:property value="classAssignment.subjectName" />
					</div>
					<div>
						<label class="control-label row-md-3" style="font: bold;">
							Completion Date : </label> &nbsp;
						<s:property value="classAssignment.assignmentDateFormat" />
					</div>
					<div>
						<label class="control-label row-md-3" style="font: bold;">
							Description &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; : </label> &nbsp;
						<s:property value="classAssignment.description" />
					</div>
					<div class="spaceDiv">&nbsp;</div>
					<div class="spaceDiv">&nbsp;</div>
					<div id="viewAssignmentDetails">
						<s:url id="urlManageClassAssignment"
							action="ajaxViewStudentAssignment" namespace="/student">
							<s:param name="tempId" value="tempId" />
						</s:url>
						<sj:a id="manageClassAssignment"
							href="%{urlManageClassAssignment}" cssClass='ajaxify SMCA'
							class="btn green btn-xs" targets="mainContentDiv">More Details</sj:a>
					</div>
				</s:if>
				<s:else>
				    Currently No Assignments are available.
				</s:else>
				<div id="spaceDiv"></div>

			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/FusionCharts.js">
	
</script>



