<%@ include file="/common/taglibs.jsp"%><div class="row">
	<div class="col-md-12">
		<div class="tab-content">
			
				<div class="col-md-12"><jsp:include
						page="/common/messages.jsp" /></div>
				<div class="tab-pane active" id="tab_1">
					<!-- BEGIN FILTER -->
					<div class="margin-top-10">
						<div class="row mix-grid">
						
						
						<div class="row">
							<div class="col-md-6">
								<!-- <h4 class="pageTitle bold">Update Album</h4> -->
							</div>
							<div class="col-md-6">
								<s:if test='%{ user.isSchoolTeacher=="Y" || user.isSchoolAsstStaff=="Y" || user.isSchoolLibrarian=="Y" || user.isSchoolHostel=="Y" || user.isSchoolClerk=="Y" || user.isSchoolTransport=="Y" || user.isTransportFinance=="Y" || user.isHostelFinance=="Y"}'>
									<s:url id="backToViewEventsId" action="ajaxStaffEvents"
										includeParams="all" escapeAmp="false" namespace="/staff">
									</s:url>
									<sj:a href="%{backToViewEventsId}" cssClass="btn btn-xs purple"
										targets="mainContentDiv">
										<!-- <i class="fa fa-edit"></i> --> Back
										</sj:a>
								</s:if>
								
								<s:elseif test='%{user.isParent=="Y" || user.isSchoolStudent=="Y"}'>
									<s:url id="backToViewEventsId" action="ajaxStudentEvents"
										includeParams="all" escapeAmp="false" namespace="/student">
									</s:url>
									<sj:a href="%{backToViewEventsId}" cssClass="btn btn-xs purple"
										targets="mainContentDiv">
										<!-- <i class="fa fa-edit"></i> --> Back
										</sj:a>
								</s:elseif>
							</div>
						</div>



						<div>
							
						</div>
							<s:if test="%{objectList != null && !objectList.isEmpty()}">							
												
							<s:iterator value="objectList">
								<s:set name="eventAlbumId" value="%{id}"></s:set>
								<div class="col-md-12">
									<h5 class="form-section">
										<s:if
											test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
											<s:url id="doEditAlbum" action="ajaxEidtAlbumPhotos"
												includeParams="all" escapeAmp="false" namespace="/admin">
												<s:param name="tempId2" value="%{id}" />
											</s:url>
											<sj:a href="%{doEditAlbum}" class="btn btn-xs purple"
												targets="eventsContentDiv" indicator="indicator"
												button="false" buttonIcon="ui-icon-plus"
												cssClass="btn purple btn-md">
												<s:property value="albumName" />&nbsp;&nbsp;<i
													class="fa fa-edit"></i>
											</sj:a>
										</s:if>
										<s:elseif test='%{user.isParent=="Y" || user.isSchoolTeacher=="Y" || user.isSchoolAsstStaff=="Y" || user.isSchoolStudent=="Y"}'>
											<s:property value="albumName" />&nbsp;&nbsp;
										</s:elseif>
										&nbsp;&nbsp;&nbsp;<small>[<s:property
												value="eventStartDateStr" />]&nbsp;&nbsp; <s:if
												test='%{albumAttachmentList.size()>3}'>
												<s:url id="viewAllPhotos" action="ajaxViewAllPhotos"
													namespace="/admin" includeParams="all" escapeAmp="false">
													<s:param name="tempId2" value="%{id}" />
												</s:url>
												<sj:a href="%{viewAllPhotos}" targets="eventsContentDiv"
													data-toggle="tab">View More...</sj:a>
											</s:if>
										</small>
									</h5>
								</div>
								<br>
								<s:if
									test="%{albumAttachmentList != null && !albumAttachmentList.isEmpty()}">
									<s:iterator value="albumAttachmentList">
										<div class="col-md-3 col-sm-4 mix category_2 category_1">
											<div class="mix-inner" style="height: 140px;">
												<a rel="gallery" class="fancybox"
													href="<c:url value='${hrefAttachmentFilePath}'/>"> <img
													src='<c:url value="${hrefAttachmentFilePath}"/>'
													class="img-responsive" style="height: 150px;width:290px;"/></a>
												<div class="mix-details" style="background: #008CBA; none repeat scroll 0 0;color: #555;cursor: pointer;display: block;margin-top: 10px; margin-left:;margin-top: -5em;padding: 12px 15px;position: absolute;">
													<h4>
														<s:property value="albumName" />
													</h4>
													<s:if
														test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
														<s:url id="removePhotos" action="ajaxDeletePhotos"
															includeParams="all" escapeAmp="false" namespace="/admin">
															<s:param name="anyId" value="%{id}"></s:param>
															<s:param name="tempId2" value="%{eventAlbumId}"></s:param>
														</s:url>
														<s:div class="mix-link"
															cssStyle=" background: red none repeat scroll 0 0; color: #fff; cursor: pointer; display: block; margin-top: 10px;padding: 10px 15px; position: absolute;right: 50%;display: inline;font-size: 13px;"
  									                        onclick="javascript:confirmDialogWithTarget(this,'eventsContentDiv');"
															id='%{removePhotos}' theme="simple"
															title="Delete this Photo">
															<i class="fa fa-trash-o"></i> Delete
														</s:div>
													</s:if>
													
										
													<a rel="gallery"
														class="mix-preview fancybox-button fancybox"
														href="<c:url value='${hrefAttachmentFilePath}'/>"> <i
														class="fa fa-search" ></i>&nbsp;View
													</a>
												</div>
											</div>
										</div>
									</s:iterator>
								</s:if>
								<%-- <s:else>
									<div class="alert">Currently there are no photos.</div>
								</s:else> --%>
							</s:iterator>
							</s:if>
							<s:else>
								<div class="alert alert-info">Currently there are no event
									albums.</div>
							</s:else>
						</div>
					</div>
				</div>
			
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			Portfolio.init();
			changePageTitle("View event photos");

		});
		$(".fancybox").fancybox({
			helpers : {
				title : {
					type : 'inside',
					position : 'top'
				}
			},
			nextEffect : 'fade',
			prevEffect : 'fade'
		});
		
		var tempString2 = '<s:property value="tempString2"/>';
		if(isNonEmpty(tempString2))
		{
			$("#eventULId").removeClass("active");
			$( "#albumULId" ).addClass( "active" );
			$( "#addAlbumLiId" ).addClass( "active" );
			
		}
		
	</script>