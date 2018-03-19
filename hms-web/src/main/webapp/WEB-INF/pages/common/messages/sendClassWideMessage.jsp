<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<!--<div class="block_content" id="sendClassMessagesId">
	-->
<div  class="grid_14 commonFormTabs">
 <jsp:include page="/common/messages.jsp"></jsp:include>
	<fieldset>
	<div  class="grid_14">
		<s:form action="ajaxSendClassWideMessages" theme="css_xhtml"
			id="addClassWideMessage" method="post" name="addClassWideMessages">
			<s:hidden name="student.id" />
			<input type="hidden" value="inbox" name="inbox"/>
			<div class="grid_13">
				<label class="labelRight">
					Select Class:
				</label>
				<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
					<s:select list="studyClassList" id="className" name=""
						listKey="id" listValue="classAndSection" headerKey=""
						headerValue="- Select Class -" theme="simple"
						onchange="javascript:getAjaxDoGetStudent(this.value);"
						requiredposition="first">
					</s:select>
				</s:if>
			</div>
			<div class="grid_13">
				&nbsp;
			</div>
			<div id="myStudentList1">
			</div>
		</s:form>
		</div>
	</fieldset>
</div>
<script type="text/javascript">
changePageTitle("Send Class Wide Message");
	function getAjaxDoGetStudent(studyClassId) {
		if(studyClassId == ""){
			$("#myStudentList1").hide();
		}
		else{
			var pars = "classId=" + studyClassId;
			$("#myStudentList1").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/common/ajaxGetStudentListForm.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#commonTabContent").html(html);
					$("#commonTabContent").show();
				}
			});
		}
	}

</script>
 