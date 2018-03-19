<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="form-body">
		<s:set name="schoolTermsId" value=""></s:set>
		<s:if test="%{classFeeList != null && !classFeeList.isEmpty()}">
		<div class="form-group">
			<label class="control-label col-md-2">
					<b>School Fee Terms :</b>
			</label>
			<div class="col-md-9">
				<s:iterator value="classFeeList">
					<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
					<ul class="tooltipDiv" style="float: left;margin-left:-30px;margin-top:8px;">
								<li>
									<a style="cursor: pointer;" id='<s:property value="schoolTermId" />'><input
										type="checkbox" name="chkBoxFeeSelectedIds" id="<s:property value='schoolTermId'/>"
										class="admisionPayStatus<s:property value='schoolTermId'/>"
										value="<s:property value="feeId"/>" /> <s:property
										value="termName" />&nbsp;[<b id="schoolTotalDiv<s:property value='schoolTermId'/>"></b>]</a>
									<ul class="tooltipSubDiv" style="width: 300px;">
										<div class="popover bottom " style="display: none;">
											<div class="arrow"></div>
											<h3 class="popover-title">
												School Fee Particulars
											</h3>
											<div class="popover-content">
												<s:if
													test="%{schoolFeeList != null && !schoolFeeList.isEmpty()}">
													<s:iterator value="schoolFeeList">
														<s:if test="%{schoolTermId == #schoolTermsId}">
															<li>
																<b><s:property value="feeType" /></b>
																:
																<s:property value="feeAmount" />
															</li>
															<span id="<s:property value="feeId"/>" class='classFeeIds'
																style="display: none;"><s:property value="feeAmount" />
															</span>
														</s:if>
													</s:iterator>
												</s:if>
											</div>
										</div>
									</ul>
								</li>
							</ul>
							<div id="chkBoxFeeSelectedIds<s:property value="feeId"/>"
								style="display: none;" class="<s:property value="feeId"/>">
							<s:if test='%{paidAmount == 0 || paidAmount == null}'>
								<s:property value="feeAmount" />
							</s:if>
							<s:else>
								<s:property value="studentParticularTotal" />
							</s:else>
						</div>
				</s:iterator>
			</div>
		</div>
	</s:if>
</div>
	<div>
		<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<div class="form-group">
			<label class="control-label col-md-2">
				<b>Hostel Fee Terms :</b>
			</label>
			<div class="col-md-9">
				<s:iterator value="objectList">
					<s:set name="hostelTermsId" value="%{hostelTermId}"></s:set>
					<ul class="tooltipDiv" style="float: left;margin-left:-30px;margin-top:8px;">
						<li>
							<a style="cursor: pointer;"
								id='<s:property value="hostelTermId" />'><input
									type="checkbox" name="chkBoxHostelFeeSelectedIds"
									id="<s:property value='hostelTermId'/>"
									class="hostelPayStatus<s:property value='hostelTermId'/>"
									value="<s:property value="hostelFeeId"/>" /> <s:property
									value="hostelTermName" />&nbsp;[<b
								id="hostelTotalDiv<s:property value='hostelTermId'/>"></b>]</a>
							<ul class="tooltipSubDiv" style="width: 300px;">
								<div class="popover bottom " style="display: none;">
									<div class="arrow"></div>
									<h3 class="popover-title">
										Hostl Fee Particulars
									</h3>
									<div class="popover-content">
										<s:if
											test="%{hostelFeeTypeList != null && !hostelFeeTypeList.isEmpty()}">
											<s:iterator value="hostelFeeTypeList">
												<s:if test="%{hostelTermId == #hostelTermsId}">
													<li>
														<b class="grid_2 left"><s:property
																value="hostelFeeType" />
														</b> :
														<s:property value="feeAmount" />
													</li>
													<span id="<s:property value="hostelTermId"/>"
														class='classHostelIds' style="display: none;"><s:property
															value="feeAmount" /> </span>
												</s:if>
											</s:iterator>
										</s:if>
										<s:else>
											<li>
												u don't have hostel fee particulars.
											</li>
										</s:else>
									</div>
								</div>
							</ul>
						</li>
					</ul>
					<div
						id="chkBoxHostelFeeSelectedIds<s:property value="hostelFeeId"/>"
						style="display: none;" class="<s:property value="hostelFeeId"/>">
						<s:if test='%{paidAmount == 0 || paidAmount == null}'>
							<s:property value="feeAmount" />
						</s:if>
						<s:else>
							<s:property value="studentParticularTotal" />
						</s:else>
					</div>
				</s:iterator>
			</div>
		</div>
	</s:if>
</div>
	<div>
		<s:if test="%{classFeeCountList != null && !classFeeCountList.isEmpty()}">
		<div class="form-group">
			<label class="control-label col-md-2">
				<b>Transport Fee Terms :</b>
			</label>
			<div class="col-md-9">
				<s:iterator value="classFeeCountList">
					<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
					<ul class="tooltipDiv" style="float: left;margin-left:-30px;margin-top:8px;">
						<li>
							<a style="cursor: pointer;"
								id='<s:property value="schoolTermId" />'><input
									type="checkbox" name="chkBoxTransportFeeSelectedIds"
									id="<s:property value='schoolTermId'/>"
									class="transportPayStatus<s:property value='schoolTermId'/>"
									value="<s:property value="feeId"/>" /> <s:property
									value="termName" />&nbsp;[<b
								id="transTotalDiv<s:property value='schoolTermId'/>"></b>]</a>
							<ul class="tooltipSubDiv" style="width: 300px;">
								<div class="popover bottom " style="display: none;">
									<div class="arrow"></div>
									<h3 class="popover-title">
										Transport Fee Particulars
									</h3>
									<div class="popover-content">
										<s:if
											test="%{transportSchoolFeeList != null && !transportSchoolFeeList.isEmpty()}">
											<s:iterator value="transportSchoolFeeList">
												<s:if test="%{schoolTermId == #schoolTermsId}">
													<li>
														<b class="grid_2 left"><s:property value="feeType" />
														</b> :
														<s:property value="feeAmount" />
													</li>
													<span id="<s:property value="schoolTermId"/>"
														class='classFeeIds' style="display: none;"><s:property
															value="feeAmount" /> </span>
												</s:if>
											</s:iterator>
										</s:if>
										<s:else>
											<li>
												u don't have transport fee particulars.
											</li>
										</s:else>
									</div>
								</div>
							</ul>
						</li>
					</ul>
					<div id="chkBoxTransportFeeSelectedIds<s:property value="feeId"/>"
						style="display: none;" class="<s:property value="feeId"/>">
						<s:if test='%{paidAmount == 0 || paidAmount == null}'>
							<s:property value="feeAmount" />
						</s:if>
						<s:else>
							<s:property value="studentParticularTotal" />
						</s:else>
					</div>

				</s:iterator>
			</div>
		</div>
	</s:if>
</div>
<s:if test="%{classFeeList != null && !classFeeList.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-2">
			School Fee :
		</label>
		<div class="col-md-3">
			<sj:textfield name="paymentAmount" id="totalPayAmountDiv"
				cssClass="numeric required form-control" readonly="true"></sj:textfield>
		</div>
	</div>
</s:if>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-2">
			Hostel Fee :
		</label>
		<div class="col-md-3">
			<sj:textfield name="hostelPaymentAmount" id="totalHostelPayAmountDiv"
				cssClass="numeric form-control" readonly="true"></sj:textfield>
		</div>
	</div>
</s:if>
<s:if test="%{classFeeCountList != null && !classFeeCountList.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-2">
			Transport Fee :
		</label>
		<div class="col-md-3">
			<sj:textfield name="transportPaymentAmount"
							id="totalTransportPayAmountDiv" cssClass="numeric form-control"
							readonly="true"></sj:textfield>
		</div>
	</div>
</s:if>
<div class="form-group">
	<label class="control-label col-md-2">
		Total Fee :
	</label>
	<div class="col-md-3">
		<sj:textfield name="totalAmount" id="totalAmountDiv"
			cssClass="numeric required form-control" readonly="true"></sj:textfield>
	</div>
</div>
<div>
	<div class="col-md-2"></div><sj:submit   cssClass="btn blue" value="Make Payment" />
</div>
<div class="spaceDiv"> &nbsp; </div>
<s:else>
	<div class="alert alert-info">
		Currently there are no fee assign this student.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {	
var totalSchoolFee=0;
var totalHostelFee=0;
var totalTransportFee=0;
 $("input:checkbox, input:radio").uniform();
var checkBoxId=0;
$("input:checkbox[name=chkBoxFeeSelectedIds]").each(function() { 
	checkBoxId=$(this).attr('id');
	$(this).parents('a').next('ul').find('span.classFeeIds').each( function() { 
		totalSchoolFee += Number($(this).html());
	});
		$('b#schoolTotalDiv'+checkBoxId).html(totalSchoolFee);
		totalSchoolFee=0;
	});
	
	$("input:checkbox[name=chkBoxHostelFeeSelectedIds]").each(function() { 
	checkBoxId=$(this).attr('id');
	$(this).parents('a').next('ul').find('span.classFeeIds').each( function() { 
		totalHostelFee += Number($(this).html());
	});
		$('b#hostelTotalDiv'+checkBoxId).html(totalHostelFee);
		totalHostelFee=0;
	});
	
	$("input:checkbox[name=chkBoxTransportFeeSelectedIds]").each(function() { 
	checkBoxId=$(this).attr('id');
	
	$(this).parents('a').next('ul').find('span.classFeeIds').each( function() { 
		totalTransportFee += Number($(this).html());
	});
		$('b#transTotalDiv'+checkBoxId).html(totalTransportFee);
		totalTransportFee=0;
	});
	
});
							
var schoolFeeAmt = 0;
var hostelFee = 0;
var trasportFee = 0;
var totalFee = 0;
$('input#totalPayAmountDiv').val('');
$("input:checkbox[name=chkBoxFeeSelectedIds]").change(function() {
var inputVal=$(this);
$(this).parents('a').next('ul').find('span.classFeeIds').each(
					function() { if ($(inputVal).is(':checked')) {
							schoolFeeAmt += Number($(this).html());
							setTotalAmount(Number($(this).html()));
						} else {
							schoolFeeAmt -= Number($(this).html());
							setTotalAmount((-1) * Number($(this).html()));
						}
					});
			$('input#totalPayAmountDiv').val(schoolFeeAmt);
			$('ul.topnav  a input b #totalDiv').val(schoolFeeAmt);
		});
$('input#totalHostelPayAmountDiv').val('');
$("input:checkbox[name=chkBoxHostelFeeSelectedIds]").change(function() {
	var inputVal=$(this);
	$(this).parents('a').next('ul').find('span.classFeeIds').each(function() {
					if ($(inputVal).is(':checked')) {
						hostelFee += Number($(this).html());
						setTotalAmount(Number($(this)
								.html()));
					} else {
						hostelFee -= Number($(this).html());
						setTotalAmount((-1)
								* Number($(this).html()));
					}
				});

					$('input#totalHostelPayAmountDiv').val(hostelFee);
				});

$('input#totalTransportPayAmountDiv').val('');
$("input:checkbox[name=chkBoxTransportFeeSelectedIds]") .change( function() {
	var inputVal=$(this);
	$(this).parents('a').next('ul').find('span.classFeeIds').each(
				function() {
						if ($(inputVal).is(':checked')) {
							trasportFee += Number($(this) .html());
							setTotalAmount(Number($(this) .html()));
							} else {
								trasportFee -= Number($(this) .html());
								setTotalAmount((-1)* Number($(this).html()));
							}
						});
		$('input#totalTransportPayAmountDiv').val(trasportFee);
	});

function setTotalAmount(amount) {
	totalFee = totalFee + (amount);
	$('input#totalAmountDiv').val(totalFee);
	
}

</script>