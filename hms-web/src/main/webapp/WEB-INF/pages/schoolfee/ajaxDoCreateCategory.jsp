<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{schoolCategory.id != 0}">
	<div data-width="760" class="modal fade modal-overflow in" id="responsive3" style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;" aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">Update category</h4>
		</div>
		<div class="modal-body">
	</s:if>
<s:form action="ajaxAddSchoolCategory" id="addSchoolCategory"
	method="post" theme="simple" cssClass="form-horizontal">
	<s:hidden name="schoolCategory.id" value="%{schoolCategory.id}"></s:hidden>
	<s:hidden name="tempString"></s:hidden>
	<div class="form-body">
	<s:if test="%{schoolCategory == null}">
		<h4 class="bold pageTitle">
			Add category
		</h4>
	<hr/>
	</s:if>
		<span class="label label-danger"> NOTE : </span>&nbsp;&nbsp; You can define the category to create the different fee structures to a class and students."
		<div class="spaceDiv"></div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required"> * </span>Category Name :
					</label>
					<div class="col-md-5">
						<sj:textfield name="schoolCategory.categoryName" 
							id="categoryName" cssClass="required form-control input-medium"
							 maxlength="40" />
					</div>
				</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-6 col-md-9">
					<sj:submit   cssClass="submitBt btn blue" value="Submit"  formIds="addSchoolCategory"
						onBeforeTopics="subjFormValidation" validate="true"
						indicator="indicator" targets="feeSettingsContent"  />
						<s:if test="%{schoolCategory.id != 0}">
							<button type="button" data-dismiss="modal" class="btn default">
								Cancel
							</button>
						</s:if>
						<s:else>
					<s:url id="doCancelSchoolTerms"
						action="ajaxViewSelectedFeeSettings" includeParams="all"
						escapeAmp="false">
					</s:url>
					<sj:a href="%{doCancelSchoolTerms}" cssClass="btn default"
						targets="feeSettingsContent">Cancel</sj:a>
						</s:else>
				</div>
			</div>
		</div>
	</div>
</s:form>
<s:if test="%{schoolCategory.id != 0}">
		</div>
		</div>
		</s:if>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Create Category");
	$('.blockHeader h2').html('Manage Academics');
	var schoolCategoryId = $('input[name="schoolCategory.id"]').val();
	
	$("#categoryName").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckFeeCategoryType.do?schoolCategoryId="+schoolCategoryId,{
			minChars : 3,
			min : "no"
		}).focus();
});
$.subscribe('subjFormValidation',function(event, data) {
	 $('p.word-taken').each(function() {
	  if($(this).html()=='Already taken!!!'){
	     event.originalEvent.options.submit=false;
	   }
	 });
	 $('button.close').click();
	});
/*$.subscribe('subjFormValidation',function(event, data) {
	 $('button.close').click();
	});*/
</script>