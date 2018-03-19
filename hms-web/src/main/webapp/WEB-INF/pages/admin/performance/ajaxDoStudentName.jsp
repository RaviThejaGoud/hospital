<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
		<div class="form-group">
			<label class="control-label col-md-2">
			&nbsp; &nbsp;&nbsp;<span class="required"> * </span>   Select Student :
			</label>
			<div class="col-md-3">
				<s:select list="studentsList" listKey="studId" listValue="fullName"
					cssClass="required form-control input-medium" 
					theme="simple" name="studentName" headerKey="0"
					headerValue="- Select Student Name-" id="studentName"
					onchange="javascript:getClassExamTypes();">
				</s:select>
			</div>
		</div>	
</s:if>
		