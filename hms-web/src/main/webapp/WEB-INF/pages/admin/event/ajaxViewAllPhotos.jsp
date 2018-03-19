<%@ include file="/common/taglibs.jsp"%><div class="row">
<div class="col-md-12">
		<div class="tab-content">
		<div class="col-md-12">
			<s:url id="viewEventPhotos" action="ajaxViewEventPhotos" namespace="/admin"> </s:url>
			<sj:a href="%{viewEventPhotos}" targets="eventsContentDiv"  cssClass="btn default" style="float:right;margin-right:20px;" ><i class="m-icon-swapleft"></i>&nbsp;Back To Album</sj:a>
		</div>
		<div class="spaceDiv">&nbsp;</div>
			<div class="tab-pane active" id="tab_1">
				<div class="margin-top-10">
					<div class="row mix-grid">
					
					<jsp:include page="/common/messages.jsp" />
					
						<s:if test="%{eventsAlbum.albumAttachment != null && !eventsAlbum.albumAttachment.isEmpty()}"> 
							<s:iterator value="eventsAlbum.albumAttachment"> 
								<div class="col-md-3 col-sm-4 mix category_2 category_1">
									<div class="mix-inner"   style="height:140px;">
										<a rel="gallery"  class="fancybox" href="<c:url value='${hrefAttachmentFilePath}'/>"> <img src='<c:url value="${hrefAttachmentFilePath}"/>' class="img-responsive" style="height: 150px;width:290px;"/></a>
										<div class="mix-details" style="background: #008CBA; none repeat scroll 0 0;color: #555;cursor: pointer;display: block;margin-top: 10px; margin-left:;margin-top: -5em;padding: 12px 15px;position: absolute;">
											<h4> <s:property value="eventsAlbum.albumName" /></h4>
											<s:url id="removePhotos" action="ajaxDeletePhotos"
											includeParams="all" escapeAmp="false" namespace="/admin">
											<s:param name="anyId" value="%{id}"></s:param>
										</s:url> 
										<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
										<s:div class="mix-link" cssStyle=" margin-right: 5px; right: 50%;background: none repeat scroll 0 0 red;color: #fff;cursor: pointer;display: block;margin-top: 10px;padding: 10px 15px;position: absolute;"
											onclick="javascript:confirmDialogWithTarget(this,'eventsContentDiv');"
											id='%{removePhotos}' theme="simple"
											title="Delete this Photo">
											<i class="fa fa-trash-o"></i> Delete
										</s:div>
										</s:if>
											<a rel="gallery" class="mix-preview fancybox-button fancybox" href="<c:url value='${hrefAttachmentFilePath}'/>"> <i class="fa fa-search"></i>&nbsp;View</a>
										</div>
									</div>
								</div>
						    </s:iterator>
						    </s:if>
						    <s:else>
								<div class="alert alert-info">
									Currently there are no event album.
								</div>
							</s:else>
					</div>
				</div>
			</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready( function() {
	Portfolio.init();
	changePageTitle("View All event photos");
	
	$("#eventULId").removeClass("active");
	$( "#albumULId" ).addClass( "active" );
	$( "#addAlbumLiId" ).addClass( "active" );
	
	
}); 
$(".fancybox").fancybox({
    helpers : {
        title: {
            type: 'inside',
            position: 'top'
        }
    },
    nextEffect: 'fade',
    prevEffect: 'fade'
});
</script>
