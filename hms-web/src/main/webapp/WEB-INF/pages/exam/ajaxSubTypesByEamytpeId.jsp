<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{subTypesList != null && !subTypesList.isEmpty()}"> 
	<div class="row">
		<s:iterator value="subTypesList" status="stat">
			<div class="col-md-4">
				<div class="form-group">
					<label class="control-label col-md-3">  <s:property value="subTypesList[#stat.count-1][1]" /> :
					</label>
					<div class="col-md-6">
							<!-- here id is a subtyeId_ExamtypeId -->
						<sj:textfield name="subtypeAndExamId"
							cssClass="required form-control input-small as-input numeric subtypeAndExamVal" value="0" id="%{subTypesList[#stat.count-1][0]}_%{subTypesList[#stat.count-1][1]}_%{subTypesList[#stat.count-1][2]}"  
							maxlength="3"></sj:textfield>
					</div>
				</div>
			</div>

		</s:iterator>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">You have not created Subtypes,
		Creating subtype is simple process and system would guide you.</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	$('.numeric').numeric();
});
$(".subtypeAndExamVal").change(function(event) {
    var val= $(this).val();
    if(val==''){
    	alert("Please enter value.");
    	$(this).val('0');
    	return false;
    }
});
</script>