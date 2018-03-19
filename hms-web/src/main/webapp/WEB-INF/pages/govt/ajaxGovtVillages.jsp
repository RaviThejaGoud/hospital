<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span> Select Village:
				</label>
				<div class="col-md-5">
					<s:select id="villageId" list="tempList2"
						cssClass="required form-control input-medium as-input"
						required="true" listKey="id" listValue="villageName" headerKey=""
						headerValue="- Select -" name="section" theme="css_xhtml" />
				</div>
			</div>
		</div>
	</div>
</s:if>









