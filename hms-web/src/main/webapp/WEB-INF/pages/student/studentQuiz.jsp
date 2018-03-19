<%@ include file="/common/taglibs.jsp"%>
<div class="noticeboard">
	<marquee DIRECTION="left" scrollamount="2" vspace="0px">
		<b><font color="#FFCC00"><s:property value="anyTitle" /> </font><font
			color="#66FF66"><s:property value="selectedId" /> </font> </b>
	</marquee>
	<s:if test="%{alertsList != null && !alertsList.isEmpty()}">
		<marquee DIRECTION="up" scrollamount="2" vspace="0px">
			<div>
				<s:iterator value="alertsList">
					<s:property value="title" />
					<br />
							<s:url id="doGetMyInbox"  action="ajaxDoGetSchoolWideAlertsList" namespace="/common">
							</s:url>
							<sj:a  href="%{doGetMyInbox}" targets="mainContentDiv" data-toggle="tab">More...</sj:a> 	
					<!--<a
						href="${pageContext.request.contextPath}/common/doGetMyInbox.do?value=dashBoard"
						id="commonInbox">More...</a>
					--><br />
				===============<br />
				</s:iterator>
			</div>
		</marquee>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Currently there are no Upcoming Alerts.
		</div>
	</s:else>
</div>
<!--
<script type="text/javascript">
function shortDescWithLink(source, linkName, limit, searchWord) {
	var obj = $(source);
	if (obj) {
		var trunc = removeHTMLTags(obj.html());
		if (trunc.length > limit) {
			/*
			 * Truncate the content of the P, then go back to the end of the
			 * previous word to ensure that we don't truncate in the middle
			 * of a word
			 */
			trunc = trunc.substring(0, limit);
			trunc = trunc.replace(/\w+$/, '');
			/*
			 * Add an ellipses to the end and make it a link that expands
			 * the paragraph back to its original size
			 */
			trunc += '<a href="' + linkName;
			trunc += '" style="text-decoration:none">' + '.... more</a>';
		}

		$('<div>' + trunc + '</div>').insertAfter(obj);
		obj.remove();
	}
}
function readMoreAlertTitle(id) {
	$("#ajaxResponseDiv")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars = "id=" + id;
	$.ajax( {
		url : jQuery.url.getChatURL("/common/ajaxReadMoreAlerts.do"),
		cache : false,
		data : pars,
		success : function(html) {
			$("#caseStudy").html(html);
			$('#formattedMsg').html($('#unFormattedMsg').text());
		}
	});
}
</script>-->