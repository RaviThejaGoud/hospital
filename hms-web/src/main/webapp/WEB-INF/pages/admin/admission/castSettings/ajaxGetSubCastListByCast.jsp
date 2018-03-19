<%@ include file="/common/taglibs.jsp"%>
	<div class="form-group">
		<label class="control-label col-md-4">
			Caste Name :
		</label>
		<div class="col-md-5">
			<s:if test="%{student != null}">
				<s:select id="subCastName" list="objectList" listKey="id"
					tabindex="33" listValue="subCastName" headerKey="" cssClass="form-control"
					headerValue="- Select -" name="student.account.person.subCastId"
					theme="css_xhtml"/>
			</s:if>
			<s:else>
				<s:select id="subCastName" list="quizList" listKey="id" cssClass="form-control"
					listValue="subCastName" headerKey="" headerValue="- Select -"
					name="viewStaffPersonAccountDetails.subCastId" theme="simple" />
			</s:else>
		</div>
	</div>
	<div id="resultsDiv2"> </div>

