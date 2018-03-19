<%@ include file="/common/taglibs.jsp"%>
<body
	onload="javascript:writeNewVideo('<s:property value="playListVideo.youtubeId"/>')" />

	<div class="header">
		Play Video
	</div>
	<table>
		<tr>
			<td colspan="2" align="center">
				<div id="mainImageStream"></div>
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">

var embed = '<s:property value="playListVideo.youtubeId"/>';
writeNewVideo(embed);

function writeNewVideo(embed) {
	height = 340;
	var flashObject = "";
	if (embed != null) {
		flashObject = "<iframe title=\"YouTube video player\" width=\"640\" height=\"390\" src=\"http://www.youtube.com/embed/"
				+ embed + "\" frameborder=\"0\" allowfullscreen></iframe>";
		document.getElementById("mainImageStream").innerHTML = flashObject;
	}
	document.getElementById("mainImageStream").style.height = 340;
	document.getElementById("mainImageStream").style.width = 585;
}
</script>