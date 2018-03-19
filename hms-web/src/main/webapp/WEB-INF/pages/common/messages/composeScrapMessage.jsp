<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"> </script>
<style type="text/css">
	@import url("${pageContext.request.contextPath}/styles/newCss/autocompleteStyles.css");
</style>
<div id="autoCompleterRollText">
<s:form action="ajaxSendScrapMessage" id="sendCompMsg" theme="simple" method="post" namespace="/common" cssClass="form-horizontal">
	<s:hidden name="accountIds" cssClass='accountIds' />
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>To :
			</label>
			<div id="friends" class="col-md-9">
				<input id="search_role" type="text" class="ui-autocomplete-input form-control" style="border: 1px solid #FFFFFF;"/>
			</div> 
		</div>
		<div  class="col-md-2"></div><span class="help-block">(Enter at least 3 chars to get closer match.)</span>
		<div class="form-group">
		<label class="control-label col-md-2">
			<span class="required">*</span>Message Type :
		</label>
			<div class="col-md-5">
				<s:select id="status"   headerKey=""   
						headerValue="- Select -" name="scrapMessage.messageType" cssClass="form-control required" 
						list="#{'G':'General','C':'Complaints','S':'Suggestions'}" />
		</div></div>
		<div class="form-group">
		<label class="control-label col-md-2">
			 <span class="required">*</span>Message :
		</label>
		<div class="col-md-9">
			<sj:textarea name="scrapMessage.scrapDescription" cssClass="required form-control col-md-9 word_count" 
				maxCharsData="1045" id="scrapDescription" rows="5" cols="40"></sj:textarea>
			<div class="counter"></div>
		</div>
		</div>
	<div class="col-md-5" style="margin: 7px 0 0 140px">
		<sj:submit   cssClass="submitBt btn blue" value="Submit" validate="true" onBeforeTopics="sendMsgFormValidation"   targets="mainContentDiv" formIds="sendCompMsg" />
		<s:url id="doCancelScrapMessage" action="ajaxDoInboxGetScrapMessagesList" includeParams="all"></s:url>
		<sj:a href="%{doCancelScrapMessage}" cssClass="btn default" targets="mainContentDiv">Cancel</sj:a>
	</div>
</s:form>
</div>
<div id="findStudent" class="findStudents"></div>
<script type="text/javascript">
	$(document).ready(function() {
	changePageTitle('Compose Messages');		 
		$.subscribe('sendMsgFormValidation',function(event, data) {
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
	      var selectedSchAccountIds = [];
          $('.staffStudentsId').each( function () {
        	  selectedAccountIds = $(this).attr('id');
        	  if (isNonEmpty(selectedAccountIds)) {
                  selectedSchAccountIds.push(selectedAccountIds);
        	  }
          });
          $('.accountIds').val(selectedSchAccountIds);
          nonIds=selectedSchAccountIds+",";
			if(nonIds==","){
				nonIds=selectedSchAccountIds+"0,";
			}
	});
	$("input#search_role").autocomplete({
	     minLength: 3,
	     source: function (req, response1) {
	         //pass request to server
	         var searchword = $('#search_role').val();
	         $('#search_role').addClass('spinner');
	         $.ajax({
	             dataType: 'json',
	             type: "POST",
	             data: "searchword=" + searchword + "&anyTitle=" + nonIds,
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
										label: extractLast(item.firstName + ' ' + l_LastName + '(' + item.role + ')'),
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
	             span.insertBefore("#search_role");
		 			var selectedSchAccountIds = [];
		 				$('.staffStudentsId').each(function() {
		 					selectedAccountIds = $(this).attr('id');
		 					if (isNonEmpty(selectedAccountIds)) {
		 						selectedSchAccountIds.push(selectedAccountIds);
		 					}
		 				});
		 			$(".accountIds").val(selectedSchAccountIds);
		             $("#search_role").val("");
		             $('#search_role').removeClass('spinner');
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
