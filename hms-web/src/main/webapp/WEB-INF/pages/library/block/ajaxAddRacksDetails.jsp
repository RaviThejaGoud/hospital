<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugins/jquery-multi-select/css/multi-select.css"/>
<div data-width="760" class="modal fade modal-overflow in" 
	style="display: block; width: 760px; margin-left: -379px; margin-top: 100px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Add Rack Details
		</h4>
	</div>
	<div class="modal-body">
	<div class="form-body">
	<s:form id="addRockForm" action="ajaxAddRacks" method="post"
		theme="simple" cssClass="form-horizontal" namespace="/library">
		<s:hidden name="anyTitle"  id="selectedSubject" value=""></s:hidden>
		<s:hidden name="bookAutorName" value="" id="selectedAuthor"></s:hidden>
		<s:hidden name="blockId"></s:hidden>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Rack Name :
					</label>
					<div class="col-md-5">
						<s:textfield name="rackDetails.rackName" id="rackName"
							cssClass="required form-control input-medium" maxlength="60"></s:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Rack Capacity :
					</label>
					<div class="col-md-5">
						<sj:textfield name="rackDetails.rackCapacity" id="noBooks"
							onkeypress="return onlyNumbers(event);"
							cssClass="required numeric form-control input-small"
							maxlength="8" />
					</div>
				</div>
			</div>
		</div>
		<div>
			<label>
				Select books :
			</label>
		</div>
		<div>
		<select id='optgroupAdd' multiple='multiple' name="optgroup[]"  class="searchable" style="position: absolute; left: -9999px;">
			<s:if	test="%{objectList != null || !objectList.isEmpty()}">
			  <s:iterator value="objectList" status="stat">
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
		 </select>
		 </div>
		 <div class="spaceDiv"></div>
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-8">
				<sj:submit   targets="viewAllRacks" value="Submit"
					cssClass="submitBt btn blue" onBeforeTopics="addRackFormValidation1"
					validate="true" indicator="indicator" />
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
 FormComponents.init();
 $.destroyTopic('addRackFormValidation1');
 $('#optgroupAdd').multiSelect({ selectableOptgroup: true });
});
$('input#noBooks').change(function(){
	if(Number($(this).val()==0)){
	  alert("Books count should be more than zero.");
	  $(this).val('1');
      $(this).focus();
      return false;
	} 
});

$.subscribe('addRackFormValidation1', function(event, data) {
	var strVal=[];
	var calculateCount=0;
	var str ='';
	var pos=0;
	var boolVal=true;
	if($('input#noBooks').val()==0){
	  alert('Rack Capacity should be more than Zero.');
	   event.originalEvent.options.submit=false;	
	   boolVal=false;
	}
	    $('li.ms-elem-selection:visible').each(function(){
          str = $(this).children('span').html();
          pos = str.indexOf("(") + 1;
          strVal.push($(this).attr('id').replace("-selection",""));
          calculateCount=calculateCount+Number(str.slice(pos, -1));
       });
	if($('input#noBooks').val() < calculateCount){
	  alert('Selected books are exceeded than rack capacity. Will be save up to this rack capacity.');
	}
	$('input#selectedSubject').val(strVal);
	if(boolVal)
	$('button.close').click();
});

function onlyNumbers(evt) {
	var e = evt; // for trans-browser compatibility	
	var charCode = e.which || e.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57))
		return false;

	return true;
}

function onChangeGetOTherSubjects(otherSubject) {
	if (isNonEmpty(otherSubject)) {
		$("#subjectDiv").hide();
	} else
		$("#subjectDiv").show();
}

function onChangeGetOtherBooks(selectBox) {
	if (selectBox == " ") {
		$("#otherSubject").show();
	} else
		$("#otherSubject").hide();
}
</script>