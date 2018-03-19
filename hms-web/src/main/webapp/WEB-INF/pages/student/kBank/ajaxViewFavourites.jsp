<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div>
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
                       <s:property value="title" /><s:property value="user.adminOrDelegate"/>
                     		 - <s:if test='%{status == "A"}'> <a href="#" onclick="javascript:doEditKBankStudies(<s:property value="id"/>);" title="Edit news" class="editFont">Edit</a></s:if>
                    		<s:elseif test='%{status == "I"}'> <a href="#" onclick="javascript:doAcceptKBankStudies(<s:property value="id"/>);" title="Accept" class="editFont">Accept</a></s:elseif>
                     		 
                     		 
                     		 
                     		 <!--<s:if test='%{user.adminOrDelegate == "ROLE_ADMIN" && status == "A"}'> <a href="#" onclick="javascript:doEditKBankStudies(<s:property value="id"/>);" title="Edit news" class="editFont">Edit</a></s:if>
                    		<s:elseif test='%{status == "I"}'> <a href="#" onclick="javascript:doAcceptKBankStudies(<s:property value="id"/>);" title="Accept" class="editFont">Accept</a></s:elseif>
                     		--><!--<tr>
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
						<a  rel="nofollow" href='<c:url value='/admin/ajaxDownloadFiles.do'/>?id=<s:property value="id"/>'><s:property value="attachment.fileName" /></a>
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
                  	<p>
                       <script language="JavaScript" type="text/javascript">
                           shortDescWithLink("#kBankDesc<s:property value="id"/>","javascript:readMoreKbankStudy(<s:property value="id"/>);",100);
                       </script>
                       <s:if test='%{loginAccount.isAdmin == "Y" || loginAccount.editHomePageNews == "Y"}'>
                           <a href="javascript:getAjaxRemoveNews(<s:property value="id" />);">Remove this Post</a>
                       </s:if>
                   </p>
                 </div>
       </s:iterator>
       </s:if>
       <s:else>
           <div style="padding: 20px">
               Currently there are no <s:property value="kBankTypeName"/>
           </div>
       </s:else>
 </div></div>

		