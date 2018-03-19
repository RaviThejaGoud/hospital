<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="form-body">
	<s:form action="studentPaymentCardDetails"
		id="addStudentsPaymentFee" method="post" theme="simple" enctype="multipart/form-data" cssClass="form-horizontal" name="myform">
		<input type="hidden" name="studentNumber"
			value='<s:property value="student.id" />' />
		<s:hidden id="termIdsIds" name="anyId" />
		<s:set name="schoolTermsId" value=""></s:set>
		<s:set name="feeTypeId" value=""></s:set>
		<p>
			<span class="label label-danger"> NOTE : </span> &nbsp;Here current fee
			terms is highlighted in red color.
		</p>
		<s:if test="%{viewStudentPersonAccountDetailsList.size>1}">
			<div class="form-group">
				<label class="control-label col-md-2">
					Student Name :
				</label>
				<div class="col-md-3">
					<s:select id="studId" list="viewStudentPersonAccountDetailsList"
						listKey="studentId" listValue="idAndName" name="studentId"
						cssClass="form-control"
						onchange="javascript:getStudentPaymentByClassIdAndCustId(this.value);"
						theme="simple" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">
					Class Name :
				</label>
				<div class="col-md-5">
					<p class="form-control-static">
						<s:property value="anyTitle" />
					</p>
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="form-group">
				<label class="control-label col-md-2">
					Student Name :
				</label>
				<div class="col-md-5">
					<s:iterator value="viewStudentPersonAccountDetailsList">
						<input type="hidden" id="studentId" name="selectedId"
							value="studentId" />
						<span class="studentClassId" style="display: none;"><s:property
								value="studentId" /> </span>
							<p class="form-control-static">	
								<s:property value="idAndName" />
							</p>	
					</s:iterator>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">
					Class Name :
				</label>
				<div class="col-md-5">
					<p class="form-control-static">
						<s:property value="student.classSection.classAndSection" />
					</p>
				</div>
			</div>
		</s:else>
		<div id="studentPaymentDetails">
			<jsp:include page="/WEB-INF/pages/student/parent/payment/ajaxStudentSchloolFeePayment.jsp" />
	    </div>
	</s:form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Student Invoice");
});
function getStudentPaymentByClassIdAndCustId(studentId) {
	 var url ;
  	     if(isNonEmpty(studentId)){
			 url = jQuery.url.getChatURL("/student/ajaxParentMakePayment.do?studentId="+studentId);
		 }
   		$("#studentTotalTermsComparison").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
    	$.ajax( {
    		url : url,
		cache : false,
		success : function(response) {
			$('#studentPaymentDetails').html(response);
			$('#studentPaymentDetails').show();
		}
	 });
	}
</script>
