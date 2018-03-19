<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{(objectList != null && !objectList.isEmpty())}">
<s:checkboxlist name="tempList" list="objectList"
					listKey="id" listValue="description" theme="ems"></s:checkboxlist>
</s:if>

<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform()
});
</script>