<%@ include file="/common/taglibs.jsp"%>	
	<div class="block grid_11">
			<div class="block_head">
				<h2>
			<s:if test="%{kBankTypeName != Null}">
				<s:property value="kBankTypeName"/>
			</s:if>
			<s:else>
				About Knowledge Bank
			</s:else>
		</h2>
			<ul>
				<li>
					<s:url id="urlDoAddCaseStudy" includeParams="all" action="ajaxGetKBankDetails" escapeAmp="false" namespace="/student">
					<s:param name="kBankTypeId" value="%{selectedId}"></s:param>
					<s:param name="kBankTypeName" value="%{kBankTypeName}" /> 
					</s:url>
					<sj:a href="%{urlDoAddCaseStudy}" 
						indicator="indicator" targets="kBankContent" 
						button="false" buttonIcon="ui-icon-plus">Back</sj:a>
				</li>
			</ul>
			</div> <!-- .block_head ends -->
			<div class="block_content">
				<s:if test="%{knowledgeBank != null}">
					<h3>
						<s:property value="knowledgeBank.title" />
					</h3>
					<div style="margin-bottom: 15px">
						Posted on <s:property value="knowledgeBank.createdDateStr" /> by 
								<a class="tooltip1" href="#"><span></span><s:property value="knowledgeBank.createdBy" /></a>
					</div>
					<div id="unFormattedMsg" style="display: none">
						<s:property value="knowledgeBank.description" />
					</div>
					<div id="formattedMsg"></div>
						<p>
						<label>
							<b>Comments:</b>
						</label>
						</p>
						<div>
						<s:form action="ajaxAddKBankComments" theme="css_xhtml" id="addPostKBankComments" method="post" namespace="/student">
							<s:hidden name="knowledgeBank.id"></s:hidden>
							<div class="grid_9">
								<div class="grid_5" style="text-align: left;">
									<b><font style="color: red">*&nbsp;</font>Description:</b>
								</div>
								<div class="grid_6">
									<sj:textarea rows="3" cols="20" name="kBankComments.description" cssStyle="width:88%"
										cssClass="text small  word_count required" required="true" requiredposition="left"
										></sj:textarea>
									<div class="counter"></div>
								</div>
							</div>
							<div class="clear"></div>
							<s:if test='%{(#session.academicYear == null || #session.academicYear.isEmpty()) || (#session.previousYear=="N")}'>
							<div class="grid_2" style="float: right;">
								<sj:submit   cssClass="submit small" value="Save" indicator="indicator"
								targets="viewComents" formIds="addPostKBankComments" 
								onClickTopics="formValidationForPostKBankComments" />
							
								<!--<s:url id="urlDoAddCaseStudy" includeParams="all" action="ajaxGetKBankDetails">
								<s:param name="kBankTypeId" value="%{selectedId}"></s:param>
								</s:url>
								<sj:a href="%{urlDoAddCaseStudy}" cssClass="cancelButton"
									indicator="indicator" targets="kBankContent" 
									button="false" buttonIcon="ui-icon-plus">Cancel</sj:a>-->
							</div>
							</s:if>
							</s:form>
						</div>
						<div class="clear">&nbsp;</div>
						<div id="viewComents">
							<s:if test="%{tempList != null && !tempList.isEmpty()}"><br />
								<s:iterator value="tempList">
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
				</s:if>
				<s:else>
					Currently there are no Minutes.
			  </s:else>
			</div><!-- .block_content ends -->
		</div>
		
		
		<script type="text/javascript">
changePageTitle("Add  <s:property value="kBankTypeName"/>");
$.subscribe('formValidationForPostKBankComments', function(event, data) {
	if ($('#addPostKBankComments').valid())
		return true;
	else
		return false;
});
</script>