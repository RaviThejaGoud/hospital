<%@ include file="/common/taglibs.jsp"%>
<head>
<script type="text/javascript"	src="${pageContext.request.contextPath}/scripts/starrate/js/jquery-ui.custom.min.js"></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/scripts/starrate/js/jquery.uni-form.js'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/scripts/starrate/js/jquery.ui.stars.js'></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/scripts/starrate/css/ui.stars.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
 
	function readMoreKbankStudy(id){
	$("#ajaxResponseDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars="id=" + id;
	$.ajax( {
		url : jQuery.url.getChatURL("/student/ajaxReadMoreKbankStudy.do"),
		cache : false,
		data: pars,
		success : function(html) {					   
			$("#caseStudy").html(html);
			$('#formattedMsg').html($('#unFormattedMsg').text());
		}
	});	
}
function doEditKBankStudies(id){
	$("#ajaxResponseDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars="id=" + id;
	$.ajax( {
		url : jQuery.url.getChatURL("/student/ajaxDoEditKBankStudies.do"),
		cache : false,
		data: pars,
		success : function(html) {					   
			$("#caseStudy").html(html);
			$('#formattedMsg').html($('#unFormattedMsg').text());
		}
	});	
}
function doAcceptKBankStudies(id){
	$("#ajaxResponseDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars="id=" + id;
	$.ajax( {
		url : jQuery.url.getChatURL("/student/ajaxDoAcceptKBankStudies.do"),
		cache : false,
		data: pars,
		success : function(html) {					   
			$("#acceptStudy").html(html);
		}
	});	
}
function doKBankFavouriteStudies(id,kBankTypeId,kBankTypeName){
	$("#ajaxResponseDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars="id=" + id + "&kBankTypeId="+kBankTypeId +"&kBankTypeName="+kBankTypeName;
	$.ajax( {
		url : jQuery.url.getChatURL("/student/ajaxDoKBankFavouriteStudies.do"),
		cache : false,
		data: pars,
		success : function(html) {					   
			$("#acceptStudy").html(html);
		}
	});	
}
function ajaxDoComments(id){
	$("#ajaxResponseDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars="id=" + id;
	$.ajax( {
		url : jQuery.url.getChatURL("/student/ajaxDoAddComments.do"),
		cache : false,
		data: pars,
		success : function(html) {					   
			$("#caseStudyView").html(html);
		}
	});	
}
</script>
</head>
<div id="acceptStudy">
	 	<div class="grid_13 commomnTabs">
	 	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<s:form id="searchKeword" 
				action="ajaxSearchKBankStudies" theme="css_xhtml">	
				<s:hidden name="selectedId"></s:hidden>
				<s:hidden name="kBankTypeName"></s:hidden>
				<div class="grid_12">
					<div class="grid_6">
						<sj:textfield name="searchKewords" id="searchKewords"
							cssClass="textfield large required " required="true"
							onfocus="clearText(this)" onblur="clearText(this)">
						</sj:textfield>
					</div>
					<div class="grid_2">
						<sj:submit   targets="caseStudyView" value="Search"
							cssClass="submit" indicator="indicator" cssStyle="margin:0px"
							onClickTopics="searchKBankKeywords" formIds="searchKeword"
							resetForm="true" />
					</div>
				</div>
				<div class="grid_4">
						&nbsp;
				</div>
			</s:form>
		</s:if>
		</div>
		
	<div class="grid_13 commomnTabs">
	 <s:if test="%{objectList != null && !objectList.isEmpty()}">
           <s:iterator value="objectList" status="status">
             <div>
                 <s:if test="#status.index % 2 == 1 ">
                     <div class="odd">
                 </s:if>
                 <s:else>
                     <div class="even">
                 </s:else>
                  <h3>
                      <s:property value="title" />
                    		<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
                    		<s:if test='%{user.id == userId}' >
                    		<s:if test='%{status == "A"}'> 
                    			 - <a href="#" onclick="javascript:doEditKBankStudies(<s:property value="id"/>);" title="Edit" class="editFont">Edit</a></s:if>
                    		</s:if>
                    			<s:if test='%{kBankFavourite == "N"}'> 
                    				/ <a href="#" onclick="javascript:doKBankFavouriteStudies(<s:property value="id"/>,<s:property value="selectedId" />,'<s:property value="kBankTypeName" />');" title="Add To Favourite" class="editFont">Add To Favourite</a>
                    			</s:if>
                    		</s:if>
                    		
                  </h3>
                  <div>
					<a  rel="nofollow" href='<c:url value='/student/ajaxDownloadFiles.do'/>?id=<s:property value="id"/>'><s:property value="attachment.fileName" /></a>
					<br />
				</div>
                  <div style="margin-bottom: 15px">
                          Posted on
                          <s:property value="createdDateStr" />
                          by
                         <a class="tooltip1" href="#">
                          <s:property value="createdBy" /> </a>
                  </div>
                  <div id="kBankDesc<s:property value="id"/>"
					style="display: none">
					<s:property value="description" />
				</div>
				<div class="grid_13">
						&nbsp;
					</div>
				<div class="kBankCmtRating">
					<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
					<div class="kBankComments">
	                      <script language="JavaScript" type="text/javascript">
	                          shortDescWithLink("#kBankDesc<s:property value="id"/>","javascript:readMoreKbankStudy(<s:property value="id"/>);",100);
	                      </script>
	                      <s:if test='%{loginAccount.isAdmin == "Y" || loginAccount.editHomePageNews == "Y"}'>
	                          <a href="javascript:getAjaxRemoveNews(<s:property value="id" />);">Remove this Post</a>
	                      </s:if>
	                     <s:if test="%{kBankCommentsList != null}">
	                    		<a href="#" onclick="javascript:ajaxDoComments(<s:property value="id"/>);" title="Edit news" class="editFont">Recent Comments.. (<s:property value="kBankCommentsList.size" />)</a>
	                     </s:if>
					</div>
					<div class="kBankRating">
					<table>
						<tr>
							<td colspan="2">
									<label class="left">
									Rate this post: &nbsp;
								</label>
							</td>
							<td>
								<form id="rat<s:property value="id" />" action="" method="post" style="padding: 0px;">
									<div class="knowledgeBankId" style="display: none;">
										<s:property value="id" />
									</div>
									<c:forEach var="i" begin="1" end="10">
										<c:choose>
											<c:when test="${i <= kBankRateAverage}">
												<input type="radio" name="rate"
													value='<c:out value="${i}" />' title="" checked='checked' />
											</c:when>
											<c:otherwise>
												<input type="radio" name="rate"
													value='<c:out value="${i}" />' title="" />
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</form><td >
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
													url : jQuery.url.getChatURL("/student/ajaxKBankRating.do"),
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
				<div>&nbsp;</div><br clear="all" />
                </div>
       </s:iterator>
       </s:if>
       <s:else>
           <div style="padding: 20px">
               Currently there are no <s:property value="kBankTypeName"/>
           </div>
       </s:else>
</div>
</div>
<script type="text/javascript">
changePageTitle('All Teacher Events List');
	$.subscribe('doInitEditKBankStudies', function(event, data) {
	
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	$.subscribe('removeKBankStudies', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
	$.subscribe('searchKBankKeywords', function(event, data) {
		  var searchKeword = $('#searchKewords').val();
		    if(searchKeword.length == 0)
		    { 
		    	alert("Please enter search value");
		    	return false;
		    }
            else
                return true;
	});
</script>

