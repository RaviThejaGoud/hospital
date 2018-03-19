<%@ include file="/common/taglibs.jsp"%>
<s:if test='%{tempString == "admissionClassFee"}'>
</s:if>
<!--<s:if test="%{tempId1 > 4}">
	<div class="row" style="float:right">
		<div class="col-md-6">
			<div class="form-group">
				<s:if test="%{tempList.size != 0}">
					<s:url id="urlAdminGetSchoolFeeLink" action="ajaxAdminGetSchoolFee"
						escapeAmp="false" includeParams="all">
						<s:param name="anyId" value="%{anyId}"></s:param>
					</s:url>
					<sj:a id="adminGetSchoolFeeLink" href="%{urlAdminGetSchoolFeeLink}"
						targets="allClasses" indicator="indicator" button="false"
						cssClass="btn blue linkleft" >Next Terms </sj:a>
				</s:if>
				<s:else>
					<s:url id="urlAdminGetSchoolFeeLink1"
						action="ajaxAdminGetSchoolFee" escapeAmp="false"
						includeParams="all">
						<s:param name="anyId" value="%{}"></s:param>
					</s:url>
					<sj:a id="adminGetSchoolFeeLink"
						href="%{urlAdminGetSchoolFeeLink1}" targets="allClasses"
						indicator="indicator" button="false" cssClass="btn default">Back</sj:a>
				</s:else>
			</div>
		</div>
	</div>
</s:if> -->
<s:if test="%{schoolTermsList.size != 0}">
	<span class="label label-danger">NOTE :</span>
				1. You can create a fee structure by clicking on assign button <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
				2. You cannot edit a assigned fee if any student has made a payment.
				
				<div class="form-body"></div>
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
				<s:if test='%{academicYear.transportFeeByBoardingPoint && schoolFeeSetting.settingName == "Transport Fee"}'>
				   Route Name
				</s:if>
				<s:else>
					Class Name
				</s:else>
				<s:if test="schoolTermsList!= null && !schoolTermsList.isEmpty">
					<s:iterator value="schoolTermsList">
						<s:set name="termId" value="%{id}"></s:set>
						<th>
							<s:property value="termName" />
						</th>
					</s:iterator>
				</s:if>
				<s:else>
					<s:iterator value="feeTypeList">
						<th>
							<s:property value="feeType" />
						</th>
					</s:iterator>
				</s:else>
				</th>
				<th>
					Terms Total Amount
				</th>
				<th>
					Total Students Amount
				</th>
				
				
				<s:if test='%{academicYear.transportFeeByBoardingPoint && schoolFeeSetting.settingName == "Transport Fee"}'>
				</s:if>
				<s:else>
					<th>
						Paid-Amount
					</th>
					<th>
						Create / Edit
					</th>
				</s:else>
			</tr>
		</thead>
		<tbody>
			<s:set name="transportFeeByBoardingPoint"
				value="academicYear.transportFeeByBoardingPoint"></s:set>
			<s:iterator value="classList">
				<tr>
					<td>
						<s:set name="classId" value="%{id}"></s:set>
						<input type="hidden" name="classId"
							value="<s:property value='id'/>">
						<label>
							<s:property value="className" />
						</label>
					
					<s:iterator value="objectList">
						<s:if test="%{id==#classId}">
							<td>
								<c:set value="${description}" var="priceAmt" />
								<fmt:formatNumber value="${description}" type="currency"
									pattern="##,##,##0.00" var="balance" />
								<c:out value="${balance}" />
							</td>
						</s:if>
					</s:iterator>
					</td>
					<td>
						<c:set var="termTotalAmt" value='${sortingOrder}' />
						<fmt:formatNumber value="${termTotalAmt}" type="currency"
							pattern="##,##,#00.00" var="termTotal" />
						<c:out value="${termTotal}" />
					</td>
					<td>
						<c:set var="paymentAmt" value='${description}' />
						<fmt:formatNumber value="${paymentAmt}" type="currency"
							pattern="##,##,#00.00" var="paymentBalance" />
						<c:out value="${paymentBalance}" />
					</td>
					
					<s:if test='%{#transportFeeByBoardingPoint && schoolFeeSetting.settingName == "Transport Fee"}'>
					</s:if>
					<s:else>
					<td>
						<c:set var="paymentAmt" value='${noOfSections}' />
						<fmt:formatNumber value="${paymentAmt}" type="currency"
							pattern="##,##,#00.00" var="paymentBalance" />
						<c:out value="${paymentBalance}" />
					</td>
					<td style="width: 60px;">
						<!--<s:if test="%{schoolTermsList.size == 4}">
							<s:if test='%{#session.previousYear == "N"}'>
								<s:url id="addClassFee" action="ajaxDoAddClassFee"
									includeParams="all" escapeAmp="false" namespace="/schoolfee">
									<s:param name="anyTitle" value="%{selectedId}"></s:param>
									<s:param name="classId" value="%{id}" />
									<s:param name="anyId" value="%{schoolCategory.id}"></s:param>
									<s:param name="tempId" value="%{schoolFeeSetting.id}"></s:param>
								</s:url>
								<sj:a href="%{addClassFee}" targets="stepClassFee"
									indicator="indicator" cssClass="btn btn-xs purple"
									title="Edit">
									<i class="fa fa-edit"></i>Edit
									</sj:a>
							</s:if>
						</s:if>
						<s:else> </s:else>-->
						<s:if test='%{#session.previousYear == "N"}'>
							<s:url id="addClassFee" action="ajaxDoAddClassFee"
								includeParams="all" escapeAmp="false" namespace="/schoolfee">
								<s:param name="anyTitle" value="%{selectedId}"></s:param>
								<s:param name="classId" value="%{id}" />
								<s:param name="anyId" value="%{schoolCategory.id}"></s:param>
								<s:param name="tempId" value="%{schoolFeeSetting.id}"></s:param>
							</s:url>
							<sj:a href="%{addClassFee}" targets="stepClassFee"
								indicator="indicator" cssClass="btn btn-xs purple"
								title="Edit">
								<i class="fa fa-edit"></i>Edit
								</sj:a>
						</s:if>
						</td>
					</s:else>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no fee term created under fee settings, please add fee term in Fee Terms tab. 
	</div>
</s:else>
<script type="text/javascript">
TableAdvanced.init();
UIExtendedModals.init();
$(document).ready(function() {
	changePageTitle("Class Fee");
	$('.blockHeader h2').html('Manage Academics');
});
</script>
