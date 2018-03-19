<%@ include file="/common/taglibs.jsp"%>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/jquery-multi-select/css/multi-select.css"/>
<div data-width="760" class="modal fade modal-overflow in" 
	style="display: block; width: 760px; margin-left: -379px; margin-top: 100px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Edit Rack Details
		</h4>
	</div>
	<div class="modal-body">
	<div class="form-body">
	<s:form id="editRockForm" action="ajaxEditUpdateRacks" method="post" cssClass="form-horizontal" theme="simple" namespace="/library">
			<s:hidden name="anyTitle" value="" id="selectedSubject"></s:hidden>
			<s:hidden name="rackDetails.id" value="%{rackDetails.id}"></s:hidden>
			<s:hidden name="blockId"></s:hidden>
				<div class="form-body">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Rack Name :
					</label>
					<div class="col-md-9">
						<sj:textfield name="rackDetails.rackName" id="rackName"
							cssClass="required form-control input-medium" maxlength="40" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						Rack Capacity :
					</label>
					<div class="col-md-9 rackCapacityDiv" id="<s:property value="rackDetails.rackCapacity"/>">
					<sj:textfield  id="noBooks"  name="numberOfDays"
								onkeypress="return onlyNumbers(event);" onchange="javascript:checkAvailableBooks()"
								cssClass="numeric form-control input-medium required" maxlength="8" ></sj:textfield>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						Added Books Count :
					</label>
					<div class="col-md-9 ">
						<sj:textfield name="rackDetails.booksCount" id="availabilityBooks"
							cssClass="form-control input-medium" maxlength="20" readonly="true"></sj:textfield>
					</div>
				</div>
				<div id="FreeToAddBooks">
				<div class="form-group">
					<label class="control-label col-md-3">
						Free on this rack to add :
					</label>
					<div class="col-md-9">
					<sj:textfield id="freeRackBooks" name="rackDetails.rackCapacity" cssClass="numeric form-control input-medium"
								maxlength="8" readonly="true"></sj:textfield>
					</div>
				</div>
				</div>
				<div id="noOfBooks">
					<div class="row">
						<div class="col-md-12">
							<s:if test="%{objectList.size > 0}">
								<label>
									Select books :
								</label>
							</s:if>
						  <div>
						  <select id='optgroup' multiple='multiple' name="optgroup[]"  class="searchable">
							<s:if	test="%{objectList != null || !objectList.isEmpty && objectList!= null || !objectList.isEmpty}">
							  <s:iterator value="objectList" status="stat">
							     <optgroup label='<s:property value="objectList[#stat.count-1][1]"/>-<s:property value="objectList[#stat.count-1][2]"/>'>
							     <s:if	test="%{tempList2 != null || !tempList2.isEmpty && tempList2!= null || !tempList2.isEmpty}">
									 <s:iterator value="tempList2" status="stat1">
									 <s:if test="%{objectList[#stat.count-1][0] == tempList2[#stat1.count-1][0]}">
										 <option value='<s:property value="tempList2[#stat1.count-1][0]"/>::<s:property value="tempList2[#stat1.count-1][5]"/>::<s:property value="tempList2[#stat1.count-1][4]"/>'><s:property value="tempList2[#stat1.count-1][1]"/>:<s:property value="tempList2[#stat1.count-1][2]"/> (<span id='totalBooks<s:property value="tempList2[#stat1.count-1][0]"/>'><s:property value="tempList2[#stat1.count-1][4]"/></span>)</option>
									 </s:if>
									</s:iterator>
							     </s:if>
							     </optgroup>
							  </s:iterator>
							</s:if>
						 </select>
						 <%-- <select id='optgroup' multiple='multiple' name="optgroup[]"  class="searchable" style="position: absolute; left: -9999px;">
							<s:if	test="%{objectList != null || !objectList.isEmpty()}">
							  <s:iterator value="objectList" status="stat">sivaa::
							     <optgroup label='<s:property value="objectList[#stat.count-1][1]"/>-<s:property value="objectList[#stat.count-1][2]"/>'>
							     <s:if	test="%{tempList2 != null || !tempList2.isEmpty()}">
									 <s:iterator value="tempList2" status="stat1">
									 <s:if test="%{objectList[#stat.count-1][0] == tempList2[#stat1.count-1][0]}">
									    <option value='<s:property value="tempList2[#stat1.count-1][0]"/>::<s:property value="tempList2[#stat1.count-1][5]"/>::<s:property value="tempList2[#stat1.count-1][4]"/>'><s:property value="tempList2[#stat1.count-1][1]"/>:<s:property value="tempList2[#stat1.count-1][2]"/> (<span id='totalBooks<s:property value="tempList2[#stat1.count-1][0]"/>'><s:property value="tempList2[#stat1.count-1][4]"/></span>)</option>
									 </s:if>
									</s:iterator>
							     </s:if>	
							     </optgroup>
							  </s:iterator>
							</s:if>
						 </select> --%>
					 </div>
					</div>
				</div>
			</div>
			</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-6">
						<sj:submit   targets="viewAllRacks" value="Submit" validate="true"
							formIds="editRockForm" cssClass="submitBt btn blue"
							onBeforeTopics="addRackFormValidation2" />
							<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
					</div>
				</div>
		</s:form>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$.destroyTopic('addRackFormValidation2');
	$('input#noBooks').val(<s:property value="rackDetails.rackCapacity"/>);
	var available=$('input#noBooks').val()- <s:property value='rackDetails.booksCount'/>
 	$('input#freeRackBooks').val(available);
 FormComponents.init();
 $('#optgroup').multiSelect({ selectableOptgroup: true });
});
function checkAvailableBooks(){
	var available=0;
	if($("input#noBooks").val() < Number($('div.rackCapacityDiv').attr('id'))){
		alert("You can not reduce the rack capacity.");
		$("input#noBooks").val($('div.rackCapacityDiv').attr('id'))
		 available=$("input#noBooks").val()- <s:property value='rackDetails.booksCount'/>
 		$('input#freeRackBooks').val(available);
		return false;
	}else{
		 available=$("input#noBooks").val()- <s:property value='rackDetails.booksCount'/>
 		$('input#freeRackBooks').val(available);
	}
	
}
$.subscribe('addRackFormValidation2', function(event, data) {
		var strVal=[];
		var calculateCount=0;
		var str ='';
		var pos=0;
		if($('input#noBooks').val()==0 || $('input#freeRackBooks').val()== 0){
		  alert('There is no space to add the books.');
		  event.originalEvent.options.submit=false;	
		}
	    $('li.ms-elem-selection:visible').each(function(){
          str = $(this).children('span').html();
          pos = str.indexOf("(") + 1;
          strVal.push($(this).attr('id').replace("-selection",""));
          calculateCount=calculateCount+Number(str.slice(pos, -1));
       });
		if($('input#freeRackBooks').val() < calculateCount){
		 alert('Selected books are exceeded than rack capacity. Will be save up to this rack capacity.');
		}
		$('input#selectedSubject').val(strVal);
		$('button.close').click();
	}); 
</script>