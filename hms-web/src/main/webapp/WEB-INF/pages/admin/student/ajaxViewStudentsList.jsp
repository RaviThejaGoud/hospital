<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
	<div class="searchDiv">
	             <center>
					<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
					<label>Select Class:</label>	
							<s:select list="studyClassList" id="className" 
								name="studyClass.id"  listKey="id" listValue="className+' - '+section"
								headerKey="" headerValue="- Select -"  theme="simple" onchange="javascript:getAjaxDoGetStudent(this.value);">
							</s:select>	
					</s:if>
					</center>
			<div id="myStudentList"></div>
		
		
		</div>
<script type="text/javascript">
	changePageTitle('My Students List');
	function getAjaxDoGetStudent(studyClassId) {
		if(studyClassId == ""){
			$("#myStudentList").hide();
		}
		else{
			var pars = "studyClassId=" + studyClassId;
			$("#myStudentList").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			var url = jQuery.url.getChatURL("/admin/ajaxDoGetMyStudents.do");
			$.ajax( {
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#myStudentList").html(html);
					$("#myStudentList").show();
				}
			});
		}
	}

</script>
<script type="text/javascript">
	$(document).ready(
			function() {
				var errMsg = $('.block .message');
				if (errMsg) {
					$('.block .message').hide().append(
							'<span class="close" title="Click to Close"></span>')
							.fadeIn('slow');
					$('.block .message .close').hover( function() {
						$(this).addClass('hover');
					}, function() {
						$(this).removeClass('hover');
					});

					$('.block .message .close').click( function() {
						$(this).parent().fadeOut('slow', function() {
							$(this).remove();
						});
					});
				}
	});
</script>