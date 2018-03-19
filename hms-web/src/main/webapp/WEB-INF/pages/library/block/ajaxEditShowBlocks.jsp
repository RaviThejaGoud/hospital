<%@ include file="/common/taglibs.jsp"%>
 <style type="text/css">
	.editSubjectDiv {
		background: #EAAD2A;
		float: left; 
		padding: 8px;
		margin-bottom: 8px;
		width: 250px;
	}
</style>
<script type="text/javascript">
function onChangeGetEditRacksByBlock(selectBox) {
		var id = selectBox;
		var url;
		var subjectId=$('select#subject>option:selected').val();  //'<s:property value="tempId"/>';
		var otherSubjectName= $('select#otherSubject>option:selected').val();
		if(subjectId>0){
			 url = jQuery.url.getChatURL("/library/ajaxGetEditRacksByBlock.do?subjectId="+subjectId);	
		}else{
			 url = jQuery.url.getChatURL("/library/ajaxGetEditOtherRacksByBlockName.do?plSubjectName="+otherSubjectName);	
		}
		if(id.length == 0){
			alert("!Oops subjectName.");
		}
		else{
			$("#editResultsDivRacks").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "blockId="+ id;
			$.ajax( {
				url :  url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#editResultsDivRacks").html(html);
					document.getElementById('editOtherRacksDIV').style.display = "none";
					document.getElementById('editResultsDivRacks').style.display = "block";
				}
			});
		}
	}
	
	function onChangeGetEditOtherRacksByBlock(selectBox) {
		var id = selectBox.value;
		var otherSubjectName= $('select#otherSubject selected').text();   //'<s:property value="selectedId3"/>';
		var url = jQuery.url.getChatURL("/library/ajaxGetEditOtherRacksByBlockName.do");
		if(id.length == 0){
			alert("!Oops subjectName.");
		}
		else{
			$("#editResultsDivOtherRacks").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var pars = "id=" + id +"&otherSubjectName="+otherSubjectName;
			$.ajax( {
				url :  url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#editResultsDivOtherRacks").html(html);
					document.getElementById('editResultsDivOtherRacks').style.display = "block";
				}
			});
		}
	}
</script>
<div class="editSubjectDiv">
            <!-- cccc <s:property value="blockList.size"/>
			cvs <s:property value="tempList.size"/>
			hhhi <s:property value="bookAndBlockDetails.rackDetails.type"/>-->
			
	<s:if test='%{blockList.size >0}'>
			<b>Block Name :</b>
			<s:select list="blockList" listKey="id" listValue="blockName" id="blockId" 
					name="bookAndBlockDetails.block.id" headerKey="M" headerValue="- Select Block Name -" theme="simple" onchange="javascript:onChangeGetEditRacksByBlock(this.value);" cssClass="required form-control input-medium">
			</s:select>
	</s:if>
	<!--<s:elseif test='%{tempList.size >0}'>
			<b>Block Name:</b>
			<s:select list="tempList" listKey="id" listValue="blockName" id="otherSubjectsId" tabindex="4"
					name="bookAndBlockDetails.block.id" headerKey="M" headerValue="- Select Block Name -" theme="simple" onchange="javascript:onChangeGetEditOtherRacksByBlock(this);" cssStyle="width:200px;">
			</s:select>
	</s:elseif>
   -->
   <s:else>
		Please create block for this subject.
	</s:else>
</div>
<div class="editSubjectDiv"  id="editOtherRacksDIV" style="display:none;">
	 <s:if test='%{bookAndBlockDetails.rackDetails.type=="S"}'>
		 <b>Rack Name :</b>
		<s:select list="rackDetailList" listKey="id" listValue="rackName" id="rackId" 
				name="bookAndBlockDetails.rackDetails.id" headerKey="R" headerValue="-Select Rack Name-" theme="simple" cssClass="required form-control input-medium">
		</s:select>
	</s:if>
	<s:if test='%{bookAndBlockDetails.rackDetails.type=="OS"}'>
	<b>Rack Name :</b>
		<s:select list="tempList" listKey="id" listValue="rackName" id="rackId" 
				name="bookAndBlockDetails.rackDetails.id" headerKey="R" headerValue="-Select Rack Name-" theme="simple" cssClass="required form-control input-medium">
		</s:select>
	</s:if>
</div>
<div class="editSubjectDiv" id="editResultsDivRacks" class="col-md-6" style="display:none;">
</div>
<div class="editSubjectDiv" id="editResultsDivOtherRacks" class="col-md-6" style="display:none;">
</div>
 