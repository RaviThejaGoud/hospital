<%@ include file="/common/taglibs.jsp"%>
<s:form action="ajaxGetDistrictCommodityDetails" theme="simple" id="mandalCommodityDetails" 
	method="post" cssClass="form-horizontal" namespace="/fps" >
	<div class="form-body">
	<!--	<div id="monthDiv" class="form-horizontal">
			-->
			<!--<div class="col-md-12">
				--><div class="form-group">
					<label class="control-label col-md-2">
						<span class="required">*</span>From :
					</label>
					<div class="col-md-7">
					<div class="col-md-3">
						 <select name="fromMonth" id="fromMonth" onchange="" size="1"  class="form-control input-small">
						 	<option value="0">- Month -</option>
						    <option value="1">January</option>
						    <option value="2">February</option>
						    <option value="3">March</option>
						    <option value="4">April</option>
						    <option value="5">May</option>
						    <option value="6">June</option>
						    <option value="7">July</option>
						    <option value="8">August</option>
						    <option value="9">September</option>
						    <option value="10">October</option>
						    <option value="11">November</option>
						    <option value="12">December</option>
						</select>
						</div>
						<div class="col-md-5">
						 <select name="fromYear" id="fromYear" onchange="" size="1"  class="form-control input-small">
						 	<option value="0">- Year -</option>
							<option value="2014">2014</option>
							<option value="2015">2015</option>
							<option value="2016">2016</option>
							<option value="2017">2017</option>
						</select>
						</div>
					</div>
				</div>
			<!-- </div>  -->
			<!--<div class="col-md-12">
				--><div class="form-group">
					<label class="control-label col-md-2">
						<span class="required">*</span> To :
					</label>
					<div class="col-md-7">
					<div class="col-md-3">
						 <select name="toMonth" id="toMonth" onchange="" size="1"  class="form-control input-small">
						 	<option value="0">- Month -</option>
						    <option value="1">January</option>
						    <option value="2">February</option>
						    <option value="3">March</option>
						    <option value="4">April</option>
						    <option value="5">May</option>
						    <option value="6">June</option>
						    <option value="7">July</option>
						    <option value="8">August</option>
						    <option value="9">September</option>
						    <option value="10">October</option>
						    <option value="11">November</option>
						    <option value="12">December</option>
						</select>
						</div>
						<div class="col-md-5">
						 <select name="toYear" id="toYear" onchange="" size="1" class="form-control input-small">
						 	<option value="0">- Year -</option>
						 	<option value="2014">2014</option>
							<option value="2015">2015</option>
							<option value="2016">2016</option>
							<option value="2017">2017</option>
						</select>
						</div>
					</div>
				</div>
		<!-- 	</div>
		</div>
		--><div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit targets="transactionalDistrict" value="Submit" 
				indicator="indicator" cssClass="submitBt btn blue" validate="true" />
			</div>
		</div>
	</div>
</s:form>
<div id="transactionalDistrict">
	<jsp:include page="/WEB-INF/pages/fps/ajaxDistrictCommodityList.jsp"></jsp:include>
</div>
