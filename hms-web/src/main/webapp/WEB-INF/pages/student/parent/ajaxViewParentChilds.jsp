<%@ include file="/common/taglibs.jsp"%>
<div class="block_content">
		<s:select id="sectionId" list="objectList" listKey="id"
				listValue="idAndName" label="Child Name" cssClass="required"
				required="true" headerKey="" headerValue="- Select -" name="anyId" theme="css_xhtml" onchange="getStudentClassandSubjects(this.value);"/>
<div id="studentDetails">
</div>			
</div>

<script type="text/javascript">
	function getStudentClassandSubjects(accountId) {
            var url = jQuery.url.getChatURL("/student/ajaxGetStudentSubjects.do?studAccountId="+accountId);
			$.ajax( {
			url : url,
			cache : false,
			success : function(html) {
				$("#studentDetails").html(html);
			}
		  });
         }
</script>