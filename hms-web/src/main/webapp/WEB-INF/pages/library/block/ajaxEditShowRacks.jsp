<%@ include file="/common/taglibs.jsp"%>
<s:if test='%{rackDetailList.size >0}'>
	<b>Rack Name:</b>
	<s:select list="rackDetailList" listKey="id" listValue="rackName"
		id="rackId" name="bookAndBlockDetails.rackDetails.id" headerKey="R"
		headerValue="-Select Rack Name-" theme="simple"
		cssClass="form-control input-medium"/>
</s:if>
<s:else>
currently there r are no racks this blocks;
</s:else>

