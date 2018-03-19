<%@ include file="/common/taglibs.jsp"%>
<style type="text/css">
	@import url("${pageContext.request.contextPath}/styles/newCss/autocompleteStyles.css");
</style>
<s:form id="addStudentSiblings" action="ajaxAddStudentSiblings" cssClass="form-horizontal" namespace="/student" method="post" theme="simple">
	<s:hidden name="accountIds" cssClass='accountIds' />
	<s:hidden name="tempId" value="%{tempId}" id="studentAccountId"></s:hidden>
	<s:hidden name="tempId1" value="%{tempId1}"></s:hidden>
	<div class="form-body">
	<div class="row">
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Student Sibling Name :
			</label>
			<div id="friends" class="col-md-9">
				<input id="search_role" type="text" class="ui-autocomplete-input form-control" style="border: 1px solid #FFFFFF;"/>
			</div> 
		</div>
	</div>
	<div class="form-actions">
	<div class="col-md-offset-3 col-md-6">
		<img src="../img/bg/bigWaiting.gif" alt="Loading..." title="Loading..." id="indicator"
			style="display: none; background-repeat: no-repeat; position: relative; z-index: 1000; bottom: 200px; margin-left: 240px;" />
		<sj:submit cssClass="submitBt btn blue" value="Submit" targets="studentEditContentDiv" validate="true" onBeforeTopics="siblingFormValidation" formIds="addStudentSiblings" tabindex="2" />
		<s:url id="doCancelForm" action="ajaxDoAddStudentSiblings" includeParams="all" escapeAmp="false" namespace="/student">
			<s:param name="tempId1" value="0" />
			<s:param name="tempId" value="%{tempId}" />
		</s:url>
		<sj:a href="%{doCancelForm}" targets="studentEditContentDiv"  cssClass="btn default">Cancel</sj:a>
	</div>
	</div></div>
</s:form>
<div id="deleteContentDiv">
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<div class="spaceDiv"></div><div class="spaceDiv"></div>
		<jsp:include page="/WEB-INF/pages/admin/student/ajaxViewStudentSiblingsList.jsp" />
	</s:if>
</div>
<div id="findStudent" class="findStudents"></div>
<script type="text/javascript">
	$(document).ready(function() {
	changePageTitle('Compose Messages');	
	$.destroyTopic('siblingFormValidation');
		$.subscribe('siblingFormValidation',function(event, data) {
			if(isNonEmpty($('.accountIds').val())){
				return true;
			}else{
				alert("Please select at least one user.");
				 event.originalEvent.options.submit=false;	
			}
		});
	});
	var nonIds = '0,';
	$(".remove", document.getElementById("friends")).live("click", function () {
	     //remove current friend  
	     $(this).parent().remove();
	     nonIds = nonIds.replace($(this).parent().attr('id') + ',', '').replace(/^\,/, "").replace(/,$/, "");
	     //correct 'search_role' field position  
	     if ($("#friends div").length === 0) {
	         $("#search_role").css("top", 0);
	     }
	      var selectedSchAccountIds = '';
          $('.staffStudentsId').each( function () {
              selectedSchAccountIds += $(this).attr('id') + ',';
          });
          selectedSchAccountIds += '';
          $('.accountIds').val(selectedSchAccountIds);
	});
	$("input#search_role").autocomplete({
	     minLength: 3,
	     source: function (req, response1) {
	         //pass request to server
	         var searchword = $('#search_role').val();
	         var nonIds = $('#studentAccountId').val();
	         $.ajax({
	             dataType: 'json',
	             type: "POST",
	             data: "searchword=" + searchword + "&anyTitle=" + nonIds+ "&anyId=ROLE_STUDENT",
	             url: "ajaxSearchFirstNameAndLastName.do",
	             cache: false,
	             success : function(response) {
				if (response.data) {
					var data = response.data;
					var accountId;
					var firstName;
					var lastName;
					var role;
					var inputObj;
					if (data.length >= 1) {
						for ( var i = 0; i < data.length; i++) {
							accountId = data[i].accountId;
							firstName = data[i].firstName;
							lastName = data[i].lastName;
							roleName  = data[i].role;
							//alert("accountId="+accountId+"&firstName="+firstName+"&lastName="+lastName+"&role="+roleName);
						}
					}
					response1($.map(data, function (item) {
	                         if (item.accountId != 0) {
		                          if(isNonEmpty(item.role)){
									var l_LastName = '';
										if(isNonEmpty(item.lastName ))
											l_LastName = item.lastName ;
									return {
										label: extractLast(item.firstName + ' ' + l_LastName),
										id: item.accountId,
										value: item.role
								}
								}else{
	                           		  return {
		                                 label: extractLast(item.firstName + ' ' + '' + '(' + item.role + ')'),
		                                 id: item.accountId,
		                                 value: item.role
		                             }
	                             }
	                              
	                         } else {
	                             return {
	                                 value: item.firstName,
	                                 id: item.accountId
	                             }
	                         }
	                         
	                     }))
				        }
	                $("#displaySearchResult").data(data).show(); 
	             }
	         });
	     },
	     select: function (event, ui) {
	         //create formatted friend  
	         if (ui.item.id == 0) {
	             return false;
	         } else {
	             var valueName = ui.item.label;
	             nonIds += ui.item.id + ',';
	             var friend = valueName,
	                 span = $("<span class='staffStudentsId color-Type-" + ui.item.value + "'  id='" + ui.item.id + "'>").text(friend),
	                 a = $("<a>").addClass("remove").attr({
	                     href: "javascript:",
	                     title: "Remove " + friend
	                 }).text("x").appendTo(span);
	             //add friend search_role friend div  
	             span.insertBefore("#search_role");
	
	             var selectedSchAccountIds = '';
	             $('.staffStudentsId').each(
	
	             function () {
	                 selectedSchAccountIds += $(this).attr('id') + ',';
	             });
	             selectedSchAccountIds += '';
	
	             $('.accountIds').val(selectedSchAccountIds);
	             $("#search_role").val("");
	             return false;
	         }
	     },
	     //define select handler  
	     change: function () {
	         //prevent 'search_role' field being updated and correct position  
	         $("#search_role").val("").css("top", 2);
	     }
	 });
	 $("#friends").click(function () {
	     //focus 'search_role' field  
	     $("#search_role").focus();
	 });
	 function split(val) {
	     return val.split(/,\s*/);
	 }
	 function extractLast(term) {
	     return split(term).pop();
	 }
</script>