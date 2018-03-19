<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width"
		id="sample_2">
		<thead>
			<tr>
				<th>
					Name
				</th>
				<th>
					Number
				</th>
				<th>
					Insurance
				</th>
				<th>
					Pollution
				</th>
				<th>
					Fitness
				</th>
				<th>
					Permit
				</th>
				<th>
					RoadTax
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="tempList">
				<tr>
					<td>
						<s:property value="Name" />
					</td>
					<td>
						<s:property value="vehicleNumber" />
					</td>
					<td>
						<s:if test="%{insuranceDays <= numberOfDays}">
							<s:property value="insuranceExpiredDate" />
						</s:if>
						<s:else>
							&nbsp; -
						</s:else>
					</td>
					<td>
						<s:if test="%{pollutionDays <= numberOfDays}">
							<s:property value="pollutionExpiryDate" />
						</s:if>
						<s:else>
							&nbsp; -
						</s:else>
					</td>
					<td>
						<s:if test="%{fitnessDays <= numberOfDays}">
							<div class="grid_2">
								<s:property value="fitnessExpiryDate" />
							</div>
						</s:if>
						<s:else>
							&nbsp; -
						</s:else>
					</td>
					<td>
						<s:if test="%{permitDays <= numberOfDays}">
							<s:property value="permitExpiryDate" />
						</s:if>
						<s:else>
							&nbsp; -
						</s:else>
					</td>
					<td>
						<s:if test="%{roadTaxDays <= numberOfDays}">
							<s:property value="roadTaxNextPaymentDate" />
						</s:if>
						<s:else>
							&nbsp; -
						</s:else>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no transport warnings
	</div>
</s:else>
<script type="text/javascript">
	TableAdvanced.init();
	$('button.close').click();
</script>