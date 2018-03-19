<%@ include file="/common/taglibs.jsp"%>
<div>
<s:if test="%{objectList != null && !objectList.isEmpty()}"><br />

		<s:iterator value="objectList">
			<div style="width: 55px;float: left">
				<img src='<c:url value="${commentAccount.personThumbnailImage}"/>'
					alt='<s:property  value="createdBy" />' border="0"
						 id="userThumbpreview" width="50px" height="50px"/>
			</div>
			<div style="vertical-align: top;" > 
				<s:property value="createdBy" /><br />
				<s:property value="createdDateStr" />
			</div> <br />
			<div style="float: left;padding-bottom:10px">
				<div id="shortDescEvents<s:property value="id"/>" >
                     <s:property value="description" />
                </div>
                   <p style="display: none;">   
                   	<script language="JavaScript" type="text/javascript">
                        	 shortDescEvents("#shortDescEvents<s:property value="id"/>","javascript:viewTotalDiscussion(<s:property value="id"/>);",300);
                     	</script>
                   </p>
			</div><br/><br />
		</s:iterator>
	</s:if>
	<s:else>
		No comments have been posted for this post.
 	</s:else>
</div>	