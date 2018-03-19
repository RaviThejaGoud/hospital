<%@ include file="/common/taglibs.jsp"%>
	<div class="block_content">
			<label>
				<b>Select Collections:</b>
			</label>
		<div>
			<sj:radio list="#{'TD':'To Day','DR':'DefaulRange','AY':'Academic Year'}" onclick="ajaxCollectionRangeReprots(this.value);" id="collectionRange" name="collectionRange" cssClass="radio required" required="true" label="Gender"/>			
		<!--<form name="myform"
			action="${pageContext.request.contextPath}/reports/printViewClassSectionDefaulterReports.do">
			
			<div class="alpha omega" id="searchNeighborhood"
				style="font-size: 14px; line-height: 25px; margin-bottom: 10px; font-weight: bold;">
				<div class="grid_3 alpha" >
					<b> Select Staff Type </b>
				</div>
				<input type="radio" value="teaching"
					onclick="changeQualification(this.value);" name="addGroupLeader"
					class="radio" checked="checked">
				Teaching
				<input type="radio" value="nonTeaching"
					onclick="changeQualification(this.value);" name="addGroupLeader"
					class="radio">
				Non Teaching
			</div>
			
			<s:select list="classList" listKey="id" listValue="className"
				cssClass="required" required="true" id="selectedId" theme="css_xhtml"
				name="selectedId" headerKey="" headerValue="- Select Class -"
				onchange="javascript:onClassSectionDetailsChange(this.value);">
			</s:select>
		</form>
		--></div>
	</div>
<script type="text/javascript">
function ajaxCollectionRangeReprots(collectionRange) {
	var pars = "collectionRange=" + collectionRange;
	var url = jQuery.url.getChatURL("/admin/ajaxDoCollectionByRangeOrders.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
				$("#schoolFeeReports").html(html);
		}
	});
}
</script>

