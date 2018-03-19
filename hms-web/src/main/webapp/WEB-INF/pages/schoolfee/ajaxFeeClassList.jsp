<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<jsp:include page="/common/messages.jsp" />
		<div class="portlet-body">
			<s:if test="%{classList != null && !classList.isEmpty() && schoolTermsList != null && !schoolTermsList.isEmpty()}">
				<div class="tab-content">
					<div class="form-group">
								<div class="col-md-12">
									<label class="control-label">
										<span class="required">*</span>Available Terms :
									</label>
									<div class="checkbox-list">
										<label class="checkbox-inline">
											<input type="checkbox"
												onClick="checkAllFeeTerms()" class="checkbox allTerms" />
											All Terms
										</label>
									</div>
									<s:checkboxlist name="schoolTermchkBoxSelectedIds"
										list="schoolTermsList" label="Term Name" listKey="id"
										listValue="termName" theme="ems"></s:checkboxlist>
								</div>
					</div>
					
					<div class="form-group" id="classWiseStuDetailsPdfDetails">
						<label class="conLable col-md-1 control-label">
							Select :
						</label>
						<div class="radio-list">
							<label class="radio-inline">
								<input type="radio" name="SelectType" value="classSectionWise"
									onclick="getSelectedValue(this.value);" checked="checked">
								Single Class Report
							</label>
							<label class="radio-inline">
								<input type="radio" name="SelectType" value="roleWise"
									onclick="getSelectedValue(this.value);">
								All Classes Report
							</label>
						</div>
					</div>
					<div id="classListDiv">
					<s:form action="ajaxGetClassPayentDefaulters" theme="simple"
							namespace="/schoolfee" id="classAndTerms" cssClass="form-horizontal" method="post">
							<input type="hidden" name="tempString" id="tempString" />
						<div class="form-body">
								<div class="form-group">
									<div class="col-md-6">
									<label class="control-label col-md-3">
										Select Class :
									</label>
									<div class="col-md-6">
										<s:if test="%{classList!= null}">
											<s:select list="classList" listKey="id" listValue="className"
												cssClass="select2_category form-control required"
												id="classId" theme="simple" name="classId">
											</s:select>
										</s:if>
									</div>
								</div>
								</div>
								 
							<div class="form-actions fluid">
								<div class="col-md-offset-2 col-md-9">
									<sj:submit cssClass="btn blue" value="Submit" onBeforeTopics="validateTermNames"
										targets="paymentDefaultersList" validate="true" />
								</div>
							</div>
						</div>
						<div id="paymentDefaultersList"></div>
					</s:form>						
					</div>
					<div id="staffRoles">
						<s:form action="ajaxGetClassPayentDefaulters" theme="simple"
							namespace="/schoolfee" 
							id="classAndTodate" cssClass="form-horizontal" method="post" ><!-- ajaxCommonFeeCollectionAndDues -->
							<input type="hidden" name="classId" id="classNameId" />
							<input type="hidden" name="tempString" id="tempString" />
							<div class="form-body">
							<div class="form-group">
								<div class="col-md-12">
									<div class="checkbox-list">
										<label class="checkbox-inline">
											<input type="checkbox"  
												onClick="checkAllClasses()" class="checkbox allClasses">
											All Classes
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									<div class="checkbox-list">
										<s:checkboxlist name="chkBoxSelectedIds" list="classList"
											listKey="id" listValue="className" theme="ems"
											cssClass="small" onclick="getSelectedTermsTotalPaidAmount()" />
									</div>
								</div>
							</div>
							<div class="form-actions fluid">
								<div class="col-md-offset-2 col-md-9">
									<sj:submit cssClass="btn blue" value="Submit" onBeforeTopics="validateTermNames"
										targets="paymentDefaultersList1" validate="true" />
								</div>
							</div>
						<div id="paymentDefaultersList1"></div>
						</div>
						</s:form>
						</div>
					</div>
			</s:if>
			<s:else>
				<div class="spaceDiv"></div>
				<div class="alert alert-info">
					<s:if test="%{classList.size == 0 && schoolTermsList.size == 0}">
						   There are no classes and defaulter terms
					</s:if>
					<s:elseif test="%{classList.size == 0}">
						There are no classes found.		
					</s:elseif>
					<s:elseif test="%{schoolTermsList.size==0}">
							There are no any Fee defaulter terms.
					</s:elseif>
				</div>
			</s:else>
		</div>
	</div>
</div>
<script type="text/javascript">
	changePageTitle('Payment Defaulters');
	$(document).ready(function() {
	$.destroyTopic("validateTermNames");
		 $("input:checkbox, input:radio").uniform();
		 var selected = $('input[name=SelectType]:radio:checked').val();	
			getSelectedValue(selected);	
			var classId = $('#classId').val();
			//alert(classId);
			if (isNonEmpty(classId)) {
				//onClassDetailsChange(classId);
			}
			$("input[name=chkBoxSelectedIds]").click(function() {
				if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
				   $(".allClasses").parent('span').removeClass("checked");
					$(".allClasses").attr("checked", false);
				} else {
					//alert('dsf');
				    $(".allClasses").parent('span').addClass("checked");
					$(".allClasses").attr("checked", true);
				}
			});
			$('.numericDot').numeric( {allow : "."});
			
			
	});
	 
	$.subscribe('validateTermNames', function(event, data) {
		var selectedType = $("input[name='SelectType']:checked").val();
		 if(selectedType=="classSectionWise"){
			 if($("input[name='schoolTermchkBoxSelectedIds']:checked").length == 0) {
				     alert("Please select at least one term.");
				     $("div#paymentDefaultersList").hide();
					 $("div#paymentDefaultersList1").hide();
					 event.originalEvent.options.submit=false;
				}else{
					 $("div#paymentDefaultersList").show();
					 $("div#paymentDefaultersList1").show();
				}
		 }else if(selectedType=="roleWise"){
			 if($("input[name='schoolTermchkBoxSelectedIds']:checked").length == 0 || $("input[name='chkBoxSelectedIds']:checked").length == 0) {
				 if($("input[name='schoolTermchkBoxSelectedIds']:checked").length == 0)
					   alert("Please select at least one term.");
				 if($("input[name='chkBoxSelectedIds']:checked").length == 0)
					 alert("Please select at least one class.");
				 $("div#paymentDefaultersList").hide();
				 $("div#paymentDefaultersList1").hide();
				 event.originalEvent.options.submit=false;
			 }
			 else{
				 $("div#paymentDefaultersList").show();
				 $("div#paymentDefaultersList1").show();
			}
		 }
   });
	function getSelectedTermsTotalPaidAmount() {
		if ($('input[name=chkBoxSelectedIds]:checked').length > 0) {
			var selectedRoleIds = [];
			//selectedRoleIds.push('(0');
			$('input[name=chkBoxSelectedIds]:checked').each(function() {
					selectedRoleIds.push(($(this).val()));
				});
			//selectedRoleIds.push('0)');
		$("#classNameId").val(selectedRoleIds);
		}else
			$("#classNameId").val();
			//alert('dfdf');
	}
	
	function getSelectedValue(value) {
		if (isNonEmpty(value)) {	
		 var classId=$('select#classId').val();
			 if (value == "classSectionWise" ) {
			 $("input[name='chkBoxSelectedIds']").removeAttr("checked");
			 	$(".allClasses").removeAttr("checked");
				$("#classListDiv").show();
				$("#staffRoles").hide();
				 $("div#paymentDefaultersList").hide();
				
			} else if (value == "roleWise") {
				checkAllClasses();
				$("div#paymentDefaultersList1").hide();
				$("#staffRoles").show();			
				$('#classListDiv').hide();
			}
		}
		return value;
	}	
	$('input.generateReport').click(function(){
		if($(this).hasClass('PDF')){
	 		$('.anyId').val('PDF');
	 	}else{
	 		$('.anyId').val('Excel');
	 	}
	});	
	 	
	 	
	 function generateFeeClassIds() {
	  if($("input[name='schoolTermchkBoxSelectedIds']:checked").length == 0) {
		     alert("Please select at least one term.");
			 return false;
		}
	  var selected = $('input[name=SelectType]:radio:checked').val();
		 if(selected=="roleWise"){
		 if ($("input[name=chkBoxSelectedIds]:checked").length >0) {
		 	var roleNameIds = $("input[name=chkBoxSelectedIds]:checked");
				var selectedRoleIds = [];
				if (roleNameIds.length > 0) {
				selectedRoleIds.push('(0');
					for ( var i = 0; i < roleNameIds.length; i++) {
						if (isNonEmpty(roleNameIds[i].value))
							selectedRoleIds.push(roleNameIds[i].value.trim());
					}
					selectedRoleIds.push('0)');
				}
				$("#classNameId").val(selectedRoleIds);
			}else {
			 alert("Please select at least one class.");
			 return false;
		  }	
		 }
	}
	 	
	function checkAllClasses() {
		if ($(".allClasses").is(':checked')){
		    $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $(".allClasses").parent('span').removeClass('checked');
		 $(".allClasses").removeAttr("checked");
		 $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
		getSelectedTermsTotalPaidAmount();
	}
	function checkAllFeeTerms() {
	if ($(".allTerms").is(':checked')) {
		$("[name='schoolTermchkBoxSelectedIds']").each(function() {
			$(this).parent('span').addClass('checked');
			$(this).attr("checked", "true");
		});
	} else {
		$("[name='schoolTermchkBoxSelectedIds']").each(function() {
			$(this).parent('span').removeClass('checked');
			$(this).removeAttr("checked");
		});
	}
	prepareSchoolTermIds();
}
$("input[name=schoolTermchkBoxSelectedIds]").click(function() {
	if ($("input[name=schoolTermchkBoxSelectedIds]:unchecked").length > 0) {
		$(".allTerms").parent('span').removeClass("checked");
		$(".allTerms").attr("checked", false);
	} else {
		$(".allTerms").parent('span').addClass("checked");
		$(".allTerms").attr("checked", true);
	}
	prepareSchoolTermIds();
});
function prepareSchoolTermIds(){
	var schoolTermsIdsLength = $("input[name='schoolTermchkBoxSelectedIds']").length;
	if (schoolTermsIdsLength > 0) {
		var schoolTermsIds = [];
		schoolTermsIds = '(';
		$("input[name=schoolTermchkBoxSelectedIds]:checked").each(function() {
			schoolTermsIds += $(this).val() + ', ';
		});
		schoolTermsIds += '0)';
		$("input#tempString").val(schoolTermsIds);
	}
}
</script>
