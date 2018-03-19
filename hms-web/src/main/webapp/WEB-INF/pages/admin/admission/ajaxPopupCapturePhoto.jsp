<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jQuery/webcam/swfObjectJs.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jQuery/webcam/scriptcam.js"> </script>
<div class="modal fade modal-overflow in" style="display: block; width: 400px;height:382px; margin-left: -140px; margin-top: 150px;" aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="bold pagetitle">
			 Capture Image
		</h4>
	</div>
	<div class="modal-body">
		<div id="photo">
			<div id="webcam"></div>
			<div>
				<button class="btn btn-small" id="btn2" onclick="base64_toimage()">
					Capture Image
				</button>
			</div>
		</div>
	</div>
</div>
<script language="JavaScript"> 
var imageId='';
	$(document).ready(function() {
	imageId= $('a.open').attr('id');
		$("#webcam").scriptcam({
			showMicrophoneErrors:false,
			onError:onError,
			cornerRadius:50,
			cornerColor:'e3e5e2',
			onWebcamReady:onWebcamReady
		});
	});
	function base64_tofield() {
		$('#formfield').val($.scriptcam.getFrameAsBase64());
	};
	function base64_toimage() {
		var image = $.scriptcam.getFrameAsBase64();
		if(isNonEmpty(imageId)){
		$('#image'+imageId).attr("src","data:image/png;base64,"+image);
		$('#image'+imageId).show();
		}else{
		$('#image').attr("src","data:image/png;base64,"+image);
		$('#image').show();
		}
    	var customerImageSrc = image;
    	$('#customerImage').val(customerImageSrc)
    	$('#browseImage').hide();
    	$('button.close').click();
    	//$('#image').show();
	};
	function base64_tofield_and_image(b64) {
		$('#formfield').val(b64);
		if(isNonEmpty(imageId)){
		$('#image'+imageId).attr("src","data:image/png;base64,"+b64);
		}else{
		$('#image').attr("src","data:image/png;base64,"+b64);
		}
	};
	function changeCamera() {
		$.scriptcam.changeCamera($('#cameraNames').val());
	}
	function onError(errorId,errorMsg) 
	{
		$('#webCamDivId').hide();
		if($( "#btn2" ).attr( "disabled", true )){
			$("#photo").hide();
			$('form.reservationForm').append('<input type="hidden" name="isDetectCamera" value="0" />');
			alert(errorMsg);
		}
		else{
		$("#photo").show();
		}
	}			
	function onWebcamReady(cameraNames,camera,microphoneNames,microphone,volume) 
	{
		$('form.reservationForm').append('<input type="hidden" name="isDetectCamera" value="1" />');
		$.each(cameraNames, function(index, text) {
			$('#cameraNames').append( $('<option></option>').val(index).html(text) )
		}); 
		$('#cameraNames').val(camera);
	}
	$('button.close').click(function(){
	 $('a.open').removeClass('open');
	})
</script> 