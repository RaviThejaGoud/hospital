<%@ include file="/common/taglibs.jsp"%>
	<s:form action="ajaxCreateParticularType" theme="simple" id="updateGradetypes" method="post" cssClass="form-horizontal" namespace="/admin">
		
		<s:hidden name="particularType.id" value="%{particularType.id}"></s:hidden>
		<h4 class="bold pageTitle">
			<s:if test="%{particularType.id != 0}">
				Update Particular Type
				<s:set name="particularType.id" value="%{particularType.id}"></s:set>
			</s:if>
			<s:else>
				Add Particular Type
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
						<sj:textfield name="particularType.particularTypeName" id="particularTypeName"
							cssClass="required form-control input-medium" maxlength="40"></sj:textfield>
					</div>
				</div>
			</div>
			
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-4 col-md-12">
					<sj:submit   cssClass="submitBt btn blue" value="Submit"
						 validate="true"
						indicator="indicator" targets="perticularDetailsDivId" />
						<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
				</div>
			</div>
		</div>
		
	</div>
</s:form>
	
<script type="text/javascript">
changePageTitle("Add Peticular Type");
</script>