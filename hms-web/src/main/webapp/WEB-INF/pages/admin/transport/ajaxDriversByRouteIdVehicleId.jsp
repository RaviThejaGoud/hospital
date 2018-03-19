<%@ include file="/common/taglibs.jsp"%>


<div class="row" id="driverAndHelperDivId">
			<div class="col-md-6" id="driverParentDivId">
				<div class="form-group">
					<label class="control-label col-md-3" id="driverParentLabelId">
						<span class="required">*</span>Select Driver :
					</label>
					<div class="col-md-9" id="driverChildId">
						<s:select id="driverId" list="selectboxMap" 
						headerKey="" headerValue="- Select -"  cssClass="required form-control input-medium"
						name="vehicleAcademicDetails.driverId" />
						<!--<s:select list="driverList" listKey="accountId" id="driverId"
							listValue="personFirstLastNameOnly"
							cssClass="alphabet required form-control input-medium"
							name="vehicleAcademicDetails.driverId" headerKey=""
							headerValue="- Select -">
						</s:select>
					-->
					</div>
				</div>
			</div>
			<div class="col-md-6" id="helperParentDivId">
				<div class="form-group">
					<label class="control-label col-md-3" id="helperParentLabelId">
						Select Helper :
					</label>
					<div class="col-md-9" id="helperChildId">
					<s:select id="helperId" list="jsonResult" 
						headerKey="" headerValue="- Select -"  cssClass="form-control input-medium"
						name="vehicleAcademicDetails.helperId" />
						<!--<s:select list="helperList" listKey="accountId" id="helperId"
							listValue="personFirstLastNameOnly"
							cssClass="alphabet form-control input-medium"
							name="vehicleAcademicDetails.helperId" headerKey="0"
							headerValue="- Select -">
						</s:select> -->
					</div>
				</div>
			</div>
		</div>

