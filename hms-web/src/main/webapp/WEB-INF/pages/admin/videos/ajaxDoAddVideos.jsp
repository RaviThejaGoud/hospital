<%@ include file="/common/taglibs.jsp"%>
 
					<div id="videosContentDiv" class="tab-content">
						<s:form id="addVideoId" action="ajaxAddVideos" method="post"
								theme="simple" cssClass="form-horizontal" namespace="/common">
								<div class="form-body">
								<s:hidden name="schoolVideos.id" value="%{schoolVideos.id}" />
								<!-- <p>
									<span class="label label-danger"> NOTE : </span>&nbsp; From the below screen you can buy the sms through online.
								</p> -->
								<div class="form-group">
									<label class="control-label col-md-2"><span class="required">*</span> Title : </label>
									<div class="col-md-4">
										<sj:textfield name="schoolVideos.title" id="noOfsms"
											cssClass="form-control required numeric" maxlength="200"></sj:textfield>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-2"><span class="required">*</span>YouTube Embed code : </label>
									<div class="col-md-4">
										<sj:textarea rows="3" cols="20" name="schoolVideos.embedCode"  cssClass="form-control" id="code"></sj:textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-2"><span class="required">*</span> Display to : </label>
									<div class="col-md-4">
										<s:select name="schoolVideos.receiverType" cssClass="form-control required" id="msgReceiverType"
												list="#{'A':'ALL','P':'Parents Only','S':'Staff Only','ST':'Student Only'}"></s:select>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-2 col-md-9">
										<sj:submit value="Submit" cssClass="submitBt btn blue"
											onBeforeTopics="addVideosForm" formIds="addVideoId"
											targets="mainContentDiv" indicator="indicator" validate="true" />
											
											<s:url id="viewVideos" action="ajaxDoViewVideos" namespace="/common"></s:url>
											<sj:a href="%{viewVideos}" targets="mainContentDiv" indicator="indicator" cssClass="btn default"> Cancel</sj:a>
									</div>
								</div>
								</div>
							</s:form>
					</div>
			 
<script type="text/javascript">
	$(document).ready(function(){
		$.destroyTopic('addVideosForm'); 
		changePageTitle("Add Videos");
	});
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});

$.subscribe('addVideosForm', function(event, data) {
	var code=$('textarea#code').val();
	if(!isNonEmpty(code)){
		alert('Please enter youTube embed code');
		event.originalEvent.options.submit=false;
	}
	if ($('#addVideoId').valid()){
			return true;
	}else{
		event.originalEvent.options.submit=false;
	}
}); 
</script>