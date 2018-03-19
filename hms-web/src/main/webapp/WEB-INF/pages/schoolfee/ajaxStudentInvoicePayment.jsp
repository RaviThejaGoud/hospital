<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/onload.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"> </script>
<span id="feePaymentType" class="<s:property value='customer.paymentType'/>"> </span>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{(tempList != null && !tempList.isEmpty()) || (studentTransportTermsList != null && !studentTransportTermsList.isEmpty())}">
	<div class="form-body form-horizontal">
		<div class="row">
			<s:if test="%{tempString != null}">
			<div class="alert alert-info">
				Challan	<b><s:property value="tempString"/> </b>has been generated for this student. Please
				<s:url id="doCancelStudent1" action="ajaxManageGeneratChallana" namespace="/schoolfee" includeParams="all">
				</s:url>
				<sj:a href="%{doCancelStudent1}"  id="doCancelStudent2" indicator="indicator" targets="searchStudentsForm112" data-toggle="tab"><font color="red">Click here</font></sj:a> 
				 before collecting the payment.
				 </div>
			</s:if>
			
			<s:if test="%{tempId1 > 0}">
				<div class="alert alert-info">
				In accounts module fee particulars are not associated to any of the account, If you wish to associate
				
                 <s:url id="cashBookDetails" action="ajaxViewCashBookDetails" namespace="/account"></s:url>
                 <sj:a href="%{cashBookDetails}" targets="mainContentDiv" cssClass="ajaxify CASHB"> Click here</sj:a>before collecting the payment.
				 </div>
			</s:if>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="col-md-4 control-label">
						Select Term :
					</label>
					<div class="radio-list">
						<label class="radio-inline">
							<input type="radio" name="termType" id="checkAllTurms"
							value="allTermIds" checked="checked" onClick="checkAll('AT')">
						All Terms
						</label>
						<label class="radio-inline">
							<input type="radio" name="termType" value="currentTermsIds"
							onClick="checkCurrentForm()">
						Current Term
						</label>
					</div>
				</div>

			</div>
		</div>
		<s:if test="%{onlineApplicationDetails != null && onlineApplicationDetails != empty}">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span> Admission Number :
						</label>
						<div class="col-md-8">
							<s:if test="%{admissionSettings.atuoGenerationAdmissionNumberStatus}">
								
									<s:if test="%{admissionNumber != null}">
										<sj:textfield name="admissionNumber" id="studAdmissionNumber" cssClass="form-control input-medium as-input studAdmissionNumber"
										maxlength="20" readonly="true" ></sj:textfield>
									</s:if>
									<s:else>
										<sj:textfield name="admissionNumber" id="studAdmissionNumber" cssClass="form-control input-medium as-input studAdmissionNumber required"
										maxlength="20" ></sj:textfield>
									</s:else>
								
							</s:if><s:else>
									<sj:textfield name="admissionNumber" id="studAdmissionNumber" cssClass="form-control input-medium as-input studAdmissionNumber required"
										maxlength="20" ></sj:textfield>
								
							</s:else>
						
						
						
							<%-- <sj:textfield name="admissionNumber" id="studAdmissionNumber"
								cssClass="required form-control input-medium"></sj:textfield> --%>
							<span class="help-block">(Enter at least three characters for admission number.)</span>
						</div>
					</div>
				</div>
			</div>
		</s:if>
		<div class="row">
			<s:if test='%{academicYear.receiptGenerationType=="M"}'>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span> Receipt Number :
						</label>
						<div class="col-md-5">
							<sj:textfield name="studentPayment.invoiceString" id="studReceiptNumber"
								cssClass="required form-control input-medium invoiceNumber" maxlength="11"></sj:textfield>
								<div class="receiptNumberExists" style="display: none;color: red;">Receipt number is already exist.</div>
								<span title="(Please provide the receipt numbers in the above text box. Otherwise system will generate and assign the receipt number.)">
								<img class="irc_mi" src="https://upload.wikimedia.org/wikipedia/commons/5/54/Information.png" alt="Related image" 
								style="margin-top: -52px; margin-left: 239px;" width="30" height="30"  
								onload="typeof google==='object'&&google.aft&&google.aft(this)"></span>
					</div>
				</div>
				</div>
			</s:if>
			<s:else>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span> Receipt Number :
						</label>
						<div class="col-md-5">
							<s:hidden name="studentPayment.id" value="%{numberOfDays}"></s:hidden><!-- committed student fee payment id -->
							<sj:textfield name="studentPayment.invoiceNumber" id="studReceiptNumber"
								cssClass="numeric form-control input-medium invoiceNumber" maxlength="11"></sj:textfield>
								<div class="receiptNumberExists" style="display: none;color: red;">Receipt number is already exist.</div>
								<span title="(Please provide the receipt numbers in the above text box. Otherwise system will generate and assign the receipt number.)">
								<img class="irc_mi" src="https://upload.wikimedia.org/wikipedia/commons/5/54/Information.png" alt="Related image" 
								style="margin-top: -52px; margin-left: 239px;" width="30" height="30"  
								onload="typeof google==='object'&&google.aft&&google.aft(this)"></span>
						</div>
					</div>
				</div>
			</s:else>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span> Payment Date :
					</label>
					<div class="col-md-5">
						<s:if test='%{customer.allowPastDatesForPayments == "Y"}'>
							<div data-date-start-date="${customer.paymentPastDates}" data-date-end-date="+0d"
								data-date-format="mm/dd/yy"
								class="input-group input-medium date date-picker">
								<input type="text" id="paymentDate"
									name="studentPayment.paymentDate" readonly="readonly"
									value='<s:property value="studentPayment.paymentDate"/>'
									class="required form-control input-medium" /> <span
									class="input-group-btn">
									<button type="button" class="btn default">
										<i class="fa fa-calendar"></i>
									</button>
								</span>
							</div>
						</s:if>
						<s:else>
							<div data-date-start-date="-0d" data-date-end-date="+0d"
								data-date-format="mm/dd/yy"
								class="input-group input-medium date date-picker">
								<input type="text" id="paymentDate"
									name="studentPayment.paymentDate" readonly="readonly"
									value='<s:property value="studentPayment.paymentDate"/>'
									class="required form-control input-medium" /> <span
									class="input-group-btn">
									<button type="button" class="btn default">
										<i class="fa fa-calendar"></i>
									</button>
								</span>
							</div>
						</s:else>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
		</div>
		<s:if test='%{customer.chalanaGenerationStatus == "Y"}'>
			<div class="row" id="studentChallanNumberDivId">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span> Challan Number :
						</label>
						<div class="col-md-5">
							<sj:textfield name="challanaNumber" id="studChallanaNumber" readonly="true"
								cssClass="required form-control input-medium" maxlength="11"></sj:textfield>
								<span title="(Please provide the challan numbers in the above text box. Otherwise system will generate and assign the challan number.)">
								<img class="irc_mi" src="https://upload.wikimedia.org/wikipedia/commons/5/54/Information.png" alt="Related image" 
								style="margin-top: -52px; margin-left: 239px;" width="30" height="30"  
								onload="typeof google==='object'&&google.aft&&google.aft(this)"></span>
						</div>
					</div>
				</div>
			</div>
		</s:if>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Total Due Amount : 
					</label>
					<div class="col-md-5">
						<sj:textfield name="paymentAmount" id="totalPayAmountDiv"
							cssClass="numeric required form-control input-medium"
							readonly="true"></sj:textfield>
					</div>
				</div>
			</div>
			<s:if test='%{customer.allowDiscountOptOnOtherRoles == "Y" || user.isOnlySchoolAdmin =="Y" }'>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Discount :
						</label>
						<div class="col-md-2">
							<sj:textfield name="discPerc" id="termsDescountPerm"
								cssClass="numeric defaultValue form-control span1" value="%" disabled="true"
								></sj:textfield><!-- onchange="javascript:calcDiscAmtByPerc(this.value);" -->
						</div>
						<div class="col-md-5">
							<sj:textfield name="discAmount" id="termDescountAmount"
								cssClass="numeric defaultValue form-control" value="Amount"
								onkeyup="javascript:calcDiscAmtForSelectedParticularsByDiscAmt(this.value);"></sj:textfield>
						</div>
					</div>
				</div>
			</s:if>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Payable Amount :
					</label>
					<div class="col-md-5">
						<sj:textfield name="studentPayment.paidAmount"
							id="totalPayAmountAfterDiscount"
							cssClass="numeric form-control input-medium"
							onchange="javascript:onChangeTotal(this.value);"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Excess Amount :
					</label>
					<div class="col-md-5">
						<sj:textfield name="studentPayment.excessAmount" id="excessAmt"
							cssClass="numeric form-control input-medium" readonly="true"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row" >
			<div class="col-md-6" id="fineFeeAmount" style="display: none;">
				<div class="form-group">
					<label class="control-label col-md-4">
						Late Fee :
					</label>
					<div class="col-md-5">
						<sj:textfield name="studentPayment.fineAmount" id="fineAmount"
							cssClass="numeric form-control input-medium" maxlength="6"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6" id="concessionInput" style="display: none;">
				<div class="form-group">
						<label class="control-label col-md-4">
							Concession Amount :
						</label>
						<div class="col-md-5"><b> <p class="form-control-static"><span id="totalConcessionAmount">  </span></p> </b></div>
					</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6" ><!-- id="reasonForDiscount" -->
				<div class="form-group">
					<label class="control-label col-md-4"><span class="required" id="reasonForDiscount" style="display: none;">*</span>Remarks :</label>
					<div class="col-md-5">
						<sj:textarea rows="3" cols="20" name="studentPayment.discountDesc" 
						 cssClass="form-control word_count" maxCharsData="1024" id="studentDiscount"></sj:textarea>
						<div class="counter"></div> </span>
					</div>
				</div>
			</div>
			<s:if test="%{balance != null}">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							Available Excess Amount :
							<span id="avbExceeAmt"> <s:property value="balance" /> </span>
						</label>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							Remaining Excess Amount :
							<span id="remainingExceeAmt"> 0 </span>
						</label>
					</div>
				</div>
				
				
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							Use Excess Amount :
						</label>
						<div class="col-md-6">
							<s:checkbox name="selectedId" id="excessPay"
								onclick="calculatePayableAmount(this)" theme="css_xhtml"></s:checkbox>
						</div>
					</div>
				</div>
			</s:if>
			<s:else> &nbsp; </s:else>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label class="col-md-2 control-label">
						Payment Type :
					</label>
					<div class="radio-list">
						<label class="radio-inline">
							<input type="radio" value="C" name="studentPayment.paymentType"
								onclick="javascript:paymentTypeMethodChange(this.value)" checked>
							Cash 
						</label>
						<label class="radio-inline">
							<input type="radio" value="D" name="studentPayment.paymentType"
								id="ddForm"
								onclick="javascript:paymentTypeMethodChange(this.value)">
							DD
						</label>
						<label class="radio-inline">
							<input type="radio" value="CH" name="studentPayment.paymentType"
								id="chForm"
								onclick="javascript:paymentTypeMethodChange(this.value)">
							Cheque
						</label>
						<label class="radio-inline">
							<input type="radio" value="CS" name="studentPayment.paymentType"
								id="csForm"
								onclick="javascript:paymentTypeMethodChange(this.value)">
							Card Swipe
						</label>
						<label class="radio-inline">
							<input type="radio" value="NEFT" name="studentPayment.paymentType"
								id="neftForm"
								onclick="javascript:paymentTypeMethodChange(this.value)">
							NEFT
						</label>
						<s:if test="%{onlineApplicationDetails == null && onlineApplicationDetails == empty}">
							<s:if test='%{customer.chalanaGenerationStatus == "Y"}'>
								<label class="radio-inline">
									<input type="radio" value="CL" name="studentPayment.paymentType"
										id="clForm"
										onclick="javascript:paymentTypeMethodChange(this.value)">
									Challan
								</label>
							</s:if>
						</s:if>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row" style="display: none;" id="inputboxhideText">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span> Enter DD Number :
					</label>
					<div class="col-md-6">
						<sj:textfield name="studentPayment.ddNumber" id="ddNumberFee"
							value="DD Number"
							cssClass="required numeric form-control input-medium defaultValue"
							maxlength="15"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="display: none;" id="checkinputboxhideText">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span> Enter Cheque Number :
					</label>
					<div class="col-md-6">
						<sj:textfield name="studentPayment.chequeNumber" id="chequeNumber"
							value="Number"
							cssClass="required numeric form-control input-medium defaultValue"
							maxlength="15"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="display: none;" id="neftinputboxhideText">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span> Transaction Number / UTR No :
					</label>
					<div class="col-md-6">
						<sj:textfield name="studentPayment.transactionNumber" id="transactionNumber"
							value="Transaction Number"
							cssClass="required numeric form-control input-medium defaultValue"
							maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="display: none;" id="bankinputboxhideText">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<!--<span class="required">*</span>-->
						Select Bank Name :
					</label>
					<div class="col-md-6">
						<s:select list="objectList" id="bankName"
							name="studentPayment.bankMaster.id" theme="simple"
							listValue="bankName" listKey="id"
							cssClass="required form-control input-medium" />
					</div>
				</div>
			</div>
			<div class="col-md-6" id="bankibranchnputboxhideText">
				<div class="form-group">
					<label class="control-label col-md-4">
						<!--<span class="required">*</span>-->
						Bank Branch Name :
					</label>
					<div class="col-md-6">
						<sj:textfield name="studentPayment.branchName" id="branchName"
							value="Enter Bank Branch Name"
							cssClass="form-control input-medium defaultValue" maxlength="25"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<s:if test='%{#session.previousYear=="N"}'>
			<s:if test="%{description == 'studInvPaymt'}">
				<div class="form-actions fluid">
					<div class="col-md-6">
						<div class="col-md-offset-4 col-md-12">
							<sj:submit cssClass="submitBt btn blue" value="Pay" indicator="indicator" targets="searchStudentsForm112"
								onBeforeTopics="studentPaymentFormValidation" validate="true" onclick="payfees()" formIds="addStudentPaymentFee" /><!-- onCompleteTopics="doGenerateStudentChallana" -->
							<s:if test="%{wishTitle == 'futureYearPayment'}">
								<sj:submit  cssClass="submitBt btn green long" value="Pay & Print" indicator="indicator" onclick="payfee()"
								targets="searchStudentsForm112" onBeforeTopics="studentPaymentFormValidation" formIds="addStudentPaymentFee"
								onCompleteTopics="doPrintFutureYearStudentInvoice" validate="true" />
							</s:if>
							<s:else>
								<sj:submit cssClass="submitBt btn green long" value="Pay & Print" indicator="indicator" onclick="payfee()"
								targets="searchStudentsForm112" onBeforeTopics="studentPaymentFormValidation" formIds="addStudentPaymentFee"
								onCompleteTopics="doPrintStudentInvoice" validate="true" />				
							</s:else>
							<%-- <sj:submit cssClass="submitBt btn blue" value="Generate Vochaer"
								indicator="indicator" targets="searchStudentsForm112"
								onBeforeTopics="studentPaymentFormValidation" validate="true" onclick="payfeesVocher()"
								formIds="addStudentPaymentFee" /> --%>
							<%-- <s:if test='%{customer.chalanaGenerationStatus == "Y"}'>
								<sj:submit cssClass="submitBt btn green" value="Generate Challan" indicator="indicator" targets="searchStudentsForm112"
	                                onBeforeTopics="studentPaymentFormValidation" validate="true" onclick="payfeesVocher()" formIds="addStudentPaymentFee" onCompleteTopics="doGenerateStudentChallana"/>
                            </s:if> --%>
							<s:url id="doCancelStudent" action="ajaxSearchStudentsForMakePayment" namespace="/schoolfee" includeParams="all" escapeAmp="false">
								<s:param name="academicYearId" value="%{0}"></s:param>
								<s:param name="anyTitle" value="%{anyTitle}"></s:param>
								<s:param name="classSectionId" value="%{classSectionId}"></s:param>
							</s:url>
							<sj:a href="%{doCancelStudent}" cssClass="btn default" targets="searchStudentsForm112">Cancel</sj:a>
						</div>
					</div>
				</div>
			</s:if>
			<s:elseif test="%{description == 'prevStudInvPaymt'}">
				<div class="form-actions fluid">
					<div class="col-md-6">
						<div class="col-md-offset-4 col-md-12">
							<sj:submit cssClass="submitBt btn blue" value="Pay" indicator="indicator" targets="searchStudentsForm112"
								onBeforeTopics="studentPaymentFormValidation" validate="true" onclick="payfees()" formIds="addStudentPaymentFee" /><!-- onCompleteTopics="doGenerateStudentChallana" -->
							<sj:submit  cssClass="submitBt btn green long" value="Pay & Print" indicator="indicator" onclick="payfee()"
								targets="searchStudentsForm112" onBeforeTopics="studentPaymentFormValidation" formIds="addStudentPaymentFee"
								onCompleteTopics="doPrintStudentInvoice" validate="true" />
							<%-- <s:if test='%{customer.chalanaGenerationStatus == "Y"}'>
								<sj:submit cssClass="submitBt btn green" value="Generate Challan" indicator="indicator" targets="searchStudentsForm112"
	                                onBeforeTopics="studentPaymentFormValidation" validate="true" onclick="payfeesVocher()" formIds="addStudentPaymentFee" onCompleteTopics="doGenerateStudentChallana"/>
                            </s:if> --%>
							<s:url id="doCancelStudent" action="ajaxViewPreviousPendingFeeDetails" namespace="/schoolfee" includeParams="all" escapeAmp="false">
								<s:param name="academicYearId" value="%{student.academicYearId}"></s:param>
							</s:url>
							<sj:a href="%{doCancelStudent}" cssClass="btn default" targets="searchStudentsForm112">Cancel</sj:a>
						</div>
					</div>
				</div>
			</s:elseif>
			<s:else>
				<div class="form-actions fluid">
					<div class="col-md-6">
						<div class="col-md-offset-4 col-md-12">
							<sj:submit cssClass="submitBt btn blue" value="Pay" onclick="payfees()"
								indicator="indicator" targets="mainContentDiv"
								onBeforeTopics="studentPaymentFormValidation" validate="true"
								formIds="addStudentPaymentFee"/>
							<sj:submit  cssClass="submitBt btn green long" onclick="payfee()"
								value="Pay & Print" indicator="indicator"
								targets="mainContentDiv" formIds="addStudentPaymentFee"
								onBeforeTopics="studentPaymentFormValidation"
								onCompleteTopics="doPrintStudentInvoice" validate="true"/>
							<s:if test="%{eventId == 'pendingList'}">
								<s:url id="urlViewGetAdmissions" action="ajaxGetOnlineAdmissions" namespace="/admin"/>
								<sj:a href="%{urlViewGetAdmissions}" 
									targets="mainContentDiv" cssClass="btn default">Cancel</sj:a>
								<!--<a
									href="${pageContext.request.contextPath}/admin/onlineApplicationDetails.do?description=''"
									class="btn default">Cancel</a>
							--></s:if>
							<s:elseif test="%{eventId == 'shortList'}">
								<s:url id="urlViewGetShortlistAdmissions" action="ajaxApprovedApplicationsHome" namespace="/admin"/>
								<sj:a href="%{urlViewGetShortlistAdmissions}" 
									targets="mainContentDiv" cssClass="btn default">Cancel</sj:a>
								<!--<a
									href="${pageContext.request.contextPath}/admin/onlineApplicationDetails.do?description=admissionPaymentCalcel"
									class="btn default">Cancel</a>
							--></s:elseif>
							<s:else>
								<s:url id="urlViewGetAdmitted" action="ajaxViewAdmittedStudents" namespace="/admin"/>
								<sj:a href="%{urlViewGetAdmitted}" 
									targets="mainContentDiv" cssClass="btn default">Cancel</sj:a>
								<!--<a
									href="${pageContext.request.contextPath}/admin/onlineApplicationDetails.do?description=viewAdmittedStudents"
									class="btn default">Cancel</a>
							--></s:else>
						</div>
					</div>
				</div>
			</s:else>
		</s:if>
	</div>
	<div class="spaceDiv"></div>
	<s:set name="schoolTermsId" value=""></s:set>
	<s:set name="schoolFeeSettingId" value=""></s:set>
	<s:set name="feeParticularId" value=""></s:set>
	<table class="table table-bordered table-hover" id="sample_2">
		<thead>
			<th>
				<s:if test="%{customer.paymentType == 'T'}">
									Term Name
								</s:if>
				<s:else>
									Particular Name							
								</s:else>
			</th>
			<th>
				Total Amount
			</th>
			<s:if test="%{tempBoolean}">
			<th>
				Concession Amount
			</th>
			</s:if>
			<th>
				Paid Amount
			</th>
			<th>
				Balance
			</th>
			<s:if test='%{customer.allowDiscountOptOnOtherRoles == "Y" || user.isOnlySchoolAdmin =="Y" }'>
				<th>
					Discount (%) | Amount 
				</th>
			</s:if>
			<s:if test="%{customer.preferences != null && customer.preferences != empty}">
				<s:if test='%{customer.preferences.particularWisePartialAmount == "Y"}'><th>Amount Payable
				</th></s:if><s:else><th style="display: none;">Amount Payable
				</th></s:else>
			</s:if>
			<s:else>
				<th>Amount Payable
				</th>
			</s:else>
			
				
			<th> 
				Pay All Terms <input type="checkbox" name="payFeeForAllType" id="checkAllTerms"
							value="allPayFeeTermIds" onClick="checkAll('PAT')">
			</th>
		</thead>
		<tbody>
		<s:if test="%{tempList != null && !tempList.isEmpty()}">
			<s:iterator value="tempList">
				<s:if test='%{paymentStatus == "N"}'>
					<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
					<s:if test="%{feeSettingId != #schoolFeeSettingId}">
						<tr>
							<s:if test="%{tempBoolean}">
								<td colspan="8">
							</s:if><s:else><td colspan="7">
							</s:else>
								<h5>
									<center>
										<s:property value="settingName" />
									</center>
								</h5>
							</td>
						</tr>
					</s:if>
					<s:set name="termPayableAmount" value="payableAmount"></s:set>
						<tr class="partclrTerm">
							<s:if test="%{tempBoolean}">
								<td colspan="8">
							</s:if>
							<s:else>
								<td colspan="7">
							</s:else>
								<center>
									<strong> 
										Term Name :
										<span id='<s:property value="schoolTermId" />'>
											
										</span> 
									</strong>
									<s:property value="termName" />   
									<strong>   Due date :</strong> <s:property value="dueDateStr"/> <strong><s:if test="%{daysPending != 0}"> Over Due Days :</strong> <s:property value="daysPending"/></s:if>
								</center>
							</td>
						</tr>
						<s:set name="termDaysPending" value="%{daysPending}"></s:set>
						<s:iterator value="pendingStudentList">
							<s:if test='%{paymentStatus == "N"}'>
								<tr>
									<td>
										<s:property value="feeType" />
									</td>
									<td>
										<s:property value="feeAmount" />
									</td>
									<s:if test="%{tempBoolean}">
										<td>
										<s:property value="concessionAmount"/>
									</td>
									</s:if>
									<td>
										<s:property value="paymentAmount+discountAmount" />
									</td>
									<td>
										<s:property value="payableAmount" />
									</td>
									<s:if test='%{customer.allowDiscountOptOnOtherRoles == "Y" || user.isOnlySchoolAdmin =="Y" }'>
										<td class="discAndPayableAmt"  width="223px;">
										 <div class="col-md-4">
											<sj:textfield name="partclrDiscPerc"
												id="particularDiscPer_%{feeTypeId}_%{schoolTermId}"
												title="%{feeTypeId}_%{payableAmount}_%{schoolTermId}_%{concessionAmount}"
												onkeyup="calculateParticularDiscountByPrctg(this.value,%{feeTypeId},%{schoolTermId},%{payableAmount})"
												cssClass="form-control input-small numeric term_%{schoolTermId}_PartclrDiscPerc partclrWiseField"></sj:textfield>
										</div>
										<div class="col-md-4">
											<sj:textfield name="partclrDiscAmt"
												id="particularDiscAmt_%{feeTypeId}_%{schoolTermId}"
												onkeyup="calclatePrtclrPaybleAmt(%{schoolTermId},this.value,%{payableAmount},%{feeTypeId})"
												cssClass="form-control input-small numeric term_%{schoolTermId}_PartclrDiscAmt partclrWiseField"></sj:textfield>
											</div>
										</td>
									</s:if>									<s:if test="%{customer.preferences != null && customer.preferences != empty}">
										<s:if test='%{customer.preferences.particularWisePartialAmount == "Y"}'><td></s:if><s:else><td style="display: none;"></s:else>
									</s:if>
									<s:else>
										<td>
									</s:else>
									
										<div class="col-md-2">
										<sj:textfield name="payableAmount"
											title="%{feeTypeId}_%{payableAmount}_%{schoolTermId}_%{concessionAmount}" id="partRemainingPayAmt_%{feeTypeId}_%{schoolTermId}"
											onkeyup="getSelectedTermsTotalAmount()"
											cssClass="form-control input-small numeric term_%{schoolTermId}_PayableAmt partclrWiseField"
											cssStyle="width:75%" ></sj:textfield>
											</div>
									</td>
									<td>
										<s:checkbox name="chkBoxFeeSelectedIds"
											id="totalPartclrAmt_%{feeTypeId}_%{schoolTermId}"
											 theme="css_xhtml"
											title="%{payableAmount}_%{feeTypeId}_%{schoolTermId}_%{concessionAmount}_%{#termDaysPending}_%{feeSettingId}"
											onclick="getSelectedTermsTotalPaidAmount(this)"></s:checkbox><!-- cssClass="partclrWiseChkField" -->
									</td>
								</tr>
							</s:if>
						</s:iterator>
						<script type="text/javascript">
						highLightFeeDueTabParticularwise('<s:property value="schoolTermId" />',
								'<s:property value="fromMonthName" />',
								'<s:property value="toMonthName" />');
						</script>
					<s:set name="schoolFeeSettingId" value="%{feeSettingId}"></s:set>
				</s:if>
			</s:iterator>
			</s:if>
			<!-- Transport Fee -->
			<s:if test="%{studentTransportTermsList != null && !studentTransportTermsList.isEmpty()}">
			<s:iterator value="studentTransportTermsList">
				<s:if test='%{paymentStatus == "N"}'>
					<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
					<s:if test="%{feeSettingId != #schoolFeeSettingId}">
						<tr>
							<s:if test="%{tempBoolean}">
								<td colspan="8">
							</s:if><s:else><td colspan="7">
							</s:else>
								<h5>
									<center>
										<s:property value="settingName" />
									</center>
								</h5>
							</td>
						</tr>
					</s:if>
					<s:set name="termPayableAmount" value="payableAmount"></s:set>
						<tr class="partclrTerm">
							<s:if test="%{tempBoolean}">
								<td colspan="8">
							</s:if>
							<s:else>
								<td colspan="7">
							</s:else>
								<center>
									<strong> 
										Term Name :
										<span id='<s:property value="schoolTermId" />'>
											
										</span> 
									</strong>
									<s:property value="termName" />   
									<strong>   Due date :</strong> <s:property value="dueDateStr"/> <strong><s:if test="%{daysPending != 0}"> Over Due Days :</strong> <s:property value="daysPending"/></s:if>
								</center>
							</td>
						</tr>
						<s:set name="termDaysPending" value="%{daysPending}"></s:set>
						<s:if test="%{studentTransportFeeList != null && !studentTransportFeeList.isEmpty()}">
						<s:iterator value="studentTransportFeeList">
							<s:if test='%{paymentStatus == "N"}'>
								<tr>
									<td>
										<s:property value="feeType" />
									</td>
									<td>
										<s:property value="feeAmount" />
									</td>
									<s:if test="%{tempBoolean}">
										<td>
										<s:property value="concessionAmount"/>
									</td>
									</s:if>
									<td>
										<s:property value="paymentAmount+discountAmount" />
									</td>
									<td>
										<s:property value="payableAmount" />
									</td>
									<s:if test='%{customer.allowDiscountOptOnOtherRoles == "Y" || user.isOnlySchoolAdmin =="Y" }'>
										<td class="discAndPayableAmt"  width="223px;">
										 <div class="col-md-4">
											<sj:textfield name="partclrDiscPerc"
												id="particularDiscPer_%{feeTypeId}_%{schoolTermId}"
												title="%{feeTypeId}_%{payableAmount}_%{schoolTermId}_%{concessionAmount}"
												onkeyup="calculateParticularDiscountByPrctg(this.value,%{feeTypeId},%{schoolTermId},%{payableAmount})"
												cssClass="form-control input-small numeric term_%{schoolTermId}_PartclrDiscPerc partclrWiseField"></sj:textfield>
										</div>
										<div class="col-md-4">
											<sj:textfield name="partclrDiscAmt"
												id="particularDiscAmt_%{feeTypeId}_%{schoolTermId}"
												onkeyup="calclatePrtclrPaybleAmt(%{schoolTermId},this.value,%{payableAmount},%{feeTypeId})"
												cssClass="form-control input-small numeric term_%{schoolTermId}_PartclrDiscAmt partclrWiseField"></sj:textfield>
											</div>
										</td>
									</s:if>
									<s:if test="%{customer.preferences != null && customer.preferences != empty}">
										<s:if test='%{customer.preferences.particularWisePartialAmount == "Y"}'><td></s:if><s:else><td style="display: none;"></s:else>
									</s:if>
									<s:else>
										<td>
									</s:else>
									
										<div class="col-md-2">
										<sj:textfield name="payableAmount"
											title="%{feeTypeId}_%{payableAmount}_%{schoolTermId}_%{concessionAmount}_%{feeSettingId}" id="partRemainingPayAmt_%{feeTypeId}_%{schoolTermId}"
											onkeyup="getSelectedTermsTotalAmount()"
											cssClass="form-control input-small numeric term_%{schoolTermId}_PayableAmt partclrWiseField"
											cssStyle="width:75%" ></sj:textfield>
											</div>
									</td>
									<td>
										<s:checkbox name="chkBoxFeeSelectedIds"
											id="totalPartclrAmt_%{feeTypeId}_%{schoolTermId}"
											 theme="css_xhtml"
											title="%{payableAmount}_%{feeTypeId}_%{schoolTermId}_%{concessionAmount}_%{#termDaysPending}_%{feeSettingId}"
											onclick="getSelectedTermsTotalPaidAmount(this)"></s:checkbox><!-- cssClass="partclrWiseChkField" -->
									</td>
								</tr>
							</s:if>
						</s:iterator>
						</s:if>
						<script type="text/javascript">
						highLightFeeDueTabParticularwise('<s:property value="schoolTermId" />',
								'<s:property value="fromMonthName" />',
								'<s:property value="toMonthName" />');
						</script>
					<s:set name="schoolFeeSettingId" value="%{feeSettingId}"></s:set>
				</s:if>
			</s:iterator>
			</s:if>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Fees not configured for this student class or you have paid fee for
		this student.
		<s:if test="%{description == 'studInvPaymt'}">
				<s:url id="doCancelStudent" action="ajaxSearchStudentsForMakePayment"
					namespace="/schoolfee" includeParams="all" escapeAmp="false">
					<s:param name="academicYearId" value="%{0}">
					</s:param>
					<s:param name="anyTitle" value="%{anyTitle}"></s:param>
					<s:param name="classSectionId" value="%{classSectionId}"></s:param>
				</s:url>
				<sj:a href="%{doCancelStudent}" 
					indicator="indicator" targets="searchStudentsForm112" ><font color="red">Click here</font></sj:a> to go back.
			</s:if>
			<s:else>
					<s:if test="%{eventId == 'pendingList'}">
						<s:url id="urlViewGetAdmissions" action="ajaxGetOnlineAdmissions" namespace="/admin"/>
						<sj:a href="%{urlViewGetAdmissions}" 
							targets="mainContentDiv" ><font color="red">Click here</font></sj:a> to view Pending Applications.
					</s:if>
					<s:elseif test="%{eventId == 'shortList'}">
						<s:url id="urlViewGetShortlistAdmissions" action="ajaxApprovedApplicationsHome" namespace="/admin"/>
						<sj:a href="%{urlViewGetShortlistAdmissions}" 
							targets="mainContentDiv" ><font color="red">Click here</font></sj:a> to view Shortlist Applications.
					</s:elseif>
					<s:else>
						<s:url id="urlViewGetAdmitted" action="ajaxViewAdmittedStudents" namespace="/admin"/>
						<sj:a href="%{urlViewGetAdmitted}" 
							targets="mainContentDiv"><font color="red">Click here</font></sj:a> to view Admitted Students.
					</s:else>
			</s:else>
	</div>
</s:else>
<s:if test='%{academicYear.receiptGenerationType=="A"}'>
	<span class="receiptNumber" id="<s:property value='tempId'/>"></span>
</s:if>
<span class="challanaNumber" id="<s:property value='tempId2'/>"></span>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<script type="text/javascript">
var totalTermsAmount =0;
$(document).ready(function() {
	var receiptType ='<s:property value="academicYear.receiptGenerationType" />';
	if(receiptType == "A"){
		$("#studReceiptNumber").val($('span.receiptNumber').attr('id'));
	}
	$("#studChallanaNumber").val($('span.challanaNumber').attr('id'));
	$("#studentChallanNumberDivId").hide();
	var payMode=null;
				$.destroyTopic('studentPaymentFormValidation');
				$('div#reasonForDiscount').hide();
					 $("input:checkbox, input:radio:not('.toggle')").uniform(); 
					 FormComponents.init(); 
					 totalTermsAmount = getSelectedTermsTotalAmount();
					 $('#totalPayAmountDiv').val(totalTermsAmount);
					 var formattedDate = $.datepicker.formatDate('mm/dd/yy',new Date());
					$("#paymentDate").val(formattedDate);
					$('.numeric').numeric();
					if ($("input[name='termType']" + ':checked').val() == 'allTermIds') {
						checkAll('AT');
					}
					/* $("input#studAdmissionNumber").autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckStudentAdmissionNumber.do",
					{
						minChars : 3,
						min : "no"
					}); */
					$('input#studReceiptNumber').autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckStudentReceiptNumber.do",
					{
						minChars : 3,
						min : "no"
					});
					
					$('input.invoiceNumber').mouseover(function(){
							$('.submitBt').attr('disabled', false);
							$("div.receiptNumberExists").hide();
							});
					
					
					$( "input.submitBt" ).mouseover(function() {
						var invoiceNumber=$(".invoiceNumber").val();
						//event.originalEvent.options.submit=false;
						var feeURL = jQuery.url.getChatURL("/schoolfee/ajaxValidateInvoiceNumber.do?studentPayment.invoiceString="+ invoiceNumber);
						$.ajax( {
							url : feeURL,
							cache : false,
							dataType : 'json',
							success : function(response) {
								if(isNonEmpty(response)){
								var validateCode=response.validateCode;
									if(validateCode==0){
										//alert(invoiceNumber+" Invoice number already exists.");
										//$('.submitBt').removeAttr('disabled');
										$('.submitBt').attr('disabled', true);
										$("div.receiptNumberExists").show();
									}
									else{
										//alert(" Else");
										$("div.receiptNumberExists").hide();
										$('.submitBt').attr('disabled', false);
										//$("#addStudentPaymentFee").submit();
										//continue; 
									}
								}
								}
						});
					});
					
					$.subscribe('studentPaymentFormValidation',function(event, data) {
						 
							$('.submitBt').attr('disabled', 'disabled');
							var branchName = $("#branchName").val();
							var bankName = $("#bankName").val();
							var jsonObj = [];
							var payableAmount = 0;
							var discountAmt = 0;
							var concessionAmount =0;
							var ids = '';
							var paymentType = $('#feePaymentType').attr('class');
							
							$('#paymentType').val(paymentType);
							if ('T' == paymentType) {
								if ($('input[name=chkBoxFeeSelectedIds]:checked').length > 0) {
									$('input[name=chkBoxFeeSelectedIds]:checked').each(function() {
											ids = $(this).attr('title').split('_');
											if (isNonEmpty(ids)) {
												payableAmount = $('#term_' + ids[1] + '_RemainingPayAmt').val();
												discountAmt = $('#totalTerm_' + ids[1] + 'discountAmt').val();
												payableAmount = isNonEmpty(payableAmount) ? parseFloat(payableAmount) : parseFloat(0);
												discountAmt = isNonEmpty(discountAmt) ? parseFloat(discountAmt) : parseFloat(0);
												if (discountAmt != 0 || payableAmount != 0) {
													jsonObj.push( {
																"termId" : ids[1],
																"totalAmount" : ids[0],
																"payableAmount" : payableAmount,
																"discountAmount" : discountAmt
															});
												}
											}
										});
								} else {
									alert('Please select at least one term.');
									$('.submitBt').removeAttr('disabled');
									event.originalEvent.options.submit=false;
								}
							} else {
								if ($('input[name=chkBoxFeeSelectedIds]:checked').length > 0) {
									$('input[name=chkBoxFeeSelectedIds]:checked').each(function() {
										ids = $(this).attr('title').split('_');
										//alert("concession amount :"+ids[3]+" pending due days :"+ids[4]);
										if (isNonEmpty(ids)) {
											concessionAmount = ids[3];
											payableAmount = $('#partRemainingPayAmt_'+ ids[1]+ '_'+ ids[2]).val();
											discountAmt = $('#particularDiscAmt_'+ ids[1]+ '_'+ ids[2]).val();
											payableAmount = isNonEmpty(payableAmount) ? parseFloat(payableAmount) : parseFloat(0);
											discountAmt = isNonEmpty(discountAmt) ? parseFloat(discountAmt)	: parseFloat(0);
											if (discountAmt != 0 || payableAmount != 0) {
												jsonObj.push( {
											                "termId" : ids[2],
															"feeTypeId" : ids[1],
															"totalAmount" : ids[0],
															"payableAmount" : payableAmount,
															"discountAmount" : discountAmt,
															"concessionAmount" : concessionAmount,
															"feeSettingId" : ids[5]
														});
											}
										}
									});
									var generationType = $('input[name="studentPayment.paymentType"]:radio:checked').val();
									if('CL' == generationType){
										var excessAmt = $('#excessAmt').val();
										if(parseFloat(excessAmt) >0){
											alert("You can not generate challan for excess payment.");
											$('.submitBt').removeAttr('disabled');
											event.originalEvent.options.submit=false;
										}
										if($('input#totalPayAmountAfterDiscount').val()==0){
											alert("100% discount is not allowed for challan.");
											$('.submitBt').removeAttr('disabled');
											event.originalEvent.options.submit=false;
										}
									}
								} else {
									if($('input#totalPayAmountAfterDiscount').val()==0)
									alert("Payable Amount should be greater than Zero.");
									
									alert('Please select at least one particular.');
									$('.submitBt').removeAttr('disabled');
									event.originalEvent.options.submit=false;
								}
							}
							
							if ($('#ddForm').is(':checked')) {
								var ddNum = $("#ddNumberFee").val();
								if (!isNonEmpty(ddNum) || ddNum == "DD Number") {
									alert("Please enter dd number.");
									$('.submitBt').removeAttr('disabled');
									event.originalEvent.options.submit=false;
								}
							} else if ($('#chForm').is(':checked')) {
								var chequeNum = $("#chequeNumber").val();
								if (chequeNum == null || chequeNum == "" || chequeNum == "Number") {
									alert("Enter Cheque Number");
									$('.submitBt').removeAttr('disabled');
									event.originalEvent.options.submit=false;
								}
							}else if ($('#csForm').is(':checked') || $('#neftForm').is(':checked')) {
								var transactionNum = $("#transactionNumber").val();
								if (transactionNum == null || transactionNum == "" || transactionNum == "Transaction Number") {
									alert("Please enter transaction / UTR number.");
									$('.submitBt').removeAttr('disabled');
									event.originalEvent.options.submit=false;
								}
							}
							var admissnnumber = $("[name='admissionNumber']").val();
							if(isNonEmpty(admissnnumber)){
								if(admissnnumber.length < 3){
									alert('Please provide at least three characters for admission number.')
									$('.submitBt').removeAttr('disabled');
									 event.originalEvent.options.submit=false;
								}
							}		
							 if($('p.word-taken').html()=='Already taken!!!'){
								 $('.submitBt').removeAttr('disabled');
					    	    event.originalEvent.options.submit=false;
					         }else{
					        	 if(receiptType == "A"){
							        var receiptNumber = $("[name='studentPayment.invoiceNumber']").val();
									if (!isNonEmpty(receiptNumber)) {
										var receiptNum = $('span.receiptNumber').attr('id');
										var r = confirm("You have not provided receipt number. So we will use '"+ receiptNum+ "' as receipt number.");
										if (r == false) {
											$('.submitBt').removeAttr('disabled');
											event.originalEvent.options.submit=false;
										} else {
											$("input[name='studentPayment.invoiceNumber']").val(receiptNum);
										}
									}
					        	 }
					         }
							 if ($('#addStudentPaymentFee').valid()){
								 //alert(JSON.stringify(jsonObj))
								 $("#feeData").val(JSON.stringify(jsonObj));
									return true;
								}else{
									$('.submitBt').removeAttr('disabled');
									event.originalEvent.options.submit=false;
								}
						 
						
						});
					
					$.subscribe('doPrintStudentInvoice', function(event, data) {
						$('#printReport').submit();
						$('#printChallana').submit();
						$.destroyTopic('doPrintStudentInvoice');
					});
					$.subscribe('doGenerateStudentChallana', function(event, data) {
						$('#printChallana').submit();
						$.destroyTopic('doPrintStudentInvoice');
					});
					$.subscribe('doPrintFutureYearStudentInvoice', function(event, data) {
						$('#printFutureAcademicReport').submit();
						$.destroyTopic('doPrintFutureYearStudentInvoice');
					});
					$("input[name='chkBoxFeeSelectedIds']:checked").each(function() {
						var termAmtAndtermId = $(this).attr('title').split('_');
						alert("concession amount :"+termAmtAndtermId[3]+" pending due days :"+termAmtAndtermId[4]);
						//amount += parseFloat(termAmtAndtermId[0]);
					});
					$('input[id^="totalPartclrAmt_"]').click(function () {
						$("input[name='chkBoxFeeSelectedIds']:checked").each(function() {
							var dueDaysCount = $(this).attr('title').split('_');
							if(dueDaysCount[4] > 0){
								$('#fineFeeAmount').show();								
								return false;
							}else{
								$('#fineFeeAmount').hide();	
							}
						});
				    });
				});

function payfees() {
	$('#paybtn').val('Pay');
}
function payfee() {
	$('#paybtn').val('Print');
}
function payfeesVocher() {
	$('#paybtn').val('Voucher');
}
function calculatePayableAmount(event) {
	calculatePaybleAmountBase($(event).is(':checked'));
}

function calculatePaybleAmountBase(excessVal)
{
	if (excessVal) {
		var totalPayAmt = $('#totalPayAmountAfterDiscount').val();
		var excessAmt = $('span#avbExceeAmt').html();
		if (isNonEmpty(totalPayAmt) && totalPayAmt != 0	&& isNonEmpty(excessAmt)) {
			totalPayAmt = parseFloat(totalPayAmt);
			excessAmt = parseFloat(excessAmt);
			if (totalPayAmt >= excessAmt) {
				$('#totalPayAmountAfterDiscount').val(totalPayAmt - excessAmt);
				$('#remainingExceeAmt').html(0);
				
			} else {
				$('#totalPayAmountAfterDiscount').val(0);
				$('#remainingExceeAmt').html(excessAmt - totalPayAmt);
			}
		}
	} else {
		getSelectedTermsTotalAmount();
	}
}
function calcDiscAmtByPerc(discPerc) {
	if (isNonEmpty(discPerc) && discPerc>0) {
		if (discPerc <= 100) {
			calcMultipleTermsDiscPerc(discPerc);
			disbleAllTermsFields();
			$('#studentDiscount').addClass('required');
			$('#reasonForDiscount').show();
		} else {
			alert('Discount percentage should be less than or equal to 100.');
			$('#termsDescountPerm').val('%');
			$('#reasonForDiscount').hide();
			calcMultipleTermsDiscPerc(0);
			enableAllTermsFields();
		}
	} else {
		calcMultipleTermsDiscPerc(0);
		enableAllTermsFields();
		$('#reasonForDiscount').hide();
	}
}

function calcDiscAmtForSelectedParticularsByDiscAmt(discAmt) {
	if (isNonEmpty(discAmt) && discAmt>0) {
		$('#reasonForDiscount').show();
		$('#studentDiscount').addClass('required');
	}else{
		$('#reasonForDiscount').hide();
	}
	var totalPaymentAmount = getBalanceAmountForSelectedPart();
	//alert("totalPaymentAmount "+totalPaymentAmount)
	if (isNonEmpty(discAmt) && isNonEmpty(totalPaymentAmount)) {
		discAmt = parseFloat(discAmt);
		totalPaymentAmount = parseFloat(totalPaymentAmount);
		if (totalPaymentAmount >= discAmt) {
			var mulTermsDiscPerc = (discAmt / parseFloat(totalPaymentAmount) * 100);
			$('#termsDescountPerm').val(mulTermsDiscPerc);
			calculateMultipleTermsDiscAmt(mulTermsDiscPerc, discAmt);
			disbleAllTermsFields();
		} else {
			alert('Discount amount should be less than or equal to ' + totalPaymentAmount);
			$('#reasonForDiscount').hide();
			$('#termDescountAmount').val('Amount');
			$('#termsDescountPerm').val('%');
			calculateMultipleTermsDiscAmt(0, discAmt);
			enableAllTermsFields();
		}
	} else {
		$('#termsDescountPerm').val('%');
		calculateMultipleTermsDiscAmt(0, 0);
		enableAllTermsFields();
	}
	getSelectedTermsTotalAmount();
}

function calculateMultipleTermsDiscAmt(mulTermsDiscPerc, discAmt) {
	var paymentType = $('#feePaymentType').attr('class');
	// For sorting fee particluars based on Balance Amount.
	var partPercList = $("input[name='chkBoxFeeSelectedIds']:checked").sort(
			function(a, b) {
				var an = a.getAttribute('title'), bn = b.getAttribute('title');
				if (isNonEmpty(an) && isNonEmpty(bn)) {
					an = parseFloat(an.split('_')[0]);
					bn = parseFloat(bn.split('_')[0]);
					if (an > bn) {
						return 1;
					}
					if (an < bn) {
						return -1;
					}
				}
				return 0;
			});
	var totTermsDiscAmt = 0;
	var isLastRecord = '';
	partPercList.each(function(i) {
		var feeTypeIdAndPaymentAmt = $(this).attr('title');
		feeTypeIdAndPaymentAmt = feeTypeIdAndPaymentAmt.split('_');
		if ('T' == paymentType) {
			$('#totalTerm_' + feeTypeIdAndPaymentAmt[1] + '_discPerc').val(mulTermsDiscPerc);
			if (partPercList.size() == (i + 1))
				isLastRecord = true;
			totTermsDiscAmt = calculateTermDiscount(mulTermsDiscPerc, discAmt,feeTypeIdAndPaymentAmt[1], feeTypeIdAndPaymentAmt[0],
					isLastRecord, totTermsDiscAmt, 0);
		} else {
			$('#particularDiscPer_' + feeTypeIdAndPaymentAmt[1] + '_'+ feeTypeIdAndPaymentAmt[2]).val(mulTermsDiscPerc);
			if (partPercList.size() == (i + 1))
				isLastRecord = true;
			//alert("before calculateTermDiscount")
			totTermsDiscAmt = calculateTermDiscount(mulTermsDiscPerc, discAmt,feeTypeIdAndPaymentAmt[2], feeTypeIdAndPaymentAmt[0],isLastRecord, totTermsDiscAmt, feeTypeIdAndPaymentAmt[1]);
		}
	});
	//$('#termDescountAmount').val(totTermsDiscAmt);
}

function calculateTermDiscount(discPer, discAmt, schoolTermId, payableAmount,isLastRecord, totTermsDiscAmt, partclrId) {
	var paymentType = $('#feePaymentType').attr('class');
	if (isNonEmpty(discPer) && isNonEmpty(payableAmount)) {
		discPer = parseFloat(discPer);
		payableAmount = parseFloat(payableAmount);
		if (discPer <= 100) {
			if (isNonEmpty(isLastRecord) && isLastRecord == true) {
				var disAmt = discAmt - totTermsDiscAmt;
				if ('T' == paymentType) {
					$('#totalTerm_' + schoolTermId + 'discountAmt').val(disAmt);
					$('#term_' + schoolTermId + '_RemainingPayAmt').val(payableAmount - disAmt);
				} else {
					$('#particularDiscAmt_' + partclrId + '_' + schoolTermId).val(disAmt);
					$('#partRemainingPayAmt_' + partclrId + '_' + schoolTermId).val(payableAmount - disAmt);
				}
			} else {
				var disAmt = Math.round(((discPer / 100) * payableAmount));
				totTermsDiscAmt += disAmt;
				if ('T' == paymentType) {
					$('#totalTerm_' + schoolTermId + 'discountAmt').val(disAmt);
					$('#term_' + schoolTermId + '_RemainingPayAmt').val(payableAmount - disAmt);
				} else {
					$('#particularDiscAmt_' + partclrId + '_' + schoolTermId).val(disAmt);
					$('#partRemainingPayAmt_' + partclrId + '_' + schoolTermId).val(payableAmount - disAmt);
				}
			}
		}
	} else {
		if ('T' == paymentType) {
			$('#totalTerm_' + schoolTermId + 'discountAmt').val('');
			$('#term_' + schoolTermId + '_RemainingPayAmt').val(payableAmount);
		} else {
			$('#particularDiscAmt_' + partclrId + '_' + schoolTermId).val('');
			$('#partRemainingPayAmt_' + partclrId + '_' + schoolTermId).val(payableAmount);
		}
	}
	return totTermsDiscAmt;
}

function calcMultipleTermsDiscPerc(discPerc) {
	var paymentType = $('#feePaymentType').attr('class');
	if ('T' == paymentType) {
		$('td.termDiscAndPayableAmt').each(function() {
					var discPercObj = $(this).find("input[name='termDiscountPerc']");
					var termAmtAndTermId = $(discPercObj).attr('title');
					termAmtAndTermId = termAmtAndTermId.split('_');
					if ($('#totalTermAmt_' + termAmtAndTermId[1]).is(':checked')) {
						$(discPercObj).val(discPerc);
					} else {
						$(discPercObj).val('');
					}
					calcDiscAmtForTermsByPtg(termAmtAndTermId[1], discPerc,termAmtAndTermId[0]);
				});
	} else {
		var totalParclrDisAmt =0;
		$('td.discAndPayableAmt').each(function() {
			var discPercObj = $(this).find("input[name='partclrDiscPerc']");
			var feeTypeIdPayableAmt = $(discPercObj).attr('title');
			feeTypeIdPayableAmt = feeTypeIdPayableAmt.split('_');
			if ($('#totalPartclrAmt_' + feeTypeIdPayableAmt[0] + '_'+ feeTypeIdPayableAmt[2]).is(':checked')) {
				$(discPercObj).val(discPerc);
			} else {
				$(discPercObj).val('');
			}
			var partlrDisc = calcDiscAmtForPartclrsByPtg(feeTypeIdPayableAmt[0],discPerc, feeTypeIdPayableAmt[1],feeTypeIdPayableAmt[2]);
			totalParclrDisAmt = (totalParclrDisAmt+partlrDisc);
		});
		if(totalParclrDisAmt >0){
			$('#termDescountAmount').val(totalParclrDisAmt);
			$('#termsDescountPerm').val(discPerc);
		}
	}
}

// For calculating particular discount amount and particular payable amount based on particular discount percentage.
function calculateParticularDiscountByPrctg(discountPerc, feeTypeId, schoolTermId, payableAmount) {
	if (isNonEmpty(discountPerc) && isNonEmpty(payableAmount)) {
		discountPerc = parseFloat(discountPerc);
		payableAmount = parseFloat(payableAmount);
		if (discountPerc <= 100) {
			//if ($('#totalPartclrAmt_' + feeTypeId + '_' + schoolTermId).is(':checked')) {
				var discAmt = Math.round(((discountPerc / 100) * payableAmount) * 100) / 100;
				$('#particularDiscAmt_' + feeTypeId + '_' + schoolTermId).val(discAmt);
				$('#partRemainingPayAmt_' + feeTypeId + '_' + schoolTermId).val(payableAmount - discAmt);
				disablePartclrPayableAmountFields(feeTypeId, schoolTermId);
			/* } else {
				$('#particularDiscAmt_' + feeTypeId + '_' + schoolTermId).val('');
				$('#partRemainingPayAmt_' + feeTypeId + '_' + schoolTermId).val(payableAmount);
				enablePartclrPayableAmountFields(feeTypeId, schoolTermId);
			} */
		} else {
			alert('Discount percentage should be less than or equal to 100.');
			$('#particularDiscPer_' + feeTypeId + '_' + schoolTermId).val('');
			$('#particularDiscAmt_' + feeTypeId + '_' + schoolTermId).val('');
			$('#partRemainingPayAmt_' + feeTypeId + '_' + schoolTermId).val(payableAmount);
			enablePartclrPayableAmountFields(feeTypeId, schoolTermId);
		}
	} else {
		$('#particularDiscAmt_' + feeTypeId + '_' + schoolTermId).val('');
		$('#partRemainingPayAmt_' + feeTypeId + '_' + schoolTermId).val(payableAmount);
		enablePartclrPayableAmountFields(feeTypeId, schoolTermId);
	}
	getSelectedTermsTotalAmount();
}

function calclatePrtclrPaybleAmt(schoolTermId, partTotDiscAmt, prtclrPayableAmount, feeTypeId) {
	var partclrDiscPerc = 0;
	if (isNonEmpty(partTotDiscAmt)) {
		if (prtclrPayableAmount >= partTotDiscAmt) {
			partclrDiscPerc = (partTotDiscAmt / parseFloat(prtclrPayableAmount) * 100);
			$('#particularDiscPer_' + feeTypeId + '_' + schoolTermId).val(partclrDiscPerc);
			$('#partRemainingPayAmt_' + feeTypeId + '_' + schoolTermId).val((prtclrPayableAmount - partTotDiscAmt));
			$('#partRemainingPayAmt_' + feeTypeId + '_' + schoolTermId).attr('readonly', 'true');
			$('#totalPayAmountAfterDiscount').attr('readonly', 'true');
			$('#reasonForDiscount').show();
			$('#studentDiscount').addClass('required');
		} else {
			alert('Discount amount should be less than or equal to ' + prtclrPayableAmount);
			$('#particularDiscAmt_' + feeTypeId + '_' + schoolTermId).val('');
			$('#particularDiscPer_' + feeTypeId + '_' + schoolTermId).val('');
			$('#partRemainingPayAmt_' + feeTypeId + '_' + schoolTermId).val(prtclrPayableAmount);
			$('#partRemainingPayAmt_' + feeTypeId + '_' + schoolTermId).removeAttr('readonly');
			$('#totalPayAmountAfterDiscount').removeAttr('readonly');
			$('#reasonForDiscount').hide();
		}
	} else {
		$('#particularDiscPer_' + feeTypeId + '_' + schoolTermId).val('');
		$('#partRemainingPayAmt_' + feeTypeId + '_' + schoolTermId).val('');
		$('#partRemainingPayAmt_' + feeTypeId + '_' + schoolTermId).val((prtclrPayableAmount));
		$('#partRemainingPayAmt_' + feeTypeId + '_' + schoolTermId).removeAttr('readonly');
		$('#totalPayAmountAfterDiscount').removeAttr('readonly');
		$('#reasonForDiscount').hide();
		
		
	}
	getSelectedTermsTotalAmount();

}

// For calculating disc percentag for term and particlulars based on term disc amount		
function calcDiscAmtForTrmsAndPrtclsByAmt(schoolTermId, termPayableAmt, termTotPartDiscAmt) {
	var termDiscPerc = 0;
	if (isNonEmpty(termTotPartDiscAmt)) {
		if (termPayableAmt >= termTotPartDiscAmt) {
			termDiscPerc = (termTotPartDiscAmt / parseFloat(termPayableAmt) * 100);
			$('#totalTerm_' + schoolTermId + '_discPerc').val(termDiscPerc);
			$('#term_' + schoolTermId + '_RemainingPayAmt').val((termPayableAmt - termTotPartDiscAmt));
			$('#term_' + schoolTermId + '_RemainingPayAmt').attr('readonly', 'true');
			$('#totalPayAmountAfterDiscount').attr('readonly', 'true');
			$('#reasonForDiscount').show();
			$('#studentDiscount').addClass('required');
		} else {
			alert('Discount amount should be less than or equal to ' + termPayableAmt);
			$('#totalTerm_' + schoolTermId + 'discountAmt').val('');
			$('#totalTerm_' + schoolTermId + '_discPerc').val('');
			$('#term_' + schoolTermId + '_RemainingPayAmt').val((termPayableAmt));
			$('#term_' + schoolTermId + '_RemainingPayAmt').removeAttr('readonly');
			$('#totalPayAmountAfterDiscount').removeAttr('readonly');
			$('#reasonForDiscount').hide();
		}
	} else {
		$('#totalTerm_' + schoolTermId + '_discPerc').val('');
		$('#totalTerm_' + schoolTermId + 'discountAmt').val('');
		$('#term_' + schoolTermId + '_RemainingPayAmt').val((termPayableAmt));
		$('#term_' + schoolTermId + '_RemainingPayAmt').removeAttr('readonly');
		$('#totalPayAmountAfterDiscount').removeAttr('readonly');
		$('#reasonForDiscount').hide();
	}
	getSelectedTermsTotalAmount();
}

// For calculating discount for term based on disc percentage		
function calcDiscAmtForTermsByPtg(termId, discountPerc, termPayableAmt) {
	if (isNonEmpty(discountPerc) && isNonEmpty(termPayableAmt)) {
		discountPerc = parseFloat(discountPerc);
		termPayableAmt = parseFloat(termPayableAmt);
		if (discountPerc <= 100) {
			if ($('#totalTermAmt_' + termId).is(':checked')) {
				var discAmt = Math.round(((discountPerc / 100) * termPayableAmt) * 100) / 100;
				$('#totalTerm_' + termId + 'discountAmt').val(discAmt);
				$('#term_' + termId + '_RemainingPayAmt').val(termPayableAmt - discAmt);
				disablePayableAmountFields(termId);
			} else {
				$('#totalTerm_' + termId + 'discountAmt').val('');
				$('#term_' + termId + '_RemainingPayAmt').val(termPayableAmt);
				enablePayableAmountFields(termId);
			}
		} else {
			alert('Discount percentage should be less than or equal to 100.');
			$('totalTerm_' + termId + '_discPerc').val('');
			$('#totalTerm_' + termId + 'discountAmt').val('');
			$('#term_' + termId + '_RemainingPayAmt').val(termPayableAmt);
			enablePayableAmountFields(termId);
		}
	} else {
		$('#totalTerm_' + termId + 'discountAmt').val('');
		$('#term_' + termId + '_RemainingPayAmt').val(termPayableAmt);
		enablePayableAmountFields(termId);
	}
	getSelectedTermsTotalAmount();
}

// For calculating discount for particulars based on disc percentage	
function calcDiscAmtForPartclrsByPtg(partclrId, discountPerc,partclrPayableAmt, termId) {
	if (isNonEmpty(discountPerc) && isNonEmpty(partclrPayableAmt)) {
		discountPerc = parseFloat(discountPerc);
		partclrPayableAmt = parseFloat(partclrPayableAmt);
		
		if (discountPerc <= 100) {
			var totalParclrDisAmt = 0;
			if ($('#totalPartclrAmt_' + partclrId + '_' + termId).is(':checked')) {
				var discAmt = Math.round(((discountPerc / 100) * partclrPayableAmt) * 100) / 100;
				$('#particularDiscAmt_' + partclrId + '_' + termId).val(discAmt);
				$('#partRemainingPayAmt_' + partclrId + '_' + termId).val(partclrPayableAmt - discAmt);
				disablePartclrPayableAmountFields(partclrId, termId);
				totalParclrDisAmt =+(totalParclrDisAmt+discAmt);
				//alert("partclrPayableAmt :"+partclrPayableAmt+" totalParclrDisAmt :"+totalParclrDisAmt)
			} else {
				$('#particularDiscAmt_' + partclrId + '_' + termId).val('');
				$('#partRemainingPayAmt_' + partclrId + '_' + termId).val(partclrPayableAmt);
				enablePartclrPayableAmountFields(partclrId, termId);
			}
			return totalParclrDisAmt;
		} else {
			alert('Discount percentage should be less than or equal to 100.');
			$('#particularDiscPer_' + partclrId + '_' + termId).val('');
			$('#particularDiscAmt_' + partclrId + '_' + termId).val('');
			$('#partRemainingPayAmt_' + partclrId + '_' + termId).val(partclrPayableAmt);
			enablePartclrPayableAmountFields(partclrId, termId);
		}
	} else {
		$('#particularDiscAmt_' + partclrId + '_' + termId).val('');
		$('#partRemainingPayAmt_' + partclrId + '_' + termId).val(partclrPayableAmt);
		enablePartclrPayableAmountFields(partclrId, termId);
	}
	return 0;
	getSelectedTermsTotalAmount();
}
function disablePayableAmountFields(termId) {
	$('#term_' + termId + '_RemainingPayAmt').attr('readonly', 'true');
	$('#totalPayAmountAfterDiscount').attr('readonly', 'true');
	$('#reasonForDiscount').show();
	$('#studentDiscount').addClass('required');
}

function disablePartclrPayableAmountFields(partclrId, termId) {
	$('#partRemainingPayAmt_' + partclrId + '_' + termId).attr('readonly','true');
	$('#totalPayAmountAfterDiscount').attr('readonly', 'true');
	$('#reasonForDiscount').show();
	$('#studentDiscount').addClass('required');
}

function enablePartclrPayableAmountFields(partclrId, termId) {
	$('#partRemainingPayAmt_' + partclrId + '_' + termId).removeAttr('readonly');
	$('#totalPayAmountAfterDiscount').removeAttr('readonly');
	$('#studentDiscount').removeClass('required');
	$('#reasonForDiscount').hide();
}

function enablePayableAmountFields(termId) {
	$('#term_' + termId + '_RemainingPayAmt').removeAttr('readonly');
	$('#totalPayAmountAfterDiscount').removeAttr('readonly');
	$('#reasonForDiscount').hide();
}

function enableAllTermsFields() {
	var paymentType = $('#feePaymentType').attr('class');
	if ('T' == paymentType) {
		$('.termWiseField').removeAttr('readonly');
		$('#totalPayAmountAfterDiscount').removeAttr('readonly');
		$(".termWiseChkField").removeAttr("disabled");
	} else {
		$('.partclrWiseField').removeAttr('readonly');
		$('#totalPayAmountAfterDiscount').removeAttr('readonly');
		$(".partclrWiseChkField").removeAttr("disabled");
	}
}

function disbleAllTermsFields() {
	var paymentType = $('#feePaymentType').attr('class');
	if ('T' == paymentType) {
		$('.termWiseField').attr('readonly', 'true');
		$('#totalPayAmountAfterDiscount').attr('readonly', 'true');
		$(".termWiseChkField").attr("disabled", true);
	} else {
		$('.partclrWiseField').attr('readonly', 'true');
		$('#totalPayAmountAfterDiscount').attr('readonly', 'true');
		$(".partclrWiseChkField").attr("disabled", true);
	}
}

function checkAll(type) {
	$('#currentTermDivId').hide();
	$('input[type="submit"]').removeAttr("disabled");
	if(type=='PAT'){
		if($('input#checkAllTerms').is(":checked")){
			
			$("input[name='chkBoxFeeSelectedIds']").attr("checked", "true");
			$("input[name='chkBoxFeeSelectedIds']").parent('span').addClass('checked');
			$('#termsDescountPerm').val('%');
			$('#termDescountAmount').val('Amount');
			calcMultipleTermsDiscPerc(0);
			enableAllTermsFields();
			checkAndUncheckPayAllTerms();
			if ($("input[name='termType']" + ':checked').val() == 'currentTermsIds') {
				checkCurrentForm();
		   }
			getSelectedTermsTotalPaidAmount();
		}else{
		 $("input[name='chkBoxFeeSelectedIds']:checked").each(function() {
		 $(this).attr('checked',false);
		 $(this).parent('span').removeClass('checked');
	     });
		 $('input#totalPayAmountAfterDiscount').val(0);
		}
	}else{
		$('#totalPayAmountDiv').val(totalTermsAmount);
		$("input[name='chkBoxFeeSelectedIds']").each(function() {
		 $(this).attr('checked',false);
		 $(this).parent('span').removeClass('checked');
		 $(this).removeAttr('disabled');
	   });
		$('input#totalPayAmountAfterDiscount').val(0);
		checkAndUncheckPayAllTerms();
	}
}

function getSelectedTermsTotalAmount() {
	var amount = 0;
	var concessionAmount =0;
	var paymentType = $('#feePaymentType').attr('class');
	$("input[name='payableAmount']").each(function() {
						var termIdAndAmt = $(this).attr('title').split('_');
						
						var termTotAmt = parseFloat(termIdAndAmt[1]);
						if (termTotAmt < parseFloat($(this).val())) {
							$(this).val(termTotAmt);
							alert('Amount Payable should be less than or equal to ' + termTotAmt);
						}
						if ('T' == paymentType) {
							if ($('#totalTermAmt_' + termIdAndAmt[0]).is(':checked')&& isNonEmpty($(this).val())) {
								amount += parseFloat($(this).val());
							}
						} else {
							
							if ($('#totalPartclrAmt_' + termIdAndAmt[0] + '_'+ termIdAndAmt[2]).is(':checked')&& isNonEmpty($(this).val())) {
								//alert("termIdAndAmt :"+termIdAndAmt+" concession amount : "+termIdAndAmt[3]+" days pending :"+termIdAndAmt[4]);
								amount += parseFloat($(this).val());
								concessionAmount += parseFloat(termIdAndAmt[3]);
								if(concessionAmount == 0 || isNaN(concessionAmount)) 
									$("#concessionInput").hide();
								else
									$("#concessionInput").show();
							}
						}
					});
	if ($('#excessPay').is(':checked')) {
		var excessAmt = $('span#avbExceeAmt').html();
		if (isNonEmpty(amount) && amount != 0 && isNonEmpty(excessAmt)) {
			excessAmt = parseFloat(excessAmt);
			if (amount >= excessAmt) {
				$('#totalPayAmountAfterDiscount').val(amount - excessAmt);
				$('#remainingExceeAmt').html(0);
			} else {
				$('#totalPayAmountAfterDiscount').val(0);
				$('#remainingExceeAmt').html(excessAmt - amount);
			}
		} else {
			$('#totalPayAmountAfterDiscount').val(amount);
		}
	} else {
		var concessionAmountValue = isNaN(parseInt(concessionAmount)) ? 0 : parseInt(concessionAmount);
		$('#totalPayAmountAfterDiscount').val(amount);
	 	$('#totalConcessionAmount').text(concessionAmountValue);
	}
	if ($('input[name=chkBoxFeeSelectedIds]:checked').length == 0){
		$('#fineFeeAmount').hide();	
	}
	$('#excessAmt').val('');
	return amount;
}

function getSelectedTermsTotalPaidAmount(obj) {
	$('input#totalPayAmountAfterDiscount').val(getSelectedTermsTotalAmount());
	calculatePayableAmount();
	checkAndUncheckPayAllTerms();
	
	if($(obj).is(":checked")) 
	{
		if(!$("#excessPay").is(':checked'))
		{
			var availableExcessAmount= '<s:property value="balance"/>';
			if(isNonEmpty(availableExcessAmount))
			{
				var confirmVal = confirm("Excess amount is available, do you want to use?");
				if(confirmVal)
				{
					calculatePaybleAmountBase(true);
					$("input[name='selectedId']").attr("checked", "true");
					$("input[name='selectedId']").parent('span').addClass('checked');
				}
			}
		}
		
    } else {
    	var feeTypeIdAndPaymentAmt = $(obj).attr('title').split("_");
    	$('#particularDiscPer_' + feeTypeIdAndPaymentAmt[1] + '_' + feeTypeIdAndPaymentAmt[2]).val('');
		$('#particularDiscAmt_' + feeTypeIdAndPaymentAmt[1] + '_' + feeTypeIdAndPaymentAmt[2]).val('');
		$('#partRemainingPayAmt_' + feeTypeIdAndPaymentAmt[1] + '_' + feeTypeIdAndPaymentAmt[2]).val(feeTypeIdAndPaymentAmt[0]);
		enablePartclrPayableAmountFields(feeTypeIdAndPaymentAmt[1], feeTypeIdAndPaymentAmt[2]);
		
		if($("input[name='chkBoxFeeSelectedIds']:checked").length == 0)
		{
			$("input[name='selectedId']").attr("checked", "false");
			$("input[name='selectedId']").removeAttr('checked');
			$("input[name='selectedId']").parent('span').removeClass('checked');
		}
		
    }
	var discAmt = $('#termDescountAmount').val();
	var discPerc = $('#termsDescountPerm').val();
	if(discAmt>0 || discAmt != 'Amount')
		calcDiscAmtForSelectedParticularsByDiscAmt(discAmt)
    else if(discPerc > 0 || discPerc!='%'){
		calcMultipleTermsDiscPerc(discPerc)
    }
	
}

function getBalanceAmountForSelectedPart() {
	var amount = 0;
	$("input[name='chkBoxFeeSelectedIds']:checked").each(function() {
		var termAmtAndtermId = $(this).attr('title').split('_');
		amount += parseFloat(termAmtAndtermId[0]);
	});
	return amount;
}

function paymentTypeMethodChange(clickButton) {
	payMode=clickButton;
	if (clickButton == 'D') {
		$("#inputboxhideText").show();
		$("#checkinputboxhideText").hide();
		$("#bankinputboxhideText").show();
		$("#bankibranchnputboxhideText").show();
		$('#neftinputboxhideText').hide();
		$("#studentChallanNumberDivId").hide();
	} else if (clickButton == 'CH') {
		$("#checkinputboxhideText").show();
		$("#inputboxhideText").hide();
		$("#bankinputboxhideText").show();
		$("#bankibranchnputboxhideText").show();
		$('#neftinputboxhideText').hide();
		$("#studentChallanNumberDivId").hide();
	} else if (clickButton == 'C') {
		$("#inputboxhideText").hide();
		$("#checkinputboxhideText").hide();
		$("#bankinputboxhideText").hide();
		$('#neftinputboxhideText').hide();
		$("#studentChallanNumberDivId").hide();
	}else if (clickButton == 'CS' || clickButton == 'NEFT') {
		$("#inputboxhideText").hide();
		$("#checkinputboxhideText").hide();
		$("#bankinputboxhideText").show();
		$("#bankibranchnputboxhideText").hide();
		$('#neftinputboxhideText').show();
		$("#studentChallanNumberDivId").hide();
	}else if (clickButton == 'CL') {
		$("#inputboxhideText").hide();
		$("#checkinputboxhideText").hide();
		$("#bankinputboxhideText").hide();
		$('#neftinputboxhideText').hide();
		$("#studentChallanNumberDivId").show();
	}
}

function checkCurrentForm() {
	$('#currentTermDivId').hide();
	$('input[type="submit"]').removeAttr("disabled");
	var paymentType = $('#feePaymentType').attr('class');
	$('#termsDescountPerm').val('%');
	$('#termDescountAmount').val('Amount');
	calcMultipleTermsDiscPerc(0);
	enableAllTermsFields();
	var amount = 0;
	var termAmtAndId;
	var currentTermTotAmt = 0;
	$('input[name="chkBoxFeeSelectedIds"]').each(function() {
						termAmtAndId = $(this).attr('title').split('_');
						if ('T' == paymentType) {
							if ($('ul.tooltipDiv  a#' + termAmtAndId[1]).css('color') == 'rgb(255, 0, 0)') {
								currentTermTotAmt += parseFloat(termAmtAndId[0]);
								$(this).attr('checked', 'checked');
								$(this).parent('span').addClass('checked');
							} else {
								$(this).removeAttr('checked');
								$(this).parent('span').removeClass('checked');
								$(this).attr('disabled','disabled');
							}
						} else {
							if ($('tr.partclrTerm  span#' + termAmtAndId[2]).css('color') == 'rgb(255, 0, 0)') {
								currentTermTotAmt += parseFloat(termAmtAndId[0]);
								$(this).attr('checked', 'checked');
								$(this).parent('span').addClass('checked');
							} else {
								$(this).removeAttr('checked');
								$(this).parent('span').removeClass('checked');
								$(this).attr('disabled','disabled');
							}
						}
					});
	checkAndUncheckPayAllTerms();
	$('#totalPayAmountDiv').val(currentTermTotAmt);
	$('#totalPayAmountAfterDiscount').val(currentTermTotAmt);
	
	$('#excessAmt').val('');
	
	if(currentTermTotAmt == 0)
	{
		$('#currentTermDivId').show();
		$('input[type="submit"]').attr('disabled','disabled');
	}
}

function onChangeTotal(amount) {
	var paymentType = $('#feePaymentType').attr('class');
	var payableAmount = 0;
	if (isNonEmpty(amount)) {
		payableAmount = parseFloat(amount);
	}
	var excessAmt = $('span#avbExceeAmt').html();
	$("input[name='payableAmount']").each(function() {
				if ('T' == paymentType) {
					//if($("input[name='chkBoxFeeSelectedIds']:checked").length
					if($("input[name='chkBoxFeeSelectedIds']:checked").length > 0){
						var termIdAndAmt = $(this).attr('title').split('_');
						var termTotAmt = parseFloat(termIdAndAmt[1]);
						if ($('#totalTermAmt_' + termIdAndAmt[0]).is(':checked')) {
							if (payableAmount != 0) {
								if (termTotAmt >= payableAmount) {
									$(this).val(payableAmount);
									payableAmount = 0;
								} else {
									$(this).val(termTotAmt);
									payableAmount = payableAmount - termTotAmt;
									$('#excessPay').removeAttr('checked');
									$('#excessPay').parent('span').removeClass('checked');
								}
							} else {
								$(this).val(termTotAmt);
								$('#totalTermAmt_' + termIdAndAmt[0]).removeAttr('checked');
								$('#totalTermAmt_' + termIdAndAmt[0]).parent('span').removeClass('checked');
							}
						}
					}else{
						payableAmount='';
					}	/*else{
						if ($("input[name='termType']" + ':checked').val() == 'currentTermsIds') {
						   if ($('ul.tooltipDiv  a#' + termIdAndAmt[1]).css('color') == 'rgb(255, 0, 0)') {
								if (payableAmount != 0) {
									if (termTotAmt >= payableAmount) {
										$(this).val(payableAmount);
										payableAmount = 0;
									} else {
										$(this).val(termTotAmt);
										payableAmount = payableAmount - termTotAmt;
									}
									$('#totalTermAmt_' + termIdAndAmt[0]).attr('checked',true);
									$('#totalTermAmt_' + termIdAndAmt[0]).parent('span').addClass('checked');
								}
							 }
					     }
						else{
							 if (payableAmount != 0) {
								if (termTotAmt >= payableAmount) {
									$(this).val(payableAmount);
									payableAmount = 0;
								} else {
									$(this).val(termTotAmt);
									payableAmount = payableAmount - termTotAmt;
								}
								$('#totalTermAmt_' + termIdAndAmt[0]).attr('checked',true);
								$('#totalTermAmt_' + termIdAndAmt[0]).parent('span').addClass('checked');
							 }
						}
					}*/
				} else {
					var partclrIdAndAmt = $(this).attr('title').split('_');
					var partclrTotAmt = parseFloat(partclrIdAndAmt[1]);
					if($("input[name='chkBoxFeeSelectedIds']:checked").length > 0){
						if ($('#totalPartclrAmt_' + partclrIdAndAmt[0] + '_'+ partclrIdAndAmt[2]).is(':checked')) {
							if (payableAmount != 0) {
								if (partclrTotAmt >= payableAmount) {
									$(this).val(payableAmount);
									payableAmount = 0;
								} else {
									$(this).val(partclrTotAmt);
									payableAmount = payableAmount - partclrTotAmt;
									$('#excessPay').removeAttr('checked');
									$('#excessPay').parent('span').removeClass('checked');
								}
							} else {
								$(this).val(partclrTotAmt);
								$('#totalPartclrAmt_' + partclrIdAndAmt[0]+ '_' + partclrIdAndAmt[2]).removeAttr('checked');
								$('#totalPartclrAmt_' + partclrIdAndAmt[0]+ '_' + partclrIdAndAmt[2]).parent('span').removeClass('checked');
							}
						}
					}else{
						payableAmount='';
					}
					
					/*else{
						if ($("input[name='termType']" + ':checked').val() == 'currentTermsIds') {
						  if ($('tr.partclrTerm  span#' + partclrIdAndAmt[2]).css('color') == 'rgb(255, 0, 0)') {
								if (payableAmount != 0) {
									if (partclrTotAmt >= payableAmount) {
										$(this).val(payableAmount);
										payableAmount = 0;
									} else {
										$(this).val(partclrTotAmt);
										payableAmount = payableAmount - partclrTotAmt;
									}
								$('#totalPartclrAmt_' + partclrIdAndAmt[0]+ '_' + partclrIdAndAmt[2]).attr('checked',true);
							    $('#totalPartclrAmt_' + partclrIdAndAmt[0]+ '_' + partclrIdAndAmt[2]).parent('span').addClass('checked');
								}
							 }
					     }else{
							 if (payableAmount != 0) {
								if (partclrTotAmt >= payableAmount) {
									$(this).val(payableAmount);
									payableAmount = 0;
								} else {
									$(this).val(partclrTotAmt);
									payableAmount = payableAmount - partclrTotAmt;
								}
								$('#totalPartclrAmt_' + partclrIdAndAmt[0]+ '_' + partclrIdAndAmt[2]).attr('checked',true);
								$('#totalPartclrAmt_' + partclrIdAndAmt[0]+ '_' + partclrIdAndAmt[2]).parent('span').addClass('checked');
							}
					    }
					}*/
				}
			});
			checkAndUncheckPayAllTerms();
	      $('#excessAmt').val(payableAmount);
	      $('#remainingExceeAmt').html(0);
	      //$('input#totalPayAmountDiv').val(getBalanceAmountForSelectedPart());
}
function checkAndUncheckPayAllTerms(){
	if($("input[name='chkBoxFeeSelectedIds']:checked").length == $("input[name='chkBoxFeeSelectedIds']").length){
				$('input#checkAllTerms').attr('checked',true);
				$('input#checkAllTerms').parent('span').addClass('checked');
			}else{
				$('input#checkAllTerms').attr('checked',false);
				$('input#checkAllTerms').parent('span').removeClass('checked');
			}
}

var applicationId = '<s:property value="onlineApplicationDetails.classId.id"/>';
if(!isNonEmpty(applicationId))
	applicationId = 0;
$("#studAdmissionNumber").unbind('#studAdmissionNumber').autoCheck("${pageContext.request.contextPath}/admin/ajaxCheckAdmissionNumberAvailableOrNotForAdmissions.do?selectedId="+applicationId,
		{
			minChars : 1,
			min : "no",
		});
$('a#doCancelStudent2').click(function() {
	window.location.hash = "target=ESI.ajaxify SMP";
	$('li#searchStudentsForMakePaymentTest').removeClass('active');
	$('li#challanaDivId').addClass('active');
	$("#searchStudentsForm112").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$('li#challanaDivId a').click();
});

var onlineApplicationDetailsId = '<s:property value="onlineApplicationDetails.id"/>';
if(isNonEmpty(onlineApplicationDetailsId))
{
	$("#studentChallanNumberDivId").hide();
}
 $('input[type="checkbox"]').hover( 
  function() {
        $(this).attr("org_title", $(this).attr('title'));
        $(this).attr('title', '');
  }, function() {
        $(this).attr('title', $(this).attr("org_title"));
  }
); 
</script>