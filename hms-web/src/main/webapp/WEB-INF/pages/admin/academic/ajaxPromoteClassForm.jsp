<%@ include file="/common/taglibs.jsp"%>
<div class="grid_11">
	<s:form id="createPromoteClass" action="ajaxCreatePromoteClass" method="post" theme="css_xhtml" namespace="/admin">
		<fieldset>
			<div class="grid_11">
				<div class="grid_3">
					<label>
						Current Class
					</label>
				</div>
				<div class="grid_5">
					<div class="tableactions" style="padding-bottom: 0px;">
						<s:select list="classList" listKey="id" listValue="className" cssClass="required textfield small" cssStyle="width:100%;" 
							required="true" name="classId" headerKey="" headerValue="- Select -" requiredposition="first" >
						</s:select>
					</div>
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3">
					<label>
						Promote Class
					</label>
				</div>
				<div class="grid_5">
					<div class="tableactions" style="padding-bottom: 0px;">
						<s:select list="objectList" listKey="id" listValue="className" cssClass="required textfield small" cssStyle="width:100%;" 
							required="true" name="promoteClassId" headerKey="" headerValue="- Select -" requiredposition="first" >
						</s:select>
					</div>
				</div>
			</div>
			<div class="grid_11" style="float: right;">
				<div class="grid_6">
					&nbsp;
				</div>
				<sj:submit   targets="classPromoteContent" value="Submit"
					cssClass="submit small" formIds="createPromoteClass"
					indicator="indicator" validate="true" />
				<s:url id="cancelPromoteClassUrl" action="ajaxClancelClassPromote"
					includeParams="all" escapeAmp="false" namespace="/admin">
				</s:url>
				<sj:a href="%{cancelPromoteClassUrl}" cssClass="cancelButton"
					indicator="indicator" targets="classPromoteContent" button="false"
					buttonIcon="ui-icon-plus">Cancel</sj:a>
			</div>
		</fieldset>
	</s:form>
</div>

<script type="text/javascript">
changePageTitle("Create New Promote Class");
</script>
