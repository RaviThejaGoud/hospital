<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<div class="block grid_12" >
	<div class="block_head">
		<h2>
			Post Wishes
		</h2>
	</div>
	<div class="block_content">
		<jsp:include page="/common/messages.jsp"></jsp:include>
		<s:form id="sendWishes" action="ajaxSendWishesToFriend" method="post" theme="css_xhtml" namespace="/student">
		 <s:hidden name="rollNumber" value="%{anyId}"/>
		 <s:hidden name="stAccountId" value="%{selectedId}"/>
			<table>
			<tr>
					<td>
						<b>Title:</b> <s:textfield name="wishTitle"     cssClass="text small required"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						<s:textarea id="editor" name="wishDescription"     theme="simple" />
					</td>
				</tr>
			   <tr>
				<td>
					<div>
						<sj:submit   targets="studentContent" value="Send" onClickTopics="sendWishesFormValidation"
				        cssClass="submit small" indicator="indicator" />
					</div>
				</td>
			</table>
		</s:form>
		</div>
		</div>
<script type="text/javascript">
changePageTitle('BirtDay Wishes');
$(document).ready(
function() {
	$.subscribe('sendWishesFormValidation', function(event, data) {
		  if ($('#sendWishes').valid())
               return true;
           else
               return false;
	});
});
$.subscribe('cancelRegistration', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
function clearTextField(field){
    	if (field.defaultValue == field.value) field.value = '';
    	else if (field.value == '') field.value = field.defaultValue;

	}
	if(CKEDITOR.instances.editor)
					CKEDITOR.remove(CKEDITOR.instances.editor);	
				CKEDITOR.replace( 'editor',{charcount_limit : 1000});
				
</script>