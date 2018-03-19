<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> My Favourites
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content">
					<%@ include file="/common/messages.jsp"%>
					<s:if test="%{objectList != null && !objectList.isEmpty()}">
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="sample_2">
							<thead>
								<tr>
									<th>
										View posts
									</th>
								</tr>
							</thead>
							<tbody>
								
									<s:iterator value="objectList" status="status">
									<tr>
										<td>
											<div class="message">
												<span class="arrow"> </span>
												<h5 class="pageTilte bold">
													<b><s:property value="title" /> </b>
												</h5>
												<div>
													Attachment :
													<a rel="nofollow"
														href='<c:url value='/admin/ajaxDownloadFiles.do'/>?id=<s:property value="id"/>'><s:property
															value="attachment.fileName" /> </a>
												</div>
												<div>
													Posted On :
													<s:property value="createdDateStr" />
												</div>
												<div>
													Post By :
													<a class="tooltip1" href="#"> <s:property
															value="createdBy" /> </a>
												</div>
												<div id="kBankDesc<s:property value="id"/>"
													style="display: none">
													<b>Description :</b>&nbsp;
													<s:property value="description" />
												</div>

												<div>
													<script language="JavaScript" type="text/javascript">
												shortDescWithLink("#kBankDesc<s:property value="id"/>",<s:property value="id"/>,100);
                      								</script>
													<s:if
														test='%{loginAccount.isAdmin == "Y" || loginAccount.editHomePageNews == "Y"}'>
														<a
															href="javascript:getAjaxRemoveNews(<s:property value="id" />);">Remove
															this Post</a>
													</s:if>
												</div>
											</div>
										</td>
										</tr>
									</s:iterator>
								
							</tbody>
						</table>
					</s:if>
					<s:else>
						<s:if test="%{kBankTypeName != null && !kBankTypeName.isEmpty()}">
							<s:property value="kBankTypeName" />
						</s:if>
						<s:else>
							<div class="alert alert-info">
								Currently there are no Favourites.
							</div>
						</s:else>
					</s:else>
				</div>
				<!-- below lines used in when click the Reports---Fee Collections in left nav  changed by cvs-26-2-2014 -->
				<div id="dynamicKvideosDiv" style="display: none;">
					<s:if
						test="%{knowledgeBankTypeList != null && !knowledgeBankTypeList.isEmpty()}">
						<s:iterator value="knowledgeBankTypeList">
							<li class="removeKVDiv">
								<s:url id="urlKBankDetailsLink" action="ajaxGetKBankDetails"
									namespace="/admin" includeParams="all" escapeAmp="false">
									<s:param value="id" name="kBankTypeId" />
									<s:param value="typeName" name="kBankTypeName" />
								</s:url>
								<sj:a href="%{urlKBankDetailsLink}" targets="mainContentDiv"
									cssClass="ajaxify MKBT">
									<s:property value="typeName" />
								</sj:a>
							</li>
						</s:iterator>
					</s:if>
				</div>
				<!-- END -->
			</div>
		</div>
	</div>
</div>
<div id="popupReadMoreDiv"></div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('My Favourite');
	TableAdvanced.init();
	//below 2 lines used in when click the manage K-Bank-----> manage K-Bank in left nav 
		$('ul#dynamicKvideosDiv').find('.removeKVDiv').remove();
		$('ul#dynamicKvideosDiv').append($('div#dynamicKvideosDiv').html());
	});
function popupReadMoreKbankStudy(id) {
	$("#popupReadMoreDiv")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars = "id=" + id;
	$.ajax( {
		url : jQuery.url.getChatURL("/admin/ajaxReadMoreKbankStudy.do"),
		cache : false,
		data : pars,
		success : function(html) {
			$("#popupReadMoreDiv").html(html);
			//$("#caseStudy").html(html);
		//$('#popupReadMoreDiv').html($('#unFormattedMsg').text());
	}
	});
}
</script>
