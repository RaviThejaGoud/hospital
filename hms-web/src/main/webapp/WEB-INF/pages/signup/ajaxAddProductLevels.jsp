<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<s:iterator value="tempList">
		<input
			onclick="javascript:addinglevelPrice(<s:property value='id'/>,<s:property value='productId'/>);"
			id="singleProduct<s:property value='id'/>"
			value="<s:property value='id'/>" type="radio" name="levelName"
			title="all">
		<s:property value="levelName" />
	</s:iterator>
</s:if>

