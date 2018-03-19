<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{studyClassList != null && !studyClassList.isEmpty() || tempList1 != null && !tempList1.isEmpty() || tempList2 != null && !tempList2.isEmpty()}">
 <div class="form-group">
	<div class="col-md-11">
		<div class="checkbox-list">
			<label class="checkbox-inline"> <input type="checkbox"
				name="" value="" onClick="checkAll()" class="checkbox examTypes">
				All Study Classes
			</label>
		</div>
	</div>
</div> 
<div class="form-group">
	<div class="col-md-11">
		<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
			<s:checkboxlist name="chkBoxSelectedIds" list="tempList1"
				disabled="true" listKey="id" listValue="classAndSection" theme="ems" />
		</s:if>
		<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
			<s:checkboxlist name="chkBoxSelectedIds" list="tempList2"
				listKey="id" listValue="classAndSection" theme="ems" />
		</s:if>
		  <s:checkboxlist name="chkBoxSelectedIds" list="studyClassList"
			listKey="id" listValue="classAndSection" theme="ems" />
	</div>
</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no classes available. please create classes and
		then create create exam types.
		<s:url id="urlViewClassSectionInfo" action="ajaxDoManageClassSections"
			namespace="/admin"></s:url>
		<sj:a id="viewClassSecInfo" href="%{urlViewClassSectionInfo}"
			targets="mainContentDiv" data-toggle="tab">
			<font color="red"><b>Click here</b></font>
		</sj:a>
		to add class &amp; sections.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();
	if($("input[name=chkBoxSelectedIds]:checked").length==$("input[name=chkBoxSelectedIds]").length){
	 	 $(".examTypes").parent('span').addClass("checked");
			$(".examTypes").attr("checked", true);
	 }
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
			$(".examTypes").parent('span').removeClass("checked");
			$(".examTypes").attr("checked", false);
		} else {
			$(".examTypes").parent('span').addClass("checked");
			$(".examTypes").attr("checked", true);
		}
	});
	/* var exId='<s:property value="examTypes.id "/>';
	if(exId == 0 ){
		$("[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	} */
});
</script>

