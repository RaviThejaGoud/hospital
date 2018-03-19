<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp" %>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/validation/jquery.validate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<s:if test="%{categories != null && !categories.isEmpty()}">
				<table class="striped" width="100%" cellpadding="1" cellspacing="1">
					<thead>
						<tr>
							<th width="45%" class="head">
								Category Name
							</th>
							<th class="head">
								Description
							</th>
							<th width="10%" class="head">
								Edit
							</th>
						</tr>
					</thead>
					<s:iterator value="categories">
						<tr class="loaded" > 
							<td>
								<s:url id="removeCategory" action="ajaxCheckCategory"
									escapeAmp="false">
									<s:param name="id" value="id"></s:param>
								</s:url>
								<s:div cssStyle="margin-top:-1px;" cssClass="close emsEventRemove" id='%{removeCategory}' theme="simple" title="Delete this Category" ></s:div>
			
									<s:property value="name" />
							</td>
							<td style="vertical-align: top;">
								<s:property value="description" />
							</td>
							<td>
								<s:url id="doEditCategory" action="ajaxDoEditCategory"
								includeParams="all" escapeAmp="false">
								<s:param name="id" value="{id}" />
								</s:url>
								<sj:a href="%{doEditCategory}" onCompleteTopics="doInitEditCategory"
									onBeforeTopics="cleanOpenRows" indicator="indicator"
									targets="editCategory%{id}" button="false"
									buttonIcon="ui-icon-plus"  cssClass="normalEdit" title="Edit">
								</sj:a>
							</td>
						</tr>
						<tr id="editCategory<s:property value='id' />" style="display: none;" class='load'>
				        </tr>
					</s:iterator>
				</table>
		  </s:if>
		<s:else>
	Currently there are no Categories
</s:else>

<script type="text/javascript">
	$.subscribe('doInitEditCategory', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});

	
  $(document).ready(function() {
  if ($('div.emsEventRemove')) {
			$('div.emsEventRemove').unbind('click');
			$("div.emsEventRemove").click(function() {
				confirmDeleteCategory(this);
			});
		}
  });
  function confirmDeleteCategory(event) {
	thishref = $(event).attr('id');
	var categoryId = thishref.split("=");
	if ($(event).next('.question').length <= 0) {
		$(event)
				.after(
						'<div class="question">Are You Sure?<br/> <span class="yes">Yes</span><span class="cancel">Cancel</span></div>');
	}
	$(event).next('.question').animate( {
		opacity : 1
	}, 300);
	$('.yes').unbind('click');
	$('.yes').bind('click', function() {
		var prdDiv = $(this).parents('.question');
		prdDiv.html('Processing...');
		$.ajax( {
		url : thishref,
			cache : false,
			success : function(response) {
			 if(response.match('No events')!=null){
			     deleteCategory(categoryId[1]);
			 }else{
			  $('#categoryEvents').html(response);
			     var isEventDelete=confirm('This category have events.Are You Sure?');
			     if(isEventDelete){
			 	     deleteCategory(categoryId[1]);
			     }
			   }
			 }
			
		   });
		});
			
		$('.cancel').unbind('click');
	    $('.cancel').bind('click', function() {
		$(this).parents('.question').fadeOut(300, function() {
			$(this).remove();
		});
		return false;
	});
  }
  function deleteCategory(categoryId){
        $.ajax( {
	        url : jQuery.url.getChatURL("/calendar/ajaxRemoveCategory.do?id="+categoryId),
			cache : false,
			success : function(html) {
			 $('#categoryEvents').html(html);
			}
		});
  }
  
  </script>