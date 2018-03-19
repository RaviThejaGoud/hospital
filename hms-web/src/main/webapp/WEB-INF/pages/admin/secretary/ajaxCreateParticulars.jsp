<%@ include file="/common/taglibs.jsp"%>
	
	<s:form action="ajaxCreateParticulars" theme="simple" id="createParticulars" method="post" cssClass="form-horizontal" namespace="/admin">
		<s:hidden name="particular.id" value="%{particular.id}"></s:hidden>
		<%-- <s:hidden name="particularType.id" value="%{particularType.id}"></s:hidden> --%>
		<h4 class="bold pageTitle">
			<s:if test="%{particular.id != 0}">
				<s:set name="particularId" value="%{particular.id}"></s:set>
				Update Particulars
				<hr/>
			</s:if>
			<s:else>
				Add Particulars
				<hr/>
			</s:else>
		</h4>
	<div class="form-body">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label">
						<span class="required">*</span>Particular Type Name :
					</label>
					<div class="col-md-5">
						<s:select list="objectList" listKey="id" listValue="particularTypeName" cssClass="required form-control" headerKey="" headerValue="- Select -" name="particularType.id"></s:select>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label">
						<span class="required">*</span>Particular Name :
					</label>
					<div class="col-md-5">
						<sj:textfield name="particular.particularName" id="particularName"
							cssClass="required form-control input-medium" maxlength="100"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-4 col-md-12">
					<sj:submit   cssClass="submitBt btn blue" value="Submit"
						 validate="true"
						indicator="indicator" targets="mainContentDiv" />
						
						<s:url id="doCancel" action="ajaxParticularDetailsHome"
							includeParams="all" namespace="/admin"></s:url>
						<sj:a href="%{doCancel}" cssClass="btn default"
							indicator="indicator" targets="mainContentDiv" button="false">Cancel</sj:a>
					
					
						
				</div>
			</div>
		</div>
		
	</div>
</s:form>
<script type="text/javascript">
changePageTitle("Add Peticular Type");
</script>