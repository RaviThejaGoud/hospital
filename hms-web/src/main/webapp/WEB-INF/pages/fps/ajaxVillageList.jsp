<%@ include file="/common/taglibs.jsp"%>
	<div class="form-body">
		<div class="form-group">
			<label class="control-label col-md-2">
				Select Village :
			</label>
			<div class="col-md-3">
				<s:select list="villageList" listKey="id"
					listValue="villageName" theme="simple" onchange="javascript:getMonthDetails(this.value);"
					cssClass="required form-control" name="villageId"
					headerKey="S" headerValue="- Select -" cssStyle="width: 100%">
				</s:select>
			</div>
		</div>
		<div id="monthDiv" style="display: none">
			<div class="form-group">
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
			<div class="form-group">
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
		</div>
	</div>
<script type="text/javascript">
	function getMonthDetails(selectedId){
		if (selectedId != 'S') {
			$('#monthDiv').show();
		} else{
			$('#monthDiv').hide();
		}
	}
</script>
	