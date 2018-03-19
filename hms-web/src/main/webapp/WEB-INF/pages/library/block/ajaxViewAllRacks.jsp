<%@ include file="/common/taglibs.jsp"%>
<div align="right">
	<s:url id="addRack" action="ajaxDoBlockDetails" includeParams="all"
		escapeAmp="false" namespace="/library">
		<s:param name="blockId" value="{block.id}" />
	</s:url>
	<sj:a href="%{addRack}" cssClass="btn default" targets="mainContentDiv">
		<i class="fa fa-mail-reply"></i> Back To View
	</sj:a>

	<s:if test='%{#session.previousYear == "N"}'>
		<a data-toggle="modal" href="#addRackDetailsDiv" class="btn green"
			onclick="javascript:addPopupRackDetails(<s:property value="selectedId2" />);"><i
			class="fa fa-plus"></i>Add Rack </a>
	</s:if>
</div>
<div class="spaceDiv"></div>
<jsp:include page="/common/messages.jsp"></jsp:include>
<h4>
	Block Name :
	<s:property value="block.blockName" />
</h4>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>Rack Name</th>
				<th>Rack Capacity</th>
				<th>Added Books</th>
				<th>Edit</th>
				<th>Book Details</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="tempList">
				<tr>
					<td><s:property value="rackName" /></td>
					<td><s:property value="rackCapacity" /></td>
					<td id="availableBooksCount"><s:property value="booksCount" />
					</td>
					<td><s:if test='%{#session.previousYear == "N"}'>
							<a data-toggle="modal" href="#editRackDetails"
								class="btn btn-xs purple"
								onclick="javascript:editPopupManageRackDetails(<s:property value="id" />,<s:property value="selectedId2" />,<s:property value="rackCapacity" />);"><i
								class="fa fa-edit"></i>Edit </a>
						</s:if></td>
					<td><s:if test='%{booksCount > 0}'>
							<a data-toggle="modal" href="#viewRackDetails"
								class="btn btn-xs yellow"
								onclick="javascript:PopupManageRackDetails(<s:property value="id" />);">View
								Books </a>
						</s:if> <s:else>
							    	No Books.
							  </s:else></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">Currently there are no racks to
		this block.</div>
</s:else>
<div id="viewRackDetails"></div>
<div id="editRackDetails"></div>
<div id="addRackDetailsDiv"></div>
<script type="text/javascript">
	function PopupManageRackDetails(blockId) {
		var url = jQuery.url.getChatURL("/library/ajaxDoViewRack.do");
		$('#viewRackDetails').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "tempId=" + blockId,
				success : function(html) {
					$("#viewRackDetails").html(html);
				}
			});
		} 
		
	function editPopupManageRackDetails(rackId,blockId,rackCapacity) {
		var url = jQuery.url.getChatURL("/library/ajaxUpdateRack.do");
		$('#editRackDetails').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "tempId="+ rackId+"&blockId=" + blockId+"&tempId1="+rackCapacity,
				success : function(html) {
					$("#editRackDetails").html(html);
				}
			});
		} 
		function addPopupRackDetails(blockId) {
		var url = jQuery.url.getChatURL("/library/ajaxDoAddNewRack.do");
		$('#addRackDetailsDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "blockId=" + blockId,
				success : function(html) {
					$("#addRackDetailsDiv").html(html);
				}
			});
		} 
</script>