<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<div>
		<div class="tableactions" style="padding-bottom:0px;">
		<input type="text" />
			</div>
			<div class="grid_11">
			&nbsp;
			</div>
		<div class="tableactions" style="padding-bottom:0px;">
		<input type="text" />
			</div>
		<s:else>Currently there are no Driver.</s:else>
	</div>

<style type="text/css">
.active
{
color:#0033CC;
text-decoration:none;
}
.inactive
{
font-weight: bold;
text-decoration: underline; 
cursor:default;
}
.paginator
{
text-align: right;
color: #FFF;
}
</style>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/subscription/paginator.js"></script>
	<script type="text/javascript">
	$(function(){ $("#driverResultsPage").pagination(); });
	</script>