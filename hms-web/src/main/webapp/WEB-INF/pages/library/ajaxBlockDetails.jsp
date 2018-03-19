<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Block Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<s:if test='%{#session.previousYear == "N"}'>
						<ul class="nav nav-tabs">
							<li class="active">
								<s:url id="manageRacks" action="ajaxDoBlockDetails"
									namespace="/library">
								</s:url>
								<sj:a href="%{manageRacks}" targets="mainContentDiv" data-toggle="tab">
										View Blocks & Racks</sj:a>
							</li>
						</ul>
					</s:if> 
				<div id="resultsDiv1" class="tab-content">
					<jsp:include
						page="/WEB-INF/pages/library/block/ajaxAllBloks.jsp" />
				</div>
			</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function resetBlockName(){
	var blockName = $("#blockName").val('');
	return false;
}
function addBlockName() {
	var blockName = $("#blockName").val();
	var url = jQuery.url.getChatURL("/library/ajaxAddBlock.do");
	if (blockName.length == 0) {
		 alert("Please enter block name.");
		 return false;
	} else {
		$("#resultsDiv1")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "blockName=" + blockName;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#resultsDiv1").html(html);
				document.getElementById('resultsDiv1').style.display = "block";
				$('input#blockName').val('');
			}
		});
	}
}

$(document).ready(function() {
	$('.numeric').numeric(); 
});
</script>
