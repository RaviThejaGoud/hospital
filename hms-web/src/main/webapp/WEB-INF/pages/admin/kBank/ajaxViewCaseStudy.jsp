<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/starrate/js/jquery-ui.custom.min.js">
</script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/scripts/starrate/js/jquery.uni-form.js'>
</script>
<script type='text/javascript'
	src='${pageContext.request.contextPath}/scripts/starrate/js/jquery.ui.stars.js'>
</script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/scripts/starrate/css/ui.stars.css" />
<script type="text/javascript">
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
			$("#popupReadMoreDiv").html(html);
			//$('#formattedMsg').html($('#unFormattedMsg').text());
	}
	});
}
function doEditKBankStudies(id) {
	$("#ajaxResponseDiv")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars = "id=" + id;
	$.ajax( {
		url : jQuery.url.getChatURL("/admin/ajaxDoEditKBankStudies.do"),
		cache : false,
		data : pars,
		success : function(html) {
			$("#caseStudy").html(html);
			$('#formattedMsg').html($('#unFormattedMsg').text());
		}
	});
}
function doAcceptKBankStudies(id) {
	$("#ajaxResponseDiv")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars = "id=" + id;
	$.ajax( {
		url : jQuery.url.getChatURL("/admin/ajaxDoAcceptKBankStudies.do"),
		cache : false,
		data : pars,
		success : function(html) {
			$("#acceptStudy").html(html);
		}
	});
}
function doKBankFavouriteStudies(id, kBankTypeId, kBankTypeName) {
	$("#ajaxResponseDiv")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars = "id=" + id + "&kBankTypeId=" + kBankTypeId + "&kBankTypeName="
			+ kBankTypeName;
	$.ajax( {
		url : jQuery.url.getChatURL("/admin/ajaxDoKBankFavouriteStudies.do"),
		cache : false,
		data : pars,
		success : function(html) {
			$("#acceptStudy").html(html);
		}
	});
}

function PopupDoComments(id) {
	$("#popupCommentsDiv")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars = "id=" + id;
	$.ajax( {
		url : jQuery.url.getChatURL("/admin/ajaxDoAddComments.do"),
		cache : false,
		data : pars,
		success : function(html) {
			$("#popupCommentsDiv").html(html);
		}
	});
}
</script>

<div id="acceptStudy">
	<div class="row form-body">
		<jsp:include page="/common/messages.jsp"></jsp:include>
		<s:if test="%{objectList != null && !objectList.isEmpty()}">
			<s:form id="searchKeword" action="ajaxSearchKBankStudies"
				cssClass="form-horizontal" theme="simple" namespace="/admin">
				<s:hidden name="selectedId"></s:hidden>
				<s:hidden name="kBankTypeName"></s:hidden>
				<div class="form-group">
					<label class="col-md-2 control-label">
						<span class="required">*</span>Search By Name :
					</label>
					<div class="col-md-4">
						<div class="input-group">
							<sj:textfield name="title" id="title"
								cssClass="form-control large"
								placeholder="Please enter search value." >
							</sj:textfield>
							<span class="input-group-btn"> <sj:submit  
									targets="caseStudyView" value="Search" cssClass="btn blue"
									indicator="indicator" onBeforeTopics="searchKBankKeywords"
									formIds="searchKeword" resetForm="true" validate="true"/> </span>
						</div>
						<span class="hintMessage">(Key at least 3 chars and hit
							submit to get closer match.)</span>
					</div>
				</div>
			</s:form>
		</s:if>
	</div>
	<div class="form-body">
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
										<s:if
											test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
											<s:if test='%{status == "A"}'>
												<a href="#"
													onclick="javascript:doEditKBankStudies(<s:property value="id"/>);"
													title="Edit" class="editFont">Edit</a>
											</s:if>
											<s:elseif test='%{status == "I"}'>
												<a href="#"
													onclick="javascript:doAcceptKBankStudies(<s:property value="id"/>);"
													title="Accept" class="editFont">Accept</a>
											</s:elseif>
										</s:if>
										<s:if test='%{kBankFavourite == "N"}'> 
                    				-- <a href="#"
												onclick="javascript:doKBankFavouriteStudies(<s:property value="id"/>,<s:property value="selectedId" />,'<s:property value="kBankTypeName" />');"
												title="Add To Favourite" class="editFont">Add To
												Favourite</a>
										</s:if>
									</h5>
									<div>
										Attachment :<a rel="nofollow"
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
									<span class="body">
										<div id="kBankDesc<s:property value="id"/>"
											style="display: none">
											<b>Description :</b>&nbsp; <s:property value="description" />
										</div> </span>
								</div>
								<div class="spaceDiv"></div>
								<div class="kBankCmtRating">
									<s:if
										test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
										<div class="kBankComments col-md-4">
											<script language="JavaScript" type="text/javascript">
												shortDescWithLink("#kBankDesc<s:property value="id"/>",<s:property value="id"/>,100);
                    			 			 </script>
											<s:if
												test='%{loginAccount.isAdmin == "Y" || loginAccount.editHomePageNews == "Y"}'>
												<a
													href="javascript:getAjaxRemoveNews(<s:property value="id" />);">Remove
													this Post</a>
											</s:if>
											<s:if test="%{kBankCommentsList != null}">
												<a data-toggle="modal"  href="#popupCommentsDiv" 
													onclick="javascript:PopupDoComments(<s:property value="id" />);" title="Edit news">Recent Comments..(<s:property value="kBankCommentsList.size" />) 
												</a>
											</s:if>
										</div>
									</s:if>
									<s:if
										test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
										<div class="kBankRating col-md-4">
											<table>
												<tr>
													<td colspan="2">
														<label class="left">
															Rate this post:&nbsp;
														</label>
														<td>
															<form id="rat<s:property value="id" />" action=""
																method="post" style="padding: 0px;">
																<div class="knowledgeBankId" style="display: none;">
																	<s:property value="id" />
																</div>
																<c:forEach var="i" begin="1" end="10">
																	<c:choose>
																		<c:when test="${i <= kBankRateAverage}">
																			<input type="radio" name="rate"
																				value='<c:out value="${i}" />' title=""
																				checked='checked' />
																		</c:when>
																		<c:otherwise>
																			<input type="radio" name="rate"
																				value='<c:out value="${i}" />' title="" />
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
															</form>
															</td>
															<td>
																<div id="loader<s:property value="id" />" class="loader">
																	<div style="padding-top: 5px;">
																		please wait...
																	</div>
																</div>
																<script type="text/javascript">
																	if($("#rat"+'<s:property value='id' />'))
																	{
																		$("#rat"+'<s:property value='id' />').stars({
																		starWidth: 18, // only needed in "split" mode
																		split: 2,
																		oneVoteOnly: true,
																		callback: function(ui, type, value)
																		{
																			// Hide Stars while AJAX connection is active
																			//var kBankId=$("#rat .knowledgeBankId"+'<s:property value='id' />').html();
																			$("#rat"+'<s:property value='id' />').hide();
																			$("#loader"+'<s:property value='id' />').show();
																			$.ajax({
																				url : jQuery.url.getChatURL("/admin/ajaxKBankRating.do"),
																				data:"&starValue=" + value + "&kBankId=" + '<s:property value='id' />',
																				success : function(receiveRequest) {
																					//var response = receiveRequest.evalJSON(true);
																					ui.select(Math.round(receiveRequest.newRating));
																					$("#loader"+'<s:property value='id' />').hide();
																					$("#rat"+'<s:property value='id' />').show();
																				}
																			});
																		}
																	});
																}
															</script>
															</td>
															
												</tr>
											</table>
										</div>
									</s:if>
								</div>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Currently there are no
				<s:property value="kBankTypeName" />
			</div>
		</s:else>
	</div>
</div>
<div id="popupCommentsDiv"></div>
<div id="popupReadMoreDiv"></div>
<script type="text/javascript">
changePageTitle('All Teacher Events List');
$(document).ready(function() {
	TableAdvanced.init();
	$.destroyTopic('searchKBankKeywords');
});
$.subscribe('searchKBankKeywords', function(event, data) {
var name = $('#title').val();
	if (name == null || name == '' || name == 'Student First or Last Name') {
		alert("Please enter search value.");
		 event.originalEvent.options.submit=false;
	}else if(name.length < 3){
		alert("Please enter minimum 3 characters.");
		 event.originalEvent.options.submit=false;
	}
});
</script>
