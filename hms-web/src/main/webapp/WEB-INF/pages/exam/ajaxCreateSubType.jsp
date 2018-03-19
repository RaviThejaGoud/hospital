<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{subType.id != 0}">
	<div data-width="760" class="modal fade modal-overflow in"
		id="responsive4"
		style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;"
		aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">
				Update Exam Subtype
			</h4>
		</div>
		<div class="modal-body">
</s:if>
<s:form action="ajaxCreateOrUpdateSubType" theme="simple"
	cssClass="form-horizontal form-body" id="createOrUpdateSubType"
	method="post" namespace="/exam">
	<s:hidden name="subType.id" value="%{subType.id}"></s:hidden>
	<s:hidden name="tempString" value="subTypes"></s:hidden>
	<s:if test="%{subType == null}">
		<h4 class="pageTitle bold">
			Add exam subtype
		</h4>
		<hr />
	</s:if>
	<div class="form-group">
		<label class="control-label col-md-3">
			<span class="required">*</span> SubType Name :
		</label>
		<div class="col-md-5">
			<sj:textfield name="subType.subTypeName" id="subTypeName"
				cssClass="required form-control input-medium" maxlength="40" />
			<span class="help-block">(Subtype name like Theory, Practical,
				Assignment e.t.c)</span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-3">
			Is Scheduled Exam :
		</label>
		<p class="form-control-static">
			<s:checkbox name="subType.schedule" />
		</p>
		<span class="help-block">(If you want to conduct exam for this
			type please check this check box.)</span>
	</div>
	<div class="form-actions fluid">
		<div class="col-md-6">
			<div class="col-md-offset-6 col-md-9">
				<sj:submit cssClass="submitBt btn blue" value="Submit"
					formIds="createOrUpdateSubType" resetForm="true" validate="true"
					indicator="indicator" targets="examContentInfo"
					onBeforeTopics="subTypesValidation" />
				<s:if test="%{subType.id != 0}">
					<button type="button" data-dismiss="modal" class="btn default">
						Cancel
					</button>
				</s:if>
				<s:else>
					<s:url id="doCancelExamSubTypes"
						action="ajaxViewExamTypesAndGrades" includeParams="all"
						escapeAmp="false" namespace="/exam">
						<s:param name="tempString">subTypes</s:param>
					</s:url>
					<sj:a href="%{doCancelExamSubTypes}" cssClass="btn default"
						targets="examContentInfo">Cancel</sj:a>
				</s:else>
			</div>
		</div>
	</div>
</s:form>
<s:if test="%{subType.id != 0}">
	</div>
	</div>
</s:if>
<script type="text/javascript">
	$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();
	$.destroyTopic('subjFormValidation');
	$("#subTypeName")
		.autoCheck(
				"${pageContext.request.contextPath}/exam/ajaxCheckExamSubTypeAvailableOrNot.do",
				{
					minChars : 2,
					min : "no"
				});
	});
	$.subscribe('subTypesValidation',function(event, data) {
	 $('button.close').click();
	});
	
	$.subscribe('subTypesValidation',function(event, data) {
	 $('p.word-taken').each(function() {
	  if($(this).html()=='Already taken!!!'){
	     event.originalEvent.options.submit=false;
	   }
	 });
	 $('button.close').click();
	});
	</script>
