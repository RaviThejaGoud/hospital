<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" tabindex="-1" class="modal fade modal-overflow in" 
	style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			View Knowledge Videos
		</h4>
	</div>
	<div class="modal-body">
		 <body onload="javascript:writeNewVideoDisplay('<s:property value="playListVideo.youtubeId"/>')" />
				<div id="mainImageStream"></div>
		</body>
	</div>
</div>
<script type="text/javascript">
 var embed = '<s:property value="playListVideo.youtubeId"/>';
 //alert(embed);
 writeNewVideoDisplay(embed);
 function writeNewVideoDisplay(embed) {
	height = 340;
	var flashObject = "";
	if (embed != null) {
		flashObject = "<iframe title=\"YouTube video player\" width=\"700\" height=\"410\" src=\"http://www.screencast.com/t/RDz6n7Vv"
				+ embed + "\" frameborder=\"0\" allowfullscreen></iframe>";
				//alert(flashObject)
		document.getElementById("mainImageStream").innerHTML = flashObject;
	}
}  
</script>