<%@ include file="/common/taglibs.jsp"%>
<div class="grid_14">
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
	<div class="grid_14 commonFormTabs">
		<s:if test="%{tempId1 > 4}">
			<s:if test="%{tempList.size != 0}">
				<s:url id="urlAdminGetHostelFeeLink" action="ajaxAdminGetHostelFee"
					escapeAmp="false" includeParams="all" namespace="/hostel">
					<s:param name="anyId" value="%{anyId}"></s:param>
				</s:url>
				<sj:a id="adminGetHostelFeeLink" href="%{urlAdminGetHostelFeeLink}"
					targets="searchStudentsCategoryList" indicator="indicator"
					button="false" cssClass="linkRight">Next Terms</sj:a>
			</s:if>
			<s:else>
				<s:url id="urlAdminGetHostelFeeLink" action="ajaxAdminGetHostelFee"
					escapeAmp="false" includeParams="all" namespace="/hostel">
					<s:param name="anyId" value=""></s:param>
				</s:url>
				<sj:a id="adminGetHostelFeeLink" href="%{urlAdminGetHostelFeeLink}"
					targets="searchStudentsCategoryList" indicator="indicator"
					button="false" cssClass="linkRight">Back</sj:a>
			</s:else>
		</s:if>
		<br />
		<s:if test="%{anyTitle == 'Student'}">
			<s:if
				test="%{classList!= null && !classList.isEmpty && hostelTermsList!= null && !hostelTermsList.isEmpty && objectList!= null && !objectList.isEmpty}">
				<div class="grid_14 noteFont">
					<div class="grid_1">
						<span class="noteMassage"> Note :</span>
					</div>
					<div class="grid_13">
						The below list contain all classes. you can create/edit fee
						structure by click edit icon right side.
					</div>
				</div>
				<div class="grid_14 th">
				<div class="grid_12">
					<div class="grid_2 classNameDiv sortHeader divArrow">
						Class Name
					</div>
					<s:iterator value="hostelTermsList">
						<s:set name="termId" value="%{id}"></s:set>
						<div class="grid_2 linkRight">
							<s:property value="hostelTermName" />
						</div>
					</s:iterator>
					<div class="grid_2 totalDiv sortHeader divArrow" style="width: 110px">
						Total Amount
					</div>
					<div class="grid_2 paidDiv sortHeader divArrow" style="width: 110px">
						Paid-Amount
					</div>
				</div>
				<div class="grid_2">
					<div class="grid_2">
						Create/Edit
					</div>
				
				</div>
				</div>
				<div id="classListDiv">
				 <s:iterator value="classList">
				  <div className="<s:property value='className' />"   description="<s:property value='description' />"  noOfSections="<s:property value='noOfSections' />" class="item">
					<div class="grid_14 row">
					<div class="grid_12">
						<div class="grid_2">
							<s:set name="classId" value="%{id}"></s:set>
							<input type="hidden" name="classId"
								value="<s:property value='id'/>">
							<label>
								<s:property value="className" />
							</label>
						</div>
						<s:iterator value="objectList">
							<s:if test="%{id==#classId}">
								<div class="grid_2">
									<c:set value="${description}" var="priceAmt" />
									<fmt:formatNumber value="${description}" type="currency"
										pattern="##,##,##0.00" var="balance" />
									<c:out value="${balance}" />
								</div>

							</s:if>
						</s:iterator>
						<div class="grid_2">
							<c:set var="paymentAmt" value='${description}' />
							<fmt:formatNumber value="${paymentAmt}" type="currency"
								pattern="##,##,#00.00" var="paymentBalance" />
							<c:out value="${paymentBalance}" />
						</div>
						<div class="grid_2">
							<c:set var="paymentAmt" value='${noOfSections}' />
							<fmt:formatNumber value="${paymentAmt}" type="currency"
								pattern="##,##,#00.00" var="paymentBalance" />
							<c:out value="${paymentBalance}" />
						</div>
						</div>
						<div class="grid_2">
						<div class="grid_1 linkRight">
							<s:if test="%{hostelTermsList.size != 4}">
								<s:url id="addStaffFee" action="ajaxDoAddClassHostelFee"
									includeParams="all" escapeAmp="false" namespace="/hostel">
									<s:param name="anyTitle" value="%{anyId}"></s:param>
									<s:param name="classId" value="%{id}" />
									<s:param name="anyId" value="%{hostelCategory.id}"></s:param>
								</s:url>
								<sj:a href="%{addStaffFee}" targets="stepHostelClassFee"
									indicator="indicator" title="Edit" cssClass="normalEdit">
								</sj:a>
							</s:if>
							<s:else>
								<s:url id="addStaffFee" action="ajaxDoAddClassHostelFee"
									includeParams="all" escapeAmp="false" namespace="/hostel">
									<s:param name="classId" value="%{id}" />
									<s:param name="anyId" value="%{hostelCategory.id}"></s:param>
								</s:url>
								<sj:a href="%{addStaffFee}" targets="stepHostelClassFee"
									indicator="indicator" title="Edit" cssClass="normalEdit">
								</sj:a>
							</s:else>
						</div>
					</div>
					</div>
					</div>
				</s:iterator>
				</div>
				<div class="thb grid_14">
				</div>
			</s:if>
		</s:if>
		<s:elseif test="%{anyTitle == 'Staff'}">
			<s:if
				test="%{hostelTermsList!= null && !hostelTermsList.isEmpty && objectList!= null && !objectList.isEmpty}">
				<div class="grid_14 noteFont">
					<div class="grid_1">
						<span class="noteMassage"> Note :</span>
					</div>
					<div class="grid_12">
						The below list contain all classes. you can create/edit fee
						structure by click edit icon right side.
					</div>
				</div>
				<div class="grid_14 th">
					<div class="grid_12">
						<div class="grid_2">
							Type
						</div>
						<s:iterator value="hostelTermsList">
							<s:set name="termId" value="%{id}"></s:set>
							<div class="grid_2 linkRight">
								<s:property value="hostelTermName" />
							</div>
						</s:iterator>
						<div class="grid_2">
							Total Amount
						</div>
						<div class="grid_2">
							Paid-Amount
						</div>
					</div>
					<div class="grid_2">
						<div class="grid_2">
							Create/Edit
						</div>
					</div>
				</div>
				<div class="grid_14 row">
					<div class="grid_12">
						<div class="grid_2">
							Staff
						</div>
						<s:iterator value="objectList">
							<div class="grid_2">
								<c:set value="${description}" var="priceAmt" />
								<fmt:formatNumber value="${description}" type="currency"
									pattern="##,##,##0.00" var="balance" />
								<c:out value="${balance}" />
							</div>
						</s:iterator>
						<div class="grid_2">
							<c:set var="paymentAmt" value='${totalAmount}' />
							<fmt:formatNumber value="${paymentAmt}" type="currency"
								pattern="##,##,#00.00" var="paymentBalance" />
							<c:out value="${paymentBalance}" />
							&nbsp;
						</div>
						<div class="grid_2">
							<c:set var="paymentAmt" value='${numberOfDays}' />
							<fmt:formatNumber value="${paymentAmt}" type="currency"
								pattern="##,##,#00.00" var="paymentBalance" />
							<c:out value="${paymentBalance}" />
							&nbsp;
						</div>
					</div>
					<div class="grid_2">

						<div class="grid_1 linkRight">
							<s:if test="%{hostelTermsList.size != 4}">
								<s:url id="addClassFee" action="ajaxDoAddStaffHostelFee"
									includeParams="all" escapeAmp="false" namespace="/hostel">
									<s:param name="anyTitle" value="%{anyId}"></s:param>
									<s:param name="anyId" value="%{hostelCategory.id}"></s:param>
								</s:url>
								<sj:a href="%{addClassFee}" targets="stepHostelClassFee"
									indicator="indicator" title="Edit" cssClass="normalEdit">
								</sj:a>
							</s:if>
							<s:else>
								<s:url id="addClassFee" action="ajaxDoAddStaffHostelFee"
									includeParams="all" escapeAmp="false" namespace="/hostel">
									<s:param name="anyId" value="%{hostelCategory.id}"></s:param>
								</s:url>
								<sj:a href="%{addClassFee}" targets="stepHostelClassFee"
									indicator="indicator" title="Edit" cssClass="normalEdit">
								</sj:a>
							</s:else>
						</div>
					</div>
				</div>
				<div class="thb grid_14">
				</div>
			</s:if>
		</s:elseif>
		<s:else>
		  Currently there are no terms.
		</s:else>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Class Fee");
	$('.blockHeader h2').html('Manage Academics');
});
$.subscribe('doDisplayFee', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});

var timeInverse=1;
$('div.classNameDiv').click(function () {
    $('div#classListDiv>div.item').sortElements(function (a, b) {return ($(a).attr('className').toLowerCase() > $(b).attr('className').toLowerCase() ? 1 : -1) * timeInverse; });
    updateDirectionArrows($(this), timeInverse);
    timeInverse = timeInverse * -1;
    return false;
});
var total=1;
$('div.totalDiv').click(function () {
    $('div#classListDiv>div.item').sortElements(function (a, b) {return (parseFloat($(a).attr('description')) > parseFloat($(b).attr('description')) ? 1 : -1) * total; });
    updateDirectionArrows($(this), total);
    total = total * -1;
    return false;
});
var paidAmount=1;
$('div.paidDiv').click(function () {
    $('div#classListDiv>div.item').sortElements(function (a, b) {return (parseFloat($(a).attr('noOfSections')) > parseFloat($(b).attr('noOfSections')) ? 1 : -1) * paidAmount; });
    updateDirectionArrows($(this), paidAmount);
    //$("#classListDiv").pagination();
    paidAmount = paidAmount * -1;
    return false;
});
</script>
 
<style type="text/css">
.grid_1,.grid_2,.grid_3,.grid_4,.grid_5,.grid_6,.grid_7,.grid_8,.grid_9,.grid_10,.grid_11,.grid_12,.grid_13,.grid_14,.grid_15,.grid_16,grid_17,grid_18
	{
	margin-right: 0px;
}
</style>