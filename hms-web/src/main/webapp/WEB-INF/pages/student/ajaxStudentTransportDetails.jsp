<%@ include file="/common/taglibs.jsp"%>
<div class="block grid_12" style="margin-top: 10px;">
	<div class="block_head">
		<h2>
			Transport Details
		</h2>
	</div>
	<div class="block_content">
		<s:if test='%{student.transportMode == "ST"}'>
			<div>
				<b>Transport Mode</b>
				<s:url id="getSchoolTransport" action="ajaxLoadTransportByStatus"
					includeParams="all" escapeAmp="false" namespace="/student">
					<s:param name="type" value="%{'ST'}"></s:param>
				</s:url>
				<sj:a href="%{getSchoolTransport}" indicator="indicator"
					targets="schoolTransportInfo"
					onCompleteTopics="getSchoolTransportInfo" buttonIcon="ui-icon-plus">
			(+ <s:property value="student.transportModeDec" />)
	</sj:a>
			</div>
			<div id="schoolTransportInfo" style="display: none;" class='load'
				style="margin: 0px 0px 0px 3px;display: none;">
			</div>

			<br clear="all" />
			<br clear="all" />
		</s:if>
		<s:if test='%{student.transportMode == "O"}'>
			<div>
				<b>Transport Mode</b>
				<s:url id="getOwnTransport" action="ajaxLoadTransportByStatus"
					includeParams="all" escapeAmp="false">
					<s:param name="type" value="%{'O'}"></s:param>
				</s:url>
				<sj:a href="#">
			(<s:property value="student.transportModeDec" />)
	</sj:a>
			</div>
			<br clear="all" />
			<br clear="all" />
		</s:if>
		<s:if test='%{student.transportMode == "P"}'>
			<div>
				<b>Transport Mode</b>
				<s:url id="getPrivateTransport" action="ajaxLoadTransportByStatus"
					includeParams="all" escapeAmp="false">
					<s:param name="type" value="%{'P'}"></s:param>
				</s:url>
				<sj:a href="#">
			(<s:property value="student.transportModeDec" />)
	</sj:a>
			</div>
		</s:if>
	</div>
</div>
<script type="text/javascript">
$.subscribe('getSchoolTransportInfo', function(event, data) {

	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});
$(document).ready(function() {
	changePageTitle("Student Transport");
	});
</script>