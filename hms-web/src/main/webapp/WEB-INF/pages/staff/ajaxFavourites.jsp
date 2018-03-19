<%@ include file="/common/taglibs.jsp"%>
<title>Teacher | Class Exam Details</title>
<div class="block grid_12">
	<div class="block_head">
		<h2>
			My Favourites
		</h2>
		<ul>
			<li>
				&nbsp;
			</li>
		
		</ul>
	</div>
	<div class="block_content">
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
                       <s:property value="title" /><!--
                     		 - <s:if test='%{status == "A"}'> <a href="#" onclick="javascript:doEditKBankStudies(<s:property value="id"/>);" title="Edit news" class="editFont">Edit</a></s:if>
                    		<s:elseif test='%{status == "I"}'> <a href="#" onclick="javascript:doAcceptKBankStudies(<s:property value="id"/>);" title="Accept" class="editFont">Accept</a></s:elseif>
                   --></h3>
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
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("My Favourite");

//changePageTitle("<s:property value="kBankTypeName"/>");
</script>

