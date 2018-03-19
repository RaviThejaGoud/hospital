<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
				<s:set name="inSubTypeId" value=""></s:set>
				<table class="table table-striped table-bordered table-hover table-full-width" id="sample_4">
				</table>
				<s:iterator value="objectList">
					
					<s:if test="%{subTypeId != #inSubTypeId}">
					<div class="col-md-12">&nbsp;</div>
					  	<label class="control-label col-md-2">	<s:property value="subTypeName"/> :</label>
					</s:if>
					<div class="col-md-2">
						<s:checkbox name="selectBoxIdList" checked="checked" id="%{description}"></s:checkbox><s:property value="name"/>
					</div>	
					<s:set name="inSubTypeId" value="%{subTypeId}"></s:set>
				</s:iterator>
			</s:if>
<script type="text/javascript">
$(document).ready(function(){
$("input:checkbox, input:radio").uniform();
/* $("input[name='selectBoxIdList']").each(function(){
	$(this).attr("checked",true); 
	$(this).parent("span").addClass("checked");
 }); */
});
</script>