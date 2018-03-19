<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function onChangeGetRacksByBlock(selectBox) {
		var id = selectBox;
		var subjectId='<s:property value="tempId"/>';
		var url = jQuery.url.getChatURL("/library/ajaxGetRacksByBlock.do");
		if(id.length == 0){
			alert("!Oops subjectName.");
		}
		else{
			$("#resultsDivRacks").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "blockId=" + id +"&subjectId="+subjectId;
			$.ajax( {
				url :  url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#resultsDivRacks").html(html);
					document.getElementById('resultsDivRacks').style.display = "block";
				}
			});
		}
	}
	function onChangeGetOtherRacksByBlock(selectBox) {
		var id = selectBox;
		var subjectName='<s:property value="selectedId"/>';
		var url = jQuery.url.getChatURL("/library/ajaxGetOtherRacksByBlock.do");
		if(id.length == 0){
			alert("!Oops subjectName.");
		}
		else{
			$("#resultsDivOtherRacks").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "blockId=" + id +"&plSubjectName="+subjectName;
			$.ajax( {
				url :  url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#resultsDivOtherRacks").html(html);
					document.getElementById('resultsDivOtherRacks').style.display = "block";
				}
			});
		}
	}
</script>
 <style type="text/css">
	.subjectDiv {
		background: #EAAD2A;
		float: left; 
		padding: 6px;
		margin-bottom: 6px;
		width: 220px;
	}
</style>
<div class="subjectDiv">
	<s:if test='%{objectList.size >0}'>
			<b>Block Name:</b>
			<s:select list="objectList" listKey="id" listValue="blockName" id="blockId" tabindex="8"  
					name="blockId" headerKey="M" headerValue="- Select Block Name -" theme="simple" onchange="javascript:onChangeGetRacksByBlock(this.value);" cssClass="form-control input-medium;">
			</s:select>
	</s:if>
	<s:elseif test='%{tempList.size >0}'>
			<b>Block Name:</b>
			<s:select list="tempList" listKey="id" listValue="blockName" id="otherBlockId"  tabindex="8" 
					name="blockId" headerKey="M" headerValue="- Select Block Name -" theme="simple" onchange="javascript:onChangeGetOtherRacksByBlock(this.value);" cssClass="form-control input-medium;">
			</s:select>
			
	</s:elseif>
	<s:else>
		Please create block for this subject.
	</s:else>
</div>
<div class="subjectDiv" id="resultsDivRacks" class="col-md-6" style="display:none;">
</div>
<div class="subjectDiv" id="resultsDivOtherRacks" class="col-md-6" style="display:none;">
</div>
