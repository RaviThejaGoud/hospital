<%@ include file="/common/taglibs.jsp"%>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
 
	function readMoreKbankStudy(id){
	$("#ajaxResponseDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars="id=" + id;
	$.ajax( {
		url : jQuery.url.getChatURL("/staff/ajaxReadMoreKbankStudy.do"),
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
		url : jQuery.url.getChatURL("/staff/ajaxDoEditKBankStudies.do"),
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
		url : jQuery.url.getChatURL("/staff/ajaxDoAcceptKBankStudies.do"),
		cache : false,
		data: pars,
		success : function(html) {					   
			$("#acceptStudy").html(html);
		}
	});	
}
function doKBankFavouriteStudies(id){
	$("#ajaxResponseDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars="id=" + id;
	$.ajax( {
		url : jQuery.url.getChatURL("/staff/ajaxDoKBankFavouriteStudies.do"),
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
		url : jQuery.url.getChatURL("/staff/ajaxDoAddComments.do"),
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
	 	<div>
	 	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<s:form id="searchKeword" 
				action="ajaxSearchKBankStudies" theme="css_xhtml">	
				<s:hidden name="selectedId"></s:hidden>
				<s:hidden name="kBankTypeName"></s:hidden>
				<div class="grid_4">	
					<sj:textfield name="searchKewords" id="searchKewords" 
						value="Enter Search Value" cssClass="text small required" required="true" 
						   cssStyle="width: 165px;color:#ccc;text-align:center"></sj:textfield>
				</div>
				<sj:submit   targets="caseStudyView" value="Search" 
					cssClass="submit" indicator="indicator" cssStyle="margin:0px" onClickTopics="searchKBankKeywords"
					formIds="searchKeword" resetForm="true"/>
					
			</s:form>
		</s:if>
		</div>
	<br/>
	<div>
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
                    		- <s:if test='%{status == "A"}'> <a href="#" onclick="javascript:doEditKBankStudies(<s:property value="id"/>);" title="Edit news" class="editFont">Edit</a> / <a href="#" onclick="javascript:doKBankFavouriteStudies(<s:property value="id"/>);" title="Edit news" class="editFont">Add To Favourite</a></s:if>
                    		<s:elseif test='%{status == "I"}'> <a href="#" onclick="javascript:doAcceptKBankStudies(<s:property value="id"/>);" title="Accept" class="editFont">Accept</a></s:elseif>
                    		<!--<tr>
                    		<td>
                    		<s:url id="doEditKBankStudies" action="ajaxDoEditKBankStudies"
								includeParams="all" escapeAmp="false">
								<s:param name="id" value="{id}" />
							</s:url>
							<sj:a href="%{doEditKBankStudies}" onCompleteTopics="doInitEditKBankStudies"
								indicator="indicator" targets="editKBankStudies%{id}"
								onBeforeTopics="cleanOpenRows">
								Edit
							</sj:a>
						</td>
						</tr>
						<tr id="editKBankStudies<s:property value='id' />" style="display: none;" class="load">
						</tr>
                    		-->
                  </h3>
                  <div>
					<a  rel="nofollow" href='<c:url value='/staff/ajaxDownloadFiles.do'/>?id=<s:property value="id"/>'><s:property value="attachment.fileName" /></a>
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
				<br/>
				<div class="kBankCmtRating">
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
					<div class="blogRating">
					<!--<table>
						<tr>
							<td>
								<label>
									Rate this post: &nbsp;
								</label>
							</td>
							<td>
								<s:if test="#status.index == 0">
									<form id="rat1" action="" method="post">
								</s:if>
								<s:elseif test="#status.index == 1">
									<form id="rat2" action="" method="post">
								</s:elseif>
								<div class="blogPostIdValue" style="display: none;">
									<s:property value="id" />
								</div>
								<c:forEach var="i" begin="1" end="10">
									<c:choose>
										<c:when test="${i <= blogRateAverage}">

											<input type="radio" name="rate"
												value='<c:out value="${i}" />' title="" checked='checked' />
										</c:when>
										<c:otherwise>
											<input type="radio" name="rate"
												value='<c:out value="${i}" />' title="" />
										</c:otherwise>
									</c:choose>
								</c:forEach>
								</form>
								<s:if test="#status.index == 0">
									<div id="loader1" class="loader">
										<div style="padding-top: 5px;">
											please wait...
										</div>
									</div>
								</s:if>
								<s:elseif test="#status.index == 1">
									<div id="loader2" class="loader">
										<div style="padding-top: 5px;">
											please wait...
										</div>
									</div>
								</s:elseif>
							</td>
						</tr>
					</table>
				--></div>
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
</div></div>
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
		    if(searchKeword == null || searchKeword == '' || searchKeword == 'Enter Search Value')
		    {
		    	alert("Please enter search value");
		    	return false;
		    }
            else
                return true;
	});
	function clearTextField(field){
   	if (field.defaultValue == field.value) field.value = '';
   	else if (field.value == '') field.value = field.defaultValue;
	}
</script>

