<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>Eazy School| Admissions</title>
</head>
<body />
	<div class="wrapper container_18">
		<div class="wrapper">
			<!-- wrapper begins -->
			<div class="grid_18 block grid_18MarginLeft">
				<jsp:include page="/WEB-INF/pages/admin/admission/admissionLeftNav.jsp"></jsp:include>
				<div id="admissionSettings">
					<div class="grid_14 omega">
						<div id="admissionContentDetails">
						<s:if test='%{description != null && description != empty && description == "viewAdmittedStudents"}'>
							<jsp:include page="/WEB-INF/pages/admin/admission/admittedStudentHome.jsp"></jsp:include>
						</s:if>
						<s:elseif test='%{description != null && description != empty && description == "admissionPaymentCalcel"}'>
							<jsp:include page="/WEB-INF/pages/admin/admission/approvedApplicationDetails.jsp"></jsp:include>	
						</s:elseif>
						<s:else>
							<jsp:include page="/WEB-INF/pages/admin/admission/admissionDetails.jsp"></jsp:include>			
						</s:else>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	 <form method="post" id="printReport" action="javaScript:printPreview('<s:property value="anyId" />','<s:property value="todayDate"/>','<s:property value="tempId" />','payandprint','<s:property value="tempId2" />','<s:property value="alertSendType"/>')" style="display: none;">
        </form>
<script Language="Javascript1.2" type="text/javascript">
$(document).ready(function() {
	$('#admissionProcess').addClass('current');
	alert("Is this calling every Time..");
});
</script>