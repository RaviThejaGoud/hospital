<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<s:form id="editStaffHistory" action="ajaxEditStaffHistory" cssClass="form-horizontal" namespace="/staff" method="post" theme="simple">
	<s:hidden name="tempId" value="%{tempId}"></s:hidden>
	<s:hidden name="tempId1" value="%{tempId1}"></s:hidden>
	<div class="form-body">
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">School Name :</label>
				<div class="col-md-5">
				<sj:textfield name="staffHistoryVo.schoolName" id="schoolName"
					cssClass="form-control" maxlength="60"></sj:textfield>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group ">
				<label class="control-label col-md-4">Salary :</label>
				<div class="col-md-5">
					<sj:textfield name="staffHistoryVo.salary" id="salary"
						  cssClass="numericDot form-control"
						maxlength="10"></sj:textfield>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> Start Date :</label>
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy" class="input-group input-medium date date-picker">
							<input type="text"  id="contractStartDate"  readonly="" class="form-control"  name="staffHistoryVo.startDate" value='<s:property value="staffHistoryVo.startDateStr"/>' onchange="verifyStaffDate();"> 
							<span class="input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button>
							  </span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
		   <div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">End Date :</label>
				<div class="col-md-5">
					<div data-date-format="mm/dd/yyyy" class="input-group input-medium date date-picker">
						<input type="text"  id="staffContractEndDate"  readonly="" class="form-control" name="staffHistoryVo.endDate" value='<s:property value="staffHistoryVo.endDateStr"/>' onchange="verifyStaffDate();">
						<span class="input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button>
						  </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
		</div>
	  </div>
	  <div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">Other Experience :</label>
				<div class="col-md-5">
					<sj:textarea rows="3" cols="20" name="staffHistoryVo.otherExperience" maxCharsData="1000"
					 cssClass="form-control word_count"></sj:textarea>
					<span class="help-block">
							<div class="counter"></div> </span>
				</div>
			</div>
		</div>
	</div>
			
	<div class="form-actions">
	<div class="col-md-offset-3 col-md-6">
		<img src="../img/bg/bigWaiting.gif" alt="Loading..." title="Loading..." id="indicator"
			style="display: none; background-repeat: no-repeat; position: relative; z-index: 1000; bottom: 200px; margin-left: 240px;" />
		<sj:submit cssClass="submitBt btn blue" value="Submit" targets="staffEditContentDiv" validate="true" formIds="editStaffHistory"   />
			<s:url id="doCancelForm" action="ajaxDoEditStaffHistory" includeParams="all" escapeAmp="false" namespace="/staff">
				<s:param name="tempId1" value="0" />
				<s:param name="tempId" value="%{tempId}" />
			</s:url>
			<sj:a href="%{doCancelForm}" targets="staffEditContentDiv"  cssClass="btn default">Cancel
			</sj:a>
							
		
	</div>
	</div></div>
</s:form>
<div class="spaceDiv"></div>
<s:if test="%{newUser.staffHistory != null && !newUser.staffHistory.isEmpty()}">
<div class="spaceDiv"></div>
	<jsp:include page="/WEB-INF/pages/staff/manageStaff/ajaxViewStaffHistoryList.jsp" />
</s:if>
<script type="text/javascript">
	$(document).ready(function(){
			changePageTitle("Staff History");
			FormComponents.init();
			$('.alphabet').alpha( {
			allow : "a-z,A-Z.?/~!@#)() "
		});
		$('.numericDot').numeric( {
			allow : "."
		});
	});
	function verifyStaffDate() {
		var startDate = $('#contractStartDate').val();
		var endDate = $('#staffContractEndDate').val();
		if (isNonEmpty(endDate) && isNonEmpty(startDate)) {
			startDate = new Date(startDate);
			endDate = new Date(endDate);
			if (endDate < startDate) {
				$('#staffContractEndDate').val('');
				alert("End date must be greater than the start date.");
			}
		}
	}
</script>