<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="block grid_14">
	<div class="block_head">
		<div class="header">
			<h2>Return Book Details</h2>
		</div>
	</div>
	<div class="block_content"> 
		<div class="grid_14">
			<s:form action="" id="addReturnBooks" method="post" theme="css_xhtml">
				<div  class="grid_12" style="width:730px;">
					<div class="grid_4">
						<b class="labelRight"><span class="required">*</span>Enter
							Student ID / Staff ID:</b>
					</div>
					<div class="grid_5">
						<sj:textfield name="issuedBook.studentId" id="username"
							cssClass="textfield large required defaultValue" required="true"
							value="Please enter book title.">
						</sj:textfield>
					</div>
					<div class="grid_2">
						<s:submit type="submit" cssClass="submit" value="Search"
							onclick="javascript:getGeturnBooks(); return false;" />
					</div>
				</div>
			</s:form>
			<div id="resultsDiv" style="display: none; float: left; width: 100%">
			</div>
		</div>
	 </div>
</div>
<script type="text/javascript">
function getGeturnBooks() {
	var stId = document.getElementById("username").value;
	var url = jQuery.url.getChatURL("/library/ajaxReturntBooks.do");
	if (stId.length == 0 || stId=='Please enter book title.') {
		alert("!Oops enter Student ID / Staff ID.");
	} else {
		$("#resultsDiv")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var pars = "username=" + stId;
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#resultsDiv").html(html);
				document.getElementById('resultsDiv').style.display = "block";
			}
		});
	}
}
changePageTitle("Books Return to Library");
</script>