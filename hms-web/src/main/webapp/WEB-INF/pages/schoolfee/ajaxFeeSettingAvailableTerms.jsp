<%@ include file="/common/taglibs.jsp"%>
	<s:if test="%{schoolTermsList != null && !schoolTermsList.isEmpty()}">
		<b>Clone Term & Fee Particulars from :</b>
	<div class="spaceDiv"></div>
	<table
		class="table table-bordered table-striped table-condensed flip-content">
		<thead class="flip-content">
			<tr>
				<th>
					Term Type
				</th>
				<th>
					StartDate
				</th>
				<th>
					EndDate
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="schoolTermsList">
				<tr>
					<td>
						<input type="radio" name="termDetails_" id="checkRadioTermName" onclick="javascript:validateTerms(this.value);"
							value='<s:property value="id"/>' />
						<b><s:property value="termName" /> </b>
					</td>
					<td>
						<s:property value='fromDateStr' />
					</td>
					<td>
						<s:property value='toDateStr' />
					</td>
				</tr>
				<div class="termDates grid_1">
					<span id="termStartDateSpan"
						class="<s:property value='termFromDateStr'/>"></span>
					<span id="termEndDateSpan"
						class="<s:property value='termToDateStr'/>"></span>
				</div>
			</s:iterator>
		</tbody>
	</table>
	<span>(Select the any of the above term to copy (clone) all setting to current terms)</span>
		<s:if test="schoolFeeSetting.settingType == true ">
		<div class="form-group">
					<label class="col-md-4 control-label">
						Is this term applicable only for new students :
					</label>
					<p class="form-control-static">
						<s:checkbox name="schoolTerms.applToNewStuds" />
					</p>
				</div>
		</s:if>
	</s:if>
<script type="text/javascript">
$(document).ready(function(){
	TableAdvanced.init();
 	$("input:checkbox, input:radio").uniform();
}) ;

function validateTerms() {
	 var checked = $(this).attr('checked', true);
	  if(checked){ 
		  $('div#showClassAndSetions').show();
	  }
	  else{ 
		  $('div#showClassAndSetions').hide();
	  }
}
	
</script>
