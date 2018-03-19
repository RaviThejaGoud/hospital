<%@ include file="/common/taglibs.jsp"%>
	<div class="block_content">
			<label>
				<b>Select Collections:</b>
			</label>
		<div>
			<sj:radio list="#{'BC':'By Class','ES':'Entair School','BM':'By Mounths'}" onclick="ajaxDoCollectionRangeReprots(this.value);" id="collectionRangeOrder" name="collectionRange" cssClass="radio required" required="true" label="Gender"/>			
		</div>
	</div>
<script type="text/javascript">
function ajaxDoCollectionRangeReprots(collectionRangeOrder) {
alert()"hello";
	var pars = "collectionRangeOrder=" + collectionRangeOrder;
	var url = jQuery.url.getChatURL("/admin/ajaxDoCollectionByRangeOrders.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			if ("ROLE_PRINCIPAL" == staffType) {
				$("#staffTypeDiv").html("");
			} else {
				$("#staffTypeDiv").html(html);
			}
		}
	});
}
</script>

