<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" id="style_color" href="${pageContext.request.contextPath}/styles/newCss/cubeportfolio.css" />
<div class="row">
	<div class="col-md-12">
			<s:if test="%{tempList != null && !tempList.isEmpty()}">
				<div class="col-md-12"><jsp:include page="/common/messages.jsp" /></div>
				<div class="tab-pane active" id="tab_1">
					<!-- BEGIN FILTER -->
					<div class="margin-top-10">
						<div class="row mix-grid">
						<div class="col-md-12">
							<s:iterator value="tempList">
								<div class="cbp-item identity web-design">
									<div class="cbp-item-wrapper">
		                                <div class="cbp-caption">
		                                	 <div class="titleClass">
		                                 		<s:property value="title"/>
		                                 	</div>
		                                    <div class="cbp-caption-defaultWrap">
		                                   	  <s:property value="embedCode" escape="false" />
		                                     </div>
		                                    <div class="cbp-caption-activeWrap">
		                                        <div class="cbp-l-caption-alignCenter">
		                                            <div class="cbp-l-caption-body">
		                                            <s:if test='%{#session.previousYear=="N"}'>
		                                           		 <s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
															<s:url id="doEditVideos" action="ajaxDoAddVideos" includeParams="all" escapeAmp="false" namespace="/common">
																<s:param name="schoolVideos.id" value="%{id}" />
															</s:url>
															<sj:a href="%{doEditVideos}"  targets="videosContentDiv" indicator="indicator"
																button="false" buttonIcon="ui-icon-plus" cssClass="btn purple btn-md cbp-singlePage cbp-l-caption-buttonLeft">
																<i class="fa fa-edit"></i>&nbsp;Edit
															</sj:a>
															<s:url id="deleteVideos" action="ajaxDeleteVedios" includeParams="all" namespace="/common">
																<s:param name="schoolVideos.id" value="%{id}" />
															</s:url>
															<s:div id='%{deleteVideos}' cssClass="cbp-singlePage cbp-l-caption-buttonLeft btn red btn-md" title="Delete this Videos"
																onclick="javascript:confirmDialogWithTarget(this,'videosContentDiv');">
																<i class="fa fa-times"></i>&nbsp;Delete</s:div>
															</s:if>
														</s:if>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
	                            </div>
							</s:iterator>
							</div>
						</div>
					</div>
				</div>
			</s:if>
			<s:else>
				<div class="alert alert-info">Currently there are no videos.</div>
			</s:else>
		</div>
		</div>
	<script type="text/javascript">
		$(document).ready(function() {
			Portfolio.init();
			changePageTitle("View All Videos");
			$('iframe').css("height", "250");
			$('iframe').css("width", "250");

		});
		 
	</script>